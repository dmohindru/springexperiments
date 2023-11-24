package dev.dmohindru.StringProcessorServer.processor;

import dev.dmohindru.StringProcessorServer.constants.ResponseCode;
import dev.dmohindru.StringProcessorServer.constants.StringOperation;
import dev.dmohindru.StringProcessorServer.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

@RequiredArgsConstructor
@Slf4j
public class HttpConnectionHandler implements Callable<Void> {
    private final Socket socket;
    private final int LEN_FIELD_BYTES = 8;
    private final int OP_FIELD_BYTES = 2;

    // TODO Externalise it to property file
    private final String errorMessage = "Error processing the data";

    @Override
    public Void call() throws Exception {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            InputStream in = new BufferedInputStream(socket.getInputStream());

            while (true) {
                byte[] dataLengthBytes = in.readNBytes(LEN_FIELD_BYTES);
                byte[] operationBytes = in.readNBytes(OP_FIELD_BYTES);

                Integer dataLength = getHeaderLength(dataLengthBytes);
                StringOperation strOp = StringOperation.getOperationFromValue(getHeaderLength(operationBytes));

                byte[] dataBytes = in.readNBytes(dataLength);

                Response response = processData(dataBytes, strOp);
                byte[] messageByte = response.getMessageByte();
                // use null as connection termination trigger
                if (messageByte == null) {
                    in.close();
                    out.close();
                    socket.close();
                    break;
                }

                // Message length
                out.writeLong(messageByte.length);

                // Operation result
                out.writeShort(response.getResponseCode().getCode());

                // Actual data
                out.write(messageByte);
            }

        } catch (IOException ex) {
            // TODO handle properly and send error messages or terminate connection
            log.error("Error in handling connection ex: {}", ex.toString());
        }
        return null;
    }

    private Integer getHeaderLength(byte[] dataLengthBytes) {
        int intValue = 0;
        for (byte b : dataLengthBytes) {
            intValue = (intValue << 8) + (b & 0xFF);
        }

        return intValue;
    }

    private Response processData(byte[] dataBytes, StringOperation op) {
        try {
            String data = new String(dataBytes, StandardCharsets.UTF_8);
            String processedData = switch (op) {
                case LOWER_CASE -> data.toLowerCase();
                case UPPER_CASE -> data.toUpperCase();
                case INVERT_CASE -> StringUtils.swapCase(data);
                default -> null;
            };

            return Response
                    .builder()
                    .messageByte(processedData == null ? null : processedData.getBytes())
                    .responseCode(ResponseCode.SUCCESS)
                    .build();

        } catch (Exception ex) {
            return Response
                    .builder()
                    .messageByte(errorMessage.getBytes())
                    .responseCode(ResponseCode.ERROR)
                    .build();
        }

    }
}

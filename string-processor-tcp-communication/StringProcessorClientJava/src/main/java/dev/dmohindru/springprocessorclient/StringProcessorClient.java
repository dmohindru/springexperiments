package dev.dmohindru.springprocessorclient;

import dev.dmohindru.springprocessorclient.constants.ResponseCode;
import dev.dmohindru.springprocessorclient.constants.StringOperation;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class StringProcessorClient implements Closeable {
    private final String hostName;
    private final int hostPort;

    private final Socket socket;

    private final DataOutputStream outputStream;
    private final InputStream inputStream;

    private static final int LEN_FIELD_BYTES = 8;
    private static final int OP_FIELD_BYTES = 2;


    public StringProcessorClient(String hostName, int hostPort) throws IOException {
        this.hostName = hostName;
        this.hostPort = hostPort;
        socket = new Socket(hostName, hostPort);
        outputStream = new DataOutputStream(socket.getOutputStream());
        inputStream = new BufferedInputStream(socket.getInputStream());
    }

    public String processString(String inputString, StringOperation op) throws IOException {
        byte[] dataBytes = inputString.getBytes();
        // Header: Length of data
        outputStream.writeLong(dataBytes.length);
        // Header: Operation
        outputStream.writeShort(op.getValue());
        // Actual Data
        outputStream.write(dataBytes);

        // Read response
        byte[] dataLengthBytes = inputStream.readNBytes(LEN_FIELD_BYTES);
        byte[] opResult = inputStream.readNBytes(OP_FIELD_BYTES);

        Integer responseDataLength = getHeaderLength(dataLengthBytes);
        ResponseCode responseCode = ResponseCode.getResponseFromValue(getHeaderLength(opResult));

        if (responseCode == ResponseCode.ERROR) {
            throw new IOException(String.format("String operation [%s] failed for data [%s]", op, inputString));
        }

        byte[] processedBytes = inputStream.readNBytes(responseDataLength);



        return new String(processedBytes, StandardCharsets.UTF_8);
    }


    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }

    private Integer getHeaderLength(byte[] dataLengthBytes) {
        int intValue = 0;
        for (byte b : dataLengthBytes) {
            intValue = (intValue << 8) + (b & 0xFF);
        }

        return intValue;
    }
}

package dev.dmohindru.s3cameldemo.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws2.s3.AWS2S3Constants;
import org.apache.camel.spi.Registry;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import java.util.Random;

//@Component
@Slf4j
public class S3ToFileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // Get camel context
        CamelContext camelContext = this.getContext();

        // Get registry from camel context to set S3 client to be used in aws2-s3 route
        Registry registry = camelContext.getRegistry();

        // Create S3Client
        S3Client s3Client = S3Client
                .builder()
                .credentialsProvider(
                        ProfileCredentialsProvider.builder().profileName("dhruv").build())
                .build();

        // Set S3Client in camel registry
        registry.bind("s3Client", s3Client);

        /*
        Description of below camel route
        --------------------------------
        1. On every five seconds a random number is generated in range (0-9)
        2. On the basis of random number generated a random file name is generated
           of a format File{0-9}.txt
        3. Generated file name is then downloaded from S3 bucket
        4. Downloaded file is then saved into folder data/inbox
         */

        from("quartz://mytimer?trigger.repeatInterval=5000")
                // Route ID
                .routeId("Quartz-timer-S3-File")
                // Log every time a quartz component is invoked
                .log("quartz timer invoked")
                // Set exchange property
                .setProperty("randomNumber", () -> {
                    Random random = new Random();
                    return random.nextInt(10);
                })
                // Processor to set camel header for file name to be read from S3 bucket
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Integer randomNumber = exchange.getProperty("randomNumber", Integer.class);
                        log.info("Next random integer: " + randomNumber);
                        // IMPORTANT all the folder structure in S3 bucket must be included in KEY
                        // For this example files are located in following S3 location
                        // s3://my-test-bucket-dhruv/myfolder1/File0.txt
                        exchange.getIn().setHeader(AWS2S3Constants.KEY, "myfolder1/File"+randomNumber+".txt");
                    }
                })
                // Log name of the file to be read
                .log("S3 file name to be processed ${header.CamelAwsS3Key}")
                // AWS s3 route
                .to("aws2-s3://my-test-bucket-dhruv?amazonS3Client=#s3Client&operation=getObject&deleteAfterRead=false")
                // Exception handling if file in S3 bucket not found
                .onException(NoSuchKeyException.class)
                // Mark Exception as handled
                .handled(true)
                // Log any error message
                .log("Key not found")
                // End current route here so that processing doesn't go down stream
                .end()
                // Processor to set camel header for file name to be written to destination folder
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String readFileName = exchange.getIn().getHeader(AWS2S3Constants.KEY, String.class);
                        log.info("Name of S3 file read: " + readFileName);
                        String body = exchange.getIn().getBody(String.class);
                        log.info("Content of body: " + body);
                        String destinationFileName = "Local-" + readFileName;
                        exchange.getIn().setHeader(Exchange.FILE_NAME, destinationFileName);
                    }
                })
                // Log file name about to be written into destination folder
                .log("About to write following file: ${header.CamelFileName}")
                .to("file:data/inbox");

        /* when(body().isNull()).
                    stop().

                when(body().isNotNull()).
                bean(Utility.class,"incomingSqlData") */
    }
}

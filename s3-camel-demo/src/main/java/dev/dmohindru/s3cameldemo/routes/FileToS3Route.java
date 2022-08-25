package dev.dmohindru.s3cameldemo.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws2.s3.AWS2S3Constants;
import org.apache.camel.spi.Registry;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class FileToS3Route extends RouteBuilder {

    private static String fileEndpoint;

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
        Description of this route
        -------------------------
        1. This route is called from s3 to file route
        2. It reads the file from dynamic folder (on the basis of date) and wild card in file name
        3. Processes the content of file
        4. Uploads the file to different folder in AWS S3
         */

        // Direct route called from s3-to-file route
        from("direct:file-to-S3")
                // Route ID
                .routeId("file-to-s3")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // make folder name by date format year-month-day_of_year
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
                        LocalDateTime now = LocalDateTime.now();
                        String dateFolder = formatter.format(now);
                        // retrieve original file name set previously in route
                        String originalFileName = exchange.getProperty("originalFileName", String.class);
                        // prepare uri for file to be read
                        fileEndpoint = "file://data/inbox/"
                                + dateFolder
                                + "?noop=true&antInclude="+ originalFileName +"*";
                        exchange.getIn().setHeader("fileEndpoint", fileEndpoint);
                    }
                })
                // Log file endpoint uri used
                .log("Reading file with uri: " + "${header.fileEndpoint}")
                // Read file from dynamic uri
                .pollEnrich().simple("${header.fileEndpoint}")
                .process(new Processor() {

                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String fileNameConsumed = exchange.getIn().getHeader(Exchange.FILE_NAME_CONSUMED, String.class);
                        log.info("File name consumed: " + fileNameConsumed);

                        // Process body content
                        String bodyContent = exchange.getIn().getBody(String.class);
                        bodyContent += "\nModified by camel project written by Dhruv";
                        exchange.getIn().setBody(bodyContent, String.class);
                        // Set file name/key to be uploaded to S3
                        exchange.getIn().setHeader(AWS2S3Constants.KEY, "myfolder1/processed/" + fileNameConsumed);
                    }
                })
                .to("aws2-s3://my-test-bucket-dhruv?amazonS3Client=#s3Client");
    }
}

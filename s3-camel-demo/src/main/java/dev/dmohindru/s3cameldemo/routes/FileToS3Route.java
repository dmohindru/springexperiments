package dev.dmohindru.s3cameldemo.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.Registry;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class FileToS3Route extends RouteBuilder {

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

    }
}

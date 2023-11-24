package dev.dmohindru.springprocessorclient;

import dev.dmohindru.springprocessorclient.constants.StringOperation;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class StringProcessorClientTest {

    @Test
    void testStringProcessorClient() throws IOException {
        String testString = "I am a test String";
        String response;
        try(StringProcessorClient client = new StringProcessorClient("localhost", 5000)) {
             response = client.processString(testString, StringOperation.LOWER_CASE);
             response = client.processString(testString, StringOperation.UPPER_CASE);
             response = client.processString(testString, StringOperation.INVERT_CASE);
            System.out.println("------END--------");
        }

    }
}

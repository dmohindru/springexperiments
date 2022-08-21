package dev.dmohindru.as2demo;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.as2.AS2Component;
import org.apache.camel.component.as2.AS2Configuration;
import org.apache.camel.component.as2.api.AS2EncryptionAlgorithm;
import org.apache.camel.component.as2.api.AS2SignatureAlgorithm;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopierWithCamel {
    public static void main(String args[]) throws Exception {
        // create CamelContext
        CamelContext context = new DefaultCamelContext();

        // Get AS2 component
        AS2Component as2Component = context.getComponent("as2", AS2Component.class);
        // Create AS2 configuration object
        AS2Configuration as2Configuration = new AS2Configuration();
        // Set Encryption Algorithm – AES256
        as2Configuration.setEncryptingAlgorithm(AS2EncryptionAlgorithm.AES256_CBC);
        // TODO Set Encryption certificate chain

        // Set Signing Algorithm – SHA256
        as2Configuration.setSigningAlgorithm(AS2SignatureAlgorithm.SHA256WITHRSA);
        // TODO Set Signing certificate chain

        // TODO Set signing private key


        // TODO set Decrypting private key -- research do we need this
        // Set AS2Configuration in the component
        as2Component.setConfiguration(as2Configuration);


        // add our route to the CamelContext
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:data/inbox?noop=true")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {

                            }
                        })
                        .to("file:data/outbox");
            }
        });

        // start the route and let it do its work
        context.start();
        Thread.sleep(10000);

        // stop the CamelContext
        context.stop();
    }
}

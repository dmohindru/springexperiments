import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;


public class MyCamelRoute {
    public static void main(String[] args) throws Exception {

        // create CamelContext
        CamelContext camelContext = new DefaultCamelContext();

        // connect to activeMQ broker
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.56.101:61616");
        camelContext.addComponent("jms",
                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        // add route to camel context
        camelContext.addRoutes(new RouteBuilder() {

            @Override
            public void configure() {

                // Route from ftp server to incomingOrders
                from("ftp://192.168.56.101/data/inbox?username=dhruv&password=poonam&noop=true")
                        .process(exchange -> System.out.println("We just downloaded: "
                                + exchange.getIn().getHeader("CamelFileName")))
                        .to("jms:queue:incomingOrders");

                // Route from incomingOrders to content based routing
                from("jms:queue:incomingOrders")
                        .choice()
                        .when(header("CamelFileName").endsWith(".json"))
                                .to("jms:jsonOrders")
                        .when(header("CamelFileName").endsWith(".edi"))
                            .to("jms:ediOrders")
                        .otherwise()
                            .to("jms:badOrders");

                // Route from jsonOrders to multicast production, accounts via wiretap to audit queues
                // TODO: Implement following features
                // TODO: 1. Filter out test files
                // TODO: 2. Process file to generate common structure
                from("jms:queue:jsonOrders")
                        .wireTap("jms:audit")
                        .multicast()
                        .to("jms:production", "jms:accounts");

                // Route from jsonOrders to multicast production, accounts via wiretap to audit queues
                // TODO: Implement following features
                // TODO: 1. Filter out test files
                // TODO: 2. Process file to generate common structure
                from("jms:queue:ediOrders")
                        .wireTap("jms:audit")
                        .multicast()
                        .to("jms:production", "jms:accounts");

                // Route from production queue to recipient list
                // TODO: Implement Recipient list as per the content of message
                from("jms:queue:production")
                        .setHeader("recipients", method(RecipientBeans.class, "myRecipients"))
                        .recipientList(header("recipients"));



            }
        });

        camelContext.start();
        Thread.sleep(10000);

    }
}

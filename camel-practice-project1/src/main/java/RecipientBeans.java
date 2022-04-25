import java.util.List;

public class RecipientBeans {
    public List<String> myRecipients() {
        // TODO: Implement Recipient list as per the content of message
        return List.of("jms:priority", "jms:normal");
    }
}

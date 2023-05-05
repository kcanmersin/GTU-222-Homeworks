package homework1;

/**
 * 
 * The Message class represents a message sent between two accounts
 */
public class Message {

    private int messageId;

    private int senderId;

    private int receiverId;

    private String content;

    /**
     * 
     * Constructs a new Message object
     * 
     * @param messageId  the unique identifier of the message
     * @param senderId   the identifier of the party sending the message
     * @param receiverId the identifier of the party receiving the message
     * @param content    the content of the message
     */
    public Message(int messageId, int senderId, int receiverId, String content) {

        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message{" + "messageId=" + messageId + ", senderId=" + senderId + ", receiverId=" + receiverId
                + ", content= " + content;
    }

}
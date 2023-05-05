package arraylist;

/**
 * 
 * The Comment class represents a comment made by a user on a post
 */
public class Comment extends Interaction {

    private String content;

    /**
     * 
     * Creates a new Comment instance
     * 
     * @param interactionId the ID of the comment interaction
     * @param accountId     the ID of the account that made the comment
     * @param postId        the ID of the post that the comment is made on
     * @param content       the content of the comment
     */
    public Comment(int interactionId, int accountId, int postId, String content) {
        super(interactionId, accountId, postId);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
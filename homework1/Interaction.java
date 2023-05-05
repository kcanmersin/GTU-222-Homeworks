package homework1;

/**
 * 
 * The Interaction class represents an interaction made by an account on a post
 * This is an abstract class and cannot be instantiated on its own
 */
public abstract class Interaction {

    private int interactionId;

    private int accountId;
    private int postId;

    /**
     * 
     * Constructs a new Interaction object
     * 
     * @param interactionId the ID of the interaction
     * @param accountId     the ID of the account that made the interaction
     * @param postId        the ID of the post that the interaction was made on
     */
    public Interaction(int interactionId, int accountId, int postId) {
        this.interactionId = interactionId;
        this.accountId = accountId;
        this.postId = postId;
    }

    public int getInteractionId() {
        return interactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getPostId() {
        return postId;
    }
}
package homework1;

/**
 * 
 * The Like class represents a like interaction made by an account on a post
 */
public class Like extends Interaction {

    /**
     * 
     * Constructs a new Like object
     * 
     * @param interactionId the ID of the like interaction
     * @param accountId     the ID of the account that made the like interaction
     * @param postId        the ID of the post that the like interaction was made on
     */
    public Like(int interactionId, int accountId, int postId) {
        super(interactionId, accountId, postId);
    }
}

package homework1;

/**
 * 
 * The Post class represents a social media post created by an account
 */
public class Post {

    private int postId;

    private int accountId;

    private Like[] likes;

    private String content;

    private Comment[] comments;

    public int commentCount;

    private int likeCount;

    /**
     * 
     * Constructs a new Post object with the specified post ID, account ID, and
     * content
     * 
     * @param postId    the unique identifier of the post
     * @param accountId the identifier of the account that created the post
     * @param content   the content of the post
     */
    public Post(int postId, int accountId, String content) {
        this.postId = postId;
        this.accountId = accountId;
        this.likeCount = 0;
        this.likes = new Like[100];
        this.comments = new Comment[100];
        this.content = content;
    }

    public int getAccountId() {
        return accountId;
    }

    /**
     * 
     * Returns a string representation of the post
     * 
     * @return a String
     */
    @Override
    public String toString() {
        return "Post #" + postId + ": " + content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    /**
     * 
     * Returns the comment at the specified index.
     * 
     * @param index the index of the desired comment
     * @return the comment at the specified index
     */
    public Comment getComments(int index) {
        return comments[index];
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getPostId() {
        return postId;
    }

    /**
     * 
     * Returns the like at the specified index.
     * 
     * @param index the index of the desired like
     * @return the like at the specified index
     */
    public Like getLikes(int index) {
        return likes[index];
    }

    public String getContent() {
        return content;
    }

    /**
     * 
     * Adds a like to the posts array of likes
     * 
     * @param like the like to be added
     */
    public void addLike(Like like) {
        likes[likeCount++] = like;
    }

    /**
     * 
     * Adds a comment to the posts array of comments
     * 
     * @param comment the comment to be added
     */
    public void addComment(Comment comment) {
        comments[commentCount++] = comment;
    }
}

package ldlinkedlist;

public class Post {

    private int postId;
    private int accountId;
    private LDLinkedList<Like> likes;
    private String content;
    private LDLinkedList<Comment> comments;

    public Post(int postId, int accountId, String content) {
        this.postId = postId;
        this.accountId = accountId;
        this.content = content;
        this.likes = new LDLinkedList<>();
        this.comments = new LDLinkedList<>();
    }

    public int getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "Post #" + postId + ": " + content;
    }

    public int getLikeCount() {
        return likes.size();
    }

    public Comment getComment(int index) {
        return comments.get(index);
    }

    public LDLinkedList<Comment> getComments() {
        return comments;
    }

    public int getCommentCount() {
        return comments.size();
    }

    public int getPostId() {
        return postId;
    }

    public Like getLike(int index) {
        return likes.get(index);
    }

    public LDLinkedList<Like> getLikes() {
        return likes;
    }

    public String getContent() {
        return content;
    }

    public void addLike(Like like) {
        likes.add(like);
    }

    public void delLike(Like like) {
        likes.remove(like);
    }

    public void delComment(Comment comment) {
        comments.remove(comment);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}

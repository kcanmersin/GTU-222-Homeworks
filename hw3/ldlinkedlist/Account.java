package ldlinkedlist;

/**
 * 
 * The Account class represents a user account in a social media application
 */
public class Account {
    private int accountId;
    private LDLinkedList<Account> blockedAccounts;
    private String userName;
    private String birthdate;
    private String location;
    private LDLinkedList<Account> followers;
    private LDLinkedList<Account> following;
    private LDLinkedList<Message> inbox;
    private LDLinkedList<Message> outbox;
    private LDLinkedList<Post> posts;
    private LDLinkedList<String> history;

    /**
     * 
     * Creates an Account object with the specified parameters
     * 
     * @param accountId the unique identifier of the account
     * 
     * @param userName  the username of the account owner
     * 
     * @param location  the location of the account owner
     * 
     * @param birthdate the birthdate of the account owner
     * @param accounts  control for the username dublicates
     */
    public Account(int accountId, String userName, String location, String birthdate, LDLinkedList<Account> accounts) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName)) {
                return;
            }
        }
        this.accountId = accountId;
        this.userName = userName;
        this.birthdate = birthdate;
        this.location = location;
        this.blockedAccounts = new LDLinkedList<Account>();
        this.followers = new LDLinkedList<Account>();
        this.following = new LDLinkedList<Account>();
        this.inbox = new LDLinkedList<Message>();
        this.outbox = new LDLinkedList<Message>();
        this.posts = new LDLinkedList<Post>();
        this.history = new LDLinkedList<String>();
        System.out.println("An account with username " + userName + " has been created.");
    }

    private boolean wiewScreenOn = false;
    private boolean postScreenOn = false;
    private boolean logged = false;
    /**
     * It show at that moment which account you wiew
     */
    Account currentWiewed;

    public void updateHistory(String content) {
        history.add(content);
    }

    public String getBirthdate() {
        return birthdate;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getLocation() {
        return location;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * 
     * Checks if the user is logged in
     * 
     * @return true if the user is logged in, false otherwise
     */
    public boolean isLogged() {
        if (logged == false)
            System.out.println("You need to login first");
        return logged;
    }

    /**
     * Logs into this account, if no other account is already logged in.
     *
     * @param accounts an array of all the accounts in the social media application
     */

    public void login(LDLinkedList<Account> accounts) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account != this) {
                if (account.logged == true) {
                    System.out.println("Another account is logged in...");
                    return;
                }
            }
        }
        if (this.logged == false) {
            this.logged = true;
            System.out.println("Logging into an account (username:" + getUserName() + ")...");
        } else {
            System.out.println("User already logged in.....");
        }
    }

    public boolean logout() {

        if (!logged) {
            System.out.println("User is not logged in.....");
            return false;
        } else {
            System.out.println("Logging out from account(" + getUserName() + ")...");
            logged = false;
            return true;
        }
    }

    /**
     * 
     * Blocks the specified account
     * 
     * @param targetAccount the account to be blocked
     */
    public void block(Account account) {
        if (!isLogged()) {
            return;
        }
        if (!blockedAccounts.contains(account)) {
            blockedAccounts.add(account);
            System.out.println("Account with username " + account.getUserName() + " has been blocked.");

            if (following.contains(account)) {
                following.remove(account);
                account.followers.remove(this);
            }
            this.updateHistory(new String("You have blocked " + account.getUserName()));
            return;
        } else {
            System.out.println("Account with username " + account.getUserName() + " is already blocked.");
        }
    }

    /**
     * 
     * unlocks the specified account
     * 
     * @param targetAccount the account to be unblocked
     */
    public void unblock(Account account) {
        if (!isLogged()) {
            return;
        }
        if (blockedAccounts.contains(account)) {
            blockedAccounts.remove(account);
            System.out.println("Account with username " + account.getUserName() + " has been unblocked.");

            this.updateHistory(new String("You have unblocked " + account.getUserName()));

        } else {
            System.out.println("Account with username " + account.getUserName() + " is not blocked.");
        }
    }

    /**
     * 
     * Checks if the specified account is blocked by this account
     * 
     * @param targetAccount the account to check
     * @return true if the account is blocked, false otherwise
     */
    public boolean isBlocked(Account account) {
        if (account.blockedAccounts.contains(this)) {
            System.out.println("The account with username " + account.getUserName() + " has blocked your account.");
            return true;
        }
        if (blockedAccounts.contains(account)) {
            System.out.println("You have blocked the account with username " + account.getUserName());
            return true;
        }
        return false;
    }

    public boolean isExist(Account account) {
        if (account == null) {
            System.out.println("Account does not exist.");
        }
        return true;
    }

    public boolean isFollowing(Account targetAccount) {
        if (following.contains(targetAccount)) {
            return true;
        }
        System.out.println("You need to follow that account first!!");
        return false;
    }

    /**
     * follow the wanted account
     * 
     * @param targetAccount
     */
    public void follow(Account account) {
        if (!isLogged() || !isExist(account) || isBlocked(account)) {
            return;
        }
        if (account == this) {
            System.out.println("You cannot follow yourself");
            return;
        }
        if (following.contains(account)) {
            System.out.println("You are already following this account.");
            return;
        }
        following.add(account);
        account.followers.add(this);

        System.out.println("You are now following " + account.getUserName() + ".");
        this.updateHistory(new String("You followed " + account.getUserName()));
    }

    public void unfollow(Account account) {
        if (!isLogged() || !isExist(account) || isBlocked(account)) {
            return;
        }
        if (account == this) {
            System.out.println("You cannot unfollow yourself");
            return;
        }

        if (!following.contains(account)) {
            System.out.println("You are not following this account.");
            return;
        }

        following.remove(account);
        account.followers.remove(this);

        System.out.println("You have unfollowed " + account.getUserName() + ".");
        this.updateHistory(
                new String("You unfollowed " + account.getUserName()));
    }

    /**
     * Adds a post to the account's list of posts, if the account is logged in.
     *
     * @param post the post to add
     */
    public void addPost(Post post) {
        if (!isLogged()) {
            return;
        }
        posts.add(post);
        System.out.println(getUserName() + " share " + post.getContent());
        this.updateHistory(
                new String("You share post : " + post.getContent()));
    }

    /**
     * add like to a post
     * in order to add like firstly you need to seeposts
     * 
     * @param like
     */
    public void like(Like like, Account targetAccount) {
        if (!isLogged()) {
            return;
        }
        if (postScreenOn) {
            boolean found = false;
            for (Post post : targetAccount.posts) {
                if (post.getPostId() == like.getPostId()) {

                    for (Like tempLike : post.getLikes()) {
                        if (tempLike == like) {
                            System.out.println("You already like that post");
                            return;
                        }
                    }

                    found = true;

                    post.addLike(like);

                    System.out.println("You liked the post with ID " + like.getPostId() + ".");
                    this.updateHistory(
                            new String("You liked " + targetAccount.getUserName() + " 's post id:" + post.getPostId()));
                    break;
                }
            }
            if (!found) {
                System.out.println("The post with ID " + like.getPostId() + " does not exist.");
            }

        } else {
            System.out.println("You are not at post screen! First view posts to like them.");
        }
    }

    public void unlike(Like like, Account targetAccount) {
        if (!isLogged()) {
            return;
        }

        if (!postScreenOn) {
            System.out.println("You are not at post screen! First view posts to unlike.");
            return;
        }
        boolean postFound = false;
        boolean likeFound = false;
        for (Post post : targetAccount.posts) {
            if (post.getPostId() == like.getPostId()) {
                postFound = true;
                for (Like tempLike : post.getLikes()) {
                    if (tempLike == like) {
                        post.delLike(like);
                        System.out.println("You unliked the post with ID " + post.getPostId() + ".");
                        this.updateHistory(new String("You unliked a post from " + targetAccount.getUserName()
                                + " with Id:" + post.getPostId()));
                        likeFound = true;
                        break;
                    }
                }
                if (!likeFound) {
                    System.out.println("You have not liked this post.");
                }
            }
        }
        if (!postFound) {
            System.out.println("Post not found.");
        }
    }

    /**
     * wiew the profile of sended account
     * 
     * @param targetAccount
     */
    public void wiewProfile(Account targetAccount) {
        if (!isLogged() || !isExist(targetAccount) || this.isBlocked(targetAccount)) {
            return;
        }
        currentWiewed = targetAccount;
        wiewScreenOn = true;
        System.out.println("----------------------");
        System.out.println("User ID: " + targetAccount.getAccountId());
        System.out.println("Username: " + targetAccount.getUserName());
        System.out.println("Location: " + targetAccount.getLocation());
        System.out.println("Birth Date: " + targetAccount.getBirthdate());
        System.out.println(targetAccount.getUserName() + " is following " + targetAccount.following.size()
                + " account(s) and has " + targetAccount.followers.size()
                + " follower(s).");

        if (!targetAccount.followers.isEmpty()) {
            System.out.println("The followers of " + targetAccount.getUserName() + " are: ");
            for (Account follower : targetAccount.followers) {
                System.out.println(follower.getUserName() + ",");
            }

            System.out.println();
        }
        if (!targetAccount.following.isEmpty()) {
            System.out.println(targetAccount.getUserName() + " is following: ");
            for (Account following : targetAccount.following) {
                System.out.println(following.getUserName() + ",");
            }
        }

        System.out.println(targetAccount.getUserName() + " has " + targetAccount.posts.size() + " post(s).");
    }

    /**
     * wiew post of senden account
     * in order to see post first need to wiew profile
     * 
     * @param targetAccount
     */
    public void wiewPosts(Account targetAccount) {
        if (!isLogged() || !isExist(targetAccount) || this.isBlocked(targetAccount)) {
            return;
        }

        if (wiewScreenOn == true) {
            if (currentWiewed == targetAccount) {
                System.out.println("Viewing posts...");
                for (int i = 0; i < targetAccount.posts.size(); i++) {
                    System.out.println(
                            "(PostID: " + targetAccount.posts.get(i).getPostId() + ") "
                                    + targetAccount.getUserName()
                                    + ": "
                                    + targetAccount.posts.get(i).getContent());
                }
            } else {
                System.out.println("You are not wiew " + targetAccount.getUserName() + "'s account!");
            }
        } else {
            System.out.println("You are not at wiew screen!! First wiew profile to see posts");
        }

        wiewScreenOn = false;
        postScreenOn = true;

    }

    /**
     * wiew interactions of posts
     * 
     * @param targetAccount
     * @param accounts      accounts list need because of see who liked or comment
     *                      that post
     */
    public void wiewInteraction(Account targetAccount, LDLinkedList<Account> accounts) {
        if (!isLogged() || !isExist(targetAccount) || this.isBlocked(targetAccount)) {
            return;
        }
        if (currentWiewed == targetAccount) {
            if (postScreenOn) {
                if (targetAccount.posts.size() == 0) {
                    System.out.println("There is no post to see interactions...");
                }
                for (int i = 0; i < targetAccount.posts.size(); i++) {
                    System.out.println("PostId: " + targetAccount.posts.get(i).getPostId() + ": "
                            + targetAccount.posts.get(i).getContent());
                    System.out.print("The post was liked by the following account(s): ");
                    for (int j = 0; j < targetAccount.posts.get(i).getLikeCount(); j++) {
                        System.out.print(
                                accounts.get(targetAccount.posts.get(i).getLike(j).getAccountId()).getUserName()
                                        + ",");
                    }
                    System.out.println();

                    System.out.println("The post has " + targetAccount.posts.get(i).getCommentCount() + " comment");
                    for (int j = 0; j < targetAccount.posts.get(i).getCommentCount(); j++) {
                        System.out
                                .println(accounts.get(targetAccount.posts.get(i).getComment(j).getAccountId())
                                        .getUserName()
                                        + " said : "
                                        + targetAccount.posts.get(i).getComment(j).getContent());
                    }
                    System.out.println();
                }

            } else {
                System.out.println("You are not at post screen! First view posts to see their interactions.");
            }
        } else {
            System.out.println("You are not wiew " + targetAccount.getUserName() + "'s account!");
        }

    }

    /**
     * add comment to a post
     * in order to make comment firstly you need to seeposts
     * 
     * @param comment
     */
    public void addComment(Comment comment, Account targetAccount) {
        if (!isLogged()) {
            return;
        }
        if (postScreenOn) {
            boolean found = false;
            for (int i = 0; i < currentWiewed.posts.size(); i++) {
                if (currentWiewed.posts.get(i).getPostId() == comment.getPostId()) {
                    found = true;

                    currentWiewed.posts.get(i).addComment(comment);

                    System.out.println("Your comment has been sent to post with ID " + comment.getPostId() + ".");
                    this.updateHistory(
                            new String("You commented " + targetAccount.getUserName() + " 's post id:"
                                    + comment.getPostId()));
                    break;
                }
            }
            if (!found) {
                System.out.println("The post with ID " + comment.getPostId() + " does not exist.");
            }
        } else {
            System.out.println("You are not at post screen! First view posts to send comments.");
        }
    }

    /**
     * delete comment from a post
     * in order to delete comment firstly you need to seeposts
     * 
     * @param comment
     * @targetAccoun
     */
    public void unComment(Comment comment, Account targetAccount) {
        if (!isLogged()) {
            return;
        }

        if (!postScreenOn) {
            System.out.println("You are not at post screen! First view posts to un-comment.");
            return;
        }
        boolean postFound = false;
        boolean commentFound = false;
        for (Post post : targetAccount.posts) {
            if (post.getPostId() == comment.getPostId()) {
                postFound = true;
                for (Comment tempComment : post.getComments()) {
                    if (tempComment == comment) {
                        post.delComment(comment);
                        System.out.println("You delete your comment succesfully from post with " + post.getPostId());
                        this.updateHistory(new String("You delete your comment from " + targetAccount.getUserName()
                                + " post with Id:" + post.getPostId()));
                        return;
                    }
                }
            }
        }
        if (!postFound) {
            System.out.println("Post not found");
        }
        if (!commentFound) {
            System.out.println("You have not commented this post.");
        }
    }

    /**
     * send a message to target account
     * in order to send message you need to follow that account first. There is no
     * need to that account follow you
     * 
     * @param message
     * @param targetAccount
     */
    public void sendMessage(Message message, Account targetAccount) {
        if (!isLogged() || this.isBlocked(targetAccount) == true || this.isFollowing(targetAccount) == false) {
            return;
        }
        outbox.add(message);
        if (targetAccount == null) {
            System.out.println("You cannot send a message does not exsist");
        }
        targetAccount.inbox.add(message);
        System.out.println("Your message sended to " + targetAccount.getUserName());
        this.updateHistory(new String("You send message to " + targetAccount.getUserName()));

    }

    public void checkOutbox() {
        if (!isLogged()) {
            return;
        }
        System.out.println("Checking outbox...");
        System.out.println("There is/are " + this.outbox.size() + " message(s) in the outbox.");
    }

    public void checkInbox() {
        if (!isLogged()) {
            return;
        }
        System.out.println("Checking inbox...");
        System.out.println("There is/are " + this.inbox.size() + " message(s) in the inbox.");
    }

    /**
     * wiew messages sended to account
     * 
     * @param accounts
     */
    public void wiewInbox(LDLinkedList<Account> accounts) {
        if (!isLogged()) {
            return;
        }
        System.out.println("Wiewing inbox...");
        for (int a = 0; a < this.inbox.size(); a++) {

            System.out.println("Message Id: " + this.inbox.get(a).getMessageId());
            System.out.println("From: " + accounts.get(this.inbox.get(a).getSenderId()).getUserName());
            System.out.println("To: " + accounts.get(this.inbox.get(a).getReceiverId()).getUserName());
            System.out.println("Message: " + this.inbox.get(a).getContent());
        }
    }

    /**
     * wiew messages sended by account
     * 
     * @param accounts
     */
    public void wiewOutbox(LDLinkedList<Account> accounts) {
        if (!isLogged()) {
            return;
        }
        System.out.println("Wiewing outbox...");
        for (int a = 0; a < this.outbox.size(); a++) {

            System.out.println("Message Id: " + this.outbox.get(a).getMessageId());
            System.out.println("From: " + accounts.get(this.outbox.get(a).getSenderId()).getUserName());
            System.out.println("To: " + accounts.get(this.outbox.get(a).getReceiverId()).getUserName());
            System.out.println("Message: " + this.outbox.get(a).getContent());
        }

    }

    public void showHistory(Account targeAccount) {
        if (!isLogged()) {
            return;
        }
        if (!(this == targeAccount)) {
            System.out.println("You cannot see other users history!!");
            return;
        }
        System.out.println("Showing history............");
        for (String content : history) {
            System.out.println(content);
        }
    }

}

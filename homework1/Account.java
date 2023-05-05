package homework1;

/**
 * 
 * The Account class represents a user account in a social media application
 */
public class Account {

    private int accountId;
    private Account[] blockedAccounts;
    private String userName;
    private String birthdate;
    private String location;
    private Account[] followers;
    private Account[] following;
    private Message[] inbox;
    private Message[] outbox;
    private Post[] posts;

    private final int ARRAY_LENGTH = 100;
    private int followingCount;
    private int followersCount;
    private int receivedMesCount;
    private int sendedMesCount;
    private int postsCount;
    private int blockedAccountCount;

    // variables for control
    boolean wiewScreenOn = false;
    boolean postScreenOn = false;
    private boolean logged = false;
    /**
     * It show at that moment which account you wiew
     */
    Account currentWiewed;

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
     */
    public Account(int accountId, String userName, String location, String birthdate) {
        this.accountId = accountId;
        this.userName = userName;
        this.location = location;
        this.birthdate = birthdate;
        System.out.println("An account with username " + userName + " has been created.");

        this.posts = new Post[ARRAY_LENGTH];
        this.following = new Account[ARRAY_LENGTH];
        this.followers = new Account[ARRAY_LENGTH];
        this.inbox = new Message[ARRAY_LENGTH];
        this.outbox = new Message[ARRAY_LENGTH];
        this.blockedAccounts = new Account[ARRAY_LENGTH];
        this.postsCount = 0;
        this.followingCount = 0;
        this.followersCount = 0;
        this.receivedMesCount = 0;
        this.sendedMesCount = 0;
        this.blockedAccountCount = 0;
    }

    public int getAccountId() {
        return accountId;
    }

    /**
     * 
     * Checks if the user is logged in
     * 
     * @return true if the user is logged in, false otherwise
     */
    public boolean isLogged() {
        return logged;
    }

    /**
     * 
     * Blocks the specified account
     * 
     * @param targetAccount the account to be blocked
     */
    public void block(Account targetAccount, Account[] accounts) {
        if (isExsist(accounts, targetAccount.accountId) == false) {
            return;
        }
        if (targetAccount == this) {
            System.out.println("You cannot block yoursefl!!...");
            return;
        }
        for (Account acc : blockedAccounts) {
            if (acc == targetAccount) {
                System.out.println("You already blocked that account!! Use unblock function to unblock it");
                return;
            }
        }
        System.out.println("You block " + targetAccount.getUserName() + " succesfully");

        blockedAccounts[blockedAccountCount++] = targetAccount;
    }

    /**
     * 
     * Checks if the specified account is blocked by this account
     * 
     * @param targetAccount the account to check
     * @return true if the account is blocked, false otherwise
     */
    public boolean isBlocked(Account targetAccount) {
        for (Account acc : targetAccount.blockedAccounts) {
            if (acc == this) {
                System.out.println("That account block you...");
                return true;
            }
        }
        for (Account acc : blockedAccounts) {
            if (acc == targetAccount) {
                System.out.println("You blocked that account. Unblock the account to contiune...");
                return true;
            }
        }

        return false;
    }

    /**
     * check that account follow target account
     * 
     * @param targetAccount
     * @return
     */
    public boolean isFollowing(Account targetAccount) {
        for (Account acc : following) {
            if (acc == targetAccount) {

                return true;
            }
        }
        System.out.println("You need to follow that account first!!");
        return false;
    }

    /**
     * unblock sended user
     * 
     * @param targetAccount
     */
    public void unBlock(Account targetAccount) {
        for (int i = 0; i < blockedAccountCount; i++) {
            if (blockedAccounts[i] == targetAccount) {
                System.out.println("You unblock " + targetAccount.getUserName() + " successfully.");
                blockedAccounts[i] = blockedAccounts[blockedAccountCount - 1];
                blockedAccounts[blockedAccountCount - 1] = null;
                blockedAccountCount--;
                return;
            }
        }
        System.out.println("You didn't block " + targetAccount.getUserName() + " before.");
    }

    /**
     * Returns whether the account is logged in or not.
     *
     * @return true if the account is logged in, false otherwise
     */
    public boolean isLoggedIn() {

        if (this.logged == false) {
            System.out.println("User is not logged in!...");
            return false;
        }
        return true;
    }

    /**
     * Adds a post to the account's list of posts, if the account is logged in.
     *
     * @param post the post to add
     */
    public void addPost(Post post) {
        if (isLoggedIn()) {
            posts[postsCount++] = post;
            System.out.println(getUserName() + " share " + post.getContent());
        }

    }

    /**
     * Logs into this account, if no other account is already logged in.
     *
     * @param accounts an array of all the accounts in the social media application
     */
    public void login(Account[] accounts) {
        for (Account account : accounts) {
            if (account != this) {
                if (account.isLogged() == true) {
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

    /**
     * Logs out of this account, if it is currently logged in.
     */
    public void logout() {

        if (this.logged == true) {
            this.logged = false;
            System.out.println("Logging out from account(" + getUserName() + ")...");
        } else {
            System.out.println("User is not logged in.....");
        }
    }

    public String getUserName() {
        return userName;
    }

    /**
     * follow the wanted account
     * 
     * @param targetAccount
     * @param accounts      it take accounts array to look that account exsist
     */
    public void follow(Account targetAccount, Account[] accounts) {
        if (isLoggedIn() || isExsist(accounts, targetAccount.getAccountId())) {
            if (this == targetAccount) {
                System.out.println("You cannot follow yourself");
                return;
            }

            for (int i = 0; i < followingCount; i++) {
                if (targetAccount == following[i]) {
                    System.out.println("You are already following " + targetAccount.getUserName() + ".");
                    return;
                }
            }
            following[followingCount++] = targetAccount;

            targetAccount.followers[targetAccount.followersCount++] = this;

            System.out.println("You are now following " + targetAccount.getUserName() + ".");
        }
    }

    /**
     * Looks account array, if found account with targetId return true
     * 
     * @param accounts
     * @param targetId
     * @return true if that account exsist
     */
    public boolean isExsist(Account[] accounts, int targetId) {
        for (Account account : accounts) {
            if (account.accountId == targetId) {
                return true;
            }
        }
        System.out.println("This profile does not exist!");

        return false;
    }

    /**
     * wiew the profile of sended account
     * 
     * @param targetAccount
     * @param accounts      it take accounts array to look that account exsist
     */
    public void wiewProfile(Account targetAccount, Account[] accounts) {
        if (isExsist(accounts, targetAccount.accountId) == false) {
            return;
        }
        if (this.isBlocked(targetAccount)) {
            return;
        }
        currentWiewed = targetAccount;
        wiewScreenOn = true;
        System.out.println("----------------------");
        System.out.println("User ID: " + targetAccount.accountId);
        System.out.println("Username: " + targetAccount.userName);
        System.out.println("Location: " + targetAccount.location);
        System.out.println("Birth Date: " + targetAccount.birthdate);
        System.out.println(targetAccount.userName + " is following " + targetAccount.followingCount
                + " account(s) and has " + targetAccount.followersCount
                + " follower(s).");

        if (targetAccount.followersCount != 0) {
            System.out.print("The followers of " + targetAccount.userName + " are: ");
            for (int i = 0; i < targetAccount.followersCount; i++) {
                System.out.print(targetAccount.followers[i].getUserName());
                if (i != targetAccount.followersCount - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        if (targetAccount.followingCount != 0) {
            System.out.print(targetAccount.userName + " is following: ");
            for (int i = 0; i < targetAccount.followingCount; i++) {
                System.out.print(targetAccount.following[i].getUserName());
                if (i != targetAccount.followingCount - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        System.out.println(targetAccount.getUserName() + " has " + targetAccount.postsCount + " post(s).");

    }

    /**
     * wiew post of senden account
     * in order to see post first need to wiew profile
     * 
     * @param targetAccount
     */
    public void wiewPosts(Account targetAccount) {
        if (isLoggedIn()) {
            if (wiewScreenOn == true) {
                if (currentWiewed == targetAccount) {
                    System.out.println("Viewing posts...");
                    for (int i = 0; i < targetAccount.postsCount; i++) {
                        System.out.println(
                                "(PostID: " + targetAccount.posts[i].getPostId() + ") " + targetAccount.getUserName()
                                        + ": "
                                        + targetAccount.posts[i].getContent());
                    }
                } else {
                    System.out.println("You are not wiew " + targetAccount.getUserName() + "'s account!");
                }
            } else {
                System.out.println("You are not at wiew screen!! First wiew profile to see posts");
            }

        }
        wiewScreenOn = false;
        postScreenOn = true;
    }

    /**
     * add comment to a post
     * in order to make comment firstly you need to seeposts
     * 
     * @param comment
     */
    public void addComment(Comment comment) {
        if (isLoggedIn()) {
            if (postScreenOn) {
                boolean found = false;
                for (int i = 0; i < currentWiewed.postsCount; i++) {
                    if (currentWiewed.posts[i].getPostId() == comment.getPostId()) {
                        found = true;

                        currentWiewed.posts[i].addComment(comment);

                        System.out.println("Your comment has been sent to post with ID " + comment.getPostId() + ".");
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
    }

    /**
     * add like to a post
     * in order to add like firstly you need to seeposts
     * 
     * @param like
     */
    public void like(Like like) {
        if (isLoggedIn()) {
            if (postScreenOn) {
                boolean found = false;
                for (int i = 0; i < currentWiewed.postsCount; i++) {
                    if (currentWiewed.posts[i].getPostId() == like.getPostId()) {
                        found = true;

                        currentWiewed.posts[i].addLike(like);

                        System.out.println("You liked the post with ID " + like.getPostId() + ".");
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

    }

    /**
     * wiew interactions of posts
     * 
     * @param targetAccount
     * @param accounts      accounts list need because of see who liked or comment
     *                      that post
     */
    public void wiewInteraction(Account targetAccount, Account[] accounts) {
        // önce kontrol et vs vs
        if (isLoggedIn()) {
            if (currentWiewed == targetAccount) {
                if (postScreenOn) {
                    if (targetAccount.postsCount == 0) {
                        System.out.println("There is no post to see interactions...");
                    }
                    for (int i = 0; i < targetAccount.postsCount; i++) {
                        System.out.println("PostId: " + targetAccount.posts[i].getPostId() + ": "
                                + targetAccount.posts[i].getContent());
                        System.out.print("The post was liked by the following account(s): ");
                        for (int j = 0; j < targetAccount.posts[i].getLikeCount(); j++) {
                            System.out.print(
                                    accounts[targetAccount.posts[i].getLikes(j).getAccountId()].getUserName() + ",");
                        }
                        System.out.println();

                        System.out.println("The post has " + targetAccount.posts[i].getCommentCount() + " comment");
                        for (int j = 0; j < targetAccount.posts[i].getCommentCount(); j++) {
                            System.out
                                    .print(accounts[targetAccount.posts[i].getComments(j).getAccountId()].getUserName()
                                            + " said : "
                                            + targetAccount.posts[i].getComments(j).getContent());
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

    }

    /**
     * send a message to target account
     * in order to send message you need to follow that account first. There is no
     * need to that account follow you
     * 
     * @param message
     * @param targetAccount
     */
    public void sendMessage(Message message, Account targetAccount, Account[] accounts) {
        if (isExsist(accounts, targetAccount.accountId) == false || this.isBlocked(targetAccount) == true
                || this.isFollowing(targetAccount) == false) {
            return;
        }
        this.outbox[sendedMesCount++] = message;

        targetAccount.inbox[targetAccount.receivedMesCount++] = message;
        System.out.println("Your message sended to " + targetAccount.getUserName());
    }

    public void checkOutbox() {
        System.out.println("Checking outbox...");
        System.out.println("There is/are " + this.sendedMesCount + " message(s) in the outbox.");
    }

    public void checkInbox() {
        System.out.println("Checking inbox...");
        System.out.println("There is/are " + this.receivedMesCount + " message(s) in the inbox.");
    }

    /**
     * wiew messages sended to account
     * 
     * @param accounts
     */
    public void wiewInbox(Account[] accounts) {
        System.out.println("Wiewing inbox...");
        for (int a = 0; a < this.receivedMesCount; a++) {

            System.out.println("Message Id: " + this.inbox[a].getMessageId());
            System.out.println("From: " + accounts[this.inbox[a].getSenderId()].getUserName());
            System.out.println("To: " + accounts[this.inbox[a].getReceiverId()].getUserName());
            System.out.println("Message: " + this.inbox[a].getContent());
        }
    }

    /**
     * wiew messages sended by account
     * 
     * @param accounts
     */
    public void wiewOutbox(Account[] accounts) {
        System.out.println("Wiewing outbox...");
        for (int a = 0; a < this.sendedMesCount; a++) {

            System.out.println("Message Id: " + this.inbox[a].getMessageId());
            System.out.println("From: " + accounts[this.inbox[a].getSenderId()].getUserName());
            System.out.println("To: " + accounts[this.inbox[a].getReceiverId()].getUserName());
            System.out.println("Message: " + this.inbox[a].getContent());
        }
    }

}
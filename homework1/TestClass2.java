package homework1;

public class TestClass2 {
    public static void main(String[] args) {
        System.out.println("Executing scenario 2...");

        Account gizemsungu = new Account(0, "gizemsungu", "Istanbul", "11.08.1990");
        Account sibelgulmez = new Account(1, "sibelgülmez", "Istanbul", "10.03.1995");
        Account gokhankaya = new Account(2, "gokhankaya", "Istanbul", "21.08.2001");
        Account[] accounts = { gizemsungu, sibelgulmez, gokhankaya };

        gizemsungu.login(accounts);
        Post post1 = new Post(0, sibelgulmez.getAccountId(), "Wheater is so nice");
        gizemsungu.addPost(post1);
        Post post2 = new Post(1, sibelgulmez.getAccountId(), "I like Mettallica");
        gizemsungu.addPost(post2);
        gizemsungu.logout();

        sibelgulmez.login(accounts);
        sibelgulmez.wiewProfile(gizemsungu, accounts);
        // in order to like you need to see post first
        sibelgulmez.wiewPosts(gizemsungu);
        Like like1 = new Like(0, sibelgulmez.getAccountId(), 0);
        sibelgulmez.like(like1);
        sibelgulmez.logout();
        gokhankaya.login(accounts);
        gokhankaya.wiewProfile(gizemsungu, accounts);
        gokhankaya.wiewPosts(gizemsungu);
        Comment comment1 = new Comment(1, gokhankaya.getAccountId(), 0, "Nice");
        gokhankaya.addComment(comment1);
        // in order to send a message first you need to folow that account
        gokhankaya.follow(gizemsungu, accounts);
        Message message1 = new Message(0, gokhankaya.getAccountId(),
                gizemsungu.getAccountId(),
                "Hello!");
        gokhankaya.sendMessage(message1, gizemsungu, accounts);

        gokhankaya.logout();

        gizemsungu.login(accounts);
        gizemsungu.wiewProfile(gizemsungu, accounts);
        gizemsungu.checkOutbox();
        gizemsungu.checkInbox();
        gizemsungu.wiewInbox(accounts);
        gizemsungu.logout();
    }
}

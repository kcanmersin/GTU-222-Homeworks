package homework1;

public class TestClass1 {

    public static void main(String[] args) {
        System.out.println("Executing scenario 1...");

        Account gizemsungu = new Account(0, "gizemsungu", "Istanbul", "11.08.1990");
        Account sibelgulmez = new Account(1, "sibelgülmez", "Istanbul", "10.03.1995");
        Account gokhankaya = new Account(2, "gokhankaya", "Istanbul", "21.08.2001");
        Account[] accounts = { gizemsungu, sibelgulmez, gokhankaya };

        sibelgulmez.login(accounts);
        Post post1 = new Post(0, sibelgulmez.getAccountId(), "I like Java..");
        sibelgulmez.addPost(post1);
        Post post2 = new Post(1, sibelgulmez.getAccountId(), "Java the coffe...");
        sibelgulmez.addPost(post2);

        sibelgulmez.follow(gizemsungu, accounts);
        sibelgulmez.follow(gokhankaya, accounts);
        sibelgulmez.logout();

        gokhankaya.login(accounts);
        gokhankaya.wiewProfile(sibelgulmez, accounts);

        gokhankaya.wiewPosts(sibelgulmez);
        Like like1 = new Like(0, gokhankaya.getAccountId(), 0);
        gokhankaya.like(like1);
        Comment comment1 = new Comment(2, gokhankaya.getAccountId(), 0, "Me too");
        gokhankaya.addComment(comment1);

        gokhankaya.follow(sibelgulmez, accounts);
        gokhankaya.follow(gizemsungu, accounts);
        Message message1 = new Message(0, gokhankaya.getAccountId(), gizemsungu.getAccountId(),
                "This homework is too easy!");

        gokhankaya.sendMessage(message1, gizemsungu, accounts);

        gokhankaya.logout();
        gizemsungu.login(accounts);
        gizemsungu.checkOutbox();
        gizemsungu.checkInbox();

        gizemsungu.wiewInbox(accounts);

        gizemsungu.wiewProfile(sibelgulmez, accounts);
        gizemsungu.wiewPosts(sibelgulmez);
        gizemsungu.wiewInteraction(sibelgulmez, accounts);
        Like like3 = new Like(3, gizemsungu.getAccountId(), 0);
        gizemsungu.like(like3);
        Like like4 = new Like(4, gizemsungu.getAccountId(), 1);
        gizemsungu.like(like4);
        gizemsungu.wiewInteraction(sibelgulmez, accounts);
    }
}
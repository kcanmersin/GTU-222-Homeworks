package arraylist;

import java.util.ArrayList;

public class TestClass1 {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Executing scenario 1...");
        ArrayList<Account> accounts = new ArrayList<>();
        Account gizemsungu = new Account(0, "gizemsungu", "Istanbul", "11.08.1990", accounts);
        accounts.add(gizemsungu);
        Account sibelgulmez = new Account(1, "sibelgülmez", "Istanbul", "10.03.1995", accounts);
        accounts.add(sibelgulmez);

        Account gokhankaya = new Account(2, "gokhankaya", "Istanbul", "21.08.2001", accounts);
        accounts.add(gokhankaya);

        sibelgulmez.login(accounts);
        Post post1 = new Post(0, sibelgulmez.getAccountId(), "I like Java..");
        sibelgulmez.addPost(post1);
        Post post2 = new Post(1, sibelgulmez.getAccountId(), "Java the coffe...");
        sibelgulmez.addPost(post2);

        sibelgulmez.follow(gizemsungu);
        sibelgulmez.follow(gokhankaya);
        sibelgulmez.logout();

        gokhankaya.login(accounts);
        gokhankaya.wiewProfile(sibelgulmez);

        gokhankaya.wiewPosts(sibelgulmez);
        Like like1 = new Like(0, gokhankaya.getAccountId(), 0);
        gokhankaya.like(like1, sibelgulmez);
        Comment comment1 = new Comment(2, gokhankaya.getAccountId(), 0, "Me too");
        gokhankaya.addComment(comment1, sibelgulmez);

        gokhankaya.follow(sibelgulmez);
        gokhankaya.follow(gizemsungu);
        Message message1 = new Message(0, gokhankaya.getAccountId(), gizemsungu.getAccountId(),
                "This homework is too easy!");

        gokhankaya.sendMessage(message1, gizemsungu);

        gokhankaya.logout();
        gizemsungu.login(accounts);
        gizemsungu.checkOutbox();
        gizemsungu.checkInbox();

        gizemsungu.wiewInbox(accounts);

        gizemsungu.wiewProfile(sibelgulmez);
        gizemsungu.wiewPosts(sibelgulmez);
        gizemsungu.wiewInteraction(sibelgulmez, accounts);
        Like like3 = new Like(3, gizemsungu.getAccountId(), 0);
        gizemsungu.like(like3, sibelgulmez);
        Like like4 = new Like(4, gizemsungu.getAccountId(), 1);
        gizemsungu.like(like4, sibelgulmez);
        gizemsungu.wiewInteraction(sibelgulmez, accounts);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("total time: " + totalTime / 1000000.0);
    }
}
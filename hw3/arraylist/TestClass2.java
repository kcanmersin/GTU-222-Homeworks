package arraylist;

import java.util.ArrayList;

public class TestClass2 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Executing scenario 2...");

        ArrayList<Account> accounts = new ArrayList<>();
        Account gizemsungu = new Account(0, "gizemsungu", "Istanbul", "11.08.1990", accounts);
        accounts.add(gizemsungu);
        Account sibelgulmez = new Account(1, "sibelgülmez", "Istanbul", "10.03.1995", accounts);
        accounts.add(sibelgulmez);

        Account gokhankaya = new Account(2, "gokhankaya", "Istanbul", "21.08.2001", accounts);
        accounts.add(gokhankaya);
        gizemsungu.login(accounts);
        Post post1 = new Post(0, sibelgulmez.getAccountId(), "Wheater is so nice");
        gizemsungu.addPost(post1);
        Post post2 = new Post(1, sibelgulmez.getAccountId(), "I like Mettallica");
        gizemsungu.addPost(post2);
        gizemsungu.logout();

        sibelgulmez.login(accounts);
        sibelgulmez.wiewProfile(gizemsungu);
        // in order to like you need to see post first
        sibelgulmez.wiewPosts(gizemsungu);
        Like like1 = new Like(0, sibelgulmez.getAccountId(), 0);
        sibelgulmez.like(like1, gizemsungu);
        sibelgulmez.logout();
        gokhankaya.login(accounts);
        gokhankaya.wiewProfile(gizemsungu);
        gokhankaya.wiewPosts(gizemsungu);
        Comment comment1 = new Comment(1, gokhankaya.getAccountId(), 0, "Nice");
        gokhankaya.addComment(comment1, gizemsungu);
        // in order to send a message first you need to folow that account
        gokhankaya.follow(gizemsungu);
        Message message1 = new Message(0, gokhankaya.getAccountId(),
                gizemsungu.getAccountId(),
                "Hello!");
        gokhankaya.sendMessage(message1, gizemsungu);

        gokhankaya.logout();

        gizemsungu.login(accounts);
        gizemsungu.wiewProfile(gizemsungu);
        gizemsungu.checkOutbox();
        gizemsungu.checkInbox();
        gizemsungu.wiewInbox(accounts);
        gizemsungu.logout();
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("total time: " + totalTime / 1000000.0);
    }
}

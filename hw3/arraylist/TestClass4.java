package arraylist;

import java.util.ArrayList;

public class TestClass4 {
        public static void main(String[] args) {
                long startTime = System.nanoTime();
                System.out.println("Executing scenario 4...");
                ArrayList<Account> accounts = new ArrayList<>();
                Account gizemsungu = new Account(0, "gizemsungu", "Istanbul", "11.08.1990", accounts);
                accounts.add(gizemsungu);
                Account sibelgulmez = new Account(1, "sibelgülmez", "Istanbul", "10.03.1995", accounts);
                accounts.add(sibelgulmez);
                Account gokhankaya = new Account(2, "gokhankaya", "Istanbul", "21.08.2001", accounts);
                accounts.add(gokhankaya);
                Account kerim = new Account(3, "kerimmersin", "Istanbul", "21.08.2001", accounts);
                accounts.add(kerim);
                Account can = new Account(4, "canmersin", "Istanbul", "21.08.2001", accounts);
                accounts.add(can);
                Account kubilayyazman = new Account(5, "kubilayyazman", "Kastamonu", "01.11.2002", accounts);
                accounts.add(kubilayyazman);
                Account burakdemir = new Account(6, "burakdemir", "Kocaeli", "10.03.1999", accounts);
                accounts.add(burakdemir);
                Account erentorlak = new Account(7, "erentorlak", "Ankara", "22.04.2003", accounts);
                accounts.add(erentorlak);
                Account suacetin = new Account(8, "suacetin", "Manisa", "12.04.2002", accounts);
                accounts.add(suacetin);
                Account diyarisi = new Account(9, "diyarisi", "Istanbul", "11.02.2001", accounts);
                accounts.add(diyarisi);
                System.out.println("++Block method test");
                suacetin.login(accounts);
                suacetin.follow(kerim);
                System.out.println("+Wiew own profile ");
                suacetin.wiewProfile(suacetin);
                suacetin.block(kerim);
                System.out.println("+Wiew own profile ");
                suacetin.wiewProfile(suacetin);

                suacetin.logout();
                kerim.login(accounts);
                kerim.follow(suacetin);
                Message message1 = new Message(0, kerim.getAccountId(), suacetin.getAccountId(),
                                "Hello");
                kerim.sendMessage(message1, suacetin);
                kerim.wiewProfile(suacetin);

                System.out.println("+You can block a person who blocked you");
                kerim.block(suacetin);
                Post post1 = new Post(0, kerim.getAccountId(), "I like play game..");
                kerim.addPost(post1);
                Message message2 = new Message(1, kerim.getAccountId(), diyarisi.getAccountId(),
                                "Hello");
                kerim.sendMessage(message2, diyarisi);
                kerim.follow(diyarisi);
                Message message3 = new Message(2, kerim.getAccountId(), diyarisi.getAccountId(),
                                "How are you");
                kerim.sendMessage(message3, diyarisi);

                Message message4 = new Message(3, kerim.getAccountId(), diyarisi.getAccountId(),
                                "Can you help me with my homework");
                kerim.sendMessage(message4, diyarisi);
                kerim.logout();
                diyarisi.login(accounts);
                diyarisi.checkInbox();

                diyarisi.wiewInbox(accounts);

                diyarisi.wiewProfile(kerim);
                diyarisi.wiewPosts(kerim);
                diyarisi.wiewInteraction(kerim, accounts);
                Like like1 = new Like(0, diyarisi.getAccountId(), 0);
                diyarisi.like(like1, kerim);
                diyarisi.like(like1, kerim);

                Comment comment1 = new Comment(1, diyarisi.getAccountId(), 0, "Me too");
                diyarisi.addComment(comment1, kerim);

                diyarisi.wiewInteraction(kerim, accounts);

                diyarisi.unlike(like1, kerim);
                diyarisi.unlike(like1, kerim);
                diyarisi.unComment(comment1, kerim);
                diyarisi.unComment(comment1, kerim);
                diyarisi.wiewInteraction(kerim, accounts);

                diyarisi.unfollow(diyarisi);
                diyarisi.unfollow(kerim);
                diyarisi.block(kerim);
                diyarisi.showHistory(kubilayyazman);
                diyarisi.showHistory(diyarisi);
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("total time: " + totalTime / 1000000.0);

        }
}

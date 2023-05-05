package arraylist;

import java.util.ArrayList;

public class TestClass3 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Executing scenario 3...");
        ArrayList<Account> accounts = new ArrayList<>();
        Account gizemsungu = new Account(0, "gizemsungu", "Istanbul", "11.08.1990", accounts);
        accounts.add(gizemsungu);
        Account sibelgulmez = new Account(1, "sibelgülmez", "Istanbul", "10.03.1995", accounts);
        accounts.add(sibelgulmez);

        Account gokhankaya = new Account(2, "gokhankaya", "Istanbul", "21.08.2001", accounts);
        accounts.add(gokhankaya);
        gizemsungu.login(accounts);
        gizemsungu.block(sibelgulmez);
        gizemsungu.logout();
        sibelgulmez.login(accounts);
        sibelgulmez.wiewProfile(gizemsungu);
        Message message1 = new Message(0, sibelgulmez.getAccountId(),
                gizemsungu.getAccountId(),
                "Hello!");
        sibelgulmez.sendMessage(message1, gizemsungu);

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("total time: " + totalTime / 1000000.0);
    }
}

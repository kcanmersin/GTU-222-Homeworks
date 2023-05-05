package homework1;

public class TestClass3 {
    public static void main(String[] args) {
        System.out.println("Executing scenario 3...");
        Account gizemsungu = new Account(0, "gizemsungu", "Istanbul", "11.08.1990");
        Account sibelgulmez = new Account(1, "sibelgülmez", "Istanbul", "10.03.1995");
        Account gokhankaya = new Account(2, "gokhankaya", "Istanbul", "21.08.2001");
        Account[] accounts = { gizemsungu, sibelgulmez, gokhankaya };
        gizemsungu.login(accounts);
        gizemsungu.block(sibelgulmez, accounts);
        gizemsungu.logout();
        sibelgulmez.login(accounts);
        sibelgulmez.wiewProfile(gizemsungu, accounts);
        Message message1 = new Message(0, sibelgulmez.getAccountId(),
                gizemsungu.getAccountId(),
                "Hello!");
        sibelgulmez.sendMessage(message1, gizemsungu, accounts);
    }
}

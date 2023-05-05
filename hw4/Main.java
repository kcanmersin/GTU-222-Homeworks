public class Main {
    public static void main(String[] args) {
        String[] userNamesString = { "sibelgulmez", "", "sibel1", "sibel", "sibel", "sibel", "sibel", "sibel", "sibel",
                "sibel", "sibel" };
        String[] Password1s = { "[rac()ecar]", "[rac()ecar]", "[rac()ecar]", "pass[]", "abcdabcd", "[[[[]]]]",
                "[no](no)", "[rac()ecar]]", "[rac()ecars]", "[rac()ecar]", "[rac()ecar]" };
        int[] Password2s = { 74, 74, 74, 74, 74, 74, 74, 74, 74, 5, 35 };
        int[] denomination = { 4, 17, 29 };

        for (int a = 0; a < userNamesString.length; a++) {
            UserName userName = new UserName(userNamesString[a]);
            Password1 password1 = new Password1(Password1s[a]);
            Password2 password2 = new Password2(Password2s[a]);
            System.out.println(a + 1 + "");
            System.out.println("username : " + userName.getData() + " password1: " + password1.getData()
                    + " password2: " + password2.getData());
            if (!userName.checkIfValidUsername(userName.getData()))
                continue;
            if (!password1.checkIfValidPassword(password1.getData()))
                continue;
            if (!password2.checkIfValidPassword(password2.getData()))
                continue;

            if (!userName.containsUserNameSpirit(userName.getData(), password1.getData()))
                continue;

            if (!password1.isBalancedPassword(password1.getData()))
                continue;

            if (!password1.isPalindromePossible(password1.getData()))
                continue;

            if (!password2.isExactDivision(password2.getData(), denomination))
                continue;

            System.out.println("The username and passwords are valid. The door is opening, please wait..");
        }

    }

}

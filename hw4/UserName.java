import java.util.Stack;

public class UserName {
    private String data;

    public String getData() {
        return data;
    }

    public UserName(String data) {
        this.data = data;
    }

    public boolean checkIfValidUsername(String username) {

        if (username.length() <= 0) {
            System.out.println("The username is invalid. It should have at least 1 character");
            return false;
        }

        if (Character.isLetter(username.charAt(0)) == false) {
            System.out.println("The username is invalid. It should have letters only.");
            return false;
        }
        if (username.length() == 1) {
            return true;
        }

        return checkIfValidUsername(username.substring(1));
    }

    public boolean containsUserNameSpirit(String username, String password) {
        Stack<Character> stack1 = new Stack<>();
        for (int i = 0; i < username.length(); i++) {
            stack1.push(username.charAt(i));
        }

        Stack<Character> stack2 = new Stack<>();
        for (int i = 0; i < password.length(); i++) {
            stack2.push(password.charAt(i));
        }

        while (stack1.isEmpty() == false) {
            Character ch = stack1.pop();
            if (stack2.contains(ch)) {
                return true;
            }
        }
        System.out.println("The password1 is invalid. It should have at least 1 character from the username.");
        return false;
    }

}

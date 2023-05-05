import java.util.Stack;

public class Password1 {
    public String data;

    public String getData() {
        return data;
    }

    public Password1(String data) {
        this.data = data;
    }

    /**
     * remove letter from coming str and return temp version without letter
     * 
     * @param str
     * @return
     */
    private static String removeLetter(String str) {
        StringBuilder temp = new StringBuilder();
        for (int a = 0; a < str.length(); a++) {
            char c = str.charAt(a);
            if (!Character.isLetter(c)) {
                temp.append(c);
            }
        }
        return temp.toString();
    }

    /**
     * check the password is valid
     * 
     * @param password1
     * @return
     */
    public boolean checkIfValidPassword(String password1) {
        if (password1.length() <= 7) {
            System.out.println("The password1 is invalid. It should have at least 8 character");
            return false;
        }
        boolean containLetter = false;
        for (int a = 0; a < password1.length(); a++) {
            if (Character.isLetter(password1.charAt(a))) {
                containLetter = true;
                break;
            }
        }
        if (containLetter == false) {
            System.out.println("The password1 is invalid. It should have letters too.");
            return false;
        }
        if (countBrackets(password1) == false) {
            System.out.println("It should contain at least 2 bracket!");
            return false;
        }

        String temp = removeBracket(password1);
        temp = removeLetter(temp);
        if (temp.length() == 0) {
            return true;
        } else {
            System.out.println("It can only contain letter or bracket");
        }

        return false;
    }

    /**
     * count brackets of password1
     * 
     * @param password1
     * @return
     */
    private boolean countBrackets(String password1) {
        String brackets = "{}[]()";
        int count = 0;
        for (int i = 0; i < password1.length(); i++) {
            if (brackets.indexOf(password1.charAt(i)) != -1) {
                count++;
                if (count == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check the password is balanced or not
     * 
     * @param password
     * @return
     */
    public boolean isBalancedPassword(String password) {
        String pass = removeLetter(password);
        Stack<Character> stack = new Stack<Character>();
        boolean isBalanced = true;
        for (int i = 0; i < pass.length(); i++) {
            if (pass.charAt(i) == '(' || pass.charAt(i) == '{' || pass.charAt(i) == '[') {
                stack.push(pass.charAt(i));
            } else if (stack.empty()) {
                isBalanced = false;
                break;
            } else if (stack.peek() == '{' && pass.charAt(i) == '}') {
                stack.pop();
            } else if (stack.peek() == '(' && pass.charAt(i) == ')') {
                stack.pop();
            } else if (stack.peek() == '[' && pass.charAt(i) == ']') {
                stack.pop();
            } else {
                isBalanced = false;
                break;
            }
        }
        if (!stack.isEmpty() || isBalanced == false) {
            System.out.println("The password1 is invalid. It should be balanced.");
            return false;
        }
        return stack.isEmpty();
    }

    /**
     * count letters of password write count of them at arr
     * return odd count alphabet
     * 
     * @param str
     * @param arr
     * @param index
     * @param oddCount
     * @return
     */
    public static int countLetters(String str, int[] arr, int index, int oddCount) {
        if (index == str.length()) {
            return oddCount;
        }
        arr[str.charAt(index) - 'a']++;
        if (arr[str.charAt(index) - 'a'] % 2 == 1) {
            oddCount++;
        } else {
            oddCount--;
        }
        return countLetters(str, arr, index + 1, oddCount);
    }

    /**
     * find the oddCount index at alphabet
     * we need it to make our palindrome string
     * 
     * @param alphabet
     * @param i
     * @return
     */
    private static int checkOdd(int[] alphabet, int i) {

        if (i >= alphabet.length) {
            return -1;
        }

        if (alphabet[i] % 2 == 1) {

            return i;

        }

        return checkOdd(alphabet, i + 1);
    }

    /**
     * call helper function inside
     * if there is more then one odd count letter return false
     * else build palindrome version
     * 
     * @param password
     * @return
     */
    public boolean isPalindromePossible(String password) {

        String pass = removeBracket(password);
        int[] alphabet = new int[26];

        int oddCount = countLetters(pass, alphabet, 0, 0);
        int oddIndex = -1;
        if (oddCount > 1) {
            System.out.println(
                    "The password1 is invalid. It should be possible to obtain a palindrome from the password1");
            return false;
        } else if (oddCount == 1) {
            oddIndex = checkOdd(alphabet, 0);
        }

        String output = buildPalindrome(pass, new StringBuilder(), oddIndex, 0, alphabet, 0);
        System.out.println(output);
        return true;

    }

    /**
     * Build palindrome.
     * look for string if that letter is not oddCount add it end of the string
     * 
     * @param password
     * @param stringBuilder
     * @param oddIndex
     * @param index
     * @param alphabet
     * @param added
     * @return
     */
    private static String buildPalindrome(String password, StringBuilder stringBuilder, int oddIndex, int index,
            int[] alphabet, int added) {
        if (index > password.length() - 1) {
            if (oddIndex != -1) {
                char ch = (char) (oddIndex + 'a');
                stringBuilder.insert(stringBuilder.length() / 2, ch);
            }

            return stringBuilder.toString();
        }
        char ch = password.charAt(index);
        int ord = Character.toLowerCase(ch) - 'a';

        if (alphabet[ord] >= 2) {

            stringBuilder.insert(stringBuilder.length() - added, ch);
            stringBuilder.insert(added, ch);
            added++;
            alphabet[ord] -= 2;
        }

        return buildPalindrome(password, stringBuilder, oddIndex, index + 1, alphabet, added);
    }

    /**
     * remove bracket of string and return temp version
     * 
     * @param str
     * @return
     */
    private static String removeBracket(String str) {
        StringBuilder temp = new StringBuilder();
        String brackets = "{}[]()";
        for (int a = 0; a < str.length(); a++) {
            char c = str.charAt(a);
            if (brackets.indexOf(c) == -1) {
                temp.append(c);
            }
        }
        return temp.toString();
    }

}

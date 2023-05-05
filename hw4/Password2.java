public class Password2 {
    public int data;

    public int getData() {
        return data;
    }

    public Password2(int data) {
        this.data = data;
    }

    /**
     * check that password 2 is write with sum of denominations
     * 
     * @param password2
     * @param denominations
     * @return
     */
    public boolean isExactDivision(int password2, int[] denominations) {
        if (isExactDivisionHelper(password2, denominations, 0) == false) {
            System.out.println("The password2 is invalid. It is not compatible with the denominations.");
            return false;
        }
        return true;
    }

    /**
     * check the passord is valid
     * 
     * @param number
     * @return
     */
    public boolean checkIfValidPassword(int number) {
        if (number > 10 && number < 10000) {
            return true;
        }
        System.out.println("Number must between 10 and 10000");
        return false;
    }

    /**
     * it is helper for isExactDivision checks if it is possible to divide password2
     * use with denominations
     * 
     * @param password2
     * @param denominations
     * @param index
     * @return
     */
    public boolean isExactDivisionHelper(int password2, int[] denominations, int index) {

        if (password2 == 0) {
            return true;
        }
        if (password2 < 0 || index >= denominations.length) {

            return false;
        }
        return isExactDivisionHelper(password2 - denominations[index], denominations, index)
                || isExactDivisionHelper(password2, denominations, index + 1);
    }
}

import java.util.Scanner;

public class HugeInteger {
    private int[] digits;

    public HugeInteger() {
        digits = new int[40];
    }

    public void parse(String s) {
        s = s.replaceAll("^0+", ""); 
        int numLength = s.length();
        
        if (numLength > 40) {
            throw new IllegalArgumentException("Input number is too large to stored.");
        }
        
        for (int i = 0; i < 40 - numLength; i++) {
            digits[i] = 0;
        }
        
        for (int i = 40 - numLength, j = 0; i < 40; i++, j++) {
            digits[i] = Character.getNumericValue(s.charAt(j));
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        boolean leadingZeros = true;

        for (int digit : digits) {
            if (digit != 0) {
                leadingZeros = false;
            }

            if (!leadingZeros) {
                result.append(digit);
            }
        }

        return result.length() == 0 ? "0" : result.toString();
    }

    public void add(HugeInteger num1, HugeInteger num2) {
        int carry = 0;

        for (int i = 39; i >= 0; i--) {
            int sum = num1.digits[i] + num2.digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }

        if (carry != 0) {
            throw new ArithmeticException("Overflow: Result too large to stored.");
        }
    }

    public void subtract(HugeInteger num1, HugeInteger num2) {
        int borrow = 0;

        for (int i = 39; i >= 0; i--) {
            int diff = num1.digits[i] - num2.digits[i] - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            digits[i] = diff;
        }

        if (borrow != 0) {
            throw new ArithmeticException("Underflow: Result is negative and cannot be stored.");
        }
    }
    
    public boolean isEqualTo(HugeInteger num1, HugeInteger num2) {
        for (int i = 0; i < 40; i++) {
            if (num1.digits[i] != num2.digits[i]) {
                return false;
            }
        }
        return true;
    }

    public  boolean isGreaterThan(HugeInteger num1, HugeInteger num2) {
        for (int i = 0; i < 40; i++) {
            if (num1.digits[i] > num2.digits[i]) {
                return true;
            } else if (num1.digits[i] < num2.digits[i]) {
                return false;
            }
        }
        return false;
    }

    public boolean isLessThan(HugeInteger num1, HugeInteger num2) {
        return !isGreaterThan(num1, num2) && !isEqualTo(num1, num2);
    }

    public boolean isGreaterThanOrEqualTo(HugeInteger num1, HugeInteger num2) {
        return isGreaterThan(num1, num2) || isEqualTo(num1, num2);
    }

    public boolean isLessThanOrEqualTo(HugeInteger num1, HugeInteger num2) {
        return isLessThan(num1, num2) || isEqualTo(num1, num2);
    }

    public boolean isZero() {
        for (int digit : digits) {
            if (digit != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    	
    	Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first HugeInteger: ");
        String input1 = scanner.nextLine();
        
        System.out.print("Enter the second HugeInteger: ");
        String input2 = scanner.nextLine();

        HugeInteger num1 = new HugeInteger();
        HugeInteger num2 = new HugeInteger();

        num1.parse(input1);
        num2.parse(input2);

        System.out.println("num1: " + num1.toString());
        System.out.println("num2: " + num2.toString());

        HugeInteger sum = new HugeInteger();
        sum.parse("0");
        sum.add(num1, num2);
        System.out.println("Sum: " + sum.toString());

        HugeInteger difference = new HugeInteger();
        difference.parse("0");  
        difference.subtract(num1, num2);
        System.out.println("Difference: " + difference.toString());
        
        if (num1.isEqualTo(num1, num2)) {
            System.out.println("Num1 = Num2: True");
        } else {
            System.out.println("Num1 = Num2: False");
          }
        
        if (num1.isGreaterThan(num1, num2)) {
            System.out.println("Num1 > Num2: True");
        } else {
        	System.out.println("Num1 > Num2: False");
          }

        if (num1.isLessThan(num1, num2)) {
            System.out.println("Num1 < Num2: True");
        } else {
        	System.out.println("Num1 < Num2: False");
          }
        
        if (num1.isGreaterThanOrEqualTo(num1, num2)) {
            System.out.println("Num1 >= Num2: True");
        } else {
        	System.out.println("Num1 >= Num2: False");
          } 
        
       if (num1.isLessThanOrEqualTo(num1, num2)) {
           System.out.println("Num1 <= Num2: True");
       } else {
    	   System.out.println("Num1 <= Num2: False");
         } 
        
       if (num1.isZero()) {
           System.out.println("Is zero?: True");
       } else {
    	   System.out.println("Is zero?: False");
         } 
        
       if (num2.isZero()) {
           System.out.println("Is zero?: True");
       } else {
    	   System.out.println("Is zero?: False");
         }  
    }
}
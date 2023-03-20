import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input:");
        String expression = input.nextLine();
        String response = calc(expression);
        System.out.println("Output:\n" + response);
    }
    public static String calc(String input) {
        Main romanTest = new Main();
        Main arabToRoman = new Main();
        String exceptionRomanNegative = "throws Exception //т.к. в римской системе нет отрицательных чисел";
        String exceptionArabRoman = "throws Exception //т.к. используются одновременно разные системы счисления";
        String exceptionNoMath = "throws Exception //т.к. строка не является математической операцией";
        String exceptionFormat = "throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        Integer num1;
        Integer num2;
        int result;
        boolean romanArab = false;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 3) {
            return exceptionNoMath;
        }
        try {
            num1 = Integer.valueOf(inputSplit[0]);
            num2 = Integer.valueOf(inputSplit[2]);
        } catch (RuntimeException e) {
            try {
                num1 = romanTest.romanToArab(inputSplit[0]);
                num2 = romanTest.romanToArab(inputSplit[2]);
                romanArab = true;
            } catch (RuntimeException ex) {
                return exceptionArabRoman;
            }
        }
        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
            return exceptionFormat;
        }
        String operator = inputSplit[1];
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                return exceptionFormat;
    }
        String output;
        if (romanArab){
            if(result < 1){
                return exceptionRomanNegative;
            } else {
                output = arabToRoman.arabToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }
        return output;
    }
    Integer romanToArab(String romanInput){
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }
        return result;
    }
    String arabToRome(int arabInput){
        String result = "";
        int value;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;
    }
}






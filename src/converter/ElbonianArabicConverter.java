package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

import java.util.*;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;
    final String KEY = "MeDCmLXwVI";

    /**
     * Constructor for the ElbonianArabic class that takes a string. The string should contain a valid
     * Elbonian or Arabic numeral. The String can have leading or trailing spaces. But there should be no
     * spaces within the actual number (ie. "9 9" is not ok, but " 99 " is ok). If the String is an Arabic
     * number it should be checked to make sure it is within the Elbonian number systems bounds. If the
     * number is Elbonian, it must be a valid Elbonian representation of a number.
     *
     * @param number A string that represents either a Elbonian or Arabic number.
     * @throws MalformedNumberException  Thrown if the value is an Elbonian number that does not conform
     *                                   to the rules of the Elbonian number system. Leading and trailing spaces should not throw an error.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic number that cannot be represented
     *                                   in the Elbonian number system.
     */
    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {


        this.number = number.trim();
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() throws MalformedNumberException, ValueOutOfBoundsException {
        try {
            if (Integer.parseInt(this.number) < 1) {
                throw new ValueOutOfBoundsException("Number is Out of Bounds");
            }
        } catch (NumberFormatException e) {}
        char[] testContains = this.number.toCharArray();
        int numC = 0;
        int numX = 0;
        int numI = 0;
        int numD = 0;
        int numL = 0;
        int numV = 0;
        int nume = 0;
        int numm = 0;
        int numw = 0;
        int numcapM = 0;
        for (char c : testContains) {
            if (!(c == 'M' || c == 'e' || c == 'D' || c == 'C' || c == 'm' || c == 'L'
                    || c == 'X' | c == 'w' || c == 'V' || c == 'I')) {
                if (this.number.matches("[0-9]+")) {
                    if (Integer.parseInt(this.number) < 0 || Integer.parseInt(this.number) > 3999) {
                        throw new ValueOutOfBoundsException("Value was Out of Bounds");
                    } else {
                        return Integer.parseInt(this.number);
                    }
                } else {
                    throw new MalformedNumberException("Number was Malformed");
                }
            } else {
                if (c == 'C') {
                    numC++;
                } else if (c == 'X') {
                    numX++;
                } else if (c == 'I') {
                    numI++;
                } else if (c == 'D') {
                    numD++;
                } else if (c == 'L') {
                    numL++;
                } else if (c == 'V') {
                    numV++;
                } else if (c == 'e') {
                    nume++;
                } else if (c == 'm') {
                    numm++;
                } else if (c == 'w') {
                    numw++;
                } else if (c == 'M') {
                    numcapM++;
                }
            }
        }
        if (numC > 3 || numX > 3 || numI > 3 || numD > 1 || numL > 1 || numV > 1 || nume > 1
                || numm > 1 || numw > 1 || numcapM > 3) {
            throw new MalformedNumberException("Number was Malformed - Too Many of 1 Digit");
        } else {
            if (nume > 0 && numC > 0) {
                throw new MalformedNumberException("Number was Malformed - Can't Use e and C");
            } else if (numm > 0 && numX > 0) {
                throw new MalformedNumberException("Number was Malformed - Can't Use m and X");
            } else if (numw > 0 && numI > 0) {
                throw new MalformedNumberException("Number was Malformed - Can't Use w and I");
            }
        }

        String[] testOrder = this.number.split("");

        Arrays.sort(testOrder, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return KEY.indexOf(o1) - KEY.indexOf(o2);
            }
        });

        String testOrderFinal = "";
        for (int i = 0; i < testOrder.length; i++) {
            testOrderFinal += testOrder[i];
        }

        if (!Objects.equals(this.number, testOrderFinal)) {
            throw new MalformedNumberException("Number was Malformed - Wasn't in the right order!");
        } else {
            int result = 0;
            for (int i = 0; i < this.number.length(); i++) {
                if (String.valueOf(this.number.charAt(i)).equals("M")) {
                    result += 1000;
                } else if (String.valueOf(this.number.charAt(i)).equals("D")) {
                    result += 500;
                } else if (String.valueOf(this.number.charAt(i)).equals("e")) {
                    result += 400;
                } else if (String.valueOf(this.number.charAt(i)).equals("C")) {
                    result += 100;
                } else if (String.valueOf(this.number.charAt(i)).equals("L")) {
                    result += 50;
                } else if (String.valueOf(this.number.charAt(i)).equals("m")) {
                    result += 40;
                } else if (String.valueOf(this.number.charAt(i)).equals("X")) {
                    result += 10;
                } else if (String.valueOf(this.number.charAt(i)).equals("V")) {
                    result += 5;
                } else if (String.valueOf(this.number.charAt(i)).equals("w")) {
                    result += 4;
                } else if (String.valueOf(this.number.charAt(i)).equals("I")) {
                    result += 1;
                }
            }
            if (result == 0 || result > 3999) {
                throw new ValueOutOfBoundsException("Value was Out of Bounds");
            } else {
                return result;
            }

        }
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() throws MalformedNumberException, ValueOutOfBoundsException {
        if (!this.number.matches("[0-9]+")) {
            try {
                if (Integer.parseInt(this.number) < 1) {
                    throw new ValueOutOfBoundsException("Number is Out of Bounds");
                }
            } catch (NumberFormatException e) {}
            char[] testContains = this.number.toCharArray();
            int numC = 0;
            int numX = 0;
            int numI = 0;
            int numD = 0;
            int numL = 0;
            int numV = 0;
            int nume = 0;
            int numm = 0;
            int numw = 0;
            int numcapM = 0;
            for (char c : testContains) {
                if (!(c == 'M' || c == 'e' || c == 'D' || c == 'C' || c == 'm' || c == 'L'
                        || c == 'X' | c == 'w' || c == 'V' || c == 'I')) {
                        throw new MalformedNumberException("Number was Malformed");
                } else {
                    if (c == 'C') {
                        numC++;
                    } else if (c == 'X') {
                        numX++;
                    } else if (c == 'I') {
                        numI++;
                    } else if (c == 'D') {
                        numD++;
                    } else if (c == 'L') {
                        numL++;
                    } else if (c == 'V') {
                        numV++;
                    } else if (c == 'e') {
                        nume++;
                    } else if (c == 'm') {
                        numm++;
                    } else if (c == 'w') {
                        numw++;
                    } else if (c == 'M') {
                        numcapM++;
                    }
                }
            }
            if (numC > 3 || numX > 3 || numI > 3 || numD > 1 || numL > 1 || numV > 1 || nume > 1
                    || numm > 1 || numw > 1 || numcapM > 3) {
                throw new MalformedNumberException("Number was Malformed - Too Many of 1 Digit");
            } else {
                if (nume > 0 && numC > 0) {
                    throw new MalformedNumberException("Number was Malformed - Can't Use e and C");
                } else if (numm > 0 && numX > 0) {
                    throw new MalformedNumberException("Number was Malformed - Can't Use m and X");
                } else if (numw > 0 && numI > 0) {
                    throw new MalformedNumberException("Number was Malformed - Can't Use w and I");
                }
            }

            String[] testOrder = this.number.split("");

            Arrays.sort(testOrder, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return KEY.indexOf(o1) - KEY.indexOf(o2);
                }
            });

            String testOrderFinal = "";
            for (int i = 0; i < testOrder.length; i++) {
                testOrderFinal += testOrder[i];
            }

            if (!Objects.equals(this.number, testOrderFinal)) {
                throw new MalformedNumberException("Number was Malformed - Wasn't in the right order!");
            } else {
                return this.number;
            }
        } else {
            if (Integer.parseInt(this.number) > 3999 || Integer.parseInt(this.number) < 1) {
                throw new ValueOutOfBoundsException("Number was Out of Bounds");
            } else {
                int numDig = this.number.length();
                int[] sepNum = new int[numDig];
                LinkedList<Character> result = new LinkedList<Character>();
                for (int i = 0; i < numDig; i++) {
                    sepNum[i] = Integer.parseInt(String.valueOf(this.number.charAt(i)));
                }
                int multiplier = 1;
                for (int i = numDig; i > 0; i--) {
                    sepNum[i - 1] = sepNum[i - 1] * multiplier;
                    multiplier = multiplier * 10;
                }
                for (int i = numDig; i > 0; i--) {
                    while ((sepNum[i - 1] - 1000) >= 0) {
                        result.add('M');
                        sepNum[i - 1] = sepNum[i - 1] - 1000;
                    }
                    if (sepNum[i - 1] >= 500) {
                        result.add('D');
                        sepNum[i - 1] = sepNum[i - 1] - 500;
                    }
                    if (sepNum[i - 1] >= 400) {
                        result.add('e');
                        sepNum[i - 1] = sepNum[i - 1] - 400;
                    }
                    while (sepNum[i - 1] >= 100) {
                        result.add('C');
                        sepNum[i - 1] = sepNum[i - 1] - 100;
                    }
                    if (sepNum[i - 1] >= 50) {
                        result.add('L');
                        sepNum[i - 1] = sepNum[i - 1] - 50;
                    }
                    if (sepNum[i - 1] >= 40) {
                        result.add('m');
                        sepNum[i - 1] = sepNum[i - 1] - 40;
                    }
                    while (sepNum[i - 1] >= 10) {
                        result.add('X');
                        sepNum[i - 1] = sepNum[i - 1] - 10;
                    }
                    if (sepNum[i - 1] >= 5) {
                        result.add('V');
                        sepNum[i - 1] = sepNum[i - 1] - 5;
                    }
                    if (sepNum[i - 1] >= 4) {
                        result.add('w');
                        sepNum[i - 1] = sepNum[i - 1] - 4;
                    }
                    while (sepNum[i - 1] >= 1) {
                        result.add('I');
                        sepNum[i - 1] = sepNum[i - 1] - 1;
                    }
                }
                Collections.reverse(result);

                for (int i = 0; i < result.size(); i++) {
                    if ((result.get(i).equals('D') || result.get(i).equals('L') || result.get(i).equals('V')) && !result.getLast().equals(result.get(i))) {
                        if (result.get(i + 1).equals('e') || result.get(i + 1).equals('m') || result.get(i + 1).equals('w')) {
                            char temp = result.get(i);
                            result.set(i, result.get(i + 1));
                            result.set(i + 1, temp);
                        }
                    }
                }

                String resultString = "";
                for (int i = 0; i < result.size(); i++) {
                    resultString += result.get(i);
                }

                String[] finalOut = resultString.split("");

                Arrays.sort(finalOut, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return KEY.indexOf(o1) - KEY.indexOf(o2);
                    }
                });

                String finalOutFinal = "";
                for (int i = 0; i < finalOut.length; i++) {
                    finalOutFinal += finalOut[i];
                }

                return finalOutFinal;
            }
        }
    }
}

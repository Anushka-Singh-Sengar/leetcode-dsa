class Solution {
    public boolean isNumber(String s) {

        boolean digitSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        boolean digitAfterE = true;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Case 1: digit
            if (Character.isDigit(ch)) {

                digitSeen = true;

                if (eSeen) {
                    digitAfterE = true;
                }
            }

            // Case 2: decimal point
            else if (ch == '.') {

                // Dot cannot appear after exponent
                // and cannot appear twice
                if (dotSeen || eSeen) {
                    return false;
                }

                dotSeen = true;
            }

            // Case 3: exponent
            else if (ch == 'e' || ch == 'E') {

                // Exponent can occur only once
                // and there must be a number before it
                if (eSeen || !digitSeen) {
                    return false;
                }

                eSeen = true;
                digitAfterE = false;
            }

            // Case 4: + or -
            else if (ch == '+' || ch == '-') {

                // Sign is valid only at the beginning
                // or immediately after e/E
                if (i != 0 &&
                    s.charAt(i - 1) != 'e' &&
                    s.charAt(i - 1) != 'E') {

                    return false;
                }
            }

            // Case 5: anything else
            else {
                return false;
            }
        }

        return digitSeen && digitAfterE;
    }
}
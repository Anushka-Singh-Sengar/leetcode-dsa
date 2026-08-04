import java.util.*;

class Solution {
    public String sortVowels(String s) {
        List<Character> vowels = new ArrayList<>();
        char[] arr = s.toCharArray();

        // Collect all vowels
        for (char ch : arr) {
            if (isVowel(ch)) {
                vowels.add(ch);
            }
        }

        // Sort vowels by ASCII value
        Collections.sort(vowels);

        // Replace vowels with sorted ones
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (isVowel(arr[i])) {
                arr[i] = vowels.get(index);
                index++;
            }
        }

        return new String(arr);
    }

    private boolean isVowel(char ch) {
        return ch == 'A' || ch == 'E' || ch == 'I' ||
               ch == 'O' || ch == 'U' ||
               ch == 'a' || ch == 'e' || ch == 'i' ||
               ch == 'o' || ch == 'u';
    }
}
class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char c : s.toCharArray()) {

            if (c == '(') {
                // Start a new nested level
                stack.push(0);
            } else {
                // Score inside the current pair
                int inside = stack.pop();

                // () = 1
                // (A) = 2 * A
                int score = Math.max(2 * inside, 1);

                // Add this score to the outer level
                stack.push(stack.pop() + score);
            }
        }

        return stack.pop();
    }
}
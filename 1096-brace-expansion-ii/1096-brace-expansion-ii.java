class Solution {
    int i = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> set = parse(expression);

        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s) {
        Set<String> result = new HashSet<>();

        while (i < s.length() && s.charAt(i) != '}') {

            Set<String> curr = new HashSet<>();

            if (s.charAt(i) == '{') {
                i++; // skip {

                curr = parse(s);

                i++; // skip }
            } 
            else {
                curr.add(String.valueOf(s.charAt(i)));
                i++;
            }

            // Concatenation
            if (result.isEmpty()) {
                result = curr;
            } else {
                Set<String> temp = new HashSet<>();

                for (String a : result) {
                    for (String b : curr) {
                        temp.add(a + b);
                    }
                }

                result = temp;
            }

            // Union
            if (i < s.length() && s.charAt(i) == ',') {
                i++;
                
                Set<String> next = parse(s);
                result.addAll(next);
                break;
            }
        }

        return result;
    }
}
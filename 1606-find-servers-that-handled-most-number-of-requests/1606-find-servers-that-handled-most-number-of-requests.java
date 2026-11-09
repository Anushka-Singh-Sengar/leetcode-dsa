
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        int n = arrival.length;

        // Stores currently available servers
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Min heap: {finishing time, server ID}
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        // Number of requests handled by each server
        int[] count = new int[k];

        for (int i = 0; i < n; i++) {

            // 1. Free servers whose requests are completed
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= arrival[i]) {

                int[] finished = busy.poll();
                available.add(finished[1]);
            }

            // 2. If no server is available, drop request
            if (available.isEmpty()) {
                continue;
            }

            // 3. Find the preferred server
            int start = i % k;

            // Find smallest available server >= start
            Integer server = available.ceiling(start);

            // 4. Wrap around if necessary
            if (server == null) {
                server = available.first();
            }

            // 5. Assign request to server
            available.remove(server);

            count[server]++;

            // 6. Calculate finishing time
            busy.offer(new int[]{
                arrival[i] + load[i], server
            });
        }

        // 7. Find maximum number of requests
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        // 8. Collect busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                result.add(i);
            }
        }

        return result;
    }
}

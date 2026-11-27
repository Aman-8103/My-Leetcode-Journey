class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] free = new int[n];
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    free[i]++;
                }
            }
        }

        // 2. 0/1 Knapsack for the first purchase of each item
        // dp[b] = max copies obtained using exact/at-most budget 'b' from distinct first-time purchases
        int[] dp = new int[budget + 1];
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int value = 1 + free[i];
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + value);
            }
        }

        // 3. Use any leftover budget to buy extra copies of the cheapest item
        int maxTotal = 0;
        for (int b = 0; b <= budget; b++) {
            int extraCopies = (budget - b) / minPrice;
            maxTotal = Math.max(maxTotal, dp[b] + extraCopies);
        }

        return maxTotal;
    }
}

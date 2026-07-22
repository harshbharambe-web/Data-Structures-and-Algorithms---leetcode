class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int profit = 0;
        int min = prices[0];

        for (int i = 0; i < n; i++) {
            min = Math.min(prices[i], min);
            int sum = prices[i] - min;
            profit = Math.max(profit, sum);
        }
        return profit;
    }
}

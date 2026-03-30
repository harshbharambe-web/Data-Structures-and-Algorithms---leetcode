class Solution {
        public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int sum = 0, L = 0;
        
        for (int R = 0; R < nums.length; R++) {
            sum += nums[R];
            
            while (sum >= target) {
                res = Math.min(res, R - L + 1);
                sum -= nums[L];
                L++;
            }
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

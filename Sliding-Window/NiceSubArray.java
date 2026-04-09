class Solution {
    public int atmost(int[] nums, int k) {
        if (k < 0)
            return 0;
        int l = 0;
        int r = 0;
        int count = 0;
        int sum = 0;
        while (r < nums.length) {

            sum += (nums[r] % 2);

            while (sum > k) {

                sum -= nums[l] % 2;
                l = l + 1;

            }

            count += r - l + 1;
            r = r + 1;
          
        }
          return count;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        return atmost(nums, k) - atmost(nums, k - 1);
    }
}

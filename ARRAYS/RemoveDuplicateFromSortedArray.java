class Solution {
    public int removeDuplicates(int[] nums) {

        // If the array is empty, return 0
        if (nums.length == 0) {
            return 0;
        }

        // i points to the last unique element
        int i = 0;

        // j traverses the array
        for (int j = 1; j < nums.length; j++) {

            // If a new unique element is found
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }

        // Number of unique elements
        return i + 1;
    }
}

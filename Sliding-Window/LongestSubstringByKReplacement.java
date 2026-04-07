class Solution {
    public int characterReplacement(String s, int k) {

        int left = 0;
        int [] hash = new int[26];
        int maxFreq = 0;
        int maxLen = 0;

        for(int right = 0; right< s.length(); right++) {

            hash[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq,hash[s.charAt(right)-'A']);

            int len = right - left + 1;

            if(len - maxFreq > k) {

                hash[s.charAt(left)- 'A']--;
                left++;


            }

            maxLen = Math.max(maxLen, right-left+1);


        }
        return maxLen;
    }
}

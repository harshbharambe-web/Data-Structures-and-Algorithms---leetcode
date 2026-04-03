import java.util.*;

class Solution {
    public int numberOfSubstrings(String s) {
        int left = 0;
        int count = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {

            // step 1: add current character
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // step 2: when we have a,b,c
            while (map.size() == 3) {

                // IMPORTANT LINE
                count += (s.length() - right);

                // step 3: shrink window
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);

                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }

                left++;
            }
        }
        return count;
    }
}

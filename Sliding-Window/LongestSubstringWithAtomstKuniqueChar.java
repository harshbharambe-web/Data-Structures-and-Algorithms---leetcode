import java.util.*;

class Solution {

    public int kDistinctChar(String s, int k) {

        int l = 0, r = 0;
        int maxLen = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        while (r < s.length()) {

            char rightChar = s.charAt(r);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

            if (map.size() <= k) {

                maxLen = Math.max(maxLen, r - l + 1);
                r++;

            } else {

                char leftChar = s.charAt(l);
                map.put(leftChar, map.get(leftChar) - 1);

                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }

                l++;
            }
        }

        return maxLen;
    }
}

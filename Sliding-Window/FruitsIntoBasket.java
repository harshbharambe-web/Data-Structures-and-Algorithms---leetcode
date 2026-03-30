import java.util.*;

class Solution {
    public int totalFruit(int[] fruits) {

        int left = 0;      // window start
        int maxLen = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int right = 0; right < fruits.length; right++) {

            // 👉 STEP 1: add fruit in map
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            // 👉 STEP 2: if more than 2 types → shrink
            while (map.size() > 2) {

                // remove left fruit
                map.put(fruits[left], map.get(fruits[left]) - 1);

                // if count becomes 0 → remove from map
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }

                left++; // move window
            }

            // 👉 STEP 3: update answer
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

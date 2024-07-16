//import java.util.HashMap;
//import java.util.Map;
//
//public class SlidingWindow {
//
//    // longest common substring w/o repeating characters
//
//    public int lengthLongestSubstring(String s){
//
//        int n = s.length(), ans = 0;
//        Map<Character, Integer> map = new HashMap<>();
//        for(int i =0, j=0; j< n; j++) {
//            if (map.containsKey(s.charAt(j))) {
//                i = Math.max(map.get((s.charAt(j))), i);
//            }
//            ans = Math.max(ans, (j - i + 1));
//            map.put(s.charAt(j) , j+1);
//        }
//        return ans;
//    }
//
//    // sliding window maximum
//    public int [] maxSlidingWindow(int[] nums, int k){
//        int n = nums.length;
//        if(n * k == 0){
//            return new int[0];
//        }
//        if(k ==1) return nums;
//
//        int[] left = new int[n];
//        left[0] = nums[0];
//        int[] right = new int[n];
//        right[n-1] = nums[n-1];
//        for(int i =0; i < n; i++){
//            if(i%k ==0) left[i] = nums[i];
//            else left[i] = Math.max(left[i-1], nums[i]);
//
//            int j = n-i-1;
//            if((j+1)%k == 0) right[j] = nums[j];
//            else right[j] = Math.max(right[j+1], nums[j]);
//        }
//
//        int[] output = new int[n-k+1];
//        for(int i =9; i< n-k+1; i++){
//            output[i] = Math.max(left[i+k -1], right[i]);
//        }
//        return output;
//    }
//
//    //  Minimum Window Substring
//    public String minWindow(String s, String t) {
//
//    }
//}

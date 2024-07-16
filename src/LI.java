import java.util.*;

public class LI {

    // Kth Largest Element in an Array
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((x,y) -> x-y);
        for(int num: nums){
            heap.add(num);
            if(heap.size() >k){
                heap.poll();
            }
        }
        return heap.poll();
    }
    // Max Points on a Line
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n == 1) {
            return 1;
        }
        int result = 2;
        for (int i = 0; i < n; i++) {
            Map<Double, Integer> cnt = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    cnt.merge(Math.atan2(points[j][1] - points[i][1],
                            points[j][0] - points[i][0]), 1, Integer::sum);
                }
            }
            result = Math.max(result, Collections.max(cnt.values()) + 1);
        }
        return result;
    }
    // Longest Palindromic Subsequence
    public int longestPalindromeSubseq(String s) {
        int [][] dp = new int[s.length()][s.length()];
        for(int i = s.length() -1; i>=0;i-- ){
            dp[i][i] =1;
            for(int j =i+1; j< s.length(); j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1]+2;
                }else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
    // Partition Labels -- You are given a string s. We want to partition the string into as many parts as possible
    // so that each letter appears in at most one part.
    public List<Integer> partitionLabels(String s) {
        int[] last = new int [26];
        for(int i =0; i< s.length(); i++){
            last[s.charAt(i) - 'a'] = i;
        }
        int j =0, anchor = 0;
        List<Integer> ans = new ArrayList<>();
        for(int i =0; i< s.length(); i++){
            j = Math.max(j, last[s.charAt(i) - 'a']);
            if(i == j ){
                ans.add(i - anchor +1);
                anchor = i + 1;
            }
        }
        return ans;
    }
    // Word Ladder -- A transformation sequence from word beginWord to word endWord using a dictionary wordList is a
    // sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        // COUNT NUMBER OF WORDS TRANSFORMED
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // FOR ALL WORDS THIS ROUND
            for (int i = 0; i < size; i++) {
                char[] current = queue.poll().toCharArray();
                // TRAVERSE CURRENT WORD
                for (int j = 0; j < current.length; j++) {
                    char tmp = current[j];
                    // CHANGE ONE LETTER AT A TIME
                    for (char c = 'a'; c <= 'z'; c++) {
                        current[j] = c;
                        String next = new String(current);
                        // WHEN NEXT WORD IS IN THE SET
                        if (set.contains(next)) {
                            if (next.equals(endWord)) return count + 1;
                            queue.add(next);
                            set.remove(next);
                        }
                    }
                    // HAVE TO UNDO FOR NEXT CHANGE OF LETTER
                    current[j] = tmp;
                }
            }
            // THIS ROUND ENDS WITH ONE LETTER CHANGED
            count++;
        }
        return 0;
    }
    
}

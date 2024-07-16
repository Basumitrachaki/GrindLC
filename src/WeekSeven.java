import java.util.*;

public class WeekSeven {

    // Container With Most Water -- two pointers -- greedy
    public int maxArea(int[] height) {
        int maxArea = 0, l =0, r = height.length -1;
        while (l < r){
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r-1));
            if(height[l] < height[r]){
                l++;
            }else {
                r--;
            }
        }
        return maxArea;
    }

    // Letter Combinations of a Phone Number -- backtracking
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    List<String> output = new ArrayList<>();
    public void backtrack(String combination, String next){
        if(next.length() == 0){
            output.add(combination);
        }else {
            String digit = next.substring(0,1);
            String letters = phone.get(digit);
            for(int i =0; i < letters.length(); i++){
                String letter = phone.get(digit).substring(i , i+1);
                backtrack(combination, next.substring(1));
            }
        }
    }
    public List<String> letterCombinations(String digits) {
        if(digits.length() != 0){
            backtrack("", digits);
        }
        return output;
    }

    // Word Search -- Given an m x n grid of characters board and a string word, return true if word exists in the grid-- backtracking
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (exist(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }
    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] ^= 256;
        boolean exist = exist(board, y, x+1, word, i+1)
                || exist(board, y, x-1, word, i+1)
                || exist(board, y+1, x, word, i+1)
                || exist(board, y-1, x, word, i+1);
        board[y][x] ^= 256;
        return exist;
    }

    // Find All Anagrams in a String -- Given two strings s and p, return an array of all the start indices of
    // p's anagrams in s. You may return the answer in any order. sliding window
    public List<Integer> findAnagrams(String s, String p) {
        int ns = s.length(), np = p.length();
        if(ns < np){
            return new ArrayList<>();
        }
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for(char c: p.toCharArray()){
            pCount[(int)(c = 'a')]++;
        }
        List<Integer> output = new ArrayList<>();
        for(int i =0; i< s.length(); i++){
            sCount[(int)(s.charAt(i) - 'a')]++;
            if(i > np){
                sCount[(int)(s.charAt(i - np) - 'a')]--;
            }
            if(Arrays.equals(sCount, pCount)){
                output.add(i - np +1);
            }
        }
        return output;
    }

    // Minimum Height Trees -- topological sort, Graph, BFS -- not doing

    // Task Scheduler -- You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time,
    // n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a
    // constraint: identical tasks must be separated by at least n intervals due to cooling time. Greedy, Heap
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(int t: tasks){
            freq[t - 'A']++;
        }
        Arrays.sort(freq);
        int f_max = freq[25];
        int idle_time = (f_max - 1) * n;
        for(int i = freq.length -2; i >=0 && idle_time > 0; --i) {
            idle_time -= Math.min(f_max -1, freq[i] );
        }
        idle_time = Math.max(0, idle_time);
        return idle_time + tasks.length;

    }

    // LRU Cache
    class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;
        public LRUCache(int capacity) {
            super(capacity, .75F, true);
            this.capacity =capacity;
        }
        public int get(int key) {
           return super.getOrDefault(key, -1);
    }
        public void put(int key, int value) {
            super.put(key, value);
        }
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
            return size() > capacity;
        }
    }
    }

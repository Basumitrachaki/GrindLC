import java.util.*;

public class WeekSix {

    // Word Break -- Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a
    // space-separated sequence of one or more dictionary words. [leetcode] -> leet, code -- DP
    public boolean wordBreak(String s, List<String> wordDict ){
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i =1; i<= s.length(); i++){
            for(int j = 0; j<i; j++){
                if(dp[j] && (wordDictSet.contains(s.substring(j, i)))){
                    dp[i] = true;
                    break;
                }
            }
        }
        for(boolean b: dp){
            System.out.println(b);
        }
        return dp[s.length()];
    }

    // Partition Equal Subset Sum -- Given an integer array nums, return true if you can partition the array into
    // two subsets such that the sum of the elements in both subsets is equal or false otherwise. -- dp
    public boolean canPartition(int[] nums) {
        if(nums.length == 0){
            return false;
        }
        int totalSum  =0;
        for(int sum : nums){
            totalSum += sum;
        }
        if(totalSum % 2 != 0){
            return false;
        }
        int subSetSum  = totalSum/2;
        boolean[] dp = new boolean[subSetSum + 1];
        dp[0] = true;
        for(int curr: nums){
            for(int j = subSetSum; j>=curr; j--){
                System.out.println(curr);
                System.out.println(j);
                dp[j] = dp[j] || dp[j -curr];
            }
        }
        return dp[subSetSum];
    }

    // String to Integer (atoi) not doing

    // Spiral Matrix
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int up =0; int left = 0; int down = rows -1; int right = left -1;
        while (result.size() <= rows* cols){
            // left to right
            for(int col = left; col <= right; col++){
                result.add(matrix[up][col]);
            }
            // Traverse downwards.
            for(int row = up+1; row <= down; row++){
                result.add(matrix[row][right]);
            }
            // Make sure we are now on a different row.
            if (up != down){
                // right to left.
                for(int r = right -1; r >= left; r--){
                    result.add(matrix[down][r]);
                }
            }
            // Make sure we are now on a different column.
            if (left != right){
                // travel up
                for(int row = down -1; row > up; row++){
                    result.add(matrix[row][left]);
                }
            }
            left++;right--; up++; down--;
        }
        return result;
    }

    // Subsets -- backtrack -- Input: nums = [1,2,3]
    //Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    private void backtrackSubset(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i =0; i < nums.length; i++){
            tempList.add(nums[i]);
            this.backtrackSubset(list, tempList, nums, i+1);
            tempList.remove(tempList.size() -1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        if(nums.length == 0){
            return null;
        }
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        this.backtrackSubset(list, new ArrayList<>(), nums, 0);
        return list;
    }

    // Binary Tree Right Side View
    List<Integer> right = new ArrayList<Integer>();
    private void helper(TreeNode root, int level){
        if(level == right.size()){
            right.add(root.val);
        }
        if(root.right != null){
            helper(root.right , level+1);
        }
        if(root.left != null){
            helper(root.left, level+1);
        }
    }
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null){
            return right;
        }
        helper(root, 0);
        return right;
    }

    // Longest Palindromic Substring -- dp -- two pointers
    private int expandCenter(String s, int left, int right){
        int l = left; int r = right;
        while (l >=0 && r < s.length() && (s.charAt(l) == s.charAt(r))){
            l--; r++;
        }
        return (r-l+1);
    }
    public String longestPalindrome(String s) {
        if(s == null || s.length() <1){
            return s;
        }
        int start =0, end =0;
        for(int i =0; i< s.length(); i++){
            int len1 = expandCenter(s, i ,i);
            int len2 = expandCenter(s, i , i+1);
            int len = Math.max(len1, len2);
            if(len > end - start){
                start = i - (len -1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start, end+1);
    }

    // Unique Paths for robot -- DP
    public int uniquePaths(int m, int n) {
        int [][] dp = new int[m][n];
        for(int j =0; j< n; j++){
            dp[m-1][j] =1;
        }
        for(int i =0; i< m; i++){
            dp[i][n-1] = 1;
        }
        for(int i =m-2; i >=0; i-- ){
            for(int j = n-2; j >=0; j--){
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }
        return dp[0][0];
    }

    // Construct Binary Tree from Preorder and Inorder Traversal
    class Solution {
        int preorderIndex;
        Map<Integer, Integer> inorderIndexMap;
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            preorderIndex = 0;
            // build a hashmap to store value -> its index relations
            inorderIndexMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderIndexMap.put(inorder[i], i);
            }

            return arrayToTree(preorder, 0, preorder.length - 1);
        }

        private TreeNode arrayToTree(int[] preorder, int left, int right) {
            // if there are no elements to construct the tree
            if (left > right) return null;

            // select the preorder_index element as the root and increment it
            int rootValue = preorder[preorderIndex++];
            TreeNode root = new TreeNode(rootValue);

            // build left and right subtree
            // excluding inorderIndexMap[rootValue] element because it's the root
            root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
            root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
            return root;
        }
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        WeekSix ws = new WeekSix();
//        System.out.println(ws.wordBreak(s, wordDict));
        int[] arr = {1, 5, 5, 1};
//        System.out.println(ws.canPartition(arr));
    }
}

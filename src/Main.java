import java.util.*;

public class Main {

    // implement stack using queues
    class  MyStack {

        private Queue<Integer>q1 = new LinkedList<>();
        private Queue<Integer>q2 = new LinkedList<>();
        private int top;


    public MyStack() {

        }

        public void push(int x) {
            q1.add(x);
            top = x;
        }

        public int pop() {
            while (q1.size() > 1) {
                top = q1.remove();
                q2.add(top);
            }
            q1.remove();
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return top;
        }

//        public int top() {
//            q1.remove();
//            if (!q1.isEmpty()) {
//                top = q1.peek();
//            }
//        }

//        public boolean empty() {
//
//        }
    }

    // snake pattern
    public static void printSnake(int[][] arr){
        if(arr.length * arr[0].length ==0){
            System.out.println(0);
        }
        for(int i = 0; i< arr.length; i++){
            if(i % 2 == 0){
                for(int j = 0; j < arr[0].length; j++){
                    System.out.print(arr[i][j] + " ");
                }
            }else{
                for(int j = arr[0].length -1; j >=0; j--){
                    System.out.print(arr[i][j] + " ");
                }
            }
        }
    }

    // Longest Substring Without Repeating Characters.. Input: s = "abcabcbb" output = abc
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans =0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i =0, j =0; j< n; j++){
            if(map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, (j-i+1));
            map.put(s.charAt(j), j+1);
        }
        return ans;
    }

    // Valid Parentheses
    public boolean isValidParan(String s) {
        if(s == null){
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(c =='('){
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            }else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    // Rotate Image
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i =0; i< n/2 + n%2; i++){
            for(int j = 0; j < n/2; j++){
                int[] tmp = new int[4];
                int row = i;
                int col = j;
                for (int k = 0; k < 4; k++) {
                    tmp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                for (int k = 0; k < 4; k++) {
                    matrix[row][col] = tmp[(k + 3) % 4];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
            }
        }
    }

    // Climbing Stairs
    public int climbStairs(int n) {
        if(n ==1){
            return 1;
        }
        int [] dp = new int[n+1];
        dp[1] =1;
        dp[2] = 2;
        for(int i =3; i<=n; i++){
            dp[i] = dp[i-1]+ dp[i-2];
        }
        return dp[n];
    }

    // Number of 1 Bits
    public int hammingWeight(int n) {
        int sum =0;
        while ( n !=0){
            sum++;
            n &= (n-1);
        }
        return sum;
    }

    // Reverse Linked List
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr!= null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return  prev;
    }

    // Find if Path Exists in Graph
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i =0; i< n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge: edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] seen = new boolean[n];
        stack.push(source);
        Arrays.fill(seen, false);
        while (!stack.isEmpty()){
            int node = stack.pop();
            if(node == destination){
                return true;
            }
            if(seen[node]){
                continue;
            }
            seen[node] = true;
            for(int neighbours: adjList.get(node)){
                stack.push(neighbours);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int mat[][] = new int[][] { { 10, 20, 30, 40 },
                { 15, 25, 35, 45 },
                { 27, 29, 37, 48 },
                { 32, 33, 39, 50 } };
        printSnake(mat);
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
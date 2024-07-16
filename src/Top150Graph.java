import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Top150Graph {
    // Number of Islands
    public int numIslands(char[][] grid) {
        if(grid.length ==0 || grid[0].length ==0){
            return 0;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        int numIslands = 0;
        for(int i =0; i< nr; i++){
            for(int j =0; j< nc; j++){
                if(grid[i][j] == '1'){
                    numIslands++;
                    helper(grid, i, j);
                }
            }
        }
        return numIslands;
    }
    public void helper(char[][] grid, int r, int c){
        int nr = grid.length;
        int nc = grid[0].length;
        if(r <0 || r>= nr || c < 0 || c >= nc || grid[r][c] == '0'){
            return;
        }
        grid[r][c] = '0';
        helper(grid, r-1, c);
        helper(grid, r+1, c);
        helper(grid, r, c-1);
        helper(grid, r, c+1);
    }

    // Surrounded Regions
    int[] dx = new int[]{0,0,1,-1};
    int[] dy = new int[]{1,-1,0,0};
    public void dfs(int x, int y, int[][] v,char[][] b){
        v[x][y] = 1;
        for(int i = 0;i<4;i++) {
            int r = x + dx[i];
            int c = y + dy[i];
            if(r >=0 && r <b.length && c >= 0 && c < b[0].length && v[r][c] == 0 && b[r][c] == 'O') {
                dfs(r, c, v, b);
            }
        }
    }
    public void solve(char[][] board) {
        int[][] v = new int[board.length][board[0].length];
        for(int i = 0;i<board.length;i++){
            if(v[i][0] == 0 && board[i][0] == 'O'){
                dfs(i,0,v,board);
            }
            if(v[i][board[0].length-1] == 0 && board[i][board[0].length-1] == 'O'){
                dfs(i,board[0].length-1,v,board);
            }
        }
        for(int i = 0;i<board[0].length;i++){
            if(v[0][i] == 0 && board[0][i] == 'O'){
                dfs(0,i,v,board);
            }
            if(v[board.length-1][i] == 0 && board[board.length-1][i] == 'O'){
                dfs(board.length-1,i,v,board);
            }
        }

        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                if(v[i][j] == 0 && board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }

    // Clone Graph
    private HashMap<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null)
            return node;
        if(visited.containsKey(node))
            return visited.get(node);
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);
        for(Node neighbours: node.neighbors){
            cloneNode.neighbors.add(cloneGraph(neighbours));
        }
        return cloneNode;
    }


    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}

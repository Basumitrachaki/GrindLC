import java.util.LinkedList;
import java.util.Queue;

public class TreeBasic {

    TreeNode root;
    void bfs(){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            System.out.println(temp.val+" ");
            if(temp.left != null){
                queue.add(temp.left);
            }
            if(temp.right != null){
                queue.add(temp.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeBasic tree_level = new TreeBasic();
        tree_level.root = new TreeNode(1);
        tree_level.root.left = new TreeNode(2);
        tree_level.root.right = new TreeNode(3);
        tree_level.root.left.left = new TreeNode(4);
        tree_level.root.left.right = new TreeNode(5);

        System.out.println("Level order traversal of binary tree is - ");
        tree_level.bfs();
    }
}

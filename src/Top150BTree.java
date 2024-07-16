import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Top150BTree {

    // Maximum Depth of Binary Tree
    // dfs
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int left = maxDepth((root.left));
        int right = maxDepth(root.right);
        int big = Math.max(left, right);
        return big +1;
    }

    // BFS
    public int maxDepthbfs(TreeNode root) {
        if(root == null)
            return 0;
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> depths = new LinkedList<>();
        int depth =0, curr_depth = 0;
        stack.add(root);
        depths.add(1);
        while (!stack.isEmpty()){
            root = stack.pollLast();
            curr_depth = depths.pollLast();
            if(root != null){
                depth = Math.max(curr_depth, depth);
                stack.add(root.left);
                depths.add(curr_depth+1);
                stack.add(root.right);
                depths.add(curr_depth+1);
            }
        }
        return depth;
    }

    // Same Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p!=null && q!= null){
            return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    // Invert Binary Tree
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if(current.left != null)
                queue.add(current.left);
            if(current.right != null)
                queue.add(current.right);
        }
        return root;
    }

    // Symmetric Tree
    public boolean isSymmetric(TreeNode root) {
        return (root == null || checkSymmetry(root.left, root.right));
    }
    private boolean checkSymmetry(TreeNode lTree, TreeNode rTree){
        if(lTree == null && rTree == null)
            return true;
        if (lTree != null && rTree != null){
            return (lTree.val == rTree.val && checkSymmetry(lTree.left, rTree.left) && checkSymmetry(lTree.right, rTree.right));
        }
        return false;
    }

    // Populating Next Right Pointers in Each Node II
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // The root node is the only node on the first level
        // and hence its the leftmost node for that level
        this.leftmost = root;

        // Variable to keep track of leading node on the "current" level
        Node curr = leftmost;

        // We have no idea about the structure of the tree,
        // so, we keep going until we do find the last level.
        // the nodes on the last level won't have any children
        while (this.leftmost != null) {
            // "prev" tracks the latest node on the "next" level
            // while "curr" tracks the latest node on the current
            // level.
            this.prev = null;
            curr = this.leftmost;

            // We reset this so that we can re-assign it to the leftmost
            // node of the next level. Also, if there isn't one, this
            // would help break us out of the outermost loop.
            this.leftmost = null;

            // Iterate on the nodes in the current level using
            // the next pointers already established.
            while (curr != null) {
                // Process both the children and update the prev
                // and leftmost pointers as necessary.
                this.processChild(curr.left);
                this.processChild(curr.right);

                // Move onto the next node.
                curr = curr.next;
            }
        }

        return root;
    }

    Node prev, leftmost;

    public void processChild(Node childNode) {
        if (childNode != null) {
            // If the "prev" pointer is alread set i.e. if we
            // already found atleast one node on the next level,
            // setup its next pointer
            if (this.prev != null) {
                this.prev.next = childNode;
            } else {
                // Else it means this child node is the first node
                // we have encountered on the next level, so, we
                // set the leftmost pointer
                this.leftmost = childNode;
            }

            this.prev = childNode;
        }
    }

    // Flatten Binary Tree to Linked List
    public void flatten(TreeNode root) {
        getFlatten(root);
    }
    public void getFlatten(TreeNode root){
        if(root ==null){
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left=null;
        getFlatten(left);
        getFlatten(right);
        root.right=left;
        TreeNode current = root;
        while(current.right !=null) current = current.right;
        current.right =right;
    }

    // Path Sum
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.right == null && root.right == null)
            return root.val == sum;
        return (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val));
    }

    // Sum Root to Leaf Numbers
//    Input: root = [1,2,3]
//    Output: 25
//    Explanation:
//    The root-to-leaf path 1->2 represents the number 12.
//    The root-to-leaf path 1->3 represents the number 13.
//    Therefore, sum = 12 + 13 = 25.

    public int sumNumbers(TreeNode root) {
        int rootToLeaf = 0, currNumber = 0;
        int steps;
        TreeNode predecessor;

        while (root != null) {
            // If there is a left child,
            // then compute the predecessor.
            // If there is no link predecessor.right = root --> set it.
            // If there is a link predecessor.right = root --> break it.
            if (root.left != null) {
                // Predecessor node is one step to the left
                // and then to the right till you can.
                predecessor = root.left;
                steps = 1;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                    ++steps;
                }

                // Set link predecessor.right = root
                // and go to explore the left subtree
                if (predecessor.right == null) {
                    currNumber = currNumber * 10 + root.val;
                    predecessor.right = root;
                    root = root.left;
                }
                // Break the link predecessor.right = root
                // Once the link is broken,
                // it's time to change subtree and go to the right
                else {
                    // If you're on the leaf, update the sum
                    if (predecessor.left == null) {
                        rootToLeaf += currNumber;
                    }
                    // This part of tree is explored, backtrack
                    for (int i = 0; i < steps; ++i) {
                        currNumber /= 10;
                    }
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // If there is no left child
            // then just go right.
            else {
                currNumber = currNumber * 10 + root.val;
                // if you're on the leaf, update the sum
                if (root.right == null) {
                    rootToLeaf += currNumber;
                }
                root = root.right;
            }
        }
        return rootToLeaf;
    }

    // Binary Tree Maximum Path Sum
    int maxGain = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxGain;
    }
    private int helper(TreeNode node){
        if (node == null)
            return 0;
        int leftGain = Math.max(helper(node.left),0);
        int rightGain = Math.max(helper(node.right),0);
        int currMaxValue = node.val + leftGain + rightGain;
        maxGain = Math.max(maxGain, currMaxValue);
        return node.val + leftGain + rightGain;
    }

    // Binary Search Tree Iterator
    class BSTIterator {
        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            // Stack for the recursion simulation
            this.stack = new Stack<TreeNode>();

            // Remember that the algorithm starts with a call to the helper function
            // with the root node as the input
            this._leftmostInorder(root);
        }

        private void _leftmostInorder(TreeNode root) {
            // For a given node, add all the elements in the leftmost branch of the tree
            // under it to the stack.
            while (root != null) {
                this.stack.push(root);
                root = root.left;
            }
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            // Node at the top of the stack is the next smallest element
            TreeNode topmostNode = this.stack.pop();

            // Need to maintain the invariant. If the node has a right child, call the
            // helper function for the right child
            if (topmostNode.right != null) {
                this._leftmostInorder(topmostNode.right);
            }

            return topmostNode.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return this.stack.size() > 0;
        }
    }

    // Count Complete Tree Nodes

    public int countNodes(TreeNode root) {
        if(root==null)
            return 0;
        int leftHeight = getLeftHeight(root) + 1;
        int rightHeight = getRightHeight(root) + 1;
        if(leftHeight == rightHeight){
            return (2<<(rightHeight-1))-1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
    private int getLeftHeight(TreeNode n){
        if(n==null) return 0;

        int height=0;
        while(n.left!=null){
            height++;
            n = n.left;
        }
        return height;
    }

    private int getRightHeight(TreeNode n){
        if(n==null) return 0;

        int height=0;
        while(n.right!=null){
            height++;
            n = n.right;
        }
        return height;
    }

    // Lowest Common Ancestor of a Binary Tree
    private TreeNode  ans;
    private boolean recurseTree(TreeNode root, TreeNode p, TreeNode q){
        if(root == null)
            return false;
        int left = this.recurseTree(root.left,p,q) ? 1 :0;
        int right = this.recurseTree(root.right,p,q) ? 1 :0;
        int mid = (root == p || root == q)? 1:0;
        if((mid + left + right) >1)
            this.ans = root;
        return (mid + left + right >0);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.recurseTree(root, p ,q);
        return this.ans;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}

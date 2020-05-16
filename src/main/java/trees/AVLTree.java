package trees;

public class AVLTree {

	class Node{
		Node left, right;
		int val, height;

		public Node(Node left, Node right, int val, int height) {
			this.left = left;
			this.right = right;
			this.val = val;
			this.height = height;
		}
	}

	private Node root;

	AVLTree(){root = null;}

	private Node newNode(int val){
		return new Node(null,null,val,1);
	}

	private int height(Node r){
		if(r == null) return 0;
		return r.height;
	}

	private int getBalance(Node r){
		if(r == null) return 0;
		return height(r.left) - height(r.right);
	}

	private Node leftRotate(Node x){
		if(x.right == null) return x;
		Node y = x.right;
		Node T = y.left;
		y.left = x;
		x.right = T;
		y.height = 1 + Math.max(height(y.left), height(y.right));
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return y;
	}

	private Node rightRotate(Node y){
		if(y.left == null)return y;
		Node x = y.left;
		Node T = x.right;
		x.right = y;
		y.left = T;
		y.height = 1 + Math.max(height(y.left), height(y.right));
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return x;
	}

	public void insert(int val){
		root = insertUtil(root, val);
 	}

 	private Node insertUtil(Node r, int val){
		if(r == null) {return newNode(val);}
		else if(val < r.val) {r.left = insertUtil(r.left, val);}
		else if(val > r.val) {r.right = insertUtil(r.right, val);}
		else {return r;}

		r.height = 1 + Math.max(height(r.left), height(r.right));
		int balance = getBalance(r);
		if(balance > 1){
			Node y = r.left;
			if(val > y.val){
				r.left = leftRotate(y);
			}
			return rightRotate(r);
		}
		if(balance < -1){
			Node y = r.right;
			if(val < y.val){
				r.right = rightRotate(y);
			}
			return leftRotate(r);
		}
		return r;
	}

	public void preOrder(){
		System.out.println();
		preOrderHelper(root);
		System.out.println();
		return;
	}

	private void preOrderHelper(Node r){
		if(r == null)return;
		System.out.print(r.val + " ");
		preOrderHelper(r.left);
		preOrderHelper(r.right);
	}

	public void inOrder(){
		System.out.println();
		inOrderHelper(root);
		System.out.println();
		return;
	}

	private void inOrderHelper(Node r){
		if(r == null)return;
		inOrderHelper(r.left);
		System.out.print(r.val + " ");
		inOrderHelper(r.right);
	}
}

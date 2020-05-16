package trees;

public class RBTree {

	enum Color{
		RED,BLACK
	}

	class Node{

		int val;
		Node parent, left, right;
		Color color;

		Node(int val, Node parent, Node left, Node right, Color color){
			this.val = val;
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.color = color;
		}

		boolean isNil(){
			return this.left == null && this.right == null;
		}
	}

	private Node nilNode(){
		return new Node(-1,null,null,null, Color.BLACK);
	}

	private Node newNode(int val){
		return new Node(val, null, nilNode(), nilNode(), Color.RED);
	}

	private Node root;

	RBTree(){
		root = nilNode();
	}

	private void leftRotate(Node x){
		Node parent = x.parent;
		Node y = x.right;
		if(!parent.isNil() && x == parent.left){
			parent.left = y;
		}else if(!parent.isNil() && x == parent.right){
			parent.right = y;
		}
		y.parent = parent;
		x.right = y.left;
		x.right.parent = x;
		y.left = x;
		x.parent = y;
		if(x ==  root) root = y;
		return;
	}

	private void rightRotate(Node y){
		Node parent = y.parent;
		Node x = y.left;
		if(!parent.isNil() && y == parent.right){
			parent.right = x;
		}else if(!parent.isNil() && y == parent.left){
			parent.left = x;
		}
		x.parent = parent;
		y.left = x.right;
		y.left.parent = y;
		x.right = y;
		y.parent = x;
		if(y == root) root = x;
		return;
	}

	public void insert(int val){

		Node z = newNode(val);
		Node x = root;
		Node y = nilNode();

		while(!x.isNil()){
			y = x;
			x = (z.val < x.val) ? x.left : x.right;
		}

		if(y.isNil()){
			root = z;
			root.color = Color.BLACK;
			root.parent = y;
			return;
		}
		if(z.val < y.val){
			y.left = z;
		}else{
			y.right = z;
		}
		z.parent = y;
		fixInsertion(z);
		return;
	}

	private void fixInsertion(Node z){

		while(z.parent.color == Color.RED && z != root){
			Node p = z.parent;
			Node gp = p.parent;
			Node u;
			if(p == gp.left){
				u = gp.right;
				if(u.color == Color.RED){
					p.color = Color.BLACK;
					u.color = Color.BLACK;
					gp.color = Color.RED;
					z = gp;
				}else{
					if(z == p.right){
						leftRotate(p);
						z = p;
					}
					rightRotate(gp);
					z.parent.color = Color.BLACK;
					gp.color = Color.RED;
				}
			}else{
				u = gp.left;
				if (u.color == Color.RED) {
					u.color = Color.BLACK;
					p.color = Color.BLACK;
					gp.color = Color.RED;
					z = gp;
				}else{
					if(z == p.left){
						rightRotate(p);
						z = p;
					}
					leftRotate(gp);
					z.parent.color = Color.BLACK;
					gp.color = Color.RED;
				}
			}
		}
		root.color = Color.BLACK;
		return;
	}

	public void printLevelOrderTraversal(){

		int count = countNodesInTree(root);
		int F, B, qSize = count/2 + 2;
		Node[] q = new Node[qSize];
		F=B=0;
		if(!root.isNil()) q[B++] = root;
		while(F!=B){
			Node current = q[F++];
			F = F%qSize;
			System.out.print(current.val + "  ");
			if(!current.left.isNil()){
				q[B++] = current.left;
				B = B%qSize;
			}
			if(!current.right.isNil()){
				q[B++] = current.right;
				B = B % qSize;
			}
		}
		return;
	}

	public void printInorder(){
		inOrderHelper(root);
	}

	public void inOrderHelper(Node r){
		if(r.isNil()) return;
		inOrderHelper(r.left);
		System.out.print(r.val + " ");
		inOrderHelper(r.right);
		return;
	}


	public int countNodesInTree(Node root){
		if(root.isNil()) return 0;
		return 1 + countNodesInTree(root.left) + countNodesInTree(root.right);

	}

}

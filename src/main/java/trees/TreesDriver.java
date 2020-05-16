package trees;

public class TreesDriver {

	public static void main(String[] args){
//		RBTree tree = new RBTree();
//		tree.insert(7);
//		tree.insert(6);
//		tree.insert(5);
//		tree.insert(4);
//		tree.insert(3);
//		tree.insert(2);
//		tree.insert(1);
//		tree.printInorder();
//		System.out.println();
//		System.out.println();
//		tree.printLevelOrderTraversal();

		AVLTree avlTree = new AVLTree();
		avlTree.insert(10);
		avlTree.insert(20);
		avlTree.insert(30);
		avlTree.insert(40);
		avlTree.insert(50);
		avlTree.insert(25);
		avlTree.preOrder();
		avlTree.inOrder();
	}
}

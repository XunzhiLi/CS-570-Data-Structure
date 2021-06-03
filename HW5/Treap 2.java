/**Name:Xunzhi Li
 * Homework5
 * ID:10457500
 */
package hw5;

import java.util.Random;
import java.util.Stack;


public class Treap<E> {
	public static void main(String[] args) {
		Treap testTree = new Treap<Integer>();
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		System.out.println(testTree.find(4));
		System.out.println(testTree.find(10));

		System.out.println(testTree.delete(5));
		System.out.println(testTree.toString());
	}

	private static class Node<E> {
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;

		public Node(E data, int priority) {
			if (data == null) {
				throw new NumberFormatException("data is null");
			}
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}

		public String toString() {
			return data.toString();
		}

		public Node<E> rotateRight() {
			Node<E> this_left = this.left;
			if (this_left == null) return this;
			Node<E> old_this_right = this.right;
//			copy "this" itself as its right node
			this.right = new Node<>(this.data, this.priority);
			this.right.left = this_left.right;
			this.right.right = old_this_right;
			this.data = this_left.data;
			this.priority = this_left.priority;
			this.left = this_left.left;
			return this;
		}
		public Node<E> rotateLeft() {
			Node<E> this_right = this.right;
			if (this_right == null) return this;
			Node<E> old_this_left = this.left;
//			copy "this" itself as its left node
			this.left = new Node<>(this.data, this.priority);
			this.left.right = this_right.left;
			this.left.left = old_this_left;
			this.data = this_right.data;
			this.priority = this_right.priority;
			this.right = this_right.right;
			return this;
		}
	}

	private Random priorityGenerator;
	private Node<E> root;
	private boolean deleteReturn; //its for delete method

	public Treap() {
		this.priorityGenerator = new Random();
		this.root = null;
	}

	public Treap(long seed) {
		this.priorityGenerator = new Random(seed);
		this.root = null;
	}

	public boolean add(E key) {
		int seed = this.priorityGenerator.nextInt();
		return add(key, seed);
	}

	public boolean add(E key, int priority) {
		System.out.println(key.toString());
		if (find(key)) {
			return false;
		}
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		} else {
			Node<E> new_node = new Node<E>(key, priority);
			Stack<Node<E>> pathstack = new Stack<Node<E>>();
			Node<E> temperary_pos = root;
			while (temperary_pos != null) {
				pathstack.push(temperary_pos);
				if (key.toString().compareTo(temperary_pos.data.toString()) > 0) {
					if (temperary_pos.right == null) {
						temperary_pos.right = new_node;
      					reheap(pathstack, temperary_pos.right);
						return true;
					} else {
						temperary_pos = temperary_pos.right;
					}
				} else {
					if (temperary_pos.left == null) {
						temperary_pos.left = new_node;
						reheap(pathstack, temperary_pos.left);
						return true;
					} else {
						temperary_pos = temperary_pos.left;
					}
				}
			}
			return false;

		}
	}

	 public void reheap(Stack<Node<E>> S, Node<E> temperary_pos) {
	  while (S.size()>0) {
	   Node<E> parent = S.pop();
	   if (temperary_pos.priority > parent.priority) {
		if (parent.data.toString().compareTo(temperary_pos.data.toString()) > 0) {
		 temperary_pos = parent.rotateRight();
		} else {
		 temperary_pos = parent.rotateLeft();
		}
	   }
	  }
	 }

//	public boolean delete(E key){
//
//		Node<E> delete_node = find_1(root,key);
//		Node<E> parents = null;
//		int direction = 0;
////		System.out.println(delete_node.data.toString());
//		while(delete_node.left!=null && delete_node.right!=null){
//			if (delete_node.left == null){
//				parents = delete_node;
//				delete_node = parents.right;
//			}else if(delete_node.right == null){
//				parents = delete_node;
//				delete_node = delete_node.left;
//				direction = -1;
//			}else{
//				if(delete_node.left.priority > delete_node.right.priority){
//					delete_node = delete_node.rotateRight();
//					parents = delete_node;
//					delete_node = parents.right;
//				}
//				if(delete_node.left.priority < delete_node.right.priority){
//					delete_node = delete_node.rotateLeft();
//					parents = delete_node;
//					delete_node = parents.left;
//				}
//			}
//		}
//		if (direction == 1){
//			parents.right = null;
//		}else{
//			parents.left = null;
//		}
//		return true;
//	}

	public boolean delete(E key )
	{
		if(root == null){
			throw new NumberFormatException("There is not a existing tree");
		}
		if(key == null){
			throw new NumberFormatException("key is null");
		}
		if(!find(key)){
			return false;
		}
		root=delete(root,key);
		return deleteReturn;
	}

	private Node<E> delete(Node<E> localRoot, E key)
	{
		int Comp_result = key.toString().compareTo(localRoot.data.toString());
//		key < localroot, localroot = localroot.left
		if(Comp_result<0)
		{
			localRoot.left=delete(localRoot.left, key);
			return localRoot;
		}
		else if(Comp_result>0)//key > localroot,localroot = localroot.right
		{
			localRoot.right=delete(localRoot.right,key);
			return localRoot;
		}
		else              //key == localroot
		{
//			its on the leaf
			if(localRoot.left==null && localRoot.right==null)
			{
				deleteReturn=true;
				return null;
			}
			else
			{
				//if there is no left child
				if(localRoot.left==null)
				{
					localRoot=localRoot.rotateLeft();
					delete(localRoot,key);
					return localRoot;
				}
				//if there is no right child
				else if(localRoot.right==null)
				{
					localRoot=localRoot.rotateRight();
					delete(localRoot,key);
					return localRoot;
				}

//				if two children all exsits, then compare their priority and rotate and process delete recursion then
				else
				{
//					left child
					if(localRoot.left.priority>localRoot.right.priority)
					{
						Node<E> temp=localRoot.rotateRight();
						delete(temp,key);
						return temp;
					}
					else
					{
						Node<E> temp=localRoot.rotateLeft();
						delete(temp,key);
						return temp;
					}
				}
			}
		}
	}

	public boolean find(E key) {
		return find(root, key);
	}

	private boolean find(Node<E> node, E key) {
		if (node == null) {
			return false;
		}
		int comp_result = key.toString().compareTo(node.data.toString());
		if (comp_result == 0) {
			return true;
		} else if (comp_result < 0) {
			return find(node.left, key);
		} else {
			return find(node.right, key);
		}
	}
//	this is a find method for 'delete' operation with a Node as the return
	private Node find_1(Node<E> node, E key) {
		if (node == null) {
			return node;
		}
		int comp_result = key.toString().compareTo(node.data.toString());
		if (comp_result == 0) {
			return node;
		} else if (comp_result < 0) {
			return find_1(node.left, key);
		} else {
			return find_1(node.right, key);
		}
	}

//	I use this preordertraverse method from Prof's lecture video
	public String toString() {
		StringBuilder tree_str = new StringBuilder();
		preOderTraverse(root,1,tree_str);
		return tree_str.toString();
	}

	private void preOderTraverse(Node<E> node,int depth,StringBuilder tree_str){
		for(int i = 1; i < depth; i++){
			tree_str.append(" ");
		}
		if(node == null){
			tree_str.append("null \n");
		}else {
			tree_str.append("(key = "+node.data.toString()+",priority = "+node.priority+")");
			tree_str.append("\n");
			preOderTraverse(node.left,depth+1,tree_str);
			preOderTraverse(node.right,depth+1,tree_str);
		}
	}
}
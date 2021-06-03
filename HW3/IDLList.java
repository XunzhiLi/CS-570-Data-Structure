/*Author :Xunzhi Li
 * Id : 10457500
 * Homework3
 */

package hm3;

import java.util.ArrayList;

public class IDLList<E> {

	private class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;

		private Node(E elem) {
			this.data = elem;
			next = null;
			prev = null;
		}

		private Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}

	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;

	public IDLList() {
		head = new Node(null);
		tail = new Node(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
		indices = new ArrayList<>();
	}

	// a method to evaluate whether linkedlist is empty
	public boolean isempty() {
		if (head.next == tail) {
			return true;
		} else {
			return false;
		}
	}

	// add node in certain position with a data
	public boolean add(int index, E elem) {
		if (index < 0 || elem == null || index > size) {
			return false;
		} else if (index == 0) {
			//add from the front
			return add(elem);
		} else if (index == size) {
			//add in the back 
			return append(elem);
		} else {
			//add in the middle position
			Node<E> targetnode = getnode(index);
			Node<E> new_nd = new Node<E>(elem, targetnode.prev, targetnode);
			targetnode.prev.next = new_nd;
			targetnode.prev = new_nd;
			size++;
			indices.add(index, new_nd);
			return true;
		}

	}

	// add a node in the first
	public boolean add(E elem) {
		if (isempty() == false) {
			Node<E> nd = new Node<E>(elem, head, head.next);
			head.next.prev = nd;
			head.next = nd;
			size++;
			indices.add(0, nd);
			return true;

		} else {
			Node<E> nd = new Node<E>(elem, head, tail);
			head.next = nd;
			tail.prev = nd;
			size++;
			indices.add(0, nd);
			return true;
		}
	}

//	add a node in the last
	public boolean append(E elem) {
		if (isempty() == false) {
			Node<E> nd = new Node<E>(elem, tail.prev, tail);
			tail.prev.next = nd;
			tail.prev = nd;
			size++;
			indices.add(nd);
			return true;

		} else {
			Node<E> nd = new Node<E>(elem, head, tail);
			head.next = nd;
			tail.prev = nd;
			size++;
			indices.add(nd);
			return true;
		}
	}

	public E getHead() {
		if (isempty()) {
			throw new IllegalArgumentException("You cannot get the object at the head because the linkelist is empty.");
		}
		return head.next.data;
	}

	public E getLast() {
		if (isempty()) {
			throw new IllegalArgumentException("You cannot get the object at the tail because the linkelist is empty.");
		}
		return tail.prev.data;
	}

// get the data in a node of a certain index
	public E get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		} else {
			Node<E> f_node = head.next;
			for (int i = 0; i < index; i++) {
				f_node = f_node.next;
			}
			return f_node.data;
		}
	}

	public int size() {
		return size;
	}

// a assistant operation helping to get a node in a position and return.mostly used in add operations.
	public Node<E> getnode(int index) {
		Node<E> f_node = head;
		int i = -1;
		while (f_node.next != null) {
			if (i == index) {
				return f_node;
			} else {
				f_node = f_node.next;
				i++;
			}
		}
		if (i == size - 1) {
			return f_node;
		}
		return null;
	}

// remove the first node
	public E remove() {
		if (isempty()) {
			throw new IllegalArgumentException("You cannot remove the first node because the linkedlist is empty.");
		}
		return removeAt(0);
	}

//	remove the last node
	public E removeLast() {
		if (isempty()) {
			throw new IllegalArgumentException("You cannot remove the last node because the linkedlist is empty.");
		}
		return removeAt(size - 1);
	}

//  remove a certain node with index
	public E removeAt(int index) {
		if (isempty()) {
			throw new IllegalArgumentException("You cannot remove any node because the linkedlist is empty.");
		} else if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		} else {
			Node<E> remove_node = indices.get(index);
			E a = remove_node.data;
			remove_node.prev.next = remove_node.next;
			remove_node.next.prev = remove_node.prev;
			remove_node = null;
			size--;
			indices.remove(index);
			return a;
		}
	}

//	remove a certain node with data
	public boolean remove(E elem) {
		if (isempty()) {
			throw new IllegalArgumentException("You cannot remove the the node  because the linkedlist is empty.");
		} else if (elem == "") {
			throw new IllegalArgumentException("input cannot be null");
		} else {
			for (int i = 0; i < indices.size(); i++) {
				if (indices.get(i).data == elem) {
					//find this node with the data ,so run removeAt(i) operation.size will decrease in its process.
					removeAt(i);
					return true;
				}
			}
		}
		return false;
	}
	public String toString(){
        StringBuilder re_list = new StringBuilder();
        for(int i=0;i<size;i++)
        {
            if(i==size-1)
            	re_list.append(indices.get(i).data);
            else
            	re_list.append(indices.get(i).data+"<->");
        }
        return re_list.toString();
    }

}

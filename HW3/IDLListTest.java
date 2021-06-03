/*Author :Xunzhi Li
 * Id : 10457500
 * Homework3
 */

package hm3;

public class IDLListTest {
	public static void main(String[] args) {
		IDLList<String> l = new IDLList<String>();
		
//		l.removeAt(1);  error case, cannot remove a node in a empty linkedlist.
//		l.remove();   error case, cannot remove a node in a empty linkedlist.
//		l.removeLast(); error case,You cannot remove the last node because the linkedlist is empty.
//		l.remove("G"); error case ,You cannot remove the the node  because the linkelist is empty.
//		l.getHead(); error case , You cannot get the object at the head because the linkelist is empty.
//		l.getLast(); error case,  You cannot get the object at the tail because the linkelist is empty.
		
		//if add a node successfully,return true
		System.out.println("add a node successfully \t"+ l.add("A"));
		l.add(1,"H");
		l.append("B");
		System.out.println("failed to add a node because its out of bounds \t"+ l.add(4,"D"));
		System.out.println("failed to add a node because its out of bounds (negative index)\t"+l.add(-1, "L"));
		l.add("E");
		l.append("D");
		l.add(0,"F");
		l.add(5,"G");
		l.add(3,"H");
		System.out.println("now the linkedlist is :"+l.toString());
		System.out.println("now the linkedlist size is :"+ l.size());
		System.out.println("-----------------------------");
		

		System.out.println("remove the first node by using removeAt method \t"+l.removeAt(0));
		System.out.println("remove the index of 4 nodex \t"+l.removeAt(4));
		System.out.println("remove the last node by using removeAt method \t"+l.removeAt(5));
		System.out.println("remove the first node \t"+l.remove());
		System.out.println("remove the last node \t"+    l.removeLast());
		
//		System.out.println(l.removeAt(4));    error case , index out of bounds
//		System.out.println(l.removeAt(-1));   error case, index out of bounds(can't be negative)
		System.out.println("now the linkedlist is :"+l.toString());
		System.out.println("now the linkedlist size is :"+ l.size());
		System.out.println("-----------------------------");
		
		System.out.println(l.getHead());
		System.out.println(l.getLast());
		System.out.println(l.size());
		System.out.println(l.get(0));
		System.out.println(l.get(1));
		System.out.println(l.get(2));
		
//		System.out.println(l.get(3)); error case ,  index out of bounds
//		System.out.println(l.get(-1));error case ,  index out of bounds
		
		System.out.println("removes the first occurrence of H \t"+ l.remove("H"));
		System.out.println("didn't find 'Q' in this list,so return false \t"+l.remove("Q"));
//		System.out.println(l.remove("")); error case ,input can't be null
		System.out.println("-----------------------------");
		System.out.println("now the linkedlist is :"+l.toString());
		System.out.println(l.get(0));
		System.out.println(l.get(1));


		
		
		
		

	}
}
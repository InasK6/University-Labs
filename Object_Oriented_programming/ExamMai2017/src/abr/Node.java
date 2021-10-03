package abr;

import java.io.PrintStream;

public class Node implements INode2 {
	private String value;
	private Node left=null;
	private Node right=null;
	
	public Node(String s) {
		value =s;
	}
	
	
	public Node(String value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}


	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public INode getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public INode getRight() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		if(s.contentEquals(value)) {
			return true;
		}
		if(s.compareTo(value)<0) {
			if(left==null) {
				return false;
			}
			return left.contains(s);
		}
		else {
			if(right==null) {
				return false;
			}
			return right.contains(s);
		}
		
		
	}

	@Override
	public boolean add(String s) {
		// TODO Auto-generated method stub
		
		if(s.compareTo(value)<0) {
			if(left==null) {
				left=new Node(s);
				return true;
			}
			return left.add(s);
			
		}
		else {
			if(right==null) {
				right=new Node(s);
				return true;
			}
			return right.add(s);
			
		}
	

	}


	@Override
	public void print(PrintStream pw) {
		// TODO Auto-generated method stub

		if(left!=null) {
			left.print(pw);
			
		}
		pw.append(value+" ");
		
		if(right!=null) {
			right.print(pw);
		}
		
	}

}

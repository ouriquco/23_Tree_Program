import java.util.*;
import java.io.*;


public class Tree {
	
	//Reference variable to the root of the tree
	private Node head;
	private ArrayList<Integer> tmpList = new ArrayList<Integer>();
	 
	public Tree() 
	{
		head = null;
	
	}
	
	/* Pre: a numerical value that is not a duplicate of another value in the tree
	 * Post: A boolean value that returns true if the insertion was successful
	 * Purpose: To insert and build a 2-3 balanced tree
	 * */
	
	public boolean insert(int value) 
	{
	
		if(tmpList.contains(value))
			return false;
	
			tmpList.add(value);
		
		
			if (head == null)
			{
				Node leaf = new Node(value);
				head = leaf;
				return true;
			}
			
			
			Node tmp = new Node();
			tmp = head.insertHelper(value);
			
			tmp.organize(value);
				
		return true;

	}


				
	public int size(int x)
	{
		Node tmp = head.insertHelper(x);
		
		if(!(tmp.myData.contains(x)))
			return 0;
		else
			return tmp.num;			
	}
	

		

	
	
private class Node 
{
	
	
	private ArrayList<Integer> myData =  new ArrayList<Integer>(4);
	private ArrayList<Node> myChild = new ArrayList<Node>(4);
	
	
	private int num;

	private Node parent;
	
	private Node ()
	{
		parent = null;
		num = 0;
	}
	
	private Node(int d1)
	{
		num = 1;
		myData.add(0, d1);
		parent = null;
		
	}
	
	private Node(int d1, int d2)
	{
		num = 2;
		myData.add(0,d1);
		myData.add(2,d2);
		parent = null;
		
	
	}

	
	
	private void split()
	{
		
		
		if(isRoot())
		{
			
			for (int i = 0; i <= myData.size()-1; i++)
			{
				myChild.add(new Node(myData.get(i)));
				myChild.get(i).parent = this;
			}
			
			myData.removeAll(myData);
			myData = myChild.get(1).myData;
			myChild.remove(1);
			
		}
		else if (hasThreeKeys())
		{
			
			
			for (int i = 0; i <= myData.size()-1; i++)
			{
				if(i == 1)
				{
					parent.myData.add(myData.get(i));
				}
				else
				{
					parent.myChild.add(new Node(myData.get(i)));
				}
				
				if(parent.myChild.get(i).hasThreeKeys())
					parent.myChild.remove(i);
				
			}
		
			
		
			if(parent.myChild.size() == 4)
			{
				Collections.sort(parent.myData);
				int tmp = parent.num;
				
				Node left = new Node(parent.myData.get(0));
				Node fosterParent = new Node(parent.myData.get(1));
                Node right = new Node(parent.myData.get(2));
                
                
                left.num = tmp/2;
                right.num = tmp/2;
                fosterParent.num = tmp;
               
         
                for(int i = 0; i <= parent.myChild.size()-1; i++)
                {
                	if (i<2)
                	{
                	right.myChild.add((parent.myChild.get(i)));
                	}
                	else
                	left.myChild.add((parent.myChild.get(i)));
                }
                
                
                fosterParent.myChild.add(left);
                fosterParent.myChild.add(right);
                head = fosterParent;
						
					
			}
				
				
			
				
			}
			
		}
		
		
	private boolean organize(int value)
	{
	
			
			if(hasOneKey())
			{
				myData.add(value);
				Collections.sort(myData);
				num = myData.size();
			}	
			else if (hasTwoKeys())
			{
				myData.add(value);
				Collections.sort(myData);
				num = myData.size();
				split();
			}
		
		return true;
	}
	
	
	//Checks if a node has just one data element (for readability)
	private boolean hasOneKey()
	{
		if (myData.size() == 1)
			return true;
		else 
			return false;
		
		
	}
	 private boolean hasTwoKeys()
	 {
		
		if (myData.size() == 2)
			return true;
		else 
			return false;
		
	 }
	 private boolean hasThreeKeys()
	 {
		
		if (myData.size() == 3)
			return true;
		else 
			return false;
		
	 }
	 
	 //Checks if a node is a leaf
	 private boolean isLeaf()
	 {
		 if(myChild.isEmpty())
			 return true;
		 else
			 return false;
	
		 
	 }
	 
	 //Checks if a node is the root of the tree
	 private boolean isRoot()
	 {
		 if (parent == null)
			 return true;
		 else 
			 return false;
	 }
	 
	 private Node insertHelper(int val)
		{
		 	int i;
		 	
			if(this.isLeaf())
			{
				return this;
			}
			else if (this.myData.contains(val))
				return this;
			else 
			{
					this.num = num + 1;
				
				for (i = 0; i < myData.size(); i++)
				{
					if(val > myData.get(i))
						continue;
					else 
						break;
					
				}
			}
		
			return myChild.get(i).insertHelper(val); 
		}		
	 
	 
}

	
}

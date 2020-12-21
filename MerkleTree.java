import java.util.*;
import java.io.IOException;
import java.io.FileWriter;

public class MerkleTree extends BasicTree<Integer> implements Comparable<MerkleTree>{
	private ArrayList<ArrayList<Node <Integer>>> Tree;
	private ArrayList<Integer> Data;
	private int maxsize;

	private int Hash(int key) {
		//Hash value
		//Can be changed depending on the data type of the MerkleTree
		//The current data type used is Integer
		return key%maxsize;
	}

	public MerkleTree() {
		//Default constucter;
		Tree = new ArrayList<ArrayList<Node <Integer>>>();
		Data = new ArrayList<Integer>();
		maxsize = 0;
	}

	public MerkleTree(ArrayList<Integer> lt) {
		//Constuctor
		Tree = new ArrayList<ArrayList<Node<Integer>>>();
		Data = lt;
		maxsize = Data.size();
		initialize(Data);
	}

	private void initialize(ArrayList<Integer> value) {
		//generates merkle tree using ArrayList
		ArrayList<Node<Integer>> temp = new ArrayList<Node<Integer>>();
		for (int i = 0; i < Data.size(); i++) {
			temp.add(new Node<Integer>(Data.get(i)));
		}
		Tree.add(temp);


		ArrayList<Node <Integer>> temp1;
		while(Tree.get(Tree.size()-1).size() != 1) {
			temp1 = new ArrayList<Node<Integer>>();

			if ((temp.size()%2) != 0) {
				temp.add(temp.get(temp.size()-1));
			}


			for (int i = 0; i < temp.size()-1; i=i+2) {
				temp1.add(getparent(temp.get(i),temp.get(i+1)));
			}


			Tree.add(temp1);
			temp = temp1;
		}
		Head = Tree.get(Tree.size()-1).get(0);
		height = Tree.size();

	}

	public void insert_element(int transaction) {
		//add element to transaction list and reinitialize
		Data.add(transaction);
		for (int i = 0; i < Tree.size();i++) {
			Tree.get(i).clear();
		}
		Tree.clear();
		maxsize = Data.size();
		initialize(Data);
	}

	public void delete_element(int position) {
		//delete first element with value equal to transaction
		//and reinitialize
		if ((position >= Data.size())||(position < 0)) {
			System.out.println("Position is out of bounds!! Merkle Tree is unchanged.");
		}
		else {
			Data.remove(position);
			for (int i = 0; i < Tree.size();i++) {
				Tree.get(i).clear();
			}
			Tree.clear();
			maxsize = Data.size();
			initialize(Data);
		}
	}


	public int getDataSize() {
		//returns size of data array
		return Data.size();
	}

	public int getTreeSize() {
		//returns number of leaves in tree
		int total = 0;

		for(int i = 0; i < Tree.size();i++) {
			total = total + Tree.get(i).size();
		}
		return total;
	}


	public void display() {
		//display merkle tree and hash values
		if (Tree.size()==0) {
			return;
		}
		int total = Tree.size();

		for (int i = Tree.size()-1; i >= 0; i--) {
			System.out.print(total-i);
			System.out.print(" ");

			for (int j = 0; j < Tree.get(i).size() ;j++) {
				System.out.print(Tree.get(i).get(j));
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public void display(String my_file) { // change it to where it prints out into report file instead of on the terminal
		//display merkle tree and hash values
		if (Tree.size()==0) {
			return;
		}
		int total = Tree.size();
		try {
			FileWriter myWriter = new FileWriter(my_file, true);
			myWriter.write("\n");
			for (int i = Tree.size()-1; i >= 0; i--) {
				myWriter.write(total-i);
				myWriter.write(" ");

				for (int j = 0; j < Tree.get(i).size() ;j++) {
					myWriter.write(Tree.get(i).get(j).toString()); //error
					myWriter.write(" ");
				}
				myWriter.write("\n");
			}

			myWriter.close();
			//System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public String toString() {
		//returns string for system.out to use
		String temp;
		temp = "Rootvalue: " + Head.getvalue() + "\n" + "Total Number of Nodes: " + getTreeSize() + "\n" + "Height: " + getheight();
		return temp;
	}

	private Node<Integer> getparent(Node<Integer> n1,Node<Integer> n2) {
		//takes two nodes and outputs parent node
		int val1 = n1.getvalue();
		int val2 = n2.getvalue();
		return new Node<Integer>(Hash(val1 + val2), n1, n2);
	}

	public int compareTo(MerkleTree B) {
		//compares merkle trees using head value
		MerkleTree X = (MerkleTree) B;
		return this.Head.getvalue().compareTo(X.Head.getvalue());
	}
}




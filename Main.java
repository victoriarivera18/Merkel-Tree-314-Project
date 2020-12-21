import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;


public class Main {

    public static Scanner fileSelect(String filename){ //select file to gather data from
        Scanner infile = null;
        try{
            infile = new Scanner(new FileReader("./src/" + filename)); //open file
        } catch (FileNotFoundException e) { //error handling
            System.out.println("File not found");
            e.printStackTrace(); // prints error(s)
            System.exit(0); // Exits entire program
        }

        return infile;
    }

    public static void printReport(){
        System.out.println("File name \"report.txt\" is now ready to view!");
        //file is now reading withing directory
    }

    // figure out how to print out to report

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to Data Reader Pro!\nPlease select from the following options:");
        System.out.println("(1) Read data from file \n(2) Add new transaction to existing data file\n(3) Remove a transaction \n(4) Validation of data\n(5) Compare two sets of Data \n(6) Quit");
        int answer = keyboard.nextInt();
        String filename; //infile
        String report_file = "report.txt";
        PrintWriter outfile = null; //outfile

        ArrayList<Integer> list = new ArrayList<Integer>();
        MerkleTree M1 = new MerkleTree();

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        MerkleTree M2 = new MerkleTree();
        int num = 0;

        //create instance of merkle tree
        //define in option 1

        try { // opening our report file
            outfile = new PrintWriter("report.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace(); // prints error(s)
            System.exit(0); // Exits entire program
        }

        while(answer != 6){
            if(answer == 1){
                // initialize tree from file already in folder
                //print out tree structure
                System.out.println("Enter file::");
                filename = keyboard.next();
                Scanner my_file = fileSelect(filename);

                while(my_file.hasNextLine()) {
                    String line = my_file.nextLine();
                    list.add(Integer.parseInt(line));//add each value to merkle tree
                }

                my_file.close(); // close file for reading

                M1 = new MerkleTree(list); // initial merkle tree created
                M1.display(report_file);
                //M1.display_prefix();
                //System.out.print(M1); //toString


            } else if(answer == 2){
                //add new transaction to existing data file
                System.out.println("Enter data to add:: ");
                num = keyboard.nextInt();
                M1.insert_element(num);
                //in report print existing tree
                //add value

                //print new tree
                M1.display(report_file);
                //M1.display_prefix();
                //System.out.print(M1); //toString
            } else if (answer == 3){
                // remove transaction from existing data file
                System.out.println("Enter index of a value to delete:: ");
                num = keyboard.nextInt();
                M1.delete_element(num);
                //in report print existing tree
                //remove value

                //print new tree
                M1.display(report_file);
                //M1.display_prefix();
                //System.out.print(M1); //toString
            } else if (answer == 4){
                // validation a given input(key) or root node
                System.out.println("Enter value of root node you want to validate:: ");
                num = keyboard.nextInt();
                int current_head = M1.getheadvalue();

                //all of this is going in report
                try {
                    FileWriter myWriter = new FileWriter(report_file, true);
                    myWriter.write("\nInput Root::" + num);
                    myWriter.write("\nCurrent Root::" + current_head);

                    if(num == current_head){
                        //no data breach is reported
                        myWriter.write("\nResult:: Data has not been compromised!");
                    } else{
                        //data breach
                        // there is a breach so we need to provide recommendations on wha to do
                        myWriter.write("\nResult:: Data has been compromised!");

                        myWriter.write("\nRecommended Action:: 1) Contact Breached Data companies to help you find out how to improve your security " +
                                "\n2) Update cyber-security protocols and software \n3) Check to see if your other groups of data have been breached");
                    }
                    myWriter.close();
                    //System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                //print head and compare to given value
            } else if(answer == 5){
                System.out.println("Enter file::");
                filename = keyboard.next();
                Scanner my_file = fileSelect(filename);

                while(my_file.hasNextLine()) {
                    String line = my_file.nextLine();
                    list2.add(Integer.parseInt(line));//add each value to merkle tree
                }

                my_file.close(); // close file for reading
                M2 = new MerkleTree(list2); // other merkle tree created

                try {
                    FileWriter myWriter = new FileWriter(report_file, true);
                    myWriter.write("\n^ Merkle Tree to compare to original is above ^");
                    M2.display(report_file);
                    if(M1.compareTo(M2) == 0){
                        myWriter.write("\nMerkle Trees root nodes are equal.");
                    } else{
                        myWriter.write("\nMerkle Trees root nodes are not equal.");
                    }
                    myWriter.close();
                    //System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                //M1.display_prefix();
                //System.out.print(M1); //toString
            }else{
                //answer != 1 2 3 4 5
                System.out.println("Invalid answer! Please try again!");
            }
            System.out.println("(1) Read data from file \n(2) Add new transaction to existing data file\n(3) Remove a transaction \n(4) Validation of data\n(5) Compare two sets of Data \n(6) Quit");
            answer = keyboard.nextInt();
        }

        printReport();
        // read report function


    }
}

//tree is parent class (abstract)
//merkel tree inherits basic functions from tree, create its own

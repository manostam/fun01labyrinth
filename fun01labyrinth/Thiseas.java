// living on a praaaayer !

import java.io.File;  					// Import the File class
import java.io.FileNotFoundException;  	// Import this class to handle errors
import java.util.Scanner; 				// Import the Scanner class to read text files
import java.util.Arrays;				// Importing Arrays to read and store the text file in arrays

public class Thiseas {
	public static String fileAddressOfMain;
    public static int n = 0;										// the lines that will be given in the 1st line
    public static int m = 0;										// the rows that will be given in the 1st line
    public static int x = 0;										// Entrance line coordinate that will be given
    public static int y = 0;										// Entrance row coordinate that will be given
    public static String [][] theLabyrinth;				     		// array that will portray the labyrinth inside the program and will be modified (its string elements) accordingly to find the path to the exit 
    public static String [][] labyrinth_Array;						// the array used in readAndStoreFile method, which stores the labyrinth and returns it (to the above) when the corresponding method is used
    public static boolean exitNotFound = true;
    public static StringStackImpl pathStack;

    // main !
	public static void main (String [] args) {		
		fileAddressOfMain = args [0];				// the address of the labyrinth file in our system
		for(int i = 1; i < args.length; i++) {      // passing it from string [] to string for easier use
            fileAddressOfMain = fileAddressOfMain + args [i];   
        }
        System.out.println("About the labyrinth: \n");

        // reading, printing, storing the context of the file
        theLabyrinth = readAndStoreFile(fileAddressOfMain);

    	System.out.println("\nBeginning from E(" + x + "," + y + ") your path towards the exit as follows: \n");
    	pathStack = new StringStackImpl();
    	int whileCounter = 0;
    	boolean goingRightIsOk = true;								// booleans to avoid going out of bounds if E is at the borders (and we do not want to search "outside" the labyrinth)
    	boolean goingDownIsOk = true;
    	boolean goingLeftIsOk = true;
    	boolean goingUpIsOk = true;

    	// finding the path out of the labyrinth
    	int Ex = x;							// in order to check at the end that I did not just go back to the Entrance (if it is at the borders)
    	int Ey = y;

    	while (exitNotFound) {
    		// if we are at E and its at the borders, limit its access outside the borders of the labyrinth (depending which side of the border is E at)
    		if (x == Ex && y == Ey) {
    			if (y == m-1) {
    				goingRightIsOk = false;
    			}
    			else {goingRightIsOk = true;}
    			if (x == n-1) {
    				goingDownIsOk = false;
    			}
    			else {goingDownIsOk = true;}
		    	if (y == 0) {
    				goingLeftIsOk = false;
    			}
    			else {goingLeftIsOk = true;}
    			if (x == 0) {
    				goingUpIsOk = false;
    			}
    			else {goingUpIsOk = true;}
    		}
    		// if we are not at E, allow the access right, down, left, up
    		else {
    			goingRightIsOk = true;
    			goingDownIsOk = true;
	   			goingLeftIsOk = true;
    			goingUpIsOk = true;
    		}

			if (goingRightIsOk && theLabyrinth[x][y+1].equals("0")) {		// checking if you can move to the right
    			++y;														// moving to the right
    			System.out.println("x:" + x + " y:" + y);					// typing where we moved, where we are now
    			theLabyrinth[x][y] = "CFL";									// making the current node at our array CFL (so that we know towards where we should move during possible backtracking)
    			pathStack.push("CFL");										// Came From Left (passing the current node at our stack about finding the path)
    		}
    		else if (goingDownIsOk && theLabyrinth[x+1][y].equals("0")) {	// checking if you can move down
    			++x;
    			System.out.println("x:" + x + " y:" + y);
    			theLabyrinth[x][y] = "CFU";
    			pathStack.push("CFU");										// Came From Up
    		}
    		else if (goingLeftIsOk && theLabyrinth[x][y-1].equals("0")) {	// checking if you can move to the left
    			--y;
    			System.out.println("x:" + x + " y:" + y);
    			theLabyrinth[x][y] = "CFR";
    			pathStack.push("CFR");										// Came From Right
    		}
    		else if (goingUpIsOk && theLabyrinth[x-1][y].equals("0")) {		// checking if you can move up
    			--x;
    			System.out.println("x:" + x + " y:" + y);
    			theLabyrinth[x][y] = "CFD";
    			pathStack.push("CFD");										// Came From Down
    		}
    		// searching if I'm blocked and NOT at the borders
    		else if ( (goingRightIsOk && goingDownIsOk && goingLeftIsOk && goingUpIsOk) && ( (theLabyrinth[x][y+1].equals("1")) && (theLabyrinth[x+1][y].equals("1")) && (theLabyrinth[x][y-1].equals("1")) && (theLabyrinth[x-1][y].equals("1")))) {
    				System.out.println("You are trapped, no exit from the labyrinth ! ");
    				break;
    			}
    		// searching if I'm blocked at the entrance, on the right, down, left or up border
    		else if ( (!goingRightIsOk && theLabyrinth[x][y-1].equals("1")) || (!goingDownIsOk && theLabyrinth[x-1][y].equals("1")) || (!goingLeftIsOk && theLabyrinth[x][y+1].equals("1")) || (!goingUpIsOk && theLabyrinth[x+1][y].equals("1"))) {
    			System.out.println("You returned at the entrance, no exit from the labyrinth ! ");
    			break;
    		}
    		else {											// backtracking
    			if (pathStack.head.keimeno == "CFL") {
    				theLabyrinth[x][y] = "1";
    				--y;									// to return, I move to the left
    			}					
    			else if (pathStack.head.keimeno == "CFU") {
    				theLabyrinth[x][y] = "1";
    				--x;									// to return, I move up
    			}				
    			else if (pathStack.head.keimeno == "CFR") {
    				theLabyrinth[x][y] = "1";
    				++y;									// to return, I move to the right
    			}				
    			else if (pathStack.head.keimeno == "CFD") {
    				theLabyrinth[x][y] = "1";
    				++x;									// to return, I move down
    			}				
    			else {
    				System.out.println(" The text here at " + x + " " + y + " is not what should be, please check it");
    				break;
    			}
    			System.out.println("x:" + x + " y:" + y + " (backtracking)");
    			pathStack.pop();							// ... go back to the previous one, to see if there is another open passage ("0")
    		}
    		if ( ((x == 0) || (y == 0) || (x == n-1) || (y == m-1))  && (!(x == Ex && y ==Ey)) ) { //the 2nd comparison so that if I return to E I do not count it as an exit
    			System.out.println("You ve reached the borders, you ve found an exit");
    			System.out.println("Exit is at x:" + x + " and y:" + y);
    			exitNotFound = false;
    			theLabyrinth[Ex][Ey] = " E ";
    			theLabyrinth[x][y] = "ESC";
    		}
    		++whileCounter;
    	}
    	System.out.println("\n the labyrinth in the end: ");
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
    			if (theLabyrinth[i][j].equals("0") || theLabyrinth[i][j].equals("1")) {
    				System.out.print("  " + theLabyrinth[i][j] + "  ");
    			}
    			else{
    				System.out.print(" " + theLabyrinth[i][j] + " ");
    			}
    		}
    		System.out.print("\n");
    	}
    	System.out.println("CFR = Came From Right, CFD = Came From Down, CFL = Came From Left, CFU = Came From Up");
    	System.out.println("CFR, CFD, CFL, CFU show the orientation of a supposed player in the labyrinth during the finding of the exit");
    	System.out.println("\nThe stack \"pathStack\" showing the nodes so far from the escape back to the entrance is as follows: ");
    	pathStack.printStack(System.out);
	}



	// reads the File labyrinthFile, dimensions, coordinates of Entrance, stores the rest of the labyrinth in a String array which is then returned
	public static String[][] readAndStoreFile(String stringAddress){
	   	try {
        	File labyrinthFile = new File(stringAddress);	// saving the system file in a local variable of our program
    		Scanner myReader = new Scanner(labyrinthFile);  // reading the File labyrinthFile
      		int loop_counter = 0;
      		int counterOfLines = 0;
      		// loop for checking if there is a new line for reading
      		while (myReader.hasNextLine()) {
      			// at the 1st line, reading lines and rows
        		if (loop_counter == 0) {					
        			try {
        				n = myReader.nextInt();
        				m = myReader.nextInt();
        				System.out.println("size of lines: " + n);
        				System.out.println("size of rows:  " + m);
        			}
        			catch (Exception e) {
      					System.out.println("Problem with the size input of the labyrinth");
    				}
    			}
    			// at the 2nd line, reading the coordinates of Entrance	
        		else if (loop_counter == 1) {				
        			try {
        				x = myReader.nextInt();
        				y = myReader.nextInt();
        				System.out.println("line of Entrance: " + x);
        				System.out.println("row of Entrance: " + y +"\n");
        				String lineOfData = myReader.nextLine();		// reads the details of the 2nd line, not interested, just passing the line to go later at the 3rd line where our labyrinth begins
        				labyrinth_Array = new String [n][m];			// we initialize it here, to use it at the next loop
        			}
        			catch (Exception e) {
      					System.out.println("Problem with the Entrance coordinates input of the labyrinth");
    				}
        		}
        		// reads the whole labyrinth   
        		else {												     			
        			String lineOfData = myReader.nextLine();	// reads the whole line (starting from the 3rd line, 1st for the labyrinth)		
        			String anArray[] = lineOfData.split(" ");	// splits in different strings the whole line
        			// checking if the rows (in each line) are OK
        			if (anArray.length != m) {
        				System.out.println(" Given and actual rows do not match up, check the text file and the rows ");
        				System.exit(0);
        			}
        			for (int j = 0; j < m; j++) {
        				labyrinth_Array[counterOfLines][j] = anArray[j];	// passing the elements of the each line at the array of the labyrinth
        				System.out.print(labyrinth_Array[counterOfLines][j]);
        				//CHECKS if E is at the given coordinates, if it is at the given coordinates it zeroes it
        				if ((counterOfLines == x) && (j == y)) {				
        					String s = labyrinth_Array[x][y];
        					if (! (s.equals("E") || s.equals("Ε")) ) {
        						System.out.println("\nThe given coordinates of the Entrance do not illustrate an E marked entrance at the labyrinth, check coordinates or E in the text file");
        						System.exit(0);
        					}
        					labyrinth_Array[x][y] = "0";			// putting 0 to the Entrance, so that it can be marked as not explored later during the finding of the exit
        				}
        			}
        			++counterOfLines;								// counts the actual lines
        			System.out.print("\n");
        		}
        		++loop_counter;
        	}
        	// checking if the lines are OK
        	if (counterOfLines != n) {
        		System.out.println(" Given and actual lines do not match up, check the text file and the lines ");
        		System.exit(0);
        	}
        }
      	catch (Exception e) {
     		System.out.println("An error occurred during the finding of the file");
    	}

    	return labyrinth_Array;
	}

}

package cmsc256;  // do not remove or comment out this statement

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *  CMSC 256
 *  Project 1
 *  Vinod, Kyle
 */
// place any import statements here
public class Project1 {
    public static void main(String[] args) {
        //Activated this to allow static methods to work with non static methods
        Project1 p = new Project1();
        //Scanner implemented
        Scanner input = new Scanner(System.in);
        String checker = p.checkArgs(args);
        //Getting the inputfile
        File inputFile = null;
        try {
            inputFile = p.getFile(checker);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Getting info from inputfile and putting it into an array
        String[][] listArray = new String[500][3];
        int fileLine = listArray.length;
        try {
            listArray = p.readFile(inputFile, fileLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Getting tallest
        int tall = p.findTallest(listArray);
        //Getting lighest record
        String[] light = p.findLightestRecord(listArray);
        //Asking for lowerbound and upperbound
        System.out.println("Enter a lowerbound");
        int young = input.nextInt();
        System.out.println("Enter an upperbound");
        int old = input.nextInt();
        //Getting average
        double avg = p.findAvgHeightByAgeRange(listArray, young, old);
    }

    /**
     * Gets the file name from command line argument;
     * If parameter is empty, call promptForFileName() method
     *
     * @param argv String array from command line argument
     * @return the name of the data file
     */
    public String checkArgs(String[] argv) {
        if (argv.length >= 1) {              // check for argument to main method
            return argv[0];                  // set file name to argument
        }
        else                 			// if no argument, prompt user to enter it
            return promptForFileName();
    }

    /**
     * Prompt user to enter a file name
     *
     * @return user entered file name
     */
    public String promptForFileName() {
            @SuppressWarnings("resource")
            Scanner in = new Scanner(System.in);
            System.out.print("Please enter the file name:");
            return in.next();
    }

    /**
     * Retrieve file with the given file name.
     * Prompts user if file cannot be opened or does not exist.
     *
     * @param fileName The name of the data file
     * @return File object
     * @throws java.io.FileNotFoundException
     */
    public File getFile(String fileName) throws FileNotFoundException {
        File myFile = new File(fileName);

        while (!myFile.exists()) {
            System.out.println("File not found.");
            fileName = promptForFileName();
            myFile = new File(fileName);
        }
        return myFile;
    }

    /**
     * Reads the comma delimited file to extract the number data elements
     * provided in the second argument.
     *
     * @param file       The File object
     * @param numRecords The number of values to read from the input file
     * @return 2D array of data from the File
     * @throws IOException if any lines are missing data
     */
    public String[][] readFile(File file, int numRecords) throws IOException {
        Scanner readFile = new Scanner(file);
        String[][] inData = new String[numRecords][3];
        String line = readFile.nextLine();    // get the header line from file
        int row = 0;

        while (readFile.hasNextLine() && row < numRecords) {
            line = readFile.nextLine().trim();    // get a line of data from file
            Scanner lineParser = new Scanner(line);
            lineParser.useDelimiter(",");
            for (int col = 0; col < 3; col++) {
                if (lineParser.hasNext())
                    inData[row][col] = lineParser.next().trim();
                else {
                    System.out.println("Error reading the file.");
                    lineParser.close();
                    throw new IOException("Error reading the data in the file.");
                }
            }
            row++;
            lineParser.close();
        }
        readFile.close();
        return inData;
    }

    /**
     * Determines the tallest height in the data set
     * Height is the second field in each row
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Maximum height value
     */
    public int findTallest(String[][] db) {
        int large = 0;
        int num = 0;
        //Determines if the number in the array is the biggest and sets it to large variable
        for (int i = 0; i < db.length; i++) {
            num = Integer.parseInt(db[i][1]);
            if (num > large) {
                large = num;
            }
        }
        return large;
    }

    /**
     * Returns the values in the record that have the lowest weight
     *
     * @param db 2D array of data containing [age] [height] [weight]
     * @return Smallest weight value
     */
    public String[] findLightestRecord(String[][] db) {
        int num = 0;
        int small = 100000000;
        int boop = 0;
        //Gets the smallest weight number and puts it in small variable
        for (int i = 0; i < db.length; i++) {
            num = Integer.parseInt(db[i][2]);
            if (num < small) {
                small = num;
            }
        }
        //Gets the first variable in weight that matches small and puts all info in that row into array
        String[] low = new String[3];
        for (int i = 0; i < db.length; i++) {
            num = Integer.parseInt(db[i][2]);
            if (num == small && boop == 0) {
                for (int j = 0; j < 3; j++) {
                    low[j] = db[i][j];
                }
                boop++;
            }
        }
        return low;
    }

    /**
     * Calculates the average height for all records with the given age range.
     *
     * @param db         2D array of data containing [age] [height] [weight]
     * @param lowerBound youngest age to include in the average
     * @param upperBound oldest age to include in the average
     * @return The average height for the given range or 0 if no
     * records match the filter criteria
     */
    public double findAvgHeightByAgeRange(String[][] db, int lowerBound, int
            upperBound) {
        double avg = 0;
        int young = 1000000;
        int old = 0;
        int num = 0;
        double total = 0;
        double boop = 0;
        //Determines if the numbers match the criteria and adds to total and boop
        for (int i = 0; i < db.length; i++) {
            num = Integer.parseInt(db[i][0]);
            if (num >= lowerBound && num <= upperBound) {
                total += Integer.parseInt(db[i][1]);
                if (num < young) {
                    young = num;
                } else if (num > old) {
                    old = num;
                }
                boop++;
            }
        }
        //If total is greater than 0, it will calculate avg and return it. Else it will return 0
        if (total > 0) {
            //Divides total by boop to get avg variable
            avg = total / boop;
        }
        else {
            avg = 0;
        }
        return avg;
    }
}

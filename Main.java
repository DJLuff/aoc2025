
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int day = 2;
        String filename = "input" + day + ".txt";
        ArrayList<String> puzzleInput = readFile(filename);
        if (day == 1) {
            System.out.println(dayOne(puzzleInput));
        } else if (day == 2) {
            System.out.println(dayTwo(puzzleInput));
        }

    }

    public static ArrayList<String> readFile(String filename) throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<String>();
        File myObj = new File(filename);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String instruction = myReader.nextLine();
            data.add(instruction);
        }
        myReader.close();
        return data;
    }

    public static int dayOne(ArrayList<String> instructions) {
        int password = 0;
        int pos = 50;
        //take the list of instructions
        //interpret the instruction
        for (String instruction : instructions) {
            char direction = instruction.charAt(0);
            Integer movement = Integer.parseInt(instruction.substring(1, instruction.length()));
            //make the rotation
            if (direction == 'L') {
                movement = -1 * movement;
            }
            int previousPos = pos;
            pos = pos + movement;
            System.out.println(instruction);
            System.out.println(pos);
            int passes = 0;
            while (pos > 99 || pos < 0) {
                if (pos > 99) {
                    pos -= 100;
                    if (pos != 0) {
                        passes++;
                    }
                }
                if (pos < 0) {
                    passes++;
                    pos += 100;
                }
            }
            if (pos == 0) {
                passes++;
            }
            if (previousPos == 0 && passes != 0 && direction == 'L') {
                passes--;
            }
            password += passes;

        }
        //check if I pass 0 and increment final number
        //return number
        System.out.println(password);
        return password;
    }

    public static long dayTwo(ArrayList<String> puzzleInput) {
        String inputValue = puzzleInput.get(0);
        String[] inputSplit = inputValue.split(",");
        long totalValue = 0;
        for (String instruction : inputSplit) {
            //Interpret list of values to check
            String[] bounds = instruction.split("-");
            String lowerBound = bounds[0];
            String upperBound = bounds[1];
            for (long testValue = Long.parseLong(lowerBound); testValue < Long.parseLong(upperBound) + 1; testValue++) {
				//System.out.println(testValue);
                if (!evaluateGoodNumber(String.valueOf(testValue))) {
					//Add the digit to a running total
                    totalValue += testValue;
                }
            }
        }
		//Return the running total
        return totalValue;
    }
    //Evaluate each value in the list

    private static boolean evaluateGoodNumber(String testValueStr) {
        int testLength = testValueStr.length();
		//Needs to have an even number of digits
        if (testLength % 2 != 0) {
            return true;
        }
        long firstHalf = Long.parseLong(testValueStr.substring(0, testLength / 2));
        long secondHalf = Long.parseLong(testValueStr.substring(testLength / 2, testLength));
		//Needs to have the first half be identical to the second half
        return firstHalf != secondHalf;
    }

    //To be excluded:
    
    
    
    
}

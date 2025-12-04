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
		if (day == 1){
			System.out.println(dayOne(puzzleInput));
		} else if (day == 2){
			System.out.println(dayTwo(puzzleInput[0]));
		}

  	}

	public static ArrayList<String> readFile(String filename) throws FileNotFoundException{
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
				if (pos > 99){
					pos -= 100;
					if (pos != 0){
						passes ++;
					}
				}
				if (pos < 0){
					passes ++;
					pos += 100;
				}
			}
			if (pos == 0){
				passes ++;
			}
			if (previousPos == 0 && passes != 0 && direction == 'L'){
				passes --;
			}
			password += passes;
			
		}
    	//check if I pass 0 and increment final number
    	//return number
		System.out.println(password);
    	return password;
  	}

	public static int dayTwo(ArrayList<String> puzzleInput) {
		List<String> instructions = Arrays.asList(puzzleInput.split(","));
		int totalValue = 0;
		for (String instruction : instructions) {
			//Interpret list of values to check
			String[] bounds = instruction.split("-");
			String lowerBound = bounds[0];
			String upperBound = bounds[1];
			for (int testValue = Integer.parseInt(lowerBound); testValue < Integer.parseInt(upperBound) + 1; testValue++){
				if (!evaluateNumber(String.valueOf(testValue))) {
					totalValue += testValue;
				}
			}
		}
		return totalValue;
	}
	//Evaluate each value in the list
	private static boolean evaluateNumber(String testValueStr) {
		int testLength = testValueStr.length();
		if (testLength %2 != 0) {
			return true;
		}
		int firstHalf = Integer.parseInt(testValueStr.substring(0, testLength/2));
		int secondHalf = Integer.parseInt(testValueStr.substring(testLength/2, testLength));
		if (firstHalf == secondHalf) {
			return false;
		}
		return true;
	}


	
		//To be excluded:
		//Needs to have an even number of digits
		//Needs to have the first half be identical to the second half
	//Add the digit to a running total
	//Return the running total


}
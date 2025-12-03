import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> instructions = readFile();
		System.out.println(solveLock(instructions));
  	}

	public static ArrayList<String> readFile() throws FileNotFoundException{
		ArrayList<String> data = new ArrayList<String>();
		File myObj = new File("input.txt");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String instruction = myReader.nextLine();
			data.add(instruction);
		}
		myReader.close();
		return data;
    }

	public static int solveLock(ArrayList<String> instructions) {
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
      		pos = pos + movement;
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
			password += passes;
			
		}
    	//check if I pass 0 and increment final number
    	//return number
		System.out.println(password);
    	return password;
  	}
}
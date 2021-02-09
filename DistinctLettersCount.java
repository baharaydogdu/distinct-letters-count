import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DistinctLettersCount {
	
	static final int MIN_SIZE = 1;
	static final int MAX_SIZE = 30000;

public static void main( String[] args ) throws Exception {
    	
    	List<String> inputList;
    	List<String> outputList;
    	    	
    	//input argument null check
    	if(args.length == 0) {
    		System.out.println("You need to specify a path for input file!");
    		System.exit(0);
    	}    		  	
    	
    	//get input list from file
    	inputList = getInputFromFile(args[0]);	
    	
    	//validate input
    	validateInput(inputList);
    	
    	//process input
    	outputList = returnOutputForDistinctLetterCount(inputList);
    	
     	System.out.println(formatPrint(outputList));
    	
    }


private static String formatPrint(List<String> outputList) {
	StringBuilder result = new StringBuilder();
	
	for(int i = 1; i <= outputList.size(); i++) {
		result.append(i + "- " + outputList.get(i-1) + "\n");
	}	
	return result.toString();
}


public static List<String> getInputFromFile(String path) {
	List<String> inputList = new ArrayList<>();
	
	try(Stream<String> lines = Files.lines(Paths.get(path))) { 		
		inputList = lines.collect(Collectors.toList());  //collect data as a list
	} catch(Exception e) {
		System.out.println("Error occured while getting input from file!\nERROR: " + e);
	}
		
	return inputList;
}

private static void validateInput(List<String> inputList) throws Exception {
	if(inputList.isEmpty())
		throw new Exception(ErrorCode.EMPTY_FILE_ERROR.getMessage());
	
	for(String input : inputList) {
		if(input.length() < MIN_SIZE)
			throw new Exception(ErrorCode.MIN_CHAR_SIZE_ERROR.getMessage());  //check min input size
		else if(input.length() > MAX_SIZE)
			throw new Exception(ErrorCode.MAX_CHAR_SIZE_ERROR.getMessage());  //check max input size	
		else if(hasUpperCaseLetter(input))
			throw new Exception(ErrorCode.UPPER_CASE_ERROR.getMessage());  //check max input size
	}	
}


private static boolean hasUpperCaseLetter(String str) {
	long uppers = str.chars()
					.filter(Character::isUpperCase)
					.count();
	return (uppers != 0) ? true : false;	
}



private static List<String> returnOutputForDistinctLetterCount(List<String> inputList) {
	List<String> outputList = new ArrayList<>();

	for (String input : inputList) {   //create frequency array and send to algorithm for all input lines
		Map<Character, Long> frequencyMap = input.chars()
				.mapToObj(c -> (char)c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		int[] frequencyArr = frequencyMap.values().stream()
				.sorted(Comparator.reverseOrder())
				.mapToInt(l -> l.intValue())
				.toArray();
		
		int result = distinctLetterCountAlgorithm(frequencyArr);  //call algorithm to calculate result count according to the frequency array
		
		outputList.add(String.valueOf(result));
	}
	return outputList;
}


private static int distinctLetterCountAlgorithm(int[] arr) {
	//input frequency array is descending ordered
	int result = 0;
	
	for(int i = 0; i < arr.length-1; i++) {		
		if(arr[i] == arr[i+1]) {  //if the frequencies of two element is equal, then extract 1 from second 
			result++;
			arr[i+1] -= 1;  
		} else if(arr[i] < arr[i+1]) {  //if the frequency of first element is less than the second one(it might happen after extraction above), then extract 2 from second
			result += 2;
			arr[i+1] -= 2;
		}
		if(arr[i+1] == 1) {   //when reach to frequency 1, remove all remain letters and break
			int[] arrR = Arrays.copyOfRange(arr, i+2, arr.length);
			int remainCount = Arrays.stream(arrR).sum();
			result += remainCount;  
			break;
		}		
	}	
	return result;
}

public enum ErrorCode {
	EMPTY_FILE_ERROR("\nError occured: File is empty, there is no data to process!"),
	MIN_CHAR_SIZE_ERROR("\nError occured: Each input line must have at least " + MIN_SIZE + " character to process!"),
	MAX_CHAR_SIZE_ERROR("\nError occured: Each input line can have max " + MAX_SIZE + " characters to process!"),
	UPPER_CASE_ERROR("\nError occured: Input contains uppercase letters! Please be sure all characters in the file are lowercase..");
	
	private String message;

	private ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}	
}
   
}

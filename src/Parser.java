import java.util.*;
import java.io.*;

public class Parser {
	public static HashMap<Integer, String> instructions;

	public Parser(){
		instructions = new HashMap<Integer, String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			String pattern = "";
			String regex = "";
			String line;


		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
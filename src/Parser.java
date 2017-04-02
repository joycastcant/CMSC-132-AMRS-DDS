import java.util.HashMap;
import java.util.Set;
import java.io.FileReader;
import java.io.BufferedReader;

public class Parser {
	public static HashMap<Integer, Instruction> instructions;

	public Parser(){
		instructions = new HashMap<Integer, Instruction>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("../files/input.txt"));
			String pattern = "";
			String regex = "";
			String line;
			int address = 1000;

			line = reader.readLine();
			while(line != null){
				line = line.toUpperCase();
				String[] lineArray = line.split(" ");
				Instruction newInst = new Instruction(lineArray);

				instructions.put(address, newInst);
				address++;
				line = reader.readLine();
			}

			Set<Integer> keys = instructions.keySet();
			for(Integer addr : keys){
				if(addr < address-1){
					Instruction a = instructions.get(addr);
					Instruction b = instructions.get(addr + 1);
					detectHazard(a,b);
				}
			}

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void detectHazard(Instruction a, Instruction b){
		String op1A = a.getOp1();
		String op2A = a.getOp2();
		String op1B = b.getOp1();
		String op2B = b.getOp2();

		String instA = a.getOperation() + " " + op1A + " " + op2A;
		String instB = b.getOperation() + " " + op1B + " " + op2B;
		System.out.println(instA);
		System.out.println(instB);

		if(op2A.equals(op1B)){
			System.out.println("Data Hazard: Write after Read (WAR) on " + instA + " and " + instB + ".");
		}
		else if(op1A.equals(op1B)){
			System.out.println("Data Hazard: Write after Write (WAW) on " + instA + " and " + instB + ".");
		}
		else if(op1A.equals(op2B)){
			System.out.println("Data Hazard: Read after Write (RAW) on " + instA + " and " + instB + ".");
		}
	}
}
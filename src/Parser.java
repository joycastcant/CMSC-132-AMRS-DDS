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
					Instruction prev = instructions.get(addr);
					Instruction current = instructions.get(addr + 1);
					//Boolean bool = detectHazard(prev,current,addr);
				}
			}

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static boolean detectHazard(Instruction prev, Instruction current){
		String op1A = prev.getOp1();
		String op2A = prev.getOp2();
		String op1B = current.getOp1();
		String op2B = current.getOp2();

		String instA = prev.getOperation() + " " + op1A + " " + op2A;
		String instB = current.getOperation() + " " + op1B + " " + op2B;
		System.out.println(instA);
		System.out.println(instB);


		if(op2A.equals(op1B)){
			if (current.getOperation().equals("CMP")) return false;
			System.out.println("Data Hazard: Write after Read (WAR) on " + instA + " and " + instB + ".");
			return true;
		}
		else if(op1A.equals(op1B)){
			if (current.getOperation().equals("CMP")) return false;
			System.out.println("Data Hazard: Write after Write (WAW) on " + instA + " and " + instB + ".");
			return true;
		}
		else if(op1A.equals(op2B)){
			System.out.println("Data Hazard: Read after Write (RAW) on " + instA + " and " + instB + ".");
			return true;
		}

		return false;
	}

/*	public static boolean detectRAW(Instruction instB, int size){
		int flag = 0;
	
		for (int i = size-1; i > 999; i--){
			Instruction instA = instructions.get(i);

			String op1 = instA.getOp1();
			String op2 = instB.getOp2();
			if (op1.equals(op2) && flag < 3){
				String prev = instA.getOperation() + " " + instA.getOp1() + " " + instA.getOp2();
				String current = instB.getOperation() + " " + instB.getOp1() + " " + instB.getOp2();
				System.out.println("Data Hazard: Read after Write (RAW) on " + prev + " and " + current + ".");
				return true;
			}
			flag++;
			
		}

		return false;
	}*/

    public static int detect(int address){
		int size = instructions.size();
    	int max_address = (1000+size) - 1;
    	boolean  haz = false;
    	int addr = 0;

		Instruction inst = instructions.get(address);

		int j = address-1, flag = 1;
		while (j >= 1000 && flag != 5 && j < max_address){
			if (detectHazard(instructions.get(j), inst)){
				return j;
			}
			j--;
			flag++;
		}

    	return addr;
	}

}
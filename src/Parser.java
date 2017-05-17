import java.util.HashMap;
import java.util.Set;
import java.io.FileReader;
import java.io.BufferedReader;

public class Parser {
	public static HashMap<Integer, Instruction> instructions;

	public Parser(String path){
		instructions = new HashMap<Integer, Instruction>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String pattern = "";
			String regex = "";
			String line;
			int address = 1000;
			
			
			
			line = reader.readLine();
			while(line != null){
				line = line.toUpperCase();
				line = line.replaceAll("[^a-zA-Z0-9 ]", "");
				UI.model.addRow(new Object[]{line}); 
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
					//Boolean bool = detectHazard(a,b,addr);
				}
			}

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static boolean detectHazard(Instruction a, Instruction b, int next){
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
			return true;
		}
		else if(op1A.equals(op1B)){
			System.out.println("Data Hazard: Write after Write (WAW) on " + instA + " and " + instB + ".");
			return true;
		}
		else if(detectRAW(b, next)){
			System.out.println("Data Hazard: Read after Write (RAW) on " + instA + " and " + instB + ".");
			return true;
		}

		return false;
	}

	public static boolean detectRAW(Instruction instB, int size){
		int flag = 0;
	
		for (int i = size-1; i > 999; i--){
			Instruction instA = instructions.get(i);

			String op1 = instA.getOp1();
			String op2 = instB.getOp2();
			if (op1.equals(op2) && flag < 3){
				String a = instA.getOperation() + " " + instA.getOp1() + " " + instA.getOp2();
				String b = instB.getOperation() + " " + instB.getOp1() + " " + instB.getOp2();
				System.out.println("Data Hazard: Read after Write (RAW) on " + a + " and " + b + ".");
				return true;
			}
			flag++;
			
		}

		return false;
	}
}
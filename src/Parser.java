import java.util.HashMap;
import java.util.Set;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.JOptionPane;

public class Parser {
	public static HashMap<Integer, Instruction> instructions;
	public static boolean valid;

	public Parser(String path){
		instructions = new HashMap<Integer, Instruction>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String pattern = "";
			String regex = "";
			String line;
			int address = 1000;
			
			
			
			line = reader.readLine();
			for(int i=1; line != null && checkSyntax(line, i); i++){
				line = line.toUpperCase();
				line = line.replaceAll("[^a-zA-Z0-9 ]", "");
				UI.model.addRow(new Object[]{line});
				String[] lineArray = line.split(" ");
				Instruction newInst = new Instruction(lineArray);

				instructions.put(address, newInst);
				address++;
				line = reader.readLine();
			}

			if(line != null)
				valid = false;
			else
				valid = true;

			Set<Integer> keys = instructions.keySet();
			for(Integer addr : keys){
				// if(addr < address-1){
					Instruction a = instructions.get(addr);
					String haza = detect(addr);
					UI.hazmodel.addRow(new Object[]{a.getInstruction(), haza});
				// }
			}

		} catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static String detectHazard(Instruction prev, Instruction current){
		String op1A = prev.getOp1();
		String op2A = prev.getOp2();
		String op1B = current.getOp1();
		String op2B = current.getOp2();

		String instA = prev.getOperation() + " " + op1A + " " + op2A;
		String instB = current.getOperation() + " " + op1B + " " + op2B;

		if(op2A.equals(op1B)){
			if (current.getOperation().equals("CMP")) return "NONE";
			return "WAR";
		}
		else if(op1A.equals(op1B)){
			if (current.getOperation().equals("CMP")) return "NONE";
			return "WAW";
		}
		else if(op1A.equals(op2B)){
			return "RAW";
		}

		return "NONE";
	}

	public static String detect(int address){
		int size = instructions.size();
    	int max_address = (1000+size) - 1;
    	boolean  haz = false;
    	int addr = 0;

		Instruction inst = instructions.get(address);

		int j = address-1, flag = 1;
		while (j >= 1000 && flag != 5 && j < max_address){
			if (!detectHazard(instructions.get(j), inst).equals("NONE")){
				return detectHazard(instructions.get(j), inst);
			}
			j--;
			flag++;
		}

    	return "NONE";
	}

	public static boolean checkSyntax(String line, int lineNum){
		boolean check;
		String[] arr = line.split(" ");

		if(arr.length < 3 || arr.length > 3) check = false;
		else{
			if(arr[0].equals("LOAD")){
				try{
					int immed = Integer.valueOf(arr[2]);
					if(!isReg(arr[1]))
						check = false;
					else if(immed < -99 || immed > 99)
						check = false;
					else check = true;
				}catch(Exception e){
					check = false;
					System.out.println("Syntax error in line " + lineNum);
					JOptionPane.showMessageDialog(null, "Syntax error in line " + lineNum, "Syntax error", JOptionPane.INFORMATION_MESSAGE);
					return check;
				}
			}else if(arr[0].equals("ADD") || arr[0].equals("SUB") || arr[0].equals("CMP")){
				try{
					if(!isReg(arr[1]) || !isReg(arr[2]))
						check = false;
					else check = true;
				}catch(Exception ee){
					System.out.println("A: " + line);
					check = false;
					System.out.println("Syntax error in line " + lineNum);
					JOptionPane.showMessageDialog(null, "Syntax error in line " + lineNum, "Syntax error", JOptionPane.INFORMATION_MESSAGE);
					return check;
				}
			}else check = false;
		}

		if(!check){
			System.out.println("Syntax error in line " + lineNum);
			JOptionPane.showMessageDialog(null, "Syntax error in line " + lineNum, "Syntax error", JOptionPane.INFORMATION_MESSAGE);
		}

		return check;
	}

	public static boolean isReg(String reg){
		int len = reg.length();

		if(len <= 1)
			return false;

		int num = Integer.valueOf(reg.substring(1,len));
		if(!reg.substring(0,1).equals("R"))
			return false;
		
		if(num < 1 || num > 32)
			return false;

		return true;
	}
}
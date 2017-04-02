import java.util.HashMap;
import java.util.Map.Entry;

public class Main {
	public static HashMap<Integer, Instruction> instructions;
	public static HashMap<String, Integer> registers;

	public static void main (String[] args){
		Parser parser = new Parser();
		MSC msc = new MSC();
		instructions = parser.instructions;
		
		for(Entry e : instructions.entrySet()){
			Instruction i = (Instruction)e.getValue();
			String inst = i.getInstruction();
			int key = (int)e.getKey();
			System.out.println(key + " : " + inst);
			Fetch fetch = new Fetch(instructions, true, key, msc);
		}

		registers = msc.getRegisters();
		System.out.println(registers.entrySet());
		System.out.println("OF: " + msc.getOf() + "\nZF: " + msc.getZf() + "\nNF: " + msc.getNf() + "\n");
	}
}
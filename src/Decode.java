import java.util.HashMap;

public class Decode {
	public HashMap<Integer, Instruction> instructions;
	public boolean occupied;
	public int ir;

	public Decode(HashMap<Integer, Instruction> instructions, int ir, MSC msc){
		System.out.println("Decoding...");
		this.instructions = instructions;
		this.occupied = true;
	
		Instruction value = instructions.get(ir);
		Execute execute = new Execute(msc);
		String operand = value.getOperation();
		String op1 = value.getOp1();
		String op2 = value.getOp2();

		System.out.println("Instruction: " + value.getInstruction());

		if (operand.equals("LOAD")){
			int oP2 = Integer.valueOf(op2);
			execute.load(op1, oP2);
		}

		if (operand.equals("ADD")){
			execute.add(op1, op2);
		}

		if (operand.equals("SUB")){
			execute.sub(op1, op2);
		}

		if (operand.equals("CMP")){
			execute.cmp(op1, op2);
		}
	}
}
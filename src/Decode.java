import java.util.HashMap;

public class Decode {
	public HashMap<Integer, Instruction> instructions;
	public boolean occupied;
	public int ir;

	public Decode(HashMap<Integer, Instruction> instructions, int ir){
		this.instructions = instructions;
		this.occupied = true;
	
		Instruction value = instructions.get(ir);
		Execute execute = new Execute();
		String operand = value.getOperation();
		String op1 = value.getOp1();
		String op2 = value.getOp2();
		int oP2 = Integer.valueOf(op2);

		if (operand.equals("LOAD")){
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
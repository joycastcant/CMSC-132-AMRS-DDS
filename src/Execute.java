import java.util.HashMap;

public class Execute{
	public static HashMap<Integer, Instruction> instructions;
	public static MSC msc;
	private static  HashMap<String, Integer> reg;
	public static String operand = "";
	public static String op1 = "";
	public static String op2 = "";
	public static int ir = -1;
	public static boolean occupied = false;
	static int a;
	static int b;

	/* OLD public Execute(MSC msc){
		System.out.println("Executing...");
		reg = msc.getRegisters();
		this.msc = msc;
	}*/
	public Execute(HashMap<Integer, Instruction> instructions, MSC msc){
		this.instructions = instructions;
		reg = msc.getRegisters();
		this.msc = msc;
		
	}
	
	public static void performOperation() {
		if(ir!=-1) {
			System.out.println("executing "+ir);
			if (operand.equals("LOAD")){
				int oP2 = Integer.valueOf(op2);
				load(op1, oP2);
			}

			if (operand.equals("ADD")){
				add(op1, op2);
			}

			if (operand.equals("SUB")){
				sub(op1, op2);
			}

			if (operand.equals("CMP")){
				cmp(op1, op2);
			}
			
			occupied = true;
		}
	}
	
	public static void free() {
		setIr(-1);
		operand = "";
		op1 = "";
		op2 = "";
		occupied = false;
	}
	
	public static void setIr(int i) {
		ir = i;
	}
	
	public static boolean isOccupied() {
		return occupied;
	}
	
	public static void load(String op1, int a){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		msc.setReg(op1, a);
		if(a > 9) msc.setOf(1);
	}

	public static void add(String op1, String op2){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		a = reg.get(op1);
		b = reg.get(op2);
		a = a + b;
		msc.setReg(op1, a);
		if(a > 9) msc.setOf(1);
	}

	public static void sub(String op1, String op2){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		a = reg.get(op1);
		b = reg.get(op2);
		a = a - b;
		msc.setReg(op1, a);
		if(a > 9) msc.setOf(1);
	}

	public static void cmp(String op1, String op2){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		a = reg.get(op1);
		b = reg.get(op2);
		a = a - b;

		if(a == 0) msc.setZf(1);
		else if(a < 1) msc.setNf(1);
	}
	
	public static void setOperand(String opr) {
		operand = operand;
	}
	public static void setOp1(String x) {
		op1 = x;
	}
	public static void setOp2(String y) {
		op2 = y;
	}
}

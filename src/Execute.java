import java.util.HashMap;

public class Execute{
	public static HashMap<Integer, Instruction> instructions;
	public static MSC msc;
	public static String dest;
	public static String operand;
	public static int op1;
	public static int op2;
	public static int ir = -1;
	public static boolean occupied = false;
	static int result;

	/* OLD public Execute(MSC msc){
		System.out.println("Executing...");
		reg = msc.getRegisters();
		this.msc = msc;
	}*/
	public Execute(HashMap<Integer, Instruction> instructions, MSC msc){
		this.instructions = instructions;
		this.msc = msc;
		
	}
	
	public static void performOperation() {
		if(ir!=-1) {
			System.out.println("executing "+ir);

			switch(operand){
				case "LOAD":
					load(op2);
					break;
				case "ADD":
					add(op1, op2);
					break;
				case "SUB":
					sub(op1, op2);
					break;
				case "CMP":
					cmp(op1, op2);
					break;
				default:
					System.out.println("Invalid operation");
					break;
			}
			
			//occupied = true;
		}
	}
	
	public static void occupy() {
		occupied = true;
	}
	
	public static void free() {
		Main.ma.setIr(ir);
		Main.ma.occupy();
		Main.ma.setDest(dest);
		Main.ma.setVal(result);
		System.out.println("d: " + dest + " r: " + result);
		setIr(-1);
		operand = "";
		op1 = 0;
		op2 = 0;
		occupied = false;
	}
	
	public static void setIr(int i) {
		ir = i;
	}
	
	public static boolean isOccupied() {
		return occupied;
	}
	
	public static void load(int op2){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		result = op2;
		if(result > 9) msc.setOf(1);
	}

	public static void add(int op1, int op2){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		result = op1 + op2;
		System.out.println(op1 + " + " + op2);
		if(result > 9) msc.setOf(1);
	}

	public static void sub(int op1, int op2){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		result = op1 - op2;
		System.out.println(op1 + " - " + op2);
		if(result > 9) msc.setOf(1);
	}

	public static void cmp(int op1, int op2){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		result = op1 - op2;
		System.out.println(op1 + " vs " + op2);
		if(result == 0) msc.setZf(1);
		else if(result < 1) msc.setNf(1);
	}
	
	public static void setOperand(String opr) {
		operand = opr;
	}

	public static void setOp1(int x) {
		op1 = x;
	}

	public static void setOp2(int y) {
		op2 = y;
	}

	public static void setDest(String destination) {
		dest = destination;
	}
}

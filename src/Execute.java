import java.util.HashMap;

public class Execute{
	public static HashMap<Integer, Instruction> instructions;
	public static MSC msc;
	public static String dest;
	public static String operand;
	public static int op1;
	public static int op2;
	public static int ir = -1;
	public static String reg1, reg2;
	public static boolean stall;
	public static boolean occupied;
	static int result;

	public Execute(HashMap<Integer, Instruction> instructions, MSC msc){
		this.instructions = instructions;
		this.msc = msc;
		this.reg1 = "e_none";
		this.reg2="e_none";
		this.stall = false;
		this.occupied = false;
	}
	
	public static int performOperation() {
		if(ir!=-1) {
			System.out.println("executing "+ir);
			
			if(Main.instructions.containsKey(ir))Main.instructions.get(ir).stages.add("E"); //add E to pipeline table
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
					
		}
		
		return ir;
	}
	
	public static void occupy() {
		occupied = true;
	}
	
	public static void free() {
		Main.ma.reg1 = reg1;
		Main.ma.reg2 = reg2;
		reg1 ="e_none";
		reg2="e_none";
    
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

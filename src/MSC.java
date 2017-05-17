import java.util.HashMap;

public class MSC {
	private static HashMap<String, Integer> registers;
	private static int a;
	private static int ir;
	private static int of;
	private static int nf;
	private static int zf;
	private static int pc;
	private static int mar;
	private static Instruction mbr;

	public MSC(){
		pc = 1000;
		registers = new HashMap<String, Integer>();
		for (int i = 1; i < 33; i++){
			registers.put("R"+i, 0);
		}
	}

	public static void incrementPC(){
		pc++;
	}

	public static int getPC(){
		return pc;
	}

	public static HashMap<String, Integer> getRegisters(){
		return registers;
	}

	public static int getA(){
		return a;
	}

	public static int getIr(){
		return ir;
	}

	public static int getOf(){
		return of;
	}

	public static int getNf(){
		return nf;
	}

	public static int getZf(){
		return zf;
	}

	public static int getMar(){
		return mar;
	}

	public static Instruction getMbr(){
		return mbr;
	}

	public static void setRegisters(HashMap<String, Integer> newValues){
		registers = newValues;
	}

	public static void setA(int newA){
		a = newA;
	}

	public static void setIr(int newIr){
		ir = newIr;
	}

	public static void setOf(int newOf){
		of = newOf;
	}

	public static void setNf(int newNf){
		nf = newNf;
	}

	public static void setZf(int newZf){
		zf = newZf;
	}

	public static void setMar(int newMar){
		mar = newMar;
	}

	public static void setMbr(Instruction newMbr){
		mbr = newMbr;
	}

	public static void setReg(String key, int val){
		registers.put(key, val);
	}
}
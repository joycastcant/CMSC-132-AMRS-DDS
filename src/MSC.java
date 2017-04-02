import java.util.HashMap;

public class MSC {
	public static HashMap<String, Integer> registers;
	public static int a;
	public static int ir;
	public static int of;
	public static int nf;
	public static int zf;
	public static int pc;
	public static int mar;
	public static int mbr;

	public MSC(){
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

}
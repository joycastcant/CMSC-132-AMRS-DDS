import java.util.HashMap;

public class MSC {
	private HashMap<String, Integer> registers;
	private int a;
	private int ir;
	private int of;
	private int nf;
	private int zf;
	private int pc;
	private int mar;
	private Instruction mbr;

	public MSC(){
		pc = 0;
		registers = new HashMap<String, Integer>();
		for (int i = 1; i < 33; i++){
			registers.put("R"+i, 0);
		}
	}

	public void incrementPC(){
		pc++;
	}

	public int getPC(){
		return pc;
	}

	public HashMap<String, Integer> getRegisters(){
		return registers;
	}

	public int getA(){
		return a;
	}

	public int getIr(){
		return ir;
	}

	public int getOf(){
		return of;
	}

	public int getNf(){
		return nf;
	}

	public int getZf(){
		return zf;
	}

	public int getMar(){
		return mar;
	}

	public Instruction getMbr(){
		return mbr;
	}

	public void setRegisters(HashMap<String, Integer> newValues){
		registers = newValues;
	}

	public void setA(int newA){
		a = newA;
	}

	public void setIr(int newIr){
		ir = newIr;
	}

	public void setOf(int newOf){
		of = newOf;
	}

	public void setNf(int newNf){
		nf = newNf;
	}

	public void setZf(int newZf){
		zf = newZf;
	}

	public void setMar(int newMar){
		mar = newMar;
	}

	public void setMbr(Instruction newMbr){
		mbr = newMbr;
	}

	public void setReg(String key, int val){
		System.out.println("ayy " + key + " " + val);
		registers.put(key, val);
		System.out.println("Reg: " + registers.entrySet());
	}
}
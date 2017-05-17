import java.util.HashMap;
import java.util.ArrayList;

public class Stall {
	public static HashMap<Integer, Instruction> instructions;
	public static MSC msc;
	private static  HashMap<String, Integer> reg;
	public static boolean occupied;
	public static Instruction value;
	public static ArrayList<Integer> stack;
	public static HashMap<Integer, Integer> storu;
	
	public Stall(HashMap<Integer, Instruction> instructions, MSC msc){
		this.instructions = instructions;
		this.msc = msc;
		this.storu = new HashMap<Integer, Integer>();
		this.stack = new ArrayList<Integer>();
		this.occupied = false;
	}
	
	public static void stall() {
		//for loop
		for(int i : stack) {
			Instruction in = instructions.get(i);
			System.out.println("stalling "+i);
			if(Main.instructions.containsKey(i)){
				Main.instructions.get(i).stages.add("S"); //add S to pipeline table
				storu.put(Main.clockCycles, 0);
			}
		}
	}
	
	public static void free() {
		Instruction a = instructions.get(stack.get(0));
		if(((a.getOp2().equals(Main.execute.reg1) || a.getOp2().equals(Main.ma.reg1) || a.getOp2().equals(Main.wb.reg1)) || (a.getOp2().equals(Main.execute.reg2) || a.getOp2().equals(Main.ma.reg2) || a.getOp2().equals(Main.wb.reg2)) || (a.getOp1().equals(Main.execute.reg1) || a.getOp1().equals(Main.ma.reg1) || a.getOp1().equals(Main.wb.reg1)) || (a.getOp2().equals(Main.execute.reg1) || a.getOp2().equals(Main.ma.reg1) || a.getOp2().equals(Main.wb.reg1))))
		{
			// do nothing
		}
		else {
			int ir = stack.remove(0);
			System.out.print("BYEBYE "+ir);
			Main.decode.setIr(ir);
			Main.decode.occupy();
			if(instructions.containsKey(ir)) Main.decode.setValue(instructions.get(ir));
			if(stack.size()==0) {
				occupied = false;
			}
		}
	}
	
	public static void addIr(int i) {
		stack.add(i);
	}
	
	public static void occupy() {
		occupied = true;
	}
	
	public static boolean isOccupied() {
		return occupied;
	}
}
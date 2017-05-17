import java.util.HashMap;

public class Fetch {
	public static HashMap<Integer, Instruction> instructions;
	public static MSC msc;
	public static boolean occupied;
	public static int ir;
	public static boolean stall;

	public Fetch(HashMap<Integer, Instruction> instructions, MSC msc){
		this.instructions = instructions;
		this.msc = msc;
		this.ir=-1;
		this.occupied=false;
		this.stall=false;

	}
	
	public static void fetchInstruction() {
		if(!stall) {
			msc.setMar(msc.getPC());
			ir = msc.getPC();
			
			msc.setMbr(instructions.get(msc.getMar()));
			if(msc.getMbr()!=null) {
				System.out.println("fetching "+ir);
				msc.incrementPC();
				Main.instructions.get(ir).stages.add("F"); //add F to pipeline table
				occupied = true;
			}
		
		}
	}
	
	public static void occupy() {
		occupied = true;
	}

	public static void free() {
		if(instructions.containsKey(ir)) {
			Instruction a = instructions.get(ir);
			if(Main.stallfu.isOccupied() || ((a.getOp2().equals(Main.execute.reg1) || a.getOp2().equals(Main.ma.reg1) || a.getOp2().equals(Main.wb.reg1)) || (a.getOp2().equals(Main.execute.reg2) || a.getOp2().equals(Main.ma.reg2) || a.getOp2().equals(Main.wb.reg2)) || (a.getOp1().equals(Main.execute.reg1) || a.getOp1().equals(Main.ma.reg1) || a.getOp1().equals(Main.wb.reg1)) || (a.getOp2().equals(Main.execute.reg1) || a.getOp2().equals(Main.ma.reg1) || a.getOp2().equals(Main.wb.reg1)))) {
				Main.stallfu.addIr(ir);
				Main.stallfu.occupy();
			}
			else {
				Main.decode.setIr(ir);
				Main.decode.occupy();
				Main.decode.setValue(msc.getMbr());
				ir = -1;
				occupied = false;
				stall = false;
			}
		}
		else occupied = false;
	}
	
	public static boolean isOccupied() {
		return occupied;
	}
}
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class Main {
	public static HashMap<Integer, Instruction> instructions;
	public static HashMap<String, Integer> registers;
	public static MSC msc;
	public static Stall stallfu;
	public static Fetch fetch;
	public static Decode decode;
	public static Execute execute;
	public static MemoryAccess ma;
	public static WriteBack wb;
	public static int clockCycles;
	
	public static void main (String[] args){
		UI ui = new UI("AMRS");
	}
	
	public static void begin(String path) {
		Boolean hazard = false;
		Parser parser = new Parser(path);
		clockCycles = 1;
		msc = new MSC();

		if(!parser.valid)
			return;

		int temp = 0;
		instructions = parser.instructions;
		stallfu = new Stall(instructions, msc);
		fetch = new Fetch(instructions, msc);
		decode = new Decode(instructions, msc);
		execute = new Execute(instructions, msc);
		ma = new MemoryAccess(instructions, msc);
		wb = new WriteBack(instructions, msc);
		
		
		do{
			System.out.println("\n\n============================ TIME "+clockCycles+" ============================\n\n");
			
			if(wb.isOccupied()) {
				wb.free();
			}
			if(ma.isOccupied()) {
				ma.free();
			}
			if(execute.isOccupied()) {
				execute.free();
			}
			if(decode.isOccupied()) {
				decode.free();
			}
			if(stallfu.isOccupied()) {
				stallfu.free();
			}
			if(fetch.isOccupied()) {
				fetch.free();
			}
			
			fetch.fetchInstruction();
			int hzrd = 0;
			
			stallfu.stall();
			decode.getValues();
			execute.performOperation();
			ma.accessMem();
			int a = wb.write();

			System.out.println("Fetch is occupied: "+fetch.isOccupied()+" ir="+fetch.ir);
			System.out.println("Decode is occupied: "+decode.isOccupied()+" ir="+decode.ir);
			System.out.println("Execute is occupied: "+execute.isOccupied()+" ir="+execute.ir);
			System.out.println("MemAccess is occupied: "+ma.isOccupied()+" ir="+ma.ir);
			System.out.println("WBack is occupied: "+wb.isOccupied()+" ir="+wb.ir);
			
			clockCycles++;
			if(!fetch.isOccupied() && !stallfu.isOccupied() && !decode.isOccupied() && !execute.isOccupied() && !ma.isOccupied() && wb.isOccupied()) wb.free();
		} while(fetch.isOccupied() || decode.isOccupied() || execute.isOccupied() || ma.isOccupied() || wb.isOccupied());
		
		clockCycles--;
		registers = msc.getRegisters();

		Set<String> keys = registers.keySet();
		for(int i=0; i<33; i++){
			for(String a:keys){
				if(a.substring(1, a.length()).equals(String.valueOf(i)))
					UI.regmodel.addRow(new Object[]{a, registers.get(a)});
			}
		}

		UI.flagmodel.addRow(new Object[]{"OF", msc.getOf()});
		UI.flagmodel.addRow(new Object[]{"NF", msc.getNf()});
		UI.flagmodel.addRow(new Object[]{"ZF", msc.getZf()});

		UI.stalls.setText("Stalls: " + Stall.storu.size());
		
		int cols = UI.model.getColumnCount()-1;
		System.out.print(cols);
		System.out.print("cols>clockCycles:: "+(cols<clockCycles));
		UI.clk.setText("Cycles: " + clockCycles + "\t");
		if(cols<clockCycles) {
			while(cols<clockCycles) {
				UI.model.addColumn(""+(cols+1));
				cols++;
			}
		}
		
		int a = 1;
		int row = 0;
		int addr = 1000;
		
		while(instructions.containsKey(addr)) {
			ArrayList<String> pipelineStage = instructions.get(addr).stages;
			int b = a;
			System.out.println("PIPELINE" + pipelineStage.size());
			for(String s : pipelineStage) { 
				UI.model.setValueAt(s,row,b);b++;
			}
			addr++;
			row++;
			a++;
		}
	}
}
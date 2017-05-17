import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class Main {
	public static HashMap<Integer, Instruction> instructions;
	public static HashMap<String, Integer> registers;
	public static MSC msc = new MSC();
	public static Stall stallfu;
	public static Fetch fetch;
	public static Decode decode;
	public static Execute execute;
	public static MemoryAccess ma;
	public static WriteBack wb;
	public static int clockCycles=1;
	
	public static void main (String[] args){
		
		UI ui = new UI("AMRS");
		
		/*Boolean hazard = false;
		Parser parser = new Parser();
		int temp = 0;
		//MSC msc = new MSC(); OLD
		instructions = parser.instructions;
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
				// transfer and execute
				execute.free();
			}
			if(decode.isOccupied()) {
				decode.free();
			}
			if(fetch.isOccupied()) {
				fetch.free(hazard);
			}

			int next = fetch.fetchInstruction();
			int hzrd = 0;
			if(next-1 > 1000 && instructions.containsKey(next)){
				hazard = parser.detectHazard(instructions.get(next-1),instructions.get(next), next);
				if(hazard == true) hzrd = next;
				System.out.println(hazard);
				temp = next;
			}

			if(hazard == true){
				
				System.out.println("Stall");
			}

			decode.getValues();
			execute.performOperation();
			ma.accessMem();
			int a = wb.write();
			execute.performOperation();
			if(a == -1 && hazard == true){
				hazard = false;
				decode.setIr(temp);
				msc.setMbr(instructions.get(temp));
				decode.occupy();
				decode.getValues();
			}

			System.out.println("Fetch is occupied: "+fetch.isOccupied()+" ir="+fetch.ir);
			System.out.println("Decode is occupied: "+decode.isOccupied()+" ir="+decode.ir);
			System.out.println("Execute is occupied: "+execute.isOccupied()+" ir="+execute.ir);
			System.out.println("MemAccess is occupied: "+ma.isOccupied()+" ir="+ma.ir);
			System.out.println("WBack is occupied: "+wb.isOccupied()+" ir="+wb.ir);
			
			//UI.model.addRow(new Object[]{"INSTR1", scr_fetch, scr_decode, scr_exec, scr_macc, scr_wback}); 
			
			try{
				Thread.sleep(1000);
			} catch(Exception ee) {}
			clockCycles++;
			if(!fetch.isOccupied() && !decode.isOccupied() && !execute.isOccupied() && !ma.isOccupied() && wb.isOccupied()) wb.free();
		} while(fetch.isOccupied() || decode.isOccupied() || execute.isOccupied() || ma.isOccupied() || wb.isOccupied());
		
		/*
		for(Entry e : instructions.entrySet()){
			Instruction i = (Instruction)e.getValue();
			String inst = i.getInstruction();
			int key = (int)e.getKey();
			//System.out.println("time "+count);
			count++;
			//System.out.println(key + " : " + inst);
			
			//NEWER
			//Thread thread = new Thread(i);
			//thread.start();
			
			if(fetch.isOccupied()) {
				// transfer contents to decode
				
			}
			fetch.fetchInstruction(key);
			//load next instruction
			if(decode.isOccupied()) {
				// transfer contents to execute
			}
			//load values
			if(execute.isOccupied()) {
				// transfer and execute
			}
			// load val
			
			try{
				Thread.sleep(1000);
			} catch(Exception ee) {}
			
			// NEW
			//fetch.fetchInstruction(key);
			
			// OLD
			//Fetch fetch = new Fetch(instructions, msc);
			
			//try{
			//	Thread.sleep(2000);
			//} catch(Exception ex){}
		}*/

		//registers = msc.getRegisters();
		//System.out.println(registers.entrySet());
		//System.out.println("OF: " + msc.getOf() + "\nZF: " + msc.getZf() + "\nNF: " + msc.getNf() + "\n");
	}
	
	public static void begin(String path) {
		Boolean hazard = false;
		Parser parser = new Parser(path);

		if(!parser.valid)
			return;

		int temp = 0;
		//MSC msc = new MSC(); OLD
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
				// transfer and execute
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

			/* int next =  */fetch.fetchInstruction();
			int hzrd = 0;
			/* if(next-1 > 1000 && instructions.containsKey(next)){
				hazard = parser.detectHazard(instructions.get(next-1),instructions.get(next), next);
				if(hazard == true) hzrd = next;
				System.out.println(hazard);
				temp = next;
			}

			if(hazard == true){
				
				System.out.println("Stall");
				if(instructions.containsKey(next))Main.instructions.get(next).stages.add("S"); //add F to pipeline table
			} */
			
			stallfu.stall();
			decode.getValues();
			execute.performOperation();
			ma.accessMem();
			int a = wb.write();
			/* execute.performOperation();
			if(a == -1 && hazard == true){
				hazard = false;
				decode.setIr(temp);
				msc.setMbr(instructions.get(temp));
				decode.occupy();
				decode.getValues();
			} */

			System.out.println("Fetch is occupied: "+fetch.isOccupied()+" ir="+fetch.ir);
			System.out.println("Decode is occupied: "+decode.isOccupied()+" ir="+decode.ir);
			System.out.println("Execute is occupied: "+execute.isOccupied()+" ir="+execute.ir);
			System.out.println("MemAccess is occupied: "+ma.isOccupied()+" ir="+ma.ir);
			System.out.println("WBack is occupied: "+wb.isOccupied()+" ir="+wb.ir);
			
			//UI.model.addRow(new Object[]{"INSTR1", scr_fetch, scr_decode, scr_exec, scr_macc, scr_wback}); 
			
			/* try{
				Thread.sleep(1000);
			} catch(Exception ee) {} */
			clockCycles++;
			if(!fetch.isOccupied() && !stallfu.isOccupied() && !decode.isOccupied() && !execute.isOccupied() && !ma.isOccupied() && wb.isOccupied()) wb.free();
		} while(fetch.isOccupied() || decode.isOccupied() || execute.isOccupied() || ma.isOccupied() || wb.isOccupied());
		
		clockCycles--;
		registers = msc.getRegisters();

		Set<String> keys = registers.keySet();
		// for(String a:keys){
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
			for(String s : pipelineStage) {
				UI.model.setValueAt(s,row,b);b++;
			}
			addr++;
			row++;
			a++;
		}
	}
}
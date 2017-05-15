import java.util.HashMap;
import java.util.Map.Entry;

public class Main {
	public static HashMap<Integer, Instruction> instructions;
	public static HashMap<String, Integer> registers;
	public static MSC msc = new MSC();
	public static Fetch fetch;
	public static Decode decode;
	public static Execute execute;
	public static MemoryAccess ma;
	public static WriteBack wb;
	
	public static void main (String[] args){
		boolean hazard = false;
		Parser parser = new Parser();
		int temp = 0;
		//MSC msc = new MSC(); OLD
		instructions = parser.instructions;
		fetch = new Fetch(instructions, msc);
		decode = new Decode(instructions, msc);
		execute = new Execute(instructions, msc);
		ma = new MemoryAccess(instructions, msc);
		wb = new WriteBack(instructions, msc);
		int count=1;
		
		do{
			System.out.println("\n\n============================ TIME "+count+" ============================\n\n");count++;
			
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

			int addr = fetch.fetchInstruction();
			int dependency = parser.detect(addr);
			if(dependency > 0){
				while(decode.ir == dependency || execute.ir == dependency || ma.ir == dependency || wb.ir == dependency){
					System.out.println("Staaaaaaaaaaaalllllllllllll");
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
					decode.getValues();
					execute.performOperation();
					ma.accessMem();
					wb.write();
					count++;
				}
			}
			else{
				decode.getValues();
				execute.performOperation();
				ma.accessMem();
				wb.write();
			}

			System.out.println("Fetch is occupied: "+fetch.isOccupied()+" ir="+fetch.ir);
			System.out.println("Decode is occupied: "+decode.isOccupied()+" ir="+decode.ir);
			System.out.println("Execute is occupied: "+execute.isOccupied()+" ir="+execute.ir);
			System.out.println("MemAccess is occupied: "+ma.isOccupied()+" ir="+ma.ir);
			System.out.println("WBack is occupied: "+wb.isOccupied()+" ir="+wb.ir);
			
			try{
				Thread.sleep(1000);
			} catch(Exception ee) {}
			
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

		registers = msc.getRegisters();
		//System.out.println(registers.entrySet());
		//System.out.println("OF: " + msc.getOf() + "\nZF: " + msc.getZf() + "\nNF: " + msc.getNf() + "\n");
	}
}
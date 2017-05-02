import java.util.HashMap;
import java.util.Map.Entry;

public class Main {
	public static HashMap<Integer, Instruction> instructions;
	public static HashMap<String, Integer> registers;
	public static MSC msc = new MSC();
	public static Fetch fetch;
	public static Decode decode;
	public static Execute execute;
	
	public static void main (String[] args){
		Parser parser = new Parser();
		//MSC msc = new MSC(); OLD
		instructions = parser.instructions;
		fetch = new Fetch(instructions, msc);
		decode = new Decode(instructions, msc);
		execute = new Execute(instructions, msc);
		int count=1;
		
		do{
			System.out.println("=============== time "+count+" =============");count++;
			
			
			if(execute.isOccupied()) {
				// transfer and execute
				execute.free();
			}
			if(decode.isOccupied()) {
				decode.free();
			}
			if(fetch.isOccupied()) {
				fetch.free();
			}
			fetch.fetchInstruction();
			decode.getValues();
			execute.performOperation();
			
			System.out.println("Fetch is occupied: "+fetch.isOccupied()+" ir="+fetch.ir);
			System.out.println("Decode is occupied: "+decode.isOccupied()+" ir="+decode.ir);
			System.out.println("Execute is occupied: "+execute.isOccupied()+" ir="+execute.ir);
			
			try{
				Thread.sleep(1000);
			} catch(Exception ee) {}
			
			if(!fetch.isOccupied() && !decode.isOccupied() && execute.isOccupied()) execute.free();
		} while(fetch.isOccupied() || decode.isOccupied() || execute.isOccupied());
		
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
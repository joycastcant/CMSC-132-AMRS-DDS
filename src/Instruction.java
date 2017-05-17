public class Instruction {
	private String operation;
	private String op1;
	private String op2;
	/*public Instruction(String[] inst){
		this.operation = inst[0];
		this.op1 = inst[1];
		this.op2 = inst[2];
	}*/
	
	public Instruction(String[] inst){
		this.operation = inst[0];
		this.op1 = inst[1];
		this.op2 = inst[2];
	}
	
	/* 			jay
	public void run() {
		try{
			while(Main.fetch.isOccupied()) this.sleep(1000);
			Main.fetch.fetchInstruction(key);
			while(Main.decode.isOccupied()) this.sleep(1000);
			Main.decode.getValues(key);
			while(Main.execute.isOccupied()) this.sleep(1000);
			Main.execute.performOperation(operation, op1, op2, key);
		} catch(Exception e) {}
	}*/
	
	public String getOperation(){
		return this.operation;
	}

	public String getOp1(){
		return this.op1;
	}

	public String getOp2(){
		return this.op2;
	}

	public String getInstruction(){
		return this.operation + " " + this.op1 + " " + this.op2;
	}
}
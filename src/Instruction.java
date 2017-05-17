import java.util.*;

public class Instruction {
	private String operation;
	private String op1;
	private String op2;
	public ArrayList<String> stages;
	
	public Instruction(String[] inst){
		this.operation = inst[0];
		this.op1 = inst[1];
		this.op2 = inst[2];
		this.stages = new ArrayList<String>();
	}
	
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
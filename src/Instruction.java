import java.util.*;
import java.io.*;

public class Instruction {
	public String operation;
	public String op1;
	public String op2;

	public Instruction(String[] inst){
		this.operation = inst[0];
		this.op1 = inst[1];
		this.op2 = inst[2];
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
}
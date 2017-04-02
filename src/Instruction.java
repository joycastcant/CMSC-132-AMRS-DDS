import java.util.*;
import java.io.*;

public class Instruction(){
	public String operation;
	public Sting op1;
	public String op2;

	public Instruction(String[] inst){
		operation = inst[0];
		op1 = inst[1];
		op2 = inst[2];
	}

	public statio String getOperation(){
		return operation;
	}

	public static String getOp1(){
		return op1;
	}

	public static String getOp2(){
		return op2;
	}
}
import java.util.HashMap;

public class Execute{
	private static HashMap<String, Integer> reg;
	int a;
	int b;

	public Execute(){
		reg = Main.getRegisters();
		/*of = Main.getOf();
		zf = Main.getZf();
		nf = Main.getNf();*/
	}
	/*LOAD
	Loads an immediate value into a register. For simplicity, we assume that no floating point values are provided.
	Sets OF to 1 if the value loaded is more than 2 digits, 0 otherwise.
	Example: LOAD R1 5

	ADD
	Adds the current values of two registers and accumulates the results in the first register. Sets OF to 1 if the
	value loaded is more than 2 digits, 0 otherwise.
	Example: ADD R32, R1

	SUB
	Subtracts the value of the second register from the first register and stores the results in the first register. Sets
	OF to 1 if the value loaded is more than 2 digits, 0 otherwise.
	Example: SUB R32, R1

	CMP
	Compares the values of two registers by subtracting the value of the second register from the value of the
	first register. If the result is zero (0), the ZF is set to 1, 0 otherwise (default). If the result is negative, the NF is set
	to 1, 0 otherwise (default). No change (default value) for NF and ZF for a positive difference.*/

	//arr[a, b, of, zf, nf]
		//initialized na 0 yung flags
	public void load(String op1, int a){
		reg.put(op1, a);
		if(a > 9) Main.setOf(1);
	}

	public void add(String op1, String op2){
		a = reg.get(op1);
		b = reg.get(op2);
		a = a + b;
		reg.put(op1, a);
		if(a > 9) Main.setOf(1);
	}

	public void sub(String op1, String op2){
		a = reg.get(op1);
		b = reg.get(op2);
		a = a - b;
		reg.put(op1, a);
		if(a > 9) Main.setOf(1);
	}

	public void cmp(String op1, String op2){
		a = reg.get(op1);
		b = reg.get(op2);
		a = a - b;

		if(a == 0) Main.setZf(1);
		else if(a < 1) Main.setNf(1);
	}
}

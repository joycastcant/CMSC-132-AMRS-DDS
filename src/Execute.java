import java.util.HashMap;

public class Execute{
	private static HashMap<String, Integer> reg;
	int a;
	int b;

	public Execute(){
		reg = MSC.getRegisters();
	}
	
	public void load(String op1, int a){
		reg.put(op1, a);
		if(a > 9) MSC.setOf(1);
	}

	public void add(String op1, String op2){
		a = reg.get(op1);
		b = reg.get(op2);
		a = a + b;
		reg.put(op1, a);
		if(a > 9) MSC.setOf(1);
	}

	public void sub(String op1, String op2){
		a = reg.get(op1);
		b = reg.get(op2);
		a = a - b;
		reg.put(op1, a);
		if(a > 9) MSC.setOf(1);
	}

	public void cmp(String op1, String op2){
		a = reg.get(op1);
		b = reg.get(op2);
		a = a - b;

		if(a == 0) MSC.setZf(1);
		else if(a < 1) MSC.setNf(1);
	}
}

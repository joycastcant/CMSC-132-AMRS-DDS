import java.util.HashMap;

public class Execute{
	private HashMap<String, Integer> reg;
	int a;
	int b;
	MSC msc;

	public Execute(MSC msc){
		System.out.println("Executing...");
		reg = msc.getRegisters();
		this.msc = msc;
	}
	
	public void load(String op1, int a){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		msc.setReg(op1, a);
		if(a > 9) msc.setOf(1);
	}

	public void add(String op1, String op2){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		a = reg.get(op1);
		b = reg.get(op2);
		a = a + b;
		msc.setReg(op1, a);
		if(a > 9) msc.setOf(1);
	}

	public void sub(String op1, String op2){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		a = reg.get(op1);
		b = reg.get(op2);
		a = a - b;
		msc.setReg(op1, a);
		if(a > 9) msc.setOf(1);
	}

	public void cmp(String op1, String op2){
		msc.setOf(0);
		msc.setZf(0);
		msc.setNf(0);
		a = reg.get(op1);
		b = reg.get(op2);
		a = a - b;

		if(a == 0) msc.setZf(1);
		else if(a < 1) msc.setNf(1);
	}
}

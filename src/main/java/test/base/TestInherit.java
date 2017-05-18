package test.base;

class Inherited{
	
	public void one(){
		System.out.println("Inherited.one()");
	}
	
	public void two(){
		System.out.println("Inherited.two");
		one();
	}
	
	private void three(){
		System.out.println("Inherited.three()");
	}
	
	public static void main(String[] args) {
		Inherited inherited = new TestInherit();
		inherited.three();
	}
}

public class TestInherit extends Inherited{

	public void one(){
		System.out.println("TestInherit.one()");
	}
	
	public void three(){
		System.out.println("TestInherit.three()");
	}
	
	public static void main(String[] args) {
		Inherited in = new TestInherit();
		in.two();
		Inherited.main(args);
	}
}

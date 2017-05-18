package thinking.chapter9;

interface I1 {
	void f();
}

interface I2{
	int f(int i);
}

interface I3{
	int f();
}

class C1 {
	public int f(){
		return 1;
	}
}

class C2 implements I1,I2 {
	public void f() {}
	
	//overload
	public int f(int i) {
		return 1;
	}
}

class C3 extends C1 implements I2 {
	//overload
	public int f(int i) {
		return 1;
	}
}

class C4 extends C1 implements I3 {
	//Identical,no problem
	public int f() {
		return 1;
	}
}

public class NameCollision {

}

//Method differ only by return type:
//!class C5 extends C1 implements I1{}
//!interface I4 extends I1,I3{}
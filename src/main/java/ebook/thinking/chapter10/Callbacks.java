package ebook.thinking.chapter10;
//An example for using callback method
interface Incrementable{
	void increment();
}

class Callee1 implements Incrementable {
	private int i = 0;
	public void increment() {
		i++;
		System.out.println("Callee1:" + i + "");
	}
}

class MyIncrement {
	public void increment() {
		System.out.println("Other operation.");
	}
	public static void f(MyIncrement mi) {
		mi.increment();
	}
}

class Callee2 extends MyIncrement {
	private int i = 0;
	public void increment() {
		super.increment();
		i++;
		System.out.println("Callee2:" + i + "");
	}

	private class Closure implements Incrementable {
		public void increment() {
			Callee2.this.increment();
		}
	}

	Incrementable getCallbackReference() {
		return new Closure();
	}
}

class Caller {
	private Incrementable callbackReference;
	Caller(Incrementable cbh) {
		callbackReference = cbh;
	}
	void go() {
		callbackReference.increment();
	}
}


public class Callbacks {

	public static void main(String[] args) {
		Callee1 c1 = new Callee1();
		Caller caller1 = new Caller(c1);
		caller1.go();
		caller1.go();
		Callee2 c2 = new Callee2();
		Caller caller2 = new Caller(c2.getCallbackReference());
		MyIncrement.f(c2);
		caller2.go();
		caller2.go();
	}
}

package ebook.thinking.chapter10;

class Base {
	public int i;
	public Base(int i) {
		this.i = i;
	}

	public void f() {}
}

public class Practice15 {

	public Base getBase(int i) {
		return new Base(i) {
			public void f(){
				System.out.println("f()");
			}
		};
	}

	public static void main(String[] args) {
		Practice15 p = new Practice15();
		Base b = p.getBase(1);
		b.f();
	}
}

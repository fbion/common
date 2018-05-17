package ebook.thinking.chapter10;

interface U {
	void f();
	void g();
	void h();
}

class A {
	public U getU() {
		return new U() {
			public void f() {
				System.out.println("f()");
			}
			public void g() {
				System.out.println("g()");
			}
			public void h() {
				System.out.println("h()");
			}
		};
	}
}


public class Practice23 {
	public static final int LNGTH = 10;
	public U[] uArray = new U[LNGTH];
	public int n = 0;

	public void add(U u) {
		if(n < uArray.length){
			uArray[n++] = u;
		}
	}

	public void clear() {
		for(int i = 0; i < n; i++){
			uArray[i] = null;
		}
		System.out.println("clear");
	}

	public void iterator() {
		for(int i = 0; i < n; i++){
			uArray[i].f();
			uArray[i].g();
			uArray[i].h();
		}
	}

	public static void main(String[] args) {
		A[] aArray = new A[]{new A(),new A(),new A() ,new A(),new A()};
		Practice23 p = new Practice23();
		for (A a : aArray) {
			p.add(a.getU());
		}

		p.iterator();

		p.clear();
	}
}

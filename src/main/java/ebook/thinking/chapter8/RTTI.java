package ebook.thinking.chapter8;

class Useful {
	public void f() {}
	public void g() {}
}

class MoreUseful extends Useful {
	public void f() {}
	public void g() {}
	public void h() {}
	public void i() {}
	public void j() {}

}

public class RTTI {

	public static void main(String[] args) {
		Useful[] u = {new Useful(),new MoreUseful()};
		u[0].f();
		u[1].g();
//		((MoreUseful)u[0]).j();//ClassCastException
		((MoreUseful)u[1]).j();
	}
}

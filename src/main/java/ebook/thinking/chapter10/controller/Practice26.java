package ebook.thinking.chapter10.controller;

class Outer {
	Outer(){}

	class InnerO {
		private int i = 0;
		InnerO(int i) {
			this.i = i;
			System.out.println(i + "");
		}
	}
}

public class Practice26 {
	class InnerP extends Outer.InnerO {
		InnerP(Outer o) {
			o.super(1);
		}
	}

	public static void main(String[] args) {

		(new Practice26()).new InnerP(new Outer());
	}
}

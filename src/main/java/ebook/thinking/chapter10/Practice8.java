package ebook.thinking.chapter10;

public class Practice8 {

	class Inner {
		private int i =1;
	}

	public static void main(String[] args) {
		Practice8 p = new Practice8();
		Practice8.Inner inner = p.new Inner();
		System.out.println(inner.i);
	}
}

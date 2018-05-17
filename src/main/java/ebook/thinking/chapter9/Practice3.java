package ebook.thinking.chapter9;

abstract class SuperPractice3 {
	SuperPractice3() {
		print();
	}
	abstract void print();
}

public class Practice3 extends SuperPractice3 {

	public int i = 2;

	public void print() {
		System.out.println(this.i + "");
	}

	public static void main(String[] args) {
		new Practice3();
	}
}

package ebook.thinking.chapter9;

abstract class SuperPractice4 {}

public class Practice4 extends SuperPractice4 {

	public void practice() {System.out.println("222");}

	public static Practice4 StaticPractice(SuperPractice4 sp) {
		System.out.println("111");
		return (Practice4)(sp);
	}

	public static void main(String[] args) {
		Practice4 p = Practice4.StaticPractice(new Practice4());
	}
}

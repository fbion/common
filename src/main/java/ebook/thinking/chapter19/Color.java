package ebook.thinking.chapter19;

import java.lang.reflect.Modifier;

public enum Color{
//	RED{
//		public void test() {
//			System.out.println("RED");
//		}
//	}, BLUE{
//		public void test() {
//			System.out.println("BLUE");
//		}
//	}, GREEN{
//		public void test() {
//			System.out.println("GREEN");
//		}
//	};

	RED, BLUE, GREEN;
//	public abstract void test();


	public static void main(String[] args) {
		System.out.println(Color.RED.getClass());
		System.out.println(Color.BLUE.getClass());
		System.out.println(Color.GREEN.getClass());
		System.out.println(RED instanceof Color);
		System.out.println(Modifier.toString(Color.class.getModifiers()));
	}
}

//class MyColor extends Color{}
package thinking.chapter8;

class StaticSuper{
	public static void staticGet(){
		System.out.println("super.staticGet");
	}
	
	public static void staticMethod(){
		System.out.println("static method");
	}
	
	public void dynamicGet(){
		System.out.println("super.dynamicGet");
	}
}

class StaticSub extends StaticSuper {
	public static void staticGet(){
		System.out.println("sub.staticGet");
	}
	
	public void dynamicGet(){
		System.out.println("sub.dynamicGet");
	}
}

public class StaticPolyPhism {

	public static void main(String[] args) {
		StaticSuper sup = new StaticSub();
		sup.staticGet();
		sup.dynamicGet();
		StaticSuper.staticGet();
		StaticSub.staticGet();
		StaticSuper.staticMethod();
		StaticSub.staticMethod();
	}
}

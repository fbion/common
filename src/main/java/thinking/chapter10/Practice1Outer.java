package thinking.chapter10;

public class Practice1Outer {

	class Inner {}
	
	public Inner getInner() {
		return new Inner();
	}
	
	static class StaticInner {}
	
	public static StaticInner getStaticInner() {
		return new StaticInner();
	}
	
	public static void main(String[] args) {
		Practice1Outer.Inner inner1 = new Practice1Outer().getInner();
		System.out.println(inner1.getClass().getName());
		
		Practice1Outer.Inner inner2 = (new Practice1Outer()).new Inner();
		System.out.println(inner2.getClass().getName());
		
		Practice1Outer.StaticInner staticInner1 = Practice1Outer.getStaticInner();
		System.out.println(staticInner1.getClass().getName());
		
		Practice1Outer.StaticInner staticInner2 = new Practice1Outer.StaticInner();
		System.out.println(staticInner2.getClass().getName());
	}
}

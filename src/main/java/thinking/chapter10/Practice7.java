package thinking.chapter10;

public class Practice7 {
	private int i = 1;
	
	private int getI(){
		return i;
	}
	class Inner {
		public void change(){
			i = 2;
			System.out.println(i + "");
			System.out.println(getI());
		}
	}
	
	public static void main(String[] args) {
		Practice7.Inner inner = (new Practice7()).new Inner();
		inner.change();
	}
}

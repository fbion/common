package thinking.chapter10;

public interface ClassInInterface {
	void howdy();
	
	public class Test implements ClassInInterface {

		@Override
		public void howdy() {
			System.out.println("howdy");
		}
		
		public static void main(String[] args) {
			new Test().howdy();
		}
	}
}

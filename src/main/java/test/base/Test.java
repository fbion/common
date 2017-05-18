package test.base;

class Father {
	
	public void father(int i){
		System.out.println(i + "");
	}
	public void father(String str){
		System.out.println(str);
	}
	
	public void father(float f){
		System.out.println(f + "");
	}
}

public class Test extends Father {

	private static int lang = 0;
	private static int java = 1;
	private static String String = "2";
	static String str = "1";
	static void f() {}
	@Override
	public void father(int i){
		System.out.println(true);
	}
	
	@Override
	public void father(float f){
		System.out.println(false);
	}
	
	public static void main(String[] args) {
		new Test().father(1);
		new Test().father("asdf");
		String s = "123";
		System.out.println(java + " " + lang + " " + String + "");
		System.out.println(s);

//		String str = "/home/ssgmr/text/";
//		Scanner scan = new Scanner(str);
//		scan.useDelimiter("/");
//		while(scan.hasNext()){
//			System.out.println(scan.next());
//		}
	}
}

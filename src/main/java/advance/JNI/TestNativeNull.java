package advance.JNI;

public class TestNativeNull {

	public static String str;
	
	public native static void test(Object obj);
	
	static{
		System.loadLibrary("test");
	}
	
	public static void main(String[] args) {
//		System.out.println((Integer)null);
		TestNativeNull.test(null);
		TestNativeNull.test(TestNativeNull.str);
		TestNativeNull.test(true);
		TestNativeNull.test(false);
	}
}

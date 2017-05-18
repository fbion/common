package designpattern.singleton;

public class LazySingleton2 {

	private static LazySingleton2 lazySingleton2 = null;
	
	private LazySingleton2() {}
	
	public static LazySingleton2 getInstance(){
		if(lazySingleton2 == null){
			synchronized(LazySingleton2.class){
				lazySingleton2 = new LazySingleton2();
			}
		}
		return lazySingleton2;
	}
}

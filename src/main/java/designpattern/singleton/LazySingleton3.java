package designpattern.singleton;

public class LazySingleton3 {

	private static LazySingleton3 lazySingleton3 = null;
	
	private LazySingleton3() {}
	
	public static LazySingleton3 getInstance(){
		synchronized(LazySingleton3.class){
			if(lazySingleton3 == null){
				lazySingleton3 = new LazySingleton3();
			}
		}
		return lazySingleton3;
	}
}

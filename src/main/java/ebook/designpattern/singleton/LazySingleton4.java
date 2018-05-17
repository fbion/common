package ebook.designpattern.singleton;

public class LazySingleton4 {

	private static LazySingleton4 lazySingleton4 = null;

	private LazySingleton4() {}

	public static LazySingleton4 getInstance(){
		if(lazySingleton4 == null){
			synchronized(LazySingleton4.class){
				if(lazySingleton4 == null){
					lazySingleton4 = new LazySingleton4();
				}
			}
		}
		return lazySingleton4;
	}
}

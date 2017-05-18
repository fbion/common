package designpattern.singleton.good;

public class InnerStaticSingleton {

	private static class SingletonHolder{
		private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
	}
	private InnerStaticSingleton(){}

	public static InnerStaticSingleton getInstance(){
		return SingletonHolder.INSTANCE;
	}
}

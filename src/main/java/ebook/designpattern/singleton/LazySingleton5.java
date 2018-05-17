package ebook.designpattern.singleton;

public class LazySingleton5 {

	private static volatile LazySingleton5 lazySingleton5 = null;

	private LazySingleton5() {}

	public static LazySingleton5 getInstance(){
		if(lazySingleton5 == null){
			synchronized(LazySingleton5.class){
				if(lazySingleton5 == null){
					lazySingleton5 = new LazySingleton5();
				}
			}
		}
		return lazySingleton5;
	}

private int flag = 0;

	public void setFlag(int flag){
		this.flag = flag;
	}
	public int getFlag(){
		return this.flag;
	}

	public static void main(String[] args) {
		LazySingleton5 instance1 = LazySingleton5.getInstance();
		System.out.println(instance1.getFlag());
		System.out.println(instance1.getClass().getName());
		LazySingleton5 instance2 = null;
		try {
			instance2 = (LazySingleton5)Class.forName("singleton.LazySingleton5").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		instance2.setFlag(1);
		System.out.println(instance1.getFlag());
		System.out.println(instance2.getFlag());
	}
}

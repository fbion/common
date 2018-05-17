package ebook.designpattern.singleton;

public class LazySingleton1 {

	private static LazySingleton1 lazySingleton1 = null;


	private LazySingleton1() {}

	public static LazySingleton1 getInstance(){
		if(lazySingleton1 == null){
			lazySingleton1 = new LazySingleton1();
		}
		return lazySingleton1;
	}

	private int flag = 0;

	public void setFlag(int flag){
		this.flag = flag;
	}
	public int getFlag(){
		return this.flag;
	}

	public static void main(String[] args) {
		LazySingleton1 instance1 = LazySingleton1.getInstance();
		System.out.println(instance1.getFlag());
		LazySingleton1 instance2 = null;
		try {
			instance2 = (LazySingleton1)Class.forName(instance1.getClass().getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		instance2.setFlag(1);
		System.out.println(instance1.getFlag());
		System.out.println(instance2.getFlag());
	}
}

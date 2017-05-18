package designpattern.singleton;

public class HungrySingleton {

	//
	private static HungrySingleton hungrySingleton = new HungrySingleton();
	
	private int flag = 0;
	
	private HungrySingleton(){}
	
	public static HungrySingleton getInstance(){
		return hungrySingleton;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public static void main(String[] args) {
		HungrySingleton singleton1 = HungrySingleton.getInstance();
		System.out.println(singleton1.getFlag());
		HungrySingleton singleton2 = null;
		try {
			singleton2 = (HungrySingleton) Class.forName(singleton1.getClass().getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		singleton2.setFlag(1);
		System.out.println(singleton1.getFlag());
		System.out.println(singleton2.getFlag());
	}
}

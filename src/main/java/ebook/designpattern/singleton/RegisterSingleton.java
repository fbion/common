package ebook.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;

public class RegisterSingleton {

	public static Map<String,RegisterSingleton> map = new HashMap<String,RegisterSingleton>();
	static {
		RegisterSingleton registerSingleton = new RegisterSingleton();
		map.put(registerSingleton.getClass().getName(), registerSingleton);
	}

	private RegisterSingleton() {}

	public static RegisterSingleton getInstance(String name){
		if(name == null){
			name = RegisterSingleton.class.getName();
			System.out.println("name == null" + "---->name=" + name);
		}
		if(map.get(name) == null){
			System.out.println("Null");
			try {
				map.put(name, (RegisterSingleton)Class.forName(name).newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			}catch (IllegalAccessException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return map.get(name);
	}

	public String about(){
		return "Hello,I am a RegisterSinleton.";
	}

	public static void main(String[] args) {
		RegisterSingleton registerSingleton = RegisterSingleton.getInstance(null);
		System.out.println(registerSingleton.about());
	}
}

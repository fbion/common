package thinking.chapter11;

import java.util.ArrayList;
import java.util.List;

class Apple {
	public static long counter;
	public final long id = counter++;
	public long id() {
		return id;
	}
}

class Orange {}

public class AppleAndOrangeWithoutGeneric {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List apples = new ArrayList();
		for(int i = 0; i < 3; i++){
			apples.add(new Apple());
		}
		for(int i = 0; i < apples.size(); i++){
			System.out.println(((Apple)apples.get(i)).id() + "");;
		}
	}
}

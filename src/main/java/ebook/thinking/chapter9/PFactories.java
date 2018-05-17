package ebook.thinking.chapter9;
//An example for Factory Pattern.

interface Service {
	void method1();
	void method2();
}

interface ServiceFactory {
	Service getService();
}

class Implementation1 implements Service {

	Implementation1() {}
	@Override
	public void method1() {
		System.out.println("Implementation1.Method1");
	}

	@Override
	public void method2() {
		System.out.println("Implementation1.Method2");
	}

}

class ImplementationFactory1 implements ServiceFactory {
	public Service getService() {
		return new Implementation1();
	}
}

class Implementation2 implements Service {

	Implementation2() {}
	@Override
	public void method1() {
		System.out.println("Implementation2.Method1");
	}

	@Override
	public void method2() {
		System.out.println("Implementation2.Method2");
	}

}

class ImplementationFactory2 implements ServiceFactory {
	public Service getService() {
		return new Implementation2();
	}
}

public class PFactories {

	public static void serviceConsumer(ServiceFactory fact){
		Service s = fact.getService();
		s.method1();
		s.method2();
	}

	public static void main(String[] args) {
		serviceConsumer(new ImplementationFactory1());
		serviceConsumer(new ImplementationFactory2());
	}
}

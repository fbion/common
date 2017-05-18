package designpattern.strategy.example1;

public class ZhaoYun {

	public static void main(String[] args) {
		Context context;
		
		System.out.println("-----刚到吴国时拆第一个-----");
		context = new Context(new BackDoor());
		context.operate();
		System.out.println("\n\n\n\n");
		
		System.out.println("-----刘备乐不思蜀了，拆第二个-----");
		context.setStrategy(new GiveGreenLight());
		context.operate();
		System.out.println("\n\n\n\n");
		
		System.out.println("-----孙权的兵追了，拆第三个-----");
		context.setStrategy(new BlockEnemy());
		context.operate();
	}
}

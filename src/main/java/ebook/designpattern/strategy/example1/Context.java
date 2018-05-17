package ebook.designpattern.strategy.example1;

public class Context {

	public IStrategy strategy;

	public Context(IStrategy strategy){
		this.strategy = strategy;
	}

	public void operate(){
		this.strategy.operate();
	}

	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}
}

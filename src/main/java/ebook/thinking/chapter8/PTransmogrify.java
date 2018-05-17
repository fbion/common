package ebook.thinking.chapter8;
/**
 * An example for State pattern.
 * @author Administrator
 *
 */

class Actor {
	public void act() {}
}

class HappyActor extends Actor {
	public void act() {
		System.out.println("HappyActor");
	}
}

class SadActor extends Actor {
	public void act() {
		System.out.println("SadActor");
	}

}

class Stage {
	private Actor actor = new HappyActor();

	public void change() {
		actor = new SadActor();
	}
	public void performPlay() {
		actor.act();
	}
}

public class PTransmogrify {
	public static void main(String[] args) {
		Stage stage = new Stage();
		stage.performPlay();
		stage.change();
		stage.performPlay();
	}
}

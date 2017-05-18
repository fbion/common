package thinking.chapter8;

class Grain {
	public String toString() {
		return "Grain";
	}
}

class Wheat extends Grain {
	public String toString() {
		return "Wheat";
	}
}

class Mill{
	Grain Process() {
		return new Grain();
	}
}

class WheatMill extends Mill {
	
	@Override
    Wheat Process() {
		return new Wheat();
	}
}

public class CovariantReturn {

	public static void main(String[] args) {
		Mill m = new Mill();
		Grain g = m.Process();
		System.out.println(g);
		m = new WheatMill();
		g = m.Process();
		System.out.println(g);
	}
}

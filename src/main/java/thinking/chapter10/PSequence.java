package thinking.chapter10;
//An example for Iterator Pattern.

interface Selector {
	boolean end();
	Object current();
	void next();
}

public class PSequence {
	private Object[] items;
	private int next = 0;
	
	public PSequence(int size) {
		items = new Object[size];
	}
	public void add(Object x) {
		if(next < items.length){
			items[next++] = x;
		}
	}
	private class SequenceSelector implements Selector {
		private int i = 0;
		
		@Override
		public boolean end() {
			return i == items.length;
		}

		@Override
		public Object current() {
			return items[i];
		}

		@Override
		public void next() {
			if(i < items.length){
				i++;
			}
		}
	}
	
	public Selector selector() {
		return new SequenceSelector();
	}
	
	public static void main(String[] args) {
		PSequence pSequence = new PSequence(10);
		for(int i = 0;i < 10; i++){
			pSequence.add(Integer.toString(i));
		}
		Selector selector = pSequence.selector();
		while(!selector.end()){
			System.out.println(selector.current());
			selector.next();
		}
		
		PSequence pSequence2 = new PSequence(10);
		for(int i = 0;i < 10; i++){
			pSequence2.add(new MyString(Integer.toString(i)));
		}
		Selector selector1 = pSequence.selector();
		while(!selector1.end()){
			System.out.println(selector1.current());
			selector1.next();
		}
	}
}


class MyString{
	private String str;
	
	public MyString(String str) {
		this.str = str;
	}
	public String toString() {
		return str;
	}
}
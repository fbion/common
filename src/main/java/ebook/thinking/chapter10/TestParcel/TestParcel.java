package ebook.thinking.chapter10.TestParcel;

class Parcel4 {
	private class PContents implements Contents {
		private int i = 11;
		public int value() {
			return i;
		}
	}
	protected class PDestination implements Destination {
		private String label;
		public PDestination(String whereTo) {
			label = whereTo;
		}
		public String readLabel() {
			return label;
		}
	}
	public Destination destination(String s) {
		return new PDestination(s);
	}
	public Contents contents(){
		return new PContents();
	}
}

public class TestParcel {

	public static void main(String[] args) {
		Parcel4 p = new Parcel4();
		Contents c = p.contents();
		Destination d = p.destination("Nanjing");
		//Illegal---can't access pviate class;
		//!Parcel4.PContents pc = p.new PContents();
		Parcel4.PDestination pd = p.new PDestination("Beijing");
		System.out.println(c.getClass().getName());
		System.out.println(d.getClass().getName());
	}
}

package ebook.thinking.chapter10;

import ebook.thinking.chapter10.TestParcel.Destination;

public class Parcel9 {

	public Destination destination(final String dest) {//final is acquired.
		return new Destination() {
			private String label = dest;
			public String readLabel() {
				return label;
			}
		};
	}
	public static void main(String[] args) {
		Parcel9 p = new Parcel9();
		Destination d = p.destination("Nanjing");
	}
}

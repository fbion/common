package ebook.thinking.chapter10;

import ebook.thinking.chapter10.TestParcel.Destination;

public class Parcel5 {

	public Destination destination(String whereTo) {
		class PDestination implements Destination {
			private String label;
			public PDestination(String whereTo) {
				label = whereTo;
			}
			public String readLabel() {
				return label;
			}
		}

		return new PDestination(whereTo);
	}

	public static void main(String[] args) {
		Parcel5 p = new Parcel5();
		Destination d = p.destination("Nanjing");
		System.out.println(d.getClass().getName());
	}
} /**output:
  * thinking.chapter10.Parcel5$1PDestination
*/

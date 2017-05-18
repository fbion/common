package thinking;

public class TestBlankFinal {

	public final int i;
	public TestBlankFinal(int i){
		this.i = i;
		System.out.println(this.i + "");
	}
	
	public TestBlankFinal(){
		this.i=0;//Blank final field must be initialized.
	}
	
	public static void main(String[] args) {
		new TestBlankFinal(2);
	}
}

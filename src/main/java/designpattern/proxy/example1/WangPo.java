package designpattern.proxy.example1;

public class WangPo implements KindWoman {
	private KindWoman kindWomandman;
	
	public WangPo(){
		this.kindWomandman = new PanJinlian();
	}

	public WangPo(KindWoman kindWomandman){
		this.kindWomandman = kindWomandman;
	}
	
	@Override
	public void MakeEyesWithMan() {
		this.kindWomandman.MakeEyesWithMan();
	}

	@Override
	public void HappyWithMan() {
		this.kindWomandman.HappyWithMan();
	}
}

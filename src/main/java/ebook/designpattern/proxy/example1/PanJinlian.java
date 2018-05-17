package ebook.designpattern.proxy.example1;

public class PanJinlian implements KindWoman {

	@Override
	public void MakeEyesWithMan() {
		System.out.println("潘金莲抛媚眼");
	}

	@Override
	public void HappyWithMan() {
		System.out.println("潘金莲在和男人做那个......");
	}

}

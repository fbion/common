package test.code;

import java.util.regex.Pattern;

public class TestChinese {

	public static void main(String[] args) {
		System.out.println("抢购".matches("\\u62a2\\u8d2d"));
		String[] str = new String[16];
		for(int i = 0; i < 10; i++) {
			str[i] = i + "";
		}
		str[10] = "a";
		str[11] = "b";
		str[12] = "c";
		str[13] = "d";
		str[14] = "e";
		str[15] = "f";
		for(int i = 4; i <= 9; i++) {
			for(int j = 0; j < 16; j++) {
				for(int k = 0; k < 16; k++) {
					for(int l = 0; l < 16; l++) {
						String temp = "\\u" + str[i] + str[j] + str[k] + str[l];
						if("购".matches(temp)) {
							System.out.println(temp);	//\u62a2\u8d2d
						}
					}
				}
			}
		}
	}
}

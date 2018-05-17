package ebook.thinking.chapter19;

import ebook.thinking.chapter15.generate.Generator;

import java.util.Random;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：EnumImplementation</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/6 15:46<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/6 15:46<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class EnumImplementation {
    public static <T> void printNext(Generator<T> rg) {
        System.out.println(rg.next() + ", ");
    }


    public static void main(String[] args) {
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++) {
            printNext(cc);
        }
    }
}

enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

    private Random random= new Random(47);

    @Override
    public CartoonCharacter next() {
        return values()[random.nextInt(values().length)];
    }
}

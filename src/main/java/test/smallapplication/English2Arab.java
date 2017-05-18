package test.smallapplication;

/**
 * Created by Administrator on 2016/5/28.
 */
public class English2Arab {
    static String[] bigNumbers = {"billion", "million", "thousand", "hundred"};
    public static long convert(String str) {
        //去掉字符串头部多余空白符、词尾非字母字符
        // 将单词之间单空白符替换为一个空格，把and加前后空格替换为一个空格
        //全部改为小写，避免billion,million,thousand,hundred含大写字母导致错误
        str = str.replaceAll("^\\s+", "").replaceAll("\\W+$", "").replaceAll("\\b\\s+\\b", " ").replaceAll(" and ", " ").toLowerCase();
        long result = 0;
        String left = str;
        for (int i = 0; i < 3; i++) {
            int index = left.indexOf(bigNumbers[i]);
            if(index >= 0) {
                result += convert1(left.substring(0, index)) * Figures.valueOf(bigNumbers[i].toUpperCase()).getValue();
                left = left.substring(index + bigNumbers[i].length() + 1);
            }
        }
        return result + convert1(left);
    }

    private static long convert1(String str) {
        long result = 0;
        String[] numbers;
        int indexOfHundred = str.indexOf(bigNumbers[3]);
        String left = str;
        if(indexOfHundred >= 0) {
            numbers = str.substring(0, indexOfHundred).split(" ");
            left = str.substring(indexOfHundred + bigNumbers[3].length() + 1);
            result += caculate(numbers) * Figures.valueOf(bigNumbers[3].toUpperCase()).getValue();
        }
        result += caculate(left.split(" "));
        return result;
    }

    public static long caculate(String[] numbers) {
        long result = 0;
        for (String number : numbers) {
            //如果字符串里有非数字单词或拼写错误，将会在这里抛出异常
            result += Figures.valueOf(number.toUpperCase()).getValue();
//            System.out.println(number + " = " + Figures.valueOf(number.toUpperCase()).getValue());
        }
        return result;
    }
    public static void main(String[] args) {
        String str = " nine hundred and eighty seven million six hundred    and fifty four thousand three hundred and twelve.";
        String str1 = "a thousand one Hundred and twenty";
        String str2 = "a hundred and fifty eight billion a thousand one hundred and twenty";
        int maxLength = str.length() > str1.length() ? (str.length() > str2.length() ? str.length() : str2.length()) : (str1.length() > str2.length() ? str1.length() : str2.length());
        System.out.println(String.format("%" + maxLength + "s", str) + " : " +convert(str));
        System.out.println(String.format("%" + maxLength + "s", str1) + " : " +convert(str1));
        System.out.println(String.format("%" + maxLength + "s", str2) + " : " +convert(str2));
    }
}
enum Figures{
    ZERO(0), A(1), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
    TEN(10), ELEVEN(11), TWELVE(12), THRETEEN(13), FOURTEEN(14), FIFTEEN(15), SIXTEEN(16), SEVENTEEN(17), EIGHTEEN(18), NINTEEN(19),
    TWENTY(20), THIRTY(30), FOUTY(40), FIFTY(50), SIXTY(60), SEVENTY(70), EIGHTY(80), NINTY(90),
    HUNDRED(100), THOUSAND(1000), MILLION(1000000), BILLION(1000000000);
    private long value;
    Figures(long value) {
        this.value = value;
    }
    public long getValue() {
        return value;
    }
}
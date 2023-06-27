package work.hdjava.sample.common.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

    /**
     * 加法运算
     */
    public static String addition(String one, String two){
        BigDecimal decimal_one = new BigDecimal(one);
        BigDecimal decimal_two = new BigDecimal(two);
        return decimal_one.add(decimal_two).stripTrailingZeros().toPlainString();
    }

    /**
     * 减法运算
     */
    public static String subtract(String one, String two){
        BigDecimal decimal_one = new BigDecimal(one);
        BigDecimal decimal_two = new BigDecimal(two);
        return decimal_one.subtract(decimal_two).stripTrailingZeros().toPlainString();
    }

    /**
     * 乘法运算
     */
    public static String multiply(String one, String two){
        BigDecimal decimal_one = new BigDecimal(one);
        BigDecimal decimal_two = new BigDecimal(two);
        return decimal_one.multiply(decimal_two).stripTrailingZeros().toPlainString();
    }

    /**
     * 除法运算
     */
    public static String divide(String one, String two){
        BigDecimal decimal_one = new BigDecimal(one);
        BigDecimal decimal_two = new BigDecimal(two);
        return decimal_one.divide(decimal_two,2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     *  舍弃小数点后面数
     */
    public static String whole(String one){
        int floor = (int) Math.floor(Double.parseDouble(one));
        return String.valueOf(floor);
    }
    /**
     * BigDecimal比较大小
     * //前提为a、b均不能为null
     * if(a.compareTo(b) == -1){
     *     System.out.println("a小于b");
     * }
     *
     * if(a.compareTo(b) == 0){
     *     System.out.println("a等于b");
     * }
     *
     * if(a.compareTo(b) == 1){
     *     System.out.println("a大于b");
     * }
     *
     * if(a.compareTo(b) > -1){
     *     System.out.println("a大于等于b");
     * }
     *
     * if(a.compareTo(b) < 1){
     *     System.out.println("a小于等于b");
     * }
     */
}

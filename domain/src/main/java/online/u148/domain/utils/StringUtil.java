package online.u148.domain.utils;


import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: dk
 * @date:2016/3/21
 * @email:dk-26@163.com
 */
public class StringUtil {
    public static boolean isEmpty(CharSequence str) {
        return isNull(str) || str.length() == 0;
    }

    public static boolean isEmpty(Object[] os) {
        return isNull(os) || os.length == 0;
    }

    public static boolean isEmpty(Collection<?> l) {
        return isNull(l) || l.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> m) {
        return isNull(m) || m.isEmpty();
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    /**
     * 手机号验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 验证身份证号是否符合规则
     * @param text 身份证号
     * @return
     */
    public static boolean checkIdValidation(String text) {
        //具体验证规则 ----> http://jingyan.baidu.com/article/d5a880eb974b6513f147ccec.html?qq-pf-to=pcqq.c2c
        String regex = "[0-9]{6}[12][09][0-9]{2}(([0][1-9])|([1][0-2]))(([0][1-9])|([12][0-9])|([3][01]))[0-9]{3}[0-9Xx]$";

        if (!text.matches(regex)){
            return false;
        }

        //先取出每一位的数字
        String[] number = new String[text.length() -1];
        for (int i = 0; i< text.length() -1; i++) {
            number[i] = text.substring(i,i+1);
        }

        //对取出的数字进行二次处理
        String[] number2 = new String[]{
                String.valueOf(Integer.parseInt(number[0])*7),
                String.valueOf(Integer.parseInt(number[1])*9),
                String.valueOf(Integer.parseInt(number[2])*10),
                String.valueOf(Integer.parseInt(number[3])*5),
                String.valueOf(Integer.parseInt(number[4])*8),
                String.valueOf(Integer.parseInt(number[5])*4),
                String.valueOf(Integer.parseInt(number[6])*2),
                String.valueOf(Integer.parseInt(number[7])*1),
                String.valueOf(Integer.parseInt(number[8])*6),
                String.valueOf(Integer.parseInt(number[9])*3),
                String.valueOf(Integer.parseInt(number[10])*7),
                String.valueOf(Integer.parseInt(number[11])*9),
                String.valueOf(Integer.parseInt(number[12])*10),
                String.valueOf(Integer.parseInt(number[13])*5),
                String.valueOf(Integer.parseInt(number[14])*8),
                String.valueOf(Integer.parseInt(number[15])*4),
                String.valueOf(Integer.parseInt(number[16])*2),
        };

        //求和
        int sum = 0;
        for (int i =0; i< number2.length; i ++) {
            sum = sum + Integer.parseInt(number2[i]);
        }

        //取余
        int idCardMod = sum % 11;

        //不同余数对应不同的最后一位
        String lastDYStr;
        switch (idCardMod) {
            case 0 :
                lastDYStr = "1";
                break;
            case 1 :
                lastDYStr = "0";
                break;
            case 2 :
                lastDYStr = "x";
                break;
            case 3 :
                lastDYStr = "9";
                break;
            case 4 :
                lastDYStr = "8";
                break;
            case 5 :
                lastDYStr = "7";
                break;
            case 6 :
                lastDYStr = "6";
                break;
            case 7 :
                lastDYStr = "5";
                break;
            case 8 :
                lastDYStr = "4";
                break;
            case 9 :
                lastDYStr = "3";
                break;
            case 10 :
                lastDYStr = "2";
                break;
            default:
                lastDYStr = null;
                break;
        }

        if (isEmpty(lastDYStr)) {
            return false;
        }

        if (text.substring(text.length()-1, text.length()).toLowerCase().equals(lastDYStr.toLowerCase())){
            return true;
        }else {
            return false;
        }
    }

}

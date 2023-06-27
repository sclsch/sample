package work.hdjava.sample.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author：my
 * @date：2020/7/1 23:31
 * @describe：
 */
@Slf4j
public class TimeUtil {

    /**
     * @author：my
     * @date：2020/10/22 9:01
     * @describe：获取当前时间
     */
    public static String currentTime(){
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
         String time = format.format(Calendar.getInstance().getTime());
         return time;
    }

    /**
     * @author：my
     * @date：2020/7/1 23:31
     * @describe：获取：年
     */
    public static String year() {
        //获取东八区时间
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        //获取年
        int year = c.get(Calendar.YEAR);
        return year + "";
    }

    /**
     * @author：my
     * @date：2020/7/1 23:31
     * @describe：获取：年-月
     */
    public static String month() {
        //获取东八区时间
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        //获取年
        int year = c.get(Calendar.YEAR);
        //获取月份，0表示1月份
        int month = c.get(Calendar.MONTH) + 1;
        return year + "-" + month;
    }

    /**
     * @author：my
     * @date：2020/7/1 23:31
     * @describe：获取：年-月-日
     */
    public static String when() {
        //获取东八区时间
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        //获取年
        int year = c.get(Calendar.YEAR);
        //获取月份，0表示1月份
        int month = c.get(Calendar.MONTH) + 1;
        //获取当前天数
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }


    /**
     * 获取指定年月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定年月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * @author：my
     * @date：2020/7/20 8:44
     * @describe：获取传入日期所在月的第一天
     */
    public static String getFirstDayDateOfMonth(final Date date) {

        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.DAY_OF_MONTH, last);

        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());

    }

    /**
     * @author：my
     * @describe：获取传入日期所在月的最后一天
     */
    public static String getLastDayOfMonth(final Date date) {

        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.DAY_OF_MONTH, last);

        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());

    }

    /**
     * @author：my
     * @describe：获取传入日期所在年的第一天
     */
    public static String getFirstDayDateOfYear(final Date date) {

        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        final int last = cal.getActualMinimum(Calendar.DAY_OF_YEAR);

        cal.set(Calendar.DAY_OF_YEAR, last);


        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());

    }

    /**
     * @author：my
     * @describe：获取传入日期所在年的最后一天
     */
    public static String getLastDayOfYear(final Date date) {

        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        final int last = cal.getActualMaximum(Calendar.DAY_OF_YEAR);

        cal.set(Calendar.DAY_OF_YEAR, last);

        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());

    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date StrToDate(String type, String str) {

        SimpleDateFormat format = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @author：my
     * @describe：当前日期减去 time = time前的日期
     */
    public static String subtraction(Integer time) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(now.getTime());
    }

    /**
     * @author：my
     * @describe：当前日期加 time = time后的日期
     */
    public static String addition(Integer time) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(cal.getTime());
    }

    public static String getNetworkTime() {
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate();
            Date date = new Date(dateL);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return when();
        } catch (IOException e) {
            e.printStackTrace();
            return when();
        }
    }
    public static Timestamp  str2timeStamp(String timeStr,String pattern)   {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            log.info("str2timeStamp 时间转换错误：{}",e);
        }
        return new Timestamp(date.getTime());
    }
    public static Timestamp str2timeStamp(String timeStr)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            log.info("str2timeStamp 时间转换错误：{}",e);
        }
        return new Timestamp(date.getTime());
    }



}

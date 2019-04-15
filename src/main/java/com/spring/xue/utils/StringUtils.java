package com.spring.xue.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
public class StringUtils {
    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }


    public static boolean isBlank(String str)
    {
        if(str == null || str.trim().equals(""))
        {
            return true;
        }
        return false;
    }

    public static boolean isNull(Object obj)
    {
        if(obj == null)
        {
            return true;
        }else{
            if(isNull(obj.toString())){
                return true;
            }
        }
        return false;
    }

    public static boolean isNaObj(Object obj)
    {
        if(obj == null)
        {
            return true;
        }else{
            if(isNull(obj.toString())){
                return true;
            }
        }
        return false;
    }

    public static boolean isNull(String str)
    {
        if(str == null || str.trim().equals("") || "NULL".equals(str)
                || "null".equals(str) || "undefined".equals(str) || "undefine".equals(str))
        {
            return true;
        }
        return false;
    }

    public static String utf8Encode(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        String xmString = "";
        String xmUTF8="";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmUTF8 = URLEncoder.encode(xmString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("ERROR:" + e);
        }
        return xmUTF8;
    }

    public static boolean isListInclude(List<String> list,String str){
        boolean flag = false;
        if(list.size() == 0){
            flag = false;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).equals(str)){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }


    public static boolean inArray(String str,String[] strArray)
    {
        if(str == null || strArray == null || strArray.length == 0)
        {
            return false;
        }
        for(String curStr : strArray)
        {
            if(str.equals(curStr))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Description: 获取当前日期
     *
     * @return YYYYMMDD
     */
    public static String getDate(String splitStr)
    {
        String splitChar = "";
        if(splitStr != null){
            splitChar = splitStr;
        }
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        StringBuilder yyyymmdd = new StringBuilder("");
        yyyymmdd.append(year);
        yyyymmdd.append(splitChar);
        if(month < 10){
            yyyymmdd.append("0").append(month);
        }else{
            yyyymmdd.append(month);
        }
        yyyymmdd.append(splitChar);
        if(day < 10){
            yyyymmdd.append("0").append(day);
        }else{
            yyyymmdd.append(day);
        }
        return yyyymmdd.toString();
    }

    /**
     * Description: 获取当前日期
     *
     * @return YYYY-MM-DD
     */
    public static String getCurrentDate(String formatType){
        if(isBlank(formatType)){
            formatType = "yyyy-MM-dd";
        }
        String currentDate = new java.text.SimpleDateFormat(formatType).format(new java.util.Date());
        return currentDate;
    }

    /**
     * Description: 获取当前日期时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTime(String formatType){
        if(isBlank(formatType)){
            formatType = "yyyy-MM-dd HH:mm:ss";
        }
        String currentDate = new java.text.SimpleDateFormat(formatType).format(new java.util.Date());
        return currentDate;
    }

    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    /**
     * Description: 获取当前月的第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getFirstDayOfCurrentMonth(String formatType){
        if(isBlank(formatType)){
            formatType = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
    }

    /**
     * Description: 获取昨天所在月份的第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getFirstDayOfMonthByYesterday(String formatType){
        if(isBlank(formatType)){
            formatType = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.DAY_OF_MONTH,-1);//当前日期减一天，即为昨天
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
    }

    /**
     * Description: 获取昨天
     *
     * @return YYYY-MM-DD
     */
    public static String getYesterday(String formatType){
        if(isBlank(formatType)){
            formatType = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.DAY_OF_MONTH,-1);//当前日期减一天，即为昨天
        String yesterday = format.format(cal_1.getTime());
        return yesterday;
    }

    /**
     * Description: 获取上月最后一天
     *
     * @return YYYY-MM-DD
     */
    public static String getTheLastDayOfLastMonth(String formatType){
        if(isBlank(formatType)){
            formatType = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置日期为当前月第一天
        cal_1.add(Calendar.DAY_OF_MONTH,-1);//当前日期减一天，即为昨天，即为上月最后一天
        String theLastDayOfLastMonth = format.format(cal_1.getTime());
        return theLastDayOfLastMonth;
    }

    /**
     * Description: 获取上月第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getTheFirstDayOfLastMonth(String formatType){
        if(isBlank(formatType)){
            formatType = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置日期为当前月第一天
        cal_1.add(Calendar.DAY_OF_MONTH,-1);//当前日期减一天，即为昨天，即为上月最后一天
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为上月第一天
        String theFirstDayOfLastMonth = format.format(cal_1.getTime());
        return theFirstDayOfLastMonth;
    }

    /**
     * Description: 获取上月第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getTheFirstDayOfLastMonthTemp(String formatType){
        if(isBlank(formatType)){
            formatType = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置日期为当前月第一天
        cal_1.add(Calendar.DAY_OF_MONTH,-1);//当前日期减一天，即为昨天，即为上月最后一天
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为上月第一天
        String theFirstDayOfLastMonth = format.format(cal_1.getTime());
        return theFirstDayOfLastMonth;
    }

    /**
     * Description: 获取当日是该月的第几天
     *
     * @return 当日是该月的第几天
     */
    public static int getDayOfCurrentMonth(){
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        int dayOfMonth = cal_1.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth;
    }

    /**
     * Description: 获取当日是该年的第几天
     *
     * @return 当日是该年的第几天
     */
    public static int getDayOfCurrentYear(){
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        int dayOfYear = cal_1.get(Calendar.DAY_OF_YEAR);
        return dayOfYear;
    }

    /**
     * Description: 获取当前日期
     *
     * @return YYYYMMDDHHMMSS
     */
    public static String getCurrentTime(){
        String currentTime = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        return currentTime;
    }

    /**
     * Description: 获取当前日期
     *
     * @return YYYYMMDDHHMMSSsss
     */
    public static String getCurrentTimeSSS(){
        String currentTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
        return currentTime;
    }


    /**
     * Description: 如果小数点后面是0，则把0去掉
     * @param   str    被格式化的参数
     * @return 被格式化后的数据
     */
    public static String removeTailZero(String str) {
        if(str != null){
            int i,len = str.length();
            if(str.indexOf(".") >= 0){
                for(i = 0; i < len; i++){
                    if(str.charAt(len - 1 - i) != '0'){
                        break;
                    }
                }
                if(str.charAt(len - i - 1) == '.'){
                    return str.substring(0, len - i - 1);
                }else{
                    return str.substring(0, len - i);
                }
            }else{
                return str;
            }
        }else{
            return null;
        }

    }

    /**
     * Description: 获取当前日期N天后的日期
     * @param   addDays    当前日期往后推的天数
     * @return 获取当前日期N天后的日期
     */
    public static String getDatebyAddDays(int addDays){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,addDays);//把日期往后增加N天.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推N天的结果
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * Description: 获取指定日期N天后的日期
     * @param   addDays    当前日期往后推的天数
     * @return 获取指定日期N天后的日期
     */
    public static String getDatebyAddDays(String setDate,int addDays){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
        String newDate = "";
        try {
            date = sdf.parse(setDate);
            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            cl.add(Calendar.DATE, addDays);
            newDate = sdf.format(cl.getTime());
        } catch (Exception e) {
            log.error("ERROR:" + e);
        }
        return newDate;
    }

    /**
     * Description: 计算两个日期的差
     * @param   smdate    比较日期
     * @param   bdate     被比较日期
     * @return 比较结果(被比较日期 - 比较日期)
     */
    public static int daysBetween(String smdate,String bdate){
        long between_days = 0;
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            between_days=(time2-time1)/(1000*3600*24);
        }catch(Exception e){
            log.error("ERROR:" + e);
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String formatDeskJobCode(int currentNo, int length){
        StringBuilder jobCode = new StringBuilder();
        int maxCode = 1;
        for(int k = 1; k < length + 1; k++){
            maxCode = maxCode * 10;
            if(currentNo < maxCode){
                for(int i = 0; i < length - k; i++){
                    jobCode.append("0");
                }
                break;
            }
        }
        jobCode.append(currentNo);
        return jobCode.toString();
    }

    public static String percentFormat(Double targetData,int digit){
        String formatData = "#.";
        if(digit > 0){
            for(int i = 0; i < digit; i++){
                formatData = formatData + "#";
            }
        }
        targetData = targetData * 100;
        java.text.DecimalFormat df = new java.text.DecimalFormat(formatData);
        return String.valueOf(df.format(targetData));
    }

    public static String gainsFormat(Double targetData,int digit){
        String formatData = "#.";
        if(digit > 0){
            for(int i = 0; i < digit; i++){
                formatData = formatData + "#";
            }
        }
        java.text.DecimalFormat df = new java.text.DecimalFormat(formatData);
        return String.valueOf(df.format(targetData));
    }

    /**
     * Description: 对参数进行校验，是否满足格式(数字+字母+下划线)
     * @param str 请求参数
     * @return bl 参数是否满足格式
     */
    public static boolean isSeatString(String str){
        Boolean bl = false;
        Pattern pt = Pattern.compile("^[&_a-zA-Z][0-9a-zA-Z_]+$");
        Matcher mt = pt.matcher(str);
        if(mt.matches()){
            bl = true;
        }
        return bl;
    }

    /**
     * Description: 对参数进行校验，是否满足格式(数字+字母)
     * @param str 请求参数
     * @return 参数是否满足格式
     */
    public static boolean isLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }

    /**
     * Description: 对参数进行校验，是否满足格式(数字)
     * @param str 请求参数
     * @return 参数是否满足格式
     */
    public static boolean isDigit(String str) {
        String regex = "^[0-9]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }

    /**
     * Description: 对参数进行校验，是否满足格式(手机号)
     * @param str 请求参数
     * @return bl 参数是否满足格式
     */
    public static boolean isMoboleNum(String str){

        if(str != null){
            if(str.length() == 11){
                Boolean bl = false;
                Pattern pt = Pattern.compile("^[0-9]+$");
                Matcher mt = pt.matcher(str);
                if(mt.matches()){
                    bl = true;
                }
                return bl;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * Description: 返回Date类型日期
     * @param planDay 计划日期，如：yyyy-MM-dd
     * @return
     */
    public static Date getDatebyPlanMonth(String planDay){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(planDay);
        } catch (ParseException e) {
            log.error("ERROR:" + e);
        }
        return date;
    }

    /**
     * Description: 参数月份第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getCurrentMonth(String formatType){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(formatType);
        } catch (ParseException e) {
            log.error("ERROR:" + e);
            return "";
        }
        Calendar cal_1 =Calendar.getInstance();
        cal_1.setTime(date);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置日期为当前月第一天
        String theFirstDayOfMonth = format.format(cal_1.getTime());
        return theFirstDayOfMonth;
    }

    /**
     * Description: 参数上月份第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getFixMonth(String formatType){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(formatType);
        } catch (ParseException e) {
            log.error("ERROR:" + e);
            return "";
        }
        Calendar cal_1 =Calendar.getInstance();
        cal_1.setTime(date);
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置日期为当前月第一天
        String theFirstDayOfMonth = format.format(cal_1.getTime());
        return theFirstDayOfMonth;
    }

    /**
     * Description: 参数上季度第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getFixQuarter(String formatType){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(formatType);
        } catch (ParseException e) {
            log.error("ERROR:" + e);
            return "";
        }
        Calendar cal_1 =Calendar.getInstance();
        cal_1.setTime(date);
        cal_1.add(Calendar.MONTH, -3);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置日期为当前月第一天
        String theFirstDayOfMonth = format.format(cal_1.getTime());
        return theFirstDayOfMonth;
    }


    /**
     * Description: 参数下月份第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getNextMonth(String formatType){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(formatType);
        } catch (ParseException e) {
            log.error("ERROR:" + e);
            return "";
        }
        Calendar cal_1 =Calendar.getInstance();
        cal_1.setTime(date);
        cal_1.add(Calendar.MONTH, 1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置日期为当前月第一天
        String theNextDayOfMonth = format.format(cal_1.getTime());
        return theNextDayOfMonth;
    }

    /**
     * Description: 参数当前季度第一个月的第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getThisQuarter(String formatType){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(formatType);
        } catch (ParseException e) {
            log.error("ERROR:" + e);
            return "";
        }
        Calendar cal_1 =Calendar.getInstance();
        cal_1.setTime(date);
        int month = cal_1.get(Calendar.MONTH) + 1;
        if(month==2||month==5||month==8||month==11){
            cal_1.add(Calendar.MONTH, -1); // 月份减1
        }
        if(month==3||month==6||month==9||month==12){
            cal_1.add(Calendar.MONTH, -2); // 月份减2
        }
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置日期为当前月第一天
        String theNextDayOfMonth = format.format(cal_1.getTime());
        return theNextDayOfMonth;
    }

    /**
     * Description: 参数当前季度第一个月的第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getNextQuarter(String formatType){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(formatType);
        } catch (ParseException e) {
            log.error("ERROR:" + e);
            return "";
        }
        Calendar cal_1 =Calendar.getInstance();
        cal_1.setTime(date);
        int month = cal_1.get(Calendar.MONTH) + 1;
        if(month==1||month==4||month==7||month==10){
            cal_1.add(Calendar.MONTH, +3); // 月份+3
        }
        if(month==2||month==5||month==8||month==11){
            cal_1.add(Calendar.MONTH, +2); // 月份+2
        }
        if(month==3||month==6||month==9||month==12){
            cal_1.add(Calendar.MONTH, +1); // 月份+1
        }
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置日期为当前月第一天
        String theNextDayOfMonth = format.format(cal_1.getTime());
        return theNextDayOfMonth;
    }

    /**
     * Description: 参数上年份当前月份第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getFixYear(String formatType){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(formatType);
        } catch (ParseException e) {
            log.error("ERROR:" + e);
            return "";
        }
        Calendar cal_1 =Calendar.getInstance();
        cal_1.setTime(date);
        cal_1.add(Calendar.YEAR, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置日期为当前月第一天
        String theFirstDayOfMonth = format.format(cal_1.getTime());
        return theFirstDayOfMonth;
    }


    /**
     * Description: 参数下一年份第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getNextYear(String formatType){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(formatType);
        } catch (ParseException e) {
            log.error("ERROR:" + e);
            return "";
        }
        Calendar cal_1 =Calendar.getInstance();
        cal_1.setTime(date);
        cal_1.add(Calendar.YEAR, 1);
        cal_1.set(Calendar.DAY_OF_YEAR, 1);//设置日期为全年第一天
        String theFirstDayOfMonth = format.format(cal_1.getTime());
        return theFirstDayOfMonth;
    }


    /**
     * Description: 对参数进行校验，是不是小数
     * @param str 请求参数
     * @return boolean
     */
    public static boolean isDecimal(String str){
        if(str == null || str.trim().equals("")){
            return false;
        }
        Pattern pattern = Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$");
        Matcher isDecimal = pattern.matcher(str);
        if (!isDecimal.matches()) {
            return false;
        }
        return true;
    }

    /**
     * Description: 对参数进行校验，是不是整数
     * @param str 请求参数
     * @return boolean
     */
    public static boolean isIngeter(String str){
        if(str == null || str.trim().equals("")){
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d+$|-\\d+$");
        Matcher isIngeter = pattern.matcher(str);
        if (!isIngeter.matches()) {
            return false;
        }
        return true;
    }

    /**
     * Description: 对参数进行校验，是不是数字(整数或者小数)
     * @param str 请求参数
     * @return boolean
     */
    public static boolean isNumber(String str){
        if(str == null || str.trim().equals("")){
            return false;
        }
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    /**
     * Description: 对参数进行校验，是不是工号
     * @param str 请求参数
     * @return boolean
     */
    public static boolean isUserCode(String str){
        if(str == null || str.trim().equals("")){
            return false;
        }
        if(str.length() != 8){
            return false;
        }
        String reg = "^[0-9]+?$";
        return str.matches(reg);
    }

    /*获取指定月份的第一天*/
    public static String getFirstDay(String datadate){
        Date date = null;
        String day_first = null;
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            SimpleDateFormat ddformat = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(datadate);

            //创建日历
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            day_first = ddformat.format(calendar.getTime());
        }catch(Exception e){
            log.error("ERROR:" + e);
        }

        return day_first;
    }

    /*获取指定月份的最后一天*/
    public static String getLastDay(String datadate){
        Date date = null;
        String day_last = null;
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            SimpleDateFormat ddformat = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(datadate);

            //创建日历
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);    //加一个月
            calendar.set(Calendar.DATE, 1);     //设置为该月第一天
            calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
            day_last = ddformat.format(calendar.getTime());
        }catch(Exception e){
            log.error("ERROR:" + e);
        }
        return day_last;
    }

    public static Object checkStringIsNull(Object data){
        Object obj="";
        try {
            if (data == null||"".equals(data)||"null".equals(data)|| "NULL".equals(data)) {
                obj="";
            }else{
                obj=data;
            }
        }catch(Exception e){
            log.info(""+e);
            obj="";
        }
        return obj;

    }

    /**
     * Description: 获取当前年的第一天
     *
     * @return YYYY-MM-DD
     */
    public static String getFirstDayOfCurrentTime(String datadate){
        Date date = null;
        String day_first = null;
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            SimpleDateFormat ddformat = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(datadate);

            //创建日历
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            day_first = ddformat.format(calendar.getTime());
        }catch(Exception e){
            log.error("ERROR:" + e);
        }
        return day_first;
    }

    /**
     * Description: 获取当前年的最后一天
     *
     * @return YYYY-MM-DD
     */
    public static String getLastDayOfCurrentTime(String datadate){
        Date date = null;
        String day_last = null;
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            SimpleDateFormat ddformat = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(datadate);

            //创建日历
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);
            calendar.set(Calendar.DAY_OF_YEAR, -1);
            day_last = ddformat.format(calendar.getTime());
        }catch(Exception e){
            log.error("ERROR:" + e);
        }
        return day_last;
    }
    /**
     * Description: 获取当前日期的前七天
     *
     * @return YYYY-MM-DD
     */
    public static String getSevenDayOfTime(String datadate){
        Date date = null;
        String day_seven = null;
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat ddformat = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(datadate);

            //创建日历
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -6);
            day_seven = ddformat.format(calendar.getTime());
        }catch(Exception e){
            log.error("ERROR:" + e);
        }
        return day_seven;
    }

    /**
     * Description: 获取当前日期的前一年
     *
     * @return YYYY-MM-DD
     */
    public static String getLastYearOfTime(String datadate){
        Date date = null;
        String day_year = null;
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat ddformat = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(datadate);

            //创建日历
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -11);
            calendar.set(Calendar.DATE, 1);
            day_year = ddformat.format(calendar.getTime());
        }catch(Exception e){
            log.error("ERROR:" + e);
        }
        return day_year;
    }
    //获取X轴月数字
    public static List<String> getMonthNumber(){
        List<String> list = new ArrayList<String>();
        list.add("1");list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");list.add("7");
        list.add("8");list.add("9");list.add("10");list.add("11");list.add("12");
        return list;
    }
    //获取X轴天数字
    public static List<String> getDayNumber(){
        List<String> list = new ArrayList<String>();
        list.add("1");list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");list.add("7");
        return list;
    }


    /**
     * 检查数据是否null， null或者值是null或者值是NULL，都会返回""；其他返回自身
     * @param data
     * @return
     */
    public static String checkStringIsNullReturnString(Object data){
        Object obj="";
        try {
            if (data == null||"".equals(data)||"null".equals(data)|| "NULL".equals(data)) {
                obj="";
            }else{
                obj=data;
            }
        }catch(Exception e){
            log.info(""+e);
            obj="";
        }
        return obj.toString();
    };

    /**
     * 根据代码得到具体性别 1：男 M，2：女 F
     */
    public static String getSexByNumber(Object sex){
        if("1".equals(sex)){
            return "男";
        }else if("2".equals(sex)){
            return "女";
        }
        return "";
    }

    /**
     * 根据代码得到具体性别
     */
    public static String getSexByCode(Object sex){
        if("M".equals(sex)){
            return "男";
        }else if("F".equals(sex)){
            return "女";
        }
        return "";
    }
}

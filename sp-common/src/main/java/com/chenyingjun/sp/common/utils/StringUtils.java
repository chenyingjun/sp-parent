package com.chenyingjun.sp.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串相关方法
 *
 * @author chenyingjun
 * @version 2017年05月05日
 * @since 1.0
 */
public class StringUtils {

    /**
     * 一次性判断多个或单个对象为空。
     * @param objects
     * @author zhou-baicheng
     * @return 只要有一个元素为Blank，则返回true
     */
    public static boolean isBlank(Object...objects){
        Boolean result = false ;
        for (Object object : objects) {
            if(null == object || "".equals(object.toString().trim())
                    || "null".equals(object.toString().trim())){
                result = true ;
                break ;
            }
        }
        return result ;
    }

    /**
     * 把数组转换成set
     * @param array
     * @return
     */
    public static Set<?> array2Set(Object[] array) {
        Set<Object> set = new TreeSet<Object>();
        for (Object id : array) {
            if(null != id){
                set.add(id);
            }
        }
        return set;
    }

    /**
     * 将以逗号分隔的字符串转换成字符串数组
     *
     * @param valStr
     * @return String[]
     */
    public static String[] StrList(String valStr) {
        int i = 0;
        String TempStr = valStr;
        String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
        valStr = valStr + ",";
        while (valStr.indexOf(',') > 0) {
            returnStr[i] = valStr.substring(0, valStr.indexOf(','));
            valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

            i++;
        }
        return returnStr;
    }

    /**
     * 获取随机六位数
     *
     * @return
     */
    public static String randomStr() {
        Random r = new Random();
        return String.valueOf(r.nextInt(900000) + 100000);
    }

	/*public String main(String[] args) {
    Random r = new Random();
	Integer pwdCode=r.nextInt(2);
	System.out.println(pwdCode);
}*/

    /**
     * 功能描述:判断字符串是否为空或者为空白串
     *
     * @return 判断结果
     * @date 2013-6-17
     */
    public static boolean isEmptyStr(Object obj) {
        if (null == obj || "".equals(obj.toString()) || "".equals(obj.toString().trim()) || "null".equals(obj)) {
            return true;
        } else
            return false;
    }

    /**
     * 功能描述:判断字符串是否非空
     *
     * @param str 待判断字符串
     * @return 判断结果
     * @date 2013-6-17
     */
    public static boolean isNotEmpty(String str) {
        return !isEmptyStr(str);
    }


    /**
     * 功能描述:判断字符串是否为空或者为空白串
     *
     * @param str 待判断字符串
     * @return 判断结果
     * @date 2013-6-17
     */
    public static boolean isEmptyStr(String str) {
        if (null == str || "".equals(str.trim())) {
            return true;
        } else
            return false;
    }

    /**
     * 功能描述：将null转化为空字符串
     *
     * @param str 源字符串
     * @return
     * @date 2013-8-29
     */
    public static String cancelNull(String str) {
        return str == null ? "" : str;
    }

    /**
     * 功能描述:如果传入串为空或者undefined，那么用defaultStr初始化，否则返回截取空白后的字符串
     *
     * @param str        待处理的字符串
     * @param defaultStr 缺省的初始化串
     * @return 处理结果
     * @date 2013-6-19
     */
    public static String getTrimStr(String str, String defaultStr) {
        if (isEmptyStr(str) || "undefined".equals(str)) {
            str = defaultStr;
        }
        return str.trim();
    }

    /**
     * 功能描述：如果传入串不为空,则返回截取空白后带%的字符
     *
     * @param str
     * @return
     * @date 2014-11-21
     */
    public static String getTrimPerStr(String str) {
        if (isEmptyStr(str)) {
            str = "";
        } else {
            str = str + "%";
        }
        return str.trim();
    }

    /**
     * 功能描述:根据身份证号码获取生日的日期，可以处理15位或18位身份证号码
     *
     * @param idCardNo 身份证号码
     * @return 生日的日期
     * @date 2013-6-19
     */
    public static Date getBirthdayFromIdCard(String idCardNo) {
        if (isEmptyStr(idCardNo)
                || !(idCardNo.matches(RegexStringsUtils.REG_IDCARD_15) || idCardNo.matches(RegexStringsUtils.REG_IDCARD_18))) {
            throw new IllegalArgumentException("idCardNo is not right");
        }
        String birdayStr = "";
        Date birday = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        try {
            if (!isEmptyStr(idCardNo) && 15 == idCardNo.length()) {
                birdayStr = "19" + idCardNo.substring(6, 12);
                birday = dateFormat.parse(birdayStr);
            } else if (!isEmptyStr(idCardNo) && 18 == idCardNo.length()) {
                birdayStr = idCardNo.substring(6, 14);
                birday = dateFormat.parse(birdayStr);
            }
        } catch (Exception e) {
            System.out.println("转换身份证号码的生日出错" + e.getMessage());
        }
        return birday;
    }

    /**
     * 功能描述:根据身份证号码获取性别，可以处理15位或18位身份证号码
     *
     * @param idCardNo 身份证号码
     * @return 性别（M表示男，F表示女）
     * @date 2013-6-19
     */
    public static String getGenderFromIdCard(String idCardNo) {
        String result = "";
        int genderNo = 0;
        if (isEmptyStr(idCardNo)
                || !(idCardNo.matches(RegexStringsUtils.REG_IDCARD_15) || idCardNo.matches(RegexStringsUtils.REG_IDCARD_18))) {
            throw new IllegalArgumentException("idCardNo is not right");
        }
        if (15 == idCardNo.length()) {
            genderNo = Integer.parseInt(idCardNo.substring(12));
        } else if (18 == idCardNo.length()) {
            genderNo = Integer.parseInt(idCardNo.substring(14, 17));
        }
        result = (genderNo % 2) > 0 ? "M" : "F";
        return result;
    }

    /**
     * 功能描述:将15位身份证转换成为18位，如果已经是18位，那么直接返回。
     *
     * @param idCardNo 身份证号码
     * @return 转换后的身份证号码
     * @date 2013-6-19
     */
    public static String change15IdCardTo18(String idCardNo) {
        if (isEmptyStr(idCardNo)
                || !(idCardNo.matches(RegexStringsUtils.REG_IDCARD_15) || idCardNo.matches(RegexStringsUtils.REG_IDCARD_18))) {
            throw new IllegalArgumentException("idCardNo is not right");
        }
        if (15 == idCardNo.length()) {
            final int[] W = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
            final String[] A = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
            int i, j, s = 0;
            String newid;
            newid = idCardNo;
            newid = newid.substring(0, 6) + "19" + newid.substring(6, idCardNo.length());
            for (i = 0; i < newid.length(); i++) {
                j = Integer.parseInt(newid.substring(i, i + 1)) * W[i];
                s = s + j;
            }
            s = s % 11;
            newid = newid + A[s];
            return newid;
        } else
            return idCardNo;
    }

    /**
     * 功能描述:从身份证中获取周岁
     *
     * @param idCardNo 身份证
     * @return 周岁
     * @date 2013-6-19
     */
    public static int getAgeFromIdCard(String idCardNo) {
        Date birthday = getBirthdayFromIdCard(idCardNo);
        Calendar calBirthday = Calendar.getInstance();
        calBirthday.setTime(birthday);
        calBirthday.get(Calendar.YEAR);

        Calendar calToday = Calendar.getInstance();
        int diffYear = calToday.get(Calendar.YEAR) - calBirthday.get(Calendar.YEAR);

        calBirthday.add(Calendar.YEAR, diffYear);
        if (calBirthday.after(calToday))//如果今天的日期还没到达今年的生日，说明周岁还需要减1
        {
            diffYear = diffYear - 1;
        }
        return diffYear;
    }

    /**
     * 功能描述:将DTO对象转换成字符串，主要用于日志的记录
     *
     * @param dto 待序列化的对象
     * @return
     * @date 2013-6-19
     */
    public static String getDTOString(Object dto) {
        StringBuffer returnSb = new StringBuffer();
        String returnStr = "";
        try {
            Class c = dto.getClass();
            Method[] methods = c.getDeclaredMethods();

            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.indexOf("get") == 0) {
                    String fieldName = methodName.substring(3, methodName.length());
                    Object fieldValue = method.invoke(dto, new Object[0]);
                    String strValue = "";
                    if (fieldValue != null) {
                        strValue = fieldValue.toString();
                    } else
                        strValue = "null";
                    returnSb.append(fieldName + "==" + strValue + ",");
                }
            }
            if (returnSb.length() > 0) {
                returnStr = returnSb.substring(0, returnSb.length() - 1);
            }
        } catch (Exception e) {
            System.out.println("转换DTO到字符串出错:" + e.getMessage());
        }
        return returnStr;
    }

    /**
     * 功能描述:将字符串转成小写，可避免直接调用String.toLowerCase引起空指针的情况
     *
     * @param str
     * @return
     * @date 2013-8-17
     */
    public static String toLowerCase(String str) {
        if (str == null)
            return "";
        else {
            return str.toLowerCase();
        }

    }

    /**
     * 功能描述:将字符串转成大写，可避免直接调用String.toUpperCase引起空指针的情况
     *
     * @param str
     * @return
     * @date 2013-8-17
     */
    public static String toUpperCase(String str) {
        if (str == null)
            return "";
        else {
            return str.toUpperCase();
        }
    }

    /**
     * 功能描述:将字符串的首字母转成大写
     *
     * @param str
     * @return
     * @date 2014-1-6
     */
    public static String toUpper1stLetter(String str) {
        if (str == null)
            return "";
        else {
            return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
        }
    }


    /**
     * 用delimeter连接个字符串
     *
     * @param list
     * @return
     */
    public static String getMergeString(List<String> list, String delimeter) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            return (String) list.get(0);
        }
        StringBuffer sb = new StringBuffer();

        Iterator<String> iter = list.iterator();
        String str;
        while (iter.hasNext()) {
            str = iter.next();
            sb.append(str);
            sb.append(delimeter);
        }
        return sb.substring(0, sb.lastIndexOf(delimeter));
    }

    /**
     * 功能描述：将String转为Integer
     *
     * @return Integer
     * @date 2014年1月7日
     */
    public static Integer strToInt(String inValue) {
        int outValue = 0;
        try {
            outValue = Integer.parseInt(inValue);
        } catch (NumberFormatException ex) {
        } catch (NullPointerException ex) {
        }
        return Integer.valueOf(outValue);
    }

    /**
     * 功能描述:判断字符串数组是否为空
     *
     * @param arg 待判断字符串数组
     * @return 判断结果
     * @date 2014-01-15
     */
    public static boolean isEmptyStrArray(String[] arg) {
        boolean flag = false;
        if (null == arg || arg.length == 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 功能描述：将String转为BigDecimal
     *
     * @return BigDecimal
     * @date 2014年1月23日
     */
    public static BigDecimal convertStringToBigDecimal(String str) {
        BigDecimal bigDecimal = null;
        try {
            bigDecimal = new BigDecimal(str);
        } catch (Exception e) {
        }
        return bigDecimal;
    }


    public static BigDecimal convertStrToBigDecimal(String str) {
        if (isEmptyStr(str)) {
            return new BigDecimal(0);
        }
        BigDecimal bigDecimal = new BigDecimal(0);
        try {
            bigDecimal = new BigDecimal(str);
        } catch (Exception e) {
        }
        return bigDecimal;
    }

    /**
     * 功能描述：空字符串置换
     *
     * @param in
     * @param out
     * @return
     * @date 2014-1-26
     */
    public static String emptyStrConvert(String in, String out) {
        return isNotEmpty(in) ? in : out;
    }

    /**
     * 功能描述：字符串转长整型对象
     * <p>
     * 传入参数：inValue (内容：输入字串；类型：字符串)
     * <p>
     * 传出参数：outValue (内容：输出长整型对象；类型：类)
     * <p>
     * 异常处理：无
     */
    public static Long strToLong(String inValue) {
        long outValue = 0;
        try {
            outValue = Long.parseLong(inValue);
        } catch (NumberFormatException ex) {
        } catch (NullPointerException ex) {
        }
        return Long.valueOf(outValue);
    }

    /**
     * 功能描述：根据指定长度截取字符串,并追加结束符
     *
     * @return
     * @date 2014-4-14
     */
    public static String subString(String s, int n, String endWith) {
        if (s == null) {
            return "";
        }
        Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]|[\\uff08]|[\\uff09]$");
        int i = 0, j = 0;
        char[] ch = s.toCharArray();
        char c;
        for (int k = 0; k < ch.length; k++) {
            c = ch[k];
            Matcher m = p.matcher(String.valueOf(c));
            i += m.find() ? 2 : 1;
            ++j;
            if (i == n)
                break;
            if (i > n) {
                --j;
                break;
            }
        }
        return s.substring(0, j) + endWith;
    }

    /**
     * 功能描述：通过escape转码
     *
     * @param src
     * @return
     * @date 2014-7-9
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * 功能描述：通过unescape转码
     *
     * @param src
     * @return
     * @date 2014-7-9
     */
    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }


    /**
     * 功能描述：字符串匹配()
     *
     * @param regStr 正则表达式
     * @param  matStr 匹配Str
     * @return
     * @date 2014-7-9
     */
    public static boolean checkMatcher(String regStr, String matStr) {
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(matStr);
        return matcher.matches();
    }

    /**
     * 功能描述：判断两个字符串是否相等（空字符串与Null相等）
     *
     * @param str1
     * @param str2
     * @return
     * @date 2014-10-20
     */
    public static boolean isEqualStr(String str1, String str2) {
        return cancelNull(str1).equals(cancelNull(str2));
    }


    /**
     * 功能描述：检查是否前后有空格
     *
     * @return
     * @date 2014-12-11
     */
    public static boolean isHaveBAblankBankStr(String str) {
        String tmpStr = str.trim();

        return tmpStr.length() != str.length();
    }

    /**
     * 功能描述：去除str中所有空格(前\中\后),如果入参为空或null，则返回原值
     *
     * @param str
     * @return String
     * @date 2014-12-31
     */
    public static String stringTrim(String str) {
        if (isNotEmpty(str)) {
            str = str.replaceAll("\\s+", "");
            str = stringFullCornerTrim(str);//去掉全角空格
            return str;
        }
        return str;
    }

    /**
     * 功能描述：去除str中所有空格(前\中\后),如果入参为空或null，则返回原值
     *
     * @param str
     * @return String
     * @date 2015-02-10
     */
    public static String getDefaultString(String str) {
        if (isEmptyStr(str)) {
            return "";
        }
        return str;
    }

    /**
     * 功能描述：根据表名获取主键字段名（对应DTO字段名，只适用于驼峰原则生成的主键）
     * 例如：传入clm_channel_process,返回idClmChannelProcess
     *
     * @param tableName
     * @return
     * @date 2015-2-28
     */
    public static String getPrimaryFieldByTableName(String tableName) {
        StringBuffer primaryKey = new StringBuffer("id");
        if (StringUtils.isNotEmpty(tableName)) {
            String lowerTableName = tableName.toLowerCase();
            String[] items = lowerTableName.split("_");
            for (String item : items) {
                primaryKey.append(item.substring(0, 1).toUpperCase()).append(item.substring(1, item.length()));
            }
        }
        return primaryKey.toString();
    }

    /**
     * 功能描述：根据表名获取主键名（对应数据库字段名）
     *
     * @param tableName
     * @return
     * @date 2015-3-5
     */
    public static String getPrimaryKeyByTableName(String tableName) {
        StringBuffer primaryKey = new StringBuffer("id_");
        primaryKey.append(tableName);
        return primaryKey.toString();
    }

    /**
     * 功能描述：根据字段名获取get方法名(只适用驼峰原则，入参非数据库字段，而是dto定义字段,暂不支持boolean类型字段)
     * 例如：传入idClmChannelProcess，返回getIdClmChannelProcess
     *
     * @param columnName
     * @return
     * @date 2015-2-28
     */
    public static String getGetMethodNameByColumn(String columnName) {
        StringBuffer getMethodName = new StringBuffer("get");
        return getMethodName.append(columnName.substring(0, 1).toUpperCase()).append(columnName.substring(1, columnName.length())).toString();
    }

    /**
     * 功能描述：根据DTO字段名获取对应的数据库列名(只适用驼峰原则)
     * 例如传入idClmChannelProcess，返回id_clm_channel_process
     *
     * @param fieldName
     * @return
     * @date 2015-2-28
     */
    public static String getColumnNameByFiledName(String fieldName) {
        if (StringUtils.isEmptyStr(fieldName)) {
            return "";
        }
        StringBuffer col = new StringBuffer();
        for (int i = 0; i < fieldName.length(); i++) {
            char s = fieldName.charAt(i);
            int as = (int) s;
            //s为大写字母,转成小写
            if (as >= 65 && as <= 90) {
                col.append("_").append((char) (as + 32));
            } else {
                col.append(s);
            }
        }
        return col.toString();
    }


    /**
     * 功能描述：去掉全角空格(前\中\后),如果入参为空或null，则返回原值
     *
     * @param str
     * @return
     * @date 2015-2-5
     */
    public static String stringFullCornerTrim(String str) {
        if (isNotEmpty(str)) {
            String regStartSpace = "^[　 ]*";//左边空格
            String regEndSpace = "[　 ]*$";//右边空格
            //替换左右全角空格(中间不管)
            str = str.replaceAll(regStartSpace, "").replaceAll(regEndSpace, "");
            //str = str.replaceAll("\u3000", "");//去掉全角空格
        }
        return str;
    }


    /**
     * 功能描述：将null对象转换成空字符串
     *
     * @param obj
     * @return
     * @date 2015-2-28
     */
    public static String cancelNullObj(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 功能描述：判断是否有一个字符串为空
     *
     * @param args
     * @return
     * @date2015-6-5
     */
    public static boolean isHaveOneEmpty(String... args) {
        boolean retValue = false;
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                if (isEmptyStr(args[i])) {
                    retValue = true;
                    return retValue;
                }
            }
        }
        return retValue;
    }


    /**
     * 功能描述:判断是否为纯数字类型的字符串
     *
     * @return
     * @date 2015-9-14
     */
    public static boolean isNumStr(String str) {
        boolean ret = false;
        ret = checkMatcher("^\\d+$", str);
        return ret;
    }

    /**
     * @param map
     * @param append
     * @return
     * @Desc map的key值从0开始往上增加
     * @Date 2015-12-3
     */
    public static String mapToString(Map<String, String> map, String append) {
        if (map == null || isEmptyStr(append)) {
            return "";
        }
        StringBuffer value = new StringBuffer();
        int index = 0;
        while (map.get(index + "") != null) {
            value.append(map.get(index + "")).append(append);
            index++;
        }
        if (isNotEmpty(value.toString())) {
            return value.substring(0, value.length() - append.length());
        }
        return value.toString();
    }

    /**
     * @Title: checkXss
     * @Description: 验证value是否包含Xss 包含返回false不包含返回true
     *
     * @return
     * @throws Exception
     */
    public static boolean checkXss(String value) {

        if (org.apache.commons.lang3.StringUtils.isBlank(value)) {

            return true;
        }

        // NOTE: It's highly recommended to use the ESAPI library and uncomment
        // the following line to
        // avoid encoded attacks.
        // value = ESAPI.encoder().canonicalize(value);
        // Avoid null characters
        // Avoid anything between script tags
        Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid anything in a src="http://www.yihaomen.com/article/java/..."
        // type of e­xpression
        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }
        // Remove any lonesome </script> tag
        scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Remove any lonesome <script ...> tag
        scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid eval(...) e­xpressions
        scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid e­xpression(...) e­xpressions
        scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid javascript:... e­xpressions
        scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid vbscript:... e­xpressions
        scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        // Avoid onload= e­xpressions
        scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        if (scriptPattern.matcher(value).find()) {

            return false;
        }

        try {
            value = value.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            value = value.replaceAll("\\+", "%2B");
            String value_ = URLDecoder.decode(value, "UTF-8");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL);

            if (scriptPattern.matcher(value_).find()) {

                return false;
            }

        } catch (UnsupportedEncodingException e) {

        }

        return true;
    }

    /**
     *
     * @Title: md532
     * @Description: 32位md5加密
     * @param text
     *            需要加密的字符串
     * @return 加密后的字符串小写
     */
    public static String md532(String text) throws Exception {

        StringBuffer hexString = new StringBuffer();

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(text.getBytes());

        byte[] digest = md.digest();

        for (int i = 0; i < digest.length; i++) {

            text = Integer.toHexString(0xFF & digest[i]);

            if (text.length() < 2) {

                text = "0" + text;
            }

            hexString.append(text);
        }

        return hexString.toString().toLowerCase();
    }
}

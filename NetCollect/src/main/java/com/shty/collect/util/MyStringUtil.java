package com.shty.collect.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

/**
 * Created by john on 2016/11/16.
 */
public class MyStringUtil extends StringUtils {
    private final static String ascSpace = Jsoup.parse("&nbsp").text();
    /**
     * Test to see if a string is an empty string
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str)
    {
        if(str == null || str.trim().equals(""))
        {
            return true;
        }

        return false;
    }

    /**
     * trims white spaces from a String. Use it instead of String.trim() to
     * deal with null scenarios.
     * @param str
     * @return
     */
    public static String trim(String str)
    {
        if(str == null)
        {
            return null;
        }

        return str.trim();
    }



    /**
     * trims sql statements where it ends with , or AND.
     * @param sql
     * @return
     */
    public static String trimSQLExtra(String sql)
    {
        if(!isNullOrEmpty(sql) && sql.length() > 1)
        {
            if(sql.endsWith(","))
            {
                return sql.substring(0, sql.length()-1);
            }
            else if(sql.endsWith("AND"))
            {
                return sql.substring(0, sql.length()-3);
            }
        }

        return sql;
    }

    /**
     * Convert a stack trace into a string
     * @param t
     * @return
     */
    public static String stackTraceToString(Throwable t)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);

        return sw.toString();
    }

    public static String trimHtmlExtra(String echo){
        if(isNullOrEmpty(echo)){
            return echo;
        }
        String result = StringUtils.replace(echo, ascSpace, " ");
        return StringUtils.trimToEmpty(result);
    }

    private static int ld(String str1, String str2)
    {
        //Distance
        int [][] d;
        int n = str1.length();
        int m = str2.length();
        int i; //iterate str1
        int j; //iterate str2
        char ch1; //str1
        char ch2; //str2
        int temp;
        if (n == 0)
        {
            return m;
        }
        if (m == 0)
        {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++)
        {   d[i][0] = i;
        }
        for (j = 0; j <= m; j++)
        {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++)
        {
            ch1 = str1.charAt(i - 1);
            //match str2
            for (j = 1; j <= m; j++)
            {
                ch2 = str2.charAt(j - 1);
                if (ch1 == ch2)
                {
                    temp = 0;
                }
                else
                {
                    temp = 1;
                }

                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    private static int min(int one, int two, int three)
    {
        int min = one;
        if (two < min)
        {
            min = two;
        }
        if (three < min)
        {
            min = three;
        }
        return min;
    }

    public static double sim(String str1, String str2)
    {
        int ld = ld(str1, str2);
        return 1 - (double) ld / Math.max(str1.length(), str2.length());
    }

    /*Normally used for phone# formating, return all numbers contained in the given string*/
    public static String extractAllDigit(String str){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();

    }

    /*Normally used to extract profile_id number for URL, return the first matcher only*/
    public static String getContinuousNumbers(String str){
        String target = null;
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if(m.find()){
            target = m.group(0);
        }
        return target;
    }

    //JSON传图片测试
    public static byte[] inputStreamToByteArray(InputStream input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();;
        byte[] data = null ;
        try {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = input.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray() ;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data ;
    }

    /**
     * 对byte[]进行压缩
     *
     * @param 要压缩的数据
     * @return 压缩后的数据
     */
    public static byte[] compress(byte[] data) {
        GZIPOutputStream gzip = null ;
        ByteArrayOutputStream baos = null ;
        byte[] newData = null ;

        try {
            baos = new ByteArrayOutputStream() ;
            gzip = new GZIPOutputStream(baos);

            gzip.write(data);
            gzip.finish();
            gzip.flush();

            newData = baos.toByteArray() ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                gzip.close();
                baos.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return newData ;
    }

    public static InputStream decompress(byte[] byteArray) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
        GZIPInputStream gunzip = new GZIPInputStream(in);
//	    byte[] buffer = new byte[256];
//	    int n;
//	   while ((n = gunzip.read(buffer))>= 0) {
//	    out.write(buffer, 0, n);
//	    }
        // toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
        return gunzip;
    }
    public static void main(String[] args) {
        String ss = null;
        Map map = new HashMap();
        map.put("aa", ss);
        String kk = (String)map.get("bb");
    }
}

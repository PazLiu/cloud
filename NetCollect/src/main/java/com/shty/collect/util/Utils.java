package com.shty.collect.util;

/**
 * Created by john on 2016/11/18.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;

import com.google.gson.Gson;

public class Utils {
    //private static final Logger log = LoggerFactory.getLogger(Utils.class);
    private final static String ascSpace = Jsoup.parse("&nbsp").text();
    public final static String stateCode = "AL_AK_AZ_AR_CA_CO_CT_DE_DC_FL_GA_HI_ID_IL_IN_IA_KS_KY_LA_ME_MD_MA_MI_MN_MS_MO_MT_NE_NV_NH_NJ_NM_NY_NC_ND_OH_OK_OR_PA_RI_SC_SD_TN_TX_UT_VT_VA_WA_WV_WI_WY";

    private static final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36";

    private static double intervalCapSearchNextPage[] = {0.90, 1.11, 1.06, 2.01, 0.80, 0.35, 0.21, 1.01, 0.03};
    private static double intervalClientRequest[] = {2.18, 5.77, 7.18, 8.96, 9.731, 2.5, 2.99, 1.01, 3.33};

    private static double storyIntervalSelect[] = {1.5, 2.0, 2.5, 2.75, 3.0, 3.5};
    private static double imagineIntervalSelect[] = {1.4, 1.9, 2.4, 2.65, 2.9, 3.4};
    private static double intervalSevenSecs[] = {2.18, 5.77, 7.18, 8.96, 9.731, 2.5, 2.99, 1.01, 3.33};

    private static Random rand = new Random(System.currentTimeMillis());
    private static Gson gson = null;

    public static Gson getGson() {
        if (gson == null) {
            synchronized (Utils.class) {
                gson = new Gson();
            }
        }
        return gson;
    }

    public static void sleep(long interval) {
        try {
            Thread.sleep(interval);
        } catch (Exception e) {
            //log.error("Thread.sleep Error",e);
        }
    }

    public static String dowloadImg(String imgurl) {
        Response res = null;
        String imgcode = "";
        imgurl = MyStringUtil.replaceOnce(imgurl, "https", "http");
        try {
            res = Jsoup.connect(URLDecoder.decode(imgurl, "utf-8")).userAgent(userAgent).timeout(120000).ignoreHttpErrors(true).ignoreContentType(true).execute();
        } catch (IOException e) {
            //log.error("download img error on: "+imgurl, e);
        }
        if (null != res && res.statusCode() == 200) {
            byte[] byteArray = res.bodyAsBytes();
            try {
                imgcode = new String(Base64.encodeBase64(byteArray));
//				imgcode = new String(MyStringUtils.compress(Base64.encodeBase64(byteArray)),"ISO-8859-1");
//				imgcode = new String(Base64.encodeBase64(MyStringUtils.compress(byteArray)),"ISO-8859-1");
            } catch (Exception e) {
                //log.error("encode error on: "+imgurl, e);
            }
        }
        return imgcode;
    }

    public static long getInterval(IntervalEnum filter) {
        switch (filter) {
            case NEXTPAGE:
                int nextpage = rand.nextInt(intervalCapSearchNextPage.length);
                return Math.round(intervalCapSearchNextPage[nextpage] * 1000 * 60);
            case CLIENTREQUEST:
                int clientrequest = rand.nextInt(intervalClientRequest.length);
                return Math.round(intervalClientRequest[clientrequest] * 1000 * 60);
            case NOMORE:
                return 180000;
            case HALFDAY:
                return 43200000;
            case CAPPERON:
                int max = 8640;
                int min = 5000;
                int interval = rand.nextInt(max) % (max - min + 1) + min;
                return interval;
            case THIRTYSECS:
                return 30000;
            case STORY:
                int intervalselect = rand.nextInt(storyIntervalSelect.length);
                return (long) storyIntervalSelect[intervalselect] * 60 * 1000;
            case PERHOUR:
                return 3600000;
            case TENHOURS:
                return 36000000;
            case IMAGINE:
                int imagineSelect = rand.nextInt(imagineIntervalSelect.length);
                return (long) imagineIntervalSelect[imagineSelect] * 60 * 1000;
        }
        return 6000;
    }

    public static String trimHtmlExtra(String echo) {
        String result = StringUtils.replace(echo, ascSpace, " ");
        return StringUtils.trimToEmpty(result);
    }

    public static void cacheTolocal(String ss, String fileName, boolean isAppend) {
        try {
            File file = null;

            if (fileName != null && !fileName.equals("")) {
                file = new File("./debug/" + fileName);
            } else {
                file = new File("./debug/zhu.html");
            }

            File dir = file.getParentFile();

            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            if (isAppend) {
                FileUtils.writeStringToFile(file, ss + "\r\n", true);
            } else {
                FileUtils.writeStringToFile(file, ss);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void cacheTolocal(String ss, String fileName) {
        cacheTolocal(ss, fileName, true);
    }

    public static String fileToString(File file) {
        StringBuffer s = new StringBuffer();
        try {

            FileReader fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);


            String line = "";
            while ((line = br.readLine()) != null) {

                s = s.append(line);

            }

            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s.toString();
    }

    public static boolean isNullOrEmpty(String toTest) {
        return MyStringUtil.isNullOrEmpty(toTest);
    }


    public static String getNextStateCode(String state) {

        String[] states = StringUtils.split(state, "_");

        if (states.length > 0) {
            return states[0];
        } else {
            return "";
        }
    }

    public static String removeFirstState(String state) {
        String[] states = StringUtils.split(state, "_");
        if (states.length > 0) {
            String result = StringUtils.join(states, "_", 1, states.length);
            return result;
        } else {
            return "";
        }
    }

    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    public static Map<String, String> urlQueryToMap(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;
        String strUrlParam = TruncateUrlPage(URL);

        if (isNullOrEmpty(strUrlParam)) {
            return mapRequest;
        }
        // 每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (arrSplitEqual[0] != "") {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    public static String mapToUrlQuery(Map<String, String> map) {
        StringBuffer query = new StringBuffer();
        for (String key : map.keySet()) {
            if (!isNullOrEmpty(query.toString())) {
                query.append("&");
            }
            query.append(key + "=" + map.get(key));
        }

        return query.toString();
    }


    private static int ld(String str1, String str2) {
        //Distance
        int[][] d;
        int n = str1.length();
        int m = str2.length();
        int i; //iterate str1
        int j; //iterate str2
        char ch1; //str1
        char ch2; //str2
        int temp;
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            ch1 = str1.charAt(i - 1);
            //match str2
            for (j = 1; j <= m; j++) {
                ch2 = str2.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }

                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    private static int min(int one, int two, int three) {
        int min = one;
        if (two < min) {
            min = two;
        }
        if (three < min) {
            min = three;
        }
        return min;
    }

    public static double sim(String str1, String str2) {
        int ld = ld(str1, str2);
        return 1 - (double) ld / Math.max(str1.length(), str2.length());
    }


    public static void datasourceConfigGen() {


        String datasourcename = "";
        String regioncode = "";

        String ss = "<bean id=\"region_" + datasourcename + "\" class=\"org.springframework.jdbc.datasource.DriverManagerDataSource\">" + "\r\n"
                + "<property name=\"driverClassName\" value=\"com.mysql.jdbc.Driver\" />" + "\r\n"
                + "<property name=\"url\" value=\"jdbc:mysql://54.64.182.2:3306/" + datasourcename + "?characterEncoding=utf-8\" />" + "\r\n"
                + "<property name=\"username\" value=\"root\" />" + "\r\n"
                + "<property name=\"password\" value=\"root\" />" + "\r\n"
                + "</bean>" + "\r\n";

//		    <bean id="dataSource" class="com.smit.dynamicdatasource.DynamicDataSource">
//		        <property name="targetDataSources">
//		            <map key-type="java.lang.String">
//		                <entry key="dataSource_b" value-ref="dataSource_b"/>
//		            </map>
//		        </property>
//		        <property name="defaultTargetDataSource" ref="dataSource_b"/>
//		    </bean>

        System.out.println(ss);

    }

//		public static String retainSlash(String input){
//			 String part = "", output = input;
//			 int offset = 0;
//			 for(int i=0;i<=input.length()-4;i++) {
//			        part = input.substring(i+offset, i+4);
//			        if(part.startsWith("\\x")) {
//			        	output = output.replace(part, "ΩΩ"+part.substring(2));
//			        }
//			    }
//
//			 System.out.println(output);
//			 output = output.replaceAll("ΩΩ", "\\\\x".toCharArray());
//			 System.out.println(output);
//			 System.out.println(input);
//			 return output;
//		}

    public static String convertUTF8Units(String input) {
        String part = "", output = input;
        for (int i = 0; i <= input.length() - 4; i++) {
            part = input.substring(i, i + 4);
            if (part.startsWith("\\x")) {
                byte[] rawByte = new byte[1];
                rawByte[0] = (byte) (Integer.parseInt(part.substring(2), 16) & 0x000000FF);
                String raw = new String(rawByte);
                output = output.replace(part, raw);
            }
        }

        return output;
    }

    public static void main(String[] args) {
        dowloadImg("https://media.licdn.com/mpr/mpr/shrink_100_100/p/2/000/017/1f6/3503b88.jpg");
//			long total = 0l;
//			for (int i = 0; i < 10000; i++) {
//				int intervalselect = rand.nextInt(intervalClientRequest.length);
//				total = total + Math.round(intervalClientRequest[intervalselect]*1000*60);
//			}
//			System.out.println(total/10000);

//			long total = 0l;
//			int max=8640;
//	        int min=5000;
//			double maxNum = 0;
//			for (int i = 0; i < 10000000; i++) {
//				int interval = rand.nextInt(max)%(max-min+1) + min;
//				if(interval>maxNum){
//					maxNum = interval;
//				}
//				total = total + interval;
//			}
//			System.out.println(total/10000000);
//			System.out.println(maxNum);

        long total = 0l;
        for (int i = 0; i < 1000; i++) {
            long interval = getInterval(IntervalEnum.IMAGINE);
            total = total + interval;
        }
        System.out.println(total / 1000);

    }


}


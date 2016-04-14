package com.test.unittest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

// http://zhidao.baidu.com/link?url=H4eK8cjJWkdWsU_4toFAyFmUCO5yZk4Y9ylFszlwsTW4yV3xhniVPaDmf7L1xiAtKLDChiLXoYpdqexenipQ-T2QecMLACOyRZtt4pxY_tS
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String str = "ABCABCDEFGHIJKLMNOPQ小米手机";
//        String str = "ABCABCDEFGH小米手机";
//        String str = "ABCABCDEs小米手机";
//        String str = "\"RDm4BKQ8PHgAAwHR小米手机\"";
//        Log.d("zqd", checkSSIDMaxLength(str));
//        Log.d("zqd", checkSSIDMaxLength("ABCABCDEs小米手机"));
//        Log.d("zqd", checkSSIDMaxLength("\"AB小米手机CABCDEs\""));
//        Log.d("zqd", checkSSIDMaxLength("\"ABCABCDEs小米手机\""));
//        Log.d("zqd", checkSSIDMaxLength(""));
//        Log.d("zqd", checkSSIDMaxLength("\"\""));
//        Log.d("zqd", checkSSIDMaxLength("\"小米手机\""));

//        Log.d("zqd", checkString("\"RDm4BKQ8PHgAAwHR小米手机\"", 7));
//        Log.d("zqd", checkString("ABCABCDEs小米手机", 7));
//        Log.d("zqd", checkString("\"AB小米手机CABCDEs\"", 7));
//        Log.d("zqd", checkString("\"ABCABCDEs小米手机\"", 7));
        String str = checkString("", 7);
        int l = str.length();
        Log.d("zqd", str);
        Log.d("zqd", String.valueOf(l));
        Log.d("zqd", checkString("", 7));
//        Log.d("zqd", checkString("\"\"", 7));
//        Log.d("zqd", checkString("小米手机", 7));
//        Log.d("zqd", checkString("\"小米手机\"", 7));
    }





    public static String checkString2(String str, int maxLength) {
        if (str != null) {
            float count = 0.0f;
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (!StringUtils.isEnglishChar(c)) {
                    count += 1; // 中文
                } else {
                    count += 0.5; // 英文
                }
                if (count > maxLength) {
                    res.append("...");
                    break;
                }
                res.append(c);
            }
            return res.toString();
        } else {
            return null;
        }
    }

    public static String checkSSIDMaxLength(String ssid) {
        if (ssid != null) {
            String str = StringUtils.trimString(ssid, '\"');
            str = checkString2(str, 7);
            return "\"" + str +"\"";
        } else {
            return null;
        }
    }

    public static String checkString(String str, int maxLength) {
        if (str != null) {
            float count = 0.0f;
            StringBuilder res = new StringBuilder();
            boolean hasDoubleQuotes = false; // 是否有双引号
            if (str.length() >= 2 && str.charAt(0) == '\"' && str.charAt(str.length()-1) == '\"')
            {
                hasDoubleQuotes = true;
                res.append('\"');
                str = str.substring(1,str.length()-1);
            }
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (!StringUtils.isEnglishChar(c)) {
                    count += 1; // 中文
                } else {
                    count += 0.5; // 英文
                }
                if (count > maxLength) {
                    res.append("...");
                    break;
                }
                res.append(c);
            }
            if (hasDoubleQuotes) {
                res.append('\"');
            }
            return res.toString();
        } else {
            return null;
        }
    }
}

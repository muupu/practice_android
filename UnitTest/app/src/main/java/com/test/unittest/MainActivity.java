package com.test.unittest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
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
        String str = "ABCABCDEs小米手机";
        Log.d("zqd", checkString(str, 8));
    }

    //英文占1byte，非英文（可认为是中文）占2byte，根据这个特性来判断字符
    public static boolean checkChar(char ch) {
        byte[] bytes = (ch + "").getBytes();
        if (bytes.length == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static String checkString(String str, int maxLength) {
        StringBuilder res = new StringBuilder();
        float count = 0.0f;
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (!checkChar(c)) {
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
        }
        return res.toString();
    }
}

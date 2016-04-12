
public class CheckString {
	public static boolean checkChar(char ch) {
		if ((ch + "").getBytes().length == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static String checkString(String str) {
		String res = "";
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				if (!checkChar(str.charAt(i))) {
					 res = "中文";
					 break;
				} else {
				 	res = "英文";
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String str = "RKJLKJDLKJLSKJLKJ小米手机";
		System.out.println("使用长度判断:");
		System.out.println(checkString(str));
	}
}
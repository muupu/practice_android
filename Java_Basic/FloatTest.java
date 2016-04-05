
public class FloatTest {

	public static void printProgress(double progress) {
		String progressStr;
		if (progress <= 0) {
			progressStr = "0";
		} else if (progress >= 100) {
			progressStr = "100";
		} else {
			progressStr = String.format("%.1f", progress);
		}
		System.out.println("update..." + progress + "%");
	}

	public static void main(String[] args) {
		double progress = 100f * 99 / 100;
		printProgress(progress);
	}
}

public class FloatTest {

	public static void printProgress(double progress) {
		System.out.println("progress:" + progress);
		String progressStr;
		if (progress <= 0) {
			progressStr = "0";
		} else if (progress >= 100) {
			progressStr = "100";
		} else {
			progressStr = String.format("%.1f", progress);
		}
		System.out.println("update..." + progressStr + "%");
	}

	public static void main(String[] args) {
		printProgress(100f * 99 / 100);  // update...99.0%
		printProgress(100f * 0 / 100);   // update...0%
		printProgress(100f * 100 / 100); // update...100%

		printProgress(100f * 100 / 0);   // progress:Infinity
										 // update...100%
		
		printProgress(100f * 0 / 0);     // progress:NaN
		                                 // update...NaN%
	}
}
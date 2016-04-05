import java.text.DecimalFormat;
import java.math.RoundingMode;

public class FloatTest {

	public static void printDecimalFormat(double progress) {
		//占位符可以使用0和#两种，当使用0的时候会严格按照样式来进行匹配，不够的时候会补0，而使用#时会将前后的0进行忽略  
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.FLOOR);	
        // df.setMaximumFractionDigits(1);
        // df.setGroupingSize(0);
        // df.setRoundingMode(RoundingMode.FLOOR);
        System.out.println(df.format(progress));
	}

	public static void printProgress(double progress) {
		System.out.println("progress:" + progress);
		String progressStr;
		if (progress <= 0) {
			progressStr = "0";
		} else if (progress >= 100.0) {
			progressStr = "100";
		} else {
			progressStr = String.format("%.1f", progress);
		}
		System.out.println("update..." + progressStr + "%");
	}

	public static void main(String[] args) {

		// progress:99.94
		// update...99.9%
		// progress:99.95
		// update...100.0%
		printProgress(99.94);  
		printProgress(99.95); 
		printDecimalFormat(99.94);
		printDecimalFormat(99.95);

		printProgress(100f * 99/ 100);  // progress:99.0
		                                // update...99.0%

		printProgress(100f * 99.99/ 100);  // progress:99.99
		                                   // update...100.0%(四舍五入)

		printProgress(100f * 0 / 100);   // update...0%
		printProgress(100f * 100 / 100); // update...100%

		printProgress(100f * 100 / 0);   // progress:Infinity
										 // update...100%

		printProgress(100f * 0 / 0);     // progress:NaN
		                                 // update...NaN%
	}
}
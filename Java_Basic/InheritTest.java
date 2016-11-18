


public class InheritTest {
	
	static class Base {
		private static final int MAX_NUM = 1000;
		private int[] arr = new int[MAX_NUM];
		private int count;
		
		public void add(int number){
			if(count<MAX_NUM){
				arr[count++] = number;    
			}
		}
		
		public void addAll(int[] numbers){
			for(int num : numbers){
				add(num);
			}
		}
	}

	static class Child extends Base {
		
		private long sum;

		@Override
		public void add(int number) {
			super.add(number);
			sum+=number;
		}

		@Override
		public void addAll(int[] numbers) {
			super.addAll(numbers);
			for(int i=0;i<numbers.length;i++){
				sum+=numbers[i];
			}
		}
		
		public long getSum() {
			return sum;
		}
	}

	
	
	public static void main(String[] args)
	{
		Child c = new Child();
		c.addAll(new int[]{1,2,3});
		System.out.println(c.getSum());
	}
}
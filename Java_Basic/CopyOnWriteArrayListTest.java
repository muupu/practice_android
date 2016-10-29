

public class CopyOnWriteArrayListTest {
	
	
	public static void main(String...args) throws InterruptedException
	{
		final CopyOnWriteArrayList<Integer> cowList = new CopyOnWriteArrayList<Integer>();
		for(int i=0;i<10;i++)
		{
			cowList.add(i);
		}
		
		new Thread(){
			@Override
			public void run() {
				for(int i=0;i<cowList.size();i++)
				{
					try {
						Thread.currentThread().sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(cowList.get(i));
				}
//				Iterator<Integer> it = cowList.iterator();
//				while(it.hasNext())
//				{
//					try {
//						Thread.currentThread().sleep(1);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println(it.next());
//				}
			};
		}.start();
		
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.currentThread().sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cowList.clear();
			};
		}.start();
		
	}
}
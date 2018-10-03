/* Practice Thread class.(java.lang.Thread)
 * Especially implement Runnable interface and override run()
 * 
 */
public class Thread1 
{
	
	public static class ImpleRunnable implements Runnable
	{

		@Override
		public void run() {
			for(int i=0;i<10;i++)
			{
				System.out.println("Hey, What's up?!");
				try
				{
					Thread.sleep(1000);
				}
						catch(InterruptedException e) 
						{
							e.printStackTrace();
						}
			}
		}
	}
	
	
	public static class ImpleRunnable2 implements Runnable
	{

		@Override
		public void run() {
			for(int i=0;i<10;i++)
			{
			System.out.println("Are you sure??");
				try
				{
					Thread.sleep(2000);
					}
						catch(InterruptedException e) 
						{
							e.printStackTrace();
						}
				}
		}
	}

	public static void main(String[] args) {

		Runnable threadJob=new ImpleRunnable();
		Thread myThread=new Thread(threadJob);
		myThread.start();
		
		Runnable threadJob2=new ImpleRunnable2();
		Thread myThread2=new Thread(threadJob2);
		myThread2.start();
		
	}
}

//First 2018-10-03-Wed

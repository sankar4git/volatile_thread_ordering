package threadOrderingVolatile;

public class Solution {
	static volatile int counter = 0;
	static int print = 1;
	static char c = 'A';

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread[] ths = new Thread[4];
		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(new MyRunnable(i, ths.length));
			ths[i].start();
		}
	}

	static class MyRunnable implements Runnable {
		final int thID;
		final int total;

		public MyRunnable(int id, int total) {
			thID = id;
			this.total = total;
		}

		@Override
		public void run() {
			while(true) {
				if (thID == counter) {
					System.out.println("thread " + thID + " prints " + c);
					if(c=='Z'){
						c='A';
					}else{
						c=(char)((int)c+1);
					}
					System.out.println("thread " + thID + " prints " + print++);
					counter++;
					if (counter == total)
						counter = 0;
				} else {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// log it
					}
				}
			}
		}

	}

}

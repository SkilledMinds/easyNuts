package multithreading;

public class SerachUsingVolatile {

	// search range starts with
	static int START = 0;
	static int END = 10000;

	public static void main(String[] args) {

		NumberFinder t = new NumberFinder(28000);

		Thread t1 = new Thread(t, "T1");
		Thread t2 = new Thread(t, "T2");
		Thread t3 = new Thread(t, "T3");

		t1.start();
		t3.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t2.start();

	}

}

class NumberFinder implements Runnable {
	volatile boolean keepRunning = true;
	int numToFind;

	NumberFinder(int nf) {
		this.numToFind = nf;
	}

	public void run() {

		int start = SerachUsingVolatile.START;
		int end = SerachUsingVolatile.END;

		// move range of search for each Thread
		SerachUsingVolatile.START = SerachUsingVolatile.START + 10000;
		SerachUsingVolatile.END = SerachUsingVolatile.END + 10000;

		while (keepRunning) {

			for (int i = start; i <= end; i++) {
				if (i == numToFind) {
					keepRunning = false;
					System.out.println("Target found by Thread: " + Thread.currentThread().getName()
							+ " rest all Threads should stop searching !!! ");
				}
			}
		}
		System.out.println(" Search stopped for Thread :" + Thread.currentThread().getName());
	}
}

import java.util.Random;

public class PubSubDemo {

	public static void main(String[] args) {
		SharedQ sharedQ = new SharedQ();

		new Thread(new Consumer(sharedQ), "Consumer").start();
		// Delay publishing
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(new Producer(sharedQ), "Producer").start();
	}

}

class Producer implements Runnable {
	SharedQ sharedQPro = null;

	public Producer(SharedQ sharedQ) {
		this.sharedQPro = sharedQ;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			produce();
		}
	}

	public void produce() {
		synchronized (sharedQPro.sharedQueue) {

			for (int i = 0; i < 3; i++) {
				sharedQPro.sharedQueue[i] = (new Random().nextInt(20));
				System.out.println(" ### Produced ### " + sharedQPro.sharedQueue[i]);
			}
			sharedQPro.sharedQueueIsEmpty = false;
			sharedQPro.sharedQueue.notify();
			try {
				sharedQPro.sharedQueue.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class Consumer implements Runnable {
	SharedQ sharedQCon = null;

	public Consumer(SharedQ sharedQ) {
		this.sharedQCon = sharedQ;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			consume();
		}
	}

	public void consume() {
		synchronized (sharedQCon.sharedQueue) {
			if (sharedQCon.sharedQueueIsEmpty) {
				try {
					sharedQCon.sharedQueue.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			System.out.println();

			for (int i = 0; i < 3; i++) {
				System.out.println(" *** Consumed *** " + sharedQCon.sharedQueue[i]);
				sharedQCon.sharedQueue[i] = 0;
			}
			sharedQCon.sharedQueue.notify();
			System.out.println(" --------------------------");
			try {
				sharedQCon.sharedQueue.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

class SharedQ {

	int[] sharedQueue = new int[3];
	boolean sharedQueueIsEmpty = true;

}
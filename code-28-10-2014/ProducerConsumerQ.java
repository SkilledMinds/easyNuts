// Queue
class CustomerQueue {

	String sharedCustomerQueue[] = new String[5];
}

// Client
public class ProducerConsumerQ {

	static int NEED_TO_SERVE_BATCH = 2;

	public static void main(String[] args) {
		CustomerQueue cq = new CustomerQueue();
		Thread pro = new producer(cq);
		Thread con = new consumer(cq);
		pro.setName("Producer");
		con.setName("Consumer");
		pro.start();
		con.start();

	}

}

class producer extends Thread {

	int count = 1;
	CustomerQueue qp;

	public producer(CustomerQueue q) {
		qp = q;
	}

	@Override
	public void run() {

		while (true) {

			synchronized (qp.sharedCustomerQueue) { // will throw nullPointerException if q.sharedCustomerQueue not
													// initialized
				if (count > ProducerConsumerQ.NEED_TO_SERVE_BATCH) {
					System.out.println("Producer has served " + ProducerConsumerQ.NEED_TO_SERVE_BATCH + " batch");

					// lets release lock before we go
					try {
						qp.sharedCustomerQueue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}

				System.out
						.println(" Queue is empty , putting items : count " + count + " by Thread :" + this.getName());

				for (int p = 0; p < 5; p++) {
					qp.sharedCustomerQueue[p] = (int) ((Math.random() * 10) % 10) + "-cus";
					System.out.println("Genereated item is :" + qp.sharedCustomerQueue[p]);
				}

				try {
					count++;
					qp.sharedCustomerQueue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

}

class consumer extends Thread {

	int count = 1;
	CustomerQueue qc;

	public consumer(CustomerQueue q) {
		qc = q;
	}

	@Override
	public void run() {

		while (true) {

			// Sleep is required to increase fairness of Producer Thread, Otherwise this was executing three times.
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			synchronized (qc.sharedCustomerQueue) {

				// we can check a Thread is having Lock/monitor of one object/class or not.
				System.out.println(" consumer having lock " + holdsLock(qc.sharedCustomerQueue));

				if (count > ProducerConsumerQ.NEED_TO_SERVE_BATCH) {
					System.out.println("consumer has consumed " + ProducerConsumerQ.NEED_TO_SERVE_BATCH + " batch");
					qc.sharedCustomerQueue.notify();
					break;
				}

				for (String qItem : qc.sharedCustomerQueue) {
					System.out.println("Consuming item " + qItem);
				}

				System.out.println("\n");
				qc.sharedCustomerQueue.notify();
			}

			if (++count > ProducerConsumerQ.NEED_TO_SERVE_BATCH) {
				break;
			}

		}

	}

}

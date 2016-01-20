public class DecideDancePair {

	DecideDancePair ddp1;
	StringBuffer ddp2 = new StringBuffer();

	public static void main(String[] args) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (DecideDancePair.class) {
					System.out.println("Its weekend .... Hey Guys !! this is Nancy , who will dance with me? \n");
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {

				synchronized (new DecideDancePair().ddp1) {
					System.out.println("Hey Nancy !! this is Peter , I will dance with you.");
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (new DecideDancePair().ddp2) {
					System.out.println("Hey Nancy !! this is Rozer , I will dance with you.");
				}

			}
		}).start();

	}

}

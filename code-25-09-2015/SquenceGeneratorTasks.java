
// print ODD and Even number alternatively

public class SquenceGeneratorTasks {

	public static boolean oddPrinted = false;

	public static void main(String[] args) {

		Thread oddThread = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 1;
				while (i <= 20) {

					synchronized (SquenceGeneratorTasks.class) {
						if (!oddPrinted) { // make sure printing starting with Odd number and each Odd is followed with a Even number
							System.out.println(i);
							i = i + 2;
							oddPrinted = true;
							SquenceGeneratorTasks.class.notify();
							try {
								SquenceGeneratorTasks.class.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

				}
			}
		});

		Thread evenThread = new Thread(new Runnable() {

			@Override
			public void run() {
				
				System.out.println(" Though you have delayed Odd thread but printing gonna start with Odd number only !!");
				
				int i = 2;
				while (i <= 20) {
					synchronized (SquenceGeneratorTasks.class) {
						if (oddPrinted) {
							System.out.println(i);
							i = i + 2;
							oddPrinted = false;
							SquenceGeneratorTasks.class.notify();
							try {
								SquenceGeneratorTasks.class.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		});

		evenThread.start();
		
		// even if We apply some delay to Odd thread printing will start with ODD number only and 
		// Sequence of Odd-Even-Odd maintained
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		oddThread.start();

	}

}

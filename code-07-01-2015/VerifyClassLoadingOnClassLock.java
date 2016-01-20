public class VerifyClassLoadingOnClassLock {
	public static void main(String[] args) {
		show();
	}

	private static void show() {
		synchronized (SharedQ.class) {
			System.out.println(" Method Show() executing from Main() .... ");
		}
	}
}

 class SharedQ {
	static {
		System.out.println(" Classloader is loading SharedQ ");
	}

	public static void writeStream() {
		// some multiThread code here
	}
}

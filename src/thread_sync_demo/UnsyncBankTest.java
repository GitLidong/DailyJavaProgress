package thread_sync_demo;

public class UnsyncBankTest {
	
	public static void main(String[] args) {
		Bank b = new Bank(100, 1000);
		int i;
		for (i = 0 ; i < 100; i++) {
			TransferRunnable taRunnable = new TransferRunnable(b, i, 1000);
			Thread thread = new Thread(taRunnable);
			thread.start();
		}
	}

}

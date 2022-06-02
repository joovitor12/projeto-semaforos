import java.util.concurrent.Semaphore;
public class Program {

	public static void main(String[] args) {
		int permissions = 1;
		Semaphore southRunway = new Semaphore(permissions);
		Semaphore northRunway = new Semaphore(permissions);
		Semaphore[] runways = new Semaphore[] {southRunway, northRunway};
		
		for (int i = 0; i < 12; i++) {
			Thread threadPlane = new ThreadPlane(runways,i);
			threadPlane.start();
		}
	}

}

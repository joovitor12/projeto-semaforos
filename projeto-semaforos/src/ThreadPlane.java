import java.util.concurrent.Semaphore;
public class ThreadPlane extends Thread{
	private Semaphore[] runways;
	private Integer planeNumber;
	private static int OUT_POSITION;
	public ThreadPlane(Semaphore[] runways, Integer planeNumber) {
		super();
		this.runways = runways;
		this.planeNumber = planeNumber;
	}
	
	@Override
	public void run() {
		int selectedRunway = 0;
		try {
			sleep((int) (Math.random() * 1000));
			maneuver();
			taxi();
			selectedRunway = runwayChoose();
			runways[selectedRunway].acquire();
			takeOff(selectedRunway);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			runways[selectedRunway].release();
			fly();
		}
	}
	
	private int runwayChoose() {
		int selectedRunway = (int) Math.round(Math.random());
		if(runways[selectedRunway].availablePermits() != 0) {
			return selectedRunway;
		} else {
			if(runways[0].getQueueLength() <= runways[1].getQueueLength()) {
				return 0;
			} else {
				return 1;
			}
		}
	}
	
	public void maneuver() {
		System.out.println("Plane " + planeNumber + " is manobrating...");
		int time = (int) ((Math.random() * 4000) + 3000);
		try {
			sleep(time);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Plane " + planeNumber + " manobrated in " + time +"s.");
	}
	
	public void taxi() {
		System.out.println("Plane " + planeNumber + " is taxing...");
		int time = (int) ((Math.random() * 5000) + 5000);
		try {
			sleep(time);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void takeOff(int selectedRunway) {
		String runwayName = selectedRunway == 0 ? "south" : "north";
		System.out.println("Plane " + planeNumber + " is decolating in runway " + runwayName);
		int time = (int) ((Math.random() * 3000) + 1000);
		try {
			sleep(time);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Plane " + planeNumber + " decolated in runway " + runwayName);
	}
	
	public void fly() {
		System.out.println("Plane " + planeNumber + " is getting far...");
		int time = (int) ((Math.random() * 5000) + 3000);
		try {
			sleep(time);
		}catch(Exception e) {
			e.printStackTrace();
		}
		OUT_POSITION++;
		System.out.println("Plane " + planeNumber + " was the " + OUT_POSITION + "th to get far in " + time + "s.");
		
		
	}
} 

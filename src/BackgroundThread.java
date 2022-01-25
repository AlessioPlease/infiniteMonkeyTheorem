import java.util.*;
import java.time.*;

public class BackgroundThread {

	Timer statusTimer;
	private static final int updateInterval = 5000;

	private final Thread t = new Thread(() -> {

		statusTimer = new Timer();
		statusTimer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				update();
			}
		}, 0, updateInterval);
	});

	BackgroundThread() {
		t.start();
	}



	private void update() {
		Instant start = Monkey.getStartTime() != null ? Monkey.getStartTime() : Instant.now();
		Instant now = Instant.now();
		Duration timeElapsed = Duration.between(start, now);

		printStatus(Monkey.getAttempts(), timeElapsed);
	}



	private void printStatus(long attempts, Duration timeElapsed) {

		System.out.println("Current number of attempts: " + attempts);
		if (timeElapsed.toMinutes() == 0) {
			System.out.println("Time elapsed: " + timeElapsed.toSecondsPart() + " seconds");
		} else if (timeElapsed.toMinutes() == 1){
			System.out.println("Time elapsed: " + timeElapsed.toMinutes() + " minute and " + timeElapsed.toSecondsPart() + " seconds");
		} else if (timeElapsed.toHours() == 0){
			System.out.println("Time elapsed: " + timeElapsed.toMinutes() + " minutes and " + timeElapsed.toSecondsPart() + " seconds");
		} else if (timeElapsed.toHours() == 1) {
			System.out.println("Time elapsed: : " + timeElapsed.toHours() + " hour " + timeElapsed.toMinutesPart() + " minutes and " + timeElapsed.toSecondsPart() + " seconds");
		} else {
			System.out.println("Time elapsed: " + timeElapsed.toHours() + " hours " + timeElapsed.toMinutesPart() + " minutes and " + timeElapsed.toSecondsPart() + " seconds");
		}
	}



	public void shutdown() {
		statusTimer.cancel();
		statusTimer.purge();
		t.interrupt();
	}
}

import java.util.*;
import java.time.*;

public class Monkey {
	private static final String[] characters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"," "};
	private static final ArrayList<String> chars = new ArrayList<>(Arrays.asList(characters));
	private static long attempts = 0;
	private static Instant start;

	public static void main(String[] args) {
		String key = inputKey();
		String word;

		BackgroundThread statusThread = new BackgroundThread();
		start = Instant.now();
		word = generateWord();
		while (!word.equals(key)) {
			attempts++;
			word = generateWord();
		}
		Instant finish = Instant.now();
		statusThread.shutdown();

		Duration timeElapsed = Duration.between(start, finish);
		printResults(word, attempts, timeElapsed);
	}



	private static String generateWord() {

		Random rand = new Random();
		StringBuilder word = new StringBuilder();
		String character;
		int c = 0;

		character = chars.get(rand.nextInt(chars.size()));
		while (!character.equals(" ") && c < 51) {
			word.append(character);
			c++;
			character = chars.get(rand.nextInt(chars.size()));
		}
		return word.toString();
	}



	private static String inputKey() {
		Scanner input = new Scanner(System.in);
		String key;

		System.out.println("Type word to match: ");
		key = input.nextLine();
		input.close();

		System.out.println("\"" + key + "\" has " + key.length() + " characters.");
		System.out.println("The chances of randomly typing this word are 1 on " + Math.pow(chars.size(), key.length() + 1));
		System.out.println("Let's go!");

		return key;
	}



	private static void printResults(String word, long attempts, Duration timeElapsed) {

		System.out.println("\n" + word);

		if (timeElapsed.toMinutes() == 0) {
			System.out.println(attempts + " attempts have been made in " + timeElapsed.toSecondsPart() + " seconds");
		} else if (timeElapsed.toMinutes() == 1){
			System.out.println(attempts + " attempts have been made in " + timeElapsed.toMinutes() + " minute and " + timeElapsed.toSecondsPart() + " seconds");
		} else if (timeElapsed.toHours() == 0){
			System.out.println(attempts + " attempts have been made in " + timeElapsed.toMinutes() + " minutes and " + timeElapsed.toSecondsPart() + " seconds");
		} else if (timeElapsed.toHours() == 1) {
			System.out.println(attempts + " attempts have been made in " + timeElapsed.toHours() + "hour" + timeElapsed.toMinutesPart() + " minutes and " + timeElapsed.toSecondsPart() + " seconds");
		} else {
			System.out.println(attempts + " attempts have been made in " + timeElapsed.toHours() + "hours" + timeElapsed.toMinutesPart() + " minutes and " + timeElapsed.toSecondsPart() + " seconds");
		}

		if (timeElapsed.toSeconds() != 0) {
			System.out.println("Average attempts per second: " + (attempts / timeElapsed.toSeconds()));
		}
	}



	public static long getAttempts() {
		return attempts;
	}

	public static Instant getStartTime() {
		return start;
	}
}

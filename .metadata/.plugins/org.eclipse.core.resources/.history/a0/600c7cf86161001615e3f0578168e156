package functionality;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class IO {

	private static Scanner sc;

	public static void print(String message) {
		System.out.print(message);
	}

	public static void printLine(String message) {
		System.out.println(message);

	}

	public static String readLine() {
		sc = new Scanner(System.in);
		String input;
		try {
			input = sc.nextLine();
		} catch (NoSuchElementException e) {
			sc.close();
			return null;
		}

		return input;
	}
}

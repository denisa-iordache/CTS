package ro.ase.acs.modified;

import java.util.Scanner;

import ro.ase.acs.contacts.Reader;

public class ConsoleReader implements Reader {

	@Override
	public String read() {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		
		return string;
	}

}

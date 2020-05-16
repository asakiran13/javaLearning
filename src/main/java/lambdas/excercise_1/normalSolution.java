package lambdas.excercise_1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class normalSolution {

	private static void sortOnLastName(List<Person> personList){
		Collections.sort(personList, new Comparator<Person>() {
			@Override public int compare(Person p1, Person p2) {
				return p1.lastName.compareTo(p2.lastName);
			}
		});
	}

	private static void printList(List<Person> personList){
		for(Person person : personList){
			System.out.println((person));
		}
	}

	private static void printLastNameBeginningWithC(List<Person> personList){
		for(Person person : personList){
			if(person.getLastName().startsWith("c")) System.out.println((person));
		}
	}

	public static void main(String[] args){

		List<Person> personList = Arrays.asList(
				new Person("ram", "rahim", 20),
				new Person("first", "seeta", 18),
				new Person("first", "laxman", 16),
				new Person("first", "cathy", 25)
		);

		sortOnLastName(personList);

		printList(personList);

		System.out.println("");

		printLastNameBeginningWithC(personList);
	}
}

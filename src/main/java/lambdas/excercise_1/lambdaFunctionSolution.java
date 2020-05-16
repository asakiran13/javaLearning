package lambdas.excercise_1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class lambdaFunctionSolution {

	private static void sortOnLastName(List<Person> personList){
		Collections.sort(personList, (p1, p2) -> p1.lastName.compareTo(p2.lastName));
	}

	private static void printList(List<Person> personList){
		for(Person person : personList){
			System.out.println((person));
		}
	}

	private static void printOnCondition(List<Person> personList, Predicate<Person> predicate){
		for(Person person : personList){
			if(predicate.test(person)) System.out.println((person));
		}
	}


	public static void main(String[] args) {

		List<Person> personList = Arrays.asList(
				new Person("ram", "rahim", 20),
				new Person("first", "seeta", 18),
				new Person("first", "laxman", 16),
				new Person("first", "cathy", 25)
		);

		sortOnLastName(personList);

		printOnCondition(personList, p -> p.lastName.startsWith("c"));

	}
}

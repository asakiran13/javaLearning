package lambdas;

public class MethodReferenceExample {

	public static void main(String[] args){

		Thread t1 = new Thread(() -> printMessage());

		Thread t2 = new Thread(MethodReferenceExample::printMessage);

		t1.run();
		t2.run();

	}

	private static void printMessage(){
		System.out.println("Hello world");
	}
}

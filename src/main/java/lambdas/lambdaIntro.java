package lambdas;


public class lambdaIntro {

	public static void main(String[] args){

		// A class that implements Greeting interface
		HelloGreeting helloGreeting = new HelloGreeting();

		//lambdaExpression of greeting interface
		Greeting lambdaGreeting = () -> System.out.println("Hello world");


		// inner class implementation of Greeting interface
		Greeting innerClassGreeting = new Greeting(){
			public void perform(){
				System.out.println("Hello World");
			}
		};

		helloGreeting.perform();
		lambdaGreeting.perform();
		innerClassGreeting.perform();
	}
}

interface Greeting {

	public void perform();
}

class HelloGreeting implements Greeting {

	@Override
	public void perform(){
		System.out.println("Hello World");
	}
}

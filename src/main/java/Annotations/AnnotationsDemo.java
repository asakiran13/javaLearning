package Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface SmartPhone{

	String os() default "Symbian";

	int version() default 1;
}

@SmartPhone(os="Android", version = 1)
class NokiaASeries{

	String model;

	int size;

	NokiaASeries(String model, int size){
		this.model = model;
		this.size = size;
	}

}
public class AnnotationsDemo {

	public static void main(String[] args) {

		NokiaASeries nokiaASeries = new NokiaASeries("A7", 13);

		Class c = nokiaASeries.getClass();
		SmartPhone s = (SmartPhone) c.getAnnotation(SmartPhone.class);
		System.out.println(s.os());
	}
}

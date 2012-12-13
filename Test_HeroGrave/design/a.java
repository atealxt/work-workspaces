import java.util.*;
import java.util.regex.*;

class a{
	
	public static void main(String args[]){
		
		String source = "http://localhost:8080/Test_HeroGrave/picture/default/face/onion.jpg";
		String pattern ="picture";
		

		System.out.println(source.substring(source.indexOf(pattern)));
		
	}
	
}
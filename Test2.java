import java.util.ArrayList;
 
 
public class Test2 {
 
	public static void main(String[] args) {
		
		System.out.println("Liste de String");
		System.out.println("------------------------------");
		ArrayList<String> listeString= new ArrayList<String>();
		listeString.add("Une chaîne");
		listeString.add("Une Autre");
		listeString.add("Encore une autre");
		listeString.add("Allez, une dernière");
		
		for(String str : listeString)
			System.out.println(str);
		
		
 
		System.out.println("\nListe de float");
		System.out.println("------------------------------");
		
		ArrayList<Float> listeFloat = new ArrayList<Float>();
		listeFloat.add(12.25f);
		listeFloat.add(15.25f);
		listeFloat.add(2.25f);
		listeFloat.add(128764.25f);
		
		for(float f : listeFloat)
			System.out.println(f);
	}

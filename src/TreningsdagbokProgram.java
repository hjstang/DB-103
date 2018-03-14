import java.util.Scanner;

public class TreningsdagbokProgram {
	
	public String insertionØkt() {
		
		ArrayList<String> data = new ArrayList<>();
		
		Scanner in2 = new Scanner(system.in)
		
		System.out.println("Du valgte innsetting, hvilken dato var treningen? (yyyymmdd)");
		
		data.add(in2.next()); 
		
		System.out.println("Når startet økten? (hhmmss)");
		
		data.add(in2.next()); 
		
		System.out.println("Varighet? (hhmmss)");
		
		data.add(in2.next());
		
		System.out.println("Info om øvelser?");
		
		data.add(in2.next());
		
		System.out.println("Personlig form? (1-10)");
		
		data.add(in2.next());
		
		System.out.println("Prestasjon? (1-10)");
		
		data.add(in2.next());
		
		System.out.println("Notat til økten?");
		
		data.add(in2.next());
		
		System.out.println("Skal det være en mal? (TRUE/FALSE)");
		
		data.add(in2.next());
		
		String querry = "insert into Økt values ("; 
		
		for (int i = 0; i < 6; i++ ){
			querry += data.get(i) + ", "; 
		}
		
		querry += data.get(6) + ");"; 
		
		System.out.println(querry);
		
		return querry;
	}
	
	public static void main(String[] args) {
		
		TreningsdagbokProgram p = new TreningsdagbokProgram(); 
		
		p.insertionØkt(); 
		
		/*ConnectionSQL sql = new ConnectionSQL(); 
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Hva øsnker du å gjøre? (Innsetting (1), Oppgatering(2), Rapport(3))");
		
		String input = in.next(); 
		
		if (input.equals("1")){
			String query = insertion(); 
		}
		*/
	}
	
}


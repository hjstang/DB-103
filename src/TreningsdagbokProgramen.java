import java.util.Scanner;

public class TreningsdagbokProgramen {
	
public static void main(String[] args) throws Exception { 
	
		String answer; 
		ConnectionSQL sql = new ConnectionSQL();
		Treningsdagbok tr = new Treningsdagbok(); 
		Scanner in = new Scanner(System.in);
		
		do{
			System.out.println("Hva ønsker du å gjøre? (Registrering av økt(1), Treningsrapport(2), Resultatlogg(3), "
					+ "Lag øvelsesgrupper(4), Se øvelsesgrupper(5) Valgfritt usecase(6) )");
			answer = in.next(); 

			if (answer.equals("1")){
				tr.addØkt(); 
			} else if(answer.equals("2")){
				System.out.println(sql.getRapport());
			} else if(answer.equals("3")){
				System.out.println(sql.getResultatlogg()); 
			} else if (answer.equals("4")) {
				System.out.print(tr.addØvelsesgruppe());
			}  else if (answer.equals("5")) {
				System.out.print(sql.getØvelsesgruppe());
			}  else if (answer.equals("6")) {
				System.out.print(sql.valgfrittUsecase());
			}
			
			System.out.println("Vil du fortsette? ja/nei");
			answer = in.next(); 
		} while(answer.equals("ja"));
	
		in.close();
	}

}


import java.util.Scanner;

public class TreningsdagbokProgramen {
	
public static void main(String[] args) throws Exception { 
	
		String answer; 
		ConnectionSQL sql = new ConnectionSQL(); 
		Treningsdagbok tr = new Treningsdagbok(); 
		Scanner in = new Scanner(System.in);
		
		do{
			System.out.println("Hva øsnker du å gjøre? (Innsetting (1), Statistikk(2), Rapport(3))");
			answer = in.next(); 

			if (answer.equals("1")){
				tr.addØkt(); 
			} else if(answer.equals("2")){
				System.out.println(sql.getStatistikk());
			} else if(answer.equals("3")){
				System.out.println(sql.getRapport()); 
			}
			
			System.out.println("Vil du fortsette? ja/nei");
			answer = in.next(); 
		} while(answer.equals("ja"));
	
		in.close();
	}

}


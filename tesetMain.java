


public class tesetMain {
	public static void main(String [] args) {
		Context context = new InitialContext();
		DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myDB");
		
	}

}

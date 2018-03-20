import java.sql.Date;

public class Main extends ConnectionSQL{
	
	public void init() {
		
	}
	
	public void run() {
		
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection myconn = new Main().connect();
		System.out.println("Testing");
		Date dateStart = new Date(2017,4,4);
		Date dateEnd = new Date(2019,4,4);
		System.out.print(dateStart.getYear());
		
		System.out.println(AdminController.getExerciseResult(myConn, dateStart,dateEnd));
	}

}

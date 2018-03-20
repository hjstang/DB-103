
public class Treningsokt {

	import java.sql.Date;
	import java.sql.Time;
	import java.util.ArrayList;
	import java.util.List;

	public class Workout {
		
		private Date dato;
		private Time tidspunkt;
		private int varighet;
		private int personligForm;
		private int prestasjon;
		private String notat;
		
		
		private List<Exercise> exerciseList;
		
		public Workout(Date dato, Time tidspunkt, int varighet, int personligForm, int prestasjon, String notat){
			this.dato = dato;
			this.tidspunkt = tidspunkt;
			this.varighet = varighet;
			this.personligForm = personligForm;
			this.prestasjon = prestasjon;
			this.notat = notat;
			
			this.exerciseList = new ArrayList<Exercise>();
		}
		
		
		public void addExercise(Exercise ex) {
			this.exerciseList.add(ex);
		}
		
		public Date getDato() {
			return dato;
		}
			
		
		
		

	}
	
	
	
}

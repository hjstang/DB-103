
public class Treningsokt {

	import java.sql.Date;
	import java.sql.Time;
	import java.util.ArrayList;
	import java.util.List;

	public class Treningsokt {
		
		private Date dato;
		private Time tidspunkt;
		private int varighet;
		private int personligForm;
		private int prestasjon;
		private String notat;
		
		
		private List<Ovelse> ovelsesListe;
		
		public Treningsokt(Date dato, Time tidspunkt, int varighet, int personligForm, int prestasjon, String notat){
			this.dato = dato;
			this.tidspunkt = tidspunkt;
			this.varighet = varighet;
			this.personligForm = personligForm;
			this.prestasjon = prestasjon;
			this.notat = notat;
			
			this.ovelsesListe = new ArrayList<Ovelse>();
		}
		
		
		public void addExercise(Ovelse ex) {
			this.ovelsesListe.add(ex);
		}
		
		public Date getDato() {
			return dato;
		}
	}
}

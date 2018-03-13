
public class friOvelse extends ovelse{

	public String beskrivelse;

	public friOvelse(String navn, String beskrivelse) {
		super(navn); 
		this.beskrivelse=beskrivelse;
		// TODO Auto-generated constructor stub
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

}

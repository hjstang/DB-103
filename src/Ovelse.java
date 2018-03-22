
public class Ovelse {
	
	private String navn, beskrivelse, apparat, apparatBeskrivelse;
	private int kg,sett;
	
	
	
	public Ovelse(String navn, int kg, int sett) {
		this.navn = navn;
		this.kg = kg;
		this.sett = sett;
	}
	
	public Ovelse(String navn, String beskrivelse) {
		this.navn = navn;
		this.beskrivelse = beskrivelse;
	}
	
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	
	public void setApparat(String apparat) {
		this.apparat = apparat;
	}
	
	public void setApparatBeskrivelse(String apparatBeskrivelse) {
		this.apparatBeskrivelse = apparatBeskrivelse;
	}
	public String getNavn() {
		return this.navn;
	}
}


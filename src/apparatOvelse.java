
public class apparatOvelse extends ovelse{

	public int antKilo;
	public int antSett;

	public apparatOvelse(String navn, int antKilo, int antSett, apparat apparatNavn) {
		super(navn);
		this.antKilo=antKilo;
		this.antSett=antSett;
		// TODO Auto-generated constructor stub
	}

	public int getAntKilo() {
		return antKilo;
	}

	public void setAntKilo(int antKilo) {
		this.antKilo = antKilo;
	}

	public int getAntSett() {
		return antSett;
	}

	public void setAntSett(int antSett) {
		this.antSett = antSett;
	}
	
	

}

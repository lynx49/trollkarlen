package trollkarlen;

public class Fiende extends Varelse
{
	//typer fienden kan vara: vanlig, eld, sten, vatten
	private String fiendetyp;
	
	//svaghet mot: 0: inget, 1: värme, 2: kyla, 3: frätande
	private int svaghet;
	//styrka mot: 0: inget, 1: värme, 2: kyla, 3: frätande
	private int styrka;
	
	//livet fienden ursprungligen har, används för att räkna ut hur mycket spelaren ska få när de vinner
	private int originalLiv;
	
	public Fiende (int liv, int kraft, boolean[] påverkadAv, int[] påverkadTid, String fiendetyp, int svaghet, int styrka, int originalLiv)
	{
		super (liv, kraft, påverkadAv, påverkadTid);
		this.fiendetyp = fiendetyp;
		this.svaghet = svaghet;
		this.styrka = styrka;
		this.originalLiv = originalLiv;
	}
	
	public void setFiendetyp(String fiendetyp)
    {
        this.fiendetyp = fiendetyp;
    }
	
	public void setSvaghet(int svaghet)
    {
        this.svaghet = svaghet;
    }
	
	public void setStyrka(int styrka)
    {
        this.styrka = styrka;
    }
	
	public String getFiendetyp() { return fiendetyp; }
	
	public int getSvaghet() { return svaghet; }
	
	public int getStyrka() { return styrka; }
	
	public int getOriginalLiv() { return originalLiv; }
}

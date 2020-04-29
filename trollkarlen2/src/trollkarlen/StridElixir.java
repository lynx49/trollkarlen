package trollkarlen;

public class StridElixir {
	private int livP�verkan;
	private int extraP�verkan;
	private int livP�verkanTid;
	private int bed�vning;
	private int kraftP�verkan;
	private int ph;
	private int celsius;
	private int v�nlighet;
	private int magiKostnad;
	
	public StridElixir (int livP�verkan, int extraP�verkan, int livP�verkanTid, int bed�vning, int kraftP�verkan, int ph, int celsius, int v�nlighet, int magiKostnad)
	{
		this.livP�verkan = livP�verkan;
		this.extraP�verkan = extraP�verkan;
		this.livP�verkanTid = livP�verkanTid;
		this.bed�vning = bed�vning;
		this.kraftP�verkan = kraftP�verkan;
		this.ph = ph;
		this.celsius = celsius;
		this.v�nlighet = v�nlighet;
		this.magiKostnad = magiKostnad;
	}
	
	public void setLivP�verkanTid(int livP�verkanTid)
    {
        this.livP�verkanTid = livP�verkanTid;
    }
	
	public void setBed�vning(int bed�vning)
    {
        this.bed�vning = bed�vning;
    }
	
	public void setPh(int ph)
    {
        this.ph = ph;
    }
	
	public void setCelsius(int celsius)
    {
        this.celsius = celsius;
    }
	
	public int getLivP�verkan() { return livP�verkan; }
	
	public int getExtraP�verkan() { return extraP�verkan; }
	
	public int getLivP�verkanTid() { return livP�verkanTid; }
	
	public int getBed�vning() { return bed�vning; }
	
	public int getKraftP�verkan() { return kraftP�verkan; }
	
	public int getPh() { return ph; }
	
	public int getCelsius() { return celsius; }
	
	public int getV�nlighet() { return v�nlighet; }
	
	public int getMagiKostnad() { return magiKostnad; }
}

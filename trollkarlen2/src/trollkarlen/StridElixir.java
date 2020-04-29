package trollkarlen;

public class StridElixir {
	private int livPåverkan;
	private int extraPåverkan;
	private int livPåverkanTid;
	private int bedövning;
	private int kraftPåverkan;
	private int ph;
	private int celsius;
	private int vänlighet;
	private int magiKostnad;
	
	public StridElixir (int livPåverkan, int extraPåverkan, int livPåverkanTid, int bedövning, int kraftPåverkan, int ph, int celsius, int vänlighet, int magiKostnad)
	{
		this.livPåverkan = livPåverkan;
		this.extraPåverkan = extraPåverkan;
		this.livPåverkanTid = livPåverkanTid;
		this.bedövning = bedövning;
		this.kraftPåverkan = kraftPåverkan;
		this.ph = ph;
		this.celsius = celsius;
		this.vänlighet = vänlighet;
		this.magiKostnad = magiKostnad;
	}
	
	public void setLivPåverkanTid(int livPåverkanTid)
    {
        this.livPåverkanTid = livPåverkanTid;
    }
	
	public void setBedövning(int bedövning)
    {
        this.bedövning = bedövning;
    }
	
	public void setPh(int ph)
    {
        this.ph = ph;
    }
	
	public void setCelsius(int celsius)
    {
        this.celsius = celsius;
    }
	
	public int getLivPåverkan() { return livPåverkan; }
	
	public int getExtraPåverkan() { return extraPåverkan; }
	
	public int getLivPåverkanTid() { return livPåverkanTid; }
	
	public int getBedövning() { return bedövning; }
	
	public int getKraftPåverkan() { return kraftPåverkan; }
	
	public int getPh() { return ph; }
	
	public int getCelsius() { return celsius; }
	
	public int getVänlighet() { return vänlighet; }
	
	public int getMagiKostnad() { return magiKostnad; }
}

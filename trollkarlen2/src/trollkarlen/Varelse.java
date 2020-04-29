package trollkarlen;

public class Varelse
{
	//1.hp, 2.attackskada, 3.vilken typ av elixir en varelse �r p�verkad av, 4.hur l�nge de �r p�verkade av ett elixir
	protected int liv;
	protected int kraft;
	//egentligen beh�vs inte denna vektor. Om p�verkadTid[x] > 0, �r det detsamma som p�verkadAv[x] == true. Det fungerar bra som det �r just nu, att fixa det skulle enbart f� koden att se b�ttre ut och det skulle ta on�digt mycket tid
	protected boolean[] p�verkadAv = new boolean[5];
	protected int[] p�verkadTid = new int[5];
	
	public Varelse (int liv, int kraft, boolean[] p�verkadAv, int[] p�verkadTid)
	{
		this.liv = liv;
		this.kraft = kraft;
		this.p�verkadAv = p�verkadAv;
		this.p�verkadTid = p�verkadTid;
	}
	
	public void setLiv(int liv)
    {
        this.liv = liv;
    }

    public void setKraft(int kraft)
    {
        this.kraft = kraft;
    }

    public int getLiv() { return liv; }

    public int getKraft() { return kraft; }
}
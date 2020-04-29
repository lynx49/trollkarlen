package trollkarlen;

public class Varelse
{
	//1.hp, 2.attackskada, 3.vilken typ av elixir en varelse är påverkad av, 4.hur länge de är påverkade av ett elixir
	protected int liv;
	protected int kraft;
	//egentligen behövs inte denna vektor. Om påverkadTid[x] > 0, är det detsamma som påverkadAv[x] == true. Det fungerar bra som det är just nu, att fixa det skulle enbart få koden att se bättre ut och det skulle ta onödigt mycket tid
	protected boolean[] påverkadAv = new boolean[5];
	protected int[] påverkadTid = new int[5];
	
	public Varelse (int liv, int kraft, boolean[] påverkadAv, int[] påverkadTid)
	{
		this.liv = liv;
		this.kraft = kraft;
		this.påverkadAv = påverkadAv;
		this.påverkadTid = påverkadTid;
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
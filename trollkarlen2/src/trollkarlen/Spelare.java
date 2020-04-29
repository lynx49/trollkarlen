package trollkarlen;

import java.util.Random;

import javax.swing.JDialog;

public class Spelare extends Varelse
{
	private String namn;
	private int pengar;
	private int magi;
	private int sömn;
	//variabel för när spelaren kan sova
	private boolean sömnTid = false;
	
	public Spelare (int liv, int kraft, boolean[] påverkadAv, int[] påverkadTid, String namn, int pengar, int magi, int sömn)
	{
		super (liv, kraft, påverkadAv, påverkadTid);
		this.namn = namn;
		this.pengar = pengar;
		this.magi = magi;
		this.sömn = sömn;
	}
	
	public void setNamn(String namn)
    {
        this.namn = namn;
    }
	
	public void setPengar(int pengar)
    {
        this.pengar = pengar;
    }
	
	public void setMagi(int magi)
    {
        this.magi = magi;
    }
	
	public void setSömn(int sömn)
    {
        this.sömn = sömn;
    }
	
	public String getNamn() { return namn; }
	
	public int getPengar() { return pengar; }
	
	public int getMagi() { return magi; }
	
	public int getSömn() { return sömn; }
	
	//kollar om spelaren kan sova
	public void kollaSömn()
	{
		if (sömn <= 25)
		{
			//sovknappen visas
			sömnTid = true;
			stadKlass.sovBtn.setVisible(true);
			skogKlass.sovBtn.setVisible(true);
			butikKlass.sovBtn.setVisible(true);
			verkstadKlass.sovBtn.setVisible(true);
		}
		else
		{
			//sovknappen visas inte
			sömnTid = false;
			stadKlass.sovBtn.setVisible(false);
			skogKlass.sovBtn.setVisible(false);
			butikKlass.sovBtn.setVisible(false);
			verkstadKlass.sovBtn.setVisible(false);
		}
		
		if(sömnTid)
		{
			//VARNING
			//"det börjar bli dags att sova, välj en bra plats"
			if(sömn <= 5)
			{
				//spelaren tvingas sova
				//TEXT
				//"du var för trött och somnade"
				kollaSovPlats();
			}
		}
	}
	
	public void kollaSovPlats()
	{
		switch(trollkarlen.position)
		{
		case "stad":
			sovStad();
			break;
			
		case "skog":
			sovSkog();
			break;
			
		case "butik":
			sovButik();
			break;
			
		case "verkstad":
			sovVerkstad();
			break;
			
		case "strid":
			sovStrid();
			break;
		}
	}
	
	public void sovStad()
	{
		//hälften av orken kommer tillbaka
		trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() + 50);
		//det finns en 25% chans att spelaren blir rånad eller misshandlad (till döden)
		Random dödschans = new Random();
		if(dödschans.nextInt(4) == 3)
		{
			//100 pengar stjäls
			pengar -= 100;
			//om spelaren inte har så mycket blir den misshandlad
			if(pengar < 0)
			{
				pengar = 0;
				liv = liv - 75;
				
				if(liv < 0)
				{
					trollkarlen.dödLabel1.setText("Dödsorsak: sov i staden och blev mördad, för lite pengar att sno");
					stridKlass.dö();
				}
				else
				{
					stadKlass.uppdateraStats();
					//TEXT
					//"du har blivit misshandlad på gatan eftersom du inte hade tillräckligt mycket pengar att ta"
				}
			}
			else
			{
				//TEXT
				//"du blev rånad på 100$ när du sov på gatan"
			}
		}
		verkstadKlass.nyDag();
	}
	
	public void sovSkog()
	{
		//3/4 av orken kommer tillbaka
		trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() + 75);
		//50% chans att bli mörbultad
		Random dödschans = new Random();
		if(dödschans.nextInt(2) == 1)
		{
			liv -= 90;
			if(liv < 0)
			{
				trollkarlen.dödLabel1.setText("Dödsorsak: sov i skogen och blev mördad");
				stridKlass.dö();
			}
			else
			{
				//TEXT
				//"du blev attackerad i skogen när du sov"
			}
		}
		verkstadKlass.nyDag();
	}
	
	public void sovButik()
	{
		trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() + 50);
		
		pengar -= 75;
		if(pengar < 0)
		{
			trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() + (pengar*2)/3);
			trollkarlen.trollkarl.setPengar(0);
			if(sömn <= 10)
			{
				//TEXT
				//"du hade inte tillräckligt med pengar för att få sova i butiken, utsparkad på gatan"
				sovStad();
			}
			else
			{
				//TEXT
				//"du hade inte tillräckligt med pengar att sova en hel natt i butiken, vaknade tidigt"
				verkstadKlass.nyDag();
			}
		}
		else
		{
			//"du sov en natt i butiken för 75$"
			verkstadKlass.nyDag();
		}
	}
	
	public void sovVerkstad()
	{
		//3/4 av orken kommer tillbaka
		trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() + 75);
		//helar spelare upp till 100 hp
		if(trollkarlen.trollkarl.getLiv() < 100)
		{
			if(trollkarlen.trollkarl.getLiv() < 75)
			{
				trollkarlen.trollkarl.setLiv(trollkarlen.trollkarl.getLiv() + 25);
			}
			else
			{
				trollkarlen.trollkarl.setLiv(100);
			}
		}
		//regenererar magi upp till 125
		if(trollkarlen.trollkarl.getMagi() < 125)
		{
			if(trollkarlen.trollkarl.getMagi() < 75)
			{
				trollkarlen.trollkarl.setMagi(trollkarlen.trollkarl.getMagi() + 50);
			}
			else
			{
				trollkarlen.trollkarl.setMagi(125);
			}
		}
		verkstadKlass.nyDag();
	}
	
	public void sovStrid()
	{
		trollkarlen.dödLabel1.setText("Dödsorsak: somnade i strid");
		stridKlass.dö();
	}
}
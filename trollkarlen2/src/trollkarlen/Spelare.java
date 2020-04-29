package trollkarlen;

import java.util.Random;

import javax.swing.JDialog;

public class Spelare extends Varelse
{
	private String namn;
	private int pengar;
	private int magi;
	private int s�mn;
	//variabel f�r n�r spelaren kan sova
	private boolean s�mnTid = false;
	
	public Spelare (int liv, int kraft, boolean[] p�verkadAv, int[] p�verkadTid, String namn, int pengar, int magi, int s�mn)
	{
		super (liv, kraft, p�verkadAv, p�verkadTid);
		this.namn = namn;
		this.pengar = pengar;
		this.magi = magi;
		this.s�mn = s�mn;
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
	
	public void setS�mn(int s�mn)
    {
        this.s�mn = s�mn;
    }
	
	public String getNamn() { return namn; }
	
	public int getPengar() { return pengar; }
	
	public int getMagi() { return magi; }
	
	public int getS�mn() { return s�mn; }
	
	//kollar om spelaren kan sova
	public void kollaS�mn()
	{
		if (s�mn <= 25)
		{
			//sovknappen visas
			s�mnTid = true;
			stadKlass.sovBtn.setVisible(true);
			skogKlass.sovBtn.setVisible(true);
			butikKlass.sovBtn.setVisible(true);
			verkstadKlass.sovBtn.setVisible(true);
		}
		else
		{
			//sovknappen visas inte
			s�mnTid = false;
			stadKlass.sovBtn.setVisible(false);
			skogKlass.sovBtn.setVisible(false);
			butikKlass.sovBtn.setVisible(false);
			verkstadKlass.sovBtn.setVisible(false);
		}
		
		if(s�mnTid)
		{
			//VARNING
			//"det b�rjar bli dags att sova, v�lj en bra plats"
			if(s�mn <= 5)
			{
				//spelaren tvingas sova
				//TEXT
				//"du var f�r tr�tt och somnade"
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
		//h�lften av orken kommer tillbaka
		trollkarlen.trollkarl.setS�mn(trollkarlen.trollkarl.getS�mn() + 50);
		//det finns en 25% chans att spelaren blir r�nad eller misshandlad (till d�den)
		Random d�dschans = new Random();
		if(d�dschans.nextInt(4) == 3)
		{
			//100 pengar stj�ls
			pengar -= 100;
			//om spelaren inte har s� mycket blir den misshandlad
			if(pengar < 0)
			{
				pengar = 0;
				liv = liv - 75;
				
				if(liv < 0)
				{
					trollkarlen.d�dLabel1.setText("D�dsorsak: sov i staden och blev m�rdad, f�r lite pengar att sno");
					stridKlass.d�();
				}
				else
				{
					stadKlass.uppdateraStats();
					//TEXT
					//"du har blivit misshandlad p� gatan eftersom du inte hade tillr�ckligt mycket pengar att ta"
				}
			}
			else
			{
				//TEXT
				//"du blev r�nad p� 100$ n�r du sov p� gatan"
			}
		}
		verkstadKlass.nyDag();
	}
	
	public void sovSkog()
	{
		//3/4 av orken kommer tillbaka
		trollkarlen.trollkarl.setS�mn(trollkarlen.trollkarl.getS�mn() + 75);
		//50% chans att bli m�rbultad
		Random d�dschans = new Random();
		if(d�dschans.nextInt(2) == 1)
		{
			liv -= 90;
			if(liv < 0)
			{
				trollkarlen.d�dLabel1.setText("D�dsorsak: sov i skogen och blev m�rdad");
				stridKlass.d�();
			}
			else
			{
				//TEXT
				//"du blev attackerad i skogen n�r du sov"
			}
		}
		verkstadKlass.nyDag();
	}
	
	public void sovButik()
	{
		trollkarlen.trollkarl.setS�mn(trollkarlen.trollkarl.getS�mn() + 50);
		
		pengar -= 75;
		if(pengar < 0)
		{
			trollkarlen.trollkarl.setS�mn(trollkarlen.trollkarl.getS�mn() + (pengar*2)/3);
			trollkarlen.trollkarl.setPengar(0);
			if(s�mn <= 10)
			{
				//TEXT
				//"du hade inte tillr�ckligt med pengar f�r att f� sova i butiken, utsparkad p� gatan"
				sovStad();
			}
			else
			{
				//TEXT
				//"du hade inte tillr�ckligt med pengar att sova en hel natt i butiken, vaknade tidigt"
				verkstadKlass.nyDag();
			}
		}
		else
		{
			//"du sov en natt i butiken f�r 75$"
			verkstadKlass.nyDag();
		}
	}
	
	public void sovVerkstad()
	{
		//3/4 av orken kommer tillbaka
		trollkarlen.trollkarl.setS�mn(trollkarlen.trollkarl.getS�mn() + 75);
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
		trollkarlen.d�dLabel1.setText("D�dsorsak: somnade i strid");
		stridKlass.d�();
	}
}
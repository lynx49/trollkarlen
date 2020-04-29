package trollkarlen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class stridKlass extends JPanel
{
	//skapar variabler och komponenter
	static BufferedImage bakgrund;
	static JButton flyBtn = new JButton(), attackBtn = new JButton(), elixirV�ljBtn = new JButton(), fiendeBtn = new JButton(), spelareBtn = new JButton();
	//dessa labels ska visa spelarens och fiendens liv, kraft osv
	static JLabel fiendeLivLabel = new JLabel(), spelareLivLabel = new JLabel(), spelareMagiLabel = new JLabel(), fiendeKraftLabel = new JLabel(), spelareKraftLabel = new JLabel(), fiendeStyrkaLabel = new JLabel(), fiendeSvaghetLabel = new JLabel();
	//knappar f�r elixir, i vektor eftersom elixir ocks� �r det och det f�renklar mkt
	static JButton[] elixirBtn = new JButton[5];
	//basfiende
	static Fiende fiende = new Fiende(100, 0, new boolean[5], new int[5], "vanlig", 1, 0, 100);
	//anv�nds f�r att slumpa liv, kraft, styrka osv (sedan f�r typ)
	static Random slumpaTal = new Random();
	//variabler som beh�vs f�r slump
	static int fLiv, fSvaghet, fStyrka;
	//elixirKnapp spelaren tryckt p�
	static int valtElixir = -1;
	//v�ljare ska f�lja v�nighetssystemet (0 = fiende | 1 = ingen/alla | 2 = spelare)
	static int v�ljare = 1;
	//beh�vs f�r att se om spelarna �r bed�vade, om man har klickat p� visa elixir, och om labels f�r rondsammansfattning ska visas
	static boolean fiendeBed�vad, spelareBed�vad, visarElixir, sGiltig, fpGiltig, spGiltig = false;
	//variabler f�r att best�mma hur stor extraskada spelarna tar
	static int fP�verkan, sP�verkan;
	
	stridKlass()
	{
		//pr�var bakgrund
		try
		{
			bakgrund = ImageIO.read(trollkarlen.bakgrunder[4]);
		}
		catch (Exception e)
		{
			System.out.print("fel4");
		}
		
		//fixar layouten
		this.setLayout(null);
		this.add(flyBtn);
		this.add(attackBtn);
		this.add(elixirV�ljBtn);
		this.add(fiendeBtn);
		this.add(spelareBtn);
		this.add(fiendeLivLabel);
		this.add(spelareLivLabel);
		this.add(spelareMagiLabel);
		this.add(fiendeKraftLabel);
		this.add(spelareKraftLabel);
		this.add(fiendeStyrkaLabel);
		this.add(fiendeSvaghetLabel);
		
		//definierar gr�nssnitt
		flyBtn.setBounds((int) Math.round(1 * trollkarlen.widthSize), (int) Math.round(669 * trollkarlen.heightSize), (int) Math.round(97 * trollkarlen.widthSize), (int) Math.round(98 * trollkarlen.heightSize));
		flyBtn.addActionListener(new flyBtnAct());
		flyBtn.setContentAreaFilled(false);
		flyBtn.setFocusPainted(false);
		flyBtn.setFont(new Font("comic sans ms", Font.BOLD, 17));
		flyBtn.setText("Fly");
		
		attackBtn.setBounds((int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(669 * trollkarlen.heightSize), (int) Math.round(97 * trollkarlen.widthSize), (int) Math.round(98 * trollkarlen.heightSize));
		attackBtn.addActionListener(new attackBtnAct());
		attackBtn.setContentAreaFilled(false);
		attackBtn.setFocusPainted(false);
		attackBtn.setFont(new Font("comic sans ms", Font.BOLD, 17));
		attackBtn.setText("Attackera");
		
		elixirV�ljBtn.setBounds((int) Math.round(199 * trollkarlen.widthSize), (int) Math.round(669 * trollkarlen.heightSize), (int) Math.round(97 * trollkarlen.widthSize), (int) Math.round(98 * trollkarlen.heightSize));
		elixirV�ljBtn.addActionListener(new elixirV�ljBtnAct());
		elixirV�ljBtn.setContentAreaFilled(false);
		elixirV�ljBtn.setFocusPainted(false);
		elixirV�ljBtn.setFont(new Font("comic sans ms", Font.BOLD, 17));
		elixirV�ljBtn.setText("V�lj elixir");
		
		//s�tter ut knapparna f�r elixir med ett visst mellanrum
		for (int i = 0; i < 5; i++)
		{
			elixirBtn[i] = new JButton();
			this.add(elixirBtn[i]);
			elixirBtn[i].setBounds((int) Math.round((328 + 97*i) * trollkarlen.widthSize), (int) Math.round(669 * trollkarlen.heightSize), (int) Math.round(97 * trollkarlen.widthSize), (int) Math.round(98 * trollkarlen.heightSize));
			elixirBtn[i].setContentAreaFilled(false);
			elixirBtn[i].setFocusPainted(false);
			elixirBtn[i].setFont(new Font("comic sans ms", Font.BOLD, 17));
			elixirBtn[i].setText("[Inget]");
			elixirBtn[i].setVisible(false);
		}
		
		//m�ste l�gga till specifika actionlisteners
		elixirBtn[0].addActionListener(new elixir1BtnAct());
		elixirBtn[1].addActionListener(new elixir2BtnAct());
		elixirBtn[2].addActionListener(new elixir3BtnAct());
		elixirBtn[3].addActionListener(new elixir4BtnAct());
		elixirBtn[4].addActionListener(new elixir5BtnAct());
		
		fiendeBtn.setBounds((int) Math.round(1150 * trollkarlen.widthSize), (int) Math.round(440 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(175 * trollkarlen.heightSize));
		fiendeBtn.addActionListener(new fiendeBtnAct());
		fiendeBtn.setContentAreaFilled(false);
		fiendeBtn.setFocusPainted(false);
		
		fiendeLivLabel.setBounds((int) Math.round(1150 * trollkarlen.widthSize), (int) Math.round(410 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(25 * trollkarlen.heightSize));
		fiendeLivLabel.setHorizontalAlignment(JLabel.CENTER);
		fiendeLivLabel.setFont(new Font("comic sans ms", Font.BOLD, 17));
		fiendeLivLabel.setOpaque(true);
		fiendeLivLabel.setBackground(new Color(255, 0, 0));
		
		fiendeKraftLabel.setBounds((int) Math.round(1150 * trollkarlen.widthSize), (int) Math.round(375 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(25 * trollkarlen.heightSize));
		fiendeKraftLabel.setHorizontalAlignment(JLabel.CENTER);
		fiendeKraftLabel.setFont(new Font("comic sans ms", Font.BOLD, 17));
		fiendeKraftLabel.setOpaque(true);
		fiendeKraftLabel.setBackground(new Color(0, 255, 0));
		
		fiendeStyrkaLabel.setBounds((int) Math.round(1150 * trollkarlen.widthSize), (int) Math.round(340 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(25 * trollkarlen.heightSize));
		fiendeStyrkaLabel.setHorizontalAlignment(JLabel.CENTER);
		fiendeStyrkaLabel.setFont(new Font("comic sans ms", Font.BOLD, 17));
		fiendeStyrkaLabel.setOpaque(true);
		fiendeStyrkaLabel.setBackground(new Color(0, 255, 255));
		
		fiendeSvaghetLabel.setBounds((int) Math.round(1150 * trollkarlen.widthSize), (int) Math.round(305 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(25 * trollkarlen.heightSize));
		fiendeSvaghetLabel.setHorizontalAlignment(JLabel.CENTER);
		fiendeSvaghetLabel.setFont(new Font("comic sans ms", Font.BOLD, 17));
		fiendeSvaghetLabel.setOpaque(true);
		fiendeSvaghetLabel.setBackground(new Color(255, 255, 0));
		
		spelareBtn.setBounds((int) Math.round(75 * trollkarlen.widthSize), (int) Math.round(440 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(175 * trollkarlen.heightSize));
		spelareBtn.addActionListener(new spelareBtnAct());
		spelareBtn.setContentAreaFilled(false);
		spelareBtn.setFocusPainted(false);
		
		spelareLivLabel.setBounds((int) Math.round(75 * trollkarlen.widthSize), (int) Math.round(375 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(25 * trollkarlen.heightSize));
		spelareLivLabel.setHorizontalAlignment(JLabel.CENTER);
		spelareLivLabel.setFont(new Font("comic sans ms", Font.BOLD, 17));
		spelareLivLabel.setOpaque(true);
		spelareLivLabel.setBackground(new Color(255, 0, 0));
		
		spelareMagiLabel.setBounds((int) Math.round(75 * trollkarlen.widthSize), (int) Math.round(410 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(25 * trollkarlen.heightSize));
		spelareMagiLabel.setHorizontalAlignment(JLabel.CENTER);
		spelareMagiLabel.setFont(new Font("comic sans ms", Font.BOLD, 17));
		spelareMagiLabel.setOpaque(true);
		spelareMagiLabel.setBackground(new Color(0, 0, 255));
		
		spelareKraftLabel.setBounds((int) Math.round(75 * trollkarlen.widthSize), (int) Math.round(340 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(25 * trollkarlen.heightSize));
		spelareKraftLabel.setHorizontalAlignment(JLabel.CENTER);
		spelareKraftLabel.setFont(new Font("comic sans ms", Font.BOLD, 17));
		spelareKraftLabel.setOpaque(true);
		spelareKraftLabel.setBackground(new Color(0, 255, 0));
	}
	
	//s�tter ut bakgrundsbild
	public void paintComponent (Graphics g)
	{
		g.drawImage(bakgrund, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	//spelaren l�mnar striden
	static class flyBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			trollkarlen.position = "skog";
			trollkarlen.Byta(trollkarlen.skog);
			/*
			skulle kunna finnas en m�jlighet att inte fly, anv�nd slumparen.
			kollaAttack();
			kollaSeger();
			 */
		}
	}
	
	//attackera fysiskt
	static class attackBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//om spelaren inte �r bed�vad och har valt n�gon, kan de attackera
			if(!spelareBed�vad)
			{
				if (v�ljare == 0 || v�ljare == 2)
				{
					//attackerar fienden - (25 + spelarens kraft*0,25) hp
					if (v�ljare == 0)
					{
						fiende.setLiv(fiende.getLiv() - 25 - trollkarlen.trollkarl.getKraft()/4);
						trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-25 - trollkarlen.trollkarl.getKraft()/4) + " liv");
						sGiltig = true;
					}
					//attackerar spelaren - (25 + spelarens kraft*0,25) hp
					else
					{
						trollkarlen.trollkarl.setLiv(trollkarlen.trollkarl.getLiv() - 25 - trollkarlen.trollkarl.getKraft()/4);
						trollkarlen.sAttackLabel.setText("Du attackerade dig sj�lv: " + (-25 - trollkarlen.trollkarl.getKraft()/4) + " liv");
						sGiltig = true;
					}
					
					//kollar vad spelarna �r p�verkade av
					kollaAttack();
					//kollar om fienden �r d�d, eller om fienden kan attackera
					kollaSeger();
				}
				else
				{
					//ERROR
					//"v�lj n�gon att attackera"
				}
			}
			else
			{
				kollaAttack();
				kollaSeger();
				trollkarlen.sAttackLabel.setText("Du var bed�vad");
				sGiltig = true;
			}
		}
	}
	
	//on�dig knapp, man trycker p� den och s� visas ens elixir, om man klickar igen visas dem ej
	static class elixirV�ljBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			if(visarElixir)
			{
				//g�m elixir
				for (int i = 0; i < 5; i++)
				{
					
					elixirBtn[i].setVisible(false);
				}
				visarElixir = false;
			}
			else
			{
				//visa tillg�ngliga elixir
				for (int i = 0; i < 5; i++)
				{
						elixirBtn[i].setVisible(true);
				}
				visarElixir = true;
			}
		}
	}
	
	//n�r man trycker p� fienden
	static class fiendeBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			v�ljare = 0;
		}
	}
	//n�r man trycker p� spelaren
	static class spelareBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			v�ljare = 2;
		}
	}
	
	//n�r man trycker p� n�gon av de 5 elixirknapparna
	static class elixir1BtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//kollar s� att det finns elixir f�r det indexet knappen �r p� och att spelaren inte �r bed�vad
			if (!elixirBtn[0].getText().equals("[Inget]"))
			{
				if(!spelareBed�vad)
				{
					valtElixir = 0;
					//anv�nder valt elixir
					anv�ndMagi();
				}
				//om spelare �r bed�vad
				else
				{
					//kollar vad spelarna �r p�verkade av
					kollaAttack();
					//kollar om fienden �r d�d, eller om fienden kan attackera
					kollaSeger();
				}
			}
		}
	}
	static class elixir2BtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//kollar s� att det finns elixir f�r det indexet knappen �r p� och att spelaren inte �r bed�vad
			if(!elixirBtn[1].getText().equals("[Inget]"))
			{
				if(!spelareBed�vad)
				{
					valtElixir = 1;
					//anv�nder valt elixir
					anv�ndMagi();
				}
				//om spelare �r bed�vad
				else
				{
					kollaAttack();
					kollaSeger();
				}
			}
		}
	}
	static class elixir3BtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//kollar s� att det finns elixir f�r det indexet knappen �r p� och att spelaren inte �r bed�vad
			if(!elixirBtn[2].getText().equals("[Inget]"))
			{
				if(!spelareBed�vad)
				{
					valtElixir = 2;
					//anv�nder valt elixir
					anv�ndMagi();
				}
				//om spelare �r bed�vad
				else
				{
					kollaAttack();
					kollaSeger();
				}
			}
		}
	}
	static class elixir4BtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//kollar s� att det finns elixir f�r det indexet knappen �r p� och att spelaren inte �r bed�vad
			if(!elixirBtn[3].getText().equals("[Inget]"))
			{
				if(!spelareBed�vad)
				{
					valtElixir = 3;
					//anv�nder valt elixir
					anv�ndMagi();
				}
				//om spelare �r bed�vad
				else
				{
					kollaAttack();
					kollaSeger();
				}
			}
		}
	}
	static class elixir5BtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//kollar s� att det finns elixir f�r det indexet knappen �r p� och att spelaren inte �r bed�vad
			if(!elixirBtn[4].getText().equals("[Inget]"))
			{
				if(!spelareBed�vad)
				{
					valtElixir = 4;
					//anv�nder valt elixir
					anv�ndMagi();
				}
				//om spelare �r bed�vad
				else
				{
					kollaAttack();
					kollaSeger();
				}
			}
		}
	}
	
	static void kollaSeger()
	{
		//om fiende �r d�d vinner man och �terv�nder till skogen, ny fiende skapas
		if (fiende.getLiv() <= 0)
		{
			seger();
			nyFiende();
			trollkarlen.position = "skog";
			trollkarlen.Byta(trollkarlen.skog);
		}
		//annars attackerar fienden
		else
		{
			fiendeAttack();
		}
		//varje rond tar tid, det �r viktigt f�r spelaren att anv�nda sin tid r�tt och inte strida i on�dan
		trollkarlen.trollkarl.setS�mn(trollkarlen.trollkarl.getS�mn() - 2);
		trollkarlen.trollkarl.kollaS�mn();
	}
	
	static void seger()
	{
		/*
		pengar tj�nade:
		vanlig = 1*25 pengar
		f�r varje +5 hp = +2 pengar (1$/2,5hp)
		 */
		trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() + 25 + (fiende.getOriginalLiv()*2)/5);
		stadKlass.uppdateraStats();
		//TEXT
		//"du har vunnit, [PENGAR]"
	}
	
	static void anv�ndMagi()
	{
		//kollar att elixirets magikostnad inte �r f�r h�g
		if(trollkarlen.trollkarl.getMagi() - verkstadKlass.elixir[valtElixir].getMagiKostnad() >= 0)
		{
			//minskar magin (om det inte visar sig att spelaren inte attackerar n�got)
			trollkarlen.trollkarl.setMagi(trollkarlen.trollkarl.getMagi() - verkstadKlass.elixir[valtElixir].getMagiKostnad());
			
			//de tv� undantagen
			if (verkstadKlass.elixir[valtElixir].getV�nlighet() == 1 && verkstadKlass.elixir[valtElixir].getCelsius() >= 0)
			{
				if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
				{
					//+pengar
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() + 20);
					trollkarlen.sAttackLabel.setText("Du skapade 20$ �t dig sj�lv");
					sGiltig = true;
				}
				else
				{
					//bed�va alla
					fiende.p�verkadAv[valtElixir] = true;
					fiende.p�verkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getBed�vning();
					trollkarlen.trollkarl.p�verkadAv[valtElixir] = true;
					trollkarlen.trollkarl.p�verkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getBed�vning();
					trollkarlen.sAttackLabel.setText("Du bed�vade alla");
					sGiltig = true;
				}
				
				kollaAttack();
				kollaSeger();
			}
			//attack p� fiende
			else if (v�ljare == 0)
			{
				//hur mycket fienden skadas
				kollaFiendeImpakt();
				//hur mycket kraft som p�verkas
				kollaFiendeKraft();
				
				fiende.p�verkadAv[valtElixir] = true;
				
				//fienden blir p�verkad den tid som som �r st�rst utav livP�verkanTid och bed�vning
				//inga nuvarande elixir har b�de och men detta �r �nd� ett bra s�tt att kontrollera framtida fel
				if(verkstadKlass.elixir[valtElixir].getLivP�verkanTid() >= verkstadKlass.elixir[valtElixir].getBed�vning())
				{
					fiende.p�verkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getLivP�verkanTid();
				}
				else
				{
					fiende.p�verkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getBed�vning();
				}
				
				kollaAttack();
				kollaSeger();
			}
			//attack p� sig sj�lv
			else if (v�ljare == 2)
			{
				//beh�ver inte kolla efter styrka eller svaghet (har inga)
				trollkarlen.trollkarl.setLiv(trollkarlen.trollkarl.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade dig sj�lv: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
				//kollar hur kraft p�verkas
				kollaSpelareKraft();
				
				trollkarlen.trollkarl.p�verkadAv[valtElixir] = true;
				
				if(verkstadKlass.elixir[valtElixir].getLivP�verkanTid() >= verkstadKlass.elixir[valtElixir].getBed�vning())
				{
					trollkarlen.trollkarl.p�verkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getLivP�verkanTid();
				}
				else
				{
					trollkarlen.trollkarl.p�verkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getBed�vning();
				}
				
				kollaAttack();
				kollaSeger();
			}
			else
			{
				
				//OM V�LJARE = 1
				//ERROR
				//"v�lj n�gon att attackera"
				trollkarlen.trollkarl.setMagi(trollkarlen.trollkarl.getMagi() + verkstadKlass.elixir[valtElixir].getMagiKostnad());
			}
			
			//uppdaterar magilabel
			spelareMagiLabel.setText("Magi: " + trollkarlen.trollkarl.getMagi());
		}
		else
		{
			//ERROR
			//"du har f�r lite magi kvar f�r detta elixiret"
		}
	}
	
	//kollar skada fr�n elixir p� fiende
	static void kollaFiendeImpakt()
	{
		//Beror p� valtElixir
		//Om fienden �r svag mot en del och stark mot en annan del av attacken, kommer svaghet prioriteras (saker som ska hj�lpa fienden g�r styrka till svaghet och svaghet till styrka, men det fungerar �nd�)
		switch(fiende.getSvaghet()*4 + fiende.getStyrka())
		{
		case 1:
			//v�rmestyrka, kollar om fienden �r attackerad av v�rme eller inte
			if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 2:
			//kylstyrka, kollar om fienden �r attackerad av kyla eller inte
			if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 3:
			//fr�tstyrka
			if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 4:
			//v�rmesvaghet
			if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			break;
			
			//case 5 ogiltigt, kan inte ha styrka OCH svaghet f�r samma sak
			
		case 6:
			//v�rmesvaghet + kylstyrka, kollar om fienden �r attackerad av v�rme, kyla eller inte
			if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			
		case 7:
			//v�rmesvaghet + fr�tstyrka
			if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			
		case 8:
			//kylsvaghet
			if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 9:
			//kylsvaghet + v�rmestyrka
			if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			break;
			
			//se case 5
			
		case 11:
			//kylsvaghet + fr�tstyrka
			if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 12:
			//fr�tsvaghet
			if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 13:
			//fr�tsvaghet + v�rmestyrka
			if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 14:
			//fr�tsvaghet + kylstyrka
			if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivP�verkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
				sGiltig = true;
			}
			break;
			
			//se case 5
			
		default:
			//inget speciellt h�nder
			fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivP�verkan());
			trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivP�verkan() + " liv");
			sGiltig = true;
			break;
		}
	}
	
	//f�rst�rker eller f�rsvagar fiende/spelare
	static void kollaFiendeKraft()
	{
		//minskar kraften till minst -5
		if(fiende.getKraft() - verkstadKlass.elixir[valtElixir].getKraftP�verkan() > -5)
		{
			fiende.setKraft(fiende.getKraft() - verkstadKlass.elixir[valtElixir].getKraftP�verkan());
		}
		else if(fiende.getKraft() - verkstadKlass.elixir[valtElixir].getKraftP�verkan() <= -5)
		{
			//INFO
			//"maximal f�rsvagning f�r fiende har n�tts"
			fiende.setKraft(-5);
		}
		fiendeKraftLabel.setText("Kraft: " + fiende.getKraft());
	}
	static void kollaSpelareKraft()
	{
		//minskar kraften till minst -5
		if(trollkarlen.trollkarl.getKraft() - verkstadKlass.elixir[valtElixir].getKraftP�verkan() > -5)
		{
			trollkarlen.trollkarl.setKraft(trollkarlen.trollkarl.getKraft() - verkstadKlass.elixir[valtElixir].getKraftP�verkan());
		}
		else if(trollkarlen.trollkarl.getKraft() - verkstadKlass.elixir[valtElixir].getKraftP�verkan() <= -5)
		{
			//INFO
			//"maximal f�rsvagning f�r spelare har n�tts"
			trollkarlen.trollkarl.setKraft(-5);
		}
		spelareKraftLabel.setText("Kraft: " + trollkarlen.trollkarl.getKraft());
	}
	
	//kollar extrap�verkan
	static void kollaAttack()
	{
		//antar att spelarna inte �r bed�vade
		fiendeBed�vad = false;
		spelareBed�vad = false;
		
		//�terst�ller gr�nssnittsv�rdena f�r p�verkan
		trollkarlen.fpAttackLabel.setText(null);
		trollkarlen.spAttackLabel.setText(null);
		fP�verkan = 0;
		sP�verkan = 0;
		
		//kollar p� varje elixirs verkan
		for(int i = 0; i < verkstadKlass.elixir.length; i++)
		{
			//kollar alla olika elixir som kan ha verkan p� fienden
			if (fiende.p�verkadTid[i] > 0)
			{
				//minskar tid p�verkad
				fiende.p�verkadTid[i]--;
				
				//om fienden ska vara bed�vad
				if(verkstadKlass.elixir[i].getBed�vning() > 0)
				{
					fiendeBed�vad = true;
				}
				//liv minskar enligt extrap�verkan
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[i].getExtraP�verkan());
				fP�verkan += verkstadKlass.elixir[i].getExtraP�verkan();
			}
			//om elixir i inte har verkan, �r en spelare inte p�verkan
			else
			{
				fiende.p�verkadAv[i] = false;
			}
			
			//kollar alla olika elixir som kan ha verkan p� spelaren, samma som fienden
			if (trollkarlen.trollkarl.p�verkadTid[i] > 0)
			{
				trollkarlen.trollkarl.p�verkadTid[i]--;
				
				if(verkstadKlass.elixir[i].getBed�vning() > 0)
				{
					spelareBed�vad = true;
				}
				
				trollkarlen.trollkarl.setLiv(trollkarlen.trollkarl.getLiv() - verkstadKlass.elixir[i].getExtraP�verkan());
				sP�verkan += verkstadKlass.elixir[i].getExtraP�verkan();
			}
			else
			{
				trollkarlen.trollkarl.p�verkadAv[i] = false;
			}
		}
		//uppdaterar livlabels och stridssammanfattning
		fiendeLivLabel.setText("HP: " + fiende.getLiv());
		spelareLivLabel.setText("HP: " + trollkarlen.trollkarl.getLiv());
		if(fP�verkan == 0)
		{
			trollkarlen.fpAttackLabel.setText(null);
		}
		else
		{
			trollkarlen.fpAttackLabel.setText("Fiende p�verkad: " + -fP�verkan + "hp");
			fpGiltig = true;
		}
		if(sP�verkan == 0)
		{
			trollkarlen.spAttackLabel.setText(null);
		}
		else
		{
			trollkarlen.spAttackLabel.setText("Spelare p�verkad: " + -sP�verkan + "hp");
			spGiltig = true;
		}
		
	}
	
	static void fiendeAttack()
	{
		if(!fiendeBed�vad)
		{
			//basattack - (5 + kraft*0,25) hp mot spelaren
			trollkarlen.trollkarl.setLiv(trollkarlen.trollkarl.getLiv() - 5 - fiende.getKraft()/4);
			spelareLivLabel.setText("HP: " + trollkarlen.trollkarl.getLiv());
			//fiendens handling i ronden uppdateras
			trollkarlen.fAttackLabel.setText("Fienden attackerade dig: " + (-5 - fiende.getKraft()/4) + " liv");
			
			//om spelaren d�r
			if (trollkarlen.trollkarl.getLiv() <= 0)
			{
				trollkarlen.d�dLabel1.setText("D�dsorsak: dog i strid");
				d�();
			}
		}
		else
		{
			trollkarlen.fAttackLabel.setText("Fienden var bed�vad");
		}
		//sammanfattning av rond
		attackNotifikation();
	}
	
	static void nyFiende()
	{
		//fiende kan f� liv fr�n 100 till 125
		fLiv = 100 + slumpaTal.nextInt(26);
		//slumpar ut olika sorters styrka och svaghet
		do
		{
			fSvaghet = slumpaTal.nextInt(4);
			fStyrka = slumpaTal.nextInt(4);
		} while (fSvaghet == fStyrka);
		
		//M�STE SLUMPA TYP SEN
		fiende = new Fiende(fLiv, 0 + slumpaTal.nextInt(16), new boolean[]{false, false, false, false, false}, new int[5], "vanlig", fSvaghet, fStyrka, fLiv);
	}
	
	//spelaren d�r, blir informerad om sin d�d, allting �terst�lls och spelaren �terv�nder till meny d�r de kan p�b�rja nytt spel
	static void d�()
	{
		JDialog dialogen = new JDialog(trollkarlen.f�nstret, "D�d");
		JPanel jDPanel = new JPanel();
		jDPanel.setLayout(null);
		jDPanel.add(trollkarlen.d�dLabel1);
		trollkarlen.d�dLabel1.setBounds(20, 10, 300, 20);
		trollkarlen.d�dLabel1.setHorizontalAlignment(JLabel.CENTER);
		jDPanel.add(trollkarlen.menyLabel);
		trollkarlen.menyLabel.setBounds(20, 30, 300, 20);
		trollkarlen.menyLabel.setHorizontalAlignment(JLabel.CENTER);
		dialogen.add(jDPanel);
		dialogen.setSize(360, 150);
		dialogen.setVisible(true);
		verkstadKlass.hyra = 10;
		verkstadKlass.hyresAnm�rkning = 0;
		verkstadKlass.verkstadKvar = true;
		stadKlass.verkstadBtn.setVisible(true);
		startMeny.nyttSpelBtn.setText("Nytt spel");
		startMeny.namnLabel.setVisible(false);
		startMeny.namnSkriv.setVisible(false);
		trollkarlen.Byta(trollkarlen.meny);
	}
	
	//sammanfattning av rond, visar spelarens attack, extra p�verkan och fiendens attack
	static void attackNotifikation()
	{
		JDialog dialogen = new JDialog(trollkarlen.f�nstret, "Attacksammanfattning");
		JPanel jDPanel = new JPanel();
		jDPanel.setLayout(null);
		//spelarens attack h�gst upp i panelen som skapas
		if(sGiltig)
		{
			jDPanel.add(trollkarlen.sAttackLabel);
			trollkarlen.sAttackLabel.setBounds(20, 10, 200, 20);
			trollkarlen.sAttackLabel.setHorizontalAlignment(JLabel.CENTER);
			sGiltig = false;
		}
		//extrap�verkan p� spelarna
		if(spGiltig)
		{
			jDPanel.add(trollkarlen.spAttackLabel);
			trollkarlen.spAttackLabel.setBounds(20, 30, 200, 20);
			trollkarlen.spAttackLabel.setHorizontalAlignment(JLabel.CENTER);
			spGiltig = false;
		}
		if(fpGiltig)
		{
			jDPanel.add(trollkarlen.fpAttackLabel);
			trollkarlen.fpAttackLabel.setBounds(20, 50, 200, 20);
			trollkarlen.fpAttackLabel.setHorizontalAlignment(JLabel.CENTER);
			fpGiltig = false;
		}
		//fiendens attack l�ngst ner, alltid giltig
		jDPanel.add(trollkarlen.fAttackLabel);
		trollkarlen.fAttackLabel.setBounds(20, 70, 200, 20);
		trollkarlen.fAttackLabel.setHorizontalAlignment(JLabel.CENTER);
		//l�gger till panelen i dialogen och s�tter dess storlek
		dialogen.add(jDPanel);
		dialogen.setSize(260, 150);
		dialogen.setVisible(true);
	}
}

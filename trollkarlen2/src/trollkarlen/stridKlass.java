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
	static JButton flyBtn = new JButton(), attackBtn = new JButton(), elixirVäljBtn = new JButton(), fiendeBtn = new JButton(), spelareBtn = new JButton();
	//dessa labels ska visa spelarens och fiendens liv, kraft osv
	static JLabel fiendeLivLabel = new JLabel(), spelareLivLabel = new JLabel(), spelareMagiLabel = new JLabel(), fiendeKraftLabel = new JLabel(), spelareKraftLabel = new JLabel(), fiendeStyrkaLabel = new JLabel(), fiendeSvaghetLabel = new JLabel();
	//knappar för elixir, i vektor eftersom elixir också är det och det förenklar mkt
	static JButton[] elixirBtn = new JButton[5];
	//basfiende
	static Fiende fiende = new Fiende(100, 0, new boolean[5], new int[5], "vanlig", 1, 0, 100);
	//används för att slumpa liv, kraft, styrka osv (sedan för typ)
	static Random slumpaTal = new Random();
	//variabler som behövs för slump
	static int fLiv, fSvaghet, fStyrka;
	//elixirKnapp spelaren tryckt på
	static int valtElixir = -1;
	//väljare ska följa vänighetssystemet (0 = fiende | 1 = ingen/alla | 2 = spelare)
	static int väljare = 1;
	//behövs för att se om spelarna är bedövade, om man har klickat på visa elixir, och om labels för rondsammansfattning ska visas
	static boolean fiendeBedövad, spelareBedövad, visarElixir, sGiltig, fpGiltig, spGiltig = false;
	//variabler för att bestämma hur stor extraskada spelarna tar
	static int fPåverkan, sPåverkan;
	
	stridKlass()
	{
		//prövar bakgrund
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
		this.add(elixirVäljBtn);
		this.add(fiendeBtn);
		this.add(spelareBtn);
		this.add(fiendeLivLabel);
		this.add(spelareLivLabel);
		this.add(spelareMagiLabel);
		this.add(fiendeKraftLabel);
		this.add(spelareKraftLabel);
		this.add(fiendeStyrkaLabel);
		this.add(fiendeSvaghetLabel);
		
		//definierar gränssnitt
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
		
		elixirVäljBtn.setBounds((int) Math.round(199 * trollkarlen.widthSize), (int) Math.round(669 * trollkarlen.heightSize), (int) Math.round(97 * trollkarlen.widthSize), (int) Math.round(98 * trollkarlen.heightSize));
		elixirVäljBtn.addActionListener(new elixirVäljBtnAct());
		elixirVäljBtn.setContentAreaFilled(false);
		elixirVäljBtn.setFocusPainted(false);
		elixirVäljBtn.setFont(new Font("comic sans ms", Font.BOLD, 17));
		elixirVäljBtn.setText("Välj elixir");
		
		//sätter ut knapparna för elixir med ett visst mellanrum
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
		
		//måste lägga till specifika actionlisteners
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
	
	//sätter ut bakgrundsbild
	public void paintComponent (Graphics g)
	{
		g.drawImage(bakgrund, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	//spelaren lämnar striden
	static class flyBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			trollkarlen.position = "skog";
			trollkarlen.Byta(trollkarlen.skog);
			/*
			skulle kunna finnas en möjlighet att inte fly, använd slumparen.
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
			//om spelaren inte är bedövad och har valt någon, kan de attackera
			if(!spelareBedövad)
			{
				if (väljare == 0 || väljare == 2)
				{
					//attackerar fienden - (25 + spelarens kraft*0,25) hp
					if (väljare == 0)
					{
						fiende.setLiv(fiende.getLiv() - 25 - trollkarlen.trollkarl.getKraft()/4);
						trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-25 - trollkarlen.trollkarl.getKraft()/4) + " liv");
						sGiltig = true;
					}
					//attackerar spelaren - (25 + spelarens kraft*0,25) hp
					else
					{
						trollkarlen.trollkarl.setLiv(trollkarlen.trollkarl.getLiv() - 25 - trollkarlen.trollkarl.getKraft()/4);
						trollkarlen.sAttackLabel.setText("Du attackerade dig själv: " + (-25 - trollkarlen.trollkarl.getKraft()/4) + " liv");
						sGiltig = true;
					}
					
					//kollar vad spelarna är påverkade av
					kollaAttack();
					//kollar om fienden är död, eller om fienden kan attackera
					kollaSeger();
				}
				else
				{
					//ERROR
					//"välj någon att attackera"
				}
			}
			else
			{
				kollaAttack();
				kollaSeger();
				trollkarlen.sAttackLabel.setText("Du var bedövad");
				sGiltig = true;
			}
		}
	}
	
	//onödig knapp, man trycker på den och så visas ens elixir, om man klickar igen visas dem ej
	static class elixirVäljBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			if(visarElixir)
			{
				//göm elixir
				for (int i = 0; i < 5; i++)
				{
					
					elixirBtn[i].setVisible(false);
				}
				visarElixir = false;
			}
			else
			{
				//visa tillgängliga elixir
				for (int i = 0; i < 5; i++)
				{
						elixirBtn[i].setVisible(true);
				}
				visarElixir = true;
			}
		}
	}
	
	//när man trycker på fienden
	static class fiendeBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			väljare = 0;
		}
	}
	//när man trycker på spelaren
	static class spelareBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			väljare = 2;
		}
	}
	
	//när man trycker på någon av de 5 elixirknapparna
	static class elixir1BtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//kollar så att det finns elixir för det indexet knappen är på och att spelaren inte är bedövad
			if (!elixirBtn[0].getText().equals("[Inget]"))
			{
				if(!spelareBedövad)
				{
					valtElixir = 0;
					//använder valt elixir
					användMagi();
				}
				//om spelare är bedövad
				else
				{
					//kollar vad spelarna är påverkade av
					kollaAttack();
					//kollar om fienden är död, eller om fienden kan attackera
					kollaSeger();
				}
			}
		}
	}
	static class elixir2BtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//kollar så att det finns elixir för det indexet knappen är på och att spelaren inte är bedövad
			if(!elixirBtn[1].getText().equals("[Inget]"))
			{
				if(!spelareBedövad)
				{
					valtElixir = 1;
					//använder valt elixir
					användMagi();
				}
				//om spelare är bedövad
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
			//kollar så att det finns elixir för det indexet knappen är på och att spelaren inte är bedövad
			if(!elixirBtn[2].getText().equals("[Inget]"))
			{
				if(!spelareBedövad)
				{
					valtElixir = 2;
					//använder valt elixir
					användMagi();
				}
				//om spelare är bedövad
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
			//kollar så att det finns elixir för det indexet knappen är på och att spelaren inte är bedövad
			if(!elixirBtn[3].getText().equals("[Inget]"))
			{
				if(!spelareBedövad)
				{
					valtElixir = 3;
					//använder valt elixir
					användMagi();
				}
				//om spelare är bedövad
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
			//kollar så att det finns elixir för det indexet knappen är på och att spelaren inte är bedövad
			if(!elixirBtn[4].getText().equals("[Inget]"))
			{
				if(!spelareBedövad)
				{
					valtElixir = 4;
					//använder valt elixir
					användMagi();
				}
				//om spelare är bedövad
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
		//om fiende är död vinner man och återvänder till skogen, ny fiende skapas
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
		//varje rond tar tid, det är viktigt för spelaren att använda sin tid rätt och inte strida i onödan
		trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() - 2);
		trollkarlen.trollkarl.kollaSömn();
	}
	
	static void seger()
	{
		/*
		pengar tjänade:
		vanlig = 1*25 pengar
		för varje +5 hp = +2 pengar (1$/2,5hp)
		 */
		trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() + 25 + (fiende.getOriginalLiv()*2)/5);
		stadKlass.uppdateraStats();
		//TEXT
		//"du har vunnit, [PENGAR]"
	}
	
	static void användMagi()
	{
		//kollar att elixirets magikostnad inte är för hög
		if(trollkarlen.trollkarl.getMagi() - verkstadKlass.elixir[valtElixir].getMagiKostnad() >= 0)
		{
			//minskar magin (om det inte visar sig att spelaren inte attackerar något)
			trollkarlen.trollkarl.setMagi(trollkarlen.trollkarl.getMagi() - verkstadKlass.elixir[valtElixir].getMagiKostnad());
			
			//de två undantagen
			if (verkstadKlass.elixir[valtElixir].getVänlighet() == 1 && verkstadKlass.elixir[valtElixir].getCelsius() >= 0)
			{
				if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
				{
					//+pengar
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() + 20);
					trollkarlen.sAttackLabel.setText("Du skapade 20$ åt dig själv");
					sGiltig = true;
				}
				else
				{
					//bedöva alla
					fiende.påverkadAv[valtElixir] = true;
					fiende.påverkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getBedövning();
					trollkarlen.trollkarl.påverkadAv[valtElixir] = true;
					trollkarlen.trollkarl.påverkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getBedövning();
					trollkarlen.sAttackLabel.setText("Du bedövade alla");
					sGiltig = true;
				}
				
				kollaAttack();
				kollaSeger();
			}
			//attack på fiende
			else if (väljare == 0)
			{
				//hur mycket fienden skadas
				kollaFiendeImpakt();
				//hur mycket kraft som påverkas
				kollaFiendeKraft();
				
				fiende.påverkadAv[valtElixir] = true;
				
				//fienden blir påverkad den tid som som är störst utav livPåverkanTid och bedövning
				//inga nuvarande elixir har både och men detta är ändå ett bra sätt att kontrollera framtida fel
				if(verkstadKlass.elixir[valtElixir].getLivPåverkanTid() >= verkstadKlass.elixir[valtElixir].getBedövning())
				{
					fiende.påverkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getLivPåverkanTid();
				}
				else
				{
					fiende.påverkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getBedövning();
				}
				
				kollaAttack();
				kollaSeger();
			}
			//attack på sig själv
			else if (väljare == 2)
			{
				//behöver inte kolla efter styrka eller svaghet (har inga)
				trollkarlen.trollkarl.setLiv(trollkarlen.trollkarl.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade dig själv: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
				//kollar hur kraft påverkas
				kollaSpelareKraft();
				
				trollkarlen.trollkarl.påverkadAv[valtElixir] = true;
				
				if(verkstadKlass.elixir[valtElixir].getLivPåverkanTid() >= verkstadKlass.elixir[valtElixir].getBedövning())
				{
					trollkarlen.trollkarl.påverkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getLivPåverkanTid();
				}
				else
				{
					trollkarlen.trollkarl.påverkadTid[valtElixir] = verkstadKlass.elixir[valtElixir].getBedövning();
				}
				
				kollaAttack();
				kollaSeger();
			}
			else
			{
				
				//OM VÄLJARE = 1
				//ERROR
				//"välj någon att attackera"
				trollkarlen.trollkarl.setMagi(trollkarlen.trollkarl.getMagi() + verkstadKlass.elixir[valtElixir].getMagiKostnad());
			}
			
			//uppdaterar magilabel
			spelareMagiLabel.setText("Magi: " + trollkarlen.trollkarl.getMagi());
		}
		else
		{
			//ERROR
			//"du har för lite magi kvar för detta elixiret"
		}
	}
	
	//kollar skada från elixir på fiende
	static void kollaFiendeImpakt()
	{
		//Beror på valtElixir
		//Om fienden är svag mot en del och stark mot en annan del av attacken, kommer svaghet prioriteras (saker som ska hjälpa fienden gör styrka till svaghet och svaghet till styrka, men det fungerar ändå)
		switch(fiende.getSvaghet()*4 + fiende.getStyrka())
		{
		case 1:
			//värmestyrka, kollar om fienden är attackerad av värme eller inte
			if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 2:
			//kylstyrka, kollar om fienden är attackerad av kyla eller inte
			if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 3:
			//frätstyrka
			if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 4:
			//värmesvaghet
			if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			break;
			
			//case 5 ogiltigt, kan inte ha styrka OCH svaghet för samma sak
			
		case 6:
			//värmesvaghet + kylstyrka, kollar om fienden är attackerad av värme, kyla eller inte
			if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			
		case 7:
			//värmesvaghet + frätstyrka
			if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			
		case 8:
			//kylsvaghet
			if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 9:
			//kylsvaghet + värmestyrka
			if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			break;
			
			//se case 5
			
		case 11:
			//kylsvaghet + frätstyrka
			if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 12:
			//frätsvaghet
			if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 13:
			//frätsvaghet + värmestyrka
			if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getCelsius() >= 100)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			break;
			
		case 14:
			//frätsvaghet + kylstyrka
			if(verkstadKlass.elixir[valtElixir].getPh() <= 3 || verkstadKlass.elixir[valtElixir].getPh() >= 10)
			{
				fiende.setLiv(fiende.getLiv() - (5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(5 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else if(verkstadKlass.elixir[valtElixir].getCelsius() < 0)
			{
				fiende.setLiv(fiende.getLiv() - (3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4);
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + (-(3 * verkstadKlass.elixir[valtElixir].getLivPåverkan())/4) + " liv");
				sGiltig = true;
			}
			else
			{
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
				trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
				sGiltig = true;
			}
			break;
			
			//se case 5
			
		default:
			//inget speciellt händer
			fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[valtElixir].getLivPåverkan());
			trollkarlen.sAttackLabel.setText("Du attackerade fienden: " + - verkstadKlass.elixir[valtElixir].getLivPåverkan() + " liv");
			sGiltig = true;
			break;
		}
	}
	
	//förstärker eller försvagar fiende/spelare
	static void kollaFiendeKraft()
	{
		//minskar kraften till minst -5
		if(fiende.getKraft() - verkstadKlass.elixir[valtElixir].getKraftPåverkan() > -5)
		{
			fiende.setKraft(fiende.getKraft() - verkstadKlass.elixir[valtElixir].getKraftPåverkan());
		}
		else if(fiende.getKraft() - verkstadKlass.elixir[valtElixir].getKraftPåverkan() <= -5)
		{
			//INFO
			//"maximal försvagning för fiende har nåtts"
			fiende.setKraft(-5);
		}
		fiendeKraftLabel.setText("Kraft: " + fiende.getKraft());
	}
	static void kollaSpelareKraft()
	{
		//minskar kraften till minst -5
		if(trollkarlen.trollkarl.getKraft() - verkstadKlass.elixir[valtElixir].getKraftPåverkan() > -5)
		{
			trollkarlen.trollkarl.setKraft(trollkarlen.trollkarl.getKraft() - verkstadKlass.elixir[valtElixir].getKraftPåverkan());
		}
		else if(trollkarlen.trollkarl.getKraft() - verkstadKlass.elixir[valtElixir].getKraftPåverkan() <= -5)
		{
			//INFO
			//"maximal försvagning för spelare har nåtts"
			trollkarlen.trollkarl.setKraft(-5);
		}
		spelareKraftLabel.setText("Kraft: " + trollkarlen.trollkarl.getKraft());
	}
	
	//kollar extrapåverkan
	static void kollaAttack()
	{
		//antar att spelarna inte är bedövade
		fiendeBedövad = false;
		spelareBedövad = false;
		
		//återställer gränssnittsvärdena för påverkan
		trollkarlen.fpAttackLabel.setText(null);
		trollkarlen.spAttackLabel.setText(null);
		fPåverkan = 0;
		sPåverkan = 0;
		
		//kollar på varje elixirs verkan
		for(int i = 0; i < verkstadKlass.elixir.length; i++)
		{
			//kollar alla olika elixir som kan ha verkan på fienden
			if (fiende.påverkadTid[i] > 0)
			{
				//minskar tid påverkad
				fiende.påverkadTid[i]--;
				
				//om fienden ska vara bedövad
				if(verkstadKlass.elixir[i].getBedövning() > 0)
				{
					fiendeBedövad = true;
				}
				//liv minskar enligt extrapåverkan
				fiende.setLiv(fiende.getLiv() - verkstadKlass.elixir[i].getExtraPåverkan());
				fPåverkan += verkstadKlass.elixir[i].getExtraPåverkan();
			}
			//om elixir i inte har verkan, är en spelare inte påverkan
			else
			{
				fiende.påverkadAv[i] = false;
			}
			
			//kollar alla olika elixir som kan ha verkan på spelaren, samma som fienden
			if (trollkarlen.trollkarl.påverkadTid[i] > 0)
			{
				trollkarlen.trollkarl.påverkadTid[i]--;
				
				if(verkstadKlass.elixir[i].getBedövning() > 0)
				{
					spelareBedövad = true;
				}
				
				trollkarlen.trollkarl.setLiv(trollkarlen.trollkarl.getLiv() - verkstadKlass.elixir[i].getExtraPåverkan());
				sPåverkan += verkstadKlass.elixir[i].getExtraPåverkan();
			}
			else
			{
				trollkarlen.trollkarl.påverkadAv[i] = false;
			}
		}
		//uppdaterar livlabels och stridssammanfattning
		fiendeLivLabel.setText("HP: " + fiende.getLiv());
		spelareLivLabel.setText("HP: " + trollkarlen.trollkarl.getLiv());
		if(fPåverkan == 0)
		{
			trollkarlen.fpAttackLabel.setText(null);
		}
		else
		{
			trollkarlen.fpAttackLabel.setText("Fiende påverkad: " + -fPåverkan + "hp");
			fpGiltig = true;
		}
		if(sPåverkan == 0)
		{
			trollkarlen.spAttackLabel.setText(null);
		}
		else
		{
			trollkarlen.spAttackLabel.setText("Spelare påverkad: " + -sPåverkan + "hp");
			spGiltig = true;
		}
		
	}
	
	static void fiendeAttack()
	{
		if(!fiendeBedövad)
		{
			//basattack - (5 + kraft*0,25) hp mot spelaren
			trollkarlen.trollkarl.setLiv(trollkarlen.trollkarl.getLiv() - 5 - fiende.getKraft()/4);
			spelareLivLabel.setText("HP: " + trollkarlen.trollkarl.getLiv());
			//fiendens handling i ronden uppdateras
			trollkarlen.fAttackLabel.setText("Fienden attackerade dig: " + (-5 - fiende.getKraft()/4) + " liv");
			
			//om spelaren dör
			if (trollkarlen.trollkarl.getLiv() <= 0)
			{
				trollkarlen.dödLabel1.setText("Dödsorsak: dog i strid");
				dö();
			}
		}
		else
		{
			trollkarlen.fAttackLabel.setText("Fienden var bedövad");
		}
		//sammanfattning av rond
		attackNotifikation();
	}
	
	static void nyFiende()
	{
		//fiende kan få liv från 100 till 125
		fLiv = 100 + slumpaTal.nextInt(26);
		//slumpar ut olika sorters styrka och svaghet
		do
		{
			fSvaghet = slumpaTal.nextInt(4);
			fStyrka = slumpaTal.nextInt(4);
		} while (fSvaghet == fStyrka);
		
		//MÅSTE SLUMPA TYP SEN
		fiende = new Fiende(fLiv, 0 + slumpaTal.nextInt(16), new boolean[]{false, false, false, false, false}, new int[5], "vanlig", fSvaghet, fStyrka, fLiv);
	}
	
	//spelaren dör, blir informerad om sin död, allting återställs och spelaren återvänder till meny där de kan påbörja nytt spel
	static void dö()
	{
		JDialog dialogen = new JDialog(trollkarlen.fönstret, "Död");
		JPanel jDPanel = new JPanel();
		jDPanel.setLayout(null);
		jDPanel.add(trollkarlen.dödLabel1);
		trollkarlen.dödLabel1.setBounds(20, 10, 300, 20);
		trollkarlen.dödLabel1.setHorizontalAlignment(JLabel.CENTER);
		jDPanel.add(trollkarlen.menyLabel);
		trollkarlen.menyLabel.setBounds(20, 30, 300, 20);
		trollkarlen.menyLabel.setHorizontalAlignment(JLabel.CENTER);
		dialogen.add(jDPanel);
		dialogen.setSize(360, 150);
		dialogen.setVisible(true);
		verkstadKlass.hyra = 10;
		verkstadKlass.hyresAnmärkning = 0;
		verkstadKlass.verkstadKvar = true;
		stadKlass.verkstadBtn.setVisible(true);
		startMeny.nyttSpelBtn.setText("Nytt spel");
		startMeny.namnLabel.setVisible(false);
		startMeny.namnSkriv.setVisible(false);
		trollkarlen.Byta(trollkarlen.meny);
	}
	
	//sammanfattning av rond, visar spelarens attack, extra påverkan och fiendens attack
	static void attackNotifikation()
	{
		JDialog dialogen = new JDialog(trollkarlen.fönstret, "Attacksammanfattning");
		JPanel jDPanel = new JPanel();
		jDPanel.setLayout(null);
		//spelarens attack högst upp i panelen som skapas
		if(sGiltig)
		{
			jDPanel.add(trollkarlen.sAttackLabel);
			trollkarlen.sAttackLabel.setBounds(20, 10, 200, 20);
			trollkarlen.sAttackLabel.setHorizontalAlignment(JLabel.CENTER);
			sGiltig = false;
		}
		//extrapåverkan på spelarna
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
		//fiendens attack längst ner, alltid giltig
		jDPanel.add(trollkarlen.fAttackLabel);
		trollkarlen.fAttackLabel.setBounds(20, 70, 200, 20);
		trollkarlen.fAttackLabel.setHorizontalAlignment(JLabel.CENTER);
		//lägger till panelen i dialogen och sätter dess storlek
		dialogen.add(jDPanel);
		dialogen.setSize(260, 150);
		dialogen.setVisible(true);
	}
}

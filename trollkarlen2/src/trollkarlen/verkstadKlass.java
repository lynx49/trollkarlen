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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import trollkarlen.skogKlass.stadBtnAct;
import trollkarlen.trollkarlen.sovBtnAct;

public class verkstadKlass extends JPanel
{
	//skapar variabler och komponenter
	static BufferedImage bakgrund;
	static JButton stadBtn = new JButton(), skapaElixirBtn = new JButton(), sovBtn = new JButton();
	//gränssnitt för nuvarande ruta
	static JLabel phLabel = new JLabel(), celsiusLabel = new JLabel(), vänlighetsLabel = new JLabel();
	//gränssnitt som alltid är där, kan fixas senare
	//static JLabel livLabel = new JLabel(), kraftLabel = new JLabel(), attackeradAvLabel = new JLabel(), namnLabel = new JLabel(), pengarLabel = new JLabel(), magiLabel = new JLabel(), sömnLabel = new JLabel(), hyraLabel = new JLabel();
	static JTextField phSkriv = new JTextField(), celsiusSkriv = new JTextField();
	static JTextArea elixirStats = new JTextArea();
	static int vänlighetsNivå, ledigtElixir = -1;
	//variabler för verkstadens tillgänglighet
	public static int hyra = 10;
	public static int hyresAnmärkning, köpVerkstad = 0;
	public static boolean verkstadKvar = true;
	static boolean kanSkapaElixir;
	//lista med grundelixir och dess värden
	static StridElixir[] elixirLista = new StridElixir[] { 
			//eld
			new StridElixir(20, 15, 1, 0, 0, 5, 100, 0, 20), 
			//is
			new StridElixir(20, 0, 0, 2, 0, 5, -50, 0, 20),
			//vind
			new StridElixir(35, 0, 0, 0, 0, 5, 50, 0, 20),
			//gift
			new StridElixir(0, 15, 4, 0, 0, 1, 50, 0, 25),
			//gifteld
			new StridElixir(20, 15, 3, 0, 0, 1, 100, 0, 40),
			//försvaga
			new StridElixir(0, 0, 0, 0, 7, 7, 0, 0, 15),
			//hela
			new StridElixir(40, 0, 0, 0, 0, 7, 0, 2, 25),
			//hela över tid
			new StridElixir(0, -15, 4, 0, 0, 5, 0, 2, 30),
			//förgöraren
			new StridElixir(75, -15, 5, 0, -5, 1, 0, 2, 20),
			//förstärk
			new StridElixir(0, 0, 0, 0, -7, 7, 100, 2, 15),
			//framkalla pengar
			new StridElixir(0, 0, 0, 0, 0, 7, 100, 1, 10),
			//bedöva
			new StridElixir(0, 0, 0, 3, 0, 7, -50, 1, 20),
			//bedöva alla
			new StridElixir(0, 0, 0, 3, 0, 7, 50, 1, 10)
	};
	//alla elixir är ursprungligen eldelixir
	static StridElixir[] elixir = new StridElixir[] { elixirLista[0], elixirLista[0], elixirLista[0], elixirLista[0], elixirLista[0] };
	
	
	verkstadKlass()
	{
		//vänlighets-knapparna för elixir (har 3 värden och visas som -1, 0 och 1)
		JButton[] vänlighetsBtn = new JButton[3];
		for (int i = 0; i < 3; i++)
		{
			vänlighetsBtn[i] = new JButton(String.valueOf(i));
			this.add(vänlighetsBtn[i]);
			vänlighetsBtn[i].setBounds((int) Math.round((925 + 55*i) * trollkarlen.widthSize), (int) Math.round(370 * trollkarlen.heightSize), (int) Math.round(50 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
			vänlighetsBtn[i].setContentAreaFilled(false);
			vänlighetsBtn[i].setFocusPainted(false);
			vänlighetsBtn[i].setFont(new Font("comic sans ms", Font.BOLD, 13));
			vänlighetsBtn[i].setText(Integer.toString(i - 1));
		}
		//separat actionlistener behövs för varje knapp
		vänlighetsBtn[0].addActionListener(new vänlighetsBtn1Act());
		vänlighetsBtn[1].addActionListener(new vänlighetsBtn2Act());
		vänlighetsBtn[2].addActionListener(new vänlighetsBtn3Act());
		
		//prövar bakgrund
		try
		{
			bakgrund = ImageIO.read(trollkarlen.bakgrunder[3]);
		}
		catch (Exception e)
		{
			System.out.print("fel3");
		}
		
		//fixar layouten
		this.setLayout(null);
		this.add(stadBtn);
		this.add(skapaElixirBtn);
		this.add(elixirStats);
		this.add(phLabel);
		this.add(celsiusLabel);
		this.add(vänlighetsLabel);
		this.add(phSkriv);
		this.add(celsiusSkriv);
		this.add(sovBtn);
		
		//definierar gränssnitt
		stadBtn.setBounds((int) Math.round(105 * trollkarlen.widthSize), (int) Math.round(360 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		stadBtn.addActionListener(new stadBtnAct());
		stadBtn.setContentAreaFilled(false);
		stadBtn.setFocusPainted(false);
		stadBtn.setFont(new Font("comic sans ms", Font.BOLD, 25));
		stadBtn.setText("Stad");
		
		sovBtn.setBounds((int) Math.round(15 * trollkarlen.widthSize), (int) Math.round(680 * trollkarlen.heightSize), (int) Math.round(97 * trollkarlen.widthSize), (int) Math.round(60 * trollkarlen.heightSize));
		sovBtn.addActionListener(new sovBtnAct());
		sovBtn.setContentAreaFilled(false);
		sovBtn.setFocusPainted(false);
		sovBtn.setFont(new Font("comic sans ms", Font.BOLD, 17));
		sovBtn.setText("Sov");
		sovBtn.setVisible(false);
		
		skapaElixirBtn.setBounds((int) Math.round(1060 * trollkarlen.widthSize), (int) Math.round(700 * trollkarlen.heightSize), (int) Math.round(150 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		skapaElixirBtn.addActionListener(new skapaElixirBtnAct());
		skapaElixirBtn.setContentAreaFilled(false);
		skapaElixirBtn.setFocusPainted(false);
		skapaElixirBtn.setFont(new Font("comic sans ms", Font.BOLD, 25));
		skapaElixirBtn.setText("Skapa");
		
		elixirStats.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(10 * trollkarlen.heightSize), (int) Math.round(400 * trollkarlen.widthSize), (int) Math.round(115 * trollkarlen.heightSize));
		elixirStats.setFont(new Font("comic sans ms", Font.BOLD, 20));
		elixirStats.setText("pH: \nTemperatur: \nVänlighet: \nTyp:");
		
		phLabel.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(130 * trollkarlen.heightSize), (int) Math.round(200 * trollkarlen.widthSize), (int) Math.round(30 * trollkarlen.heightSize));
		phLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		phLabel.setText("pH-värde");
		phLabel.setOpaque(true);
		
		//phSkriv och celsiusSkriv är för att användaren ska kunna skriva in egna värden på dessa för sina elixir
		phSkriv.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(170 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		
		celsiusLabel.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(230 * trollkarlen.heightSize), (int) Math.round(200 * trollkarlen.widthSize), (int) Math.round(30 * trollkarlen.heightSize));
		celsiusLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		celsiusLabel.setText("Temperatur");
		
		celsiusSkriv.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(270 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		
		vänlighetsLabel.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(330 * trollkarlen.heightSize), (int) Math.round(200 * trollkarlen.widthSize), (int) Math.round(30 * trollkarlen.heightSize));
		vänlighetsLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		vänlighetsLabel.setText("Vänlighet");
	}
	
	//sätter ut bakgrundsbild
	public void paintComponent(Graphics g)
	{
		g.drawImage(bakgrund, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	//de tre knapparna för att sätta elixirets vänlighet
		static class vänlighetsBtn1Act implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				vänlighetsNivå = 0;
			}
		}
		static class vänlighetsBtn2Act implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				vänlighetsNivå = 1;
			}
		}
		static class vänlighetsBtn3Act implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				vänlighetsNivå = 2;
			}
		}
	
	//efter man trycker på "skapa"-knappen
	static class skapaElixirBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//celsius och ph måste vara ifyllda med nummer enbart
			try
			{
				if(phSkriv.getText().length() > 0 && celsiusSkriv.getText().length() > 0)
				{
					Integer.parseInt(phSkriv.getText());
					Integer.parseInt(celsiusSkriv.getText());
					kanSkapaElixir = true;
				}
				else
				{
					kanSkapaElixir = false;
					//ERROR
					//"du måste fylla i alla fält med siffror"
				}
			}
			catch (NumberFormatException error)
			{
				kanSkapaElixir = false;
				//ERROR
				//"du kan bara skapa elixir med giltiga siffror"
			}
			
			//elixiren sorteras efter vänlighet och en av tre metoder kommer anropas
			if (vänlighetsNivå == 0 && kanSkapaElixir)
			{
				//om det finns plats att skapa nytt elixir
				for(int i = 0; i < 5; i++)
				{
					if(stridKlass.elixirBtn[i].getText() == "[Inget]")
					{
						ledigtElixir = i;
						//metod för elixir med vänlighet 0
						vN0Elixir();
						//information om elixir som skapats
						elixirStats.setText("pH: " + phSkriv.getText() + "\nTemperatur: " + celsiusSkriv.getText() + "\nVänlighet: " + (vänlighetsNivå - 1) + "\nTyp: " + stridKlass.elixirBtn[ledigtElixir].getText());
						break;
					}
					else if(i == 4)
					{
						//ERROR
						//"du har inte plats för ett till elixir, sälj ett av dem"
					}
				}
			}
			else if (vänlighetsNivå == 1 && kanSkapaElixir)
			{
				for(int i = 0; i < 5; i++)
				{
					if(stridKlass.elixirBtn[i].getText() == "[Inget]")
					{
						ledigtElixir = i;
						//metod för elixir med vänlighet 1
						vN1Elixir();
						elixirStats.setText("pH: " + phSkriv.getText() + "\nTemperatur: " + celsiusSkriv.getText() + "\nVänlighet: " + (vänlighetsNivå - 1) + "\nTyp: " + stridKlass.elixirBtn[ledigtElixir].getText());
						break;
					}
					else if(i == 4)
					{
						//ERROR
					}
				}
			}
			else if (vänlighetsNivå == 2 && kanSkapaElixir)
			{
				for(int i = 0; i < 5; i++)
				{
					if(stridKlass.elixirBtn[i].getText() == "[Inget]")
					{
						ledigtElixir = i;
						//metod för elixir med vänlighet 2
						vN2Elixir();
						elixirStats.setText("pH: " + phSkriv.getText() + "\nTemperatur: " + celsiusSkriv.getText() + "\nVänlighet: " + (vänlighetsNivå - 1) + "\nTyp: " + stridKlass.elixirBtn[ledigtElixir].getText());
						break;
					}
					else if(i == 4)
					{
						//ERROR
					}
				}
			}
			else
			{
				//ERROR
				//"välj en vänlighetsnivå"
			}
			
		}
	}
	
	//metod för att skapa elixir med vänlighet 0
	public static void vN0Elixir()
	{	
		//om elixiret är frätande
		if (Integer.parseInt(phSkriv.getText()) <= 3 || Integer.parseInt(phSkriv.getText()) >= 10)
		{
			//om elixiret är frätande och hett
			if (Integer.parseInt(celsiusSkriv.getText()) >= 100)
			{
				//gifteld, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
				elixir[ledigtElixir] = elixirLista[4];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Gifteld");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
			//om elixiret är frätande men inte hett
			else
			{
				//gift, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
				elixir[ledigtElixir] = elixirLista[3];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Gift");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
		}
		//om elixiret är basiskt
		else if (Integer.parseInt(phSkriv.getText()) == 7)
		{
			//försvagning, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
			elixir[ledigtElixir] = elixirLista[5];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("Försvagning");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		//om elixiret inte är basiskt eller frätande
		else
		{
			//om elixiret är hett
			if (Integer.parseInt(celsiusSkriv.getText()) >= 100)
			{
				//eld, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
				elixir[ledigtElixir] = elixirLista[0];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Eld");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
			//om elixiret är kallt
			else if (Integer.parseInt(celsiusSkriv.getText()) <= 0)
			{
				//is, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
				elixir[ledigtElixir] = elixirLista[1];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Is");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
			//om elixiret är lagom varmt
			else
			{
				//vind, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
				elixir[ledigtElixir] = elixirLista[2];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Vind");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
		}
		//tid passerar i spelet, kollar om spelaren behöver sova
		trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() - 10);
		trollkarlen.trollkarl.kollaSömn();
	}
	//metod för att skapa elixir med vänlighet 1
	public static void vN1Elixir()
	{
		if (Integer.parseInt(celsiusSkriv.getText()) >= 100)
		{
			//pengar, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
			elixir[ledigtElixir] = elixirLista[10];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("Pengar");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		else if (Integer.parseInt(celsiusSkriv.getText()) < 0)
		{
			//bedöva, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
			elixir[ledigtElixir] = elixirLista[11];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("Bedöva");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		else
		{
			//bedöva alla, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
			elixir[ledigtElixir] = elixirLista[12];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("Bedöva alla");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		//tid passerar i spelet, kollar om spelaren behöver sova
		trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() - 10);
		trollkarlen.trollkarl.kollaSömn();
	}
	//metod för att skapa elixir med vänlighet 2
	public static void vN2Elixir()
	{
		if (Integer.parseInt(phSkriv.getText()) == 7)
		{
			if (Integer.parseInt(celsiusSkriv.getText()) >= 100)
			{
				//styrka, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
				elixir[ledigtElixir] = elixirLista[9];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Styrka");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
			/*else if (Integer.parseInt(celsiusSkriv.getText()) == 37)
			{
				//medhjälpare/dubbelattack
			}*/
			else
			{
				//hela, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
				elixir[ledigtElixir] = elixirLista[6];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Hela");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
		}
		else if (Integer.parseInt(phSkriv.getText()) <= 3 || Integer.parseInt(phSkriv.getText()) >= 10)
		{
			//sno liv (URSPRUNGLIGEN)
			//helande stark attack, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
			elixir[ledigtElixir] = elixirLista[8];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("Förgöraren");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		else
		{
			//hela över tid, uppdaterar gränssnitt som hanterar elixir (+ sätter ph och celsius som användaren skrev in)
			elixir[ledigtElixir] = elixirLista[7];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("Hela över tid");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		//tid passerar i spelet, kollar om spelaren behöver sova
		trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() - 10);
		trollkarlen.trollkarl.kollaSömn();
	}
	
	//efter varje gång man sover ökar hyran, men den kan också minska och landägaren kan ge en pengar om man har tur
	public static void ökaHyra()
	{
		Random prisökning = new Random();
		//hyra ökar med -10 till 30 per dag
		hyra += prisökning.nextInt(41) - 10;
		//uppdaterar gränssnitt för spelarens statistik
		stadKlass.uppdateraStats();
	}
	
	//när spelaren går in i sömn betalas hyra
	public static void betalaHyra()
	{
		//om hyran kan betalas, betalas den
		if(trollkarlen.trollkarl.getPengar() >= hyra)
		{
			trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() - hyra);
		}
		//annars får spelaren en anmärkning att de behöver betala nästa gång, om de inte kan betala i sammanlagt 3 dagar blir de utsparkade
		else
		{
			hyresAnmärkning++;
			if(hyresAnmärkning >= 3)
			{
				//efter 3 hyresAnmärkningar återställs dem och spelaren kan köpa tillbaka verkstaden enligt en modell där hyran har varit 50 per dag
				hyresAnmärkning = 0;
				//förlorad verkstad
				verkstadKvar = false;
				hyra = 0;
				köpVerkstad = trollkarlen.dag * 50;
				//knappen för att byta till verkstad ändras
				stadKlass.verkstadBtn.setFont(new Font("comic sans ms", Font.BOLD, 13));
				stadKlass.verkstadBtn.setText("$" + köpVerkstad + " Köp tillbaka");
			}
		}
	}
	
	//när spelaren har sovit, hyra betalas och ökas
	public static void nyDag()
	{
		if(verkstadKvar)
		{
			betalaHyra();
			ökaHyra();
		}
		//kan inte sova längre
		sovBtn.setVisible(false);
		stadKlass.sovBtn.setVisible(false);
		skogKlass.sovBtn.setVisible(false);
		butikKlass.sovBtn.setVisible(false);
		//dag ökas
		trollkarlen.dag++;
	}
}
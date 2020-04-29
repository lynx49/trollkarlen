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
	//gr�nssnitt f�r nuvarande ruta
	static JLabel phLabel = new JLabel(), celsiusLabel = new JLabel(), v�nlighetsLabel = new JLabel();
	//gr�nssnitt som alltid �r d�r, kan fixas senare
	//static JLabel livLabel = new JLabel(), kraftLabel = new JLabel(), attackeradAvLabel = new JLabel(), namnLabel = new JLabel(), pengarLabel = new JLabel(), magiLabel = new JLabel(), s�mnLabel = new JLabel(), hyraLabel = new JLabel();
	static JTextField phSkriv = new JTextField(), celsiusSkriv = new JTextField();
	static JTextArea elixirStats = new JTextArea();
	static int v�nlighetsNiv�, ledigtElixir = -1;
	//variabler f�r verkstadens tillg�nglighet
	public static int hyra = 10;
	public static int hyresAnm�rkning, k�pVerkstad = 0;
	public static boolean verkstadKvar = true;
	static boolean kanSkapaElixir;
	//lista med grundelixir och dess v�rden
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
			//f�rsvaga
			new StridElixir(0, 0, 0, 0, 7, 7, 0, 0, 15),
			//hela
			new StridElixir(40, 0, 0, 0, 0, 7, 0, 2, 25),
			//hela �ver tid
			new StridElixir(0, -15, 4, 0, 0, 5, 0, 2, 30),
			//f�rg�raren
			new StridElixir(75, -15, 5, 0, -5, 1, 0, 2, 20),
			//f�rst�rk
			new StridElixir(0, 0, 0, 0, -7, 7, 100, 2, 15),
			//framkalla pengar
			new StridElixir(0, 0, 0, 0, 0, 7, 100, 1, 10),
			//bed�va
			new StridElixir(0, 0, 0, 3, 0, 7, -50, 1, 20),
			//bed�va alla
			new StridElixir(0, 0, 0, 3, 0, 7, 50, 1, 10)
	};
	//alla elixir �r ursprungligen eldelixir
	static StridElixir[] elixir = new StridElixir[] { elixirLista[0], elixirLista[0], elixirLista[0], elixirLista[0], elixirLista[0] };
	
	
	verkstadKlass()
	{
		//v�nlighets-knapparna f�r elixir (har 3 v�rden och visas som -1, 0 och 1)
		JButton[] v�nlighetsBtn = new JButton[3];
		for (int i = 0; i < 3; i++)
		{
			v�nlighetsBtn[i] = new JButton(String.valueOf(i));
			this.add(v�nlighetsBtn[i]);
			v�nlighetsBtn[i].setBounds((int) Math.round((925 + 55*i) * trollkarlen.widthSize), (int) Math.round(370 * trollkarlen.heightSize), (int) Math.round(50 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
			v�nlighetsBtn[i].setContentAreaFilled(false);
			v�nlighetsBtn[i].setFocusPainted(false);
			v�nlighetsBtn[i].setFont(new Font("comic sans ms", Font.BOLD, 13));
			v�nlighetsBtn[i].setText(Integer.toString(i - 1));
		}
		//separat actionlistener beh�vs f�r varje knapp
		v�nlighetsBtn[0].addActionListener(new v�nlighetsBtn1Act());
		v�nlighetsBtn[1].addActionListener(new v�nlighetsBtn2Act());
		v�nlighetsBtn[2].addActionListener(new v�nlighetsBtn3Act());
		
		//pr�var bakgrund
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
		this.add(v�nlighetsLabel);
		this.add(phSkriv);
		this.add(celsiusSkriv);
		this.add(sovBtn);
		
		//definierar gr�nssnitt
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
		elixirStats.setText("pH: \nTemperatur: \nV�nlighet: \nTyp:");
		
		phLabel.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(130 * trollkarlen.heightSize), (int) Math.round(200 * trollkarlen.widthSize), (int) Math.round(30 * trollkarlen.heightSize));
		phLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		phLabel.setText("pH-v�rde");
		phLabel.setOpaque(true);
		
		//phSkriv och celsiusSkriv �r f�r att anv�ndaren ska kunna skriva in egna v�rden p� dessa f�r sina elixir
		phSkriv.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(170 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		
		celsiusLabel.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(230 * trollkarlen.heightSize), (int) Math.round(200 * trollkarlen.widthSize), (int) Math.round(30 * trollkarlen.heightSize));
		celsiusLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		celsiusLabel.setText("Temperatur");
		
		celsiusSkriv.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(270 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		
		v�nlighetsLabel.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(330 * trollkarlen.heightSize), (int) Math.round(200 * trollkarlen.widthSize), (int) Math.round(30 * trollkarlen.heightSize));
		v�nlighetsLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		v�nlighetsLabel.setText("V�nlighet");
	}
	
	//s�tter ut bakgrundsbild
	public void paintComponent(Graphics g)
	{
		g.drawImage(bakgrund, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	//de tre knapparna f�r att s�tta elixirets v�nlighet
		static class v�nlighetsBtn1Act implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				v�nlighetsNiv� = 0;
			}
		}
		static class v�nlighetsBtn2Act implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				v�nlighetsNiv� = 1;
			}
		}
		static class v�nlighetsBtn3Act implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				v�nlighetsNiv� = 2;
			}
		}
	
	//efter man trycker p� "skapa"-knappen
	static class skapaElixirBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//celsius och ph m�ste vara ifyllda med nummer enbart
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
					//"du m�ste fylla i alla f�lt med siffror"
				}
			}
			catch (NumberFormatException error)
			{
				kanSkapaElixir = false;
				//ERROR
				//"du kan bara skapa elixir med giltiga siffror"
			}
			
			//elixiren sorteras efter v�nlighet och en av tre metoder kommer anropas
			if (v�nlighetsNiv� == 0 && kanSkapaElixir)
			{
				//om det finns plats att skapa nytt elixir
				for(int i = 0; i < 5; i++)
				{
					if(stridKlass.elixirBtn[i].getText() == "[Inget]")
					{
						ledigtElixir = i;
						//metod f�r elixir med v�nlighet 0
						vN0Elixir();
						//information om elixir som skapats
						elixirStats.setText("pH: " + phSkriv.getText() + "\nTemperatur: " + celsiusSkriv.getText() + "\nV�nlighet: " + (v�nlighetsNiv� - 1) + "\nTyp: " + stridKlass.elixirBtn[ledigtElixir].getText());
						break;
					}
					else if(i == 4)
					{
						//ERROR
						//"du har inte plats f�r ett till elixir, s�lj ett av dem"
					}
				}
			}
			else if (v�nlighetsNiv� == 1 && kanSkapaElixir)
			{
				for(int i = 0; i < 5; i++)
				{
					if(stridKlass.elixirBtn[i].getText() == "[Inget]")
					{
						ledigtElixir = i;
						//metod f�r elixir med v�nlighet 1
						vN1Elixir();
						elixirStats.setText("pH: " + phSkriv.getText() + "\nTemperatur: " + celsiusSkriv.getText() + "\nV�nlighet: " + (v�nlighetsNiv� - 1) + "\nTyp: " + stridKlass.elixirBtn[ledigtElixir].getText());
						break;
					}
					else if(i == 4)
					{
						//ERROR
					}
				}
			}
			else if (v�nlighetsNiv� == 2 && kanSkapaElixir)
			{
				for(int i = 0; i < 5; i++)
				{
					if(stridKlass.elixirBtn[i].getText() == "[Inget]")
					{
						ledigtElixir = i;
						//metod f�r elixir med v�nlighet 2
						vN2Elixir();
						elixirStats.setText("pH: " + phSkriv.getText() + "\nTemperatur: " + celsiusSkriv.getText() + "\nV�nlighet: " + (v�nlighetsNiv� - 1) + "\nTyp: " + stridKlass.elixirBtn[ledigtElixir].getText());
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
				//"v�lj en v�nlighetsniv�"
			}
			
		}
	}
	
	//metod f�r att skapa elixir med v�nlighet 0
	public static void vN0Elixir()
	{	
		//om elixiret �r fr�tande
		if (Integer.parseInt(phSkriv.getText()) <= 3 || Integer.parseInt(phSkriv.getText()) >= 10)
		{
			//om elixiret �r fr�tande och hett
			if (Integer.parseInt(celsiusSkriv.getText()) >= 100)
			{
				//gifteld, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
				elixir[ledigtElixir] = elixirLista[4];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Gifteld");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
			//om elixiret �r fr�tande men inte hett
			else
			{
				//gift, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
				elixir[ledigtElixir] = elixirLista[3];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Gift");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
		}
		//om elixiret �r basiskt
		else if (Integer.parseInt(phSkriv.getText()) == 7)
		{
			//f�rsvagning, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
			elixir[ledigtElixir] = elixirLista[5];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("F�rsvagning");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		//om elixiret inte �r basiskt eller fr�tande
		else
		{
			//om elixiret �r hett
			if (Integer.parseInt(celsiusSkriv.getText()) >= 100)
			{
				//eld, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
				elixir[ledigtElixir] = elixirLista[0];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Eld");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
			//om elixiret �r kallt
			else if (Integer.parseInt(celsiusSkriv.getText()) <= 0)
			{
				//is, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
				elixir[ledigtElixir] = elixirLista[1];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Is");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
			//om elixiret �r lagom varmt
			else
			{
				//vind, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
				elixir[ledigtElixir] = elixirLista[2];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Vind");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
		}
		//tid passerar i spelet, kollar om spelaren beh�ver sova
		trollkarlen.trollkarl.setS�mn(trollkarlen.trollkarl.getS�mn() - 10);
		trollkarlen.trollkarl.kollaS�mn();
	}
	//metod f�r att skapa elixir med v�nlighet 1
	public static void vN1Elixir()
	{
		if (Integer.parseInt(celsiusSkriv.getText()) >= 100)
		{
			//pengar, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
			elixir[ledigtElixir] = elixirLista[10];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("Pengar");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		else if (Integer.parseInt(celsiusSkriv.getText()) < 0)
		{
			//bed�va, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
			elixir[ledigtElixir] = elixirLista[11];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("Bed�va");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		else
		{
			//bed�va alla, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
			elixir[ledigtElixir] = elixirLista[12];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("Bed�va alla");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		//tid passerar i spelet, kollar om spelaren beh�ver sova
		trollkarlen.trollkarl.setS�mn(trollkarlen.trollkarl.getS�mn() - 10);
		trollkarlen.trollkarl.kollaS�mn();
	}
	//metod f�r att skapa elixir med v�nlighet 2
	public static void vN2Elixir()
	{
		if (Integer.parseInt(phSkriv.getText()) == 7)
		{
			if (Integer.parseInt(celsiusSkriv.getText()) >= 100)
			{
				//styrka, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
				elixir[ledigtElixir] = elixirLista[9];
				elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
				elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
				stridKlass.elixirBtn[ledigtElixir].setText("Styrka");
				
				butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
			}
			/*else if (Integer.parseInt(celsiusSkriv.getText()) == 37)
			{
				//medhj�lpare/dubbelattack
			}*/
			else
			{
				//hela, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
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
			//helande stark attack, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
			elixir[ledigtElixir] = elixirLista[8];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("F�rg�raren");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		else
		{
			//hela �ver tid, uppdaterar gr�nssnitt som hanterar elixir (+ s�tter ph och celsius som anv�ndaren skrev in)
			elixir[ledigtElixir] = elixirLista[7];
			elixir[ledigtElixir].setPh(Integer.parseInt(phSkriv.getText()));
			elixir[ledigtElixir].setCelsius(Integer.parseInt(celsiusSkriv.getText()));
			stridKlass.elixirBtn[ledigtElixir].setText("Hela �ver tid");
			
			butikKlass.elixirBtn[ledigtElixir].setBackground(new Color(255, 255, 255));
		}
		//tid passerar i spelet, kollar om spelaren beh�ver sova
		trollkarlen.trollkarl.setS�mn(trollkarlen.trollkarl.getS�mn() - 10);
		trollkarlen.trollkarl.kollaS�mn();
	}
	
	//efter varje g�ng man sover �kar hyran, men den kan ocks� minska och land�garen kan ge en pengar om man har tur
	public static void �kaHyra()
	{
		Random pris�kning = new Random();
		//hyra �kar med -10 till 30 per dag
		hyra += pris�kning.nextInt(41) - 10;
		//uppdaterar gr�nssnitt f�r spelarens statistik
		stadKlass.uppdateraStats();
	}
	
	//n�r spelaren g�r in i s�mn betalas hyra
	public static void betalaHyra()
	{
		//om hyran kan betalas, betalas den
		if(trollkarlen.trollkarl.getPengar() >= hyra)
		{
			trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() - hyra);
		}
		//annars f�r spelaren en anm�rkning att de beh�ver betala n�sta g�ng, om de inte kan betala i sammanlagt 3 dagar blir de utsparkade
		else
		{
			hyresAnm�rkning++;
			if(hyresAnm�rkning >= 3)
			{
				//efter 3 hyresAnm�rkningar �terst�lls dem och spelaren kan k�pa tillbaka verkstaden enligt en modell d�r hyran har varit 50 per dag
				hyresAnm�rkning = 0;
				//f�rlorad verkstad
				verkstadKvar = false;
				hyra = 0;
				k�pVerkstad = trollkarlen.dag * 50;
				//knappen f�r att byta till verkstad �ndras
				stadKlass.verkstadBtn.setFont(new Font("comic sans ms", Font.BOLD, 13));
				stadKlass.verkstadBtn.setText("$" + k�pVerkstad + " K�p tillbaka");
			}
		}
	}
	
	//n�r spelaren har sovit, hyra betalas och �kas
	public static void nyDag()
	{
		if(verkstadKvar)
		{
			betalaHyra();
			�kaHyra();
		}
		//kan inte sova l�ngre
		sovBtn.setVisible(false);
		stadKlass.sovBtn.setVisible(false);
		skogKlass.sovBtn.setVisible(false);
		butikKlass.sovBtn.setVisible(false);
		//dag �kas
		trollkarlen.dag++;
	}
}
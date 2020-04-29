package trollkarlen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import trollkarlen.trollkarlen.sovBtnAct;

public class butikKlass extends JPanel
{
	//skapar variabler och komponenter
	static BufferedImage bakgrund;
	static JButton stadBtn = new JButton(), s�ljBtn = new JButton(), k�pBtn = new JButton(), /*f�r elixir: uppgraderaBtn = new JButton(),*/ elixirS�ljBtn = new JButton(), sovBtn = new JButton();
	static JLabel[] elixirLabel = new JLabel[13];
	static JButton[] elixirBtn = new JButton[5], k�pElixirBtn = new JButton[13];
	static int valtElixir, k�ptElixir = -1;
	
	butikKlass()
	{
		//kollar om den tredje bakgrundsbilden fr�n trollkarlen g�r att l�sa in
		try
		{
			//s�tter bilden, kan nu anv�ndas f�r att ritas upp
			bakgrund = ImageIO.read(trollkarlen.bakgrunder[2]);
		}
		catch (Exception e)
		{
			System.out.print("fel2");
		}
		
		this.setLayout(null);
		
		this.add(stadBtn);
		this.add(s�ljBtn);
		this.add(k�pBtn);
		//this.add(uppgraderaBtn);
		this.add(elixirS�ljBtn);
		this.add(sovBtn);
		
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
		
		s�ljBtn.setBounds((int) Math.round(930 * trollkarlen.widthSize), (int) Math.round(15 * trollkarlen.heightSize), (int) Math.round(80 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		s�ljBtn.addActionListener(new s�ljBtnAct());
		s�ljBtn.setContentAreaFilled(false);
		s�ljBtn.setFocusPainted(false);
		s�ljBtn.setFont(new Font("comic sans ms", Font.BOLD, 20));
		s�ljBtn.setText("S�lj");
		
		k�pBtn.setBounds((int) Math.round(1020 * trollkarlen.widthSize), (int) Math.round(15 * trollkarlen.heightSize), (int) Math.round(70 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		k�pBtn.addActionListener(new k�pBtnAct());
		k�pBtn.setContentAreaFilled(false);
		k�pBtn.setFocusPainted(false);
		k�pBtn.setFont(new Font("comic sans ms", Font.BOLD, 20));
		k�pBtn.setText("K�p");
		
		/*uppgraderaBtn.setBounds((int) Math.round(1100 * trollkarlen.widthSize), (int) Math.round(15 * trollkarlen.heightSize), (int) Math.round(150 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		uppgraderaBtn.setContentAreaFilled(false);
		uppgraderaBtn.setFocusPainted(false);
		uppgraderaBtn.setFont(new Font("comic sans ms", Font.BOLD, 20));
		uppgraderaBtn.setText("Uppgradera");
		
		Om man uppgraderar ett elixir f�r det t.ex mer skada eller l�ngre effekt, man bara v�ljer elixiret och g�r s� h�r: elixirX.setX(elixirX.getX() + �kning);
		
		*/
		
		//l�gger till knapparna f�r att v�lja elixir i rad s� att de enkelt kan kontrolleras (man kan t.ex se om anv�ndaren har n�got d�r eller inte)
		//samma siffra som i verkstad fungerar f�r dessa
		for (int i = 0; i < 5; i++)
		{
			elixirBtn[i] = new JButton();
			this.add(elixirBtn[i]);
			elixirBtn[i].setBounds((int) Math.round((930 + 55*i) * trollkarlen.widthSize), (int) Math.round(100 * trollkarlen.heightSize), (int) Math.round(50 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
			elixirBtn[i].setFocusPainted(false);
			elixirBtn[i].setBackground(new Color(100, 100, 100));
			elixirBtn[i].setFont(new Font("comic sans ms", Font.BOLD, 13));
			elixirBtn[i].setText(Integer.toString(i + 1));
			elixirBtn[i].setVisible(false);
		}
		
		//actionlisteners m�ste l�ggas till separat
		elixirBtn[0].addActionListener(new elixirBtn1Act());
		elixirBtn[1].addActionListener(new elixirBtn2Act());
		elixirBtn[2].addActionListener(new elixirBtn3Act());
		elixirBtn[3].addActionListener(new elixirBtn4Act());
		elixirBtn[4].addActionListener(new elixirBtn5Act());
		
		elixirS�ljBtn.setBounds((int) Math.round(1210 * trollkarlen.widthSize), (int) Math.round(100 * trollkarlen.heightSize), (int) Math.round(125 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		elixirS�ljBtn.addActionListener(new elixirS�ljBtnAct());
		elixirS�ljBtn.setContentAreaFilled(false);
		elixirS�ljBtn.setFocusPainted(false);
		elixirS�ljBtn.setFont(new Font("comic sans ms", Font.BOLD, 15));
		elixirS�ljBtn.setText("S�lj elixir");
		elixirS�ljBtn.setVisible(false);
		
		//alla elixir som s�ljs, dessa �r samma som listan �ver grundelixir i verkstadKlass
		for (int i = 0; i < 13; i++)
		{
			elixirLabel[i] = new JLabel();
			this.add(elixirLabel[i]);
			elixirLabel[i].setBounds((int) Math.round(975 * trollkarlen.widthSize), (int) Math.round((100 + 50*i) * trollkarlen.heightSize), (int) Math.round(175 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
			elixirLabel[i].setFont(new Font("comic sans ms", Font.BOLD, 15));
			elixirLabel[i].setVisible(false);
		}
		elixirLabel[0].setText("$200 Eld");
		elixirLabel[1].setText("$200 Is");
		elixirLabel[2].setText("$200 Vind");
		elixirLabel[3].setText("$200 Gift");
		elixirLabel[4].setText("$200 Gifteld");
		elixirLabel[5].setText("$200 F�rsvaga");
		elixirLabel[6].setText("$200 Hela");
		elixirLabel[7].setText("$200 Hela �ver tid");
		elixirLabel[8].setText("$200 F�rg�raren");
		elixirLabel[9].setText("$200 F�rst�rk");
		elixirLabel[10].setText("$200 Framkalla pengar");
		elixirLabel[11].setText("$200 Bed�va");
		elixirLabel[12].setText("$200 Bed�va alla");
		
		//knapparna l�nkade till elixiren som kan k�pas
		for (int i = 0; i < 13; i++)
		{
			k�pElixirBtn[i] = new JButton();
			this.add(k�pElixirBtn[i]);
			k�pElixirBtn[i].setBounds((int) Math.round(1210 * trollkarlen.widthSize), (int) Math.round((100 + 50*i) * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
			k�pElixirBtn[i].setContentAreaFilled(false);
			k�pElixirBtn[i].setFocusPainted(false);
			k�pElixirBtn[i].setFont(new Font("comic sans ms", Font.BOLD, 15));
			k�pElixirBtn[i].setText("K�p");
			k�pElixirBtn[i].setVisible(false);
		}
		k�pElixirBtn[0].addActionListener(new k�pElixirBtn1Act());
		k�pElixirBtn[1].addActionListener(new k�pElixirBtn2Act());
		k�pElixirBtn[2].addActionListener(new k�pElixirBtn3Act());
		k�pElixirBtn[3].addActionListener(new k�pElixirBtn4Act());
		k�pElixirBtn[4].addActionListener(new k�pElixirBtn5Act());
		k�pElixirBtn[5].addActionListener(new k�pElixirBtn6Act());
		k�pElixirBtn[6].addActionListener(new k�pElixirBtn7Act());
		k�pElixirBtn[7].addActionListener(new k�pElixirBtn8Act());
		k�pElixirBtn[8].addActionListener(new k�pElixirBtn9Act());
		k�pElixirBtn[9].addActionListener(new k�pElixirBtn10Act());
		k�pElixirBtn[10].addActionListener(new k�pElixirBtn11Act());
		k�pElixirBtn[11].addActionListener(new k�pElixirBtn12Act());
		k�pElixirBtn[12].addActionListener(new k�pElixirBtn13Act());
	}
	
	public void paintComponent(Graphics g)
	{
		//fixar bakgrundens dimensioner och s�tter ut den
		g.drawImage(bakgrund, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	//knappen som byter position till stad, butikmenyn �terst�lls. EFTER VARJE HANDLING L�GGS S�MN TILL
	static class stadBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for (int i = 0; i < 5; i++)
			{
				elixirBtn[i].setVisible(false);
			}
			elixirS�ljBtn.setVisible(false);
			
			for (int i = 0; i < 13; i++)
			{
				k�pElixirBtn[i].setVisible(false);
				elixirLabel[i].setVisible(false);
			}
			stadKlass.uppdateraStats();
			trollkarlen.position = "stad";
			trollkarlen.trollkarl.setS�mn(trollkarlen.trollkarl.getS�mn() - 5);
			trollkarlen.Byta(trollkarlen.stad);
			trollkarlen.trollkarl.kollaS�mn();
		}
	}
	
	//visar menyn f�r att s�lja elixir, d�ljer annat
	static class s�ljBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for (int i = 0; i < 13; i++)
			{
				k�pElixirBtn[i].setVisible(false);
				elixirLabel[i].setVisible(false);
			}
			
			for (int i = 0; i < 5; i++)
			{
				elixirBtn[i].setVisible(true);
			}
			elixirS�ljBtn.setVisible(true);
		}
	}
	
	//menyn f�r att k�pa elixir, d�ljer annat
	static class k�pBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for (int i = 0; i < 5; i++)
			{
				elixirBtn[i].setVisible(false);
			}
			elixirS�ljBtn.setVisible(false);
			
			for (int i = 0; i < 13; i++)
			{
				k�pElixirBtn[i].setVisible(true);
				elixirLabel[i].setVisible(true);
			}
		}
	}
	
	//knappar f�r att k�pa olika elixir, g�r till f�Elixir()
	static class k�pElixirBtn1Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//k�ptElixir �r l�nkat med vilken elixirtyp som k�ps
			k�ptElixir = 0;
			f�Elixir();
		}
	}
	static class k�pElixirBtn2Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 1;
			f�Elixir();
		}
	}
	static class k�pElixirBtn3Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 2;
			f�Elixir();
		}
	}
	static class k�pElixirBtn4Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 3;
			f�Elixir();
		}
	}
	static class k�pElixirBtn5Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 4;
			f�Elixir();
		}
	}
	static class k�pElixirBtn6Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 5;
			f�Elixir();
		}
	}
	static class k�pElixirBtn7Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 6;
			f�Elixir();
		}
	}
	static class k�pElixirBtn8Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 7;
			f�Elixir();
		}
	}
	static class k�pElixirBtn9Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 8;
			f�Elixir();
		}
	}
	static class k�pElixirBtn10Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 9;
			f�Elixir();
		}
	}
	static class k�pElixirBtn11Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 10;
			f�Elixir();
		}
	}
	static class k�pElixirBtn12Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 11;
			f�Elixir();
		}
	}
	static class k�pElixirBtn13Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			k�ptElixir = 12;
			f�Elixir();
		}
	}
	
	static void f�Elixir()
	{
		//alla elixir kostar 200, om spelaren har r�d k�ps ett av dem
		if(trollkarlen.trollkarl.getPengar() >= 200)
		{
			for(int i = 0; i < 5; i++)
			{
				//kollar att det finns ledig plats
				if(stridKlass.elixirBtn[i].getText() == "[Inget]")
				{
					//d�r det finns plats skapas ett elixir utifr�n listan med grundelixir, grundelixiret v�ljs fr�n numret som ges fr�n k�pElixirBtn. 
					verkstadKlass.elixir[i] = verkstadKlass.elixirLista[k�ptElixir];
					//speciell text m�ste s�ttas p� knapparna i stridKlass
					switch(k�ptElixir)
					{
					case 0:
						stridKlass.elixirBtn[i].setText("Eld");
						break;

					case 1:
						stridKlass.elixirBtn[i].setText("Is");
						break;

					case 2:
						stridKlass.elixirBtn[i].setText("Vind");
						break;

					case 3:
						stridKlass.elixirBtn[i].setText("Gift");
						break;

					case 4:
						stridKlass.elixirBtn[i].setText("Gifteld");
						break;

					case 5:
						stridKlass.elixirBtn[i].setText("F�rsvaga");
						break;

					case 6:
						stridKlass.elixirBtn[i].setText("Hela");
						break;

					case 7:
						stridKlass.elixirBtn[i].setText("Hela �ver tid");
						break;

					case 8:
						stridKlass.elixirBtn[i].setText("F�rg�raren");
						break;

					case 9:
						stridKlass.elixirBtn[i].setText("F�rst�rk");
						break;

					case 10:
						stridKlass.elixirBtn[i].setText("Framkalla pengar");
						break;

					case 11:
						stridKlass.elixirBtn[i].setText("Bed�va");
						break;

					case 12:
						stridKlass.elixirBtn[i].setText("Bed�va alla");
						break;
					}
					//i s�ljmenyn �r knapparna med s�ljbara elixir vita
					elixirBtn[i].setBackground(new Color(255, 255, 255));
					//tar pengarna fr�n spelaren och avslutar sekvensen
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() - 200);
					break;
				}
				else if(i == 4)
				{
					//ERROR
					//"alla elixirplatser �r upptagna"
				}
			}
		}
		else
		{
			//ERROR
			//"du har f�r lite pengar"
		}
	}
	
	//knappen f�r att s�lja ett elixir
	static class elixirS�ljBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//om elixirX �r tillg�ngligt
			switch(valtElixir)
			{
			case 0:
				if(stridKlass.elixirBtn[0].getText() != "[Inget]")
				{
					//�terst�ller elixir, knappen f�r det i stridKlass och det visas som gr�tt i s�ljmenyn (f�rgen f�r os�ljbart elixir)
					stridKlass.elixirBtn[0].setText("[Inget]");
					verkstadKlass.elixir[valtElixir] = verkstadKlass.elixirLista[0];
					elixirBtn[valtElixir].setBackground(new Color(100, 100, 100));
					//spelaren f�r 50 $ f�r sitt elixir
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() + 50);
					
					//inget elixir �r valt l�ngre
					valtElixir = -1;
				}
				else
				{
					//ERROR
					//"det finns inget elixir att s�lja"
				}
				break;
				
			case 1:
				if(stridKlass.elixirBtn[1].getText() != "[Inget]")
				{
					stridKlass.elixirBtn[1].setText("[Inget]");
					verkstadKlass.elixir[valtElixir] = verkstadKlass.elixirLista[0];
					elixirBtn[valtElixir].setBackground(new Color(100, 100, 100));
					
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() + 50);
					
					valtElixir = -1;
				}
				else
				{
					//error
				}
				break;
				
			case 2:
				if(stridKlass.elixirBtn[2].getText() != "[Inget]")
				{
					stridKlass.elixirBtn[2].setText("[Inget]");
					verkstadKlass.elixir[valtElixir] = verkstadKlass.elixirLista[0];
					elixirBtn[valtElixir].setBackground(new Color(100, 100, 100));
					
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() + 50);
					
					valtElixir = -1;
				}
				else
				{
					//error
				}
				break;
				
			case 3:
				if(stridKlass.elixirBtn[3].getText() != "[Inget]")
				{
					stridKlass.elixirBtn[3].setText("[Inget]");
					verkstadKlass.elixir[valtElixir] = verkstadKlass.elixirLista[0];
					elixirBtn[valtElixir].setBackground(new Color(100, 100, 100));
					
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() + 50);
					
					valtElixir = -1;
				}
				else
				{
					//error
				}
				break;
				
			case 4:
				if(stridKlass.elixirBtn[4].getText() != "[Inget]")
				{
					stridKlass.elixirBtn[4].setText("[Inget]");
					verkstadKlass.elixir[valtElixir] = verkstadKlass.elixirLista[0];
					elixirBtn[valtElixir].setBackground(new Color(100, 100, 100));
					
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() + 50);
					
					valtElixir = -1;
				}
				else
				{
					//error
				}
				break;
				
				default:
					//ERROR
					//"du har inte valt n�got elixir"
					break;
			}
		}
	}
	
	//actionlisteners f�r knappar f�r elixir man kan s�lja
	static class elixirBtn1Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//g�r knapparna antingen gr�a eller vita
			�terst�llKnappar();
			
			//kollar tillg�nglighet
			if(stridKlass.elixirBtn[0].getText() != "[Inget]")
			{
				if(valtElixir == 0)
				{
					//om man trycker p� elixiret en andra g�ng blir det vitt igen
					elixirBtn[0].setBackground(new Color(255, 255, 255));
					valtElixir = -1;
				}
				else
				{
					//om elixiret �r tillg�ngligt och man inte har valt det blir det gr�nt
					elixirBtn[0].setBackground(new Color(0, 255, 0));
					valtElixir = 0;
				}
			}
			else
			{
				valtElixir = -1;
				//ERROR
				//"detta elixiret �r otilg�ngligt"
			}
		}
	}
	static class elixirBtn2Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			�terst�llKnappar();
			
			if(stridKlass.elixirBtn[1].getText() != "[Inget]")
			{
				if(valtElixir == 1)
				{
					elixirBtn[1].setBackground(new Color(255, 255, 255));
					valtElixir = -1;
				}
				else
				{
					elixirBtn[1].setBackground(new Color(0, 255, 0));
					valtElixir = 1;
				}
			}
			else
			{
				valtElixir = -1;
				//ERROR
			}
		}
	}
	static class elixirBtn3Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			�terst�llKnappar();
			
			if(stridKlass.elixirBtn[2].getText() != "[Inget]")
			{
				if(valtElixir == 2)
				{
					elixirBtn[2].setBackground(new Color(255, 255, 255));
					valtElixir = -1;
				}
				else
				{
					elixirBtn[2].setBackground(new Color(0, 255, 0));
					valtElixir = 2;
				}
			}
			else
			{
				valtElixir = -1;
				//ERROR
			}
		}
	}
	static class elixirBtn4Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			�terst�llKnappar();
			
			if(stridKlass.elixirBtn[3].getText() != "[Inget]")
			{
				if(valtElixir == 3)
				{
					elixirBtn[3].setBackground(new Color(255, 255, 255));
					valtElixir = -1;
				}
				else
				{
					elixirBtn[3].setBackground(new Color(0, 255, 0));
					valtElixir = 3;
				}
			}
			else
			{
				valtElixir = -1;
				//ERROR
			}
		}
	}
	static class elixirBtn5Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			�terst�llKnappar();
			
			if(stridKlass.elixirBtn[4].getText() != "[Inget]")
			{
				if(valtElixir == 4)
				{
					elixirBtn[4].setBackground(new Color(255, 255, 255));
					valtElixir = -1;
				}
				else
				{
					elixirBtn[4].setBackground(new Color(0, 255, 0));
					valtElixir = 4;
				}
			}
			else
			{
				valtElixir = -1;
				//ERROR
			}
		}
	}
	
	//g�r knapparna antingen gr�a eller vita enligt deras tillg�nglighet
	public static void �terst�llKnappar()
	{
		for(int i = 0; i < 5; i++)
		{
			if(stridKlass.elixirBtn[i].getText() != "[Inget]")
			{
				elixirBtn[i].setBackground(new Color(255, 255, 255));
			}
			else
			{
				elixirBtn[i].setBackground(new Color(100, 100, 100));
			}
		}
	}
}

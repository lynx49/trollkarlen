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
	static JButton stadBtn = new JButton(), säljBtn = new JButton(), köpBtn = new JButton(), /*för elixir: uppgraderaBtn = new JButton(),*/ elixirSäljBtn = new JButton(), sovBtn = new JButton();
	static JLabel[] elixirLabel = new JLabel[13];
	static JButton[] elixirBtn = new JButton[5], köpElixirBtn = new JButton[13];
	static int valtElixir, köptElixir = -1;
	
	butikKlass()
	{
		//kollar om den tredje bakgrundsbilden från trollkarlen går att läsa in
		try
		{
			//sätter bilden, kan nu användas för att ritas upp
			bakgrund = ImageIO.read(trollkarlen.bakgrunder[2]);
		}
		catch (Exception e)
		{
			System.out.print("fel2");
		}
		
		this.setLayout(null);
		
		this.add(stadBtn);
		this.add(säljBtn);
		this.add(köpBtn);
		//this.add(uppgraderaBtn);
		this.add(elixirSäljBtn);
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
		
		säljBtn.setBounds((int) Math.round(930 * trollkarlen.widthSize), (int) Math.round(15 * trollkarlen.heightSize), (int) Math.round(80 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		säljBtn.addActionListener(new säljBtnAct());
		säljBtn.setContentAreaFilled(false);
		säljBtn.setFocusPainted(false);
		säljBtn.setFont(new Font("comic sans ms", Font.BOLD, 20));
		säljBtn.setText("Sälj");
		
		köpBtn.setBounds((int) Math.round(1020 * trollkarlen.widthSize), (int) Math.round(15 * trollkarlen.heightSize), (int) Math.round(70 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		köpBtn.addActionListener(new köpBtnAct());
		köpBtn.setContentAreaFilled(false);
		köpBtn.setFocusPainted(false);
		köpBtn.setFont(new Font("comic sans ms", Font.BOLD, 20));
		köpBtn.setText("Köp");
		
		/*uppgraderaBtn.setBounds((int) Math.round(1100 * trollkarlen.widthSize), (int) Math.round(15 * trollkarlen.heightSize), (int) Math.round(150 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		uppgraderaBtn.setContentAreaFilled(false);
		uppgraderaBtn.setFocusPainted(false);
		uppgraderaBtn.setFont(new Font("comic sans ms", Font.BOLD, 20));
		uppgraderaBtn.setText("Uppgradera");
		
		Om man uppgraderar ett elixir får det t.ex mer skada eller längre effekt, man bara väljer elixiret och gör så här: elixirX.setX(elixirX.getX() + ökning);
		
		*/
		
		//lägger till knapparna för att välja elixir i rad så att de enkelt kan kontrolleras (man kan t.ex se om användaren har något där eller inte)
		//samma siffra som i verkstad fungerar för dessa
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
		
		//actionlisteners måste läggas till separat
		elixirBtn[0].addActionListener(new elixirBtn1Act());
		elixirBtn[1].addActionListener(new elixirBtn2Act());
		elixirBtn[2].addActionListener(new elixirBtn3Act());
		elixirBtn[3].addActionListener(new elixirBtn4Act());
		elixirBtn[4].addActionListener(new elixirBtn5Act());
		
		elixirSäljBtn.setBounds((int) Math.round(1210 * trollkarlen.widthSize), (int) Math.round(100 * trollkarlen.heightSize), (int) Math.round(125 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		elixirSäljBtn.addActionListener(new elixirSäljBtnAct());
		elixirSäljBtn.setContentAreaFilled(false);
		elixirSäljBtn.setFocusPainted(false);
		elixirSäljBtn.setFont(new Font("comic sans ms", Font.BOLD, 15));
		elixirSäljBtn.setText("Sälj elixir");
		elixirSäljBtn.setVisible(false);
		
		//alla elixir som säljs, dessa är samma som listan över grundelixir i verkstadKlass
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
		elixirLabel[5].setText("$200 Försvaga");
		elixirLabel[6].setText("$200 Hela");
		elixirLabel[7].setText("$200 Hela över tid");
		elixirLabel[8].setText("$200 Förgöraren");
		elixirLabel[9].setText("$200 Förstärk");
		elixirLabel[10].setText("$200 Framkalla pengar");
		elixirLabel[11].setText("$200 Bedöva");
		elixirLabel[12].setText("$200 Bedöva alla");
		
		//knapparna länkade till elixiren som kan köpas
		for (int i = 0; i < 13; i++)
		{
			köpElixirBtn[i] = new JButton();
			this.add(köpElixirBtn[i]);
			köpElixirBtn[i].setBounds((int) Math.round(1210 * trollkarlen.widthSize), (int) Math.round((100 + 50*i) * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
			köpElixirBtn[i].setContentAreaFilled(false);
			köpElixirBtn[i].setFocusPainted(false);
			köpElixirBtn[i].setFont(new Font("comic sans ms", Font.BOLD, 15));
			köpElixirBtn[i].setText("Köp");
			köpElixirBtn[i].setVisible(false);
		}
		köpElixirBtn[0].addActionListener(new köpElixirBtn1Act());
		köpElixirBtn[1].addActionListener(new köpElixirBtn2Act());
		köpElixirBtn[2].addActionListener(new köpElixirBtn3Act());
		köpElixirBtn[3].addActionListener(new köpElixirBtn4Act());
		köpElixirBtn[4].addActionListener(new köpElixirBtn5Act());
		köpElixirBtn[5].addActionListener(new köpElixirBtn6Act());
		köpElixirBtn[6].addActionListener(new köpElixirBtn7Act());
		köpElixirBtn[7].addActionListener(new köpElixirBtn8Act());
		köpElixirBtn[8].addActionListener(new köpElixirBtn9Act());
		köpElixirBtn[9].addActionListener(new köpElixirBtn10Act());
		köpElixirBtn[10].addActionListener(new köpElixirBtn11Act());
		köpElixirBtn[11].addActionListener(new köpElixirBtn12Act());
		köpElixirBtn[12].addActionListener(new köpElixirBtn13Act());
	}
	
	public void paintComponent(Graphics g)
	{
		//fixar bakgrundens dimensioner och sätter ut den
		g.drawImage(bakgrund, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	//knappen som byter position till stad, butikmenyn återställs. EFTER VARJE HANDLING LÄGGS SÖMN TILL
	static class stadBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for (int i = 0; i < 5; i++)
			{
				elixirBtn[i].setVisible(false);
			}
			elixirSäljBtn.setVisible(false);
			
			for (int i = 0; i < 13; i++)
			{
				köpElixirBtn[i].setVisible(false);
				elixirLabel[i].setVisible(false);
			}
			stadKlass.uppdateraStats();
			trollkarlen.position = "stad";
			trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() - 5);
			trollkarlen.Byta(trollkarlen.stad);
			trollkarlen.trollkarl.kollaSömn();
		}
	}
	
	//visar menyn för att sälja elixir, döljer annat
	static class säljBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for (int i = 0; i < 13; i++)
			{
				köpElixirBtn[i].setVisible(false);
				elixirLabel[i].setVisible(false);
			}
			
			for (int i = 0; i < 5; i++)
			{
				elixirBtn[i].setVisible(true);
			}
			elixirSäljBtn.setVisible(true);
		}
	}
	
	//menyn för att köpa elixir, döljer annat
	static class köpBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for (int i = 0; i < 5; i++)
			{
				elixirBtn[i].setVisible(false);
			}
			elixirSäljBtn.setVisible(false);
			
			for (int i = 0; i < 13; i++)
			{
				köpElixirBtn[i].setVisible(true);
				elixirLabel[i].setVisible(true);
			}
		}
	}
	
	//knappar för att köpa olika elixir, går till fåElixir()
	static class köpElixirBtn1Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//köptElixir är länkat med vilken elixirtyp som köps
			köptElixir = 0;
			fåElixir();
		}
	}
	static class köpElixirBtn2Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 1;
			fåElixir();
		}
	}
	static class köpElixirBtn3Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 2;
			fåElixir();
		}
	}
	static class köpElixirBtn4Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 3;
			fåElixir();
		}
	}
	static class köpElixirBtn5Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 4;
			fåElixir();
		}
	}
	static class köpElixirBtn6Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 5;
			fåElixir();
		}
	}
	static class köpElixirBtn7Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 6;
			fåElixir();
		}
	}
	static class köpElixirBtn8Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 7;
			fåElixir();
		}
	}
	static class köpElixirBtn9Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 8;
			fåElixir();
		}
	}
	static class köpElixirBtn10Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 9;
			fåElixir();
		}
	}
	static class köpElixirBtn11Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 10;
			fåElixir();
		}
	}
	static class köpElixirBtn12Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 11;
			fåElixir();
		}
	}
	static class köpElixirBtn13Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			köptElixir = 12;
			fåElixir();
		}
	}
	
	static void fåElixir()
	{
		//alla elixir kostar 200, om spelaren har råd köps ett av dem
		if(trollkarlen.trollkarl.getPengar() >= 200)
		{
			for(int i = 0; i < 5; i++)
			{
				//kollar att det finns ledig plats
				if(stridKlass.elixirBtn[i].getText() == "[Inget]")
				{
					//där det finns plats skapas ett elixir utifrån listan med grundelixir, grundelixiret väljs från numret som ges från köpElixirBtn. 
					verkstadKlass.elixir[i] = verkstadKlass.elixirLista[köptElixir];
					//speciell text måste sättas på knapparna i stridKlass
					switch(köptElixir)
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
						stridKlass.elixirBtn[i].setText("Försvaga");
						break;

					case 6:
						stridKlass.elixirBtn[i].setText("Hela");
						break;

					case 7:
						stridKlass.elixirBtn[i].setText("Hela över tid");
						break;

					case 8:
						stridKlass.elixirBtn[i].setText("Förgöraren");
						break;

					case 9:
						stridKlass.elixirBtn[i].setText("Förstärk");
						break;

					case 10:
						stridKlass.elixirBtn[i].setText("Framkalla pengar");
						break;

					case 11:
						stridKlass.elixirBtn[i].setText("Bedöva");
						break;

					case 12:
						stridKlass.elixirBtn[i].setText("Bedöva alla");
						break;
					}
					//i säljmenyn är knapparna med säljbara elixir vita
					elixirBtn[i].setBackground(new Color(255, 255, 255));
					//tar pengarna från spelaren och avslutar sekvensen
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() - 200);
					break;
				}
				else if(i == 4)
				{
					//ERROR
					//"alla elixirplatser är upptagna"
				}
			}
		}
		else
		{
			//ERROR
			//"du har för lite pengar"
		}
	}
	
	//knappen för att sälja ett elixir
	static class elixirSäljBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//om elixirX är tillgängligt
			switch(valtElixir)
			{
			case 0:
				if(stridKlass.elixirBtn[0].getText() != "[Inget]")
				{
					//återställer elixir, knappen för det i stridKlass och det visas som grått i säljmenyn (färgen för osäljbart elixir)
					stridKlass.elixirBtn[0].setText("[Inget]");
					verkstadKlass.elixir[valtElixir] = verkstadKlass.elixirLista[0];
					elixirBtn[valtElixir].setBackground(new Color(100, 100, 100));
					//spelaren får 50 $ för sitt elixir
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() + 50);
					
					//inget elixir är valt längre
					valtElixir = -1;
				}
				else
				{
					//ERROR
					//"det finns inget elixir att sälja"
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
					//"du har inte valt något elixir"
					break;
			}
		}
	}
	
	//actionlisteners för knappar för elixir man kan sälja
	static class elixirBtn1Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//gör knapparna antingen gråa eller vita
			återställKnappar();
			
			//kollar tillgänglighet
			if(stridKlass.elixirBtn[0].getText() != "[Inget]")
			{
				if(valtElixir == 0)
				{
					//om man trycker på elixiret en andra gång blir det vitt igen
					elixirBtn[0].setBackground(new Color(255, 255, 255));
					valtElixir = -1;
				}
				else
				{
					//om elixiret är tillgängligt och man inte har valt det blir det grönt
					elixirBtn[0].setBackground(new Color(0, 255, 0));
					valtElixir = 0;
				}
			}
			else
			{
				valtElixir = -1;
				//ERROR
				//"detta elixiret är otilgängligt"
			}
		}
	}
	static class elixirBtn2Act implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			återställKnappar();
			
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
			återställKnappar();
			
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
			återställKnappar();
			
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
			återställKnappar();
			
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
	
	//gör knapparna antingen gråa eller vita enligt deras tillgänglighet
	public static void återställKnappar()
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

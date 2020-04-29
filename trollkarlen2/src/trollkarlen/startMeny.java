package trollkarlen;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class startMeny extends JPanel
{
	//skapar variabler och komponenter
	static JButton avslutaSpelBtn = new JButton(), nyttSpelBtn = new JButton();
	static JLabel namnLabel = new JLabel();
	static JTextField namnSkriv = new JTextField();
	static int mellanslag, storaBokstäver = 0;
	static char numTillChar;
	static boolean accepteratNamn;
	
	startMeny()
	{
		//gör så att knappar kan placeras fritt
		this.setLayout(null);
		
		//lägger till gränssnitt
		this.add(avslutaSpelBtn);
		this.add(nyttSpelBtn);
		this.add(namnLabel);
		this.add(namnSkriv);
		
		//sätter utseende på gränssnitt + actionlisteners
		avslutaSpelBtn.setBounds((int) Math.round(500 * trollkarlen.widthSize), (int) Math.round(575 * trollkarlen.heightSize), (int) Math.round(371 * trollkarlen.widthSize), (int) Math.round(76 * trollkarlen.heightSize));
		avslutaSpelBtn.addActionListener(new avslutaSpelBtnAct());
		avslutaSpelBtn.setContentAreaFilled(false);
		avslutaSpelBtn.setFocusPainted(false);
		avslutaSpelBtn.setFont(new Font("comic sans ms", Font.BOLD, 30));
		avslutaSpelBtn.setText("Avsluta");
		
		nyttSpelBtn.setBounds((int) Math.round(500 * trollkarlen.widthSize), (int) Math.round(475 * trollkarlen.heightSize), (int) Math.round(371 * trollkarlen.widthSize), (int) Math.round(76 * trollkarlen.heightSize));
		nyttSpelBtn.addActionListener(new nyttSpelBtnAct());
		nyttSpelBtn.setContentAreaFilled(false);
		nyttSpelBtn.setFocusPainted(false);
		nyttSpelBtn.setFont(new Font("comic sans ms", Font.BOLD, 30));
		nyttSpelBtn.setText("Nytt spel");
		
		namnLabel.setBounds((int) Math.round(500 * trollkarlen.widthSize), (int) Math.round(325 * trollkarlen.heightSize), (int) Math.round(371 * trollkarlen.widthSize), (int) Math.round(76 * trollkarlen.heightSize));
		namnLabel.setHorizontalAlignment(JLabel.CENTER);
		namnLabel.setFont(new Font("comic sans ms", Font.BOLD, 30));
		namnLabel.setText("V~| Välj ditt namn |~V");
		namnLabel.setVisible(false);
		
		namnSkriv.setBounds((int) Math.round(500 * trollkarlen.widthSize), (int) Math.round(391 * trollkarlen.heightSize), (int) Math.round(371 * trollkarlen.widthSize), (int) Math.round(60 * trollkarlen.heightSize));
		namnSkriv.addFocusListener(new FocusListener() {
			//om man lägger fokus på texten när man inte haft fokus på den, försvinner den
			public void focusGained(FocusEvent e) {
				namnSkriv.setText("");
			}

			public void focusLost(FocusEvent e) {
				
			}
		});
		namnSkriv.setHorizontalAlignment(JLabel.CENTER);
		namnSkriv.setFont(new Font("comic sans ms", Font.BOLD, 30));
		namnSkriv.setVisible(false);
	}
	
	//stänger fönstret och spelet
	static class avslutaSpelBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			trollkarlen.fönstret.dispose();
		}
	}
	
	//skapar trollkarlen
	static void setTrollkarl()
	{
		//återställer trollkarlen så att det inte blir problem med att skapa en ny
		trollkarlen.trollkarl = new Spelare(100, 0, new boolean[5], new int[5], null, 0, 100, 100);
		//variabler för namnegenskaper
		mellanslag = 0;
		storaBokstäver = 0;
		accepteratNamn = false;
		//kollar efter stora bokstäver och olämpliga mellanslag
		for(int i = 0; i < namnSkriv.getText().length(); i++)
		{
			numTillChar = namnSkriv.getText().charAt(i);
			
			if(Character.isUpperCase(numTillChar))
			{
				storaBokstäver++;
				//om namnet kan nå sin fulla längd utan problem
				if(i == namnSkriv.getText().length() - 1)
				{
					trollkarlen.trollkarl.setNamn(namnSkriv.getText());
					accepteratNamn = true;
				}
			}
			else if(numTillChar == ' ')
			{
				mellanslag++;
				if(i > 0 && namnSkriv.getText().charAt(i-1) == 32)
				{
					break;
				}
				else if(i == 0 || i == namnSkriv.getText().length() - 1)
				{
					break;
				}
			}
			//om namnet kan nå sin fulla längd utan problem
			else if(i == namnSkriv.getText().length() - 1)
			{
				trollkarlen.trollkarl.setNamn(namnSkriv.getText());
				accepteratNamn = true;
			}
		}
		
		if(accepteratNamn)
		{
			if(trollkarlen.trollkarl.getNamn().equalsIgnoreCase("namn"))
			{
				//TEXT
				//"Tror du att du är rolig? Du har aktiverat hardcore-läget"
				trollkarlen.trollkarl.setLiv(10);
				trollkarlen.trollkarl.setMagi(5);
				trollkarlen.trollkarl.setPengar(0);
				trollkarlen.trollkarl.setKraft(0);
			}
			else
			{
				trollkarlen.trollkarl.setLiv(100);
				//beroende på namnlängd
				trollkarlen.trollkarl.setMagi(95 + (trollkarlen.trollkarl.getNamn().length()*2));
				//beroende på namnets mellanslag FIXA
				trollkarlen.trollkarl.setPengar(0 + mellanslag*25);
				//beroende på antal stora bokstäver i namnet FIXA
				trollkarlen.trollkarl.setKraft(0 + storaBokstäver*3);
			}
			
			//uppdaterar informationen i gränssnittet, finns bara i stad ännu
			stadKlass.uppdateraStats();
		}
	}
	
	static class nyttSpelBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//om det finns möjlighet att välja namn
			if(namnSkriv.isVisible())
			{
				//om namnet verkar lämpligt
				if(namnSkriv.getText().length() > 0 && !namnSkriv.getText().equals("Skriv ett äkta namn"))
				{
					setTrollkarl();
					//om namnet är lämpligt
					if(accepteratNamn)
					{
						//labels för stadKlass behöver namnges här eftersom namnet uppdateras efter uppstart
						//byter till stad
						nyttSpelBtn.setText("Bekräftat");
						stadKlass.namnLabel.setText("Namn: " + trollkarlen.trollkarl.getNamn());
						trollkarlen.position = "stad";
						trollkarlen.Byta(trollkarlen.stad);
					}
					else
					{
						nyttSpelBtn.setText("Ej bekräftat");
						//ERROR
						//Ditt namn är inte lämpligt: inga mellanslag i början/slutet och inte mer än 2 i rad
					}
				}
				else
				{
					namnSkriv.setText("Skriv ett äkta namn");
				}
			}
			//om det inte finns möjlighet att skriva namn, blir det möjligt
			else
			{
				nyttSpelBtn.setText("Bekräfta");
				namnLabel.setVisible(true);
				namnSkriv.setVisible(true);
			}
		}
	}
}

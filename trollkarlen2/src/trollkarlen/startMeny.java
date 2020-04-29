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
	static int mellanslag, storaBokst�ver = 0;
	static char numTillChar;
	static boolean accepteratNamn;
	
	startMeny()
	{
		//g�r s� att knappar kan placeras fritt
		this.setLayout(null);
		
		//l�gger till gr�nssnitt
		this.add(avslutaSpelBtn);
		this.add(nyttSpelBtn);
		this.add(namnLabel);
		this.add(namnSkriv);
		
		//s�tter utseende p� gr�nssnitt + actionlisteners
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
		namnLabel.setText("V~| V�lj ditt namn |~V");
		namnLabel.setVisible(false);
		
		namnSkriv.setBounds((int) Math.round(500 * trollkarlen.widthSize), (int) Math.round(391 * trollkarlen.heightSize), (int) Math.round(371 * trollkarlen.widthSize), (int) Math.round(60 * trollkarlen.heightSize));
		namnSkriv.addFocusListener(new FocusListener() {
			//om man l�gger fokus p� texten n�r man inte haft fokus p� den, f�rsvinner den
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
	
	//st�nger f�nstret och spelet
	static class avslutaSpelBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			trollkarlen.f�nstret.dispose();
		}
	}
	
	//skapar trollkarlen
	static void setTrollkarl()
	{
		//�terst�ller trollkarlen s� att det inte blir problem med att skapa en ny
		trollkarlen.trollkarl = new Spelare(100, 0, new boolean[5], new int[5], null, 0, 100, 100);
		//variabler f�r namnegenskaper
		mellanslag = 0;
		storaBokst�ver = 0;
		accepteratNamn = false;
		//kollar efter stora bokst�ver och ol�mpliga mellanslag
		for(int i = 0; i < namnSkriv.getText().length(); i++)
		{
			numTillChar = namnSkriv.getText().charAt(i);
			
			if(Character.isUpperCase(numTillChar))
			{
				storaBokst�ver++;
				//om namnet kan n� sin fulla l�ngd utan problem
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
			//om namnet kan n� sin fulla l�ngd utan problem
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
				//"Tror du att du �r rolig? Du har aktiverat hardcore-l�get"
				trollkarlen.trollkarl.setLiv(10);
				trollkarlen.trollkarl.setMagi(5);
				trollkarlen.trollkarl.setPengar(0);
				trollkarlen.trollkarl.setKraft(0);
			}
			else
			{
				trollkarlen.trollkarl.setLiv(100);
				//beroende p� namnl�ngd
				trollkarlen.trollkarl.setMagi(95 + (trollkarlen.trollkarl.getNamn().length()*2));
				//beroende p� namnets mellanslag FIXA
				trollkarlen.trollkarl.setPengar(0 + mellanslag*25);
				//beroende p� antal stora bokst�ver i namnet FIXA
				trollkarlen.trollkarl.setKraft(0 + storaBokst�ver*3);
			}
			
			//uppdaterar informationen i gr�nssnittet, finns bara i stad �nnu
			stadKlass.uppdateraStats();
		}
	}
	
	static class nyttSpelBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//om det finns m�jlighet att v�lja namn
			if(namnSkriv.isVisible())
			{
				//om namnet verkar l�mpligt
				if(namnSkriv.getText().length() > 0 && !namnSkriv.getText().equals("Skriv ett �kta namn"))
				{
					setTrollkarl();
					//om namnet �r l�mpligt
					if(accepteratNamn)
					{
						//labels f�r stadKlass beh�ver namnges h�r eftersom namnet uppdateras efter uppstart
						//byter till stad
						nyttSpelBtn.setText("Bekr�ftat");
						stadKlass.namnLabel.setText("Namn: " + trollkarlen.trollkarl.getNamn());
						trollkarlen.position = "stad";
						trollkarlen.Byta(trollkarlen.stad);
					}
					else
					{
						nyttSpelBtn.setText("Ej bekr�ftat");
						//ERROR
						//Ditt namn �r inte l�mpligt: inga mellanslag i b�rjan/slutet och inte mer �n 2 i rad
					}
				}
				else
				{
					namnSkriv.setText("Skriv ett �kta namn");
				}
			}
			//om det inte finns m�jlighet att skriva namn, blir det m�jligt
			else
			{
				nyttSpelBtn.setText("Bekr�fta");
				namnLabel.setVisible(true);
				namnSkriv.setVisible(true);
			}
		}
	}
}

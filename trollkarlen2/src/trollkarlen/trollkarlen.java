package trollkarlen;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class trollkarlen
{
	//sk�rmens dimensioner delat p� bredd och h�jd p� den datorn jag anv�nd, t.ex om den datorn som anv�nder programmets bredd �r 2049 px kommer widthSize = 1.5, g�r s� att gr�nssnitt kan passa p� alla sk�rmar
	static double widthSize = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1366, heightSize = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/768;
	//vektor d�r alla bilder l�ggs f�r att snabbt kunna v�ljas
	static URL[] bakgrunder = { trollkarlen.class.getResource("/bilder/stad.png"), trollkarlen.class.getResource("/bilder/skog.png"), trollkarlen.class.getResource("/bilder/butik.png"), trollkarlen.class.getResource("/bilder/verkstad.png"), trollkarlen.class.getResource("/bilder/strid.png") };
	//skapar f�nstret f�r spelet och dess panel som ska ha inneh�llet
	static JFrame f�nstret = new JFrame("Trollkarlen: spelet");
	static JPanel nuvarandePanel = new JPanel();
	//skapar spelare i f�rv�g med hj�lp av konstruktor
	//jag placerar spelare i denna klass eftersom inte mycket h�nder h�r och det �r den "centrala" klassen
	static Spelare trollkarl = new Spelare(100, 0, new boolean[5], new int[5], null, 0, 100, 100);
	//skapar klasser som anv�nds f�r att byta paneler, annars m�ste man skapa nya paneler varje g�ng man vill byta och det sl�ar ned programmet
	static startMeny meny = new startMeny();
	static stadKlass stad = new stadKlass();
	static skogKlass skog = new skogKlass();
	static butikKlass butik = new butikKlass();
	static verkstadKlass verkstad = new verkstadKlass();
	static stridKlass strid = new stridKlass();
	//var spelaren befinner sig
	static String position;
	//f�r alla typer av textrutor som ska visas, dock finns de bara f�r d�d och stridssammanfattning
	static JLabel d�dLabel1 = new JLabel(), /*d�dLabel2 = new JLabel(),*/ statusLabel = new JLabel(), s�mnLabel = new JLabel(), felLabel = new JLabel(), fAttackLabel = new JLabel(), sAttackLabel = new JLabel(), fpAttackLabel = new JLabel(), spAttackLabel = new JLabel(), menyLabel = new JLabel();
	public static int dag = 1;
	
	public static void main (String[] args)
	{
		//f�nstrets utseende, synlighet, storlek och vad som h�nder n�r man st�nger det
		f�nstret.setUndecorated(true);
		f�nstret.setVisible(true);
		f�nstret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f�nstret.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		f�nstret.add(meny);
		//vilken panel f�nstret visar
		nuvarandePanel = meny;
		
		//uppdaterar paneler
		f�nstret.invalidate();
		f�nstret.validate();
		//uppdaterar grafik
		f�nstret.repaint();
		
		/* ALLA TRE LABELS �R F�R TEXTRUTOR
		dessa tv� beh�vs inte l�ngre, kan anv�ndas f�r buggfix
		d�dLabel1.setText("D�dsorsak: f�r lite liv");
		d�dLabel2.setText("D�dsorsak: p�verkan av elixir 'x'");
		 */
		menyLabel.setText("Avslutar spel, �terv�nder till startmenyn");
	}
	
	//byter fr�n en panel till en annan t.ex trollkarlen.stad
	public static void Byta(JPanel panel)
	{
		//tar bort nuvarande panel och l�gger till den man ska till
		f�nstret.remove(nuvarandePanel);
		f�nstret.add(panel);
		//nuvarande panel blir den panel man ska till
		nuvarandePanel = panel;
		//uppdaterar panelen t.ex bild och knappar
		f�nstret.validate();
		f�nstret.repaint();
	}
	
	//n�r man ska sova, eftersom det �r en knapp k�nns det b�ttre att anv�nda den i trollkarlen ist�llet f�r Spelare, passar med uppl�gget
	static class sovBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//kollar var man ska sova och sedan g�r Spelare-klassen sitt
			trollkarl.kollaSovPlats();
			//knapparna slutar vara synliga
			stadKlass.sovBtn.setVisible(false);
			skogKlass.sovBtn.setVisible(false);
			butikKlass.sovBtn.setVisible(false);
			verkstadKlass.sovBtn.setVisible(false);
		}
	}
}

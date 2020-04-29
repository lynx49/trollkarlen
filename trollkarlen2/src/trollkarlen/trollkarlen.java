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
	//skärmens dimensioner delat på bredd och höjd på den datorn jag använd, t.ex om den datorn som använder programmets bredd är 2049 px kommer widthSize = 1.5, gör så att gränssnitt kan passa på alla skärmar
	static double widthSize = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1366, heightSize = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/768;
	//vektor där alla bilder läggs för att snabbt kunna väljas
	static URL[] bakgrunder = { trollkarlen.class.getResource("/bilder/stad.png"), trollkarlen.class.getResource("/bilder/skog.png"), trollkarlen.class.getResource("/bilder/butik.png"), trollkarlen.class.getResource("/bilder/verkstad.png"), trollkarlen.class.getResource("/bilder/strid.png") };
	//skapar fönstret för spelet och dess panel som ska ha innehållet
	static JFrame fönstret = new JFrame("Trollkarlen: spelet");
	static JPanel nuvarandePanel = new JPanel();
	//skapar spelare i förväg med hjälp av konstruktor
	//jag placerar spelare i denna klass eftersom inte mycket händer här och det är den "centrala" klassen
	static Spelare trollkarl = new Spelare(100, 0, new boolean[5], new int[5], null, 0, 100, 100);
	//skapar klasser som används för att byta paneler, annars måste man skapa nya paneler varje gång man vill byta och det slöar ned programmet
	static startMeny meny = new startMeny();
	static stadKlass stad = new stadKlass();
	static skogKlass skog = new skogKlass();
	static butikKlass butik = new butikKlass();
	static verkstadKlass verkstad = new verkstadKlass();
	static stridKlass strid = new stridKlass();
	//var spelaren befinner sig
	static String position;
	//för alla typer av textrutor som ska visas, dock finns de bara för död och stridssammanfattning
	static JLabel dödLabel1 = new JLabel(), /*dödLabel2 = new JLabel(),*/ statusLabel = new JLabel(), sömnLabel = new JLabel(), felLabel = new JLabel(), fAttackLabel = new JLabel(), sAttackLabel = new JLabel(), fpAttackLabel = new JLabel(), spAttackLabel = new JLabel(), menyLabel = new JLabel();
	public static int dag = 1;
	
	public static void main (String[] args)
	{
		//fönstrets utseende, synlighet, storlek och vad som händer när man stänger det
		fönstret.setUndecorated(true);
		fönstret.setVisible(true);
		fönstret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fönstret.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		fönstret.add(meny);
		//vilken panel fönstret visar
		nuvarandePanel = meny;
		
		//uppdaterar paneler
		fönstret.invalidate();
		fönstret.validate();
		//uppdaterar grafik
		fönstret.repaint();
		
		/* ALLA TRE LABELS ÄR FÖR TEXTRUTOR
		dessa två behövs inte längre, kan användas för buggfix
		dödLabel1.setText("Dödsorsak: för lite liv");
		dödLabel2.setText("Dödsorsak: påverkan av elixir 'x'");
		 */
		menyLabel.setText("Avslutar spel, återvänder till startmenyn");
	}
	
	//byter från en panel till en annan t.ex trollkarlen.stad
	public static void Byta(JPanel panel)
	{
		//tar bort nuvarande panel och lägger till den man ska till
		fönstret.remove(nuvarandePanel);
		fönstret.add(panel);
		//nuvarande panel blir den panel man ska till
		nuvarandePanel = panel;
		//uppdaterar panelen t.ex bild och knappar
		fönstret.validate();
		fönstret.repaint();
	}
	
	//när man ska sova, eftersom det är en knapp känns det bättre att använda den i trollkarlen istället för Spelare, passar med upplägget
	static class sovBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//kollar var man ska sova och sedan gör Spelare-klassen sitt
			trollkarl.kollaSovPlats();
			//knapparna slutar vara synliga
			stadKlass.sovBtn.setVisible(false);
			skogKlass.sovBtn.setVisible(false);
			butikKlass.sovBtn.setVisible(false);
			verkstadKlass.sovBtn.setVisible(false);
		}
	}
}

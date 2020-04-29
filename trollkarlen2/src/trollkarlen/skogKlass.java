package trollkarlen;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import trollkarlen.trollkarlen.sovBtnAct;

public class skogKlass extends JPanel
{
	static BufferedImage bakgrund;
	static JButton stadBtn = new JButton(), fiendeBtn = new JButton(), sovBtn = new JButton();
	
	skogKlass()
	{
		//kollar om den andra bakgrundsbilden från trollkarlen går att läsa in
		try
		{
			//sätter bilden, kan nu användas för att ritas upp
			bakgrund = ImageIO.read(trollkarlen.bakgrunder[1]);
		}
		catch (Exception e)
		{
			System.out.print("fel1");
		}
		
		//gör så att knappar kan placeras fritt
		this.setLayout(null);
		
		//lägger till gränssnitt
		this.add(stadBtn);
		this.add(fiendeBtn);
		this.add(sovBtn);
		
		//sätter utseende på gränssnitt + actionlisteners
		stadBtn.setBounds((int) Math.round(35 * trollkarlen.widthSize), (int) Math.round(600 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		stadBtn.addActionListener(new stadBtnAct());
		stadBtn.setContentAreaFilled(false);
		stadBtn.setFocusPainted(false);
		stadBtn.setFont(new Font("comic sans ms", Font.BOLD, 25));
		stadBtn.setText("Stad");
		
		fiendeBtn.setBounds((int) Math.round(400 * trollkarlen.widthSize), (int) Math.round(530 * trollkarlen.heightSize), (int) Math.round(125 * trollkarlen.widthSize), (int) Math.round(225 * trollkarlen.heightSize));
		fiendeBtn.addActionListener(new fiendeBtnAct());
		fiendeBtn.setContentAreaFilled(false);
		fiendeBtn.setFocusPainted(false);
		
		sovBtn.setBounds((int) Math.round(15 * trollkarlen.widthSize), (int) Math.round(680 * trollkarlen.heightSize), (int) Math.round(97 * trollkarlen.widthSize), (int) Math.round(60 * trollkarlen.heightSize));
		sovBtn.addActionListener(new sovBtnAct());
		sovBtn.setContentAreaFilled(false);
		sovBtn.setFocusPainted(false);
		sovBtn.setFont(new Font("comic sans ms", Font.BOLD, 17));
		sovBtn.setText("Sov");
		//knappen för att sova är osynlig och visas bara när spelaren bör sova
		sovBtn.setVisible(false);
	}
	
	public void paintComponent (Graphics g)
	{
		//fixar bakgrundens dimensioner och sätter ut den
		g.drawImage(bakgrund, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	//knappen som byter position till stad EFTER VARJE HANDLING LÄGGS SÖMN TILL
	static class stadBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			stadKlass.uppdateraStats();
			trollkarlen.position = "stad";
			trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() - 10);
			trollkarlen.Byta(trollkarlen.stad);
			trollkarlen.trollkarl.kollaSömn();
		}
	}
	
	//knappen som påbörjar en strid och byter position EFTER VARJE HANDLING LÄGGS SÖMN TILL
	static class fiendeBtnAct implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			//stridens gränssnitt: labels uppdateras
			trollkarlen.position = "strid";
			stridKlass.fiendeLivLabel.setText("HP: " + stridKlass.fiende.getLiv());
			stridKlass.spelareLivLabel.setText("HP: " + trollkarlen.trollkarl.getLiv());
			stridKlass.spelareMagiLabel.setText("Magi: " + trollkarlen.trollkarl.getMagi());
			stridKlass.fiendeKraftLabel.setText("Kraft: " + stridKlass.fiende.getKraft());
			stridKlass.spelareKraftLabel.setText("Kraft: " + trollkarlen.trollkarl.getKraft());
			
			switch(stridKlass.fiende.getSvaghet())
			{
			case 0:
				stridKlass.fiendeSvaghetLabel.setText("-: inget");
				break;
				
			case 1:
				stridKlass.fiendeSvaghetLabel.setText("-: värme");
				break;
				
			case 2:
				stridKlass.fiendeSvaghetLabel.setText("-: kyla");
				break;
				
			case 3:
				stridKlass.fiendeSvaghetLabel.setText("-: frätande");
				break;
			}
			switch(stridKlass.fiende.getStyrka())
			{
			case 0:
				stridKlass.fiendeStyrkaLabel.setText("+: inget");
				break;
				
			case 1:
				stridKlass.fiendeStyrkaLabel.setText("+: värme");
				break;
				
			case 2:
				stridKlass.fiendeStyrkaLabel.setText("+: kyla");
				break;
				
			case 3:
				stridKlass.fiendeStyrkaLabel.setText("+: frätande");
				break;
			}
			trollkarlen.Byta(trollkarlen.strid);
		}
	}
}

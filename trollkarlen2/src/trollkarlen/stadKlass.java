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
import javax.swing.border.EmptyBorder;

import trollkarlen.trollkarlen.sovBtnAct;

public class stadKlass extends JPanel
{
	//skapar variabler och komponenter
	static BufferedImage bakgrund;
	static JButton skogBtn = new JButton(), butikBtn = new JButton(), verkstadBtn = new JButton(), avslutaSpelBtn = new JButton(), sovBtn = new JButton();
	//labels för spelarens värden
	static JLabel livLabel = new JLabel(), kraftLabel = new JLabel(), attackeradAvLabel = new JLabel(), namnLabel = new JLabel(), pengarLabel = new JLabel(), magiLabel = new JLabel(), hyraLabel = new JLabel();
	
	stadKlass()
	{
		//kollar om den första bakgrundsbilden från trollkarlen går att läsa in
		try
		{
			//sätter bilden, kan nu användas för att ritas upp
			bakgrund = ImageIO.read(trollkarlen.bakgrunder[0]);
		}
		catch (Exception e)
		{
			System.out.print("fel0");
		}
		
		//gör så att knappar kan placeras fritt
		this.setLayout(null);
		//lägger till gränssnitt
		this.add(skogBtn);
		this.add(butikBtn);
		this.add(avslutaSpelBtn);
		this.add(verkstadBtn);
		this.add(livLabel);
		this.add(kraftLabel);
		this.add(attackeradAvLabel);
		this.add(namnLabel);
		this.add(pengarLabel);
		this.add(magiLabel);
		this.add(hyraLabel);
		this.add(sovBtn);
		
		//sätter utseende på gränssnitt + actionlisteners
		skogBtn.setBounds((int) Math.round(630 * trollkarlen.widthSize), (int) Math.round(465 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		skogBtn.addActionListener(new skogBtnAct());
		skogBtn.setContentAreaFilled(false);
		skogBtn.setFocusPainted(false);
		skogBtn.setFont(new Font("comic sans ms", Font.BOLD, 25));
		skogBtn.setText("Skog");
		
		butikBtn.setBounds((int) Math.round(1205 * trollkarlen.widthSize), (int) Math.round(405 * trollkarlen.heightSize), (int) Math.round(100 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		butikBtn.addActionListener(new butikBtnAct());
		butikBtn.setContentAreaFilled(false);
		butikBtn.setFocusPainted(false);
		butikBtn.setFont(new Font("comic sans ms", Font.BOLD, 25));
		butikBtn.setText("Butik");
		
		verkstadBtn.setBounds((int) Math.round(55 * trollkarlen.widthSize), (int) Math.round(420 * trollkarlen.heightSize), (int) Math.round(150 * trollkarlen.widthSize), (int) Math.round(50 * trollkarlen.heightSize));
		verkstadBtn.addActionListener(new verkstadBtnAct());
		verkstadBtn.setContentAreaFilled(false);
		verkstadBtn.setFocusPainted(false);
		verkstadBtn.setFont(new Font("comic sans ms", Font.BOLD, 25));
		verkstadBtn.setText("Verkstad");
		
		sovBtn.setBounds((int) Math.round(15 * trollkarlen.widthSize), (int) Math.round(680 * trollkarlen.heightSize), (int) Math.round(97 * trollkarlen.widthSize), (int) Math.round(60 * trollkarlen.heightSize));
		sovBtn.addActionListener(new sovBtnAct());
		sovBtn.setContentAreaFilled(false);
		sovBtn.setFocusPainted(false);
		sovBtn.setFont(new Font("comic sans ms", Font.BOLD, 17));
		sovBtn.setText("Sov");
		//knappen för att sova är osynlig och visas bara när spelaren bör sova
		sovBtn.setVisible(false);
		
		namnLabel.setBounds((int) Math.round(0 * trollkarlen.widthSize), (int) Math.round(0 * trollkarlen.heightSize), (int) Math.round(225 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		namnLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		namnLabel.setOpaque(true);
		namnLabel.setBackground(new Color(155, 155, 155));
		namnLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		
		livLabel.setBounds((int) Math.round(225 * trollkarlen.widthSize), (int) Math.round(0 * trollkarlen.heightSize), (int) Math.round(125 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		livLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		livLabel.setOpaque(true);
		livLabel.setBackground(new Color(155, 155, 155));
		livLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		
		kraftLabel.setBounds((int) Math.round(350 * trollkarlen.widthSize), (int) Math.round(0 * trollkarlen.heightSize), (int) Math.round(125 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		kraftLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		kraftLabel.setOpaque(true);
		kraftLabel.setBackground(new Color(155, 155, 155));
		kraftLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		
		attackeradAvLabel.setBounds((int) Math.round(475 * trollkarlen.widthSize), (int) Math.round(0 * trollkarlen.heightSize), (int) Math.round(250 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		attackeradAvLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		attackeradAvLabel.setOpaque(true);
		attackeradAvLabel.setBackground(new Color(155, 155, 155));
		attackeradAvLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		
		pengarLabel.setBounds((int) Math.round(725 * trollkarlen.widthSize), (int) Math.round(0 * trollkarlen.heightSize), (int) Math.round(200 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		pengarLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		pengarLabel.setOpaque(true);
		pengarLabel.setBackground(new Color(155, 155, 155));
		pengarLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		
		magiLabel.setBounds((int) Math.round(925 * trollkarlen.widthSize), (int) Math.round(0 * trollkarlen.heightSize), (int) Math.round(200 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		magiLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		magiLabel.setOpaque(true);
		magiLabel.setBackground(new Color(155, 155, 155));
		magiLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
		
		hyraLabel.setBounds((int) Math.round(1125 * trollkarlen.widthSize), (int) Math.round(0 * trollkarlen.heightSize), (int) Math.round(250 * trollkarlen.widthSize), (int) Math.round(40 * trollkarlen.heightSize));
		hyraLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		hyraLabel.setOpaque(true);
		hyraLabel.setBackground(new Color(155, 155, 155));
		hyraLabel.setFont(new Font("comic sans ms", Font.BOLD, 20));
	}
	
	public void paintComponent(Graphics g)
	{
		//fixar bakgrundens dimensioner och sätter ut den
		g.drawImage(bakgrund, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	//knappen som byter position till skog EFTER VARJE HANDLING LÄGGS SÖMN TILL
	static class skogBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			trollkarlen.position = "skog";
			trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() - 10);
			trollkarlen.Byta(trollkarlen.skog);
			trollkarlen.trollkarl.kollaSömn();
		}
	}
	
	//knappen som byter position till butik EFTER VARJE HANDLING LÄGGS SÖMN TILL
	static class butikBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			trollkarlen.position = "butik";
			trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() - 5);
			trollkarlen.Byta(trollkarlen.butik);
			trollkarlen.trollkarl.kollaSömn();
		}
	}
	
	//knappen som byter position till verkstad (om man inte förlorat den) EFTER VARJE HANDLING LÄGGS SÖMN TILL
	static class verkstadBtnAct implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(verkstadKlass.verkstadKvar)
			{
				trollkarlen.position = "verkstad";
				trollkarlen.trollkarl.setSömn(trollkarlen.trollkarl.getSömn() - 5);
				trollkarlen.Byta(trollkarlen.verkstad);
				trollkarlen.trollkarl.kollaSömn();
			}
			else
			{
				//om man har förlorat verkstaden kan man köpa tillbaka den, det kommer finnas ett pris på knappen istället för "Verkstad"
				if(trollkarlen.trollkarl.getPengar() >= verkstadKlass.köpVerkstad)
				{
					trollkarlen.trollkarl.setPengar(trollkarlen.trollkarl.getPengar() - verkstadKlass.köpVerkstad);
					verkstadKlass.verkstadKvar = true;
					//daglig hyra blir ursprungshyran * antal dagar i spelet
					verkstadKlass.hyra = trollkarlen.dag * 10;
					verkstadBtn.setFont(new Font("comic sans ms", Font.BOLD, 25));
					//sätter tillbaka texten på knappen
					verkstadBtn.setText("Verkstad");
					uppdateraStats();
				}
				else
				{
					//ERROR
					//"du har för lite pengar och kan inte köpa tillbaka verkstaden"
				}
			}
		}
	}
	//ska finnas i varje klass [senare]
	//uppdateras gränssnittets värden
	static void uppdateraStats()
	{
		livLabel.setText("|Liv: " + Integer.toString(trollkarlen.trollkarl.getLiv()));
		kraftLabel.setText("|Kraft: " + Integer.toString(trollkarlen.trollkarl.getKraft()));
		
		attackeradAvLabel.setText("|Attackerad av: ");
		for(int i = 0; i < trollkarlen.trollkarl.påverkadAv.length; i++)
		{
			if(trollkarlen.trollkarl.påverkadAv[i])
			{
				attackeradAvLabel.setText(attackeradAvLabel.getText() + (i + 1) + " ");
			}
		}
		
		
		pengarLabel.setText("|Pengar: " + Integer.toString(trollkarlen.trollkarl.getPengar()));
		magiLabel.setText("|Magi: " + Integer.toString(trollkarlen.trollkarl.getMagi()));
		hyraLabel.setText("|Dagens hyra: " + verkstadKlass.hyra);
	}
}

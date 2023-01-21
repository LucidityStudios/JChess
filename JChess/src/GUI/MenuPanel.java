package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements ActionListener
{
	private JButton nGame, lGame;
	
	public void paintComponent(Graphics g)
	{
		//adds the background image
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		File boardImgFile = new File("res/MainMenu.png");
		BufferedImage boardImg = null;
		try
		{
		boardImg = ImageIO.read(boardImgFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		};
		
		g2.drawImage(boardImg, 0, 0, null);
	}
	
	public MenuPanel()
	{
		setPreferredSize(new Dimension(400, 400));
		nGame = new JButton("New Game");
	//	lGame = new JButton("Load Game");
		nGame.addActionListener(this);
	//	lGame.addActionListener(this);
		add(nGame);
	//	add(lGame);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == nGame)
		{
	//		Run.cl.show(Run.mainpanel, "1");
			Run.cl.show(Run.mainpanel, "3");
		}
//		if(e.getSource() == lGame)
		{
//			Run.cl.show(Run.mainpanel, "2");
		}
	}
}

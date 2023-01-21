package GUI;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Run
{
	public static JPanel mainpanel = new JPanel();
	public static CardLayout cl = new CardLayout();
	
	MenuPanel menu = new MenuPanel();
	StartPanel start = new StartPanel();
	LoadPanel load = new LoadPanel();
	PausePanel pause = new PausePanel();
	BoardPanel board = new BoardPanel();
	
	public Run()
	{
		mainpanel.setLayout(cl);
		
		mainpanel.add(menu, "0");
		mainpanel.add(start, "1");
		mainpanel.add(load, "2");
		mainpanel.add(board, "3");
		mainpanel.add(pause, "4");
		
		
		cl.show(mainpanel, "0");
		
		//makes the frame
		JFrame frame = new JFrame("JChess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = frame.getContentPane();
		c.add(mainpanel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public static void main(String[]args)
	{	
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{				
				new Run();
			}
		});
	}
	
}

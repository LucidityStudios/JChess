package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements ActionListener
{
	private JButton mg, mgp;
	
	public StartPanel()
	{
		setPreferredSize(new Dimension(400, 400));
		mg = new JButton("Hot Seat");
		mgp = new JButton("Hot Seat with Passwords");
		mg.addActionListener(this);
		mgp.addActionListener(this);
		add(mg);
		add(mgp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

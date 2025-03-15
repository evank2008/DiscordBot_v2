package org.jointheleague;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class QuitButton {

	JPanel panel;
	JFrame frame;
	JButton button;
	
	public QuitButton() {
		frame = new JFrame("OnionBot Control Panel");
		panel = new JPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);
		button = new JButton("quit");
		button.setPreferredSize(new Dimension(600,400));
		frame.setVisible(true);
		button.setOpaque(true);
		button.setBackground(new Color(255,0,0));
		button.addActionListener((e)->{
			System.exit(0);
		});
		panel.add(button);
		frame.pack();
	}
	
}

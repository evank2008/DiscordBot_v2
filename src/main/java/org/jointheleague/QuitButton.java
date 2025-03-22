package org.jointheleague;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class QuitButton implements KeyListener {
	TextChannel channel;
	JPanel panel;
	JFrame frame;
	JButton button;
	JButton dirButton;
	JTextField field;
	JLabel label;
	
	
	public QuitButton(TextChannel channel) {
		field=new JTextField(20);
		this.channel=channel;
		frame = new JFrame("OnionBot Control Panel in "+channel.getName());
		panel = new JPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);
		button = new JButton("quit");
		dirButton = new JButton("show directory");
		label = new JLabel("Send message:");
		//button.setPreferredSize(new Dimension(600,400));
		frame.setVisible(true);
		button.setOpaque(true);
		button.setBackground(new Color(255,0,0));
		button.addActionListener((e)->{
			System.exit(0);
		});
		dirButton.addActionListener((e)->{
			channel.sendMessage(System.getProperty("user.dir")).submit().join();
		});
		field.addKeyListener(this);
		panel.add(button);
		panel.add(dirButton);
		panel.add(label);
		panel.add(field);
		frame.pack();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==e.VK_ENTER) {
			if(!field.getText().equals(null)) {
				channel.sendMessage(field.getText()).submit().join();
				}
			field.setText("");
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

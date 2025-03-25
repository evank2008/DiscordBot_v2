package org.jointheleague;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.utils.FileUpload;

public class QuitButton implements KeyListener {
	TextChannel channel;
	JPanel panel;
	JFrame frame;
	JButton button;
	JButton dirButton;
	JButton fileButton;
	JTextField field;
	JLabel label;
	JFileChooser jfc;
	FileUpload fu;
	
	public QuitButton(TextChannel channel) {
		field=new JTextField(20);
		this.channel=channel;
		frame = new JFrame("OnionBot Control Panel in "+channel.getName());
		panel = new JPanel();
		jfc = new JFileChooser();
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
		fileButton = new JButton("upload file");
		
		fileButton.addActionListener((e)->{
		 if (jfc.showOpenDialog(fileButton) == JFileChooser.APPROVE_OPTION) {
		            File file = jfc.getSelectedFile();
		            if(file.length()<100000000)
		            fu=FileUpload.fromData(file);
		            channel.sendFiles(fu).submit().join();
		 }
		});
		field.addKeyListener(this);
		panel.add(button);
		panel.add(dirButton);
		panel.add(fileButton);
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

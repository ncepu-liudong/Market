package market;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.table.JTableHeader;


import java.sql.*;


@SuppressWarnings("serial")
public class biaohaocha extends JFrame{
	JLabel password=new JLabel("��������:");
	JTextField pa=new JTextField(5);
	JButton lognin=new JButton("��ѯ");
	JButton quit=new JButton("ȡ��");
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
 JScrollPane scpDemo;
 JTableHeader jth;
 JTable tabDemo;
 JLabel btnShow;
 Panel p0=new Panel();
	public biaohaocha() {
		
		
		JFrame f = new JFrame("��Ʒ��ѯ");
		f.setBounds(250, 100,800,500);
		
		int r = 135;
        int g = 206;
        int b = 235;
        Color bgColor = new Color(r, g, b);
		f.getContentPane().setBackground(bgColor);
    	
    	Panel p0=new Panel();
    	
    	 p0.add(password).setBounds(50, 40, 80, 30);
    	 p0.add(pa).setBounds(200,40,80,30);
    	
    	
    	 p0.add(lognin).setBounds(400, 40, 80, 30);
    	 
    	 p0.add(quit).setBounds(550, 40, 80, 30);
    
    	
       
    	 p0. setVisible(true);
    	 f.add(p0, BorderLayout.NORTH);
    	 f.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	 class MyActionListener implements ActionListener{
  		public void actionPerformed(ActionEvent e){
  			pa.setText("");
  			
  		}
  		}
	 class MyActionListener1 implements ActionListener{
	  		public void actionPerformed(ActionEvent e){
	  			new j( pa.getText());
	  		}
	 }
    quit.addActionListener(new MyActionListener());
    lognin.addActionListener(new MyActionListener1());}
	
	 
	
	
}


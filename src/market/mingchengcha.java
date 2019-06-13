package market;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class mingchengcha extends JFrame{
	JLabel password=new JLabel("请输入商品名:");
	JTextField pa=new JTextField(5);
	JButton lognin=new JButton("查询");
	JButton quit=new JButton("取消");
	public mingchengcha() {
		
		
		JFrame f = new JFrame("商品查询");
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
	  			new cha( pa.getText());
	  		}
	 }
        quit.addActionListener(new MyActionListener());
        lognin.addActionListener(new MyActionListener1());}
	}
	 



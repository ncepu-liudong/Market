package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.net.URL;
import java.util.Date;
public class MyFrame extends JFrame{

	public MyFrame() {
		Container container=getContentPane();
		container.setLayout(null);
		Toolkit theKit=this.getToolkit();
		Dimension screenSize=theKit.getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		this.setSize(800, 600);
		this.setLocation(centerX-400, centerY-350);
		
	}

}

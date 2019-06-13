package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTabbedPane;
import java.net.URL;
import java.util.Date;
public class Error extends JDialog{
      JLabel label;
	public Error(String error) {
		super(new MyFrame(),"¥ÌŒÛÃ· æ");
		Container container=getContentPane();
		this.setLayout(new BorderLayout());
		label=new JLabel(error,JLabel.CENTER);
		
		container.add(label);
		Toolkit theKit=this.getToolkit();
		Dimension screenSize=theKit.getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		this.setSize(400, 200);
		this.setLocation(centerX-200, centerY-100);
		
	}

}

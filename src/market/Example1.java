package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.net.URL;
import java.util.Date;
import java.awt.Window;
public class Example1 extends JFrame {

	 String[] border1= {BorderLayout.CENTER,BorderLayout.NORTH,BorderLayout.SOUTH,BorderLayout.WEST,BorderLayout.EAST};
	 String[]buttonName= {"center button","north button","south button","west button","east button"};
	   private JTabbedPane tabbedPane;  
	    private JLabel label1,label2,label3,label4,label5;  
	    private JPanel panel1,panel2,panel3,panel4; 
	    static String id;
	public  void creatWindow() {
		JFrame Window=new JFrame("���й���ϵͳ");
		Toolkit theKit=Window.getToolkit();
		Dimension screenSize=theKit.getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		Window.setSize(1000, 700);
		Window.setLocation(centerX-500, centerY-375);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout border=new BorderLayout();
		tabbedPane=new JTabbedPane();   //����ѡ�������  
        //������ǩ  
        label1=new JLabel("ϵͳ����Ա�ѵ�¼");  
        label2=new JLabel("�û���"+id); 
       
        Date date=new Date();
        String s=String.format("%tF", date);
        label3=new JLabel("ʱ�䣺"+s);  
        //�������  
        panel1=new JPanel(new BorderLayout());  
        panel2=new JPanel(new BorderLayout());  
        panel3=new JPanel(new BorderLayout()); 
        panel4=new JPanel(new BorderLayout()); 
        JToolBar toolBar = new JToolBar("���Թ�����");
        toolBar.setFloatable(false);
        JToolBar toolBar1 = new JToolBar("���Թ�����");
        toolBar1.setFloatable(false);
        JToolBar toolBar3 = new JToolBar("���Թ�����");
        toolBar3.setFloatable(false);
        JToolBar toolBar4 = new JToolBar("���Թ�����");
        toolBar4.setFloatable(false);
        // ���� ��������ť
        label4=new JLabel("",JLabel.CENTER); 
        URL url=Example1.class.getResource("D:\\Users\\����\\eclipse-workspace\\Market\\3.jpg");
        Icon icon=new ImageIcon("D:\\Users\\����\\eclipse-workspace\\Market\\3.jpg");
        //���ñ�ǩ
        label4.setIcon(icon);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setOpaque(true);
        //���ð�ť
        JButton help = new JButton("����");
        help.setMaximumSize(new Dimension(70,50));
        help.setToolTipText("�鿴�����ĵ�");
        help.addActionListener(new java.awt.event.ActionListener() {
    	    public void actionPerformed(java.awt.event.ActionEvent e) {
    	    	try { 
    	    	    Process process = Runtime.getRuntime().exec(
    	    	    "cmd.exe  /c start D:\\Users\\����\\eclipse-workspace\\Market\\help2.pdf");
    	    	} catch (Exception e2) {
    	    	e2.printStackTrace();
    	    	}
    	    }
    	   });
        JButton exit = new JButton("�˳�");
        exit.setMaximumSize(new Dimension(70,50));
        exit.setToolTipText("�˳���ǰϵͳ");
        exit.addActionListener(new java.awt.event.ActionListener() {
        	    public void actionPerformed(java.awt.event.ActionEvent e) {
        	    	int option=JOptionPane.showConfirmDialog(null, "ȷ���˳���", "����",JOptionPane.YES_OPTION);
				      if(option==JOptionPane.YES_OPTION) 
				      {
				    	  Window.dispose();
				      }
				      else {
				    	  return;
				      }
        	    }
        	   });
        toolBar4.add(help);
        toolBar4.add(exit);
        JButton previousBtn = new JButton("������ѯ");
        previousBtn.setMaximumSize(new Dimension(70,50));
        previousBtn.setToolTipText("�鿴ĳһ����Ա����Ϣ");
        previousBtn.addActionListener(new previousBtn());
        JButton pauseBtn = new JButton("ȫ���ѯ");//��ѯȫ���Ա����Ϣ
        pauseBtn.setMaximumSize(new Dimension(70,50));
        pauseBtn.addActionListener(new pauseBtnAction());
        pauseBtn.setToolTipText("�鿴���еĻ�Ա����Ϣ");
        JButton nextBtn = new JButton("��ӻ�Ա");
        nextBtn.setMaximumSize(new Dimension(70,50));
        nextBtn.setToolTipText("����µĻ�Ա��Ϣ");
        nextBtn.addActionListener(new nextBtnAction());
        toolBar.add(previousBtn);
        toolBar.add(pauseBtn);
        toolBar.add(nextBtn);
        toolBar.addSeparator(new Dimension(1000,0));
        //�û���ť
        JButton addUser = new JButton("����û�");
        addUser.addActionListener(new addUser());
        addUser.setMaximumSize(new Dimension(70,50));
        addUser.setToolTipText("����һ�����û�");
        JButton selectUser = new JButton("��ѯ�û�");
        selectUser.addActionListener(new selectUser());
        selectUser.setMaximumSize(new Dimension(70,50));
        selectUser.setToolTipText("�鿴�����û�");
        toolBar1.add(addUser);
        toolBar1.add(selectUser);
        //���ݴ���
        JButton salesRecord = new JButton("���ۼ�¼");
        salesRecord.addActionListener(new salesRecord());
        salesRecord.setMaximumSize(new Dimension(70,50));
        salesRecord.setToolTipText("�������ۼ�¼");
        JButton meberSalesRecord = new JButton("��Ա��ϸ");
        meberSalesRecord.setMaximumSize(new Dimension(70,50));
        meberSalesRecord.addActionListener(new meberSalesRecord());
        System.out.println(id);
        
        meberSalesRecord.setToolTipText("�����Ա��ϸ");
        toolBar3.add(salesRecord);
        toolBar3.add(meberSalesRecord);
        panel1.add(label1);  
        panel3.add(label2); 
       
        panel3.add(label3);  
        panel1.setBackground(Color.DARK_GRAY);  
        panel2.setBackground(Color.DARK_GRAY);  
        panel3.setBackground(Color.DARK_GRAY); 
        panel4.setBackground(Color.DARK_GRAY); 
        //����ǩ�����뵽ѡ���������  
        tabbedPane.addTab("��Ա����",null,panel1,"First panel");  
        tabbedPane.addTab("�û�����",null,panel2,"Second panel");  
        tabbedPane.addTab("���ݴ���",null,panel3,"Third panel"); 
        tabbedPane.addTab("�������˳�",null,panel4,"fourth panel"); 
        panel1.add(toolBar);
        panel2.add(toolBar1);
        panel3.add(toolBar3);
        panel4.add(toolBar4);
		Container c=Window.getContentPane();
		c.setLayout(border);
		c.add(border1[1],tabbedPane);
		JPanel panelCenter=new JPanel();
		panelCenter.setLayout(new FlowLayout());
		panelCenter.add("Center", label4);
		c.add(border1[0],panelCenter);
		JPanel panelBottom=new JPanel();
		c.add(border1[2],panelBottom);
		panelBottom.setLayout(new GridLayout(1,3,1,5));
		panelBottom.add(label1);
		panelBottom.add(label2);
	
		panelBottom.add(label3);
		Window.setVisible(true);
		
	
	}
	class previousBtn implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			new selectOne().setVisible(true);
		}
	}
	class salesRecord implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			new salerecord1().setVisible(true);
		}
	}
	class selectUser implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			new SelectUser().setVisible(true);
		}
	}
	class addUser implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			new Adduser().setVisible(true);
		}
	}
	class pauseBtnAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			new SelectAll().setVisible(true);
		}
		
	}
	class nextBtnAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			new AddOneMember().setVisible(true);
		}
	}
	class meberSalesRecord implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			new Memberdetail().setVisible(true);
		}
	}
	
  

}


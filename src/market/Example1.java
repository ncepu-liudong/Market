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
		JFrame Window=new JFrame("超市管理系统");
		Toolkit theKit=Window.getToolkit();
		Dimension screenSize=theKit.getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		Window.setSize(1000, 700);
		Window.setLocation(centerX-500, centerY-375);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout border=new BorderLayout();
		tabbedPane=new JTabbedPane();   //创建选项卡面板对象  
        //创建标签  
        label1=new JLabel("系统管理员已登录");  
        label2=new JLabel("用户："+id); 
       
        Date date=new Date();
        String s=String.format("%tF", date);
        label3=new JLabel("时间："+s);  
        //创建面板  
        panel1=new JPanel(new BorderLayout());  
        panel2=new JPanel(new BorderLayout());  
        panel3=new JPanel(new BorderLayout()); 
        panel4=new JPanel(new BorderLayout()); 
        JToolBar toolBar = new JToolBar("测试工具栏");
        toolBar.setFloatable(false);
        JToolBar toolBar1 = new JToolBar("测试工具栏");
        toolBar1.setFloatable(false);
        JToolBar toolBar3 = new JToolBar("测试工具栏");
        toolBar3.setFloatable(false);
        JToolBar toolBar4 = new JToolBar("测试工具栏");
        toolBar4.setFloatable(false);
        // 创建 工具栏按钮
        label4=new JLabel("",JLabel.CENTER); 
        URL url=Example1.class.getResource("D:\\Users\\刘冬\\eclipse-workspace\\Market\\3.jpg");
        Icon icon=new ImageIcon("D:\\Users\\刘冬\\eclipse-workspace\\Market\\3.jpg");
        //设置标签
        label4.setIcon(icon);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setOpaque(true);
        //设置按钮
        JButton help = new JButton("帮助");
        help.setMaximumSize(new Dimension(70,50));
        help.setToolTipText("查看帮助文档");
        help.addActionListener(new java.awt.event.ActionListener() {
    	    public void actionPerformed(java.awt.event.ActionEvent e) {
    	    	try { 
    	    	    Process process = Runtime.getRuntime().exec(
    	    	    "cmd.exe  /c start D:\\Users\\刘冬\\eclipse-workspace\\Market\\help2.pdf");
    	    	} catch (Exception e2) {
    	    	e2.printStackTrace();
    	    	}
    	    }
    	   });
        JButton exit = new JButton("退出");
        exit.setMaximumSize(new Dimension(70,50));
        exit.setToolTipText("退出当前系统");
        exit.addActionListener(new java.awt.event.ActionListener() {
        	    public void actionPerformed(java.awt.event.ActionEvent e) {
        	    	int option=JOptionPane.showConfirmDialog(null, "确认退出？", "警告",JOptionPane.YES_OPTION);
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
        JButton previousBtn = new JButton("单个查询");
        previousBtn.setMaximumSize(new Dimension(70,50));
        previousBtn.setToolTipText("查看某一个会员的信息");
        previousBtn.addActionListener(new previousBtn());
        JButton pauseBtn = new JButton("全体查询");//查询全体会员的信息
        pauseBtn.setMaximumSize(new Dimension(70,50));
        pauseBtn.addActionListener(new pauseBtnAction());
        pauseBtn.setToolTipText("查看所有的会员的信息");
        JButton nextBtn = new JButton("添加会员");
        nextBtn.setMaximumSize(new Dimension(70,50));
        nextBtn.setToolTipText("添加新的会员信息");
        nextBtn.addActionListener(new nextBtnAction());
        toolBar.add(previousBtn);
        toolBar.add(pauseBtn);
        toolBar.add(nextBtn);
        toolBar.addSeparator(new Dimension(1000,0));
        //用户按钮
        JButton addUser = new JButton("添加用户");
        addUser.addActionListener(new addUser());
        addUser.setMaximumSize(new Dimension(70,50));
        addUser.setToolTipText("创建一个新用户");
        JButton selectUser = new JButton("查询用户");
        selectUser.addActionListener(new selectUser());
        selectUser.setMaximumSize(new Dimension(70,50));
        selectUser.setToolTipText("查看所有用户");
        toolBar1.add(addUser);
        toolBar1.add(selectUser);
        //数据处理
        JButton salesRecord = new JButton("销售记录");
        salesRecord.addActionListener(new salesRecord());
        salesRecord.setMaximumSize(new Dimension(70,50));
        salesRecord.setToolTipText("清理销售记录");
        JButton meberSalesRecord = new JButton("会员明细");
        meberSalesRecord.setMaximumSize(new Dimension(70,50));
        meberSalesRecord.addActionListener(new meberSalesRecord());
        System.out.println(id);
        
        meberSalesRecord.setToolTipText("清理会员明细");
        toolBar3.add(salesRecord);
        toolBar3.add(meberSalesRecord);
        panel1.add(label1);  
        panel3.add(label2); 
       
        panel3.add(label3);  
        panel1.setBackground(Color.DARK_GRAY);  
        panel2.setBackground(Color.DARK_GRAY);  
        panel3.setBackground(Color.DARK_GRAY); 
        panel4.setBackground(Color.DARK_GRAY); 
        //将标签面板加入到选项卡面板对象上  
        tabbedPane.addTab("会员管理",null,panel1,"First panel");  
        tabbedPane.addTab("用户管理",null,panel2,"Second panel");  
        tabbedPane.addTab("数据处理",null,panel3,"Third panel"); 
        tabbedPane.addTab("帮助与退出",null,panel4,"fourth panel"); 
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


package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
public class AddOneMember extends JDialog{
	String[] border1= {BorderLayout.CENTER,BorderLayout.NORTH,BorderLayout.SOUTH,BorderLayout.WEST,BorderLayout.EAST};
	JLabel jlid;
	JLabel jlname;
	JLabel jlgrade;
	JLabel jllevel;
	JLabel jlphone;
	private JTextField aTextField;
	private JTextField bTextField;
	private JTextField cTextField;
	private JTextField dTextField;
	private JTextField eTextField;
	JButton addButton=new JButton("添加");
	JLabel jlevel=new JLabel("请选择添加会员类型：");
	JComboBox jc=new JComboBox(new MemberType());
	Connection con;
	PreparedStatement sql;
	Statement sql1;
	ResultSet res;
	ResultSet res1;
	public AddOneMember() {
		super(new MyFrame(),"添加会员");
		jc.setToolTipText("请输入");
		Container container=getContentPane();
		this.setLayout(null);
		jlid=new JLabel("会员id：");
		jlname=new JLabel("会员姓名：");
		container.add(jlid);
		container.add(jlname);
		jlid.setBounds(100,50, 50, 50);
		jlname.setBounds(90,100, 100, 50);
		aTextField=new JTextField();
		aTextField.setEditable(false);
		int a=Integer.parseInt(getId())+1;
		String f=Integer.toString(a);
		aTextField.setText(f);
		container.add(aTextField);
		aTextField.setBounds(160, 60, 150,30 );
		bTextField=new JTextField();
		bTextField.setToolTipText("请输入会员姓名");
		container.add(bTextField);
		bTextField.setBounds(160, 110, 150,30 );
		jlgrade=new JLabel("会员积分：");
		container.add(jlgrade);
		jlgrade.setBounds(90,150, 100, 50);
		cTextField=new JTextField();
		cTextField.setToolTipText("亲输入会员积分");
		container.add(cTextField);
		cTextField.setBounds(160, 160, 150,30);
		container.add(jc);
		jc.setBounds(160, 210, 150,30);
		container.add(jlevel);
		jlevel.setBounds(30,200, 130, 50);
		jlphone=new JLabel("电话号码：");
		container.add(jlphone);
		jlphone.setBounds(95,250, 100, 50);
		eTextField=new JTextField();
		container.add(eTextField);
		eTextField.setToolTipText("请输入电话号码");
		eTextField.setBounds(160, 260, 150,30);
		container.add(addButton);
		addButton.setBounds(160, 310, 60,30);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { String regex1="[a-zA-Z]{4,8}";
			 String regex2="\\d{1,4}";
			String regex3="\\d{11}";
			String regex="\\d{4}";
			
			 if(bTextField.getText().matches(regex1)) {
				 if(cTextField.getText().matches(regex2)) {
					 if(eTextField.getText().matches(regex3)) {
						 String m=(String)jc.getSelectedItem();
						 String n;
						 if(m.equals("普通会员")) {
							 n="1";
						 }
						 else
						 {
							 n="2";
						 }
						 insert(aTextField.getText(),bTextField.getText(),cTextField.getText(),n,eTextField.getText());
						 String messege="添加会员成功";
						 new Error(messege).setVisible(true);
						 aTextField.setText("");
						 bTextField.setText("");
						 cTextField.setText("");
						 eTextField.setText("");
						 
					 }
					 else
					 {
						 String error="输入有误，积分必须11位数字";
							new Error(error).setVisible(true);
					 }
				 }
				 else {
					 String error="输入有误，积分必须时四位以内的数字";
						new Error(error).setVisible(true);
				 }
			 }
			 else {
				 String error="输入有误，请输入四到八位字母";
					new Error(error).setVisible(true);
			 }
			
			 int a=Integer.parseInt(getId())+1;
				String f=Integer.toString(a);
				aTextField.setText(f);
				
			}
		});
		Toolkit theKit=this.getToolkit();
		Dimension screenSize=theKit.getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		System.out.println(centerX);
		System.out.println(centerY);
		this.setSize(500, 400);
		this.setLocation(centerX-250, centerY-300);
	}
	public void insert(String hyid,String hyname,String hygrade,String hylevel,String hynumber) {
		Database selectData=new Database();
		con=selectData.getConnection();
		try {
			sql=con.prepareStatement("insert into hyxx( hyid,hyname,hygrade,hylevel,phonenum) values(?,?,?,?,?)");
			sql.setString(1, hyid);
			sql.setString(2, hyname);
			sql.setString(3, hygrade);
			sql.setString(4, hylevel);
			sql.setString(5, hynumber);
			sql.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String getId() {
		Database selectData=new Database();
		con=selectData.getConnection();
		String m=null;
		try {
			sql1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			res=sql1.executeQuery("select hyid from  (select * from hyxx order by hyid desc) where rownum=1");
			while(res.next()) {
					m=res.getString("hyid");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
          return m;
	}

}

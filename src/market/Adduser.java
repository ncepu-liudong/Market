package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.event.*;
import javax.swing.event.ChangeListener;

import market.Error;

import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;
public class Adduser extends JDialog{
	JLabel jlid;
	JLabel jlname;
	JLabel jlsecret;
	JLabel jlqx;
	JLabel jlbz;
	private JTextField aTextField;
	private JTextField bTextField;
	private JTextField cTextField;
	private JTextArea eTextField;
	JButton addButton=new JButton("添加");
	JLabel jlevel=new JLabel("请选择添加用户类型：");
	JComboBox jc=new JComboBox(new Usertype());
	Connection con;
	PreparedStatement sql;
	ResultSet res;
	boolean bl=true;
	public Adduser() {
		super(new MyFrame(),"添加用户");
		jc.setToolTipText("请输入");
		Container container=getContentPane();
		this.setLayout(null);
		jlid=new JLabel("用户id：");
		jlname=new JLabel("用户姓名：");
		container.add(jlid);
		container.add(jlname);
		jlid.setBounds(100,50, 50, 50);
		jlname.setBounds(90,100, 100, 50);
		aTextField=new JTextField();
		aTextField.setToolTipText("请输入用户号");
		container.add(aTextField);
		aTextField.setBounds(160, 60, 150,30 );
		bTextField=new JTextField();
		bTextField.setToolTipText("请输入用户姓名");
		container.add(bTextField);
		bTextField.setBounds(160, 110, 150,30 );
		jlsecret=new JLabel("用户密码：");
		container.add(jlsecret);
		jlsecret.setBounds(90,150, 100, 50);
		cTextField=new JTextField();
		cTextField.setToolTipText("请输入用户密码");
		container.add(cTextField);
		cTextField.setBounds(160, 160, 150,30);
		container.add(jc);
		jc.setBounds(160, 210, 150,30);
		container.add(jlevel);
		jlevel.setBounds(30,200, 130, 50);
		jlbz=new JLabel("备注：");
		container.add(jlbz);
		jlbz.setBounds(95,250, 100, 50);
		eTextField=new JTextArea();
		JScrollPane sp=new JScrollPane(eTextField);
		container.add(sp);
		eTextField.setToolTipText("请输入备注");
		sp.setBounds(160, 260, 150,50);
		container.add(addButton);
		addButton.setBounds(160, 340, 60,30);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String regex1="[a-zA-Z]{4,8}";
			 String regex2="[a-zA-Z[0-9]]{6,8}";
			String regex3="\\d{11}";
			String regex="\\d{4}";
			if(aTextField.getText().matches(regex)) {
				if(checkone(aTextField.getText())) {
				
				
			 if(bTextField.getText().matches(regex1)) {
				 if(cTextField.getText().matches(regex2)) {
					
						 String m=(String)jc.getSelectedItem();
						 String n;
						 if(m.equals("收银员")) {
							 n="1";
						 }
						 else if(m.equals("入货员"))
						 {
							 n="2";
						 }
						 else
						 {
							 n="3";
						 }
						 SetSecret ss=new SetSecret();
						 String enc = ss.encode(cTextField.getText());  
						 insert(aTextField.getText(),bTextField.getText(),enc,n,eTextField.getText());
						 String messege="添加用户成功";
						 new Error(messege).setVisible(true);
						 aTextField.setText("");
						 bTextField.setText("");
						 cTextField.setText("");
						 eTextField.setText("");
						 
					
				 }
				 else {
					 String error="用户密码输入有误，必须是6到8位的数字";
						new Error(error).setVisible(true);
				 }
			 }
			 else {
				 String error="用户名输入有误，请输入四到八位字母";
					new Error(error).setVisible(true);
			 }
				}
				else {
					 String error="该用户已存在，请重新输入";
						new Error(error).setVisible(true);
				}
			 
			 
			 
			 }
			else {
				String error="用户id号输入有误，请输入四位数字";
				new Error(error).setVisible(true);
			}
			
				
			}
		});
		Toolkit theKit=this.getToolkit();
		Dimension screenSize=theKit.getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		System.out.println(centerX);
		System.out.println(centerY);
		this.setSize(500, 450);
		this.setLocation(centerX-250, centerY-300);
	}
	public void insert(String yhid,String yhname,String yhsecret,String yhqx,String bz) {
		Database selectData=new Database();
		con=selectData.getConnection();
		try {
			sql=con.prepareStatement("insert into yhgl( yhid,yhname,yhsecret,yhqx,bz) values(?,?,?,?,?)");
			sql.setString(1, yhid);
			sql.setString(2, yhname);
			sql.setString(3, yhsecret);
			sql.setString(4, yhqx);
			sql.setString(5, bz);
			sql.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean checkone(String yhid) {
		Database selectData=new Database();
		 
		Vector	rowData=new Vector();
		con=selectData.getConnection();
		try {
			sql=con.prepareStatement("select  yhid from yhgl");
			res=sql.executeQuery();
			while(res.next()) {
				if(yhid.equals(res.getString("yhid"))) {
					bl=false;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bl;
	}

}

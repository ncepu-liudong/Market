package market;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@SuppressWarnings("serial")
public class LognIn extends JFrame  //登录界面
{     
	JLabel name=new JLabel("用 户 名:");
	JLabel password=new JLabel("密    码:");
	JTextField na=new JTextField(10);
	JPasswordField pa=new JPasswordField(10);
	JLabel type=new JLabel("登录方式:");
	JButton lognin=new JButton("登录");
	JButton exit=new JButton("退出");
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox<String> scl=new JComboBox();
	JTextField prompt=new JTextField(20);
	JFrame page = new JFrame();
	
	Connection conn=null;
	
	int[] ok = new int[3];
	String id;
	String key;
@SuppressWarnings("unused")
public void lognin() 
{  
	page.setLayout(null);
	page.setBounds(300, 200, 300, 210);
	page.add(name);
	name.setBounds(20, 15,120, 25);
	name.setFont(new java.awt.Font("新宋体", Font.PLAIN, 25));
	page.add(na);
	na.setBounds(130, 15, 120, 25);
	na.setFont(new java.awt.Font("新宋体", Font.PLAIN, 25));	
	page.add(password);
	password.setBounds(20, 50,120, 25);
	password.setFont(new java.awt.Font("新宋体", Font.PLAIN, 25));
	page.add(pa);
	pa.setBounds(130, 50, 120, 25);
	pa.setFont(new java.awt.Font("新宋体", Font.PLAIN, 25));
	page.add(type);
	type.setBounds(20,85, 140, 25);
	type.setFont(new java.awt.Font("新宋体", Font.PLAIN, 25));
	page.add(scl);
	scl.addItem("选择登录方式");
	scl.addItem("超市收银");
	scl.addItem("库存管理");
	scl.addItem("系统管理");
	scl.setBounds(130, 85, 120, 25);	
	page.add(prompt);
	prompt.setForeground(Color.red);
	prompt.setFont(new java.awt.Font("新宋体", Font.PLAIN, 15));
	//prompt.setText("hello");
	prompt.setBounds(25,115,230,20);
	prompt.setEditable(false);	
	page.add(lognin);
	lognin.setBounds(30, 140,100, 25);
	lognin.setFont(new java.awt.Font("新宋体", Font.PLAIN, 25));
	lognin.addActionListener(new logninActionListener());
	page.add(exit);
	exit.setBounds(140, 140, 100, 25);
	exit.setFont(new java.awt.Font("新宋体", Font.PLAIN, 25));
	exit.addActionListener(new exitActionListener());
	int red = 255;
	int blue = 255;
	int green = 0;
	page.setTitle("超市管理");
	page.getContentPane().setBackground(Color.LIGHT_GRAY);
	page.setVisible(true);
	page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public void test()  //测试输入值是否符合要求
{
	id = na.getText();   //获取输入框内容
	char[] keyy = pa.getPassword();
	key = new String(keyy);
	//System.out.println(key);
	String ty=scl.getSelectedItem().toString();
	//System.out.println(ty);
	char ch;
	if(id.length()==4)   //测试长度
	{
		for(int i=0;i<4;i++)
		{
			ch=id.charAt(i);
			if(ch>='0'&&ch<='9')   //测试是否为数字
			{
				ok[0]=0;
			}
			else
			{
				ok[0]=1;
				break;
			}
		}		
	}
	else
	{
		ok[0]=1;
	}
	if(key.length()>=6&&key.length()<=8)
	{
		int character=0;
		int number=0;
		int els=0;
		char ch2;
		for(int i=0;i<key.length();i++)    //for循环
		{
		   ch2=key.charAt(i);
		   if((ch2>='a'&&ch2 <='z')||(ch2>='A'&&ch2 <='Z'))//统计字母
		       character++; //等同于character=character+1
		   else if(ch2>='0'&& ch2 <='9')                //统计数字
		       number++; //等同于number=number+1;
		   else
			   els++;
		}
		if(character==0||number==0||els>0)
		{
			ok[1]=1;
		}
		else
			ok[1]=0;
	}
	else
	{
		ok[1]=1;
	}
	
	if(ty=="超市收银")
	{
		ok[2]=1;
	}
	else if(ty=="库存管理")
	{
		ok[2]=2;
	}
	else if(ty=="系统管理")
	{
		ok[2]=3;
	}
	else
	{
		ok[2]=0;
	}
}
public void open() 
{
	String url="jdbc:oracle:thin:127.0.0.1:1521:orac";
	String userName="liudong";
	String passWord="5692573";
	try 
	{
		Class.forName("oracle.jdbc.OracleDriver");
		conn=DriverManager.getConnection(url,userName,passWord);
		System.out.println("连接成功");
	}catch(Exception e) 
	{
		e.printStackTrace();
	}
}
public void close() 
{
	try 
	{
		conn.close();
		System.out.println("数据库关闭");
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}
private class logninActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		test();
		//System.out.println(ok[0]+ok[1]+ok[2]);
		if(ok[1]==0&&ok[0]==0&&ok[2]!=0)
		{
			//page.setVisible(false);
			prompt.setText("");
			repaint();
			validate();
			invalidate();
			validate();
			//database 
			try {
				Statement stm=conn.createStatement();
				ResultSet rs=stm.executeQuery("select * from yhgl where yhid="+id+"");
				int nu=0;
				while(rs.next()){
					nu++;
				}
				if(nu==0)
				{
					prompt.setText("无该账号用户，请核对后输入");
					repaint();
					validate();
					invalidate();
					validate();
				}
				else
				{
					ResultSet rn=stm.executeQuery("select * from yhgl where yhid="+id+"");
					rn.next();
					SetSecret ss=new SetSecret();
					String dec = ss.decode(rn.getString("yhsecret").toString());
					if(key.equals(dec.trim()))
					{
						System.out.println(dec);
						if(ok[2]==rn.getInt("yhqx"))
						{
							prompt.setText("well");
							if(ok[2]==1)
							{
								CashierInterface a = new CashierInterface();
								CashierInterface.cash=id;
								a.open();
								a.Start();
								a.TheTop();
								a.TheRight();
								a.TheLeft();
								a.TheBottom();
								a.TheCenter();
								page.setVisible(false);
								
							}
							else if(ok[2]==2)
							{
								new zhu();
								
							}
							else if(ok[2]==3) {
								Example1.id=id;
								new Example1().creatWindow();;
								
							}
							repaint();
							validate();
							invalidate();
							validate();
						}
						else
						{
							prompt.setText("该用户无此权限");
							repaint();
							validate();
							invalidate();
							validate();
						}
					}
					else
					{
						//System.out.println(key);
						//System.out.println(rn.getString("yhsecret").toString());
						prompt.setText("密码错误，请核对后输入");
						repaint();
						validate();
						invalidate();
						validate();
					}
				}
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}

		}
		else if(ok[1]==0&&ok[0]==0&&ok[2]==0)
		{
			//page.setVisible(false);
			prompt.setText("请选择登录方式");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==0&&ok[1]==1&&ok[2]==0)
		{
			//page.setVisible(false);
			prompt.setText("密码格式错误，请选择登录方式");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==0&&ok[1]==1&&ok[2]!=0)
		{
			//page.setVisible(false);
			prompt.setText("密码格式错误");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==1&&ok[1]==0&&ok[2]==0)
		{
			//page.setVisible(false);
			prompt.setText("账号格式错误，请选择登录方式");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==1&&ok[1]==0&&ok[2]!=0)
		{
			//page.setVisible(false);
			prompt.setText("账号格式错误，");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==1&&ok[1]==1&&ok[2]!=0)
		{
			//page.setVisible(false);
			prompt.setText("账号、密码格式错误，");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==1&&ok[1]==1&&ok[2]==0)
		{
			//page.setVisible(false);
			prompt.setText("账号、密码格式错误，请选择登录方式");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
	}
}
private class exitActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		System.exit(0); 
	}
}

  public static void main(String args[])
  {
        LognIn first = new LognIn();
        first.open();
        first.lognin();
        //first.close();
    }
}

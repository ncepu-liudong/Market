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
public class LognIn extends JFrame  //��¼����
{     
	JLabel name=new JLabel("�� �� ��:");
	JLabel password=new JLabel("��    ��:");
	JTextField na=new JTextField(10);
	JPasswordField pa=new JPasswordField(10);
	JLabel type=new JLabel("��¼��ʽ:");
	JButton lognin=new JButton("��¼");
	JButton exit=new JButton("�˳�");
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
	name.setFont(new java.awt.Font("������", Font.PLAIN, 25));
	page.add(na);
	na.setBounds(130, 15, 120, 25);
	na.setFont(new java.awt.Font("������", Font.PLAIN, 25));	
	page.add(password);
	password.setBounds(20, 50,120, 25);
	password.setFont(new java.awt.Font("������", Font.PLAIN, 25));
	page.add(pa);
	pa.setBounds(130, 50, 120, 25);
	pa.setFont(new java.awt.Font("������", Font.PLAIN, 25));
	page.add(type);
	type.setBounds(20,85, 140, 25);
	type.setFont(new java.awt.Font("������", Font.PLAIN, 25));
	page.add(scl);
	scl.addItem("ѡ���¼��ʽ");
	scl.addItem("��������");
	scl.addItem("������");
	scl.addItem("ϵͳ����");
	scl.setBounds(130, 85, 120, 25);	
	page.add(prompt);
	prompt.setForeground(Color.red);
	prompt.setFont(new java.awt.Font("������", Font.PLAIN, 15));
	//prompt.setText("hello");
	prompt.setBounds(25,115,230,20);
	prompt.setEditable(false);	
	page.add(lognin);
	lognin.setBounds(30, 140,100, 25);
	lognin.setFont(new java.awt.Font("������", Font.PLAIN, 25));
	lognin.addActionListener(new logninActionListener());
	page.add(exit);
	exit.setBounds(140, 140, 100, 25);
	exit.setFont(new java.awt.Font("������", Font.PLAIN, 25));
	exit.addActionListener(new exitActionListener());
	int red = 255;
	int blue = 255;
	int green = 0;
	page.setTitle("���й���");
	page.getContentPane().setBackground(Color.LIGHT_GRAY);
	page.setVisible(true);
	page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public void test()  //��������ֵ�Ƿ����Ҫ��
{
	id = na.getText();   //��ȡ���������
	char[] keyy = pa.getPassword();
	key = new String(keyy);
	//System.out.println(key);
	String ty=scl.getSelectedItem().toString();
	//System.out.println(ty);
	char ch;
	if(id.length()==4)   //���Գ���
	{
		for(int i=0;i<4;i++)
		{
			ch=id.charAt(i);
			if(ch>='0'&&ch<='9')   //�����Ƿ�Ϊ����
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
		for(int i=0;i<key.length();i++)    //forѭ��
		{
		   ch2=key.charAt(i);
		   if((ch2>='a'&&ch2 <='z')||(ch2>='A'&&ch2 <='Z'))//ͳ����ĸ
		       character++; //��ͬ��character=character+1
		   else if(ch2>='0'&& ch2 <='9')                //ͳ������
		       number++; //��ͬ��number=number+1;
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
	
	if(ty=="��������")
	{
		ok[2]=1;
	}
	else if(ty=="������")
	{
		ok[2]=2;
	}
	else if(ty=="ϵͳ����")
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
		System.out.println("���ӳɹ�");
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
		System.out.println("���ݿ�ر�");
	} catch (SQLException e) {
		// TODO �Զ����ɵ� catch ��
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
					prompt.setText("�޸��˺��û�����˶Ժ�����");
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
							prompt.setText("���û��޴�Ȩ��");
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
						prompt.setText("���������˶Ժ�����");
						repaint();
						validate();
						invalidate();
						validate();
					}
				}
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}

		}
		else if(ok[1]==0&&ok[0]==0&&ok[2]==0)
		{
			//page.setVisible(false);
			prompt.setText("��ѡ���¼��ʽ");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==0&&ok[1]==1&&ok[2]==0)
		{
			//page.setVisible(false);
			prompt.setText("�����ʽ������ѡ���¼��ʽ");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==0&&ok[1]==1&&ok[2]!=0)
		{
			//page.setVisible(false);
			prompt.setText("�����ʽ����");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==1&&ok[1]==0&&ok[2]==0)
		{
			//page.setVisible(false);
			prompt.setText("�˺Ÿ�ʽ������ѡ���¼��ʽ");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==1&&ok[1]==0&&ok[2]!=0)
		{
			//page.setVisible(false);
			prompt.setText("�˺Ÿ�ʽ����");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==1&&ok[1]==1&&ok[2]!=0)
		{
			//page.setVisible(false);
			prompt.setText("�˺š������ʽ����");
			//lognin();
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if(ok[0]==1&&ok[1]==1&&ok[2]==0)
		{
			//page.setVisible(false);
			prompt.setText("�˺š������ʽ������ѡ���¼��ʽ");
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

package market;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@SuppressWarnings("serial")
public class CashierInterface extends JFrame{
	JLabel title = new JLabel("超市收银系统");
	JLabel productId = new JLabel("商品编号：");
	JLabel cashierNumber = new JLabel("收银员工号：");
	JLabel serialNumber = new JLabel("流水号：");
	JLabel productName = new JLabel("品名");
	JLabel unitPrice = new JLabel("单价");
	JLabel amount = new JLabel("数量");
	JLabel totalPrice = new JLabel("总价");
	JLabel total = new JLabel("合计:");
	JLabel productAmount = new JLabel("商品数量:");
	JLabel cardNumber = new JLabel("会员卡号；");
	JLabel cardName = new JLabel("会员姓名：");
	JLabel memberPoint = new JLabel("会员积分：");
	JLabel receive = new JLabel(" 应 收：");
	JLabel gather = new JLabel(" 收 款：");
	JLabel change = new JLabel(" 找 零：");
	JLabel discount = new JLabel("折扣额度");
	JTextField productIdt = new JTextField(18);
	JTextField cashierNumbert = new JTextField(10);
	JTextField serialNumbert = new JTextField(10);
	JTextField totalt = new JTextField(10);
	JTextField productAmountt = new JTextField(10);
	JTextField cardNumbert = new JTextField(18);
	JTextField cardNamet = new JTextField(10);
	JTextField memberPointt = new JTextField(10);
	JTextField receivet = new JTextField(8);
	JTextField gathert = new JTextField(8);
	JTextField changet = new JTextField(8);
	JTextField discountt = new JTextField(8);
	JButton sure = new JButton("√");  //商品编号确认
	JButton deletep = new JButton("删除商品");  //购买商品的删除
	JButton yes = new JButton("√");  //会员号的确认
	JButton ding = new JButton("√");  //收款的确认
	JButton endPrint = new JButton("结算打印");
	JFrame page = new JFrame();	
	SimpleDateFormat gdate = new SimpleDateFormat("yyyyMMdd"); //时间格式化
	float gtotal = 0;  //初始的总价
	int gproductAmount = 0;  //初始的商品数量
	float grecrive = 0;  //初始的应收额
	float ggather = 0;  //初始的实收额
	float gchange = 0;  //初始的找零额
	Connection conn=null;  //数据库链接
	String vip;  //输入的会员号码
	String product;  //输入的商品号
	String getting;  //输入的收款额
	int ok;  //会员号是否符合格式
	int okk;  //商品编号是否符合格式	
	int okkk;  //付款金额是否符合格式
	JTable table = null;
	DefaultTableModel defaultModel = null;
	String[] name = {"商品编号","商品品名","商品单价","数量","总价"};
	String[][] data = new String[1][5];  //初始的Table
	static String cow1;  //JTable的列
	static String cow2;
	static String cow3;
	static int cow4;
	static String cow5;
	static String cash;
	static double dis=1;  //折扣
	static double sum=0;  //总价
	static double tol=0;  //应收
	static int number=0;  //数量
	static double get=0;  //收到的金额
	static double changing=0;  //找零金额
	static double hygrade;  //会员积分
	static int length=0;  //JTable的行数
	static String p1=null,p2=null;
	static int p;
	static String date;
	static double gnumber;  //流水号的编号部分
//对收银页面进行初始化操作，清空各个窗口，填入规格化的0.
public void Start() 
{
	String gre = new DecimalFormat("0.00").format(grecrive);
	receivet.setText(gre);
	String gga = new DecimalFormat("0.00").format(ggather);
	gathert.setText(gga);
	String gch = new DecimalFormat("0.00").format(gchange);
	changet.setText(gch);
	String gto = new DecimalFormat("0.00").format(gtotal);
	totalt.setText("￥"+gto);
	String gpr = new DecimalFormat("0").format(gproductAmount);
	productAmountt.setText(gpr);
	cashierNumbert.setText(cash);  //收银员号码	
	productIdt.setText("");
	test5();
	String str = new DecimalFormat("0000").format(gnumber); //格式化输出的数字
	serialNumbert.setText(date+str);
	cardNumbert.setText("");
	cardNamet.setText("");
	discountt.setText("");
	memberPointt.setText("");
	dis=1;
	sum=0;
	tol=0;
	number=0;
	get=0;
	changing=0;
	length=0;
	
}

//顶部页面设计，商品编号，删除商品，流水号。
public void TheTop() 
{
	page.setLayout(null);
	JPanel top = new JPanel();
	page.add(top);
	top.setBounds(5, 5, 1335, 50);
	top.setLayout(null);
	top.setBackground(new Color(223, 235, 246));
	top.add(productId);
	productId.setFont(new java.awt.Font("新宋体", Font.PLAIN, 18));
	productId.setBounds(15, 5, 90, 50);
	top.add(productIdt);
	productIdt.setBounds(100, 15, 150,30);
	productIdt.setFont(new java.awt.Font("新宋体", Font.PLAIN, 20));
	productIdt.setHorizontalAlignment(JTextField.RIGHT);
	top.add(sure);
	sure.setBounds(250, 15, 50, 30);
	sure.setBorder(BorderFactory.createLineBorder(new Color(223, 235, 246)));
	sure.setBackground(new Color(223, 235, 246));
	sure.addKeyListener(new KeyAdapter()
	{
		@SuppressWarnings("static-access")
		public void keyPressed(KeyEvent ke)
		{
			if(ke.getKeyChar()==ke.VK_ENTER)
			{
				enter();
			}
		}
	});
	
	sure.addActionListener(new sureActionListener());  //添加商品的监听
	top.add(deletep);
	deletep.setBounds(300, 15, 150, 30);
	deletep.setBorder(BorderFactory.createLineBorder(new Color(200, 145, 246)));
	deletep.setBackground(new Color(223, 235, 246));
	deletep.setFont(new java.awt.Font("新宋体", Font.PLAIN, 15));
	deletep.addActionListener(new deleteActionListener());  //删除商品的监听
	top.add(cashierNumber);
	cashierNumber.setFont(new java.awt.Font("新宋体", Font.PLAIN, 18));
	cashierNumber.setBounds(850, 5, 120, 50);
	top.add(cashierNumbert);
	cashierNumbert.setBounds(950, 15, 100,30);
	cashierNumbert.setBorder(BorderFactory.createLineBorder(new Color(223, 235, 246)));
	cashierNumbert.setBackground(new Color(223, 235, 246));
	cashierNumbert.setFont(new java.awt.Font("新宋体", Font.PLAIN, 20));
	//cashierNumbert.setHorizontalAlignment(JTextField.RIGHT);
	cashierNumbert.setEditable(false);
	top.add(serialNumber);
	serialNumber.setFont(new java.awt.Font("新宋体", Font.PLAIN, 18));
	serialNumber.setBounds(1110, 5, 80, 50);
	top.add(serialNumbert);
	serialNumbert.setBounds(1170, 15, 150,30);
	serialNumbert.setBorder(BorderFactory.createLineBorder(new Color(223, 235, 246)));
	serialNumbert.setBackground(new Color(223, 235, 246));
	serialNumbert.setFont(new java.awt.Font("新宋体", Font.BOLD, 20));
	serialNumbert.setHorizontalAlignment(JTextField.RIGHT); //右对齐
	serialNumbert.setEditable(false);
	page.setTitle("超市管理系统");
	page.setVisible(true);
	page.setBounds(10, 10, 1360, 700);
	page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

//右侧界面设计，收银界面，应收，收款，找零。
public void TheRight() 
{
	page.setLayout(null);
	JPanel right = new JPanel();
	page.add(right);
	right.setBounds(1040, 450, 300, 210);
	right.setBackground(new Color(223, 235, 246));
	//right.setBorder(BorderFactory.createLineBorder(Color.white));
	right.setLayout(null);
	right.add(receive);
	receive.setFont(new java.awt.Font("新宋体", Font.BOLD, 30));
	receive.setBounds(10,10,150,50);
	right.add(receivet);
	receivet.setBounds(130, 20, 150,30);
	receivet.setFont(new java.awt.Font("黑体", Font.BOLD, 20));
	receivet.setHorizontalAlignment(JTextField.RIGHT);
	receivet.setEditable(false);
	right.add(gather);
	gather.setFont(new java.awt.Font("新宋体", Font.BOLD, 30));
	gather.setBounds(10,60,150,50);
	right.add(gathert);
	gathert.setBounds(130, 70, 120,30);
	gathert.setFont(new java.awt.Font("黑体", Font.BOLD, 20));
	gathert.setHorizontalAlignment(JTextField.RIGHT);
	right.add(ding);
	ding.setBounds(250, 70, 50, 30);
	ding.setBorder(BorderFactory.createLineBorder(new Color(223, 235, 246)));
	ding.setBackground(new Color(223, 235, 246));
	ding.addActionListener(new dingActionListener());  //收款确认按钮的监听
	right.add(change);
	change.setFont(new java.awt.Font("新宋体", Font.BOLD, 30));
	change.setBounds(10,110,150,50);
	right.add(changet);
	changet.setBounds(130, 120, 150,30);
	changet.setEditable(false);
	changet.setFont(new java.awt.Font("黑体", Font.BOLD, 20));
	changet.setHorizontalAlignment(JTextField.RIGHT);
	right.add(endPrint);
	endPrint.setBounds(75, 160, 150, 40);
	//endPrint.setBorder(BorderFactory.createLineBorder(new Color(223, 235, 246)));
	endPrint.addActionListener(new endActionListener());  //打印凭条小票的监听	
	right.setVisible(true);
	page.setVisible(true);
	page.setBounds(10, 10, 1360, 700);
	page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

//左侧界面的设计，合计，商品数量。
public void TheLeft() 
{
	page.setLayout(null);
	JPanel left = new JPanel();
	page.add(left);
	left.setBounds(5, 450, 1050, 160);
	//left.setBackground(new Color(177, 209, 236));
	left.setBackground(new Color(216 ,191 ,216));
	left.setLayout(null);
	//left.setBorder(BorderFactory.createLineBorder(Color.black));
	left.setVisible(true);		
	left.add(total);
	total.setFont(new java.awt.Font("新宋体", Font.BOLD, 100));
	total.setBounds(15, 15, 280, 100);
	left.add(totalt);
	totalt.setBounds(275, 20, 480, 100);
	totalt.setBorder(BorderFactory.createLineBorder(new Color(216 ,191 ,216)));
	totalt.setBackground(new Color(216 ,191 ,216));
	totalt.setFont(new java.awt.Font("黑体", Font.BOLD, 100));
	totalt.setHorizontalAlignment(JTextField.CENTER);
	totalt.setEditable(false);
	left.add(productAmount);
	productAmount.setFont(new java.awt.Font("新宋体", Font.BOLD, 40));
	productAmount.setBounds(750, 110, 190, 40);
	left.add(productAmountt);
	productAmountt.setBounds(940, 110, 80, 40);
	productAmountt.setBorder(BorderFactory.createLineBorder(new Color(216 ,191 ,216)));
	productAmountt.setBackground(new Color(216 ,191 ,216));
	productAmountt.setFont(new java.awt.Font("新宋体", Font.BOLD, 40));
	productAmountt.setHorizontalAlignment(JTextField.CENTER);
	productAmountt.setEditable(false);			
	page.setVisible(true);
	page.setBounds(10, 10, 1360, 700);
	page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

//底部界面的设计，会员号，会员名，会员积分。
public void TheBottom() 
{
	page.setLayout(null);
	JPanel bottom = new JPanel();
	page.add(bottom);
	bottom.setBounds(5, 610, 1035, 50);
	bottom.setBackground(new Color(223, 235, 246));
	bottom.setLayout(null);
	//bottom.setBorder(BorderFactory.createLineBorder(Color.black));
	bottom.setVisible(true);
	bottom.add(cardNumber);
	cardNumber.setFont(new java.awt.Font("新宋体", Font.PLAIN, 18));
	cardNumber.setBounds(15, 5, 90, 50);
	bottom.add(cardNumbert);
	cardNumbert.setBounds(100, 15, 150,30);
	cardNumbert.setHorizontalAlignment(JTextField.RIGHT);
	cardNumbert.setFont(new java.awt.Font("新宋体", Font.PLAIN, 20));
	bottom.add(yes);
	yes.setBounds(250, 15, 50, 30);
	yes.setBorder(BorderFactory.createLineBorder(new Color(223, 235, 246)));
	yes.setBackground(new Color(223, 235, 246));
	yes.addActionListener(new yesActionListener());  //会员确认按钮的监听
	bottom.add(cardName);
	cardName.setFont(new java.awt.Font("新宋体", Font.PLAIN, 18));
	cardName.setBounds(310, 5, 90, 50);
	bottom.add(cardNamet);
	cardNamet.setBounds(390, 15, 150,30);
	cardNamet.setFont(new java.awt.Font("新宋体", Font.PLAIN, 20));
	cardNamet.setEditable(false);
	bottom.add(memberPoint);
	memberPoint.setFont(new java.awt.Font("新宋体", Font.PLAIN, 18));
	memberPoint.setBounds(550, 5, 90, 50);
	bottom.add(memberPointt);
	memberPointt.setBounds(640, 15, 150,30);
	memberPointt.setFont(new java.awt.Font("新宋体", Font.PLAIN, 20));
	memberPointt.setEditable(false);
	bottom.add(discount);
	discount.setFont(new java.awt.Font("新宋体", Font.PLAIN, 18));
	discount.setBounds(790, 5, 90, 50);
	bottom.add(discountt);
	discountt.setBounds(870, 15, 150,30);
	discountt.setFont(new java.awt.Font("新宋体", Font.PLAIN, 20));
	discountt.setEditable(false);	
	page.setVisible(true);
	page.setBounds(10, 10, 1360, 700);
	page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

//商品列表，JTable
public void TheCenter() 
{
	page.setLayout(null);
	defaultModel = new DefaultTableModel(data,name);
	table = new JTable(defaultModel);
	table.setPreferredScrollableViewportSize(new Dimension(1335,395));
	table.setBackground(new Color(240 ,255, 240));
	JScrollPane s = new JScrollPane(table);
	s.setBounds(5, 55, 1335, 395);	
	Container contentPane = page.getContentPane();
	contentPane.setBackground(new Color(240 ,255, 240));
	contentPane.setLayout(null);
	contentPane.add(s);
	s.setBounds(5, 55, 1335,395);
	int rowcount = defaultModel.getRowCount()-1;
	defaultModel.removeRow(rowcount);
	defaultModel.setRowCount(rowcount);
	table.revalidate();
	table.addMouseListener(new MouseAdapter()   //鼠标事件的监听
	{
		public void mouseClicked(MouseEvent e) 
		{
			@SuppressWarnings("unused")
			int selectedRow=table.getSelectedRow();
		}		
	});
	page.pack();			
	page.setVisible(true);
	page.setBounds(10, 10, 1360, 700);
	page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

/*对输入的会员号码是否符合格式要求的检测，要求为：4位，纯数字，不含特殊字符*/
public void test1()
{
	vip = cardNumbert.getText();
	System.out.println(vip);
	char ch;
	if(vip.length()==4)
	{
		for(int i=0;i<4;i++)
		{
			ch=vip.charAt(i);
			if(ch>='0'&&ch<='9')  //测试是否为数字
			{
				ok = 0;
			}
			else
			{
				ok = 1;
				break;
			}
		}
	}
	else
	{
		ok = 1;
	}
}

/*对输入的商品编号是否符合格式要求的检测，要求为：6位，纯数字，不含特殊字符*/
public void test2()
{
	product = productIdt.getText();
	//System.out.println(product);
	char ch;
	if(product.length()==6)
	{
		for(int i=0;i<6;i++)
		{
			ch=product.charAt(i);
			if(ch>='0'&&ch<='9')//测试是否为数字
			{
				okk = 0;
			}
			else
			{
				okk = 1;
				break;
			}
		}
	}
	else
	{
		okk = 1;
	}
}

/*对输入的付款金额是否符合格式要求的检测，要求为：6位，小数点后两位，不含特殊字符*/
public void test3()
{
	getting = gathert.getText();
	char ch;
	int point = -10;
	if(getting.length()<=8)
	{
		if(getting.charAt(0)!='-')
		{
			for(int i=0;i<getting.length();i++)
			{
				ch=getting.charAt(i);
				if(ch>='0'&&ch<='9')   //测试是否为数字
				{
					okkk=0;
					point++;
				}
				else
				{
					if(ch=='.')
					{
						point=0;
						continue;
					}
					else
					{
						okkk=1;
						break;
					}
				}
			}		
			
		}
		else
		{
			okkk=2;
		}
	}
	else
	{
		okkk=1;
	}
	if(point>2)
	{
		okkk=1;
	}
}

//两次商品货号的检测
public void test4()
{
	p = 999;
	p2 = productIdt.getText();
	for(int i=0;i<length;i++)
	{
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		String co=(String) tableModel.getValueAt(i, 0);
		if(p2.equals(co))
		{
			p = i;
		}
	}
}

//对流水号的确定检测
public void test5()
{
	date = gdate.format(new Date());
	Statement stm;
	try {
		stm = conn.createStatement();
		ResultSet rs=stm.executeQuery("select xssj from (select * from xsjl order by xssj desc) where rownum=1");
		int nu=0;
		while(rs.next())
		{
			nu++;
		}
		if(nu==0)
		{
			gnumber = 1;
		}
		else
		{
			ResultSet rt=stm.executeQuery("select * from (select * from (select * from xsjl order by xssj desc) order by xsxh desc) where rownum=1");
			rt.next();
			String dat = rt.getString("xssj");
			String gnum = rt.getString("xsxh");
			if(dat.equals(date))
			{				
				gnumber=Double.parseDouble(gnum)+1;				
			}
			else
			{
				gnumber = 1;
			}
		}
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	
}

public void enter()
{
	test2();
	if(okk == 0)
	{
		Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet rs=stm.executeQuery("select * from sp where spbh="+product+"");
			
			int nu=0;
			while(rs.next())
			{
				nu++;
			}
			if(nu==0)
			{
				JOptionPane.showMessageDialog(null, "无该编号商品，请核对后输入"); 
			}
			else
			{
				test4();
				ResultSet rn=stm.executeQuery("select * from sp where spbh="+product+"");
				rn.next();
				cow1 = rn.getString("spbh");
				cow2 = rn.getString("sppm");
				cow3 = rn.getString("dj");
				if(p!=999)
				{
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					int co=(int) tableModel.getValueAt(p, 3);
					cow4=co+1;
					defaultModel.removeRow(p);
					table.revalidate();						
					double cow51 = cow4*Double.parseDouble(cow3);
					String cow52 = new DecimalFormat("0.00").format(cow51);
					cow5 = cow52;
					stm.executeUpdate("update xsjl set spsl="+cow4+" where xssj="+date+" and xsxh="+gnumber+" and spbh="+cow1+"");
				}
				else
				{
					cow4 = 1;
					double cow51 = cow4*Double.parseDouble(cow3);
					String cow52 = new DecimalFormat("0.00").format(cow51);
					cow5 = cow52;
					System.out.println(cow2);
					stm.execute("insert into xsjl (xssj,xsxh,spbh,sppm,spjg,spsl,hykh,syyg) values ("+date+","+gnumber+","+cow1+",'"+cow2+"',"+cow3+","+cow4+",'0000',"+cash+")");
					length++;
				}
				sum = sum+Double.parseDouble(cow3);
				tol = sum*dis;
				number++;
				String summ = new DecimalFormat("0.00").format(sum);
				String toll = new DecimalFormat("0.00").format(tol);
				//System.out.println(summ);
				//System.out.println(number);
				defaultModel.addRow(new Object[] {cow1,cow2,cow3,cow4,cow5});
				receivet.setText(toll);
				totalt.setText("￥"+summ);
				productAmountt.setText(""+number);
				table.revalidate();
				repaint();
				validate();
				invalidate();
				validate();
				/*
				ResultSet rn=stm.executeQuery("select * from spxx where spbh="+product+"");
				rn.next();
				cow1 = rn.getString("spbh");
				cow2 = rn.getString("sppm");
				cow3 = rn.getString("dj");
				cow4 = 1;
				cow5 = cow4*Double.parseDouble(cow3);
				sum = sum+Double.parseDouble(cow3);
				tol = sum*dis;
				number++;
				String summ = new DecimalFormat("0.00").format(sum);
				String toll = new DecimalFormat("0.00").format(tol);
				//System.out.println(summ);
				//System.out.println(number);
				defaultModel.addRow(new Object[] {cow1,cow2,cow3,cow4,cow5});
				receivet.setText(toll);
				totalt.setText(summ);
				productAmountt.setText(""+number);
				table.revalidate();
				length++;
				repaint();
				validate();
				invalidate();
				validate();*/
			}
			
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
	}
	else
	{
		JOptionPane.showMessageDialog(null, "请检查商品编号的格式"); 
	}
}
//商品确定的监听
private class sureActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		test2();
		if(okk == 0)
		{
			Statement stm;
			try {
				stm = conn.createStatement();
				ResultSet rs=stm.executeQuery("select * from sp where spbh="+product+"");
				
				int nu=0;
				while(rs.next())
				{
					nu++;
				}
				if(nu==0)
				{
					JOptionPane.showMessageDialog(null, "无该编号商品，请核对后输入"); 
				}
				else
				{
					test4();
					ResultSet rn=stm.executeQuery("select * from sp where spbh="+product+"");
					rn.next();
					cow1 = rn.getString("spbh");
					cow2 = rn.getString("sppm");
					cow3 = rn.getString("dj");
					if(p!=999)
					{
						DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
						int co=(int) tableModel.getValueAt(p, 3);
						cow4=co+1;
						defaultModel.removeRow(p);
						table.revalidate();						
						double cow51 = cow4*Double.parseDouble(cow3);
						String cow52 = new DecimalFormat("0.00").format(cow51);
						cow5 = cow52;
						stm.executeUpdate("update xsjl set spsl="+cow4+" where xssj="+date+" and xsxh="+gnumber+" and spbh="+cow1+"");
					}
					else
					{
						cow4 = 1;
						double cow51 = cow4*Double.parseDouble(cow3);
						String cow52 = new DecimalFormat("0.00").format(cow51);
						cow5 = cow52;
						System.out.println(cow2);
						stm.execute("insert into xsjl (xssj,xsxh,spbh,sppm,spjg,spsl,hykh,syyg,hyzk) values ("+date+","+gnumber+","+cow1+",'"+cow2+"',"+cow3+","+cow4+",'0000',"+cash+",'1')");
						length++;
					}
					sum = sum+Double.parseDouble(cow3);
					tol = sum*dis;
					number++;
					String summ = new DecimalFormat("0.00").format(sum);
					String toll = new DecimalFormat("0.00").format(tol);
					//System.out.println(summ);
					//System.out.println(number);
					defaultModel.addRow(new Object[] {cow1,cow2,cow3,cow4,cow5});
					receivet.setText(toll);
					totalt.setText("￥"+summ);
					productAmountt.setText(""+number);
					table.revalidate();
					repaint();
					validate();
					invalidate();
					validate();
					/*
					ResultSet rn=stm.executeQuery("select * from spxx where spbh="+product+"");
					rn.next();
					cow1 = rn.getString("spbh");
					cow2 = rn.getString("sppm");
					cow3 = rn.getString("dj");
					cow4 = 1;
					cow5 = cow4*Double.parseDouble(cow3);
					sum = sum+Double.parseDouble(cow3);
					tol = sum*dis;
					number++;
					String summ = new DecimalFormat("0.00").format(sum);
					String toll = new DecimalFormat("0.00").format(tol);
					//System.out.println(summ);
					//System.out.println(number);
					defaultModel.addRow(new Object[] {cow1,cow2,cow3,cow4,cow5});
					receivet.setText(toll);
					totalt.setText(summ);
					productAmountt.setText(""+number);
					table.revalidate();
					length++;
					repaint();
					validate();
					invalidate();
					validate();*/
				}
				
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "请检查商品编号的格式"); 
		}
	}
}

//会员编号的监听
private class yesActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		test1();
		if(ok==0)
		{
			Statement stm;
			try {
				stm = conn.createStatement();
				ResultSet rs=stm.executeQuery("select * from hyxx where hyid="+vip+"");
				int nu=0;
				while(rs.next())
				{
					nu++;
				}
				if(nu==0)
				{
					JOptionPane.showMessageDialog(null, "无该账号用户，请核对后输入"); 
				}
				else
				{
					ResultSet rn=stm.executeQuery("select * from hyxx where hyid="+vip+"");
					rn.next();
					cardNamet.setText(rn.getString("hyname"));
					memberPointt.setText(rn.getString("hygrade"));
					if(rn.getString("hylevel").equals("0"))
					{
						dis=0.8;
					}
					else
					{
						dis=0.9;
					}
					discountt.setText(" "+dis);
					tol = sum*dis;
					String summ = new DecimalFormat("0.00").format(sum);
					String toll = new DecimalFormat("0.00").format(tol);
					receivet.setText(toll);
					totalt.setText("￥"+summ);
					productAmountt.setText(""+number);
					repaint();
					validate();
					invalidate();
					validate();
				}
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "请检查会员卡号的格式"); 
		}
		
	}

}

//付款的监听
private class dingActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		test3();
		if(okkk==0)
		{
			//System.out.println("getting"+getting);
			//System.out.println("sum"+sum);
			changing = Double.parseDouble(getting)-tol;
			//System.out.println("changing"+changing);
			if(changing<0)
			{
				JOptionPane.showMessageDialog(null, "收款金额不足以支付");
			}
			else
			{
				String changg = new DecimalFormat("0.00").format(changing);
				changet.setText(""+changg);
				repaint();
				validate();
				invalidate();
				validate();
			}
		}
		else if(okkk==1)
		{
			JOptionPane.showMessageDialog(null, "请检查收款金额的格式");
		}
		else if(okkk==2)
		{
			JOptionPane.showMessageDialog(null, "收款金额不可以为负数");
		}
	}
}

//打印凭条的监听
private class endActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		String cha = changet.getText();
		if(cha.equals("0.00"))
		{
			JOptionPane.showMessageDialog(null, "请先确认付款");
		}
		else
		{
			Statement stm;
			//System.out.println(length);
			for(int i=0;i<length;i++)
			{
				//System.out.println(length);
				int rowcount = defaultModel.getRowCount()-1;
				if(rowcount >= 0)
				{
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					String bh=(String) tableModel.getValueAt(rowcount, 0);
					Object sllll = defaultModel.getValueAt(rowcount, 3);
					String slll = sllll.toString();
					double sl=Double.parseDouble(slll);
					//int sl=Integer.parseInt(tableModel.getValueAt(rowcount, 3));
					//int sl=(int) tableModel.getValueAt(rowcount, 3);
					try {
						stm = conn.createStatement();
						ResultSet rr=stm.executeQuery("select sl from sp where spbh="+bh+"");
						rr.next();
						System.out.println(rr.getString("sl"));
						double sll = Double.parseDouble(rr.getString("sl"));
						sll=sll-sl;
						stm.executeUpdate("update sp set sl="+sll+" where spbh="+bh+"");
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					defaultModel.removeRow(rowcount);
					defaultModel.setRowCount(rowcount);
				}
				table.revalidate();
			}
			if(vip!=null)
			{
				try {
					String zk = discountt.getText();
					System.out.println(zk);
					stm = conn.createStatement();
					ResultSet rn=stm.executeQuery("select * from hyxx where hyid="+vip+"");
					rn.next();
					hygrade = Double.parseDouble(rn.getString("hygrade"))+sum;
					stm.executeUpdate("update hyxx set hygrade="+hygrade+" where hyid="+vip+"");
					stm.executeUpdate("update xsjl set hykh="+vip+" where xssj="+date+" and xsxh="+gnumber+"");
					stm.executeUpdate("update xsjl set hyzk="+zk+" where xssj="+date+" and xsxh="+gnumber+"");
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			gnumber++;
			Start();
			repaint();
			validate();
			invalidate();
			validate();
			JOptionPane.showMessageDialog(null, "收款结束");
		}		
	}
}

//删除商品的监听
private class deleteActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		int selectedRow=table.getSelectedRow();
		if(selectedRow!=-1)
		{
			Object co3 = defaultModel.getValueAt(selectedRow, 2);
			Object co4 = defaultModel.getValueAt(selectedRow, 3);
			double nu=Double.parseDouble(co4.toString());
			if(nu>1)
			{
				Object co1 = defaultModel.getValueAt(selectedRow, 0);
				Object co2 = defaultModel.getValueAt(selectedRow, 1);
				String cow1 = co1.toString();
				String cow2 = co2.toString();
				String cow3 = co3.toString();
				nu=nu-1;
				String cow4 = new DecimalFormat("0").format(nu);
				double cow51 = nu*Double.parseDouble(cow3);
				String cow52 = new DecimalFormat("0.00").format(cow51);
				String cow5 = cow52;
				defaultModel.addRow(new Object[] {cow1,cow2,cow3,cow4,cow5});
				
				Statement stm;
				try {
					stm = conn.createStatement();
					stm.executeUpdate("update xsjl set spsl="+cow4+" where xssj="+date+" and xsxh="+gnumber+" and spbh="+cow1+"");
					
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}						
			else
			{
				Statement stm;
				double dele=Double.parseDouble(co3.toString());	
				try {
					stm = conn.createStatement();
					stm.execute("delete from xsjl where xssj="+date+" and xsxh="+gnumber+" and spbh="+dele+"");
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				length--;
			}
			
			double dele=Double.parseDouble(co3.toString());		
			sum = sum-dele;
			number--;
			String summ = new DecimalFormat("0.00").format(sum);
			//System.out.println(summ);
			//System.out.println(number);
			tol = sum*dis;
			String toll = new DecimalFormat("0.00").format(tol);
			receivet.setText(toll);
			totalt.setText("￥"+summ);
			productAmountt.setText(""+number);
			table.revalidate();
			cow4--;
			defaultModel.removeRow(selectedRow);	
			repaint();
			validate();
			invalidate();
			validate();
		}	
	}
}

//打开数据库
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

public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		CashierInterface a = new CashierInterface();
		a.open();
		a.Start();
		a.TheTop();
		a.TheRight();
		a.TheLeft();
		a.TheBottom();
		a.TheCenter();

	}

}

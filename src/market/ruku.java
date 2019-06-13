package market;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@SuppressWarnings("serial")
public class ruku extends JFrame{ 
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;//登录界面
	JLabel title=new JLabel("商品入库");
	JLabel name=new JLabel("品名");
	JLabel password=new JLabel("编号");
	JLabel a=new JLabel("单价");
	JLabel b=new JLabel("数量");
	JLabel c=new JLabel("生产日期");
	JLabel d=new JLabel("保质期");
	JLabel e=new JLabel("备注");
	JLabel g=new JLabel("入库日期");
	JLabel m=new JLabel("*");
	JLabel n=new JLabel(" ");
	JLabel m1=new JLabel("*");
	JLabel m2=new JLabel("*");
	JLabel m3=new JLabel("*");
	JLabel m4=new JLabel("*");
	JLabel m5=new JLabel("*");
	JTextField na=new JTextField(1);
	JTextField pa=new JTextField(1);
	JTextField p1=new JTextField(1);
	JTextField p2=new JTextField(1);
	JTextField p3=new JTextField(1);
	JTextField p4=new JTextField(1);
	JTextField p5=new JTextField(10);
	JTextField p6=new JTextField(5);
	JTextField p10=new JTextField(10);
	JButton lognin=new JButton("入库");
	JButton quit=new JButton("取消");
	Date now = new Date(); 
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
    	public ruku(){
    	super("商品入库");
    	JFrame f = new JFrame("增加商品");
    	f.setBounds(250, 100,800,800);
    	int r = 135;
        int g1 = 206;
        int p = 235;
        Color bgColor = new Color(r, g1, p);
		f.getContentPane().setBackground(bgColor);
    	
    	Panel p9 = new Panel();
    	p9.add(new JLabel("入库信息(*号为必填项)"));
    	f.add(p9, BorderLayout.NORTH);
    	m.setForeground(Color.red);
    	m1.setForeground(Color.red);
    	m2.setForeground(Color.red);
    	m3.setForeground(Color.red);
    	m4.setForeground(Color.red);
    	m5.setForeground(Color.red);
    	p6.setEditable(false);
    	p6.setText("0");
    	p4.setText("0");
    	p10.setEditable(false);
    	p10.setText(df.format( now ));
    	pa.setToolTipText("编号为6位纯数字");
    	na.setToolTipText("请输入商品名称");
    	p1.setToolTipText("请输入商品单价");
    	p2.setToolTipText("本次进货数量");
    	p3.setToolTipText("请输入生产日期，如1997-01-23");
    	p4.setToolTipText("请输入保质期，单位为天");
    	p5.setToolTipText("请输入该商品的注意事项");
    	p6.setToolTipText("显示当前库存");
    	p10.setToolTipText("显示入库日期");
    	Panel p0 = new Panel();
    	p0.setLayout(new GridLayout(4,6,10,20));
    	
    	
    	
    	p0.add(name);
    	p0.add(na);
    	p0.add(m1);
    	p0.add(password);
    	p0.add(pa);
    	p0.add(m2);
    	p0.add(a);
    	p0.add(p1);
    	p0.add(m3);
    	p0.add(b);
    	p0.add(p2);
    	p0.add(m4);
    	p0.add(c);
    	p0.add(p3);
    	p0.add(m);
    	p0.add(d);
    	p0.add(p4);
    	p0.add(new JLabel(" "));
    	p0.add(e);
    	p0.add(p5);
    	p0.add(new JLabel(" "));
    	p0.add(g);
    	p0.add(p10);
    	p0.add(m5);
    	f.add(p0,BorderLayout.CENTER);
    	Panel p8 = new Panel();
    	p8.add(new JLabel("当前库存"));
    	p8.add(p6);
    	p8.add(lognin);
    	p8.add(quit);
    	f.add(p8,BorderLayout.SOUTH);
    	f.pack();
       
    	f. setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pa.addFocusListener (new FocusListener(){
        	
    	   public void focusLost(FocusEvent e) {
    		   boolean result=pa.getText().matches("[0-9]+");
    		   if(pa.getText().length() !=6 || pa.getText().equals("")||result==false){
    			 JOptionPane.showMessageDialog(pa, "编号为6位纯数字必填项，请重新输入", "提示",JOptionPane.YES_OPTION);
    			 pa.setText("");
    		   }
    			 else {
    			shujuku();
    			 
    		   }
    		   
    		   
    		  
}
    		 public void focusGained(FocusEvent e) {
    			 
    		   
    		   }
    		  });
        
      
        class MyActionListener1 implements ActionListener{
     		public void actionPerformed(ActionEvent e){
     			if(na.getText().length()==0) {
     				 JOptionPane.showMessageDialog(null, "品名不能为空，请重新输入", "警告",JOptionPane.YES_OPTION);
     		}
     			else {
     				if(pa.getText().length()==0) {
        				 JOptionPane.showMessageDialog(null, "编号不能为空，请重新输入", "警告",JOptionPane.YES_OPTION);
        		}
     				else {
     					 boolean reg=p1.getText().matches("^[0-9]+(.[0-9]+)?$");
     					 if(!reg) {
     						JOptionPane.showMessageDialog(null, "单价应为浮点数，请重新输入", "警告",JOptionPane.YES_OPTION);
     					 }
     					 else {
     						boolean re=p2.getText().matches("[0-9]+");
     						if(!re) {
     							JOptionPane.showMessageDialog(null, "数量应为整数，请重新输入", "警告",JOptionPane.YES_OPTION);
     						}
     						else {
     							boolean req=p3.getText().matches("^((\\d{2}(([02468][048])|([13579][26]))"  
     						             + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"  
     						             + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"  
     						             + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("  
     						             + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"  
     						             + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
     							if(!req) {
     								JOptionPane.showMessageDialog(null, "日期格式错误，请重新输入", "警告",JOptionPane.YES_OPTION);
     							}
     							else {boolean rek=p3.getText().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}");
     							if(!rek) {
     								JOptionPane.showMessageDialog(null, "日期格式错误，请重新输入", "警告",JOptionPane.YES_OPTION);
     							}else {
     								boolean res=p4.getText().matches("[0-9]+");
     								if(!res&&p4.getText().length()!=0) {
     									JOptionPane.showMessageDialog(null, "保质期格式错误，请重新输入", "警告",JOptionPane.YES_OPTION);
     								}
     								else {
     									//数据库入口；
     									 
     									sq ap=new sq();
     								ap.AddData(pa.getText(), na.getText(), p1.getText(), p2.getText(), p3.getText(), p4.getText(), p5.getText());
     								ap.AddData1(p10.getText(),pa.getText(), na.getText(), p1.getText(), p2.getText(), p3.getText(), p4.getText(), p5.getText());
     								na.setText("");
     				     			pa.setText("");
     				     			p1.setText("");
     				     			p2.setText("");
     				     			p3.setText("");
     				     			p4.setText("");
     				     			p5.setText("");}
     							}
     							
     						}
     						}
     						
     					 }
     					
     				}
     			}
     		}
        }
        class MyActionListener implements ActionListener{
      		public void actionPerformed(ActionEvent e){
      			na.setText("");
      			pa.setText("");
      			p1.setText("");
      			p2.setText("");
      			p3.setText("");
      			p4.setText("");
      			p5.setText("");
      			
      		}
      		}
       quit.addActionListener(new MyActionListener());
       lognin.addActionListener(new MyActionListener1());
       
    		
  
    	}
    	public void shujuku()  {try {
    		Class.forName("oracle.jdbc.driver.OracleDriver"); //加载数据库驱动
    		System.out.println("数据库驱动加载成功！");	 //输出的信息
    		String url = "jdbc:oracle:thin:@localhost:1521:orcl";	//获取连接URL
    		String user = "liudong"; //连接用户名
    		String password = "5692573"; //连接密码
    		 conn = DriverManager.getConnection(url, user, password); //获取数据库连接
    		
    		
    			
    			 String sql = "select spbh ,sl from  sp   ";
    			 pstm = conn.prepareStatement(sql);
    		    	rs = pstm.executeQuery();
    		    	
    		    	while(rs.next()) {
    		    		  if(rs.getString("spbh").equals(pa.getText()))
    		                {
    		    			  String sl1=rs.getString("sl");
    		    			 
    		    			  p6.setText(sl1);
    		                }
    		    	}
    	}
    	catch (SQLException e) {
            e.printStackTrace();
        
   		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	     }
    				


}
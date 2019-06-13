package market;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class j extends JFrame{
// 定义组件
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
private JScrollPane scpDemo;
private JTableHeader jth;
private JTable tabDemo;
private JLabel btnShow;
JLabel name=new JLabel("品名");
JLabel password=new JLabel("编号");
JLabel a=new JLabel("单价");
JLabel b=new JLabel("数量");
JLabel c=new JLabel("生产日期");
JLabel d=new JLabel("保质期");
JLabel e=new JLabel("备注");
JTextField na=new JTextField(1);
JTextField aa=new JTextField(1);
JTextField p1=new JTextField(1);
JTextField p2=new JTextField(1);
JTextField p3=new JTextField(1);
JTextField p4=new JTextField(1);
JTextField p5=new JTextField(1);
JButton xiu=new JButton("修改");
JButton shan=new JButton("删除");

// 构造方法
public j(String pa){
// 窗体的相关属性的定义
super("");
this.setSize(900,500);
this.setLayout(null);
this.setLocation(100,50);
// 创建组件
this.scpDemo = new JScrollPane();
this.scpDemo.setBounds(10,50,900,300);
this.btnShow = new JLabel("查询信息表");
this.btnShow.setBounds(400,10,300,30);
this.name.setBounds(10, 350, 40, 40);
this.na.setBounds(80, 350, 60, 30);
this.password.setBounds(150, 350, 40, 40);
this.aa.setBounds(230, 350, 60, 30);
this.a.setBounds(310, 350, 40, 40);
this.p1.setBounds(390, 350, 60, 30);
this.b.setBounds(470, 350, 40, 40);
this.p2.setBounds(550, 350, 60, 30);
this.c.setBounds(630, 350, 60, 40);
this.p3.setBounds(700, 350, 150, 30);
this.d.setBounds(10, 420, 40, 40);
this.p4.setBounds(80, 420, 60, 30);
this.e.setBounds(150, 420, 40, 40);
this.p5.setBounds(230, 420, 150, 30);
this.xiu.setBounds(700, 420, 90, 30);
this.shan.setBounds(800, 420, 90, 30);
aa.setEditable(false);
// 给按钮注册监听

// 将组件加入到窗体中
add(this.scpDemo);
add(this.btnShow);
add(this.name);
add(this.na);
add(this.password);
add(this.aa);
add(this.a);
add(this.p1);
add(this.b);
add(this.p2);
add(this.c);
add(this.p3);
add(this.d);
add(this.p4);
add(this.e);
add(this.p5);
add(this.xiu);
add(this.shan);

// 显示窗体
this.setVisible(true);
class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(na.getText().length()==0) {
				 JOptionPane.showMessageDialog(null, "品名不能为空，请重新输入", "警告",JOptionPane.YES_OPTION);
		}
			else {
				if(aa.getText().length()==0) {
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
			sq ap=new sq();
			ap.update(aa.getText(), na.getText(), p1.getText(), p2.getText(), p3.getText(), p4.getText(), p5.getText());
			ap.update1(aa.getText(), na.getText(), p1.getText(), p2.getText(), p3.getText(), p4.getText(), p5.getText());
		}
		}
							}
						}
					 }
				}
			}
		}
}
class MyActionListener1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			 int option=JOptionPane.showConfirmDialog(null, "是否删除", "警告",JOptionPane.YES_OPTION);
		      if(option==JOptionPane.YES_OPTION) 
		      {
		    	sq a=new sq();
  			a.shan(aa.getText());
		      }
		      else {
		    	  return;
		      }
			
		}
}
shan.addActionListener(new MyActionListener1());
xiu.addActionListener(new MyActionListener());
// 点击按钮时的事件处理

// 以下是连接数据源和显示数据的具体处理方法，请注意下
try{
// 获得连接
	Class.forName("oracle.jdbc.driver.OracleDriver");
String url = "jdbc:oracle:thin:@localhost:1521:orcl";	//获取连接URL
String user = "liudong"; //连接用户名
String password = "5692573"; //连接密码
 Connection conn = DriverManager.getConnection(url, user, password); //获取数据库连接
// 建立查询条件
String sql = "select * from sp "; 
PreparedStatement pstm = conn.prepareStatement(sql);
// 执行查询
ResultSet rs = pstm.executeQuery();
// 计算有多少条记录
int count = 0;
while(rs.next()){
count++;
}
rs = pstm.executeQuery();
// 将查询获得的记录数据，转换成适合生成JTable的数据形式
Object[][] info = new Object[count][7];
count = 0;
while(rs.next()){
if(rs.getString("spbh").equals(pa))
{
info[count][0]= rs.getString("spbh");
info[count][1] = rs.getString("sppm");
info[count][2]=   rs.getString("sl");

info[count][3] = rs.getDate("scrq");
info[count][4] =  rs.getString("bzq");
info[count][5] =   rs.getString("dj");
info[count][6] =   rs.getString("bz");
na.setText(rs.getString("sppm"));
aa.setText(rs.getString("spbh"));
p1.setText(rs.getString("dj"));
p2.setText(rs.getString("sl"));
String mn=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("scrq"));
p3.setText(mn);
p4.setText(rs.getString("bzq"));
p5.setText(rs.getString("bz"));
count++;
}
}
if(count==0)
{
	JOptionPane.showMessageDialog(null, "查无此商品，请重新输入", "警告",JOptionPane.YES_OPTION);
	
}
else {
// 定义表头
String[] title = {"商品编号","商品名称","数量","生产日期","保质期","单价","备注"};
// 创建JTable
this.tabDemo = new JTable(info,title);
// 显示表头
this.jth = this.tabDemo.getTableHeader();
// 将JTable加入到带滚动条的面板中
this.scpDemo.getViewport().add(tabDemo); 

}
}catch(ClassNotFoundException cnfe){
JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
}catch(SQLException sqle){
JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
}
finally {
	if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if (pstm != null) {
        try {
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        }
}
}



}

package market;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class chus extends JFrame{
// 定义组件
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
private JScrollPane scpDemo;
private JTableHeader jth;
private JTable tabDemo;
private JLabel btnShow;
JLabel a=new JLabel("年");
JLabel b=new JLabel("月");
JLabel c=new JLabel("日");
JComboBox jc=new JComboBox(new Year());
JComboBox jc1=new JComboBox(new Mounth());
JComboBox jc2=new JComboBox(new Day());
JButton delete=new JButton("删除");
JButton  cha=new  JButton("返回");

// 构造方法
public chus(String m,String m1,String m2){
// 窗体的相关属性的定义
super("");
this.setSize(900,500);
this.setLayout(null);
this.setLocation(100,50);
// 创建组件
this.scpDemo = new JScrollPane();
this.scpDemo.setBounds(10,50,900,300);
this.btnShow = new JLabel("出库统计表");
this.btnShow.setBounds(400,10,300,30);
this.jc.setBounds(300,400,60,20);
this.jc1.setBounds(440,400,60,20);
this.jc2.setBounds(580,400,60,20);
this.delete.setBounds(700,400,80,30);
this.a.setBounds(250,400,40,30);
this.b.setBounds(380,400,40,30);
this.c.setBounds(520,400,40,30);
this.cha.setBounds(800,400,80,30);
// 给按钮注册监听

// 将组件加入到窗体中
add(this.scpDemo);
add(this.btnShow);
add(this.jc);
add(this.jc1);
add(this.jc2);

add(this.a);
add(this.b);
add(this.c);
add(this.cha);
// 显示窗体
this.setVisible(true);

// 点击按钮时的事件处理
class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new biao();
			dispose();
			
		}
	}

cha.addActionListener(new MyActionListener());
// 以下是连接数据源和显示数据的具体处理方法，请注意下
try{
// 获得连接
	Class.forName("oracle.jdbc.driver.OracleDriver");
String url = "jdbc:oracle:thin:@localhost:1521:orcl";	//获取连接URL
String user = "liudong"; //连接用户名
String password = "5692573"; //连接密码
 Connection conn = DriverManager.getConnection(url, user, password); //获取数据库连接
// 建立查询条件
String sql = "select * from sp1 "; 
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
Object[][] info = new Object[count][8];
count = 0;
while(rs.next()){
	
	 int h=Integer.parseInt(m); 
	 int l=Integer.parseInt(m1); 
	 int n=Integer.parseInt(m2); 
	 rs.getDate("riqi");
     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
     String str=sdf.format(rs.getDate("riqi"));
     System.out.println(str);
     String year=str.substring(0, 4);
	  String month=str.substring(5, 7);
	  String day=str.substring(8, 10);
		 System.out.println(year);
		 System.out.println(month);
		 System.out.println(day);
		 int i=Integer.parseInt(year); 
		 int j=Integer.parseInt(month);
		 int k=Integer.parseInt(day); 
		 if(h==i&&l==j&&n==k) {
			 
		 
info[count][0]= rs.getDate("riqi");
info[count][1]= rs.getString("spbh");
info[count][2] = rs.getString("sppm");
info[count][3]=   rs.getString("sl");
info[count][4] = rs.getDate("scrq");
info[count][5] =  rs.getString("bzq");
info[count][6] =   rs.getString("dj");
info[count][7] =   rs.getString("bz");
count++;
}
		 }
// 定义表头
String[] title = {"出库日期","商品编号","商品名称","数量","生产日期","保质期","单价","备注"};
// 创建JTable
this.tabDemo = new JTable(info,title);
// 显示表头
this.jth = this.tabDemo.getTableHeader();
// 将JTable加入到带滚动条的面板中
this.scpDemo.getViewport().add(tabDemo); 
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

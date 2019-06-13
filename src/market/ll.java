package market;

import javax.swing.*;
import javax.swing.table.JTableHeader;


import java.sql.*;

@SuppressWarnings("serial")
public class ll extends JFrame{
// 定义组件
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
private JScrollPane scpDemo;
private JTableHeader jth;
private JTable tabDemo;
private JLabel btnShow;
// 构造方法
public ll(){
// 窗体的相关属性的定义
super("");
this.setSize(900,500);
this.setLayout(null);
this.setLocation(100,50);
// 创建组件
this.scpDemo = new JScrollPane();
this.scpDemo.setBounds(10,50,900,500);
this.btnShow = new JLabel("商品信息表");
this.btnShow.setBounds(400,10,300,30);
// 给按钮注册监听

// 将组件加入到窗体中
add(this.scpDemo);
add(this.btnShow);
// 显示窗体
this.setVisible(true);

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
String sql = "select * from sp"; 
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

info[count][0]= rs.getString("spbh");
info[count][1] = rs.getString("sppm");
info[count][2]=   rs.getString("sl");
info[count][3] = rs.getDate("scrq");
info[count][4] =  rs.getString("bzq");
info[count][5] =   rs.getString("dj");
info[count][6] =   rs.getString("bz");
count++;
}
// 定义表头
String[] title = {"商品编号","商品名称","数量","生产日期","保质期","单价","备注"};
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

package market;
import java.sql.*;
public class Database {
	static Connection con1=null;
	static Statement sql1=null;
	static ResultSet res1=null;
public Connection getConnection() {	
try {
Class.forName("oracle.jdbc.driver.OracleDriver"); //加载数据库驱动
System.out.println("数据库驱动加载成功！"); //输出的信息
String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //获取连接URL
String user = "liudong"; //连接用户名
String password = "5692573"; //连接密码
 con1 = DriverManager.getConnection(url, user, password); //获取数据库连接
if (con1 != null) {
System.out.println("成功的与Oracle数据库建立连接！！");
}
} catch (Exception e) {
e.printStackTrace();
} 
return con1; //返回Connection实例 
}
public static void main(String[] args) {
	Database selectData=new Database();
	
	try {
		con1=selectData.getConnection();
		sql1=con1.createStatement();
		res1=sql1.executeQuery("select hyid,hyname,hygrade,hylevel,phonenum from hyxx");
		while(res1.next()) {
			String hyid=res1.getString("hyid");
			String hyname=res1.getString("hyname");
			String hygrade=res1.getString("hygrade");
			String hylevel=res1.getString("hylevel");
			String phonenum=res1.getString("phonenum");
			System.out.print("会员号："+hyid);
			System.out.print("会员名："+hyname);
			System.out.print("会员积分："+hygrade);
			System.out.print("会员等级："+hylevel);
			System.out.print("电话号码："+phonenum);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}

}

}
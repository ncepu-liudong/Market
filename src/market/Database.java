package market;
import java.sql.*;
public class Database {
	static Connection con1=null;
	static Statement sql1=null;
	static ResultSet res1=null;
public Connection getConnection() {	
try {
Class.forName("oracle.jdbc.driver.OracleDriver"); //�������ݿ�����
System.out.println("���ݿ��������سɹ���"); //�������Ϣ
String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //��ȡ����URL
String user = "liudong"; //�����û���
String password = "5692573"; //��������
 con1 = DriverManager.getConnection(url, user, password); //��ȡ���ݿ�����
if (con1 != null) {
System.out.println("�ɹ�����Oracle���ݿ⽨�����ӣ���");
}
} catch (Exception e) {
e.printStackTrace();
} 
return con1; //����Connectionʵ�� 
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
			System.out.print("��Ա�ţ�"+hyid);
			System.out.print("��Ա����"+hyname);
			System.out.print("��Ա���֣�"+hygrade);
			System.out.print("��Ա�ȼ���"+hylevel);
			System.out.print("�绰���룺"+phonenum);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}

}

}
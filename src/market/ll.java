package market;

import javax.swing.*;
import javax.swing.table.JTableHeader;


import java.sql.*;

@SuppressWarnings("serial")
public class ll extends JFrame{
// �������
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
private JScrollPane scpDemo;
private JTableHeader jth;
private JTable tabDemo;
private JLabel btnShow;
// ���췽��
public ll(){
// �����������ԵĶ���
super("");
this.setSize(900,500);
this.setLayout(null);
this.setLocation(100,50);
// �������
this.scpDemo = new JScrollPane();
this.scpDemo.setBounds(10,50,900,500);
this.btnShow = new JLabel("��Ʒ��Ϣ��");
this.btnShow.setBounds(400,10,300,30);
// ����ťע�����

// ��������뵽������
add(this.scpDemo);
add(this.btnShow);
// ��ʾ����
this.setVisible(true);

// �����ťʱ���¼�����

// ��������������Դ����ʾ���ݵľ��崦��������ע����
try{
// �������
	Class.forName("oracle.jdbc.driver.OracleDriver");
String url = "jdbc:oracle:thin:@localhost:1521:orcl";	//��ȡ����URL
String user = "liudong"; //�����û���
String password = "5692573"; //��������
 Connection conn = DriverManager.getConnection(url, user, password); //��ȡ���ݿ�����
// ������ѯ����
String sql = "select * from sp"; 
PreparedStatement pstm = conn.prepareStatement(sql);
// ִ�в�ѯ
ResultSet rs = pstm.executeQuery();
// �����ж�������¼
int count = 0;
while(rs.next()){
count++;
}
rs = pstm.executeQuery();
// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
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
// �����ͷ
String[] title = {"��Ʒ���","��Ʒ����","����","��������","������","����","��ע"};
// ����JTable
this.tabDemo = new JTable(info,title);
// ��ʾ��ͷ
this.jth = this.tabDemo.getTableHeader();
// ��JTable���뵽���������������
this.scpDemo.getViewport().add(tabDemo); 
}catch(ClassNotFoundException cnfe){
JOptionPane.showMessageDialog(null,"����Դ����","����",JOptionPane.ERROR_MESSAGE);
}catch(SQLException sqle){
JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
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

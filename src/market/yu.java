package market;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

@SuppressWarnings("serial")
public class yu extends JFrame{
// �������
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
private JScrollPane scpDemo;
private JTableHeader jth;
private JTable tabDemo;
private JLabel btnShow;
JLabel a=new JLabel("��");
JLabel b=new JLabel("��");
JLabel c=new JLabel("��");
JComboBox jc=new JComboBox(new Year());
JComboBox jc1=new JComboBox(new Mounth());
JComboBox jc2=new JComboBox(new Day());
JButton delete=new JButton("ɾ��");
JButton  cha=new  JButton("��ѯ");
// ���췽��
public yu(){
// �����������ԵĶ���
super("");
this.setSize(900,500);
this.setLayout(null);
this.setLocation(100,50);
// �������
this.scpDemo = new JScrollPane();
this.scpDemo.setBounds(10,50,900,300);
this.btnShow = new JLabel("����ͳ�Ʊ�");
this.btnShow.setBounds(400,10,300,30);
// ����ťע�����
this.btnShow.setBounds(400,10,300,30);
this.jc.setBounds(300,400,60,20);
this.jc1.setBounds(440,400,60,20);
this.jc2.setBounds(580,400,60,20);
this.delete.setBounds(700,400,80,30);
this.a.setBounds(250,400,40,30);
this.b.setBounds(380,400,40,30);
this.c.setBounds(520,400,40,30);
this.cha.setBounds(800,400,80,30);
// ��������뵽������
add(this.scpDemo);
add(this.btnShow);
add(this.jc);
add(this.jc1);
add(this.jc2);
add(this.delete);
add(this.a);
add(this.b);
add(this.c);
add(this.cha);
// ��ʾ����
this.setVisible(true);
class MyActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		int option=JOptionPane.showConfirmDialog(null, "�Ƿ�ɾ��", "����",JOptionPane.YES_OPTION);
	      if(option==JOptionPane.YES_OPTION) 
	      {
	    	  String m=(String)jc.getSelectedItem();
	 		 String m1=(String)jc1.getSelectedItem();
	 		 String m2=(String)jc2.getSelectedItem();
	 		 sq a=new sq();
	 		 a.DeleteData1(m, m1, m2);
	 		 new yu();
	 		 dispose();
	      }
	      else {
	    	  return;
	      }
		
		
	}
}
class MyActionListener1 implements ActionListener{
	public void actionPerformed(ActionEvent e){
		String m=(String)jc.getSelectedItem();
		 String m1=(String)jc1.getSelectedItem();
		 String m2=(String)jc2.getSelectedItem();
		new chus(m,m1,m2);
		 
		 
		 dispose();
		
	}
}
delete.addActionListener(new MyActionListener());
cha.addActionListener(new MyActionListener1());
// �����ťʱ���¼�����

// ��������������Դ����ʾ���ݵľ��崦����������ע����
try{
// �������
	Class.forName("oracle.jdbc.driver.OracleDriver");
String url = "jdbc:oracle:thin:@localhost:1521:orcl";	//��ȡ����URL
String user = "liudong"; //�����û���
String password = "5692573"; //��������
 Connection conn = DriverManager.getConnection(url, user, password); //��ȡ���ݿ�����
// ������ѯ����
String sql = "select * from sp2"; 
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
Object[][] info = new Object[count][8];
count = 0;
while(rs.next()){
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
// �����ͷ
String[] title = {"��������","��Ʒ���","��Ʒ����","����","��������","������","����","��ע"};
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
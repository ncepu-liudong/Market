package market;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class chus extends JFrame{
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
JButton  cha=new  JButton("����");

// ���췽��
public chus(String m,String m1,String m2){
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
this.jc.setBounds(300,400,60,20);
this.jc1.setBounds(440,400,60,20);
this.jc2.setBounds(580,400,60,20);
this.delete.setBounds(700,400,80,30);
this.a.setBounds(250,400,40,30);
this.b.setBounds(380,400,40,30);
this.c.setBounds(520,400,40,30);
this.cha.setBounds(800,400,80,30);
// ����ťע�����

// ��������뵽������
add(this.scpDemo);
add(this.btnShow);
add(this.jc);
add(this.jc1);
add(this.jc2);

add(this.a);
add(this.b);
add(this.c);
add(this.cha);
// ��ʾ����
this.setVisible(true);

// �����ťʱ���¼�����
class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new biao();
			dispose();
			
		}
	}

cha.addActionListener(new MyActionListener());
// ��������������Դ����ʾ���ݵľ��崦��������ע����
try{
// �������
	Class.forName("oracle.jdbc.driver.OracleDriver");
String url = "jdbc:oracle:thin:@localhost:1521:orcl";	//��ȡ����URL
String user = "liudong"; //�����û���
String password = "5692573"; //��������
 Connection conn = DriverManager.getConnection(url, user, password); //��ȡ���ݿ�����
// ������ѯ����
String sql = "select * from sp1 "; 
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

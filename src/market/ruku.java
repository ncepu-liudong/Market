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
	ResultSet rs = null;//��¼����
	JLabel title=new JLabel("��Ʒ���");
	JLabel name=new JLabel("Ʒ��");
	JLabel password=new JLabel("���");
	JLabel a=new JLabel("����");
	JLabel b=new JLabel("����");
	JLabel c=new JLabel("��������");
	JLabel d=new JLabel("������");
	JLabel e=new JLabel("��ע");
	JLabel g=new JLabel("�������");
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
	JButton lognin=new JButton("���");
	JButton quit=new JButton("ȡ��");
	Date now = new Date(); 
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//�������ڸ�ʽ
    	public ruku(){
    	super("��Ʒ���");
    	JFrame f = new JFrame("������Ʒ");
    	f.setBounds(250, 100,800,800);
    	int r = 135;
        int g1 = 206;
        int p = 235;
        Color bgColor = new Color(r, g1, p);
		f.getContentPane().setBackground(bgColor);
    	
    	Panel p9 = new Panel();
    	p9.add(new JLabel("�����Ϣ(*��Ϊ������)"));
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
    	pa.setToolTipText("���Ϊ6λ������");
    	na.setToolTipText("��������Ʒ����");
    	p1.setToolTipText("��������Ʒ����");
    	p2.setToolTipText("���ν�������");
    	p3.setToolTipText("�������������ڣ���1997-01-23");
    	p4.setToolTipText("�����뱣���ڣ���λΪ��");
    	p5.setToolTipText("���������Ʒ��ע������");
    	p6.setToolTipText("��ʾ��ǰ���");
    	p10.setToolTipText("��ʾ�������");
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
    	p8.add(new JLabel("��ǰ���"));
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
    			 JOptionPane.showMessageDialog(pa, "���Ϊ6λ�����ֱ��������������", "��ʾ",JOptionPane.YES_OPTION);
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
     				 JOptionPane.showMessageDialog(null, "Ʒ������Ϊ�գ�����������", "����",JOptionPane.YES_OPTION);
     		}
     			else {
     				if(pa.getText().length()==0) {
        				 JOptionPane.showMessageDialog(null, "��Ų���Ϊ�գ�����������", "����",JOptionPane.YES_OPTION);
        		}
     				else {
     					 boolean reg=p1.getText().matches("^[0-9]+(.[0-9]+)?$");
     					 if(!reg) {
     						JOptionPane.showMessageDialog(null, "����ӦΪ������������������", "����",JOptionPane.YES_OPTION);
     					 }
     					 else {
     						boolean re=p2.getText().matches("[0-9]+");
     						if(!re) {
     							JOptionPane.showMessageDialog(null, "����ӦΪ����������������", "����",JOptionPane.YES_OPTION);
     						}
     						else {
     							boolean req=p3.getText().matches("^((\\d{2}(([02468][048])|([13579][26]))"  
     						             + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"  
     						             + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"  
     						             + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("  
     						             + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"  
     						             + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
     							if(!req) {
     								JOptionPane.showMessageDialog(null, "���ڸ�ʽ��������������", "����",JOptionPane.YES_OPTION);
     							}
     							else {boolean rek=p3.getText().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}");
     							if(!rek) {
     								JOptionPane.showMessageDialog(null, "���ڸ�ʽ��������������", "����",JOptionPane.YES_OPTION);
     							}else {
     								boolean res=p4.getText().matches("[0-9]+");
     								if(!res&&p4.getText().length()!=0) {
     									JOptionPane.showMessageDialog(null, "�����ڸ�ʽ��������������", "����",JOptionPane.YES_OPTION);
     								}
     								else {
     									//���ݿ���ڣ�
     									 
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
    		Class.forName("oracle.jdbc.driver.OracleDriver"); //�������ݿ�����
    		System.out.println("���ݿ��������سɹ���");	 //�������Ϣ
    		String url = "jdbc:oracle:thin:@localhost:1521:orcl";	//��ȡ����URL
    		String user = "liudong"; //�����û���
    		String password = "5692573"; //��������
    		 conn = DriverManager.getConnection(url, user, password); //��ȡ���ݿ�����
    		
    		
    			
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
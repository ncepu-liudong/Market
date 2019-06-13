package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ChangeEvent;
import java.sql.*;
import java.text.DecimalFormat;
import java.net.URL;
import java.util.Date;
import java.util.Vector;
public class Memberdetail extends JDialog{
	String[] border1= {BorderLayout.CENTER,BorderLayout.NORTH,BorderLayout.SOUTH,BorderLayout.WEST,BorderLayout.EAST};
	private DefaultTableModel tableModel;
	private MTable table;
	Connection con;
	Statement sql;
	ResultSet res;
	PreparedStatement sql1;
	JTextField jt;
	Vector m;
	boolean bl=false;
	public Memberdetail() {
		super(new MyFrame(),"��Ա��ϸ��ѯ");
		Container container=getContentPane();
		this.setLayout(new BorderLayout());
		JScrollPane sp=new JScrollPane();
		container.add(border1[0],sp);
		JPanel south=new JPanel();
		container.add(border1[2],south);
		south.setLayout(new FlowLayout());
		 jt=new JTextField(20);
		JLabel lb=new JLabel("��Աid:");
		JButton selectMember=new JButton("��ѯ");
		south.add(lb);
		south.add(jt);
		south.add(selectMember);
		Database selectData=new Database();
		 Vector columnNames;
			columnNames=new Vector();
			columnNames.add("��Ա��");
			columnNames.add("��Ʒ��");
			columnNames.add("��Ʒ�۸�");
			columnNames.add("��Ʒ����");
			columnNames.add("����ʱ��");
		
			selectMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String regex="\\d{4}";
					
						
						if(checkone(jt.getText())) {	
					m=select();
					tableModel=new DefaultTableModel(m,columnNames);
					table=new MTable(tableModel);
					table.setRowSorter(new TableRowSorter(tableModel));
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//���ñ���ѡ��ģʽΪ��ѡ
					sp.setViewportView(table);
					
						}
						else {
							 String error="�û�Ա�����ڣ�����������";
							new Error(error).setVisible(true);
						}
					
					
		
				}
			});
			tableModel=new DefaultTableModel(m,columnNames);
		table=new MTable(tableModel);
		table.setRowSorter(new TableRowSorter(tableModel));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sp.setViewportView(table);
		Toolkit theKit=this.getToolkit();
		Dimension screenSize=theKit.getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		this.setSize(800, 600);
		this.setLocation(centerX-400, centerY-350);
		
	}
	public  Vector select() {
		Database selectData=new Database();
		 Vector rowData1;
			
			rowData1=new Vector();
		con=selectData.getConnection();
		try {
			sql1=con.prepareStatement("select hykh,sppm,spjg,spsl,xssj from xsjl where hykh=?");
			sql1.setString(1, jt.getText());
			res=sql1.executeQuery();
			while(res.next()) {
				Vector hang=new Vector();
				hang.add(res.getString("hykh"));
				hang.add(res.getString("sppm"));
				hang.add(res.getString("spjg"));
				hang.add(res.getString("spsl"));
				hang.add(res.getString("xssj"));
				rowData1.add(hang);
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		 return rowData1;
	}
	public boolean checkone(String yhid) {
		Database selectData=new Database();
		 
		Vector	rowData=new Vector();
		con=selectData.getConnection();
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select hyid from hyxx");
			while(res.next()) {
				int number=Integer.parseInt(res.getString("hyid"));
				int number1=Integer.parseInt(yhid);
				if(number==number1) {
					bl=true;
				}
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bl;
	}


      
}

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
import java.net.URL;
import java.util.Date;
import java.util.Vector;
public class SelectAll extends JDialog{
	String[] border1= {BorderLayout.CENTER,BorderLayout.NORTH,BorderLayout.SOUTH,BorderLayout.WEST,BorderLayout.EAST};
	private DefaultTableModel tableModel;
	private MTable table;
	Connection con;
	Statement sql;
	ResultSet res;
	
	public SelectAll() {
		super(new MyFrame(),"会员全体查询");
		Container container=getContentPane();
		this.setLayout(new BorderLayout());
		
		JScrollPane sp=new JScrollPane();
		container.add(border1[0],sp);
		
		//数据库查询
		Database selectData=new Database();
		 Vector rowData,columnNames;
			columnNames=new Vector();
			columnNames.add("会员号");
			columnNames.add("会员名");
			columnNames.add("会员积分");
			columnNames.add("会员等级");
			columnNames.add("电话号码");
			rowData=new Vector();
		con=selectData.getConnection();
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select hyid,hyname,hygrade,hylevel,phonenum from hyxx");
			while(res.next()) {
				Vector hang=new Vector();
				hang.add(res.getString("hyid"));
				hang.add(res.getString("hyname"));
				hang.add(res.getString("hygrade"));
				if(res.getString("hylevel").equals("1")) {
					hang.add("普通会员");
				}
				else {
					hang.add("高级会员");
				}
				hang.add(res.getString("phonenum"));
				rowData.add(hang);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		tableModel=new DefaultTableModel(rowData,columnNames);
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

}

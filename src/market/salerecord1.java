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
public class salerecord1 extends JDialog{
	String[] border1= {BorderLayout.CENTER,BorderLayout.NORTH,BorderLayout.SOUTH,BorderLayout.WEST,BorderLayout.EAST};
	private DefaultTableModel tableModel;
	private MTable table;
	Connection con;
	Statement sql;
	ResultSet res;
	PreparedStatement sql1;
	JComboBox jc=new JComboBox(new Year());
	JComboBox jc1=new JComboBox(new Month());
	JComboBox jc2=new JComboBox(new Day());
	JButton delete=new JButton("删除");
	public salerecord1() {
		super(new MyFrame(),"销售记录");
		Container container=getContentPane();
		this.setLayout(new BorderLayout());
		
		JScrollPane sp=new JScrollPane();
		container.add(border1[0],sp);
		 JPanel panel=new JPanel();
			container.add(border1[2],panel);
			panel.add(new JLabel("年："));
			panel.add(jc);
			panel.add(new JLabel("月："));
			panel.add(jc1);
			panel.add(new JLabel("日："));
			panel.add(jc2);
			panel.add(delete);
			
			
		//数据库查询
		Database selectData=new Database();
		
		 Vector rowData,columnNames;
			columnNames=new Vector();
			columnNames.add("销售时间");
			columnNames.add("销售序号");
			columnNames.add("商品编号");
			columnNames.add("商品名");
			columnNames.add("商品价格");
			columnNames.add("商品数量");
			columnNames.add("会员卡号");
			columnNames.add("收银员号");
			columnNames.add("会员折扣");
			rowData=new Vector();
			con=selectData.getConnection();
			try {
				sql1=con.prepareStatement("select xssj,xsxh,spbh,sppm,spjg,spsl,hykh,syyg,hyzk from xsjl");
				res=sql1.executeQuery();
				
				while(res.next()) {
					
							
					Vector hang=new Vector();
					 String year=res.getString("xssj").substring(0, 4);
					 String month=res.getString("xssj").substring(4, 6);
					 String day=res.getString("xssj").substring(6, 8);
					 String display=year+"-"+month+"-"+day;
					     hang.add(display);
					 hang.add(res.getString("xsxh"));
						hang.add(res.getString("spbh"));
						hang.add(res.getString("sppm"));
						hang.add(res.getString("spjg"));
						hang.add(res.getString("spsl"));
						hang.add(res.getString("hykh"));
						hang.add(res.getString("syyg"));
						hang.add(res.getString("hyzk"));
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
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String m=(String)jc.getSelectedItem();
					 String m1=(String)jc1.getSelectedItem();
					 String m2=(String)jc2.getSelectedItem();
					 int option=JOptionPane.showConfirmDialog(null, "确认删除？", "警告",JOptionPane.YES_OPTION);
				      if(option==JOptionPane.YES_OPTION) 
				      {
				    	  selectdelete(m,m1,m2);
				      }
				      else {
				    	  return;
				      }
					
				    Vector rowdata=new Vector();
					con=selectData.getConnection();
					try {
						sql1=con.prepareStatement("select xssj,xsxh,spbh,sppm,spjg,spsl,hykh,syyg,hyzk from xsjl");
						res=sql1.executeQuery();
						System.out.println("liuson");
						while(res.next()) {
							System.out.println("liuson");
									
							Vector hang=new Vector();
							 String year=res.getString("xssj").substring(0, 4);
							 String month=res.getString("xssj").substring(4, 6);
							 String day=res.getString("xssj").substring(6, 8);
							 String display=year+"-"+month+"-"+day;
							hang.add(display);
							hang.add(res.getString("xsxh"));
							hang.add(res.getString("spbh"));
							hang.add(res.getString("sppm"));
							hang.add(res.getString("spjg"));
							hang.add(res.getString("spsl"));
							hang.add(res.getString("hykh"));
							hang.add(res.getString("syyg"));
							hang.add(res.getString("hyzk"));
							rowdata.add(hang);
						}
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					tableModel=new DefaultTableModel(rowdata,columnNames);
					table=new MTable(tableModel);
					table.setRowSorter(new TableRowSorter(tableModel));
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					sp.setViewportView(table);
				}
			});
			
			Toolkit theKit=this.getToolkit();
			Dimension screenSize=theKit.getScreenSize();
			int centerX=screenSize.width/2;
			int centerY=screenSize.height/2;
			this.setSize(800, 600);
			this.setLocation(centerX-400, centerY-350);
	}
	public void selectdelete(String year,String month,String day) {
		Database selectData=new Database();
		con=selectData.getConnection();
		
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select xssj,xsxh,spbh,spjg,spsl,hykh from xsjl");
			while(res.next()) {
				System.out.println(res.getString("xssj").substring(0, 4));
				 String year1=res.getString("xssj").substring(0, 4);
				 String month1=res.getString("xssj").substring(4, 6);
				 String day1=res.getString("xssj").substring(6, 8);
			     int y=Integer.parseInt(year);
			     int m=Integer.parseInt(month);
			     int d=Integer.parseInt(day);
				int y1=Integer.parseInt(year1);
				int m1=Integer.parseInt(month1);
				int d1=Integer.parseInt(day1);
				if(y1<y) {
					delete(res.getString("xssj"),res.getString("xsxh"),res.getString("spbh"));
				}
				else if(y1==y){
					if(m1<m) {
						delete(res.getString("xssj"),res.getString("xsxh"),res.getString("spbh"));
					}
					else if(m1==m) {
						if(d1<=d) {
							delete(res.getString("xssj"),res.getString("xsxh"),res.getString("spbh"));
						}
					}
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
    public void delete(String xssj,String xsxh,String spbh) {
    	Database selectData=new Database();
		con=selectData.getConnection();
		try {
			sql1=con.prepareStatement("delete from xsjl where xssj=? and xsxh=? and spbh=?");
			sql1.setString(1, xssj);
			sql1.setString(2, xsxh);
			sql1.setString(3, spbh);

			sql1.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
}

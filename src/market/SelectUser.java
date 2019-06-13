package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import market.Error;
import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
public class SelectUser extends JDialog{
	String[] border1= {BorderLayout.CENTER,BorderLayout.NORTH,BorderLayout.SOUTH,BorderLayout.WEST,BorderLayout.EAST};
	private DefaultTableModel tableModel;
	private  static MTable table;
	private JTextField aTextField;
	private JTextField bTextField;
	private JTextField cTextField;
	private JTextField dTextField;
	private JTextField eTextField;
	JLabel jlevel=new JLabel("请选择添加用户类型");
	JComboBox jc=new JComboBox(new Usertype());
	 Vector rowData,columnNames;
	 Vector temData;
	String id;
	Connection con;
	PreparedStatement sql;
	ResultSet res;
	public SelectUser() {
		super(new MyFrame(),"用户查询");
		jc.setToolTipText("请输入");
		Container container=getContentPane();
		this.setLayout(new BorderLayout());
		JScrollPane sp=new JScrollPane();
		JPanel north=new JPanel();
		container.add(border1[0],sp);
		container.add(border1[1],north);
		north.setLayout(new FlowLayout());
		
		
		JButton selectMember=new JButton("查询");
	
		columnNames=new Vector();
		columnNames.add("用户编号");
		columnNames.add("用户名");
		columnNames.add("用户密码");
		columnNames.add("用户权限");
		columnNames.add("备注");
		tableModel=new DefaultTableModel(rowData,columnNames);
		table=new MTable(tableModel);
		table.setRowSorter(new TableRowSorter(tableModel));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置表格的选择模式为单选
		sp.setViewportView(table);
		north.add(selectMember);
		 JPanel panel=new JPanel();
		container.add(border1[2],panel);
		panel.add(new JLabel("用户编号"));
		aTextField=new JTextField("",10);
		aTextField.setEditable(false);
		panel.add(aTextField);
		panel.add(new JLabel("用户名"));
		bTextField=new JTextField("",10);
		panel.add(bTextField);
		panel.add(new JLabel("用户密码"));
		cTextField=new JTextField("",10);
		panel.add(cTextField);
		panel.add(jlevel);
		dTextField=new JTextField("",10);
		panel.add(jc);
		panel.add(new JLabel("备注"));
		eTextField=new JTextField("",10);
		panel.add(eTextField);
		selectMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				temData=selectAll();
				
				tableModel=new DefaultTableModel(temData,columnNames);
				table=new MTable(tableModel);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置表格的选择模式为单选
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int selectedRow=table.getSelectedRow();
						Object oa=tableModel.getValueAt(selectedRow, 0);
						Object ob=tableModel.getValueAt(selectedRow, 1);
						Object oc=tableModel.getValueAt(selectedRow, 2);
						Object od=tableModel.getValueAt(selectedRow, 3);
						Object oe=tableModel.getValueAt(selectedRow, 4);
						aTextField.setText(oa.toString());
						bTextField.setText(ob.toString());
						cTextField.setText(oc.toString());
						dTextField.setText(od.toString());
						eTextField.setText(oe.toString());
					}
				});
				sp.setViewportView(table);
			
				
			}
		});
		final JButton updButton=new JButton("修改");
		updButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				if(selectedRow!=-1) {
					int selectedRow1=table.getSelectedRow();
				 String regex1="[a-zA-Z]{4,8}";
				 String regex2="\\d{1,4}";
				String regex3="\\d{11}";
				 if(bTextField.getText().matches(regex1)) {
					
						
							 String m=(String)jc.getSelectedItem();
							 String w="";
							if(m.equals("收银员")) {
								w="1";
							}
							else if(m.equals("入货员")) {
								w="2";
							}
							else if(m.equals("管理员")) {
								w="3";
							}
							int option=JOptionPane.showConfirmDialog(null, "确认修改？", "警告",JOptionPane.YES_OPTION);
						      if(option==JOptionPane.YES_OPTION) 
						      {
						    	  changeOne(aTextField.getText(),bTextField.getText(),cTextField.getText(),w,eTextField.getText());
						      }
						      else {
						    	  return;
						      }
							
					tableModel.setValueAt(aTextField.getText(),selectedRow, 0);
					tableModel.setValueAt(bTextField.getText(),selectedRow, 1);
					tableModel.setValueAt(cTextField.getText(),selectedRow, 2);
					tableModel.setValueAt(m,selectedRow, 3);
					tableModel.setValueAt(eTextField.getText(),selectedRow, 4);
						
					 
					 
				 }
				 else {
					 String error="输入有误，请输入四到八位字母";
						new Error(error).setVisible(true);
				 }
				}
				else {
					String error="请选择要修改的行";
					new Error(error).setVisible(true);
				}
			}
		});
		panel.add(updButton);
		final JButton delButton=new JButton("删除");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				if(selectedRow!=-1)
				{
					int option=JOptionPane.showConfirmDialog(null, "确认删除？", "警告",JOptionPane.YES_OPTION);
				      if(option==JOptionPane.YES_OPTION) 
				      {
				    	  deleteOne(aTextField.getText());
				      }
				      else {
				    	  return;
				      }
					
					tableModel.removeRow(selectedRow);
				}
				else {
					String error="请选择要删除的行";
					new Error(error).setVisible(true);
				}
			}
		});
		panel.add(delButton);
		
		Toolkit theKit=this.getToolkit();
		Dimension screenSize=theKit.getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		this.setSize(1100, 600);
		this.setLocation(centerX-550, centerY-350);
	}
	public Vector selectAll() {
		Database selectData=new Database();
		 
			rowData=new Vector();
		con=selectData.getConnection();
		try {
			sql=con.prepareStatement("select  yhid,yhname,yhsecret,yhqx,bz from yhgl");
			res=sql.executeQuery();
			while(res.next()) {
				Vector hang=new Vector();
				hang.add(res.getString("yhid"));
				hang.add(res.getString("yhname"));
				
				 SetSecret ss=new SetSecret();
				String dec = ss.decode(res.getString("yhsecret"));
				hang.add(dec);
				if(res.getString("yhqx").equals("1")) {
					hang.add("收银员");
				}
				else if(res.getString("yhqx").equals("2")){
					hang.add("入货员");
				}
				else
				{
					hang.add("管理员");
				}
				hang.add(res.getString("bz"));
				rowData.add(hang);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowData;
	}
	public void changeOne(String yhid,String yhname,String yhsecret,String yhqx,String bz) {
		Database selectData=new Database();
		con=selectData.getConnection();
		
	try {
		sql=con.prepareStatement("update yhgl set yhname=?,yhsecret=?,yhqx=?,bz=? where yhid=?");
		sql.setString(1, yhname);
		SetSecret ss=new SetSecret();
		String dec = ss.encode(yhsecret);
		sql.setString(2, dec);
		sql.setString(3, yhqx);
		sql.setString(4, bz);
		sql.setString(5, yhid);
		sql.executeUpdate();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	public void deleteOne(String yhid) {
		Database selectData=new Database();
		con=selectData.getConnection();
		try {
			sql=con.prepareStatement("delete from yhgl where yhid=?");
			sql.setString(1, yhid);

			sql.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
   
}

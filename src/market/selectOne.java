package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
public class selectOne extends JDialog{
	String[] border1= {BorderLayout.CENTER,BorderLayout.NORTH,BorderLayout.SOUTH,BorderLayout.WEST,BorderLayout.EAST};
	private DefaultTableModel tableModel;
	private  static MTable table;
	private JTextField aTextField;
	private JTextField bTextField;
	private JTextField cTextField;
	private JTextField dTextField;
	private JTextField eTextField;
	JLabel jlevel=new JLabel("��ѡ���޸Ļ�Ա����");
	JComboBox jc=new JComboBox(new MemberType());
	 Vector rowData,columnNames;
	 Vector temData;
	String id;
	Connection con;
	PreparedStatement sql;
	ResultSet res;
	public selectOne() {
		super(new MyFrame(),"��Ա������ѯ");
		jc.setToolTipText("������");
		Container container=getContentPane();
		this.setLayout(new BorderLayout());
		JScrollPane sp=new JScrollPane();
		JPanel north=new JPanel();
		container.add(border1[0],sp);
		container.add(border1[1],north);
		north.setLayout(new FlowLayout());
		JTextField jt=new JTextField(20);
		JLabel lb=new JLabel("��Աid:");
		JButton selectMember=new JButton("��ѯ");
		columnNames=new Vector();
		columnNames.add("��Ա��");
		columnNames.add("��Ա��");
		columnNames.add("��Ա����");
		columnNames.add("��Ա�ȼ�");
		columnNames.add("�绰����");
		tableModel=new DefaultTableModel(rowData,columnNames);
		table=new MTable(tableModel);
		table.setRowSorter(new TableRowSorter(tableModel));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//���ñ���ѡ��ģʽΪ��ѡ
		sp.setViewportView(table);
		north.add(lb);
		north.add(jt);
		north.add(selectMember);
		 JPanel panel=new JPanel();
		container.add(border1[2],panel);
		panel.add(new JLabel("��Ա��"));
		aTextField=new JTextField("",10);
		aTextField.setEditable(false);
		panel.add(aTextField);
		panel.add(new JLabel("��Ա��"));
		bTextField=new JTextField("",10);
		panel.add(bTextField);
		panel.add(new JLabel("��Ա����"));
		cTextField=new JTextField("",10);
		panel.add(cTextField);
		panel.add(jlevel);
		dTextField=new JTextField("",10);
		panel.add(jc);
		panel.add(new JLabel("�绰����"));
		eTextField=new JTextField("",10);
		panel.add(eTextField);
		selectMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id=jt.getText();
				String regex="\\d{4}";
				if(id.matches(regex)) {
				temData=selectOne();
				tableModel=new DefaultTableModel(temData,columnNames);
				table=new MTable(tableModel);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//���ñ���ѡ��ģʽΪ��ѡ
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
						cTextField.setText(oa.toString());
						dTextField.setText(od.toString());
						eTextField.setText(oe.toString());
					}
				});
				sp.setViewportView(table);
			}
				else
				{
					String error="����������������λ����";
					new Error(error).setVisible(true);
				}
			}
		});
		final JButton updButton=new JButton("�޸�");
		updButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				if(selectedRow!=-1) {
					int selectedRow1=table.getSelectedRow();
				 String regex1="[a-zA-Z]{4,8}";
				 String regex2="\\d{1,4}";
				String regex3="\\d{11}";
				 if(bTextField.getText().matches(regex1)) {
					 if(cTextField.getText().matches(regex2)) {
						 if(eTextField.getText().matches(regex3)) {
							 String m=(String)jc.getSelectedItem();
							 String n;
							 if(m.equals("��ͨ��Ա")) {
								 n="1";
							 }
							 else
							 {
								 n="2";
							 }
							 int option=JOptionPane.showConfirmDialog(null, "ȷ���޸ģ�", "����",JOptionPane.YES_OPTION);
						      if(option==JOptionPane.YES_OPTION) 
						      {
						    	  changeOne(aTextField.getText(),bTextField.getText(),cTextField.getText(),n,eTextField.getText());
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
						 else
						 {
							 String error="�绰���� �������󣬱���11λ����";
								new Error(error).setVisible(true);
						 }
					 }
					 else {
						 String error="�����������󣬱�������λ���ڵ�����";
							new Error(error).setVisible(true);
					 }
				 }
				 else {
					 String error="��Ա�����������������ĵ���λ��ĸ";
						new Error(error).setVisible(true);
				 }
				}
				else
				{
					String error="��ѡ��Ҫ�޸ĵ���";
					new Error(error).setVisible(true);
				}
			}
		});
		panel.add(updButton);
		final JButton delButton=new JButton("ɾ��");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=table.getSelectedRow();
				if(selectedRow!=-1)
				{
					 int option=JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "����",JOptionPane.YES_OPTION);
				      if(option==JOptionPane.YES_OPTION) 
				      {
				    	  deleteOne(aTextField.getText());
				      }
				      else {
				    	  return;
				      }
					deleteOne(aTextField.getText());
					tableModel.removeRow(selectedRow);
				}
				else {
					String error="��ѡ��Ҫɾ������";
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
	public Vector selectOne() {
		Database selectData=new Database();
		
			columnNames=new Vector();
			columnNames.add("��Ա��");
			columnNames.add("��Ա��");
			columnNames.add("��Ա����");
			columnNames.add("��Ա�ȼ�");
			columnNames.add("�绰����");
			rowData=new Vector();
		con=selectData.getConnection();
		try {
			sql=con.prepareStatement("select hyid,hyname,hygrade,hylevel,phonenum from hyxx where hyid=?");
			sql.setString(1, id);
			res=sql.executeQuery();
			while(res.next()) {
				Vector hang=new Vector();
				hang.add(res.getString("hyid"));
				hang.add(res.getString("hyname"));
				hang.add(res.getString("hygrade"));
				if(res.getString("hylevel").equals("1")) {
					hang.add("��ͨ��Ա");
				}
				else {
					hang.add("�߼���Ա");
				}
				hang.add(res.getString("phonenum"));
				rowData.add(hang);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowData;
	}
	public void changeOne(String hyid,String hyname,String hygrade,String hylevel,String hynumber) {
		Database selectData=new Database();
		
		
	try {
		sql=con.prepareStatement("update hyxx set hyname=?,hygrade=?,hylevel=?,phonenum=? where hyid=?");
		sql.setString(1, hyname);
		sql.setString(2, hygrade);
		sql.setString(3, hylevel);
		sql.setString(4, hynumber);
		sql.setString(5, hyid);
		sql.executeUpdate();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	public void deleteOne(String hyid) {
		Database selectData=new Database();
		con=selectData.getConnection();
		try {
			sql=con.prepareStatement("delete from hyxx where hyid=?");
			sql.setString(1, hyid);

			sql.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

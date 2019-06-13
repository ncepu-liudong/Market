package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.util.Date;
import java.util.Vector;
public class MTable extends JTable{

	public MTable(Vector rowData,Vector columnNames) {
		super(rowData,columnNames);
	}
	public MTable(DefaultTableModel tableModel) {
		super(tableModel);
	}
	@Override
	public JTableHeader getTableHeader() {
		JTableHeader tableheader=super.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		DefaultTableCellRenderer hr=(DefaultTableCellRenderer)tableHeader.getDefaultRenderer();
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		return tableHeader;
		
	}
	@Override
	public TableCellRenderer getDefaultRenderer(Class<?>columnsClass) {
		DefaultTableCellRenderer cr=(DefaultTableCellRenderer)super.getDefaultRenderer(columnsClass);
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		return cr;
	}
	@Override
	public boolean isCellEditable(int row,int column) {
		return false;
	}

}

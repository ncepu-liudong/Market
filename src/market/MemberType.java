package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTabbedPane;
import java.net.URL;
import java.util.Date;
public class MemberType extends AbstractListModel implements ComboBoxModel{
    String selecteditem=null;
    String[]test= {"普通会员","高级会员"};
    public Object getElementAt(int index) {
    	return test[index];
    }
    public int getSize() {
    	return test.length;
    }
    public void setSelectedItem(Object item) {
    	selecteditem=(String)item;
    }
    public Object getSelectedItem() {
    	return selecteditem;
    }
}

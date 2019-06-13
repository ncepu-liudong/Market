package market;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
public class Day extends AbstractListModel implements ComboBoxModel{

	String selecteditem=null;
	static String[]test= {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
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

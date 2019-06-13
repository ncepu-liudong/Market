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
public class Mounth extends AbstractListModel implements ComboBoxModel{

	String selecteditem=null;
	static String[]test= {"01","02","03","04","05","06","07","08","09","10","11","12"
	};
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

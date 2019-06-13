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
public class Year extends AbstractListModel implements ComboBoxModel{
	String selecteditem=null;
	 int year=1980;
	 String[]test= {"1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029"};
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

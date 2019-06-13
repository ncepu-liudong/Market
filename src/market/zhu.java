package market;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;  
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JMenu;  
import javax.swing.JMenuBar;  
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;    
public class zhu extends JFrame implements ActionListener {  
    JMenuBar MenuBar;  
    JMenu menu, menu2, menu3, menu4, menu5;  
    JMenuItem item1, item2,itema, item3, item4, item5, item6,  item7, item8, item9, item0;  
    
    ImageIcon image = new ImageIcon("D:\\Users\\����\\eclipse-workspace\\Market\\3.jpg");  
    Image icon = new ImageIcon("image/icon.png").getImage();  
     JLabel label;
    
     Dimension dimension = new Dimension(image.getIconWidth(), image.getIconHeight()); 
    public zhu() {
    	
        init();
        
       
        setSize(dimension);
        setIconImage(icon);
       

        
        setForeground(Color.yellow);  
        setLocationRelativeTo(null);  
        setVisible(true);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
    }  
  
    @SuppressWarnings("deprecation")
	void init() {  
        setTitle("������ϵͳ");  
        setBackground(new Color(212, 232, 248));  
        label = new JLabel(image);
        label.setBounds(0, 0,image.getIconWidth(), image.getIconHeight());  
  
        MenuBar = new JMenuBar();  
        MenuBar.setBackground(new Color(212, 232, 248));  
        menu = new JMenu("��Ʒ��Ϣ��ѯ ");  
        menu.setFont(new Font("����", Font.PLAIN, 16));  
        item1 = new JMenuItem("����Ų�ѯ");  
        item1.setFont(new Font("����", Font.PLAIN, 16));  
        item2 = new JMenuItem("����Ʒ����ѯ");  
        item2.setFont(new Font("����", Font.PLAIN, 16));  
        itema = new JMenuItem("ɾ����Ʒ");  
        itema.setFont(new Font("����", Font.PLAIN, 16));  
        item1.addActionListener(this);  
        item2.addActionListener(this); 
        itema.addActionListener(this);  
       
        menu.add(item1);  
        menu.add(item2);  
        
        menu2 = new JMenu("���������");  
        menu2.setFont(new Font("����", Font.PLAIN, 16));  
        item3 = new JMenuItem("��������Ʒ");  
        item4 = new JMenuItem("�����Ʒͳ��");  
        item3.setFont(new Font("����", Font.PLAIN, 16));  
        item3.addActionListener(this);  
        item4.setFont(new Font("����", Font.PLAIN, 16));  
        item4.addActionListener(this);  
  
        menu2.add(item3);  
        menu2.add(item4);  
  
        menu3 = new JMenu("��Ʒͳ�Ʒ���");  
        menu3.setFont(new Font("����", Font.PLAIN, 16));  
        item5 = new JMenuItem("��Ʒ���");  
        item6 = new JMenuItem("��Ʒ����ͳ��");  
        item5.setFont(new Font("����", Font.PLAIN, 16));  
        item6.setFont(new Font("����", Font.PLAIN, 16));  
        item5.addActionListener(this);  
        item6.addActionListener(this);  
  
        menu3.add(item5);  
        menu3.add(item6);  
  
        menu4 = new JMenu("�������");  
        menu4.setFont(new Font("����", Font.PLAIN, 16));  
        item7 = new JMenuItem("��ӳ�����Ʒ");  
        item8 = new JMenuItem("������Ʒͳ��");  
       
        item7.setFont(new Font("����", Font.PLAIN, 16));  
        item8.setFont(new Font("����", Font.PLAIN, 16));  
       
        item7.addActionListener(this);  
        item8.addActionListener(this);  
       
  
        menu4.add(item7);  
        menu4.add(item8);  
       
  
        menu5 = new JMenu("�������˳�");  
        menu5.setFont(new Font("����", Font.PLAIN, 16));  
        item9 = new JMenuItem("�����ĵ�");  
        item0 = new JMenuItem("�˳�ϵͳ");  
         
        item9.setFont(new Font("����", Font.PLAIN, 16));  
        item0.setFont(new Font("����", Font.PLAIN, 16));  
      
        item9.addActionListener(this);  
        item0.addActionListener(this);  
        
  
        menu5.add(item9);  
        menu5.add(item0);  
        
  
        MenuBar.add(menu);  
        MenuBar.add(menu2);  
        MenuBar.add(menu3);  
        MenuBar.add(menu4);  
        MenuBar.add(menu5); 
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false); 
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  
        setVisible(true);
        setJMenuBar(MenuBar);  
    }  
  
    public static void main(String[] args) {  
        new zhu();  
    }  
  
    public void actionPerformed(ActionEvent e) {  
        if (e.getSource() == item1) {  
            biaohaocha a1=new biaohaocha(); 
            
        }  
        if (e.getSource() == item2) {  
            new mingchengcha();  
            
        } 
        if (e.getSource() == itema) {  
            
            new shanchu();
        }  
        if (e.getSource() == item3) {  
        	 ruku a=new ruku();
             
            
        }  
        if (e.getSource() == item4) {  
          new biao();
            
        }  
        if (e.getSource() == item5) {  
           new ll();
           
            
        }  
        if (e.getSource() == item6) {  
          new jilu();
            
        }  
        if (e.getSource() == item7) {  
          chuku b=new chuku();
             
        }  
        if (e.getSource() == item8) {  
            new yu();
            
        }  
       
        if (e.getSource() == item9) {  
           try {
        	   Process  b=Runtime.getRuntime().exec("cmd.exe /c start D:\\Users\\����\\eclipse-workspace\\Market\\help.pdf");
        	   
           }catch(Exception a){
        	   a.printStackTrace();
        	   
           }
             
        }  
        if (e.getSource() == item0) { 
      
      int option=JOptionPane.showConfirmDialog(null, "�Ƿ��˳�ϵͳ", "����",JOptionPane.YES_OPTION);
      if(option==JOptionPane.YES_OPTION) 
      {
    	  System.exit(0);
      }
      else {
    	  return;
      }
             
        }  
       
    }  
}   
package market;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import java.sql.Date.*;

public class sq {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	public Connection getConnection() {
		
		try {
			
		Class.forName("oracle.jdbc.driver.OracleDriver"); //�������ݿ�����
		System.out.println("���ݿ��������سɹ���");	 //�������Ϣ
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";	//��ȡ����URL
		String user = "liudong"; //�����û���
		String password = "5692573"; //��������
		 conn = DriverManager.getConnection(url, user, password); //��ȡ���ݿ�����
		if (conn != null) {
		System.out.println("�ɹ�����Oracle���ݿ⽨�����ӣ���");

		}


		} catch (Exception e) {
		e.printStackTrace();
		} 
		return conn; //����Connectionʵ�� 
		}
	public void AddData(String spbh , String sppm, String dj  ,String sl ,String scr ,String bzq ,String bz ) {try { 
	        conn = getConnection();
	        int count=0;
	        String sql1 = "select spbh from sp where 1 = 1";
	        String sql = "insert into sp values(?,?,?,?,?,?,?) ";
	        pstm = conn.prepareStatement(sql1);
            rs = pstm.executeQuery();
            while (rs.next()) {
               if(rs.getString("spbh").equals(spbh))
               {
            	   count++;
               }
                
            }
	           if(count==0) { // �������ݿ�student������������
	            pstm = conn.prepareStatement(sql);
	            
	            // ִ�в������ݲ���
	            java.util.Date  date = new java.util.Date();

	            SimpleDateFormat simpleDateFormat =(SimpleDateFormat)DateFormat.getDateInstance();

	            simpleDateFormat.applyPattern("yyyy-MM-dd");

	            try {
					date = simpleDateFormat.parse(scr );
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                java.sql.Date date1 =new java.sql.Date(date.getTime());
			    pstm.setString(1, spbh);
	            pstm.setString(2, sppm);
	            pstm.setString(3, sl);
	            pstm.setDate(4, date1);
	            pstm.setString(5, bzq);
	            pstm.setString(6, dj);
	            pstm.setString(7, bz);
	            pstm.executeUpdate();
	            JOptionPane.showMessageDialog(null, "���ɹ�", "��ʾ",JOptionPane.YES_OPTION);
	           }
	           else
	           {
	        	   JOptionPane.showMessageDialog(null, "�ñ���Ѵ���", "��ʾ",JOptionPane.YES_OPTION);   
	           }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        
			} finally {
	            ReleaseResource();
	        }
	    }
	public void AddData1(String riqi,String spbh , String sppm, String dj  ,String sl ,String scr ,String bzq ,String bz ) {try { 
        conn = getConnection();
        String sq2 = "select spbh from  sp   ";
       
        String sql = "insert into sp1 values(?,?,?,?,?,?,?,?) ";
        int count=0;
        pstm = conn.prepareStatement(sq2);
        rs = pstm.executeQuery();
        while (rs.next()) {
           if(rs.getString("spbh").equals(spbh))
           {
        	   count++;
           }
            
        }
      
          // �������ݿ�student������������
            pstm = conn.prepareStatement(sql);
            
            // ִ�в������ݲ���
            java.util.Date  date = new java.util.Date();
            java.util.Date  date2 = new java.util.Date();
            SimpleDateFormat simpleDateFormat =(SimpleDateFormat)DateFormat.getDateInstance();
            simpleDateFormat.applyPattern("yyyy-MM-dd");

            try {
				date = simpleDateFormat.parse(scr );
				date2 = simpleDateFormat.parse(riqi );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            java.sql.Date date1 =new java.sql.Date(date.getTime());
            java.sql.Date date3 =new java.sql.Date(date2.getTime());
            System.out.println(date1);
		    pstm.setDate(1, date3);
            pstm.setString(2, spbh);
            pstm.setString(3, sppm);
            pstm.setString(4, sl);
            pstm.setDate(5, date1);
            pstm.setString(6, bzq);
            pstm.setString(7, dj);
            pstm.setString(8, bz);
            pstm.executeUpdate();
            
       
        
        } catch (SQLException e) {
            e.printStackTrace();
        
		} finally {
            ReleaseResource();
        }
    }   
	public void AddData2(String riqi,String spbh , String sppm, String dj  ,String sl ,String scr ,String bzq ,String bz ) {try { 
        conn = getConnection();
        
        String sq2 = "select spbh from  sp   ";
        String sql = "insert into sp2 values(?,?,?,?,?,?,?,?) ";
        int count=0;
        pstm = conn.prepareStatement(sq2);
        rs = pstm.executeQuery();
        while (rs.next()) {
           if(rs.getString("spbh").equals(spbh))
           {
        	   count++;
           }
            
        }
        
          // �������ݿ�student������������
            pstm = conn.prepareStatement(sql);
            
            // ִ�в������ݲ���
            java.util.Date  date = new java.util.Date();
            java.util.Date  date2 = new java.util.Date();
            SimpleDateFormat simpleDateFormat =(SimpleDateFormat)DateFormat.getDateInstance();

            simpleDateFormat.applyPattern("yyyy-MM-dd");

            try {
				date = simpleDateFormat.parse(scr );
				date2 = simpleDateFormat.parse(riqi );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            java.sql.Date date1 =new java.sql.Date(date.getTime());
            java.sql.Date date3 =new java.sql.Date(date2.getTime());
		    pstm.setDate(1, date3);
            pstm.setString(2, spbh);
            pstm.setString(3, sppm);
            pstm.setString(4, sl);
            pstm.setDate(5, date1);
            pstm.setString(6, bzq);
            pstm.setString(7, dj);
            pstm.setString(8, bz);
            pstm.executeUpdate();
            
         
        } catch (SQLException e) {
            e.printStackTrace();
        
		} finally {
            ReleaseResource();
        }
    }   
	 
	 public void SelectData() {
		     
	        conn = getConnection();
	        String sql = "select spbh,sppm,sl,scrq,bzq,dj,bz from  sp   ";
	        try {
	            pstm = conn.prepareStatement(sql);
	            rs = pstm.executeQuery();
	           
	            while (rs.next()) {
	            
	                String spbh = rs.getString("spbh");
	                String sppm = rs.getString("sppm");
	                String sl =   rs.getString("sl");
	                String scrp = rs.getString("scrq");
	                String bzq =  rs.getString("bzq");
	                String dj =   rs.getString("dj");
	                String bz =   rs.getString("bz");
	               
	                System.out.println(spbh + "\t" + sppm + "\t" + sl + "\t" + scrp + "\t" + bzq+ "\t" + dj+ "\t" + bz);
	            }
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            ReleaseResource();
	        }
	    }
	 public void update(String spbh , String sppm, String dj  ,String sl ,String scr ,String bzq ,String bz) {
		 java.util.Date  date = new java.util.Date();

         SimpleDateFormat simpleDateFormat =(SimpleDateFormat)DateFormat.getDateInstance();

         simpleDateFormat.applyPattern("yyyy-MM-dd");

         try {
				date = simpleDateFormat.parse(scr );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         java.sql.Date date1 =new java.sql.Date(date.getTime());
	        conn = getConnection();
	        String sql1 = "select spbh,sppm,sl,scrq,bzq,dj,bz from  sp     ";
	        String sql = "update sp set sppm=?, sl=?, scrq=?, bzq=?, dj=?, bz=?  where spbh=?   ";
	        try {  
	        pstm = conn.prepareStatement(sql1);
	    	rs = pstm.executeQuery();
	       
	       
	             while (rs!=null && rs.next()) {
	                if(rs.getString("spbh").equals(spbh))
	                {
	                	  pstm = conn.prepareStatement(sql);
		       	            pstm.setString(1, sppm);
		 	                pstm.setString(2, sl);
		 	                pstm.setDate(3, date1);
		 	                pstm.setString(4, bzq);
		 	                pstm.setString(5, dj);
		 	                pstm.setString(6, bz);
		 	                pstm.setString(7, spbh);
		 	                pstm.executeUpdate();
		 	               JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ",JOptionPane.YES_OPTION); 
	        	  
	                }   
	           
	            
	            
	            	  
	             }
	             
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            ReleaseResource();
	        }
	    }
	 public void update1(String spbh , String sppm, String dj  ,String sl ,String scr ,String bzq ,String bz) {
		 java.util.Date  date = new java.util.Date();

         SimpleDateFormat simpleDateFormat =(SimpleDateFormat)DateFormat.getDateInstance();

         simpleDateFormat.applyPattern("yyyy-MM-dd");

         try {
				date = simpleDateFormat.parse(scr );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         java.sql.Date date1 =new java.sql.Date(date.getTime());
	        conn = getConnection();
	        String sql1 = "select spbh,sppm,sl,scrq,bzq,dj,bz from  sp1     ";
	        String sql = "update sp1 set sppm=?, sl=?, scrq=?, bzq=?, dj=?, bz=?  where spbh=?   ";
	        try {  
	        pstm = conn.prepareStatement(sql1);
	    	rs = pstm.executeQuery();
	       
	       
	             while (rs!=null && rs.next()) {
	                if(rs.getString("spbh").equals(spbh))
	                {
	                	  pstm = conn.prepareStatement(sql);
		       	            pstm.setString(1, sppm);
		 	                pstm.setString(2, sl);
		 	                pstm.setDate(3, date1);
		 	                pstm.setString(4, bzq);
		 	                pstm.setString(5, dj);
		 	                pstm.setString(6, bz);
		 	                pstm.setString(7, spbh);
		 	                pstm.executeUpdate();
		 	               
	                }   
	           
	            
	            
	            	  
	             }
	             
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            ReleaseResource();
	        }
	    }
	 public void chashu(String spbh) {
		
		 try {
		 conn = getConnection();
		 String sql = "select spbh from  sp   ";
		 pstm = conn.prepareStatement(sql);
	    	rs = pstm.executeQuery();
	    	
	    	while(rs.next()) {
	    		  if(rs.getString("spbh").equals(spbh))
	                {
	    			  String sl=rs.getString("sl");
	    			 
	    			  
	                }
	    	}
	    		
	 }
	 catch (SQLException e) {
         e.printStackTrace();
     
		}
	 
	 finally {
         ReleaseResource();
     }
		
	
 } 
public void shan(String spbh) {
		
	 try {
	 conn = getConnection();
	 
	 String sql = "select spbh from  sp   ";
	 String sq2 = "delete  from  sp  where spbh=? ";
	 pstm = conn.prepareStatement(sql);
    	rs = pstm.executeQuery();
    	int count=0;
    	while(rs.next()) {
    		  if(rs.getString("spbh").equals(spbh))
                {
    			 count++;
    			 
    			  
                }
    	}
    	if(count==0) {

       	 JOptionPane.showMessageDialog(null, "δ�鵽���", "��ʾ",JOptionPane.YES_OPTION);
    	}
    	else {
    		 pstm = conn.prepareStatement(sq2);
    		 pstm.setString(1, spbh);
             pstm.executeUpdate();
             JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ",JOptionPane.YES_OPTION);
    	}
    		
 }
 catch (SQLException e) {
     e.printStackTrace();
 
	}
 
 finally {
     ReleaseResource();
 }
	

}
	 public void DeleteData(String spbh , String sppm, String dj  ,String sl ,String scr ,String bzq ,String bz) {try {
	        conn = getConnection();
	        String sql1 = "select spbh,sl from  sp   ";
	        String sql = "update sp set sl=?where spbh=?";
	        int count =0;
	        pstm = conn.prepareStatement(sql1);
	    	rs = pstm.executeQuery();
	       
	       
	             while (rs!=null && rs.next()) {
	                if(rs.getString("spbh").equals(spbh))
	                {
	                	count++;
	                	String sl1=rs.getString("sl");
	                	  int i=Integer.parseInt(sl); 
	         	          int j=Integer.parseInt(sl1);
	         	          int c=j-i;
	         	          if(c>=0) {
	         	         String str=String.valueOf(c); 
	         	        System.out.println(str);
	         	        pstm = conn.prepareStatement(sql);
	         	       pstm.setString(1, str);
	   	                pstm.setString(2, spbh);
	   	                 
	   	                pstm.executeUpdate();
	        	           
	        	           JOptionPane.showMessageDialog(null, "����ɹ�", "��ʾ",JOptionPane.YES_OPTION);
	                }
	         	          else {
	         	        	 JOptionPane.showMessageDialog(null, "�����������ڿ����", "��ʾ",JOptionPane.YES_OPTION); 
	         	          }
	                }
	                 
	             }
	             if(count==0)
	             {
	            	 JOptionPane.showMessageDialog(null, "δ�鵽���", "��ʾ",JOptionPane.YES_OPTION); 
	             }
	             else {

                	
	             }
	            // ִ��ɾ�����ݲ���
	           
	 } catch (SQLException e) {
         e.printStackTrace();
     
		} finally {
         ReleaseResource();
     }
 }
	    
	 public void DeleteData1(String a, String b, String c  ) {try {
	        conn = getConnection();
	        String sql1 = "select riqi from  sp1  ";
	        String sql = "delete  from  sp1  where riqi=?";
	        int count =0;
	        pstm = conn.prepareStatement(sql1);
	    	rs = pstm.executeQuery();
	    	 int h=Integer.parseInt(a); 
	    	 int l=Integer.parseInt(b); 
	    	 int n=Integer.parseInt(c); 
	       
	             while (rs!=null && rs.next())
	             {
	              rs.getDate("riqi");
	              SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	              String str=sdf.format(rs.getDate("riqi"));
	              System.out.println(str);
	              String year=str.substring(0, 4);
				  String month=str.substring(5, 7);
				  String day=str.substring(8, 10);
					 System.out.println(year);
					 System.out.println(month);
					 System.out.println(day);
					 int i=Integer.parseInt(year); 
					 int j=Integer.parseInt(month);
					 int k=Integer.parseInt(day); 
					 if(h>i) {
						 pstm = conn.prepareStatement(sql);
						 pstm.setDate(1, rs.getDate("riqi"));
			             pstm.executeUpdate();
					 }
					 else {
						 if(l>j) {
							 pstm = conn.prepareStatement(sql);
							 pstm.setDate(1, rs.getDate("riqi"));
				             pstm.executeUpdate();
						 }
						 else {
							 if(n>k) {
								 pstm = conn.prepareStatement(sql);
								 pstm.setDate(1, rs.getDate("riqi"));
					             pstm.executeUpdate();
							 }
							
						 }
					 }
					 
	             }    
	        	          
	
	 
	                 
	             
	           
	            // ִ��ɾ�����ݲ���
	           
	 } catch (SQLException e) {
      e.printStackTrace();
  
		} finally {
      ReleaseResource();
  }
}
	
public void ReleaseResource() {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if (pstm != null) {
        try {
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}

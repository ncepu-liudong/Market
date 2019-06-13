package market;
import java.lang.String;
public class Member {

	private String hyid;
	private String hyname;
	private String hygrade;
	private String hylevel;
	private String phonenum;
	public Member() {
		
	}
	public Member(String hyid,String hyname,String hygrade,String hylevel,String phonenum) {
		this.hyid=hyid;
		this.hyname=hyname;
		this.hygrade=hygrade;
		this.hylevel=hylevel;
		this.phonenum=phonenum;
	}
    public String gethyid() {
    	return hyid;
    }
    public void sethyid(String hyid) {
    	this.hyid=hyid;
    }
    public String hyname() {
    	return hyname;
    }
    public void hyname(String hygrade) {
    	this.hygrade=hygrade;
    }
    public String hygrade() {
    	return hygrade;
    }
    public void hygrade(String hygrade) {
    	this.hygrade=hygrade;
    }
    public String hylevel() {
    	return hylevel;
    }
    public void hylevel(String hylevel) {
    	this.hylevel=hylevel;
    }
    public String phonenum() {
    	return phonenum;
    }
    public void phonenum(String phonenum) {
    	this.phonenum=phonenum;
    }
}

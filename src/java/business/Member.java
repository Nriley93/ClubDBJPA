
package business;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author n.riley
 */
@Entity
@Table(name="tblMembers")
public class Member {
    @Id
    @Column(name="MemID")
    private String memid;
    
    @Column(name="LastName")
    private String lastname;
    
    @Column(name="FirstName")
    private String firstname;
    
    @Column(name="MiddleName")
    private String middlename;
    
    @Column(name="Status")
    private String status;
    
    @Column(name="MemDt")
    @Temporal(TemporalType.DATE)
    private Date memdt;
    
    @Column(name="Password")
    private long password;
    
    @Transient
    private long passattempt;
    
    public Member() {
        this.memid = "";
        this.lastname = "";
        this.firstname = "";
        this.middlename = "";
        this.status = "";
        this.memdt = null;
        this.password = 0;
        this.passattempt = -1;
    }
    public void setMemid(String memid) {this.memid = memid;}
    public void setLastname(String lastname) {this.lastname = lastname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}
    public void setMiddlename(String middlename){this.middlename = middlename;}
    public void setStatus(String status) {this.status = status;}
    public void setMemdt(Date memdt) {this.memdt = memdt;}
    public void setPassword(long password) {this.password = password;}
    public void setPassattempt(long passattempt){this.passattempt=passattempt;}    

    public String getMemid() {return memid;}
    public String getLastname() {return lastname;}
    public String getFirstname() {return firstname;}
    public String getMiddlename() {return middlename;}
    public String getStatus() {return status;}
    public Date getMemdt() {return memdt;}
    public String getMemdtS() { 
        return new SimpleDateFormat("MM-dd-yyyy").
            format(this.memdt);
    }
    public long getPassword() {return password;}
    public long getPassattempt() {return passattempt;}
    
    public boolean isAuthenticated() {
        if(this.password > 0) {
            if(this.password == this.passattempt) {
                   return true;
            }
        }
        return false;
    }
    
}

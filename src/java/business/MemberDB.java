
package business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author n.riley
 */
public class MemberDB {
    public static Member getMemberByID(String memid) {
        EntityManager em =
                DBUtil.getEmFactory().createEntityManager();
        try{
            Member m = em.find(Member.class, memid);
            return m;
        } catch(Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static String updtMember(Member m) {
        EntityManager em =
                DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        String msg="";
        
        try {
            trans.begin();
            em.merge(m);
            trans.commit();
            msg= "Member " + m.getMemid() + " updated.<br>";
        } catch(Exception e) {
            msg = "Error on Member Update: " + e.getMessage();
            trans.rollback();
        } finally {
            em.close();
        }
        return msg;
    }
}

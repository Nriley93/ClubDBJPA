
package business;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author n.riley
 */
public class PurchaseDB {
    public static List<Purchase> getPurchases(String memid) {
        EntityManager em = 
                DBUtil.getEmFactory().createEntityManager();
        String qs = "SELECT p FROM Purchase p " +
                    " WHERE p.memid = :memid " +
                    " ORDER BY p.purchasedt ";
        TypedQuery<Purchase> q = 
                em.createQuery(qs, Purchase.class);
        q.setParameter("memid", memid);
        
        List<Purchase> p = null;
        try{
            p = q.getResultList();
            if(p==null || p.isEmpty()) {
                p = null;
            }
        } catch(NoResultException e) {
            p = null;
        } finally {
            em.close();
        }
        return p;
    }
    
    public static List<Purchase> getPurchases(String memid, Date pd) {
        EntityManager em = 
                DBUtil.getEmFactory().createEntityManager();
        String qs = "SELECT p FROM Purchase p " +
                    " WHERE p.memid = :memid " +
                    " AND p.purchasedt >= :pd " +
                    " ORDER BY p.purchasedt ";
        TypedQuery<Purchase> q = em.createQuery(qs, Purchase.class);
        q.setParameter("memid", memid);
        q.setParameter("pd", pd);
        
        List<Purchase> p = null;
        try{
            p = q.getResultList();
            if(p==null || p.isEmpty()) {
                p = null;
            }
        } catch(NoResultException e) {
            p = null;
        } finally {
            em.close();
        }
        return p;
    }
}

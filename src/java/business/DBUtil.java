
package business;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author n.riley
 */
public class DBUtil {
    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("ClubDBJPAPU");
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}

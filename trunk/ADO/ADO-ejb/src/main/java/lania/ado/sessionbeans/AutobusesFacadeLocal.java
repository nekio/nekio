
package lania.ado.sessionbeans;

import java.util.List;
import javax.ejb.Local;
import lania.ado.entidades.Autobuses;

/**
 *
 * @author Nekio
 */
@Local
public interface AutobusesFacadeLocal {

    void create(Autobuses autobuses);

    void edit(Autobuses autobuses);

    void remove(Autobuses autobuses);

    Autobuses find(Object id);

    List<Autobuses> findAll();

    List<Autobuses> findRange(int[] range);

    int count();
    
}

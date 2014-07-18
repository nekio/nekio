
package lania.ado.sessionbeans;

import java.util.List;
import javax.ejb.Local;
import lania.ado.entidades.Terminales;

/**
 *
 * @author Nekio
 */
@Local
public interface TerminalesFacadeLocal {

    void create(Terminales terminales);

    void edit(Terminales terminales);

    void remove(Terminales terminales);

    Terminales find(Object id);

    List<Terminales> findAll();

    List<Terminales> findRange(int[] range);

    int count();
    
}

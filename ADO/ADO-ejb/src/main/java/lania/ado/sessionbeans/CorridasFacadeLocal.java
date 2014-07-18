
package lania.ado.sessionbeans;

import java.util.List;
import javax.ejb.Local;
import lania.ado.entidades.Corridas;

/**
 *
 * @author Nekio
 */
@Local
public interface CorridasFacadeLocal {

    void create(Corridas corridas);

    void edit(Corridas corridas);

    void remove(Corridas corridas);

    Corridas find(Object id);

    List<Corridas> findAll();

    List<Corridas> findRange(int[] range);

    int count();
    
}

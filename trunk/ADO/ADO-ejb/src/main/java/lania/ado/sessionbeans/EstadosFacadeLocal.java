
package lania.ado.sessionbeans;

import java.util.List;
import javax.ejb.Local;
import lania.ado.entidades.Estados;

/**
 *
 * @author Nekio
 */
@Local
public interface EstadosFacadeLocal {

    void create(Estados estados);

    void edit(Estados estados);

    void remove(Estados estados);

    Estados find(Object id);

    List<Estados> findAll();

    List<Estados> findRange(int[] range);

    int count();
    
}

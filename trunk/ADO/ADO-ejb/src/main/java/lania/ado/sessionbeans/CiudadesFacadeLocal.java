
package lania.ado.sessionbeans;

import java.util.List;
import javax.ejb.Local;
import lania.ado.entidades.Ciudades;

/**
 *
 * @author Nekio
 */
@Local
public interface CiudadesFacadeLocal {

    void create(Ciudades ciudades);

    void edit(Ciudades ciudades);

    void remove(Ciudades ciudades);

    Ciudades find(Object id);

    List<Ciudades> findAll();

    List<Ciudades> findRange(int[] range);

    int count();
    
}

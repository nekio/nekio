
package lania.ado.sessionbeans;

import java.util.List;
import javax.ejb.Local;
import lania.ado.entidades.Itinerarios;

/**
 *
 * @author Nekio
 */
@Local
public interface ItinerariosFacadeLocal {

    void create(Itinerarios itinerarios);

    void edit(Itinerarios itinerarios);

    void remove(Itinerarios itinerarios);

    Itinerarios find(Object id);

    List<Itinerarios> findAll();

    List<Itinerarios> findRange(int[] range);

    int count();
    
}

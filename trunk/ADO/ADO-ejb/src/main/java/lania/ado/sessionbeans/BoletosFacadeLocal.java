
package lania.ado.sessionbeans;

import java.util.List;
import javax.ejb.Local;
import lania.ado.entidades.Boletos;

/**
 *
 * @author Nekio
 */
@Local
public interface BoletosFacadeLocal {

    void create(Boletos boletos);

    void edit(Boletos boletos);

    void remove(Boletos boletos);

    Boletos find(Object id);

    List<Boletos> findAll();

    List<Boletos> findRange(int[] range);

    int count();
    
}

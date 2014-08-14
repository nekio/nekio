package nekio.myprp.sistema.acceso.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class PrivilegioDTO extends DTO {

    private Integer idTipoUsuario;
    private Boolean configurar;
    private Boolean buscar;
    private Boolean insertar;
    private Boolean modificar;
    private Boolean eliminar;

    @Override
    public void confirmarDTO() {
        campos = new ArrayList<String>();
        tablasForaneas = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        valoresLOV = new ArrayList<String>();
        camposExtrasLOV = new ArrayList<List>();

        super.campos.add("id_tipo_usuario");
        super.valores.add(idTipoUsuario);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);

        super.campos.add("configurar");
        super.valores.add(configurar);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);

        super.campos.add("buscar");
        super.valores.add(buscar);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);

        super.campos.add("insertar");
        super.valores.add(insertar);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);

        super.campos.add("modificar");
        super.valores.add(modificar);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);

        super.campos.add("eliminar");
        super.valores.add(eliminar);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setConfigurar(Boolean configurar) {
        this.configurar = configurar;
    }

    public void setConfigurar(int configurar) {
        if (configurar == 1) {
            this.configurar = true;
        } else {
            this.configurar = false;
        }
    }

    public Boolean isConfigurar() {
        return configurar;
    }

    public void setBuscar(Boolean buscar) {
        this.buscar = buscar;
    }

    public void setBuscar(int buscar) {
        if (buscar == 1) {
            this.buscar = true;
        } else {
            this.buscar = false;
        }
    }

    public Boolean isBuscar() {
        return buscar;
    }

    public void setInsertar(Boolean insertar) {
        this.insertar = insertar;
    }

    public void setInsertar(int insertar) {
        if (insertar == 1) {
            this.insertar = true;
        } else {
            this.insertar = false;
        }
    }

    public Boolean isInsertar() {
        return insertar;
    }

    public void setModificar(Boolean modificar) {
        this.modificar = modificar;
    }

    public void setModificar(int modificar) {
        if (modificar == 1) {
            this.modificar = true;
        } else {
            this.modificar = false;
        }
    }

    public Boolean isModificar() {
        return modificar;
    }

    public void setEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
    }

    public void setEliminar(int eliminar) {
        if (eliminar == 1) {
            this.eliminar = true;
        } else {
            this.eliminar = false;
        }
    }

    public Boolean isEliminar() {
        return eliminar;
    }
}

package nekio.myprp.sistema.acceso.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class SistemaDTO extends DTO{
	private Integer idSistema;
	private String descripcion;
	private String siglas;
	private String titulo;
	private String desarrollador;
	private String contacto;
	private String version;
	private String actualizacion;
	private Date fechaLiberacion;
	private String msjMantenimiento;
	private String bdEsquema;

	@Override
	public void confirmarDTO(){ 
		campos = new ArrayList<String>();
		tablasForaneas = new ArrayList<String>();
		valores = new ArrayList();
		tipoDatos = new ArrayList<Globales.TipoDato>();
		valoresLOV = new ArrayList<String>();
		camposExtrasLOV = new ArrayList<List>();

		super.campos.add("id_sistema");
		super.valores.add(idSistema);
		super.tipoDatos.add(Globales.TipoDato.NUMERO);

		super.campos.add("descripcion");
		super.valores.add(descripcion);
		super.tipoDatos.add(Globales.TipoDato.TEXTO);

		super.campos.add("siglas");
		super.valores.add(siglas);
		super.tipoDatos.add(Globales.TipoDato.TEXTO);

		super.campos.add("titulo");
		super.valores.add(titulo);
		super.tipoDatos.add(Globales.TipoDato.TEXTO);

		super.campos.add("desarrollador");
		super.valores.add(desarrollador);
		super.tipoDatos.add(Globales.TipoDato.TEXTO);

		super.campos.add("contacto");
		super.valores.add(contacto);
		super.tipoDatos.add(Globales.TipoDato.TEXTO);

		super.campos.add("version");
		super.valores.add(version);
		super.tipoDatos.add(Globales.TipoDato.TEXTO);

		super.campos.add("actualizacion");
		super.valores.add(actualizacion);
		super.tipoDatos.add(Globales.TipoDato.TEXTO);

		super.campos.add("fecha_liberacion");
		super.valores.add(fechaLiberacion);
		super.tipoDatos.add(Globales.TipoDato.FECHA);

		super.campos.add("msj_mantenimiento");
		super.valores.add(msjMantenimiento);
		super.tipoDatos.add(Globales.TipoDato.TEXTO);

		super.campos.add("bd_esquema");
		super.valores.add(bdEsquema);
		super.tipoDatos.add(Globales.TipoDato.TEXTO);
	}

	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}

	public Integer getIdSistema() {
		return idSistema;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}

	public String getDesarrollador() {
		return desarrollador;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getContacto() {
		return contacto;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setActualizacion(String actualizacion) {
		this.actualizacion = actualizacion;
	}

	public String getActualizacion() {
		return actualizacion;
	}

	public void setFechaLiberacion(Date fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}

	public Date getFechaLiberacion() {
		return fechaLiberacion;
	}

	public void setMsjMantenimiento(String msjMantenimiento) {
		this.msjMantenimiento = msjMantenimiento;
	}

	public String getMsjMantenimiento() {
		return msjMantenimiento;
	}

	public void setBdEsquema(String bdEsquema) {
		this.bdEsquema = bdEsquema;
	}

	public String getBdEsquema() {
		return bdEsquema;
	}
}
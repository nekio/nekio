/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rysi.sma.negocio.dao;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import rysi.sma.negocio.dao.exceptions.NonexistentEntityException;
import rysi.sma.negocio.dao.exceptions.PreexistingEntityException;
import rysi.sma.negocio.herramientas.Globales;
import rysi.sma.negocio.modelo.Usuario;
import rysi.sma.negocio.modelo.TopicoAyuda;
import rysi.sma.negocio.modelo.NivelAtencion;
import rysi.sma.negocio.modelo.EstadoTicket;
import rysi.sma.negocio.modelo.Departamento;
import rysi.sma.negocio.modelo.CausaCierreTicket;
import rysi.sma.negocio.modelo.Ticket;

/**
 *
 * @author Ivan
 */
public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuario = ticket.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                ticket.setIdUsuario(idUsuario);
            }
            Usuario idPersonalAtencion = ticket.getIdPersonalAtencion();
            if (idPersonalAtencion != null) {
                idPersonalAtencion = em.getReference(idPersonalAtencion.getClass(), idPersonalAtencion.getIdUsuario());
                ticket.setIdPersonalAtencion(idPersonalAtencion);
            }
            TopicoAyuda idTopicoAyuda = ticket.getIdTopicoAyuda();
            if (idTopicoAyuda != null) {
                idTopicoAyuda = em.getReference(idTopicoAyuda.getClass(), idTopicoAyuda.getIdTopicoAyuda());
                ticket.setIdTopicoAyuda(idTopicoAyuda);
            }
            NivelAtencion idNivelAtencion = ticket.getIdNivelAtencion();
            if (idNivelAtencion != null) {
                idNivelAtencion = em.getReference(idNivelAtencion.getClass(), idNivelAtencion.getIdNivelAtencion());
                ticket.setIdNivelAtencion(idNivelAtencion);
            }
            EstadoTicket idEstado = ticket.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                ticket.setIdEstado(idEstado);
            }
            Departamento idDepto = ticket.getIdDepto();
            if (idDepto != null) {
                idDepto = em.getReference(idDepto.getClass(), idDepto.getIdDepto());
                ticket.setIdDepto(idDepto);
            }
            CausaCierreTicket idCausaCierreTicket = ticket.getIdCausaCierreTicket();
            if (idCausaCierreTicket != null) {
                idCausaCierreTicket = em.getReference(idCausaCierreTicket.getClass(), idCausaCierreTicket.getIdCausaCierre());
                ticket.setIdCausaCierreTicket(idCausaCierreTicket);
            }
            em.persist(ticket);
            if (idUsuario != null) {
                idUsuario.getTicketCollection().add(ticket);
                idUsuario = em.merge(idUsuario);
            }
            if (idPersonalAtencion != null) {
                idPersonalAtencion.getTicketCollection().add(ticket);
                idPersonalAtencion = em.merge(idPersonalAtencion);
            }
            if (idTopicoAyuda != null) {
                idTopicoAyuda.getTicketCollection().add(ticket);
                idTopicoAyuda = em.merge(idTopicoAyuda);
            }
            if (idNivelAtencion != null) {
                idNivelAtencion.getTicketCollection().add(ticket);
                idNivelAtencion = em.merge(idNivelAtencion);
            }
            if (idEstado != null) {
                idEstado.getTicketCollection().add(ticket);
                idEstado = em.merge(idEstado);
            }
            if (idDepto != null) {
                idDepto.getTicketCollection().add(ticket);
                idDepto = em.merge(idDepto);
            }
            if (idCausaCierreTicket != null) {
                idCausaCierreTicket.getTicketCollection().add(ticket);
                idCausaCierreTicket = em.merge(idCausaCierreTicket);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTicket(ticket.getIdTicket()) != null) {
                throw new PreexistingEntityException("Ticket " + ticket + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket persistentTicket = em.find(Ticket.class, ticket.getIdTicket());
            Usuario idUsuarioOld = persistentTicket.getIdUsuario();
            Usuario idUsuarioNew = ticket.getIdUsuario();
            Usuario idPersonalAtencionOld = persistentTicket.getIdPersonalAtencion();
            Usuario idPersonalAtencionNew = ticket.getIdPersonalAtencion();
            TopicoAyuda idTopicoAyudaOld = persistentTicket.getIdTopicoAyuda();
            TopicoAyuda idTopicoAyudaNew = ticket.getIdTopicoAyuda();
            NivelAtencion idNivelAtencionOld = persistentTicket.getIdNivelAtencion();
            NivelAtencion idNivelAtencionNew = ticket.getIdNivelAtencion();
            EstadoTicket idEstadoOld = persistentTicket.getIdEstado();
            EstadoTicket idEstadoNew = ticket.getIdEstado();
            Departamento idDeptoOld = persistentTicket.getIdDepto();
            Departamento idDeptoNew = ticket.getIdDepto();
            CausaCierreTicket idCausaCierreTicketOld = persistentTicket.getIdCausaCierreTicket();
            CausaCierreTicket idCausaCierreTicketNew = ticket.getIdCausaCierreTicket();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                ticket.setIdUsuario(idUsuarioNew);
            }
            if (idPersonalAtencionNew != null) {
                idPersonalAtencionNew = em.getReference(idPersonalAtencionNew.getClass(), idPersonalAtencionNew.getIdUsuario());
                ticket.setIdPersonalAtencion(idPersonalAtencionNew);
            }
            if (idTopicoAyudaNew != null) {
                idTopicoAyudaNew = em.getReference(idTopicoAyudaNew.getClass(), idTopicoAyudaNew.getIdTopicoAyuda());
                ticket.setIdTopicoAyuda(idTopicoAyudaNew);
            }
            if (idNivelAtencionNew != null) {
                idNivelAtencionNew = em.getReference(idNivelAtencionNew.getClass(), idNivelAtencionNew.getIdNivelAtencion());
                ticket.setIdNivelAtencion(idNivelAtencionNew);
            }
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                ticket.setIdEstado(idEstadoNew);
            }
            if (idDeptoNew != null) {
                idDeptoNew = em.getReference(idDeptoNew.getClass(), idDeptoNew.getIdDepto());
                ticket.setIdDepto(idDeptoNew);
            }
            if (idCausaCierreTicketNew != null) {
                idCausaCierreTicketNew = em.getReference(idCausaCierreTicketNew.getClass(), idCausaCierreTicketNew.getIdCausaCierre());
                ticket.setIdCausaCierreTicket(idCausaCierreTicketNew);
            }
            ticket = em.merge(ticket);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getTicketCollection().remove(ticket);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getTicketCollection().add(ticket);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            if (idPersonalAtencionOld != null && !idPersonalAtencionOld.equals(idPersonalAtencionNew)) {
                idPersonalAtencionOld.getTicketCollection().remove(ticket);
                idPersonalAtencionOld = em.merge(idPersonalAtencionOld);
            }
            if (idPersonalAtencionNew != null && !idPersonalAtencionNew.equals(idPersonalAtencionOld)) {
                idPersonalAtencionNew.getTicketCollection().add(ticket);
                idPersonalAtencionNew = em.merge(idPersonalAtencionNew);
            }
            if (idTopicoAyudaOld != null && !idTopicoAyudaOld.equals(idTopicoAyudaNew)) {
                idTopicoAyudaOld.getTicketCollection().remove(ticket);
                idTopicoAyudaOld = em.merge(idTopicoAyudaOld);
            }
            if (idTopicoAyudaNew != null && !idTopicoAyudaNew.equals(idTopicoAyudaOld)) {
                idTopicoAyudaNew.getTicketCollection().add(ticket);
                idTopicoAyudaNew = em.merge(idTopicoAyudaNew);
            }
            if (idNivelAtencionOld != null && !idNivelAtencionOld.equals(idNivelAtencionNew)) {
                idNivelAtencionOld.getTicketCollection().remove(ticket);
                idNivelAtencionOld = em.merge(idNivelAtencionOld);
            }
            if (idNivelAtencionNew != null && !idNivelAtencionNew.equals(idNivelAtencionOld)) {
                idNivelAtencionNew.getTicketCollection().add(ticket);
                idNivelAtencionNew = em.merge(idNivelAtencionNew);
            }
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getTicketCollection().remove(ticket);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getTicketCollection().add(ticket);
                idEstadoNew = em.merge(idEstadoNew);
            }
            if (idDeptoOld != null && !idDeptoOld.equals(idDeptoNew)) {
                idDeptoOld.getTicketCollection().remove(ticket);
                idDeptoOld = em.merge(idDeptoOld);
            }
            if (idDeptoNew != null && !idDeptoNew.equals(idDeptoOld)) {
                idDeptoNew.getTicketCollection().add(ticket);
                idDeptoNew = em.merge(idDeptoNew);
            }
            if (idCausaCierreTicketOld != null && !idCausaCierreTicketOld.equals(idCausaCierreTicketNew)) {
                idCausaCierreTicketOld.getTicketCollection().remove(ticket);
                idCausaCierreTicketOld = em.merge(idCausaCierreTicketOld);
            }
            if (idCausaCierreTicketNew != null && !idCausaCierreTicketNew.equals(idCausaCierreTicketOld)) {
                idCausaCierreTicketNew.getTicketCollection().add(ticket);
                idCausaCierreTicketNew = em.merge(idCausaCierreTicketNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ticket.getIdTicket();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getIdTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            Usuario idUsuario = ticket.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getTicketCollection().remove(ticket);
                idUsuario = em.merge(idUsuario);
            }
            Usuario idPersonalAtencion = ticket.getIdPersonalAtencion();
            if (idPersonalAtencion != null) {
                idPersonalAtencion.getTicketCollection().remove(ticket);
                idPersonalAtencion = em.merge(idPersonalAtencion);
            }
            TopicoAyuda idTopicoAyuda = ticket.getIdTopicoAyuda();
            if (idTopicoAyuda != null) {
                idTopicoAyuda.getTicketCollection().remove(ticket);
                idTopicoAyuda = em.merge(idTopicoAyuda);
            }
            NivelAtencion idNivelAtencion = ticket.getIdNivelAtencion();
            if (idNivelAtencion != null) {
                idNivelAtencion.getTicketCollection().remove(ticket);
                idNivelAtencion = em.merge(idNivelAtencion);
            }
            EstadoTicket idEstado = ticket.getIdEstado();
            if (idEstado != null) {
                idEstado.getTicketCollection().remove(ticket);
                idEstado = em.merge(idEstado);
            }
            Departamento idDepto = ticket.getIdDepto();
            if (idDepto != null) {
                idDepto.getTicketCollection().remove(ticket);
                idDepto = em.merge(idDepto);
            }
            CausaCierreTicket idCausaCierreTicket = ticket.getIdCausaCierreTicket();
            if (idCausaCierreTicket != null) {
                idCausaCierreTicket.getTicketCollection().remove(ticket);
                idCausaCierreTicket = em.merge(idCausaCierreTicket);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ticket.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ticket findTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ticket> rt = cq.from(Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public int getProximoIdTicket(){
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root rt = cq.from(Ticket.class);
            cq.select(getEntityManager().getCriteriaBuilder().max(rt.get("idTicket")));
            Query q = em.createQuery(cq);
        
            Object id = q.getSingleResult();
            if (id == null) {
                return 1;
            }
            return ((Integer) id).intValue() + 1;
        } finally {
            em.close();
        }
    }
    
    private String generarFolio(int idUsuario){
        String fecha = new SimpleDateFormat("yyMMdd-HHmmss").format(new Date());
        String fIdUsuario = new DecimalFormat("0000000").format(idUsuario);
        
        String folio = Globales.NOMBRE_SISTEMA + fecha + "_" + fIdUsuario;
        
        return folio;
    }

    public void crearTicket(
            Usuario idUsuario, Departamento idDepto, TopicoAyuda idTopicoAyuda,
            Usuario idPersonalAtencion, NivelAtencion idNivelAtencion, EstadoTicket idEstado,
            CausaCierreTicket idCausaCierreTicket, String descripcion){
        try {
            Ticket ticket = new Ticket();
            
            int idTicket = getProximoIdTicket();
            String folio = generarFolio(idUsuario.getIdUsuario());
            
            if(idEstado == null){
                // EstadoTicket = 2 (Nuevo)
                idEstado = new EstadoTicket();
                idEstado.setIdEstado(2);
            }
            
            ticket.setIdTicket(idTicket);
            ticket.setIdUsuario(idUsuario);
            ticket.setIdDepto(idDepto);
            ticket.setIdTopicoAyuda(idTopicoAyuda);
            ticket.setIdPersonalAtencion(idPersonalAtencion);
            ticket.setIdNivelAtencion(idNivelAtencion);
            ticket.setIdEstado(idEstado);
            ticket.setIdCausaCierreTicket(idCausaCierreTicket);
            ticket.setFolio(folio);
            ticket.setDescripcion(descripcion);
            ticket.setFechaCreacion(new Date());
            ticket.setFechaCierre(null);
            ticket.setCalificacion(null);
            ticket.setResuelto(false);
            
            create(ticket);
            
            System.out.println(idTicket+": Ticket creado exitosamente con folio " + folio);
        } catch (Exception e) {
            System.out.println("Error al crear Ticket");
        }
    }
    
    public void modificarTicket(
            int idTicket, Departamento idDepartamento, TopicoAyuda idTopicoAyuda,
            Usuario idPersonalAtencion, NivelAtencion idNivelAtencion, EstadoTicket idEstado,
            CausaCierreTicket idCausaCierreTicket, String descripcion, Short calificacion){
        try {
            Ticket ticket = findTicket(idTicket);
            
            if(idDepartamento != null)
                ticket.setIdDepto(idDepartamento);
            if(idTopicoAyuda != null)
                ticket.setIdTopicoAyuda(idTopicoAyuda);
            if(idPersonalAtencion != null)
                ticket.setIdPersonalAtencion(idPersonalAtencion);
            if(idNivelAtencion != null)
                ticket.setIdNivelAtencion(idNivelAtencion);
            if(idEstado != null)
                ticket.setIdEstado(idEstado);
            if(idCausaCierreTicket != null)
                ticket.setIdCausaCierreTicket(idCausaCierreTicket);
            if(descripcion != null)
                ticket.setDescripcion(descripcion);
            if(calificacion != null){
                ticket.setCalificacion(calificacion);
                ticket.setFechaCierre(new Date());
                ticket.setResuelto(true);
            }
            
            edit(ticket);
            
            System.out.println("ID Ticket " + idTicket + " modificado exitosamente");
        } catch (Exception e){
            System.out.println("Error al modificar Ticket " + idTicket);
        }
    }
    
    public int getTicketsPorDepartamento(Departamento idDepto){
       EntityManager em = getEntityManager();
        
        try{
            Query q = em.createQuery("SELECT COUNT( t ) FROM Ticket t WHERE t.idDepto = :idDepto");
            q.setParameter("idDepto", idDepto);
            Object vContador = q.getSingleResult();
            
            if(vContador == null)
                return 0;
                
            return Integer.valueOf(vContador.toString());
        }catch(Exception e){
            System.out.println("Error al obtener el número de tickets del departamento " + idDepto);
            return 0;
        }
    }
    
    public List<Ticket> getTicketsNoResueltos(){
       EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery(
                    "SELECT t " +
                    "FROM Ticket t " +
                    "WHERE t.resuelto = 0 ");

            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Ticket> getTicketsPorFecha(){
       EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery(
                    "SELECT t " +
                    "FROM Ticket t " +
                    "ORDER BY t.fechaCreacion DESC ");

            return q.getResultList();
        } finally {
            em.close();
        }
    } 
}

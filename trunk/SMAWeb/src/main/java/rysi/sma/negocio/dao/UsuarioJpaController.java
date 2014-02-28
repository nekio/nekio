/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rysi.sma.negocio.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import rysi.sma.negocio.modelo.TipoUsuario;
import rysi.sma.negocio.modelo.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import rysi.sma.negocio.dao.exceptions.IllegalOrphanException;
import rysi.sma.negocio.dao.exceptions.NonexistentEntityException;
import rysi.sma.negocio.dao.exceptions.PreexistingEntityException;
import rysi.sma.negocio.modelo.Usuario;

/**
 *
 * @author Ivan
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getTicketCollection() == null) {
            usuario.setTicketCollection(new ArrayList<Ticket>());
        }
        if (usuario.getTicketCollection1() == null) {
            usuario.setTicketCollection1(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoUsuario idTipoUsuario = usuario.getIdTipoUsuario();
            if (idTipoUsuario != null) {
                idTipoUsuario = em.getReference(idTipoUsuario.getClass(), idTipoUsuario.getIdTipoUsuario());
                usuario.setIdTipoUsuario(idTipoUsuario);
            }
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : usuario.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            usuario.setTicketCollection(attachedTicketCollection);
            Collection<Ticket> attachedTicketCollection1 = new ArrayList<Ticket>();
            for (Ticket ticketCollection1TicketToAttach : usuario.getTicketCollection1()) {
                ticketCollection1TicketToAttach = em.getReference(ticketCollection1TicketToAttach.getClass(), ticketCollection1TicketToAttach.getIdTicket());
                attachedTicketCollection1.add(ticketCollection1TicketToAttach);
            }
            usuario.setTicketCollection1(attachedTicketCollection1);
            em.persist(usuario);
            if (idTipoUsuario != null) {
                idTipoUsuario.getUsuarioCollection().add(usuario);
                idTipoUsuario = em.merge(idTipoUsuario);
            }
            for (Ticket ticketCollectionTicket : usuario.getTicketCollection()) {
                Usuario oldIdUsuarioOfTicketCollectionTicket = ticketCollectionTicket.getIdUsuario();
                ticketCollectionTicket.setIdUsuario(usuario);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldIdUsuarioOfTicketCollectionTicket != null) {
                    oldIdUsuarioOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldIdUsuarioOfTicketCollectionTicket = em.merge(oldIdUsuarioOfTicketCollectionTicket);
                }
            }
            for (Ticket ticketCollection1Ticket : usuario.getTicketCollection1()) {
                Usuario oldIdPersonalAtencionOfTicketCollection1Ticket = ticketCollection1Ticket.getIdPersonalAtencion();
                ticketCollection1Ticket.setIdPersonalAtencion(usuario);
                ticketCollection1Ticket = em.merge(ticketCollection1Ticket);
                if (oldIdPersonalAtencionOfTicketCollection1Ticket != null) {
                    oldIdPersonalAtencionOfTicketCollection1Ticket.getTicketCollection1().remove(ticketCollection1Ticket);
                    oldIdPersonalAtencionOfTicketCollection1Ticket = em.merge(oldIdPersonalAtencionOfTicketCollection1Ticket);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getIdUsuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            TipoUsuario idTipoUsuarioOld = persistentUsuario.getIdTipoUsuario();
            TipoUsuario idTipoUsuarioNew = usuario.getIdTipoUsuario();
            Collection<Ticket> ticketCollectionOld = persistentUsuario.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = usuario.getTicketCollection();
            Collection<Ticket> ticketCollection1Old = persistentUsuario.getTicketCollection1();
            Collection<Ticket> ticketCollection1New = usuario.getTicketCollection1();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its idUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoUsuarioNew != null) {
                idTipoUsuarioNew = em.getReference(idTipoUsuarioNew.getClass(), idTipoUsuarioNew.getIdTipoUsuario());
                usuario.setIdTipoUsuario(idTipoUsuarioNew);
            }
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getIdTicket());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            usuario.setTicketCollection(ticketCollectionNew);
            Collection<Ticket> attachedTicketCollection1New = new ArrayList<Ticket>();
            for (Ticket ticketCollection1NewTicketToAttach : ticketCollection1New) {
                ticketCollection1NewTicketToAttach = em.getReference(ticketCollection1NewTicketToAttach.getClass(), ticketCollection1NewTicketToAttach.getIdTicket());
                attachedTicketCollection1New.add(ticketCollection1NewTicketToAttach);
            }
            ticketCollection1New = attachedTicketCollection1New;
            usuario.setTicketCollection1(ticketCollection1New);
            usuario = em.merge(usuario);
            if (idTipoUsuarioOld != null && !idTipoUsuarioOld.equals(idTipoUsuarioNew)) {
                idTipoUsuarioOld.getUsuarioCollection().remove(usuario);
                idTipoUsuarioOld = em.merge(idTipoUsuarioOld);
            }
            if (idTipoUsuarioNew != null && !idTipoUsuarioNew.equals(idTipoUsuarioOld)) {
                idTipoUsuarioNew.getUsuarioCollection().add(usuario);
                idTipoUsuarioNew = em.merge(idTipoUsuarioNew);
            }
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    Usuario oldIdUsuarioOfTicketCollectionNewTicket = ticketCollectionNewTicket.getIdUsuario();
                    ticketCollectionNewTicket.setIdUsuario(usuario);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldIdUsuarioOfTicketCollectionNewTicket != null && !oldIdUsuarioOfTicketCollectionNewTicket.equals(usuario)) {
                        oldIdUsuarioOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldIdUsuarioOfTicketCollectionNewTicket = em.merge(oldIdUsuarioOfTicketCollectionNewTicket);
                    }
                }
            }
            for (Ticket ticketCollection1OldTicket : ticketCollection1Old) {
                if (!ticketCollection1New.contains(ticketCollection1OldTicket)) {
                    ticketCollection1OldTicket.setIdPersonalAtencion(null);
                    ticketCollection1OldTicket = em.merge(ticketCollection1OldTicket);
                }
            }
            for (Ticket ticketCollection1NewTicket : ticketCollection1New) {
                if (!ticketCollection1Old.contains(ticketCollection1NewTicket)) {
                    Usuario oldIdPersonalAtencionOfTicketCollection1NewTicket = ticketCollection1NewTicket.getIdPersonalAtencion();
                    ticketCollection1NewTicket.setIdPersonalAtencion(usuario);
                    ticketCollection1NewTicket = em.merge(ticketCollection1NewTicket);
                    if (oldIdPersonalAtencionOfTicketCollection1NewTicket != null && !oldIdPersonalAtencionOfTicketCollection1NewTicket.equals(usuario)) {
                        oldIdPersonalAtencionOfTicketCollection1NewTicket.getTicketCollection1().remove(ticketCollection1NewTicket);
                        oldIdPersonalAtencionOfTicketCollection1NewTicket = em.merge(oldIdPersonalAtencionOfTicketCollection1NewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = usuario.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable idUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoUsuario idTipoUsuario = usuario.getIdTipoUsuario();
            if (idTipoUsuario != null) {
                idTipoUsuario.getUsuarioCollection().remove(usuario);
                idTipoUsuario = em.merge(idTipoUsuario);
            }
            Collection<Ticket> ticketCollection1 = usuario.getTicketCollection1();
            for (Ticket ticketCollection1Ticket : ticketCollection1) {
                ticketCollection1Ticket.setIdPersonalAtencion(null);
                ticketCollection1Ticket = em.merge(ticketCollection1Ticket);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public int getProximoIdUsuario(){
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root rt = cq.from(Usuario.class);
            cq.select(getEntityManager().getCriteriaBuilder().max(rt.get("idUsuario")));
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
    
    public void crearUsuario(TipoUsuario tipoUsuario, String nombre, String apellidoP, String apellidoM, String contacto, String pUsuario, String password, boolean activo){
        try{
            Usuario usuario = new Usuario();

            usuario.setIdUsuario(getProximoIdUsuario());
            usuario.setIdTipoUsuario(tipoUsuario);
            usuario.setNombre(nombre);
            usuario.setApellidoP(apellidoP);
            usuario.setApellidoM(apellidoM);
            usuario.setContacto(contacto);
            usuario.setUsuario(pUsuario);
            usuario.setPassword(password);
            usuario.setActivo(activo);
            
            create(usuario);
            
            System.out.println(pUsuario+": Usuario creado exitosamente");
        }catch(Exception e){
            System.out.println("Error al crear Usuario "+ pUsuario);
        }
    }
    
    public void modificarUsuario(int idUsuario, TipoUsuario tipoUsuario, String nombre, String apellidoP, String apellidoM, String contacto, String password){
        try {
            Usuario usuario = findUsuario(idUsuario);
            
            if(tipoUsuario != null)
                usuario.setIdTipoUsuario(tipoUsuario);
            if(nombre != null)
                usuario.setNombre(nombre);
            if(apellidoP != null)
                usuario.setApellidoP(apellidoP);
            if(apellidoM != null)
                usuario.setApellidoM(apellidoM);
            if(contacto != null)
                usuario.setContacto(contacto);
            if(password != null)
                usuario.setPassword(password);
            
            edit(usuario);
            
            System.out.println("ID Usuario " + idUsuario + " modificado exitosamente");
        } catch (Exception e){
            System.out.println("Error al modificar Usuario " + idUsuario);
        }
    }
}

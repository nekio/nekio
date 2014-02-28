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
import rysi.sma.negocio.modelo.Departamento;
import rysi.sma.negocio.modelo.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import rysi.sma.negocio.dao.exceptions.IllegalOrphanException;
import rysi.sma.negocio.dao.exceptions.NonexistentEntityException;
import rysi.sma.negocio.dao.exceptions.PreexistingEntityException;
import rysi.sma.negocio.modelo.TopicoAyuda;

/**
 *
 * @author Ivan
 */
public class TopicoAyudaJpaController implements Serializable {

    public TopicoAyudaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TopicoAyuda topicoAyuda) throws PreexistingEntityException, Exception {
        if (topicoAyuda.getTicketCollection() == null) {
            topicoAyuda.setTicketCollection(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento idDepto = topicoAyuda.getIdDepto();
            if (idDepto != null) {
                idDepto = em.getReference(idDepto.getClass(), idDepto.getIdDepto());
                topicoAyuda.setIdDepto(idDepto);
            }
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : topicoAyuda.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            topicoAyuda.setTicketCollection(attachedTicketCollection);
            em.persist(topicoAyuda);
            if (idDepto != null) {
                idDepto.getTopicoAyudaCollection().add(topicoAyuda);
                idDepto = em.merge(idDepto);
            }
            for (Ticket ticketCollectionTicket : topicoAyuda.getTicketCollection()) {
                TopicoAyuda oldIdTopicoAyudaOfTicketCollectionTicket = ticketCollectionTicket.getIdTopicoAyuda();
                ticketCollectionTicket.setIdTopicoAyuda(topicoAyuda);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldIdTopicoAyudaOfTicketCollectionTicket != null) {
                    oldIdTopicoAyudaOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldIdTopicoAyudaOfTicketCollectionTicket = em.merge(oldIdTopicoAyudaOfTicketCollectionTicket);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTopicoAyuda(topicoAyuda.getIdTopicoAyuda()) != null) {
                throw new PreexistingEntityException("TopicoAyuda " + topicoAyuda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TopicoAyuda topicoAyuda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TopicoAyuda persistentTopicoAyuda = em.find(TopicoAyuda.class, topicoAyuda.getIdTopicoAyuda());
            Departamento idDeptoOld = persistentTopicoAyuda.getIdDepto();
            Departamento idDeptoNew = topicoAyuda.getIdDepto();
            Collection<Ticket> ticketCollectionOld = persistentTopicoAyuda.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = topicoAyuda.getTicketCollection();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its idTopicoAyuda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idDeptoNew != null) {
                idDeptoNew = em.getReference(idDeptoNew.getClass(), idDeptoNew.getIdDepto());
                topicoAyuda.setIdDepto(idDeptoNew);
            }
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getIdTicket());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            topicoAyuda.setTicketCollection(ticketCollectionNew);
            topicoAyuda = em.merge(topicoAyuda);
            if (idDeptoOld != null && !idDeptoOld.equals(idDeptoNew)) {
                idDeptoOld.getTopicoAyudaCollection().remove(topicoAyuda);
                idDeptoOld = em.merge(idDeptoOld);
            }
            if (idDeptoNew != null && !idDeptoNew.equals(idDeptoOld)) {
                idDeptoNew.getTopicoAyudaCollection().add(topicoAyuda);
                idDeptoNew = em.merge(idDeptoNew);
            }
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    TopicoAyuda oldIdTopicoAyudaOfTicketCollectionNewTicket = ticketCollectionNewTicket.getIdTopicoAyuda();
                    ticketCollectionNewTicket.setIdTopicoAyuda(topicoAyuda);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldIdTopicoAyudaOfTicketCollectionNewTicket != null && !oldIdTopicoAyudaOfTicketCollectionNewTicket.equals(topicoAyuda)) {
                        oldIdTopicoAyudaOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldIdTopicoAyudaOfTicketCollectionNewTicket = em.merge(oldIdTopicoAyudaOfTicketCollectionNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = topicoAyuda.getIdTopicoAyuda();
                if (findTopicoAyuda(id) == null) {
                    throw new NonexistentEntityException("The topicoAyuda with id " + id + " no longer exists.");
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
            TopicoAyuda topicoAyuda;
            try {
                topicoAyuda = em.getReference(TopicoAyuda.class, id);
                topicoAyuda.getIdTopicoAyuda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The topicoAyuda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = topicoAyuda.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TopicoAyuda (" + topicoAyuda + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable idTopicoAyuda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Departamento idDepto = topicoAyuda.getIdDepto();
            if (idDepto != null) {
                idDepto.getTopicoAyudaCollection().remove(topicoAyuda);
                idDepto = em.merge(idDepto);
            }
            em.remove(topicoAyuda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TopicoAyuda> findTopicoAyudaEntities() {
        return findTopicoAyudaEntities(true, -1, -1);
    }

    public List<TopicoAyuda> findTopicoAyudaEntities(int maxResults, int firstResult) {
        return findTopicoAyudaEntities(false, maxResults, firstResult);
    }

    private List<TopicoAyuda> findTopicoAyudaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TopicoAyuda.class));
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

    public TopicoAyuda findTopicoAyuda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TopicoAyuda.class, id);
        } finally {
            em.close();
        }
    }

    public int getTopicoAyudaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TopicoAyuda> rt = cq.from(TopicoAyuda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

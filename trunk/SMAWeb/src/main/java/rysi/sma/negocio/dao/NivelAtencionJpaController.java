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
import rysi.sma.negocio.modelo.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import rysi.sma.negocio.dao.exceptions.IllegalOrphanException;
import rysi.sma.negocio.dao.exceptions.NonexistentEntityException;
import rysi.sma.negocio.dao.exceptions.PreexistingEntityException;
import rysi.sma.negocio.modelo.NivelAtencion;

/**
 *
 * @author Ivan
 */
public class NivelAtencionJpaController implements Serializable {

    public NivelAtencionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NivelAtencion nivelAtencion) throws PreexistingEntityException, Exception {
        if (nivelAtencion.getTicketCollection() == null) {
            nivelAtencion.setTicketCollection(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : nivelAtencion.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            nivelAtencion.setTicketCollection(attachedTicketCollection);
            em.persist(nivelAtencion);
            for (Ticket ticketCollectionTicket : nivelAtencion.getTicketCollection()) {
                NivelAtencion oldIdNivelAtencionOfTicketCollectionTicket = ticketCollectionTicket.getIdNivelAtencion();
                ticketCollectionTicket.setIdNivelAtencion(nivelAtencion);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldIdNivelAtencionOfTicketCollectionTicket != null) {
                    oldIdNivelAtencionOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldIdNivelAtencionOfTicketCollectionTicket = em.merge(oldIdNivelAtencionOfTicketCollectionTicket);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNivelAtencion(nivelAtencion.getIdNivelAtencion()) != null) {
                throw new PreexistingEntityException("NivelAtencion " + nivelAtencion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NivelAtencion nivelAtencion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NivelAtencion persistentNivelAtencion = em.find(NivelAtencion.class, nivelAtencion.getIdNivelAtencion());
            Collection<Ticket> ticketCollectionOld = persistentNivelAtencion.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = nivelAtencion.getTicketCollection();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its idNivelAtencion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getIdTicket());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            nivelAtencion.setTicketCollection(ticketCollectionNew);
            nivelAtencion = em.merge(nivelAtencion);
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    NivelAtencion oldIdNivelAtencionOfTicketCollectionNewTicket = ticketCollectionNewTicket.getIdNivelAtencion();
                    ticketCollectionNewTicket.setIdNivelAtencion(nivelAtencion);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldIdNivelAtencionOfTicketCollectionNewTicket != null && !oldIdNivelAtencionOfTicketCollectionNewTicket.equals(nivelAtencion)) {
                        oldIdNivelAtencionOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldIdNivelAtencionOfTicketCollectionNewTicket = em.merge(oldIdNivelAtencionOfTicketCollectionNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nivelAtencion.getIdNivelAtencion();
                if (findNivelAtencion(id) == null) {
                    throw new NonexistentEntityException("The nivelAtencion with id " + id + " no longer exists.");
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
            NivelAtencion nivelAtencion;
            try {
                nivelAtencion = em.getReference(NivelAtencion.class, id);
                nivelAtencion.getIdNivelAtencion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nivelAtencion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = nivelAtencion.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NivelAtencion (" + nivelAtencion + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable idNivelAtencion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nivelAtencion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NivelAtencion> findNivelAtencionEntities() {
        return findNivelAtencionEntities(true, -1, -1);
    }

    public List<NivelAtencion> findNivelAtencionEntities(int maxResults, int firstResult) {
        return findNivelAtencionEntities(false, maxResults, firstResult);
    }

    private List<NivelAtencion> findNivelAtencionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NivelAtencion.class));
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

    public NivelAtencion findNivelAtencion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NivelAtencion.class, id);
        } finally {
            em.close();
        }
    }

    public int getNivelAtencionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NivelAtencion> rt = cq.from(NivelAtencion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

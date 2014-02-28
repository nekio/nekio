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
import rysi.sma.negocio.dao.exceptions.NonexistentEntityException;
import rysi.sma.negocio.dao.exceptions.PreexistingEntityException;
import rysi.sma.negocio.modelo.CausaCierreTicket;

/**
 *
 * @author Ivan
 */
public class CausaCierreTicketJpaController implements Serializable {

    public CausaCierreTicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CausaCierreTicket causaCierreTicket) throws PreexistingEntityException, Exception {
        if (causaCierreTicket.getTicketCollection() == null) {
            causaCierreTicket.setTicketCollection(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : causaCierreTicket.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            causaCierreTicket.setTicketCollection(attachedTicketCollection);
            em.persist(causaCierreTicket);
            for (Ticket ticketCollectionTicket : causaCierreTicket.getTicketCollection()) {
                CausaCierreTicket oldIdCausaCierreTicketOfTicketCollectionTicket = ticketCollectionTicket.getIdCausaCierreTicket();
                ticketCollectionTicket.setIdCausaCierreTicket(causaCierreTicket);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldIdCausaCierreTicketOfTicketCollectionTicket != null) {
                    oldIdCausaCierreTicketOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldIdCausaCierreTicketOfTicketCollectionTicket = em.merge(oldIdCausaCierreTicketOfTicketCollectionTicket);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCausaCierreTicket(causaCierreTicket.getIdCausaCierre()) != null) {
                throw new PreexistingEntityException("CausaCierreTicket " + causaCierreTicket + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CausaCierreTicket causaCierreTicket) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CausaCierreTicket persistentCausaCierreTicket = em.find(CausaCierreTicket.class, causaCierreTicket.getIdCausaCierre());
            Collection<Ticket> ticketCollectionOld = persistentCausaCierreTicket.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = causaCierreTicket.getTicketCollection();
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getIdTicket());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            causaCierreTicket.setTicketCollection(ticketCollectionNew);
            causaCierreTicket = em.merge(causaCierreTicket);
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    ticketCollectionOldTicket.setIdCausaCierreTicket(null);
                    ticketCollectionOldTicket = em.merge(ticketCollectionOldTicket);
                }
            }
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    CausaCierreTicket oldIdCausaCierreTicketOfTicketCollectionNewTicket = ticketCollectionNewTicket.getIdCausaCierreTicket();
                    ticketCollectionNewTicket.setIdCausaCierreTicket(causaCierreTicket);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldIdCausaCierreTicketOfTicketCollectionNewTicket != null && !oldIdCausaCierreTicketOfTicketCollectionNewTicket.equals(causaCierreTicket)) {
                        oldIdCausaCierreTicketOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldIdCausaCierreTicketOfTicketCollectionNewTicket = em.merge(oldIdCausaCierreTicketOfTicketCollectionNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = causaCierreTicket.getIdCausaCierre();
                if (findCausaCierreTicket(id) == null) {
                    throw new NonexistentEntityException("The causaCierreTicket with id " + id + " no longer exists.");
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
            CausaCierreTicket causaCierreTicket;
            try {
                causaCierreTicket = em.getReference(CausaCierreTicket.class, id);
                causaCierreTicket.getIdCausaCierre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The causaCierreTicket with id " + id + " no longer exists.", enfe);
            }
            Collection<Ticket> ticketCollection = causaCierreTicket.getTicketCollection();
            for (Ticket ticketCollectionTicket : ticketCollection) {
                ticketCollectionTicket.setIdCausaCierreTicket(null);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
            }
            em.remove(causaCierreTicket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CausaCierreTicket> findCausaCierreTicketEntities() {
        return findCausaCierreTicketEntities(true, -1, -1);
    }

    public List<CausaCierreTicket> findCausaCierreTicketEntities(int maxResults, int firstResult) {
        return findCausaCierreTicketEntities(false, maxResults, firstResult);
    }

    private List<CausaCierreTicket> findCausaCierreTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CausaCierreTicket.class));
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

    public CausaCierreTicket findCausaCierreTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CausaCierreTicket.class, id);
        } finally {
            em.close();
        }
    }

    public int getCausaCierreTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CausaCierreTicket> rt = cq.from(CausaCierreTicket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

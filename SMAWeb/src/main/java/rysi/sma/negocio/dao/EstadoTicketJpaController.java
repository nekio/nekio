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
import rysi.sma.negocio.modelo.EstadoTicket;

/**
 *
 * @author Ivan
 */
public class EstadoTicketJpaController implements Serializable {

    public EstadoTicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoTicket estadoTicket) throws PreexistingEntityException, Exception {
        if (estadoTicket.getTicketCollection() == null) {
            estadoTicket.setTicketCollection(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : estadoTicket.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            estadoTicket.setTicketCollection(attachedTicketCollection);
            em.persist(estadoTicket);
            for (Ticket ticketCollectionTicket : estadoTicket.getTicketCollection()) {
                EstadoTicket oldIdEstadoOfTicketCollectionTicket = ticketCollectionTicket.getIdEstado();
                ticketCollectionTicket.setIdEstado(estadoTicket);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldIdEstadoOfTicketCollectionTicket != null) {
                    oldIdEstadoOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldIdEstadoOfTicketCollectionTicket = em.merge(oldIdEstadoOfTicketCollectionTicket);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoTicket(estadoTicket.getIdEstado()) != null) {
                throw new PreexistingEntityException("EstadoTicket " + estadoTicket + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoTicket estadoTicket) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoTicket persistentEstadoTicket = em.find(EstadoTicket.class, estadoTicket.getIdEstado());
            Collection<Ticket> ticketCollectionOld = persistentEstadoTicket.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = estadoTicket.getTicketCollection();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its idEstado field is not nullable.");
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
            estadoTicket.setTicketCollection(ticketCollectionNew);
            estadoTicket = em.merge(estadoTicket);
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    EstadoTicket oldIdEstadoOfTicketCollectionNewTicket = ticketCollectionNewTicket.getIdEstado();
                    ticketCollectionNewTicket.setIdEstado(estadoTicket);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldIdEstadoOfTicketCollectionNewTicket != null && !oldIdEstadoOfTicketCollectionNewTicket.equals(estadoTicket)) {
                        oldIdEstadoOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldIdEstadoOfTicketCollectionNewTicket = em.merge(oldIdEstadoOfTicketCollectionNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadoTicket.getIdEstado();
                if (findEstadoTicket(id) == null) {
                    throw new NonexistentEntityException("The estadoTicket with id " + id + " no longer exists.");
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
            EstadoTicket estadoTicket;
            try {
                estadoTicket = em.getReference(EstadoTicket.class, id);
                estadoTicket.getIdEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoTicket with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = estadoTicket.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadoTicket (" + estadoTicket + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable idEstado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadoTicket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoTicket> findEstadoTicketEntities() {
        return findEstadoTicketEntities(true, -1, -1);
    }

    public List<EstadoTicket> findEstadoTicketEntities(int maxResults, int firstResult) {
        return findEstadoTicketEntities(false, maxResults, firstResult);
    }

    private List<EstadoTicket> findEstadoTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoTicket.class));
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

    public EstadoTicket findEstadoTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoTicket.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoTicket> rt = cq.from(EstadoTicket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

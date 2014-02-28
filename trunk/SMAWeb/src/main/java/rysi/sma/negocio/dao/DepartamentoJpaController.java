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
import rysi.sma.negocio.modelo.Departamento;
import rysi.sma.negocio.modelo.TopicoAyuda;

/**
 *
 * @author Ivan
 */
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamento departamento) throws PreexistingEntityException, Exception {
        if (departamento.getTicketCollection() == null) {
            departamento.setTicketCollection(new ArrayList<Ticket>());
        }
        if (departamento.getTopicoAyudaCollection() == null) {
            departamento.setTopicoAyudaCollection(new ArrayList<TopicoAyuda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : departamento.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            departamento.setTicketCollection(attachedTicketCollection);
            Collection<TopicoAyuda> attachedTopicoAyudaCollection = new ArrayList<TopicoAyuda>();
            for (TopicoAyuda topicoAyudaCollectionTopicoAyudaToAttach : departamento.getTopicoAyudaCollection()) {
                topicoAyudaCollectionTopicoAyudaToAttach = em.getReference(topicoAyudaCollectionTopicoAyudaToAttach.getClass(), topicoAyudaCollectionTopicoAyudaToAttach.getIdTopicoAyuda());
                attachedTopicoAyudaCollection.add(topicoAyudaCollectionTopicoAyudaToAttach);
            }
            departamento.setTopicoAyudaCollection(attachedTopicoAyudaCollection);
            em.persist(departamento);
            for (Ticket ticketCollectionTicket : departamento.getTicketCollection()) {
                Departamento oldIdDeptoOfTicketCollectionTicket = ticketCollectionTicket.getIdDepto();
                ticketCollectionTicket.setIdDepto(departamento);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldIdDeptoOfTicketCollectionTicket != null) {
                    oldIdDeptoOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldIdDeptoOfTicketCollectionTicket = em.merge(oldIdDeptoOfTicketCollectionTicket);
                }
            }
            for (TopicoAyuda topicoAyudaCollectionTopicoAyuda : departamento.getTopicoAyudaCollection()) {
                Departamento oldIdDeptoOfTopicoAyudaCollectionTopicoAyuda = topicoAyudaCollectionTopicoAyuda.getIdDepto();
                topicoAyudaCollectionTopicoAyuda.setIdDepto(departamento);
                topicoAyudaCollectionTopicoAyuda = em.merge(topicoAyudaCollectionTopicoAyuda);
                if (oldIdDeptoOfTopicoAyudaCollectionTopicoAyuda != null) {
                    oldIdDeptoOfTopicoAyudaCollectionTopicoAyuda.getTopicoAyudaCollection().remove(topicoAyudaCollectionTopicoAyuda);
                    oldIdDeptoOfTopicoAyudaCollectionTopicoAyuda = em.merge(oldIdDeptoOfTopicoAyudaCollectionTopicoAyuda);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDepartamento(departamento.getIdDepto()) != null) {
                throw new PreexistingEntityException("Departamento " + departamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamento departamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento persistentDepartamento = em.find(Departamento.class, departamento.getIdDepto());
            Collection<Ticket> ticketCollectionOld = persistentDepartamento.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = departamento.getTicketCollection();
            Collection<TopicoAyuda> topicoAyudaCollectionOld = persistentDepartamento.getTopicoAyudaCollection();
            Collection<TopicoAyuda> topicoAyudaCollectionNew = departamento.getTopicoAyudaCollection();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its idDepto field is not nullable.");
                }
            }
            for (TopicoAyuda topicoAyudaCollectionOldTopicoAyuda : topicoAyudaCollectionOld) {
                if (!topicoAyudaCollectionNew.contains(topicoAyudaCollectionOldTopicoAyuda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TopicoAyuda " + topicoAyudaCollectionOldTopicoAyuda + " since its idDepto field is not nullable.");
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
            departamento.setTicketCollection(ticketCollectionNew);
            Collection<TopicoAyuda> attachedTopicoAyudaCollectionNew = new ArrayList<TopicoAyuda>();
            for (TopicoAyuda topicoAyudaCollectionNewTopicoAyudaToAttach : topicoAyudaCollectionNew) {
                topicoAyudaCollectionNewTopicoAyudaToAttach = em.getReference(topicoAyudaCollectionNewTopicoAyudaToAttach.getClass(), topicoAyudaCollectionNewTopicoAyudaToAttach.getIdTopicoAyuda());
                attachedTopicoAyudaCollectionNew.add(topicoAyudaCollectionNewTopicoAyudaToAttach);
            }
            topicoAyudaCollectionNew = attachedTopicoAyudaCollectionNew;
            departamento.setTopicoAyudaCollection(topicoAyudaCollectionNew);
            departamento = em.merge(departamento);
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    Departamento oldIdDeptoOfTicketCollectionNewTicket = ticketCollectionNewTicket.getIdDepto();
                    ticketCollectionNewTicket.setIdDepto(departamento);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldIdDeptoOfTicketCollectionNewTicket != null && !oldIdDeptoOfTicketCollectionNewTicket.equals(departamento)) {
                        oldIdDeptoOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldIdDeptoOfTicketCollectionNewTicket = em.merge(oldIdDeptoOfTicketCollectionNewTicket);
                    }
                }
            }
            for (TopicoAyuda topicoAyudaCollectionNewTopicoAyuda : topicoAyudaCollectionNew) {
                if (!topicoAyudaCollectionOld.contains(topicoAyudaCollectionNewTopicoAyuda)) {
                    Departamento oldIdDeptoOfTopicoAyudaCollectionNewTopicoAyuda = topicoAyudaCollectionNewTopicoAyuda.getIdDepto();
                    topicoAyudaCollectionNewTopicoAyuda.setIdDepto(departamento);
                    topicoAyudaCollectionNewTopicoAyuda = em.merge(topicoAyudaCollectionNewTopicoAyuda);
                    if (oldIdDeptoOfTopicoAyudaCollectionNewTopicoAyuda != null && !oldIdDeptoOfTopicoAyudaCollectionNewTopicoAyuda.equals(departamento)) {
                        oldIdDeptoOfTopicoAyudaCollectionNewTopicoAyuda.getTopicoAyudaCollection().remove(topicoAyudaCollectionNewTopicoAyuda);
                        oldIdDeptoOfTopicoAyudaCollectionNewTopicoAyuda = em.merge(oldIdDeptoOfTopicoAyudaCollectionNewTopicoAyuda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamento.getIdDepto();
                if (findDepartamento(id) == null) {
                    throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
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
            Departamento departamento;
            try {
                departamento = em.getReference(Departamento.class, id);
                departamento.getIdDepto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = departamento.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Departamento (" + departamento + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable idDepto field.");
            }
            Collection<TopicoAyuda> topicoAyudaCollectionOrphanCheck = departamento.getTopicoAyudaCollection();
            for (TopicoAyuda topicoAyudaCollectionOrphanCheckTopicoAyuda : topicoAyudaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Departamento (" + departamento + ") cannot be destroyed since the TopicoAyuda " + topicoAyudaCollectionOrphanCheckTopicoAyuda + " in its topicoAyudaCollection field has a non-nullable idDepto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(departamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamento> findDepartamentoEntities() {
        return findDepartamentoEntities(true, -1, -1);
    }

    public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
        return findDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
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

    public Departamento findDepartamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamento> rt = cq.from(Departamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

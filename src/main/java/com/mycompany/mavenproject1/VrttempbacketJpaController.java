/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.mavenproject1.entitys.Vrtcellcontents;
import com.mycompany.mavenproject1.entitys.Vrttempbacket;
import com.mycompany.mavenproject1.entitys.Vrtuser;
import com.mycompany.mavenproject1.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Дмитрий
 */
public class VrttempbacketJpaController implements Serializable {

    public VrttempbacketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vrttempbacket vrttempbacket) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtcellcontents idcellcontents = vrttempbacket.getIdcellcontents();
            if (idcellcontents != null) {
                idcellcontents = em.getReference(idcellcontents.getClass(), idcellcontents.getId());
                vrttempbacket.setIdcellcontents(idcellcontents);
            }
            Vrtuser iduser = vrttempbacket.getIduser();
            if (iduser != null) {
                iduser = em.getReference(iduser.getClass(), iduser.getId());
                vrttempbacket.setIduser(iduser);
            }
            em.persist(vrttempbacket);
            if (idcellcontents != null) {
                idcellcontents.getVrttempbacketList().add(vrttempbacket);
                idcellcontents = em.merge(idcellcontents);
            }
            if (iduser != null) {
                iduser.getVrttempbacketList().add(vrttempbacket);
                iduser = em.merge(iduser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vrttempbacket vrttempbacket) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrttempbacket persistentVrttempbacket = em.find(Vrttempbacket.class, vrttempbacket.getId());
            Vrtcellcontents idcellcontentsOld = persistentVrttempbacket.getIdcellcontents();
            Vrtcellcontents idcellcontentsNew = vrttempbacket.getIdcellcontents();
            Vrtuser iduserOld = persistentVrttempbacket.getIduser();
            Vrtuser iduserNew = vrttempbacket.getIduser();
            if (idcellcontentsNew != null) {
                idcellcontentsNew = em.getReference(idcellcontentsNew.getClass(), idcellcontentsNew.getId());
                vrttempbacket.setIdcellcontents(idcellcontentsNew);
            }
            if (iduserNew != null) {
                iduserNew = em.getReference(iduserNew.getClass(), iduserNew.getId());
                vrttempbacket.setIduser(iduserNew);
            }
            vrttempbacket = em.merge(vrttempbacket);
            if (idcellcontentsOld != null && !idcellcontentsOld.equals(idcellcontentsNew)) {
                idcellcontentsOld.getVrttempbacketList().remove(vrttempbacket);
                idcellcontentsOld = em.merge(idcellcontentsOld);
            }
            if (idcellcontentsNew != null && !idcellcontentsNew.equals(idcellcontentsOld)) {
                idcellcontentsNew.getVrttempbacketList().add(vrttempbacket);
                idcellcontentsNew = em.merge(idcellcontentsNew);
            }
            if (iduserOld != null && !iduserOld.equals(iduserNew)) {
                iduserOld.getVrttempbacketList().remove(vrttempbacket);
                iduserOld = em.merge(iduserOld);
            }
            if (iduserNew != null && !iduserNew.equals(iduserOld)) {
                iduserNew.getVrttempbacketList().add(vrttempbacket);
                iduserNew = em.merge(iduserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vrttempbacket.getId();
                if (findVrttempbacket(id) == null) {
                    throw new NonexistentEntityException("The vrttempbacket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrttempbacket vrttempbacket;
            try {
                vrttempbacket = em.getReference(Vrttempbacket.class, id);
                vrttempbacket.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vrttempbacket with id " + id + " no longer exists.", enfe);
            }
            Vrtcellcontents idcellcontents = vrttempbacket.getIdcellcontents();
            if (idcellcontents != null) {
                idcellcontents.getVrttempbacketList().remove(vrttempbacket);
                idcellcontents = em.merge(idcellcontents);
            }
            Vrtuser iduser = vrttempbacket.getIduser();
            if (iduser != null) {
                iduser.getVrttempbacketList().remove(vrttempbacket);
                iduser = em.merge(iduser);
            }
            em.remove(vrttempbacket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vrttempbacket> findVrttempbacketEntities() {
        return findVrttempbacketEntities(true, -1, -1);
    }

    public List<Vrttempbacket> findVrttempbacketEntities(int maxResults, int firstResult) {
        return findVrttempbacketEntities(false, maxResults, firstResult);
    }

    private List<Vrttempbacket> findVrttempbacketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vrttempbacket.class));
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

    public Vrttempbacket findVrttempbacket(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vrttempbacket.class, id);
        } finally {
            em.close();
        }
    }

    public int getVrttempbacketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vrttempbacket> rt = cq.from(Vrttempbacket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

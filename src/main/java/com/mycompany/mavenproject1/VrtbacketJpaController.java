/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.entitys.Vrtbacket;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.mavenproject1.entitys.Vrtmetal;
import com.mycompany.mavenproject1.entitys.Vrtuser;
import com.mycompany.mavenproject1.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Дмитрий
 */
public class VrtbacketJpaController implements Serializable {

    public VrtbacketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vrtbacket vrtbacket) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtmetal idmetal = vrtbacket.getIdmetal();
            if (idmetal != null) {
                idmetal = em.getReference(idmetal.getClass(), idmetal.getId());
                vrtbacket.setIdmetal(idmetal);
            }
            Vrtuser iduser = vrtbacket.getIduser();
            if (iduser != null) {
                iduser = em.getReference(iduser.getClass(), iduser.getId());
                vrtbacket.setIduser(iduser);
            }
            em.persist(vrtbacket);
            if (idmetal != null) {
                idmetal.getVrtbacketList().add(vrtbacket);
                idmetal = em.merge(idmetal);
            }
            if (iduser != null) {
                iduser.getVrtbacketList().add(vrtbacket);
                iduser = em.merge(iduser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vrtbacket vrtbacket) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtbacket persistentVrtbacket = em.find(Vrtbacket.class, vrtbacket.getId());
            Vrtmetal idmetalOld = persistentVrtbacket.getIdmetal();
            Vrtmetal idmetalNew = vrtbacket.getIdmetal();
            Vrtuser iduserOld = persistentVrtbacket.getIduser();
            Vrtuser iduserNew = vrtbacket.getIduser();
            if (idmetalNew != null) {
                idmetalNew = em.getReference(idmetalNew.getClass(), idmetalNew.getId());
                vrtbacket.setIdmetal(idmetalNew);
            }
            if (iduserNew != null) {
                iduserNew = em.getReference(iduserNew.getClass(), iduserNew.getId());
                vrtbacket.setIduser(iduserNew);
            }
            vrtbacket = em.merge(vrtbacket);
            if (idmetalOld != null && !idmetalOld.equals(idmetalNew)) {
                idmetalOld.getVrtbacketList().remove(vrtbacket);
                idmetalOld = em.merge(idmetalOld);
            }
            if (idmetalNew != null && !idmetalNew.equals(idmetalOld)) {
                idmetalNew.getVrtbacketList().add(vrtbacket);
                idmetalNew = em.merge(idmetalNew);
            }
            if (iduserOld != null && !iduserOld.equals(iduserNew)) {
                iduserOld.getVrtbacketList().remove(vrtbacket);
                iduserOld = em.merge(iduserOld);
            }
            if (iduserNew != null && !iduserNew.equals(iduserOld)) {
                iduserNew.getVrtbacketList().add(vrtbacket);
                iduserNew = em.merge(iduserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vrtbacket.getId();
                if (findVrtbacket(id) == null) {
                    throw new NonexistentEntityException("The vrtbacket with id " + id + " no longer exists.");
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
            Vrtbacket vrtbacket;
            try {
                vrtbacket = em.getReference(Vrtbacket.class, id);
                vrtbacket.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vrtbacket with id " + id + " no longer exists.", enfe);
            }
            Vrtmetal idmetal = vrtbacket.getIdmetal();
            if (idmetal != null) {
                idmetal.getVrtbacketList().remove(vrtbacket);
                idmetal = em.merge(idmetal);
            }
            Vrtuser iduser = vrtbacket.getIduser();
            if (iduser != null) {
                iduser.getVrtbacketList().remove(vrtbacket);
                iduser = em.merge(iduser);
            }
            em.remove(vrtbacket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vrtbacket> findVrtbacketEntities() {
        return findVrtbacketEntities(true, -1, -1);
    }

    public List<Vrtbacket> findVrtbacketEntities(int maxResults, int firstResult) {
        return findVrtbacketEntities(false, maxResults, firstResult);
    }

    private List<Vrtbacket> findVrtbacketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vrtbacket.class));
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

    public Vrtbacket findVrtbacket(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vrtbacket.class, id);
        } finally {
            em.close();
        }
    }

    public int getVrtbacketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vrtbacket> rt = cq.from(Vrtbacket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

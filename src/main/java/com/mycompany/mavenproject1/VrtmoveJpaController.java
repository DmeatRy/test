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
import com.mycompany.mavenproject1.entitys.Vrtmove;
import com.mycompany.mavenproject1.entitys.Vrtuser;
import com.mycompany.mavenproject1.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Дмитрий
 */
public class VrtmoveJpaController implements Serializable {

    public VrtmoveJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vrtmove vrtmove) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtcellcontents idcellcontents = vrtmove.getIdcellcontents();
            if (idcellcontents != null) {
                idcellcontents = em.getReference(idcellcontents.getClass(), idcellcontents.getId());
                vrtmove.setIdcellcontents(idcellcontents);
            }
            Vrtuser iduser = vrtmove.getIduser();
            if (iduser != null) {
                iduser = em.getReference(iduser.getClass(), iduser.getId());
                vrtmove.setIduser(iduser);
            }
            em.persist(vrtmove);
            if (idcellcontents != null) {
                idcellcontents.getVrtmoveList().add(vrtmove);
                idcellcontents = em.merge(idcellcontents);
            }
            if (iduser != null) {
                iduser.getVrtmoveList().add(vrtmove);
                iduser = em.merge(iduser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vrtmove vrtmove) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtmove persistentVrtmove = em.find(Vrtmove.class, vrtmove.getId());
            Vrtcellcontents idcellcontentsOld = persistentVrtmove.getIdcellcontents();
            Vrtcellcontents idcellcontentsNew = vrtmove.getIdcellcontents();
            Vrtuser iduserOld = persistentVrtmove.getIduser();
            Vrtuser iduserNew = vrtmove.getIduser();
            if (idcellcontentsNew != null) {
                idcellcontentsNew = em.getReference(idcellcontentsNew.getClass(), idcellcontentsNew.getId());
                vrtmove.setIdcellcontents(idcellcontentsNew);
            }
            if (iduserNew != null) {
                iduserNew = em.getReference(iduserNew.getClass(), iduserNew.getId());
                vrtmove.setIduser(iduserNew);
            }
            vrtmove = em.merge(vrtmove);
            if (idcellcontentsOld != null && !idcellcontentsOld.equals(idcellcontentsNew)) {
                idcellcontentsOld.getVrtmoveList().remove(vrtmove);
                idcellcontentsOld = em.merge(idcellcontentsOld);
            }
            if (idcellcontentsNew != null && !idcellcontentsNew.equals(idcellcontentsOld)) {
                idcellcontentsNew.getVrtmoveList().add(vrtmove);
                idcellcontentsNew = em.merge(idcellcontentsNew);
            }
            if (iduserOld != null && !iduserOld.equals(iduserNew)) {
                iduserOld.getVrtmoveList().remove(vrtmove);
                iduserOld = em.merge(iduserOld);
            }
            if (iduserNew != null && !iduserNew.equals(iduserOld)) {
                iduserNew.getVrtmoveList().add(vrtmove);
                iduserNew = em.merge(iduserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vrtmove.getId();
                if (findVrtmove(id) == null) {
                    throw new NonexistentEntityException("The vrtmove with id " + id + " no longer exists.");
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
            Vrtmove vrtmove;
            try {
                vrtmove = em.getReference(Vrtmove.class, id);
                vrtmove.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vrtmove with id " + id + " no longer exists.", enfe);
            }
            Vrtcellcontents idcellcontents = vrtmove.getIdcellcontents();
            if (idcellcontents != null) {
                idcellcontents.getVrtmoveList().remove(vrtmove);
                idcellcontents = em.merge(idcellcontents);
            }
            Vrtuser iduser = vrtmove.getIduser();
            if (iduser != null) {
                iduser.getVrtmoveList().remove(vrtmove);
                iduser = em.merge(iduser);
            }
            em.remove(vrtmove);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vrtmove> findVrtmoveEntities() {
        return findVrtmoveEntities(true, -1, -1);
    }

    public List<Vrtmove> findVrtmoveEntities(int maxResults, int firstResult) {
        return findVrtmoveEntities(false, maxResults, firstResult);
    }

    private List<Vrtmove> findVrtmoveEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vrtmove.class));
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

    public Vrtmove findVrtmove(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vrtmove.class, id);
        } finally {
            em.close();
        }
    }

    public int getVrtmoveCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vrtmove> rt = cq.from(Vrtmove.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

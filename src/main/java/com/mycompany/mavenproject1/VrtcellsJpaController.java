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
import com.mycompany.mavenproject1.entitys.Vrtcells;
import com.mycompany.mavenproject1.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Дмитрий
 */
public class VrtcellsJpaController implements Serializable {

    public VrtcellsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vrtcells vrtcells) {
        if (vrtcells.getVrtcellcontentsList() == null) {
            vrtcells.setVrtcellcontentsList(new ArrayList<Vrtcellcontents>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vrtcellcontents> attachedVrtcellcontentsList = new ArrayList<Vrtcellcontents>();
            for (Vrtcellcontents vrtcellcontentsListVrtcellcontentsToAttach : vrtcells.getVrtcellcontentsList()) {
                vrtcellcontentsListVrtcellcontentsToAttach = em.getReference(vrtcellcontentsListVrtcellcontentsToAttach.getClass(), vrtcellcontentsListVrtcellcontentsToAttach.getId());
                attachedVrtcellcontentsList.add(vrtcellcontentsListVrtcellcontentsToAttach);
            }
            vrtcells.setVrtcellcontentsList(attachedVrtcellcontentsList);
            em.persist(vrtcells);
            for (Vrtcellcontents vrtcellcontentsListVrtcellcontents : vrtcells.getVrtcellcontentsList()) {
                Vrtcells oldIdcellsOfVrtcellcontentsListVrtcellcontents = vrtcellcontentsListVrtcellcontents.getIdcells();
                vrtcellcontentsListVrtcellcontents.setIdcells(vrtcells);
                vrtcellcontentsListVrtcellcontents = em.merge(vrtcellcontentsListVrtcellcontents);
                if (oldIdcellsOfVrtcellcontentsListVrtcellcontents != null) {
                    oldIdcellsOfVrtcellcontentsListVrtcellcontents.getVrtcellcontentsList().remove(vrtcellcontentsListVrtcellcontents);
                    oldIdcellsOfVrtcellcontentsListVrtcellcontents = em.merge(oldIdcellsOfVrtcellcontentsListVrtcellcontents);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vrtcells vrtcells) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtcells persistentVrtcells = em.find(Vrtcells.class, vrtcells.getId());
            List<Vrtcellcontents> vrtcellcontentsListOld = persistentVrtcells.getVrtcellcontentsList();
            List<Vrtcellcontents> vrtcellcontentsListNew = vrtcells.getVrtcellcontentsList();
            List<Vrtcellcontents> attachedVrtcellcontentsListNew = new ArrayList<Vrtcellcontents>();
            for (Vrtcellcontents vrtcellcontentsListNewVrtcellcontentsToAttach : vrtcellcontentsListNew) {
                vrtcellcontentsListNewVrtcellcontentsToAttach = em.getReference(vrtcellcontentsListNewVrtcellcontentsToAttach.getClass(), vrtcellcontentsListNewVrtcellcontentsToAttach.getId());
                attachedVrtcellcontentsListNew.add(vrtcellcontentsListNewVrtcellcontentsToAttach);
            }
            vrtcellcontentsListNew = attachedVrtcellcontentsListNew;
            vrtcells.setVrtcellcontentsList(vrtcellcontentsListNew);
            vrtcells = em.merge(vrtcells);
            for (Vrtcellcontents vrtcellcontentsListOldVrtcellcontents : vrtcellcontentsListOld) {
                if (!vrtcellcontentsListNew.contains(vrtcellcontentsListOldVrtcellcontents)) {
                    vrtcellcontentsListOldVrtcellcontents.setIdcells(null);
                    vrtcellcontentsListOldVrtcellcontents = em.merge(vrtcellcontentsListOldVrtcellcontents);
                }
            }
            for (Vrtcellcontents vrtcellcontentsListNewVrtcellcontents : vrtcellcontentsListNew) {
                if (!vrtcellcontentsListOld.contains(vrtcellcontentsListNewVrtcellcontents)) {
                    Vrtcells oldIdcellsOfVrtcellcontentsListNewVrtcellcontents = vrtcellcontentsListNewVrtcellcontents.getIdcells();
                    vrtcellcontentsListNewVrtcellcontents.setIdcells(vrtcells);
                    vrtcellcontentsListNewVrtcellcontents = em.merge(vrtcellcontentsListNewVrtcellcontents);
                    if (oldIdcellsOfVrtcellcontentsListNewVrtcellcontents != null && !oldIdcellsOfVrtcellcontentsListNewVrtcellcontents.equals(vrtcells)) {
                        oldIdcellsOfVrtcellcontentsListNewVrtcellcontents.getVrtcellcontentsList().remove(vrtcellcontentsListNewVrtcellcontents);
                        oldIdcellsOfVrtcellcontentsListNewVrtcellcontents = em.merge(oldIdcellsOfVrtcellcontentsListNewVrtcellcontents);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vrtcells.getId();
                if (findVrtcells(id) == null) {
                    throw new NonexistentEntityException("The vrtcells with id " + id + " no longer exists.");
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
            Vrtcells vrtcells;
            try {
                vrtcells = em.getReference(Vrtcells.class, id);
                vrtcells.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vrtcells with id " + id + " no longer exists.", enfe);
            }
            List<Vrtcellcontents> vrtcellcontentsList = vrtcells.getVrtcellcontentsList();
            for (Vrtcellcontents vrtcellcontentsListVrtcellcontents : vrtcellcontentsList) {
                vrtcellcontentsListVrtcellcontents.setIdcells(null);
                vrtcellcontentsListVrtcellcontents = em.merge(vrtcellcontentsListVrtcellcontents);
            }
            em.remove(vrtcells);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vrtcells> findVrtcellsEntities() {
        return findVrtcellsEntities(true, -1, -1);
    }

    public List<Vrtcells> findVrtcellsEntities(int maxResults, int firstResult) {
        return findVrtcellsEntities(false, maxResults, firstResult);
    }

    private List<Vrtcells> findVrtcellsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vrtcells.class));
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

    public Vrtcells findVrtcells(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vrtcells.class, id);
        } finally {
            em.close();
        }
    }

    public int getVrtcellsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vrtcells> rt = cq.from(Vrtcells.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

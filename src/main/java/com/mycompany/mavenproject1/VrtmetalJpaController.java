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
import com.mycompany.mavenproject1.entitys.Vrtuser;
import com.mycompany.mavenproject1.entitys.Vrtcellcontents;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.mavenproject1.entitys.Vrtbacket;
import com.mycompany.mavenproject1.entitys.Vrtmetal;
import com.mycompany.mavenproject1.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Дмитрий
 */
public class VrtmetalJpaController implements Serializable {

    public VrtmetalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vrtmetal vrtmetal) {
        if (vrtmetal.getVrtcellcontentsList() == null) {
            vrtmetal.setVrtcellcontentsList(new ArrayList<Vrtcellcontents>());
        }
        if (vrtmetal.getVrtbacketList() == null) {
            vrtmetal.setVrtbacketList(new ArrayList<Vrtbacket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtuser iduser = vrtmetal.getIduser();
            if (iduser != null) {
                iduser = em.getReference(iduser.getClass(), iduser.getId());
                vrtmetal.setIduser(iduser);
            }
            List<Vrtcellcontents> attachedVrtcellcontentsList = new ArrayList<Vrtcellcontents>();
            for (Vrtcellcontents vrtcellcontentsListVrtcellcontentsToAttach : vrtmetal.getVrtcellcontentsList()) {
                vrtcellcontentsListVrtcellcontentsToAttach = em.getReference(vrtcellcontentsListVrtcellcontentsToAttach.getClass(), vrtcellcontentsListVrtcellcontentsToAttach.getId());
                attachedVrtcellcontentsList.add(vrtcellcontentsListVrtcellcontentsToAttach);
            }
            vrtmetal.setVrtcellcontentsList(attachedVrtcellcontentsList);
            List<Vrtbacket> attachedVrtbacketList = new ArrayList<Vrtbacket>();
            for (Vrtbacket vrtbacketListVrtbacketToAttach : vrtmetal.getVrtbacketList()) {
                vrtbacketListVrtbacketToAttach = em.getReference(vrtbacketListVrtbacketToAttach.getClass(), vrtbacketListVrtbacketToAttach.getId());
                attachedVrtbacketList.add(vrtbacketListVrtbacketToAttach);
            }
            vrtmetal.setVrtbacketList(attachedVrtbacketList);
            em.persist(vrtmetal);
            if (iduser != null) {
                iduser.getVrtmetalList().add(vrtmetal);
                iduser = em.merge(iduser);
            }
            for (Vrtcellcontents vrtcellcontentsListVrtcellcontents : vrtmetal.getVrtcellcontentsList()) {
                Vrtmetal oldIdmetalOfVrtcellcontentsListVrtcellcontents = vrtcellcontentsListVrtcellcontents.getIdmetal();
                vrtcellcontentsListVrtcellcontents.setIdmetal(vrtmetal);
                vrtcellcontentsListVrtcellcontents = em.merge(vrtcellcontentsListVrtcellcontents);
                if (oldIdmetalOfVrtcellcontentsListVrtcellcontents != null) {
                    oldIdmetalOfVrtcellcontentsListVrtcellcontents.getVrtcellcontentsList().remove(vrtcellcontentsListVrtcellcontents);
                    oldIdmetalOfVrtcellcontentsListVrtcellcontents = em.merge(oldIdmetalOfVrtcellcontentsListVrtcellcontents);
                }
            }
            for (Vrtbacket vrtbacketListVrtbacket : vrtmetal.getVrtbacketList()) {
                Vrtmetal oldIdmetalOfVrtbacketListVrtbacket = vrtbacketListVrtbacket.getIdmetal();
                vrtbacketListVrtbacket.setIdmetal(vrtmetal);
                vrtbacketListVrtbacket = em.merge(vrtbacketListVrtbacket);
                if (oldIdmetalOfVrtbacketListVrtbacket != null) {
                    oldIdmetalOfVrtbacketListVrtbacket.getVrtbacketList().remove(vrtbacketListVrtbacket);
                    oldIdmetalOfVrtbacketListVrtbacket = em.merge(oldIdmetalOfVrtbacketListVrtbacket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vrtmetal vrtmetal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtmetal persistentVrtmetal = em.find(Vrtmetal.class, vrtmetal.getId());
            Vrtuser iduserOld = persistentVrtmetal.getIduser();
            Vrtuser iduserNew = vrtmetal.getIduser();
            List<Vrtcellcontents> vrtcellcontentsListOld = persistentVrtmetal.getVrtcellcontentsList();
            List<Vrtcellcontents> vrtcellcontentsListNew = vrtmetal.getVrtcellcontentsList();
            List<Vrtbacket> vrtbacketListOld = persistentVrtmetal.getVrtbacketList();
            List<Vrtbacket> vrtbacketListNew = vrtmetal.getVrtbacketList();
            if (iduserNew != null) {
                iduserNew = em.getReference(iduserNew.getClass(), iduserNew.getId());
                vrtmetal.setIduser(iduserNew);
            }
            List<Vrtcellcontents> attachedVrtcellcontentsListNew = new ArrayList<Vrtcellcontents>();
            for (Vrtcellcontents vrtcellcontentsListNewVrtcellcontentsToAttach : vrtcellcontentsListNew) {
                vrtcellcontentsListNewVrtcellcontentsToAttach = em.getReference(vrtcellcontentsListNewVrtcellcontentsToAttach.getClass(), vrtcellcontentsListNewVrtcellcontentsToAttach.getId());
                attachedVrtcellcontentsListNew.add(vrtcellcontentsListNewVrtcellcontentsToAttach);
            }
            vrtcellcontentsListNew = attachedVrtcellcontentsListNew;
            vrtmetal.setVrtcellcontentsList(vrtcellcontentsListNew);
            List<Vrtbacket> attachedVrtbacketListNew = new ArrayList<Vrtbacket>();
            for (Vrtbacket vrtbacketListNewVrtbacketToAttach : vrtbacketListNew) {
                vrtbacketListNewVrtbacketToAttach = em.getReference(vrtbacketListNewVrtbacketToAttach.getClass(), vrtbacketListNewVrtbacketToAttach.getId());
                attachedVrtbacketListNew.add(vrtbacketListNewVrtbacketToAttach);
            }
            vrtbacketListNew = attachedVrtbacketListNew;
            vrtmetal.setVrtbacketList(vrtbacketListNew);
            vrtmetal = em.merge(vrtmetal);
            if (iduserOld != null && !iduserOld.equals(iduserNew)) {
                iduserOld.getVrtmetalList().remove(vrtmetal);
                iduserOld = em.merge(iduserOld);
            }
            if (iduserNew != null && !iduserNew.equals(iduserOld)) {
                iduserNew.getVrtmetalList().add(vrtmetal);
                iduserNew = em.merge(iduserNew);
            }
            for (Vrtcellcontents vrtcellcontentsListOldVrtcellcontents : vrtcellcontentsListOld) {
                if (!vrtcellcontentsListNew.contains(vrtcellcontentsListOldVrtcellcontents)) {
                    vrtcellcontentsListOldVrtcellcontents.setIdmetal(null);
                    vrtcellcontentsListOldVrtcellcontents = em.merge(vrtcellcontentsListOldVrtcellcontents);
                }
            }
            for (Vrtcellcontents vrtcellcontentsListNewVrtcellcontents : vrtcellcontentsListNew) {
                if (!vrtcellcontentsListOld.contains(vrtcellcontentsListNewVrtcellcontents)) {
                    Vrtmetal oldIdmetalOfVrtcellcontentsListNewVrtcellcontents = vrtcellcontentsListNewVrtcellcontents.getIdmetal();
                    vrtcellcontentsListNewVrtcellcontents.setIdmetal(vrtmetal);
                    vrtcellcontentsListNewVrtcellcontents = em.merge(vrtcellcontentsListNewVrtcellcontents);
                    if (oldIdmetalOfVrtcellcontentsListNewVrtcellcontents != null && !oldIdmetalOfVrtcellcontentsListNewVrtcellcontents.equals(vrtmetal)) {
                        oldIdmetalOfVrtcellcontentsListNewVrtcellcontents.getVrtcellcontentsList().remove(vrtcellcontentsListNewVrtcellcontents);
                        oldIdmetalOfVrtcellcontentsListNewVrtcellcontents = em.merge(oldIdmetalOfVrtcellcontentsListNewVrtcellcontents);
                    }
                }
            }
            for (Vrtbacket vrtbacketListOldVrtbacket : vrtbacketListOld) {
                if (!vrtbacketListNew.contains(vrtbacketListOldVrtbacket)) {
                    vrtbacketListOldVrtbacket.setIdmetal(null);
                    vrtbacketListOldVrtbacket = em.merge(vrtbacketListOldVrtbacket);
                }
            }
            for (Vrtbacket vrtbacketListNewVrtbacket : vrtbacketListNew) {
                if (!vrtbacketListOld.contains(vrtbacketListNewVrtbacket)) {
                    Vrtmetal oldIdmetalOfVrtbacketListNewVrtbacket = vrtbacketListNewVrtbacket.getIdmetal();
                    vrtbacketListNewVrtbacket.setIdmetal(vrtmetal);
                    vrtbacketListNewVrtbacket = em.merge(vrtbacketListNewVrtbacket);
                    if (oldIdmetalOfVrtbacketListNewVrtbacket != null && !oldIdmetalOfVrtbacketListNewVrtbacket.equals(vrtmetal)) {
                        oldIdmetalOfVrtbacketListNewVrtbacket.getVrtbacketList().remove(vrtbacketListNewVrtbacket);
                        oldIdmetalOfVrtbacketListNewVrtbacket = em.merge(oldIdmetalOfVrtbacketListNewVrtbacket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vrtmetal.getId();
                if (findVrtmetal(id) == null) {
                    throw new NonexistentEntityException("The vrtmetal with id " + id + " no longer exists.");
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
            Vrtmetal vrtmetal;
            try {
                vrtmetal = em.getReference(Vrtmetal.class, id);
                vrtmetal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vrtmetal with id " + id + " no longer exists.", enfe);
            }
            Vrtuser iduser = vrtmetal.getIduser();
            if (iduser != null) {
                iduser.getVrtmetalList().remove(vrtmetal);
                iduser = em.merge(iduser);
            }
            List<Vrtcellcontents> vrtcellcontentsList = vrtmetal.getVrtcellcontentsList();
            for (Vrtcellcontents vrtcellcontentsListVrtcellcontents : vrtcellcontentsList) {
                vrtcellcontentsListVrtcellcontents.setIdmetal(null);
                vrtcellcontentsListVrtcellcontents = em.merge(vrtcellcontentsListVrtcellcontents);
            }
            List<Vrtbacket> vrtbacketList = vrtmetal.getVrtbacketList();
            for (Vrtbacket vrtbacketListVrtbacket : vrtbacketList) {
                vrtbacketListVrtbacket.setIdmetal(null);
                vrtbacketListVrtbacket = em.merge(vrtbacketListVrtbacket);
            }
            em.remove(vrtmetal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vrtmetal> findVrtmetalEntities() {
        return findVrtmetalEntities(true, -1, -1);
    }

    public List<Vrtmetal> findVrtmetalEntities(int maxResults, int firstResult) {
        return findVrtmetalEntities(false, maxResults, firstResult);
    }

    private List<Vrtmetal> findVrtmetalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vrtmetal.class));
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

    public Vrtmetal findVrtmetal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vrtmetal.class, id);
        } finally {
            em.close();
        }
    }

    public int getVrtmetalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vrtmetal> rt = cq.from(Vrtmetal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

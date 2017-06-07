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
import com.mycompany.mavenproject1.entitys.Vrttempbacket;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.mavenproject1.entitys.Vrtmove;
import com.mycompany.mavenproject1.entitys.Vrtcellcontents;
import com.mycompany.mavenproject1.entitys.Vrtbacket;
import com.mycompany.mavenproject1.entitys.Vrtmetal;
import com.mycompany.mavenproject1.entitys.Vrtuser;
import com.mycompany.mavenproject1.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Дмитрий
 */
public class VrtuserJpaController implements Serializable {

    public VrtuserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vrtuser vrtuser) {
        if (vrtuser.getVrttempbacketList() == null) {
            vrtuser.setVrttempbacketList(new ArrayList<Vrttempbacket>());
        }
        if (vrtuser.getVrtmoveList() == null) {
            vrtuser.setVrtmoveList(new ArrayList<Vrtmove>());
        }
        if (vrtuser.getVrtcellcontentsList() == null) {
            vrtuser.setVrtcellcontentsList(new ArrayList<Vrtcellcontents>());
        }
        if (vrtuser.getVrtbacketList() == null) {
            vrtuser.setVrtbacketList(new ArrayList<Vrtbacket>());
        }
        if (vrtuser.getVrtmetalList() == null) {
            vrtuser.setVrtmetalList(new ArrayList<Vrtmetal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vrttempbacket> attachedVrttempbacketList = new ArrayList<Vrttempbacket>();
            for (Vrttempbacket vrttempbacketListVrttempbacketToAttach : vrtuser.getVrttempbacketList()) {
                vrttempbacketListVrttempbacketToAttach = em.getReference(vrttempbacketListVrttempbacketToAttach.getClass(), vrttempbacketListVrttempbacketToAttach.getId());
                attachedVrttempbacketList.add(vrttempbacketListVrttempbacketToAttach);
            }
            vrtuser.setVrttempbacketList(attachedVrttempbacketList);
            List<Vrtmove> attachedVrtmoveList = new ArrayList<Vrtmove>();
            for (Vrtmove vrtmoveListVrtmoveToAttach : vrtuser.getVrtmoveList()) {
                vrtmoveListVrtmoveToAttach = em.getReference(vrtmoveListVrtmoveToAttach.getClass(), vrtmoveListVrtmoveToAttach.getId());
                attachedVrtmoveList.add(vrtmoveListVrtmoveToAttach);
            }
            vrtuser.setVrtmoveList(attachedVrtmoveList);
            List<Vrtcellcontents> attachedVrtcellcontentsList = new ArrayList<Vrtcellcontents>();
            for (Vrtcellcontents vrtcellcontentsListVrtcellcontentsToAttach : vrtuser.getVrtcellcontentsList()) {
                vrtcellcontentsListVrtcellcontentsToAttach = em.getReference(vrtcellcontentsListVrtcellcontentsToAttach.getClass(), vrtcellcontentsListVrtcellcontentsToAttach.getId());
                attachedVrtcellcontentsList.add(vrtcellcontentsListVrtcellcontentsToAttach);
            }
            vrtuser.setVrtcellcontentsList(attachedVrtcellcontentsList);
            List<Vrtbacket> attachedVrtbacketList = new ArrayList<Vrtbacket>();
            for (Vrtbacket vrtbacketListVrtbacketToAttach : vrtuser.getVrtbacketList()) {
                vrtbacketListVrtbacketToAttach = em.getReference(vrtbacketListVrtbacketToAttach.getClass(), vrtbacketListVrtbacketToAttach.getId());
                attachedVrtbacketList.add(vrtbacketListVrtbacketToAttach);
            }
            vrtuser.setVrtbacketList(attachedVrtbacketList);
            List<Vrtmetal> attachedVrtmetalList = new ArrayList<Vrtmetal>();
            for (Vrtmetal vrtmetalListVrtmetalToAttach : vrtuser.getVrtmetalList()) {
                vrtmetalListVrtmetalToAttach = em.getReference(vrtmetalListVrtmetalToAttach.getClass(), vrtmetalListVrtmetalToAttach.getId());
                attachedVrtmetalList.add(vrtmetalListVrtmetalToAttach);
            }
            vrtuser.setVrtmetalList(attachedVrtmetalList);
            em.persist(vrtuser);
            for (Vrttempbacket vrttempbacketListVrttempbacket : vrtuser.getVrttempbacketList()) {
                Vrtuser oldIduserOfVrttempbacketListVrttempbacket = vrttempbacketListVrttempbacket.getIduser();
                vrttempbacketListVrttempbacket.setIduser(vrtuser);
                vrttempbacketListVrttempbacket = em.merge(vrttempbacketListVrttempbacket);
                if (oldIduserOfVrttempbacketListVrttempbacket != null) {
                    oldIduserOfVrttempbacketListVrttempbacket.getVrttempbacketList().remove(vrttempbacketListVrttempbacket);
                    oldIduserOfVrttempbacketListVrttempbacket = em.merge(oldIduserOfVrttempbacketListVrttempbacket);
                }
            }
            for (Vrtmove vrtmoveListVrtmove : vrtuser.getVrtmoveList()) {
                Vrtuser oldIduserOfVrtmoveListVrtmove = vrtmoveListVrtmove.getIduser();
                vrtmoveListVrtmove.setIduser(vrtuser);
                vrtmoveListVrtmove = em.merge(vrtmoveListVrtmove);
                if (oldIduserOfVrtmoveListVrtmove != null) {
                    oldIduserOfVrtmoveListVrtmove.getVrtmoveList().remove(vrtmoveListVrtmove);
                    oldIduserOfVrtmoveListVrtmove = em.merge(oldIduserOfVrtmoveListVrtmove);
                }
            }
            for (Vrtcellcontents vrtcellcontentsListVrtcellcontents : vrtuser.getVrtcellcontentsList()) {
                Vrtuser oldIduserOfVrtcellcontentsListVrtcellcontents = vrtcellcontentsListVrtcellcontents.getIduser();
                vrtcellcontentsListVrtcellcontents.setIduser(vrtuser);
                vrtcellcontentsListVrtcellcontents = em.merge(vrtcellcontentsListVrtcellcontents);
                if (oldIduserOfVrtcellcontentsListVrtcellcontents != null) {
                    oldIduserOfVrtcellcontentsListVrtcellcontents.getVrtcellcontentsList().remove(vrtcellcontentsListVrtcellcontents);
                    oldIduserOfVrtcellcontentsListVrtcellcontents = em.merge(oldIduserOfVrtcellcontentsListVrtcellcontents);
                }
            }
            for (Vrtbacket vrtbacketListVrtbacket : vrtuser.getVrtbacketList()) {
                Vrtuser oldIduserOfVrtbacketListVrtbacket = vrtbacketListVrtbacket.getIduser();
                vrtbacketListVrtbacket.setIduser(vrtuser);
                vrtbacketListVrtbacket = em.merge(vrtbacketListVrtbacket);
                if (oldIduserOfVrtbacketListVrtbacket != null) {
                    oldIduserOfVrtbacketListVrtbacket.getVrtbacketList().remove(vrtbacketListVrtbacket);
                    oldIduserOfVrtbacketListVrtbacket = em.merge(oldIduserOfVrtbacketListVrtbacket);
                }
            }
            for (Vrtmetal vrtmetalListVrtmetal : vrtuser.getVrtmetalList()) {
                Vrtuser oldIduserOfVrtmetalListVrtmetal = vrtmetalListVrtmetal.getIduser();
                vrtmetalListVrtmetal.setIduser(vrtuser);
                vrtmetalListVrtmetal = em.merge(vrtmetalListVrtmetal);
                if (oldIduserOfVrtmetalListVrtmetal != null) {
                    oldIduserOfVrtmetalListVrtmetal.getVrtmetalList().remove(vrtmetalListVrtmetal);
                    oldIduserOfVrtmetalListVrtmetal = em.merge(oldIduserOfVrtmetalListVrtmetal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vrtuser vrtuser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtuser persistentVrtuser = em.find(Vrtuser.class, vrtuser.getId());
            List<Vrttempbacket> vrttempbacketListOld = persistentVrtuser.getVrttempbacketList();
            List<Vrttempbacket> vrttempbacketListNew = vrtuser.getVrttempbacketList();
            List<Vrtmove> vrtmoveListOld = persistentVrtuser.getVrtmoveList();
            List<Vrtmove> vrtmoveListNew = vrtuser.getVrtmoveList();
            List<Vrtcellcontents> vrtcellcontentsListOld = persistentVrtuser.getVrtcellcontentsList();
            List<Vrtcellcontents> vrtcellcontentsListNew = vrtuser.getVrtcellcontentsList();
            List<Vrtbacket> vrtbacketListOld = persistentVrtuser.getVrtbacketList();
            List<Vrtbacket> vrtbacketListNew = vrtuser.getVrtbacketList();
            List<Vrtmetal> vrtmetalListOld = persistentVrtuser.getVrtmetalList();
            List<Vrtmetal> vrtmetalListNew = vrtuser.getVrtmetalList();
            List<Vrttempbacket> attachedVrttempbacketListNew = new ArrayList<Vrttempbacket>();
            for (Vrttempbacket vrttempbacketListNewVrttempbacketToAttach : vrttempbacketListNew) {
                vrttempbacketListNewVrttempbacketToAttach = em.getReference(vrttempbacketListNewVrttempbacketToAttach.getClass(), vrttempbacketListNewVrttempbacketToAttach.getId());
                attachedVrttempbacketListNew.add(vrttempbacketListNewVrttempbacketToAttach);
            }
            vrttempbacketListNew = attachedVrttempbacketListNew;
            vrtuser.setVrttempbacketList(vrttempbacketListNew);
            List<Vrtmove> attachedVrtmoveListNew = new ArrayList<Vrtmove>();
            for (Vrtmove vrtmoveListNewVrtmoveToAttach : vrtmoveListNew) {
                vrtmoveListNewVrtmoveToAttach = em.getReference(vrtmoveListNewVrtmoveToAttach.getClass(), vrtmoveListNewVrtmoveToAttach.getId());
                attachedVrtmoveListNew.add(vrtmoveListNewVrtmoveToAttach);
            }
            vrtmoveListNew = attachedVrtmoveListNew;
            vrtuser.setVrtmoveList(vrtmoveListNew);
            List<Vrtcellcontents> attachedVrtcellcontentsListNew = new ArrayList<Vrtcellcontents>();
            for (Vrtcellcontents vrtcellcontentsListNewVrtcellcontentsToAttach : vrtcellcontentsListNew) {
                vrtcellcontentsListNewVrtcellcontentsToAttach = em.getReference(vrtcellcontentsListNewVrtcellcontentsToAttach.getClass(), vrtcellcontentsListNewVrtcellcontentsToAttach.getId());
                attachedVrtcellcontentsListNew.add(vrtcellcontentsListNewVrtcellcontentsToAttach);
            }
            vrtcellcontentsListNew = attachedVrtcellcontentsListNew;
            vrtuser.setVrtcellcontentsList(vrtcellcontentsListNew);
            List<Vrtbacket> attachedVrtbacketListNew = new ArrayList<Vrtbacket>();
            for (Vrtbacket vrtbacketListNewVrtbacketToAttach : vrtbacketListNew) {
                vrtbacketListNewVrtbacketToAttach = em.getReference(vrtbacketListNewVrtbacketToAttach.getClass(), vrtbacketListNewVrtbacketToAttach.getId());
                attachedVrtbacketListNew.add(vrtbacketListNewVrtbacketToAttach);
            }
            vrtbacketListNew = attachedVrtbacketListNew;
            vrtuser.setVrtbacketList(vrtbacketListNew);
            List<Vrtmetal> attachedVrtmetalListNew = new ArrayList<Vrtmetal>();
            for (Vrtmetal vrtmetalListNewVrtmetalToAttach : vrtmetalListNew) {
                vrtmetalListNewVrtmetalToAttach = em.getReference(vrtmetalListNewVrtmetalToAttach.getClass(), vrtmetalListNewVrtmetalToAttach.getId());
                attachedVrtmetalListNew.add(vrtmetalListNewVrtmetalToAttach);
            }
            vrtmetalListNew = attachedVrtmetalListNew;
            vrtuser.setVrtmetalList(vrtmetalListNew);
            vrtuser = em.merge(vrtuser);
            for (Vrttempbacket vrttempbacketListOldVrttempbacket : vrttempbacketListOld) {
                if (!vrttempbacketListNew.contains(vrttempbacketListOldVrttempbacket)) {
                    vrttempbacketListOldVrttempbacket.setIduser(null);
                    vrttempbacketListOldVrttempbacket = em.merge(vrttempbacketListOldVrttempbacket);
                }
            }
            for (Vrttempbacket vrttempbacketListNewVrttempbacket : vrttempbacketListNew) {
                if (!vrttempbacketListOld.contains(vrttempbacketListNewVrttempbacket)) {
                    Vrtuser oldIduserOfVrttempbacketListNewVrttempbacket = vrttempbacketListNewVrttempbacket.getIduser();
                    vrttempbacketListNewVrttempbacket.setIduser(vrtuser);
                    vrttempbacketListNewVrttempbacket = em.merge(vrttempbacketListNewVrttempbacket);
                    if (oldIduserOfVrttempbacketListNewVrttempbacket != null && !oldIduserOfVrttempbacketListNewVrttempbacket.equals(vrtuser)) {
                        oldIduserOfVrttempbacketListNewVrttempbacket.getVrttempbacketList().remove(vrttempbacketListNewVrttempbacket);
                        oldIduserOfVrttempbacketListNewVrttempbacket = em.merge(oldIduserOfVrttempbacketListNewVrttempbacket);
                    }
                }
            }
            for (Vrtmove vrtmoveListOldVrtmove : vrtmoveListOld) {
                if (!vrtmoveListNew.contains(vrtmoveListOldVrtmove)) {
                    vrtmoveListOldVrtmove.setIduser(null);
                    vrtmoveListOldVrtmove = em.merge(vrtmoveListOldVrtmove);
                }
            }
            for (Vrtmove vrtmoveListNewVrtmove : vrtmoveListNew) {
                if (!vrtmoveListOld.contains(vrtmoveListNewVrtmove)) {
                    Vrtuser oldIduserOfVrtmoveListNewVrtmove = vrtmoveListNewVrtmove.getIduser();
                    vrtmoveListNewVrtmove.setIduser(vrtuser);
                    vrtmoveListNewVrtmove = em.merge(vrtmoveListNewVrtmove);
                    if (oldIduserOfVrtmoveListNewVrtmove != null && !oldIduserOfVrtmoveListNewVrtmove.equals(vrtuser)) {
                        oldIduserOfVrtmoveListNewVrtmove.getVrtmoveList().remove(vrtmoveListNewVrtmove);
                        oldIduserOfVrtmoveListNewVrtmove = em.merge(oldIduserOfVrtmoveListNewVrtmove);
                    }
                }
            }
            for (Vrtcellcontents vrtcellcontentsListOldVrtcellcontents : vrtcellcontentsListOld) {
                if (!vrtcellcontentsListNew.contains(vrtcellcontentsListOldVrtcellcontents)) {
                    vrtcellcontentsListOldVrtcellcontents.setIduser(null);
                    vrtcellcontentsListOldVrtcellcontents = em.merge(vrtcellcontentsListOldVrtcellcontents);
                }
            }
            for (Vrtcellcontents vrtcellcontentsListNewVrtcellcontents : vrtcellcontentsListNew) {
                if (!vrtcellcontentsListOld.contains(vrtcellcontentsListNewVrtcellcontents)) {
                    Vrtuser oldIduserOfVrtcellcontentsListNewVrtcellcontents = vrtcellcontentsListNewVrtcellcontents.getIduser();
                    vrtcellcontentsListNewVrtcellcontents.setIduser(vrtuser);
                    vrtcellcontentsListNewVrtcellcontents = em.merge(vrtcellcontentsListNewVrtcellcontents);
                    if (oldIduserOfVrtcellcontentsListNewVrtcellcontents != null && !oldIduserOfVrtcellcontentsListNewVrtcellcontents.equals(vrtuser)) {
                        oldIduserOfVrtcellcontentsListNewVrtcellcontents.getVrtcellcontentsList().remove(vrtcellcontentsListNewVrtcellcontents);
                        oldIduserOfVrtcellcontentsListNewVrtcellcontents = em.merge(oldIduserOfVrtcellcontentsListNewVrtcellcontents);
                    }
                }
            }
            for (Vrtbacket vrtbacketListOldVrtbacket : vrtbacketListOld) {
                if (!vrtbacketListNew.contains(vrtbacketListOldVrtbacket)) {
                    vrtbacketListOldVrtbacket.setIduser(null);
                    vrtbacketListOldVrtbacket = em.merge(vrtbacketListOldVrtbacket);
                }
            }
            for (Vrtbacket vrtbacketListNewVrtbacket : vrtbacketListNew) {
                if (!vrtbacketListOld.contains(vrtbacketListNewVrtbacket)) {
                    Vrtuser oldIduserOfVrtbacketListNewVrtbacket = vrtbacketListNewVrtbacket.getIduser();
                    vrtbacketListNewVrtbacket.setIduser(vrtuser);
                    vrtbacketListNewVrtbacket = em.merge(vrtbacketListNewVrtbacket);
                    if (oldIduserOfVrtbacketListNewVrtbacket != null && !oldIduserOfVrtbacketListNewVrtbacket.equals(vrtuser)) {
                        oldIduserOfVrtbacketListNewVrtbacket.getVrtbacketList().remove(vrtbacketListNewVrtbacket);
                        oldIduserOfVrtbacketListNewVrtbacket = em.merge(oldIduserOfVrtbacketListNewVrtbacket);
                    }
                }
            }
            for (Vrtmetal vrtmetalListOldVrtmetal : vrtmetalListOld) {
                if (!vrtmetalListNew.contains(vrtmetalListOldVrtmetal)) {
                    vrtmetalListOldVrtmetal.setIduser(null);
                    vrtmetalListOldVrtmetal = em.merge(vrtmetalListOldVrtmetal);
                }
            }
            for (Vrtmetal vrtmetalListNewVrtmetal : vrtmetalListNew) {
                if (!vrtmetalListOld.contains(vrtmetalListNewVrtmetal)) {
                    Vrtuser oldIduserOfVrtmetalListNewVrtmetal = vrtmetalListNewVrtmetal.getIduser();
                    vrtmetalListNewVrtmetal.setIduser(vrtuser);
                    vrtmetalListNewVrtmetal = em.merge(vrtmetalListNewVrtmetal);
                    if (oldIduserOfVrtmetalListNewVrtmetal != null && !oldIduserOfVrtmetalListNewVrtmetal.equals(vrtuser)) {
                        oldIduserOfVrtmetalListNewVrtmetal.getVrtmetalList().remove(vrtmetalListNewVrtmetal);
                        oldIduserOfVrtmetalListNewVrtmetal = em.merge(oldIduserOfVrtmetalListNewVrtmetal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vrtuser.getId();
                if (findVrtuser(id) == null) {
                    throw new NonexistentEntityException("The vrtuser with id " + id + " no longer exists.");
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
            Vrtuser vrtuser;
            try {
                vrtuser = em.getReference(Vrtuser.class, id);
                vrtuser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vrtuser with id " + id + " no longer exists.", enfe);
            }
            List<Vrttempbacket> vrttempbacketList = vrtuser.getVrttempbacketList();
            for (Vrttempbacket vrttempbacketListVrttempbacket : vrttempbacketList) {
                vrttempbacketListVrttempbacket.setIduser(null);
                vrttempbacketListVrttempbacket = em.merge(vrttempbacketListVrttempbacket);
            }
            List<Vrtmove> vrtmoveList = vrtuser.getVrtmoveList();
            for (Vrtmove vrtmoveListVrtmove : vrtmoveList) {
                vrtmoveListVrtmove.setIduser(null);
                vrtmoveListVrtmove = em.merge(vrtmoveListVrtmove);
            }
            List<Vrtcellcontents> vrtcellcontentsList = vrtuser.getVrtcellcontentsList();
            for (Vrtcellcontents vrtcellcontentsListVrtcellcontents : vrtcellcontentsList) {
                vrtcellcontentsListVrtcellcontents.setIduser(null);
                vrtcellcontentsListVrtcellcontents = em.merge(vrtcellcontentsListVrtcellcontents);
            }
            List<Vrtbacket> vrtbacketList = vrtuser.getVrtbacketList();
            for (Vrtbacket vrtbacketListVrtbacket : vrtbacketList) {
                vrtbacketListVrtbacket.setIduser(null);
                vrtbacketListVrtbacket = em.merge(vrtbacketListVrtbacket);
            }
            List<Vrtmetal> vrtmetalList = vrtuser.getVrtmetalList();
            for (Vrtmetal vrtmetalListVrtmetal : vrtmetalList) {
                vrtmetalListVrtmetal.setIduser(null);
                vrtmetalListVrtmetal = em.merge(vrtmetalListVrtmetal);
            }
            em.remove(vrtuser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vrtuser> findVrtuserEntities() {
        return findVrtuserEntities(true, -1, -1);
    }

    public List<Vrtuser> findVrtuserEntities(int maxResults, int firstResult) {
        return findVrtuserEntities(false, maxResults, firstResult);
    }

    private List<Vrtuser> findVrtuserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vrtuser.class));
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

    public Vrtuser findVrtuser(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vrtuser.class, id);
        } finally {
            em.close();
        }
    }

    public int getVrtuserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vrtuser> rt = cq.from(Vrtuser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.entitys.Vrtcellcontents;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.mavenproject1.entitys.Vrtcells;
import com.mycompany.mavenproject1.entitys.Vrtmetal;
import com.mycompany.mavenproject1.entitys.Vrtuser;
import com.mycompany.mavenproject1.entitys.Vrttempbacket;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.mavenproject1.entitys.Vrtmove;
import com.mycompany.mavenproject1.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Дмитрий
 */
public class VrtcellcontentsJpaController implements Serializable {

    public VrtcellcontentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vrtcellcontents vrtcellcontents) {
        if (vrtcellcontents.getVrttempbacketList() == null) {
            vrtcellcontents.setVrttempbacketList(new ArrayList<Vrttempbacket>());
        }
        if (vrtcellcontents.getVrtmoveList() == null) {
            vrtcellcontents.setVrtmoveList(new ArrayList<Vrtmove>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtcells idcells = vrtcellcontents.getIdcells();
            if (idcells != null) {
                idcells = em.getReference(idcells.getClass(), idcells.getId());
                vrtcellcontents.setIdcells(idcells);
            }
            Vrtmetal idmetal = vrtcellcontents.getIdmetal();
            if (idmetal != null) {
                idmetal = em.getReference(idmetal.getClass(), idmetal.getId());
                vrtcellcontents.setIdmetal(idmetal);
            }
            Vrtuser iduser = vrtcellcontents.getIduser();
            if (iduser != null) {
                iduser = em.getReference(iduser.getClass(), iduser.getId());
                vrtcellcontents.setIduser(iduser);
            }
            List<Vrttempbacket> attachedVrttempbacketList = new ArrayList<Vrttempbacket>();
            for (Vrttempbacket vrttempbacketListVrttempbacketToAttach : vrtcellcontents.getVrttempbacketList()) {
                vrttempbacketListVrttempbacketToAttach = em.getReference(vrttempbacketListVrttempbacketToAttach.getClass(), vrttempbacketListVrttempbacketToAttach.getId());
                attachedVrttempbacketList.add(vrttempbacketListVrttempbacketToAttach);
            }
            vrtcellcontents.setVrttempbacketList(attachedVrttempbacketList);
            List<Vrtmove> attachedVrtmoveList = new ArrayList<Vrtmove>();
            for (Vrtmove vrtmoveListVrtmoveToAttach : vrtcellcontents.getVrtmoveList()) {
                vrtmoveListVrtmoveToAttach = em.getReference(vrtmoveListVrtmoveToAttach.getClass(), vrtmoveListVrtmoveToAttach.getId());
                attachedVrtmoveList.add(vrtmoveListVrtmoveToAttach);
            }
            vrtcellcontents.setVrtmoveList(attachedVrtmoveList);
            em.persist(vrtcellcontents);
            if (idcells != null) {
                idcells.getVrtcellcontentsList().add(vrtcellcontents);
                idcells = em.merge(idcells);
            }
            if (idmetal != null) {
                idmetal.getVrtcellcontentsList().add(vrtcellcontents);
                idmetal = em.merge(idmetal);
            }
            if (iduser != null) {
                iduser.getVrtcellcontentsList().add(vrtcellcontents);
                iduser = em.merge(iduser);
            }
            for (Vrttempbacket vrttempbacketListVrttempbacket : vrtcellcontents.getVrttempbacketList()) {
                Vrtcellcontents oldIdcellcontentsOfVrttempbacketListVrttempbacket = vrttempbacketListVrttempbacket.getIdcellcontents();
                vrttempbacketListVrttempbacket.setIdcellcontents(vrtcellcontents);
                vrttempbacketListVrttempbacket = em.merge(vrttempbacketListVrttempbacket);
                if (oldIdcellcontentsOfVrttempbacketListVrttempbacket != null) {
                    oldIdcellcontentsOfVrttempbacketListVrttempbacket.getVrttempbacketList().remove(vrttempbacketListVrttempbacket);
                    oldIdcellcontentsOfVrttempbacketListVrttempbacket = em.merge(oldIdcellcontentsOfVrttempbacketListVrttempbacket);
                }
            }
            for (Vrtmove vrtmoveListVrtmove : vrtcellcontents.getVrtmoveList()) {
                Vrtcellcontents oldIdcellcontentsOfVrtmoveListVrtmove = vrtmoveListVrtmove.getIdcellcontents();
                vrtmoveListVrtmove.setIdcellcontents(vrtcellcontents);
                vrtmoveListVrtmove = em.merge(vrtmoveListVrtmove);
                if (oldIdcellcontentsOfVrtmoveListVrtmove != null) {
                    oldIdcellcontentsOfVrtmoveListVrtmove.getVrtmoveList().remove(vrtmoveListVrtmove);
                    oldIdcellcontentsOfVrtmoveListVrtmove = em.merge(oldIdcellcontentsOfVrtmoveListVrtmove);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vrtcellcontents vrtcellcontents) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vrtcellcontents persistentVrtcellcontents = em.find(Vrtcellcontents.class, vrtcellcontents.getId());
            Vrtcells idcellsOld = persistentVrtcellcontents.getIdcells();
            Vrtcells idcellsNew = vrtcellcontents.getIdcells();
            Vrtmetal idmetalOld = persistentVrtcellcontents.getIdmetal();
            Vrtmetal idmetalNew = vrtcellcontents.getIdmetal();
            Vrtuser iduserOld = persistentVrtcellcontents.getIduser();
            Vrtuser iduserNew = vrtcellcontents.getIduser();
            List<Vrttempbacket> vrttempbacketListOld = persistentVrtcellcontents.getVrttempbacketList();
            List<Vrttempbacket> vrttempbacketListNew = vrtcellcontents.getVrttempbacketList();
            List<Vrtmove> vrtmoveListOld = persistentVrtcellcontents.getVrtmoveList();
            List<Vrtmove> vrtmoveListNew = vrtcellcontents.getVrtmoveList();
            if (idcellsNew != null) {
                idcellsNew = em.getReference(idcellsNew.getClass(), idcellsNew.getId());
                vrtcellcontents.setIdcells(idcellsNew);
            }
            if (idmetalNew != null) {
                idmetalNew = em.getReference(idmetalNew.getClass(), idmetalNew.getId());
                vrtcellcontents.setIdmetal(idmetalNew);
            }
            if (iduserNew != null) {
                iduserNew = em.getReference(iduserNew.getClass(), iduserNew.getId());
                vrtcellcontents.setIduser(iduserNew);
            }
            List<Vrttempbacket> attachedVrttempbacketListNew = new ArrayList<Vrttempbacket>();
            for (Vrttempbacket vrttempbacketListNewVrttempbacketToAttach : vrttempbacketListNew) {
                vrttempbacketListNewVrttempbacketToAttach = em.getReference(vrttempbacketListNewVrttempbacketToAttach.getClass(), vrttempbacketListNewVrttempbacketToAttach.getId());
                attachedVrttempbacketListNew.add(vrttempbacketListNewVrttempbacketToAttach);
            }
            vrttempbacketListNew = attachedVrttempbacketListNew;
            vrtcellcontents.setVrttempbacketList(vrttempbacketListNew);
            List<Vrtmove> attachedVrtmoveListNew = new ArrayList<Vrtmove>();
            for (Vrtmove vrtmoveListNewVrtmoveToAttach : vrtmoveListNew) {
                vrtmoveListNewVrtmoveToAttach = em.getReference(vrtmoveListNewVrtmoveToAttach.getClass(), vrtmoveListNewVrtmoveToAttach.getId());
                attachedVrtmoveListNew.add(vrtmoveListNewVrtmoveToAttach);
            }
            vrtmoveListNew = attachedVrtmoveListNew;
            vrtcellcontents.setVrtmoveList(vrtmoveListNew);
            vrtcellcontents = em.merge(vrtcellcontents);
            if (idcellsOld != null && !idcellsOld.equals(idcellsNew)) {
                idcellsOld.getVrtcellcontentsList().remove(vrtcellcontents);
                idcellsOld = em.merge(idcellsOld);
            }
            if (idcellsNew != null && !idcellsNew.equals(idcellsOld)) {
                idcellsNew.getVrtcellcontentsList().add(vrtcellcontents);
                idcellsNew = em.merge(idcellsNew);
            }
            if (idmetalOld != null && !idmetalOld.equals(idmetalNew)) {
                idmetalOld.getVrtcellcontentsList().remove(vrtcellcontents);
                idmetalOld = em.merge(idmetalOld);
            }
            if (idmetalNew != null && !idmetalNew.equals(idmetalOld)) {
                idmetalNew.getVrtcellcontentsList().add(vrtcellcontents);
                idmetalNew = em.merge(idmetalNew);
            }
            if (iduserOld != null && !iduserOld.equals(iduserNew)) {
                iduserOld.getVrtcellcontentsList().remove(vrtcellcontents);
                iduserOld = em.merge(iduserOld);
            }
            if (iduserNew != null && !iduserNew.equals(iduserOld)) {
                iduserNew.getVrtcellcontentsList().add(vrtcellcontents);
                iduserNew = em.merge(iduserNew);
            }
            for (Vrttempbacket vrttempbacketListOldVrttempbacket : vrttempbacketListOld) {
                if (!vrttempbacketListNew.contains(vrttempbacketListOldVrttempbacket)) {
                    vrttempbacketListOldVrttempbacket.setIdcellcontents(null);
                    vrttempbacketListOldVrttempbacket = em.merge(vrttempbacketListOldVrttempbacket);
                }
            }
            for (Vrttempbacket vrttempbacketListNewVrttempbacket : vrttempbacketListNew) {
                if (!vrttempbacketListOld.contains(vrttempbacketListNewVrttempbacket)) {
                    Vrtcellcontents oldIdcellcontentsOfVrttempbacketListNewVrttempbacket = vrttempbacketListNewVrttempbacket.getIdcellcontents();
                    vrttempbacketListNewVrttempbacket.setIdcellcontents(vrtcellcontents);
                    vrttempbacketListNewVrttempbacket = em.merge(vrttempbacketListNewVrttempbacket);
                    if (oldIdcellcontentsOfVrttempbacketListNewVrttempbacket != null && !oldIdcellcontentsOfVrttempbacketListNewVrttempbacket.equals(vrtcellcontents)) {
                        oldIdcellcontentsOfVrttempbacketListNewVrttempbacket.getVrttempbacketList().remove(vrttempbacketListNewVrttempbacket);
                        oldIdcellcontentsOfVrttempbacketListNewVrttempbacket = em.merge(oldIdcellcontentsOfVrttempbacketListNewVrttempbacket);
                    }
                }
            }
            for (Vrtmove vrtmoveListOldVrtmove : vrtmoveListOld) {
                if (!vrtmoveListNew.contains(vrtmoveListOldVrtmove)) {
                    vrtmoveListOldVrtmove.setIdcellcontents(null);
                    vrtmoveListOldVrtmove = em.merge(vrtmoveListOldVrtmove);
                }
            }
            for (Vrtmove vrtmoveListNewVrtmove : vrtmoveListNew) {
                if (!vrtmoveListOld.contains(vrtmoveListNewVrtmove)) {
                    Vrtcellcontents oldIdcellcontentsOfVrtmoveListNewVrtmove = vrtmoveListNewVrtmove.getIdcellcontents();
                    vrtmoveListNewVrtmove.setIdcellcontents(vrtcellcontents);
                    vrtmoveListNewVrtmove = em.merge(vrtmoveListNewVrtmove);
                    if (oldIdcellcontentsOfVrtmoveListNewVrtmove != null && !oldIdcellcontentsOfVrtmoveListNewVrtmove.equals(vrtcellcontents)) {
                        oldIdcellcontentsOfVrtmoveListNewVrtmove.getVrtmoveList().remove(vrtmoveListNewVrtmove);
                        oldIdcellcontentsOfVrtmoveListNewVrtmove = em.merge(oldIdcellcontentsOfVrtmoveListNewVrtmove);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vrtcellcontents.getId();
                if (findVrtcellcontents(id) == null) {
                    throw new NonexistentEntityException("The vrtcellcontents with id " + id + " no longer exists.");
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
            Vrtcellcontents vrtcellcontents;
            try {
                vrtcellcontents = em.getReference(Vrtcellcontents.class, id);
                vrtcellcontents.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vrtcellcontents with id " + id + " no longer exists.", enfe);
            }
            Vrtcells idcells = vrtcellcontents.getIdcells();
            if (idcells != null) {
                idcells.getVrtcellcontentsList().remove(vrtcellcontents);
                idcells = em.merge(idcells);
            }
            Vrtmetal idmetal = vrtcellcontents.getIdmetal();
            if (idmetal != null) {
                idmetal.getVrtcellcontentsList().remove(vrtcellcontents);
                idmetal = em.merge(idmetal);
            }
            Vrtuser iduser = vrtcellcontents.getIduser();
            if (iduser != null) {
                iduser.getVrtcellcontentsList().remove(vrtcellcontents);
                iduser = em.merge(iduser);
            }
            List<Vrttempbacket> vrttempbacketList = vrtcellcontents.getVrttempbacketList();
            for (Vrttempbacket vrttempbacketListVrttempbacket : vrttempbacketList) {
                vrttempbacketListVrttempbacket.setIdcellcontents(null);
                vrttempbacketListVrttempbacket = em.merge(vrttempbacketListVrttempbacket);
            }
            List<Vrtmove> vrtmoveList = vrtcellcontents.getVrtmoveList();
            for (Vrtmove vrtmoveListVrtmove : vrtmoveList) {
                vrtmoveListVrtmove.setIdcellcontents(null);
                vrtmoveListVrtmove = em.merge(vrtmoveListVrtmove);
            }
            em.remove(vrtcellcontents);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vrtcellcontents> findVrtcellcontentsEntities() {
        return findVrtcellcontentsEntities(true, -1, -1);
    }

    public List<Vrtcellcontents> findVrtcellcontentsEntities(int maxResults, int firstResult) {
        return findVrtcellcontentsEntities(false, maxResults, firstResult);
    }

    private List<Vrtcellcontents> findVrtcellcontentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vrtcellcontents.class));
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

    public Vrtcellcontents findVrtcellcontents(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vrtcellcontents.class, id);
        } finally {
            em.close();
        }
    }

    public int getVrtcellcontentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vrtcellcontents> rt = cq.from(Vrtcellcontents.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

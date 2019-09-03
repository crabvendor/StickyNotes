package com.codecool.DAO;

import com.codecool.model.StickyNote;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class StickyNotesHibernate implements StickyNotesDAO{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickyNotes");
    private EntityManager em = emf.createEntityManager();


    @Override
    public StickyNote createNewNote() {
        StickyNote newStickyNote = new StickyNote();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newStickyNote);
        transaction.commit();
        return newStickyNote;
    }

    @Override
    public List<StickyNote> getNotes() {
        em.clear();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(StickyNote.class);
        Root modelRoot = criteriaQuery.from(StickyNote.class);
        criteriaQuery.select(modelRoot);
        TypedQuery<StickyNote> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void updateNote(String stickyNote) throws IOException {
        HashMap<String, Object> jsonParameters = new ObjectMapper().readValue(stickyNote, HashMap.class);
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<StickyNote> update = criteriaBuilder.createCriteriaUpdate(StickyNote.class);
        Root<StickyNote> noteRoot = update.from(StickyNote.class);
        for (String value : jsonParameters.keySet()) {
            if (noteRoot.get(value) != jsonParameters.get(value)) {
                update.where(criteriaBuilder.equal(noteRoot.get("id"), jsonParameters.get("id")));
                update.set(noteRoot.get(value), jsonParameters.get(value));
            }
        }
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.createQuery(update).executeUpdate();
        transaction.commit();
    }

    @Override
    public void removeNote(Long stickyNoteID) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaDelete<StickyNote> delete = criteriaBuilder.createCriteriaDelete(StickyNote.class);
        Root<StickyNote> noteRoot = delete.from(StickyNote.class);
        delete.where(criteriaBuilder.equal(noteRoot.get("id"), stickyNoteID));
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.createQuery(delete).executeUpdate();
        transaction.commit();
    }
}

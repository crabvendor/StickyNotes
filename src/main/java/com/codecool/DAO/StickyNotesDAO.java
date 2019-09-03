package com.codecool.DAO;

import com.codecool.model.StickyNote;

import java.io.IOException;
import java.util.List;

public interface StickyNotesDAO {

    StickyNote createNewNote();

    List<StickyNote> getNotes();

    void updateNote(String stickyNote) throws IOException;

    void removeNote(Long stickyNoteID);
}

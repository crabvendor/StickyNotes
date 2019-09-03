import {StickyNote} from "../model/stickyNote.js";
import {StickyNoteController} from "./stickyNoteController.js";
import {StickyNoteView} from "../view/stickyNoteView.js";
import {storage} from "../storage/notesStorage.js";

export class NewNoteController {
    
    constructor() {}

    createNewNote() {
        let note = new StickyNote(storage.createNewNote());
        let noteController = new StickyNoteController(note);
        let noteView = new StickyNoteView(noteController);
        let cork_board = document.getElementById('board');
        cork_board.appendChild(noteView.element);
    }

    loadNotes() {
        let noteList = storage.getNotes();
        for (let i=0; i<noteList.length; i++) {
            let note = noteList[i];
            let noteController = new StickyNoteController(note);
            let noteView = new StickyNoteView(noteController);
            let cork_board = document.getElementById('board');
            noteView.setElementPosition(note.positionX, note.positionY);
            cork_board.appendChild(noteView.element);
        }
    }
}

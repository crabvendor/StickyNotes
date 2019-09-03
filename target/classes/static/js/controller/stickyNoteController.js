import { mouseCoordinates } from "../mouseCoordinates.js";
import { storage } from "../storage/notesStorage.js";

export class StickyNoteController {

    constructor(note) {
        this.note = note;
        this.offsetX;
        this.offsetY;
    }

    changeTitle(e) {
        this.note.title = e.target.textContent;
        storage.updateNote(this.note);
    }

    changeContent(e) {
        this.note.content = e.target.value;
        storage.updateNote(this.note);        
    }

    remove(e) {        
        storage.deleteNote(this.note);
        e.target.parentElement.remove();
        delete this.note;
        delete this;
    }

    endDragging(e) {
        let note = e.target;

        let movementX = mouseCoordinates.X - this.note.positionX;
        let movementY = mouseCoordinates.Y - this.note.positionY;

        let X = note.offsetLeft + movementX - this.offsetX;
        let Y = note.offsetTop + movementY - this.offsetY;
        
        note.style.left = X + "px";
        note.style.top = Y + "px";
        
        this.note.positionX = X;
        this.note.positionY = Y;

        storage.updateNote(this.note);        
    }

    startDragging(e) {
        this.offsetX = e.clientX - this.note.positionX;
        this.offsetY = e.clientY - this.note.positionY;
    }
}

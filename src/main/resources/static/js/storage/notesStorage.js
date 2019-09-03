class NotesStorage {

    constructor() {
        this.req = new XMLHttpRequest();
    }

    getNotes() {
        this.req.open('GET', 'http://localhost:8080/notes', false);
        this.req.send();
        return JSON.parse(this.req.responseText);
    }

    createNewNote() {
        this.req.open('GET', 'http://localhost:8080/newnote', false);
        this.req.send();
        return JSON.parse(this.req.responseText);    
    }

    deleteNote(note) {
        this.req.open('DELETE', `http://localhost:8080/notes/${note.id}`, true);
        this.req.send();
    }

    updateNote(note) {
        console.log(note);
        this.req.open('PUT', 'http://localhost:8080/notes', true);
        this.req.overrideMimeType('application/json');
        this.req.send(JSON.stringify(note));
    }
}

export let storage = new NotesStorage();
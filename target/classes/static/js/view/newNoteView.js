export class NewNoteView {
    
    constructor(controller) {
        this.controller = controller;
        this.element = this.createElement();
        this.addEventListeners();
    }

    render() {
        return `<div id="new-post-button"></div>`;
    }

    createElement() {
        let elem = document.createElement('template');
        elem.innerHTML = this.render().trim();
        return elem.content.firstChild;
    }

    addEventListeners() {
        this.element.addEventListener("click", this.controller.createNewNote.bind(this.controller));
    }
}
export class StickyNoteView {
    
    constructor(controller) {
        this.controller = controller;
        this.element = this.createElement();
        this.addEventListeners()
    }

    render() {
        return `<div class="yellow-post-it" draggable="true">
                    <div contenteditable="true" class="post-it-title">${this.controller.note.title}</div>
                    <textarea spellcheck="false" class="post-it-content">${this.controller.note.content}</textarea>
                    <div class="delete-button">X</div>
                </div>`;
    }

    createElement() {
        let element = document.createElement('template');
        element.innerHTML = this.render().trim();
        return element.content.firstChild;
    }

    setElementPosition(x, y) {
        this.element.style.left = x + 'px';
        this.element.style.top = y + 'px';
    }

    addEventListeners() {
        this.element.getElementsByClassName("post-it-title")[0].addEventListener("input", this.controller.changeTitle.bind(this.controller));
        this.element.getElementsByClassName("post-it-content")[0].addEventListener("input", this.controller.changeContent.bind(this.controller));
        this.element.getElementsByClassName("delete-button")[0].addEventListener("click", this.controller.remove.bind(this.controller));
        this.element.addEventListener("dragstart", this.controller.startDragging.bind(this.controller));
        this.element.addEventListener("dragend", this.controller.endDragging.bind(this.controller));
    }
}

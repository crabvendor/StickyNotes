export class StickyNote {

    constructor(json) {
        const {id, title, content, positionX, positionY} = json;
        this.id = id;
        this.title = title;
        this.content = content;
        this.positionX = positionX;
        this.positionY = positionY;
    }
}

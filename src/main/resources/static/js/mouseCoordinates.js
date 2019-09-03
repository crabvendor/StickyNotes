class MouseCoordinates {
    constructor () {
        this.Y = 0;
        this.X = 0;
        this.addEventListeners();
    }

    setCoordinates(e) {
        this.Y = e.clientY;
        this.X = e.clientX;
    }

    addEventListeners() {
        document.addEventListener("dragover", this.setCoordinates.bind(this));
    }
}

export let mouseCoordinates = new MouseCoordinates();
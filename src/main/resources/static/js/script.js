"use strict";

import {NewNoteController} from "./controller/newNoteController.js";
import {NewNoteView} from "./view/newNoteView.js";

let newNoteController = new NewNoteController();
let newNoteView = new NewNoteView(newNoteController);

newNoteController.loadNotes();

let cork_board = document.getElementById('board');
cork_board.appendChild(newNoteView.element);

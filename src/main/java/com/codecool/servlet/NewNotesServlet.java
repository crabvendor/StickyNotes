package com.codecool.servlet;

import com.codecool.DAO.StickyNotesDAO;
import com.codecool.DAO.StickyNotesHibernate;
import com.codecool.model.StickyNote;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newnote")
public class NewNotesServlet extends HttpServlet {

    private StickyNotesDAO stickyNotesDAO = new StickyNotesHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        StickyNote newStickyNote = stickyNotesDAO.createNewNote();
        String stickyNoteJson = new Gson().toJson(newStickyNote);
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(stickyNoteJson);
    }
}

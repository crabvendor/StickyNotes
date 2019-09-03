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
import java.util.List;

@WebServlet("/notes/*")
public class ExistingNotesServlet extends HttpServlet {

    private StickyNotesDAO stickyNotesDAO = new StickyNotesHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<StickyNote> notes = stickyNotesDAO.getNotes();
        String stickyNoteJsonList = new Gson().toJson(notes);
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(stickyNoteJsonList);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = getRequestJson(req);
        stickyNotesDAO.updateNote(json);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long noteID = getNoteIDFromURI(req);
        stickyNotesDAO.removeNote(noteID);
    }

    private Long getNoteIDFromURI(HttpServletRequest req) {
        String noteIDString = req.getRequestURI().split("/")[2];
        return Long.parseLong(noteIDString);
    }


    private String getRequestJson(HttpServletRequest req) throws IOException {
        String line;
        String updatedJson = "";
        while ((line = req.getReader().readLine()) != null) {
            updatedJson += line;
        }
        return updatedJson;
    }
}

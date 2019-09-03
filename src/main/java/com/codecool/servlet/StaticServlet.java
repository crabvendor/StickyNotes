package com.codecool.servlet;

import com.codecool.Helpers.MimeTypeResolver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

@WebServlet("/static/*")
public class StaticServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String uri = req.getRequestURI();
        System.out.println(String.format("Looking for: %s", uri));
        String path = String.format(".%s", uri);

        ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = classLoader.getResource(path);

        if (fileUrl == null) {
            resp.setStatus(404);
            resp.getWriter().write("404");
        } else {
            sendFile(resp, fileUrl);
        }
    }

    private void sendFile(HttpServletResponse response, URL fileUrl) throws IOException {
        File file = new File(fileUrl.getFile());
        MimeTypeResolver resolver = new MimeTypeResolver(file);
        String mime = resolver.getMimeType();

        response.setContentType(mime);
        response.setStatus(200);

        OutputStream os = response.getOutputStream();
        FileInputStream fs = new FileInputStream(file);
        final byte[] buffer = new byte[0x10000];
        int count = 0;
        while ((count=fs.read(buffer)) >= 0) {
            os.write(buffer,0,count);
        }
        os.close();
    }
}

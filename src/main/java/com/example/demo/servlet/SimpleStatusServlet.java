package com.example.demo.servlet;

import jakarta.servlet.http.*;
import java.io.*;

public class SimpleStatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        PrintWriter w = resp.getWriter();
        w.write("Credit Card Reward Maximizer is running");
        w.flush();
    }
}

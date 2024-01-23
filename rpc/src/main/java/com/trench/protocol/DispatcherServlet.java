package com.trench.protocol;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        new HttpServerHandler().handler(req, resp);
    }
}

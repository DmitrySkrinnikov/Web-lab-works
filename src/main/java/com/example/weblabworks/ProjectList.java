package com.example.weblabworks;
import java.util.ArrayList;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Servlet implementation class ProjectList
 */
@WebServlet(name = "ProjectList", value = "/ProjectList")
public class ProjectList extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ArrayList<Project> projects = new ArrayList<>(Arrays.asList(
            new Project(1, "Сборка пк", "Петя Пупкин", "Да"),
            new Project(2, "Изучение java", "Вася Петькин", "Нет")
    ));

    /**
     * Servlet initialization
     */
    public void init() {
        System.out.println("Init servlet");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String customer = request.getParameter("customer");
        String done = request.getParameter("done");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head><title>Список проектов</title></head>");
            out.println("<body>");
            out.println("<h1>Список проектов " +
                    (customer != null ? "Заказчик: " + customer + " ": "") +
                    (done != null ? "Выполненость: " + done + " ": "") +"</h1>");
            out.println("<table border='1'>");
            out.println("<td><b>Проект </b></td><td><b>Заказчик </b></td><td><b>Выполнен </b></td></tr>");
            for (Project p : projects) {
                if ((p.getCustomer().equals(customer) || customer == null) &&
                        (p.getDone().equals(done) || done == null)) {
                    out.println("<tr><td>" + p.getProject() +  " </td><td>" +
                            p.getCustomer() + " </td><td>" +
                            p.getDone() +" </td></tr>");
                }
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Destroying the servlet
     */
    public void destroy() {
        System.out.println("Destroy servlet");
    }
}
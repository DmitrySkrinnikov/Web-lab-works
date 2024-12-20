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
import java.util.Locale;
import java.util.ResourceBundle;

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

        String lang = request.getParameter("lang");
        if (lang == null) lang = "ru";
        if(!"en".equalsIgnoreCase(lang) && !"ru".equalsIgnoreCase(lang))
        {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
                            "Параметр lang может принимать значения en или ru");
            return;
        }

        String customer = request.getParameter("customer");
        String done = request.getParameter("done");
        response.setContentType("text/html;charset=UTF-8");
        ResourceBundle res = ResourceBundle.getBundle(
                "Project",
                          "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head><title>Список проектов</title></head>");
            out.println("<body>");
            out.println("<h1>" + res.getString("list_project") + " "+
                    (customer != null ? res.getString("customer") + ": " + customer + " ": "") +
                    (done != null ? res.getString("done") + ": " +
                            (done.equals("Да") ? res.getString("yes") : res.getString("no")) +
                            " ": "") +"</h1>");
            out.println("<table border='1'>");
            out.println("<td><b>" + res.getString("project") + " </b></td>" +
                        "<td><b>" + res.getString("customer") + " </b></td>" +
                        "<td><b>" + res.getString("done")+ " </b></td></tr>");
            for (Project p : projects) {
                if ((p.getCustomer().equals(customer) || customer == null) &&
                        (p.getDone().equals(done) || done == null)) {
                    out.println("<tr><td>" + p.getProject() +  " </td><td>" +
                            p.getCustomer() + " </td><td>" +
                            (p.getDone().equals("Да") ? res.getString("yes"):
                                    res.getString("no")) +" </td></tr>");
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
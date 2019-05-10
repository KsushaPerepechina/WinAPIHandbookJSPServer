package by.bsuir.winapihandbookjspserver;

import by.bsuir.winapihandbookjspserver.dao.FunctionDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/deleteFunction"})
public class DeleteFunctionServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ViewFunctionsServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage = null;

        try {
            FunctionDao.deleteFunction(FunctionDao.getFunctionById(Integer.parseInt(request.getParameter("id"))));
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            errorMessage = e.getMessage();
        }

        if (errorMessage != null) {
            request.setAttribute("errorTitle", "Delete Function Error");
            request.setAttribute("errorMessage", errorMessage);
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/error_page.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + ApplicationConstants.PATH_FUNCTIONS);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

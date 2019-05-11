package by.bsuir.winapihandbookjspserver;

import by.bsuir.winapihandbookjspserver.bean.WinAPIFunction;
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

@WebServlet(urlPatterns = {"/addFunction"})
public class AddFunctionServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ViewFunctionsServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/add_function.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WinAPIFunction function = new WinAPIFunction();
        function.setName(request.getParameter("name"));
        function.setParams(request.getParameter("params"));
        function.setReturnValue(request.getParameter("return_value"));
        function.setDescription(request.getParameter("description"));

        String errorMessage = null;
        try {
            FunctionDao.addFunction(function);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            errorMessage = e.getMessage();
        }

        request.setAttribute("errorMessage", errorMessage);

        if (errorMessage == null) {
            response.sendRedirect(request.getContextPath() + ApplicationConstants.PATH_FUNCTIONS);
        } else {
            request.getServletContext().getRequestDispatcher("/WEB-INF/views/add_function.jsp").forward(request, response);
        }
    }
}

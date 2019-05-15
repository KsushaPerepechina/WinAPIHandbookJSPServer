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
import java.util.List;

/**
 * Сервлет для просмотра имеющихся в справочнике функций.
 */
@WebServlet(urlPatterns = {"/viewFunctions"})
public class ViewFunctionsServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ViewFunctionsServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<WinAPIFunction> list = null;
        String errorMessage = null;

        try {
            list = FunctionDao.getAllFunctions();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            errorMessage = e.getMessage();
        }

        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("list", list);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/view_functions.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package by.bsuir.winapihandbookjspserver;

import by.bsuir.winapihandbookjspserver.bean.WinAPIFunction;
import by.bsuir.winapihandbookjspserver.dao.FunctionDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteFunctionServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private WinAPIFunction function;
    @InjectMocks
    private DeleteFunctionServlet servlet;

    @Before
    public void setup() throws SQLException {
        function = new WinAPIFunction("name", "params", "return value", "description");
        FunctionDao.addFunction(function);
        String id = FunctionDao.getFunctionByName("name").getId().toString();
        when(request.getParameter("id")).thenReturn(id);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        servlet.doGet(request, response);
        verify(response).sendRedirect(anyString());
    }

    @Test
    public void doPost() throws ServletException, IOException {
        doGet();
    }

}
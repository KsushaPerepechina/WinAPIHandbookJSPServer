package by.bsuir.winapihandbookjspserver;

import by.bsuir.winapihandbookjspserver.bean.WinAPIFunction;
import by.bsuir.winapihandbookjspserver.dao.FunctionDao;
import org.junit.After;
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
public class EditFunctionServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private WinAPIFunction function;
    @InjectMocks
    private EditFunctionServlet servlet;
    private String id;

    @Before
    public void setup() throws SQLException {
        function = new WinAPIFunction("name", "params", "return value", "description");
        FunctionDao.addFunction(function);
        id = FunctionDao.getFunctionByName("name").getId().toString();
        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("name")).thenReturn(function.getName());
        when(request.getParameter("params")).thenReturn(function.getParams());
        when(request.getParameter("return_value")).thenReturn(function.getReturnValue());
        when(request.getParameter("description")).thenReturn(function.getDescription());
    }

    @After
    public void destroy() throws SQLException {
        FunctionDao.deleteFunction(FunctionDao.getFunctionById(Integer.parseInt(id)));
    }

    @Test
    public void doPost() throws ServletException, IOException {
        function.setName("updated name");
        servlet.doPost(request, response);
        verify(response).sendRedirect(anyString());
    }
}
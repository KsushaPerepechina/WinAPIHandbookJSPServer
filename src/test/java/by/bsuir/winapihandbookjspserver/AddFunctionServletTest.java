package by.bsuir.winapihandbookjspserver;

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

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddFunctionServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @InjectMocks
    private AddFunctionServlet servlet;

    @Before
    public void setup() {
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("params")).thenReturn("params");
        when(request.getParameter("return_value")).thenReturn("return_value");
        when(request.getParameter("description")).thenReturn("description");
    }

    @After
    public void destroy() throws SQLException {
        FunctionDao.deleteFunction(FunctionDao.getFunctionByName("name"));
    }

    @Test
    public void doPost() throws ServletException, IOException {
        servlet.doPost(request, response);
        verify(response).sendRedirect(anyString());
    }
}
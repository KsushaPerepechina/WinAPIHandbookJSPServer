package by.bsuir.winapihandbookjspserver;

import by.bsuir.winapihandbookjspserver.dao.FunctionDao;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class ViewFunctionsServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FunctionDao functionDao;

    @InjectMocks
    private ViewFunctionsServlet servlet;

}
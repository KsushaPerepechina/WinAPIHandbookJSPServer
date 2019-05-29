package by.bsuir.winapihandbookjspserver.dao;

import by.bsuir.winapihandbookjspserver.bean.WinAPIFunction;
import org.junit.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FunctionDaoTest {
    private static WinAPIFunction function;

    @BeforeClass
    public static void init(){
        function = new WinAPIFunction("some name", "some params",
                "some return value", "some description");

    }

    @Before
    public void start() throws SQLException {
        FunctionDao.addFunction(function);
    }

    @After
    public void close() throws SQLException {
        FunctionDao.deleteFunction(function);
    }

    @Test
    public void getAllFunctions() throws SQLException {
        WinAPIFunction func2 = new WinAPIFunction("some name 2", "some params 2", "some return value 2", "some description 2");
        WinAPIFunction func3 = new WinAPIFunction("some name 3", "some params 3", "some return value 3", "some description 3");

        FunctionDao.addFunction(func2);
        FunctionDao.addFunction(func3);

        List<WinAPIFunction> actual = FunctionDao.getAllFunctions();

        List<WinAPIFunction> expected = new ArrayList<>();
        actual.add(function);
        actual.add(func2);
        actual.add(func3);

        Assert.assertTrue(containsAll(actual,expected));

        FunctionDao.deleteFunction(func2);
        FunctionDao.deleteFunction(func3);
    }

    @Test
    public void addFunction() throws SQLException {
        Assert.assertTrue(containsOne(FunctionDao.getAllFunctions(), function));
    }

    @Test
    public void deleteFunction() throws SQLException {
        Assert.assertTrue(containsOne(FunctionDao.getAllFunctions(), function));
        FunctionDao.deleteFunction(function);
        Assert.assertFalse(containsOne(FunctionDao.getAllFunctions(), function));
    }

    @Test
    public void updateFunction() throws SQLException {
        function.setDescription("updated description");
        FunctionDao.updateFunction(function);
        Assert.assertTrue(containsOne(FunctionDao.getAllFunctions(), function));
    }

    @Test
    public void getFunctionById() throws SQLException {
        Integer funcId = FunctionDao.getAllFunctions().stream().
                filter(f -> f.getName().equals(function.getName()))
                .findAny().get().getId();
        Assert.assertEquals(function, FunctionDao.getFunctionById(funcId));
    }

    @Test
    public void getFunctionByName() throws SQLException {
        Assert.assertEquals(function, FunctionDao.getFunctionByName(function.getName()));
    }


    private static boolean containsAll(List<WinAPIFunction> list, List<WinAPIFunction> sublist){
        int elementsNumber = 0;
        for (WinAPIFunction function: list) {
            for (WinAPIFunction function1: sublist) {
                if(function.equals(function1)) elementsNumber++;
            }
        }
        return elementsNumber == sublist.size();
    }

    private static boolean containsOne(List<WinAPIFunction> list, WinAPIFunction function){
        for (WinAPIFunction func: list) {
            if (function.equals(func)) return true;
        }
        return false;
    }
}
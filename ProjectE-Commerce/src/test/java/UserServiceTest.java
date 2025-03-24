import com.revature.models.Order;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repos.OrderDAOImpl;
import com.revature.repos.UserDAOImpl;
import com.revature.services.UserService;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService;

    private UserDAOImpl userMockDAO;
    private OrderDAOImpl orderMockDAO;


    @Before
    public void setup(){
        userMockDAO = Mockito.mock(UserDAOImpl.class);
        orderMockDAO = Mockito.mock(OrderDAOImpl.class);

        userService = new UserService(userMockDAO, orderMockDAO);
    }
    //SR stands for Should Return.
    @Test
    public void validEmailSRTrue(){
        //arrange
        String testEmail = "validemail@gmail.com";
        //Act
        boolean result = userService.validateEmail(testEmail);
        //Assert
        Assert.assertTrue(result);
    }

    @Test
    public void invalidEmailSRFalse(){
        //arrange
        String testEmail = "validemailgmail.com";
        //Act
        boolean result = userService.validateEmail(testEmail);
        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void availableEmailSRTrue(){
        String testEmail = "test@test.com";

        // Calling the mock instead of the DB.
        when(userMockDAO.getUserByEmail(testEmail)).thenReturn(null);

        boolean result = userService.isEmailAvailable(testEmail);

        Assert.assertTrue(result);
    }

    @Test
    public void unavailableEmailSFalse(){
        String testEmail = "test@test.com";
        User mockedUser = new User("test","test",testEmail,"password");
        // Calling the mock instead of the DB.
        when(userMockDAO.getUserByEmail(testEmail)).thenReturn(mockedUser);

        boolean result = userService.isEmailAvailable(testEmail);

        Assert.assertFalse(result);
    }

    @Test
    public void shortPasswordSRFalse(){
        String testPassword = "Short";

        boolean result = userService.validatePassword(testPassword);

        Assert.assertFalse(result);
    }

    @Test
    public void allUppercasePasswordSRFalse(){
        String testPassword = "SHORTINGS";

        boolean result = userService.validatePassword(testPassword);

        Assert.assertFalse(result);
    }

    @Test
    public void allLowercasePasswordSRFalse(){
        String testPassword = "shortings";

        boolean result = userService.validatePassword(testPassword);

        Assert.assertFalse(result);
    }

    @Test
    public void ValidatePasswordSRTrue(){
        String testPassword = "Shortings";

        boolean result = userService.validatePassword(testPassword);

        Assert.assertTrue(result);
    }

    @Test
    public void registerValidUserSRTrue(){
        User u = new User("test","test","test@test.com","test");
        when(userMockDAO.createUser(u)).thenReturn(u);

        User returnedUser = userService.registerUser(u);

        Assert.assertEquals(u, returnedUser);
    }

    @Test
    public void registerInvalidUserSRFalse(){
        User u = new User("test","test","test@test.com","test");
        when(userMockDAO.createUser(u)).thenReturn(null);

        User returnedUser = userService.registerUser(u);

        Assert.assertNotEquals(u, returnedUser);
    }

    @Test
    public void loginValidUserSRTrue(){
        String email = "email@email.com";
        String password = "password";
        User u = new User("test","test",email,password);

        when(userMockDAO.getUserByEmail(email)).thenReturn(u);

        User returnedUser = userService.loginUser(email,password);

        Assert.assertEquals(u, returnedUser);
    }

    @Test
    public void loginInvalidUserSRFalse(){
        String email = "email@email.com";
        String password = "password";
        User u = new User("test","test",email,password);

        when(userMockDAO.getUserByEmail(email)).thenReturn(null);

        User returnedUser = userService.loginUser(email,password);

        Assert.assertNotEquals(u, returnedUser);
    }

    @Test
    public void updateValidUserSRTrue(){
        User u = new User("test","test","test@test.com","test");
        when(userMockDAO.updateUser(u)).thenReturn(u);

        User returnedUser = userService.updateUser(u);

        Assert.assertEquals(u, returnedUser);
    }

    @Test
    public void updateInvalidUserSRFalse(){
        User u = new User("test","test","test@test.com","test");
        when(userMockDAO.updateUser(u)).thenReturn(null);

        User returnedUser = userService.updateUser(u);

        Assert.assertNotEquals(u, returnedUser);
    }

    @Test
    public void viewOrderHistorySRTrue(){
        int userId = 1;
        List<Order> allOrders = new ArrayList<>();
        allOrders.add(new Order(1,userId,12.3f, Status.PENDING));

        when(orderMockDAO.viewByUserId(userId)).thenReturn(allOrders);

        List<Order> returnedAllOrders = userService.viewOrderHistory(userId);

        Assert.assertEquals(allOrders, returnedAllOrders);
    }

    @Test
    public void viewOrderHistorySRFalse(){
        int userId = 1;
        List<Order> allOrders = new ArrayList<>();
        allOrders.add(new Order(1,userId,12.3f, Status.PENDING));

        when(orderMockDAO.viewByUserId(userId)).thenReturn(null);

        List<Order> returnedAllOrders = userService.viewOrderHistory(userId);

        Assert.assertNotEquals(allOrders, returnedAllOrders);
    }

    @AfterClass
    public static void after(){
        System.out.println("fisnish");
    }
}

import com.revature.models.CartItem;
import com.revature.models.Order;
import com.revature.models.Product;
import com.revature.models.Status;
import com.revature.repos.*;
import com.revature.services.BuyoutService;
import com.revature.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class BuyoutServiceTest {

    private BuyoutService buyoutService;

    private CartItemDAO cartItemMockDAO;
    private ProductDAO productMockDAO;
    private OrderDAO orderMockDAO;
    private OrderItemDAO orderItemMockDAO;

    @Before
    public void setup(){
        cartItemMockDAO = Mockito.mock(CartItemDAOImpl.class);
        productMockDAO = Mockito.mock(ProductDAOImpl.class);
        orderMockDAO = Mockito.mock(OrderDAOImpl.class);
        orderItemMockDAO = Mockito.mock(OrderItemDAOImpl.class);
        buyoutService = new BuyoutService(cartItemMockDAO,productMockDAO,orderMockDAO,orderItemMockDAO);
    }

    //Under the considerations that SRT stands for Should Return True, SRF stands for Should Return False
    @Test
    public void addCartItemSRT(){
        int userid = 1, productid = 1, quantity = 1;
        CartItem cI = new CartItem(userid,productid,quantity);

        when(cartItemMockDAO.addCartProduct(userid,productid,quantity)).thenReturn(cI);

        CartItem returnCI = buyoutService.addCartItem(userid,productid,quantity);

        Assert.assertEquals(cI, returnCI);
    }

    @Test
    public void addCartItemSRF(){
        int userid = 1, productid = 1, quantity = 1;
        CartItem cI = new CartItem(userid,productid,quantity);

        when(cartItemMockDAO.addCartProduct(userid,productid,quantity)).thenReturn(null);

        CartItem returnCI = buyoutService.addCartItem(userid,productid,quantity);

        Assert.assertNotEquals(cI, returnCI);
    }

    @Test
    public void removeCartItemSRT(){
        int userid = 1, productid = 1;

        when(cartItemMockDAO.removeCartProduct(userid,productid)).thenReturn(true);

        Boolean returnCI = buyoutService.removeCartItem(userid,productid);

        Assert.assertTrue(returnCI);
    }

    @Test
    public void removeCartItemSRF(){
        int userid = 1, productid = 1;

        when(cartItemMockDAO.removeCartProduct(userid,productid)).thenReturn(false);

        Boolean returnCI = buyoutService.removeCartItem(userid,productid);

        Assert.assertFalse(returnCI);
    }

    @Test
    public void updateCartItemSRT(){
        int userid = 1, productid = 1, quantity = 1;
        CartItem cI = new CartItem(userid,productid,quantity);

        when(cartItemMockDAO.updateCartProduct(userid,productid,quantity)).thenReturn(cI);

        CartItem returnCI = buyoutService.updateCartItem(userid,productid,quantity);

        Assert.assertEquals(cI, returnCI);
    }

    @Test
    public void updateCartItemSRF(){
        int userid = 1, productid = 1, quantity = 1;
        CartItem cI = new CartItem(userid,productid,quantity);

        when(cartItemMockDAO.updateCartProduct(userid,productid,quantity)).thenReturn(null);

        CartItem returnCI = buyoutService.updateCartItem(userid,productid,quantity);

        Assert.assertNotEquals(cI, returnCI);
    }

    @Test
    public void cleanCartSRT(){
        int userid = 1;

        boolean returnCI = buyoutService.buyoutCart(userid);
        Assert.assertTrue(returnCI);
    }

    @Test
    public void cleanCartSRF(){
        int userid = 1;

        when(cartItemMockDAO.getAllItems(userid)).thenReturn(null);

        boolean returnCI = buyoutService.buyoutCart(userid);
        Assert.assertFalse(returnCI);
    }

    @Test
    public void getAllItemsSRT(){
        int userid = 1;
        List<CartItem> allitems = new ArrayList<>();
        allitems.add(new CartItem());

        when(cartItemMockDAO.getAllItems(userid)).thenReturn(allitems);

        List<CartItem> returnAllItems = buyoutService.getAllItems(userid);
        Assert.assertEquals(allitems, returnAllItems);
    }

    @Test
    public void getAllItemsSRF(){
        int userid = 1;
        List<CartItem> allitems = new ArrayList<>();
        allitems.add(new CartItem());

        when(cartItemMockDAO.getAllItems(userid)).thenReturn(null);

        List<CartItem> returnAllItems = buyoutService.getAllItems(userid);
        Assert.assertNotEquals(allitems, returnAllItems);
    }

    @Test
    public void updateOrderStatusSRT(){
        Order order = new Order(1,1,1f, Status.SHIPPED);

        when(orderMockDAO.updateStatus(order)).thenReturn(order);

        Order returnOrder = buyoutService.updateOrderStatus(order);

        Assert.assertEquals(order, returnOrder);
    }

    @Test
    public void updateOrderStatusSRF(){
        Order order = new Order(1,1,1f, Status.SHIPPED);

        when(orderMockDAO.updateStatus(order)).thenReturn(null);

        Order returnOrder = buyoutService.updateOrderStatus(order);

        Assert.assertNotEquals(order, returnOrder);
    }

    @Test
    public void getAllOrdersSRT(){
        List<Order> allOrders = new ArrayList<>();
        allOrders.add(new Order());

        when(orderMockDAO.viewAll()).thenReturn(allOrders);

        List<Order> returnedAllOrders = buyoutService.viewAllOrders();

        Assert.assertEquals(allOrders, returnedAllOrders);
    }

    @Test
    public void getAllOrdersSRF(){
        List<Order> allOrders = new ArrayList<>();
        allOrders.add(new Order());

        when(orderMockDAO.viewAll()).thenReturn(null);

        List<Order> returnedAllOrders = buyoutService.viewAllOrders();

        Assert.assertNotEquals(allOrders, returnedAllOrders);
    }

    @Test
    public void viewOrdersByStatusSRT(){
        Status status = Status.PENDING;
        List<Order> allOrders = new ArrayList<>();
        allOrders.add(new Order());

        when(orderMockDAO.viewByStatus(status)).thenReturn(allOrders);

        List<Order> returnedOrders = buyoutService.viewOrdersByStatus(status);

        Assert.assertEquals(allOrders, returnedOrders);
    }

    @Test
    public void viewOrdersByStatusSRF(){
        Status status = Status.PENDING;
        List<Order> allOrders = new ArrayList<>();
        allOrders.add(new Order());

        when(orderMockDAO.viewByStatus(status)).thenReturn(null);

        List<Order> returnedOrders = buyoutService.viewOrdersByStatus(status);

        Assert.assertNotEquals(allOrders, status);
    }
// This test is just an extra, it actually does not test anything as I am implementing the solution to this extra step yet.
    @Test
    public void deleteOrderItemSRT(){

        Assert.assertFalse(buyoutService.deleteOrderItem());
    }

}

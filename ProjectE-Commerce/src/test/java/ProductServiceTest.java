import com.revature.models.Product;
import com.revature.repos.OrderDAOImpl;
import com.revature.repos.ProductDAO;
import com.revature.repos.ProductDAOImpl;
import com.revature.repos.UserDAOImpl;
import com.revature.services.ProductService;
import com.revature.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;

    private ProductDAOImpl productMockDAO;

    @Before
    public void setup(){
        productMockDAO = Mockito.mock(ProductDAOImpl.class);

        productService = new ProductService(productMockDAO);
    }

    //Under the considerations that SRT stands for Should Return True, SRF stands for Should Return Fasle
    @Test
    public void CreateProductSRT(){
        Product p = new Product(1,"test","test",12f,4);
        when(productMockDAO.createProduct(p)).thenReturn(p);

        Product returnP = productService.createProduct(p);

        Assert.assertEquals(p,returnP);
    }

    @Test
    public void CreateProductSRF(){
        Product p = new Product(1,"test","test",12f,4);
        when(productMockDAO.createProduct(p)).thenReturn(null);

        Product returnP = productService.createProduct(p);

        Assert.assertNotEquals(p, returnP);
    }

    @Test
    public void UpdateProductSRT(){
        Product p = new Product(1,"test","test",12f,4);
        when(productMockDAO.updateProduct(p)).thenReturn(p);

        Product returnP = productService.updateProduct(p);

        Assert.assertEquals(p, returnP);
    }

    @Test
    public void UpdateProductSRF(){
        Product p = new Product(1,"test","test",12f,4);
        when(productMockDAO.updateProduct(p)).thenReturn(null);

        Product returnP = productService.updateProduct(p);

        Assert.assertNotEquals(p, returnP);
    }

    @Test
    public void getProductDetailSRT(){
        String name = "test";
        Product p = new Product(1,"test","test",12f,1);

        when(productMockDAO.getProductByName(name)).thenReturn(p);

        Product returnP = productService.getProductDetail(name);

        Assert.assertEquals(p,returnP);
    }

    @Test
    public void getProductDetailSRF(){
        String name = "test";
        Product p = new Product(1,"test","test",12f,1);

        when(productMockDAO.getProductByName(name)).thenReturn(null);

        Product returnP = productService.getProductDetail(name);

        Assert.assertNotEquals(p,returnP);
    }

    @Test
    public void getAllProductsSRT(){
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(new Product());

        when(productMockDAO.getAllProducts()).thenReturn(allProducts);

        List<Product> returnedAllProducts = productService.getAllProducts();

        Assert.assertEquals(allProducts, returnedAllProducts);
    }

    @Test
    public void getAllProductsSRF(){
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(new Product());

        when(productMockDAO.getAllProducts()).thenReturn(null);

        List<Product> returnedAllProducts = productService.getAllProducts();

        Assert.assertNotEquals(allProducts, returnedAllProducts);
    }

    @Test
    public void deleteProductSRT(){
        String name = "test";

        when(productMockDAO.deleteProductByName(name)).thenReturn(true);

        boolean returnVar = productService.deleteProduct(name);

        Assert.assertTrue(returnVar);
    }

    @Test
    public void deleteProductSRF(){
        String name = "test";

        when(productMockDAO.deleteProductByName(name)).thenReturn(false);

        boolean returnVar = productService.deleteProduct(name);

        Assert.assertFalse(returnVar);
    }
}

package hello;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bliang on 10/08/2017.
 */
@RestController
@RequestMapping("/product")
@Api(value = "product-api",description = "show product operation api")
public class ProductController {

    //add for swagger
    @ApiOperation(value = "View a list of products",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    //end
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public List<Product> list(){
        List list = initProduct();
        return list;
    }

    //add for swagger
    @ApiOperation(value = "Search a product with an ID",response = Product.class)
    //end
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public Product showProduct(@PathVariable Integer id){
        List<Product> list = initProduct();
        Product product = list.get(id);
        return product;
    }

    //add for swagger
    @ApiOperation(value = "Add a product")
    //end
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveProduct(@RequestBody Product product){
        System.out.println("---------add one product----------");
        return new ResponseEntity("Product saved successfully", HttpStatus.OK);
    }


    //add for swagger
    @ApiOperation(value = "Update a product")
    //end
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product){
        System.out.println("---------update one product---------");
        return new ResponseEntity("Product updated successfully", HttpStatus.OK);
    }

    //add for swagger
    @ApiOperation(value = "Delete a product")
    //end
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        System.out.println("-----------delete one product -----------");
        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);

    }

    @RequestMapping(value = "/listMap", method= RequestMethod.GET, produces = "application/json")
    public Map listMap(){
        List<Product> prodList =initProduct();
        Map mp = new HashMap();
        mp.put("key1","value1");
        mp.put("key2","value2");
        mp.put("key3","value3");
        mp.put("key4",prodList.get(0));
        mp.put("key5",prodList);
        return mp;
    }

    public List<Product> initProduct(){
        List<Product> products = Arrays.asList(

                new Product("Best. Cloud. Ever. (T-Shirt, Men's Large)", "SKU-24642", "<p>Do you love your cloud platform? " +
                        "Do you push code continuously into production on a daily basis? " +
                        "Are you living the cloud native microservice dream? Then rain or shine, this T-Shirt is for you. " +
                        "Show the world you're a stylish cloud platform architect with this cute yet casual tee. " +
                        "<br /><br />&nbsp; <strong>Cloud Native Tee Collection</strong><br />" +
                        "&nbsp; 110% cloud stuff, 5% spandex<br />&nbsp; Rain wash only<br />&nbsp; " +
                        "Four nines of <em>stylability</em></p>", 21.99),

                new Product("Like a BOSH (T-Shirt, Women's Medium)", "SKU-34563", "<p>The BOSH Outer Shell (<strong>BOSH</strong>) " +
                        "is an elegant release engineering tool for a more virtualized cloud-native age. " +
                        "The feeling of spinning up a highly available distributed system of VMs is second only to the " +
                        "feeling of frequently pushing code to production. Show the cloud <em>who's BOSH</em> with " +
                        "this stylish cloud native ops tee.<br /><br />&nbsp; <strong>Cloud Native Tee Collection</strong><br />&nbsp; " +
                        "99% YAML, 11% CLI<br />&nbsp; BOSH CCK <span style='text-decoration: underline;'><em>recommended</em></span><br />&nbsp; " +
                        "4 nines of <em>re-washability</em></p>", 14.99),

                new Product("We're gonna need a bigger VM (T-Shirt, Women's Small)", "SKU-12464", 13.99),
                new Product("cf push awesome (Hoodie, Men's Medium)", "SKU-64233",
                        "<p>One of the great natives of the cloud once said \"<em>" +
                                "Production is the happiest place on earth for us - it's better than Disneyland</em>\". " +
                                "With this stylish Cloud Foundry hoodie you can push code to the cloud all day while staying " +
                                "comfortable and casual. <br /><br />&nbsp; <strong>Cloud Native PaaS Collection</strong><br />" +
                                "&nbsp; 10% cloud stuff, 90% platform nylon<br />&nbsp; Cloud wash safe<br />" +
                                "&nbsp; Five nines of <em>comfortability</em></p>", 21.99));
        return products;
    }
}

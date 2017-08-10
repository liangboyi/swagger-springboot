# Springboot Swagger
## pom.xml add dependence
```
<dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.6.1</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.6.1</version>
            <scope>compile</scope>
        </dependency>
```
## Application.java add basic information
```
@SpringBootApplication
//add for swagger
@EnableSwagger2
//end
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //add for swagger
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("products")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/product.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Sample with Swagger")
                .description("Spring REST Sample with Swagger")
                .termsOfServiceUrl("https://github.com/liangboyi/gs-rest-service")
                .contact("Boyi Liang")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("2.0")
                .build();
    }
    //end
}
```
## controller.java add API information
```
@RestController
@RequestMapping("/product")
//add for swagger
@Api(value = "product-api",description = "show product operation api")
//end
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
```
## show swagger information
http://localhost:8080/swagger-ui.html
![](http://or5qh6y3k.bkt.clouddn.com/20170810172524_AuuFhE_Screenshot.jpeg)

http://localhost:8080/v2/api-docs?group=products
![](http://or5qh6y3k.bkt.clouddn.com/20170810172856_v3vLCV_Screenshot.jpeg)

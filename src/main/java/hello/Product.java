package hello;

import io.swagger.annotations.ApiModelProperty;


public class Product {

    //add for swagger
    @ApiModelProperty(notes = "The database generated product ID")
    //end
    private Long id;

    //add for swagger
    @ApiModelProperty(notes = "The application-specific product Name")
    //end
    private String name;

    //add for swagger
    @ApiModelProperty(notes = "The application-specific product ID")
    //end
    private String productId;

    //add for swagger
    @ApiModelProperty(notes = "The product description")
    //end
    private String description;

    //add for swagger
    @ApiModelProperty(notes = "The price of the product", required = true)
    //end
    private Double unitPrice;

    public Product() {
    }

    public Product(String name, String productId, Double unitPrice) {
        this.name = name;
        this.productId = productId;
        this.unitPrice = unitPrice;
    }

    public Product(String name, String productId, String description, Double unitPrice) {
        this.name = name;
        this.productId = productId;
        this.description = description;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productId='" + productId + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
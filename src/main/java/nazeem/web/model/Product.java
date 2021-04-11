package nazeem.web.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    protected Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;


    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    @Column(name = "product_type_id")
    @NotNull(message = "Select Product type!")
    private Integer productTypeId;

    public Integer getProductTypeId(){
        return this.productTypeId;
    }
    public void setProductTypeId(Integer productTypeId){
        this.productTypeId=productTypeId;
    }

    @NotEmpty(message = "Name can't be empty!")
    @Column(name = "name")
    private String name;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    @Column(name = "brand")
    private String brand;

    public String getBrand(){
        return this.brand;
    }
    public void setBrand(String brand){
        this.brand=brand;
    }

    @Column(name = "madein")
    private String madein;

    public String getMadein(){
        return this.madein;
    }
    public void setMadein(String madein){
        this.madein=madein;
    }

    @Column(name = "price")
    private float price;

    public float getPrice(){
        return this.price;
    }
    public void setPrice(float price){
        this.price=price;
    }

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public boolean getIsDeleted(){
        return this.isDeleted;
    }
    public void setIsDeleted(boolean isDeleted){
        this.isDeleted=isDeleted;
    }

    // other getters and setters are hidden for brevity
}
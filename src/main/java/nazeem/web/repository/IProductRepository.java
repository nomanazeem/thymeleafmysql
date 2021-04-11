package nazeem.web.repository;
import nazeem.web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p " +
            //"join p.productType pt " +
            "where 1=1" +
            "and ( upper(p.name) like concat('%', upper(?1), '%') " +
            "       or upper(p.brand) like concat('%', upper(?1), '%') " +
            "       or upper(p.madein) like concat('%', upper(?1), '%')" +
            //"       or upper(pt.name) like concat('%', upper(?1), '%')" +
            ")")
    List<Product> searchProduct(String criteria);
}
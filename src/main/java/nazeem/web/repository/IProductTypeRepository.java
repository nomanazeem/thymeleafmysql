package nazeem.web.repository;

import nazeem.web.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeRepository extends JpaRepository<ProductType, Long> {

}
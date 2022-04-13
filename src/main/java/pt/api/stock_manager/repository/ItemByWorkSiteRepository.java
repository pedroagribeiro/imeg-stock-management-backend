package pt.api.stock_manager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.api.stock_manager.model.ItemByWorkSite;

@Repository
public interface ItemByWorkSiteRepository extends CrudRepository<ItemByWorkSite, Long> {}

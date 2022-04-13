package pt.api.stock_manager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.api.stock_manager.model.WorkSite;

@Repository
public interface WorkSiteRepository extends CrudRepository<WorkSite, Long> {}

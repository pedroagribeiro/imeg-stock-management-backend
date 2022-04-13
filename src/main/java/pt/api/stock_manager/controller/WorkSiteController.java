package pt.api.stock_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.api.stock_manager.model.WorkSite;
import pt.api.stock_manager.repository.WorkSiteRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/work_site")
public class WorkSiteController {

    @Autowired private WorkSiteRepository workSitesRepository;

    @GetMapping("")
    public ResponseEntity<List<WorkSite>> get_all_work_sites() {
       Iterable<WorkSite> work_sites = this.workSitesRepository.findAll();
       List<WorkSite> returnable_work_sites = new ArrayList<>();
       for(WorkSite site : work_sites) returnable_work_sites.add(site);
       return new ResponseEntity<>(returnable_work_sites, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> submit_work_site(@RequestBody WorkSite work_site) {
        this.workSitesRepository.save(work_site);
        return new ResponseEntity<>("The work site has been created", HttpStatus.CREATED);
    }
}

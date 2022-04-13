package pt.api.stock_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.api.stock_manager.model.ItemByWorkSite;
import pt.api.stock_manager.repository.ItemByWorkSiteRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items_by_work_site")
public class ItemsByWorkSiteController {

    @Autowired
    ItemByWorkSiteRepository itemsByWorkSiteRepository;

    @GetMapping("")
    public ResponseEntity<List<ItemByWorkSite>> dump_table() {
        Iterable<ItemByWorkSite> items_by_work_site = this.itemsByWorkSiteRepository.findAll();
        List<ItemByWorkSite> returnable_items_by_work_site = new ArrayList<>();
        for(ItemByWorkSite item : items_by_work_site) returnable_items_by_work_site.add(item);
        return new ResponseEntity<>(returnable_items_by_work_site, HttpStatus.OK);
    }
}

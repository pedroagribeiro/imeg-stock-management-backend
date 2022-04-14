package pt.api.stock_manager.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.api.stock_manager.model.Item;
import pt.api.stock_manager.model.ItemByWorkSite;
import pt.api.stock_manager.model.WorkSite;
import pt.api.stock_manager.repository.ItemByWorkSiteRepository;
import pt.api.stock_manager.repository.ItemRepository;
import pt.api.stock_manager.repository.WorkSiteRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired private ItemRepository itemsRepository;
    @Autowired private ItemByWorkSiteRepository itemsByWorkSiteRepository;
    @Autowired private WorkSiteRepository workSitesRepository;

    @GetMapping("")
    public ResponseEntity<List<Item>> get_all_items() {
       Iterable<Item> items = this.itemsRepository.findAll();
       List<Item> returnable_items = new ArrayList<>();
       for(Item item : items) returnable_items.add(item);
       return new ResponseEntity<>(returnable_items, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> submit_item(@RequestBody Item item) {
       this.itemsRepository.save(item);
       return new ResponseEntity("The item has been created", HttpStatus.CREATED);
    }

    @GetMapping("/stock/{item_id}")
    public ResponseEntity<?> get_item_stock(@PathVariable("item_id") long item_id) {
        Item item = null;
        if(this.itemsRepository.existsById(item_id)) item = this.itemsRepository.findById(item_id).get();
        if(item == null) return new ResponseEntity<>("There is no registered item with the provided id", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(item.getQuantity(), HttpStatus.OK);
    }

    @PostMapping("/add/item/{item_id}/work_site/{work_site_id}/quantity/{quantity}")
    public ResponseEntity<?> add_item(@PathVariable("item_id") long item_id, @PathVariable("work_site_id") long work_site_id, @PathVariable("quantity") int quantity) {
        // Gravar a linha relativa à transação
        Item item = null;
        if(this.itemsRepository.existsById(item_id)) item = this.itemsRepository.findById(item_id).get();
        if(item == null) return new ResponseEntity<>("There is no registered item with the provided id", HttpStatus.NOT_FOUND);
        WorkSite workSite = null;
        if(this.workSitesRepository.existsById(work_site_id)) workSite = this.workSitesRepository.findById(work_site_id).get();
        if(workSite == null) return new ResponseEntity<>("There is no registered work site with the provided id", HttpStatus.NOT_FOUND);
        ItemByWorkSite itemByWorkSite = new ItemByWorkSite(item, workSite, quantity);
        this.itemsByWorkSiteRepository.save(itemByWorkSite);
        // Atualizar o stock do item efetivamente
        int previous_quantity = item.getQuantity();
        item.setQuantity(previous_quantity + quantity);
        this.itemsRepository.save(item);
        return new ResponseEntity<>("The stock has been updated and the transaction has been stored", HttpStatus.CREATED);
    }

    @PostMapping("/remove/item/{item_id}/work_site/{work_site_id}/quantity/{quantity}")
    public ResponseEntity<?> remove_item(@PathVariable("item_id") long item_id, @PathVariable("work_site_id") long work_site_id, @PathVariable("quantity") int quantity) {
       // Gravar a linha relativa à transação
        Item item = null;
        if(this.itemsRepository.existsById(item_id)) item = this.itemsRepository.findById(item_id).get();
        if(item == null) return new ResponseEntity<>("There is no registered item with the provided id", HttpStatus.NOT_FOUND);
        WorkSite workSite = null;
        if(this.workSitesRepository.existsById(work_site_id)) workSite = this.workSitesRepository.findById(work_site_id).get();
        if(workSite == null) return new ResponseEntity<>("There is no registered work site with the provided id", HttpStatus.NOT_FOUND);
        if(quantity > item.getQuantity()) return new ResponseEntity<>("There is not enough stock to fullfill the request", HttpStatus.BAD_REQUEST);
        ItemByWorkSite itemByWorkSite = new ItemByWorkSite(item, workSite, quantity * (-1));
        this.itemsByWorkSiteRepository.save(itemByWorkSite);
        // Atualizar o stock do item efetivamente
        int previous_quantity = item.getQuantity();
        item.setQuantity(previous_quantity - quantity);
        this.itemsRepository.save(item);
        return new ResponseEntity<>("The stock has been updated and the transaction has been stored", HttpStatus.CREATED);
    }
}

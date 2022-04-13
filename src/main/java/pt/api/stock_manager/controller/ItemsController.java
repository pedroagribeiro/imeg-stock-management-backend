package pt.api.stock_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.api.stock_manager.model.Item;
import pt.api.stock_manager.repository.ItemRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemRepository itemsRepository;

    @GetMapping("")
    public ResponseEntity<List<Item>> get_all_items() {
       Iterable<Item> items = this.itemsRepository.findAll();
       List<Item> returnable_items = new ArrayList<>();
       for(Item item : items) returnable_items.add(item);
       return new ResponseEntity<>(returnable_items, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> submitItem(@RequestBody Item item) {
       this.itemsRepository.save(item);
       return new ResponseEntity("The item has been created", HttpStatus.CREATED);
    }

}

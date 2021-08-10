package com.doit.api.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<String> postItem(@RequestBody ItemRequest itemRequest) {
        String response = itemService.addItem(itemRequest);
        if (response == "ADDED")
            return new ResponseEntity<>("ADDED", HttpStatus.OK);
        else if (response == "CONFLICT")
            return new ResponseEntity<>("Item already exists", HttpStatus.CONFLICT);
        else
            return new ResponseEntity<>("Item content too large", HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Item>> getItems() {
        return new ResponseEntity<>(itemService.getItems(), HttpStatus.OK);
    }

    @GetMapping(path = "/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable long itemId) {
        Item item = itemService.getItem(itemId);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        return new ResponseEntity<>(item, HttpStatus.NOT_FOUND);
    }
}

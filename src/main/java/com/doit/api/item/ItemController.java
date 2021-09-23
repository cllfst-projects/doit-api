package com.doit.api.item;

import com.doit.api.project.ProjectService;
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

    @PostMapping(path = "/{projectId}")
    public ResponseEntity<String> postItem(@RequestBody ItemRequest itemRequest, @PathVariable long projectId) {
        String response = itemService.addItem(itemRequest, projectId);
        if (response == "ADDED") {
//            Project project = projectService.getProject(projectId);
//            project.addItem(item);
//            item.setProject(project);
//            itemService.updateItem(item);
            return new ResponseEntity<>("ADDED", HttpStatus.OK);
        } else if (response == "NO PROJECT")
            return new ResponseEntity<>("Project does not exist", HttpStatus.CONFLICT);
        else if (response == "CONFLICT")
            return new ResponseEntity<>("Item already exists in project", HttpStatus.CONFLICT);
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

    @PutMapping(value = "/name")
    public ResponseEntity<Item> updateItemName(@RequestBody Item item) {
        Item newItem = itemService.updateItemName(item);
        return new ResponseEntity<>(newItem, HttpStatus.OK);
    }

    @PutMapping(value = "/state")
    public ResponseEntity<Item> updateItemState(@RequestBody Item item) {
        Item newItem = itemService.updateItemState(item);
        return new ResponseEntity<>(newItem, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable long id) {
        if (itemService.deleteItem(id)) {
            return new ResponseEntity<>("item deleted", HttpStatus.OK);
        }
        ;
        return new ResponseEntity<>("item not found", HttpStatus.NOT_FOUND);
    }

}

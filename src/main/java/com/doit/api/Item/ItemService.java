package com.doit.api.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public String addItem(ItemRequest request) {
        Item item = Item.builder()
                .name(request.getName())
                .itemState(request.getItemState())
                .build();
        boolean itemExists = itemRepository.findByName(item.getName()).isPresent();
        if (item.getName().length() > 100)
            return "TOO LARGE";
        else if (itemExists)
            return ("CONFLICT");
        else {
            itemRepository.save(item);
            return "ADDED";
        }
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItem(long id) {
        Item item = getItems().stream()
                .filter(t -> id == t.getId())
                .findFirst()
                .orElse(null);
        return item;
    }


}

package com.doit.api.Item;

import lombok.Data;

@Data
public class ItemRequest {
    private String Name;
    private ItemState itemState;
}

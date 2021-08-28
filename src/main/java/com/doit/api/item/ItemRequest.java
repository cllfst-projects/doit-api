package com.doit.api.item;

import lombok.Data;

@Data
public class ItemRequest {
    private String Name;
    private ItemState itemState;
}

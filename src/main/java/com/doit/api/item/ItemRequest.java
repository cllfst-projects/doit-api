package com.doit.api.item;

import com.doit.api.project.Project;
import lombok.Data;

@Data
public class ItemRequest {
    private String name;
    private ItemState itemState;
//    private Project project;
}

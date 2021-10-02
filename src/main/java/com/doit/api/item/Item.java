package com.doit.api.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemState itemState;
    //private LocalDate startDate;
    //private LocalDate endDate;

    public Item(String name, ItemState itemState) {
        this.name = name;
        this.itemState = itemState;
    }
}

package com.doit.api.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String Name);

//    @Transactional
//    void deleteByIdIn(List<Long> ids);

//    @Modifying
//    @Query("delete from Item i where i.id in ?1")
//    void deleteItemsWithIds(List<Long> ids);
}

package com.supr.daily.repository;

import com.supr.daily.data.ItemWithDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ItemRepo extends CrudRepository<ItemWithDate,Integer> {

    Optional<ItemWithDate> findByItemNameAndDate(String itemName, String date);
    Optional<ItemWithDate> findByItemName(String itemName);

}

package com.supr.daily.service.impl;

import com.supr.daily.data.CategoryWithDate;
import com.supr.daily.data.ItemWithDate;
import com.supr.daily.exception.OrderReservationException;
import com.supr.daily.model.ItemRequest;
import com.supr.daily.model.OrderRequest;
import com.supr.daily.repository.CategoryRepo;
import com.supr.daily.repository.ItemRepo;
import com.supr.daily.service.OrderFulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderFulfilmentServiceImpl implements OrderFulfilmentService {

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    CategoryRepo categoryRepo;


    @Override
    public Boolean canFulfilOrder(OrderRequest orderRequest) {

        try {


            Map<AbstractMap.SimpleImmutableEntry<String, String>, Integer> categoryQuantityMap = new HashMap<>();
            String date = orderRequest.getDeliveryDate();


            for (ItemRequest item : orderRequest.getItems()) {

                Optional<ItemWithDate> itemWithDate = itemRepo.findByItemNameAndDate(item.getItemName(), date);
                if (!itemWithDate.isPresent() || item.getQuantity() > itemWithDate.get().getQuantity()) {
                    return false;
                }

                AbstractMap.SimpleImmutableEntry<String, String> pair = new AbstractMap.SimpleImmutableEntry(item.getCategory(), date);

                if (categoryQuantityMap.containsKey(pair)) {
                    categoryQuantityMap.put(pair, 1 + categoryQuantityMap.get(pair));
                } else {
                    categoryQuantityMap.put(pair, 1);
                }


            }

            for (AbstractMap.SimpleImmutableEntry<String, String> k : categoryQuantityMap.keySet()) {

                Optional<CategoryWithDate> categoryWithDate = categoryRepo.findByCategoryNameAndDate(k.getKey(), k.getValue());
                if (!categoryWithDate.isPresent() || categoryWithDate.get().getQuantity() < categoryQuantityMap.get(k)) {
                    return false;
                }

            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void reserveOrder(OrderRequest orderRequest) throws OrderReservationException {

        Boolean isPossible = canFulfilOrder(orderRequest);

        if (!isPossible) {
            throw new OrderReservationException();
        }

        String date = orderRequest.getDeliveryDate();

        Map<AbstractMap.SimpleImmutableEntry<String, String>, Integer> categoryQuantityMap = new HashMap<>();


        for (ItemRequest item : orderRequest.getItems()) {

            Optional<ItemWithDate> itemWithDate = itemRepo.findByItemNameAndDate(item.getItemName(), date);

            itemWithDate.get().setQuantity(itemWithDate.get().getQuantity() - item.getQuantity());
            itemRepo.save(itemWithDate.get());

            AbstractMap.SimpleImmutableEntry<String, String> pair = new AbstractMap.SimpleImmutableEntry(item.getCategory(), date);

            if (categoryQuantityMap.containsKey(pair)) {
                categoryQuantityMap.put(pair, 1 + categoryQuantityMap.get(pair));
            } else {
                categoryQuantityMap.put(pair, 1);
            }


        }

        for (AbstractMap.SimpleImmutableEntry<String, String> k : categoryQuantityMap.keySet()) {

            Optional<CategoryWithDate> categoryWithDate = categoryRepo.findByCategoryNameAndDate(k.getKey(), k.getValue());
            categoryWithDate.get().setQuantity(categoryWithDate.get().getQuantity() - categoryQuantityMap.get(k));
            categoryRepo.save(categoryWithDate.get());

        }


    }
}

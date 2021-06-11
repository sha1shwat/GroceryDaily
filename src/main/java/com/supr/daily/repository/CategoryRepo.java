package com.supr.daily.repository;

import com.supr.daily.data.CategoryWithDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CategoryRepo extends CrudRepository<CategoryWithDate,Integer> {

    Optional<CategoryWithDate> findByCategoryNameAndDate(String category, String date);
}

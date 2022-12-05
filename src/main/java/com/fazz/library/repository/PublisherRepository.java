package com.fazz.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fazz.library.model.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}

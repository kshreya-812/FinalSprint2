package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Pack;

public interface PackRepository extends JpaRepository<Pack,Long> {

}

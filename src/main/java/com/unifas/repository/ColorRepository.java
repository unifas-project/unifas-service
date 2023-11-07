package com.unifas.repository;

import com.unifas.entity.Color;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ColorRepository  extends JpaRepository<Color, Long> {
}

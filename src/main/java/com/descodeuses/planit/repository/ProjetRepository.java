package com.descodeuses.planit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.descodeuses.planit.entity.ProjetEntity; // ✅

import jakarta.persistence.Entity;


@Entity
@Repository
public interface ProjetRepository  extends JpaRepository<ProjetEntity, Long> {

}
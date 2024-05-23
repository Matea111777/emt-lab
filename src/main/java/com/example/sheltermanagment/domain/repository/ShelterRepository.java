package com.example.sheltermanagment.domain.repository;

import com.example.sheltermanagment.domain.models.Shelter;
import com.example.sheltermanagment.domain.models.ShelterID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, ShelterID> {
}

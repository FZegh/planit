package com.descodeuses.planit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.descodeuses.planit.entity.ActionEntity;


//                               ... extends JpaRepository<TYPE OBJET, TYPE ID OBJET>
// TYPE ID OBJET =  Type du champ id = Long

@Repository // Fait partie du SPRING
public interface ActionRepository extends JpaRepository<ActionEntity, Long> { // Une classe ne peut pas être en lien avec
                                                                        // interface

}

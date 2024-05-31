package org.example.imtihon_8_modul.repo;

import org.example.imtihon_8_modul.entity.Card;
import org.example.imtihon_8_modul.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole,Integer> {

}

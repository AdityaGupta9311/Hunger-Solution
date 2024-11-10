package com.springboot.hungersolution.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.hungersolution.Entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

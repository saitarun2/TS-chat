package com.hooks.pingme.repo;

import com.hooks.pingme.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {

    public Users findByusername(String username);
//    public boolean findBypassword(String password);

}

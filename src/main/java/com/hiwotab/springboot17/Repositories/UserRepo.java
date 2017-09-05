package com.hiwotab.springboot17.Repositories;

import com.hiwotab.springboot17.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Long> {
    User findByUsername(String username);
}

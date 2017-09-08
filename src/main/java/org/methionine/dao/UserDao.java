package org.methionine.dao;

import org.methionine.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

}

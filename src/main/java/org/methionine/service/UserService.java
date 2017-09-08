package org.methionine.service;

import org.methionine.dao.UserDao;
import org.methionine.model.dto.UserDTO;
import org.methionine.model.po.User;
import org.methionine.model.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User register(UserDTO userDTO){

        return userDao.save(userDTO2User(userDTO));
    }

    public User login(UserDTO userDTO){
        User user = userDao.findByUserName(userDTO.getUserName());
        if(userDTO.getPasswd().equals(user.getPasswd())){
            return user;
        }else {
            return null;
        }
    }

    private User userDTO2User(UserDTO userDTO){
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPasswd(userDTO.getPasswd());
        return user;
    }
}

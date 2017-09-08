package org.methionine.controller;

import org.methionine.model.Result;
import org.methionine.model.dto.UserDTO;
import org.methionine.model.po.User;
import org.methionine.model.vo.UserVO;
import org.methionine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static javax.swing.text.html.CSS.getAttribute;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Result register(UserDTO userDTO){
        User user = userService.register(userDTO);
        if(user != null){
            UserVO userVO = new UserVO();
            userVO.setId(user.getId());
            userVO.setUserName(user.getUserName());
            return new Result("注册成功", userVO);
        }
        else {
            return new Result("注册失败", null);
        }
    }

    @PostMapping("login")
    public Result login(UserDTO userDTO,HttpSession httpSession){
        User user = userService.login(userDTO);
        if(user != null){
            UserVO userVO = new UserVO();
            userVO.setId(user.getId());
            userVO.setUserName(user.getUserName());
            //登陆成功后将id写入session中
            httpSession.setAttribute("id", user.getId());
            return new Result("登陆成功", userVO);
        }
        else {
            return new Result("登陆失败", null);
        }
    }
}

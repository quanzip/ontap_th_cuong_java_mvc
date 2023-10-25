package com.viettel.ontap_thay_cuong.controller;

import com.viettel.ontap_thay_cuong.entities.UserEntity;
import com.viettel.ontap_thay_cuong.service.UserService;
import com.viettel.ontap_thay_cuong.service.dto.UserDTO;
import com.viettel.ontap_thay_cuong.utils.Constants;
import com.viettel.ontap_thay_cuong.utils.Utils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private List<UserEntity> userEntities;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void postConstruct() {
        userEntities = new ArrayList<>();
    }

    @GetMapping(value = "/v1/users/list")
    public ModelAndView getUserList(ModelAndView modelAndView) {
        userEntities = userService.getAllUser();
        modelAndView.addObject("students", userEntities);
        modelAndView.setViewName("user/users");
        return modelAndView;
    }

    @GetMapping(value = "/v1/users/show-add-user-form")
    public ModelAndView showAddUserForm(ModelAndView modelAndView) {
        List<RoleDTO> roleDTOS = roleService.getAllByStatus(Constants.Status.ACTIVE);
        modelAndView.addObject("roles", roleDTOS);
        modelAndView.setViewName("user/addUserForm.html");
        return modelAndView;
    }

    @PostMapping(value = "/v1/users")
    public ModelAndView createUser(@ModelAttribute @Valid UserDTO userDTO, ModelAndView modelAndView) {
        this.userService.saveUser(userDTO);
        modelAndView.setViewName("redirect:/api/v1/users/list");
        return modelAndView;
    }

    @GetMapping(value = "/v1/users/{userId}")
    public ModelAndView deleteUserById(@PathVariable Long userId){
        userService.deleteUser(userId);
        ModelAndView view1 = new ModelAndView();
        view1.setViewName("redirect:/api/v1/users/list");
        return view1;
    }

    @PostMapping(value = "/v1/users/update/action")
    public ModelAndView updateUser(@ModelAttribute @Valid UserDTO userDTO) throws Exception {
        ModelAndView view1 = new ModelAndView();
        view1.setViewName("redirect:/api/v1/users/list");
        this.userService.editUser(userDTO);
        return view1;
    }

    @GetMapping(value = "/v1/users/load-avatar")
    public void loadAvatar(@RequestParam(value = "imgName") String imgName,  HttpServletResponse httpServletResponse) throws IOException {
        if (!imgName.isEmpty()) {
            File file = new File(Utils.getStorageFolder(), imgName);
            if (file.exists() && !file.isDirectory()) {
                httpServletResponse.setContentType("application/octet-stream");
                httpServletResponse.setHeader("fileName", imgName);
                Files.copy(file.toPath(), httpServletResponse.getOutputStream());
            }
        }
    }

    @GetMapping(value = "/v1/users/show-form-edit/{userId}")
    public ModelAndView showFormToEditUser(ModelAndView modelAndView, @PathVariable(value = "userId") long userId) throws Exception {
        UserDTO student = userService.getStudentByIdAndStatus(userId, Constants.Status.ACTIVE);
        modelAndView.addObject("student", student);
      modelAndView.setViewName("user/editUserForm");
      return modelAndView;
    }
}

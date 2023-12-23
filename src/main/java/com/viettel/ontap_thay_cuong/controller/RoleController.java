package com.viettel.ontap_thay_cuong.controller;

import com.viettel.ontap_thay_cuong.service.RoleService;
import com.viettel.ontap_thay_cuong.service.UserService;
import com.viettel.ontap_thay_cuong.service.dto.RoleDTO;
import com.viettel.ontap_thay_cuong.service.dto.UserDTO;
import com.viettel.ontap_thay_cuong.service.mapper.UserMapper;
import com.viettel.ontap_thay_cuong.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/v1/roles")
    public ModelAndView viewRoles(ModelAndView modelAndView) {
        List<RoleDTO> roleDTOList = roleService.getAllActiveRoles(Constants.Status.ACTIVE);
        modelAndView.addObject("roles", roleDTOList);
        modelAndView.setViewName("role/search");
        return modelAndView;
    }

    @GetMapping(value = "/v1/show-form-add-role")
    public ModelAndView showFormAddRole(ModelAndView modelAndView){
        modelAndView.setViewName("role/add");
        List<UserDTO> userDTOS = userMapper.toDTO(userService.getAllUser());
        modelAndView.addObject("users", userDTOS);
        return modelAndView;
    }

    @PostMapping(value = "/v1/roles")
    public Object addRole(RoleDTO roleDTO, ModelAndView modelAndView) throws Exception {
        roleService.addRole(roleDTO);
        modelAndView.setViewName("redirect:/api/v1/roles");
        return  modelAndView;
    }

    @GetMapping(value = "/v1/roles/delete/by-userId/{userId}")
    public void deleteByUserId(@PathVariable(value = "userId") Long userId) {
        this.roleService.deleteByUserId(userId);
    }
    @GetMapping(value = "/v1/roles/delete/by-roleId/{roleId}")
    public ModelAndView deleteRole(ModelAndView modelAndView, @PathVariable(value = "roleId") long roleId){
        this.roleService.deleteByRoleId(roleId);
        modelAndView.setViewName("redirect:/api/v1/roles");
        return modelAndView;
    }
}

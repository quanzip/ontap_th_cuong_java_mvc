package com.viettel.ontap_thay_cuong.controller;

import com.viettel.ontap_thay_cuong.service.RoleService;
import com.viettel.ontap_thay_cuong.service.dto.RoleDTO;
import com.viettel.ontap_thay_cuong.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/v1/roles")
    public ModelAndView viewRoles(ModelAndView modelAndView) {
        List<RoleDTO> roleDTOList = roleService.getAllActiveRoles(Constants.Status.ACTIVE);
        modelAndView.addObject("roles", roleDTOList);
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @PostMapping(value = "/v1/roles")
    public Object addRole(RoleDTO roleDTO, ModelAndView modelAndView) {
        roleService.addRole(roleDTO);
        modelAndView.setViewName("redirect:/api/v1/roles");
        return  modelAndView;
    }

}

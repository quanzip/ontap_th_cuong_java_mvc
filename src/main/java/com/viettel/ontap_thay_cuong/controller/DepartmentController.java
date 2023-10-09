package com.viettel.ontap_thay_cuong.controller;

import com.viettel.ontap_thay_cuong.service.DepartmentService;
import com.viettel.ontap_thay_cuong.service.UserService;
import com.viettel.ontap_thay_cuong.service.dto.DepartmentDTO;
import com.viettel.ontap_thay_cuong.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/api")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final UserService userService;

    public DepartmentController(DepartmentService departmentService, UserService userService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/v1/departments")
    public Object getAllDepartment(ModelAndView modelAndView){
        modelAndView.setViewName("department/search");
        Object object = departmentService.getAllDepartmentByStatus(Constants.Status.ACTIVE);
        modelAndView.addObject("departments", object);
        return modelAndView;
    }

    @GetMapping(value = "/v1/departments/show-department-add-form")
    public ModelAndView showFormAdd(ModelAndView modelAndView) {
        Object object = userService.getAllUser();
        modelAndView.setViewName("department/add");
        modelAndView.addObject("users", object);
        return modelAndView;
    }

    @GetMapping(value = "/v1/department/update")
    public ModelAndView UpdateDepartment(ModelAndView modelAndView, @ModelAttribute DepartmentDTO departmentDTO) throws Exception {
        departmentService.updateDepartment(departmentDTO);
        modelAndView.setViewName("redirect:/api/v1/departments");
        return modelAndView;
    }

    @GetMapping(value = "/v1/departments/delete/{departmentId}")
    public Object deleteDepartment(ModelAndView modelAndView, @PathVariable(value = "departmentId") long departmentId) throws Exception {
        departmentService.deleteDepartmentById(departmentId);
        modelAndView.setViewName("redirect:/api/v1/departments");
        return modelAndView;
    }

    @PostMapping(value = "/v1/departments")
    public Object addDepartment(@ModelAttribute DepartmentDTO departmentDTO, ModelAndView modelAndView) {
        this.departmentService.addDepartment(departmentDTO);
        modelAndView.setViewName("redirect:/api/v1/departments");
        return modelAndView;

    }
}

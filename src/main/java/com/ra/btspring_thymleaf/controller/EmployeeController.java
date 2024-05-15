package com.ra.btspring_thymleaf.controller;

import com.ra.btspring_thymleaf.Dao.IEmployeeDao;
import com.ra.btspring_thymleaf.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private IEmployeeDao iEmployeeDao;

    @RequestMapping(value = {"/", "/home"})
    public String list(Model model) {
        model.addAttribute("employee", iEmployeeDao.getEmployees());
        return "list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Employee employee = new Employee();
        model.addAttribute("em", employee);
        return "add";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("em", iEmployeeDao.getEmployeeById(id));
        return "edit";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        iEmployeeDao.deleteEmployee(id);
        return "redirect:/home";
    }

    @PostMapping("/insertEmployee")
    public String doAdd(@Validated @ModelAttribute("em") Employee em, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        boolean bl = iEmployeeDao.insertEmployee(em);
        if (bl) {
            return "redirect:/home";
        } else {
            model.addAttribute("em", em);
            return "list";
        }
    }

    @PostMapping("/editEmployee")
    public String doEdit(@Validated @ModelAttribute("em") Employee em, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        boolean bl = iEmployeeDao.updateEmployee(em);
        if (bl) {
            return "redirect:/home";
        } else {
            model.addAttribute("em", em);
            return "edit";
        }
    }
}





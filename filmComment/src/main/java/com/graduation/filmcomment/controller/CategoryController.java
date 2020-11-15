package com.graduation.filmcomment.controller;

import com.graduation.filmcomment.entity.*;
import com.graduation.filmcomment.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private TypeService typeService;


    /**********************admin*************************/

    /**
     * 分类管理
     * @param model
     * @return
     */
    @RequestMapping("/allCategory")
    public String allCategory(Model model){
        List<LeoFilmAllType> leoFilmAllTypeList = typeService.findAllType();
        List<LeoFilmType> leoFilmTypeList = typeService.findFilmType();
        List<LeoFilmForm> leoFilmFormList = typeService.findFilmForm();
        List<LeoFilmCountry> leoFilmCountryList = typeService.findFilmCountry();
        List<LeoFilmLanguage> leoFilmLanguageList = typeService.findFilmLanguage();
        model.addAttribute("leoFilmAllTypeList",leoFilmAllTypeList);
        model.addAttribute("leoFilmTypeList",leoFilmTypeList);
        model.addAttribute("leoFilmFormList",leoFilmFormList);
        model.addAttribute("leoFilmCountryList",leoFilmCountryList);
        model.addAttribute("leoFilmLanguageList",leoFilmLanguageList);
        return "/admin/admin-all-category";
    }

    @RequestMapping("/addOneByAllCategory")
    public String editAllCategory(Integer CategoryId,Model model){
        model.addAttribute("CategoryId",CategoryId);
        return "/admin/admin-add-all-category";
    }

    @RequestMapping("/submitAddAllcategoryForm")
    public String submitAddAllcategoryForm(Integer CategoryId,String newAddType){
        typeService.insertNewType(CategoryId,newAddType);
        return "redirect:/category/allCategory";
    }

    @RequestMapping("/deleteTypeById")
    public String deleteTypeById(Integer categoryId,Integer subCategoryId){
        typeService.deleteSubCategoryById(categoryId,subCategoryId);
        return "redirect:/category/allCategory";
    }

}

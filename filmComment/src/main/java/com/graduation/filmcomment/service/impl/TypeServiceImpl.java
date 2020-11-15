package com.graduation.filmcomment.service.impl;

import com.graduation.filmcomment.entity.*;
import com.graduation.filmcomment.mapper.TypeMapper;
import com.graduation.filmcomment.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<LeoFilmType> findFilmType() {
        return typeMapper.findFilmType();
    }

    @Override
    public List<LeoFilmForm> findFilmForm() {
        return typeMapper.findFilmForm();
    }

    @Override
    public List<LeoFilmCountry> findFilmCountry() {
        return typeMapper.findFilmCountry();
    }

    @Override
    public List<LeoFilmLanguage> findFilmLanguage() {
        return typeMapper.findFilmLanguage();
    }

    @Override
    public List<LeoFilmAllType> findAllType() {
        return typeMapper.findAllType();
    }

    @Override
    public void insertNewType(Integer categoryId, String newAddType) {
        if (categoryId==1){
            typeMapper.insertNewType(newAddType);
        }else if (categoryId==2){
            typeMapper.insertNewForm(newAddType);
        }else if (categoryId==3){
            typeMapper.insertNewCountry(newAddType);
        }else {
            typeMapper.insertNewLanguage(newAddType);
        }
    }

    @Override
    public void deleteSubCategoryById(Integer categoryId, Integer subCategoryId) {
        if (categoryId==1){
            typeMapper.deleteSubTypeById(subCategoryId);
        }else if (categoryId==2){
            typeMapper.deleteSubFormById(subCategoryId);
        }else if (categoryId==3){
            typeMapper.deleteSubCountryById(subCategoryId);
        }else {
            typeMapper.deleteSubLanguageById(subCategoryId);
        }
    }
}

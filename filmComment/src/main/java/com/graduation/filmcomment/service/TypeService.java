package com.graduation.filmcomment.service;

import com.graduation.filmcomment.entity.*;

import java.util.List;

public interface TypeService {

    List<LeoFilmType> findFilmType();

    List<LeoFilmForm> findFilmForm();

    List<LeoFilmCountry> findFilmCountry();

    List<LeoFilmLanguage> findFilmLanguage();

    List<LeoFilmAllType> findAllType();

    void insertNewType(Integer categoryId, String newAddType);

    void deleteSubCategoryById(Integer categoryId, Integer subCategoryId);
}

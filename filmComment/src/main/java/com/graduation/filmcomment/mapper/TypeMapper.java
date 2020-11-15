package com.graduation.filmcomment.mapper;


import com.graduation.filmcomment.entity.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TypeMapper extends tk.mybatis.mapper.common.Mapper<LeoFilmType> {

    @Select("select * from leo_film_type")
    List<LeoFilmType> findFilmType();

    @Select("select * from leo_film_form")
    List<LeoFilmForm> findFilmForm();

    @Select("select * from leo_film_country")
    List<LeoFilmCountry> findFilmCountry();

    @Select("select * from leo_film_language")
    List<LeoFilmLanguage> findFilmLanguage();

    @Select("select * from leo_film_all_type")
    List<LeoFilmAllType> findAllType();

    @Insert("insert into leo_film_type(leo_film_type_name,leo_film_type_code) values (#{newAddType},1)")
    void insertNewType(String newAddType);
    @Insert("insert into leo_film_form(leo_film_form_name,leo_film_form_code) values (#{newAddType},2)")
    void insertNewForm(String newAddType);
    @Insert("insert into leo_film_country(leo_film_country_name,leo_film_country_code) values (#{newAddType},3)")
    void insertNewCountry(String newAddType);
    @Insert("insert into leo_film_language(leo_film_language_name,leo_film_language_code) values (#{newAddType},4)")
    void insertNewLanguage(String newAddType);

    @Delete("delete from leo_film_type where leo_film_type_id=#{subCategoryId}")
    void deleteSubTypeById(Integer subCategoryId);
    @Delete("delete from leo_film_form where leo_film_form_id=#{subCategoryId}")
    void deleteSubFormById(Integer subCategoryId);
    @Delete("delete from leo_film_country where leo_film_country_id=#{subCategoryId}")
    void deleteSubCountryById(Integer subCategoryId);
    @Delete("delete from leo_film_language where leo_film_language_id=#{subCategoryId}")
    void deleteSubLanguageById(Integer subCategoryId);
}

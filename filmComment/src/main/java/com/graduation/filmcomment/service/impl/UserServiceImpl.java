package com.graduation.filmcomment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.LeoFilm;
import com.graduation.filmcomment.entity.LeoUser;
import com.graduation.filmcomment.mapper.FilmMapper;
import com.graduation.filmcomment.mapper.UserMapper;
import com.graduation.filmcomment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FilmMapper filmMapper;


    @Override
    public void register(LeoUser leoUser) {
         userMapper.register(leoUser);
    }

    @Override
    public LeoUser loginUser(LeoUser leoUser) {
        LeoUser userExist = userMapper.loginUser(leoUser);
        return userExist;
    }

    @Override
    public LeoUser findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public List<LeoFilm> findFiveWantSeeFilmByUserId(Integer id) {
        return filmMapper.findFiveWantSeeFilmByUserId(id);
    }

    @Override
    public List<LeoFilm> findFiveSeenFilmByUserId(Integer id) {
        return filmMapper.findFiveSeenFilmByUserId(id);
    }

    @Override
    public PageInfo<LeoUser> findAll(Integer page,Integer limit,LeoUser leoUser) {
        PageHelper.startPage(page,limit);

        Example example = new Example(LeoUser.class);
        Example.Criteria criteria = example.createCriteria();

        if(leoUser.getLeoUserName()!=null){
            criteria.andLike("leoUserName","%"+leoUser.getLeoUserName()+"%");
        }

        List<LeoUser> leoUserList =  userMapper.selectByExample(example);
        PageInfo<LeoUser> pageInfo = new PageInfo<>(leoUserList);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(Integer leoUserId, Boolean leoUserStatus) {
        userMapper.updateUserStatus(leoUserId,leoUserStatus);
    }

    /**
     * 判断用户名、手机号码、邮箱是否重复
     * @param leoUserName
     * @return
     */
    @Override
    public LeoUser isExistUserName(String leoUserName) {
        return userMapper.isExistUserName(leoUserName);
    }

    @Override
    public LeoUser isExistUserPhone(String leoUserPhone) {
        return userMapper.isExistUserPhone(leoUserPhone);
    }

    @Override
    public LeoUser isExistUserEmail(String leoUserEmail) {
        return userMapper.isExistUserEmail(leoUserEmail);
    }

    /**
     * 修改用户头像
     * @param leoUserId
     * @param s
     */
    @Override
    public void updateUserImage(Integer leoUserId, String uuidFileName) {
        userMapper.updateUserImage(leoUserId,uuidFileName);
    }

    @Override
    public void modifyUserInfo(LeoUser leoUser) {
        userMapper.modifyUserInfo(leoUser);
    }

    @Override
    public LeoUser findUserByPhone(String loginPhone) {
        return userMapper.findUserByPhone(loginPhone);
    }


}

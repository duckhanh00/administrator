package com.pmh.service.services;

import com.pmh.service.domain.UserDO;

import java.util.List;

public interface UserService {

    List<UserDO> list();
    UserDO get(Integer id);
    UserDO save(UserDO user);
    UserDO update(UserDO user);
    void delete(Integer id);
}


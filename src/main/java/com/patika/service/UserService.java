package com.patika.service;

import com.patika.dao.UserDao;
import com.patika.mapper.UserMapper;
import com.patika.model.entity.User;
import com.patika.model.requestDto.UserCreateDto;
import com.patika.model.responseDto.UserDto;
import com.patika.utilities.results.DataResult;
import com.patika.utilities.results.Result;
import com.patika.utilities.results.SuccessDataResult;
import com.patika.utilities.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapper mapper;

    public DataResult<List<UserDto>> getAll() {
        List<User> users = userDao.findAll();
        List<UserDto> UserDtoList = mapper.UserListToUserDtoList(users);
        return new SuccessDataResult<>(UserDtoList);
    }

    public Result create(UserCreateDto UserCreateDto) {
        User User = mapper.UserCreateDtoToUser(UserCreateDto);
        userDao.save(User);
        return new SuccessResult("User Created!");
    }

    public DataResult<UserDto> getById(Long id) {
        User User = userDao.findById(id).orElseThrow();
        UserDto UserDto = mapper.UserToUserDto(User);
        return new SuccessDataResult<>(UserDto);
    }

    public DataResult<UserDto> getByName(String userName) {
        User User = userDao.findByUserName(userName).orElseThrow();
        UserDto UserDto = mapper.UserToUserDto(User);
        return new SuccessDataResult<>(UserDto);
    }

    private void existsById(Long id) {
        boolean exist = userDao.existsById(id);
        if (!exist)
            throw new NotFoundException("");
    }

    @Transactional
    public Result deleteByUserNameAndPhoneNumber(String userName, String phoneNumber) {
        userDao.findByUserName(userName).orElseThrow();
        Long count = userDao.deleteUserByUserNameAndPhoneNumber(userName, phoneNumber);
        if (count == 0) {
            throw new NotFoundException("User " + userName + " and " + phoneNumber + " are not related!");
        }
        return new SuccessResult("User deleted!");
    }

//    @Transactional
//    public Result updateUserById(Long id, UserUpdateDto userUpdateDto) {
//        existsById(id);
//        userDao.updateUserById(id, userUpdateDto);
//        return new SuccessResult("User price updated");
//    }
}

package com.nixs.service;

import com.nixs.dao.HibernateDao;
import com.nixs.mapper.UserDtoMapper;
import com.nixs.model.User;
import com.nixs.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDtoMapper userDtoMapper;
    private final HibernateDao<User> userDao;
    private final PasswordEncoder passwordEncoder;


    public boolean addUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userDtoMapper.parseToModel(userDto);
        return userDao.save(user);
    }

    public boolean updateUser(UserDto userDto) {
        User userById = userDao.findById(userDto.getId()).get();
        if (userDto.getPassword() != null) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        } else {
            userDto.setPassword(userById.getPassword());
        }
        User user = userDtoMapper.parseToModel(userDto);
        return userDao.save(user);
    }

    public void deleteUser(Long id) {
        userDao.delete(id);
    }

    public UserDto getUser(Long id) {
        User user = userDao.findById(id).get();
        return userDtoMapper.parseToDto(user);
    }

    public Optional<UserDto> getUserByName(String name) {
        Optional<User> user = userDao.findByName(name);
        Optional<UserDto> userDto = Optional.of(userDtoMapper.parseToDto(user.orElseThrow()));
        return userDto;
    }

    public List<UserDto> getUsers() {
        return userDao.findAll().stream()
                .map(userDtoMapper::parseToDto)
                .collect(Collectors.toList());
    }
}

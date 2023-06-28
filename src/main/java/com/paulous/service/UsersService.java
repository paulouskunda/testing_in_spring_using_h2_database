package com.paulous.service;

import com.paulous.entity.UsersEntity;
import com.paulous.pojo.UsersPojo;
import com.paulous.repository.UsersRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @SneakyThrows
    public UsersEntity fetchUserByUsername(String username){
        Optional<UsersEntity> usersEntityOptional = usersRepository.findByUsername(username);
        if (usersEntityOptional.isPresent()){
            return usersEntityOptional.get();
        }
        throw new AccountNotFoundException("User with username "+username+" not found");
    }

    public UsersEntity addNewUser(UsersPojo usersPojo){
        log.info("");
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUserRole(usersPojo.getUserRole());
        usersEntity.setFirstName(usersPojo.getFirstName());
        usersEntity.setLastName(usersPojo.getLastName());
        usersEntity.setUsername(usersPojo.getUsername());

        return usersRepository.save(usersEntity);
    }

    public List<UsersEntity> fetchAllUsers(){
        return usersRepository.findAll();
    }
}

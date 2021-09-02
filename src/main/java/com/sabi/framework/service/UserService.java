package com.sabi.framework.service;

import com.google.gson.Gson;
import com.sabi.framework.dto.requestDto.UserDto;
import com.sabi.framework.dto.responseDto.UserResponse;
import com.sabi.framework.exceptions.ConflictException;
import com.sabi.framework.helpers.CoreValidations;
import com.sabi.framework.models.User;
import com.sabi.framework.repositories.UserRepository;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.framework.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final CoreValidations coreValidations;

    public UserService(UserRepository userRepository, ModelMapper mapper,CoreValidations coreValidations) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.coreValidations = coreValidations;
    }



    public UserResponse createUser(UserDto request) {
//        coreValidations.validateFunction(request);
        User user = mapper.map(request,User.class);
        User userExist = userRepository.findByEmail(request.getEmail());
        if(userExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " User already exist");
        }
        String password = Utility.getSaltString();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setCreatedBy(0l);
        user.setIsActive(false);
        user.setResetToken(Utility.guidID());
        user.setResetTokenExpirationDate(Utility.tokenExpiration());
        user = userRepository.save(user);
        log.debug("Create new user - {}"+ new Gson().toJson(user));
        return mapper.map(user, UserResponse.class);
    }
}

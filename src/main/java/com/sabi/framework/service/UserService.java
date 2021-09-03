package com.sabi.framework.service;

import com.google.gson.Gson;
import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.requestDto.UserDto;
import com.sabi.framework.dto.responseDto.UserResponse;
import com.sabi.framework.exceptions.ConflictException;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.helpers.CoreValidations;
import com.sabi.framework.models.User;
import com.sabi.framework.repositories.UserRepository;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.framework.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {


//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final CoreValidations coreValidations;

    public UserService(UserRepository userRepository, ModelMapper mapper,CoreValidations coreValidations) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.coreValidations = coreValidations;
    }



    /** <summary>
     * User creation
     * </summary>
     * <remarks>this method is responsible for creation of new user</remarks>
     */

    public UserResponse createUser(UserDto request) {
//        coreValidations.validateFunction(request);
        User user = mapper.map(request,User.class);
        User userExist = userRepository.findByEmail(request.getEmail());
        if(userExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " User already exist");
        }
        String password = Utility.getSaltString();
//        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setPassword(password);
        user.setCreatedBy(0l);
        user.setIsActive(false);
        user.setResetToken(Utility.guidID());
        user.setResetTokenExpirationDate(Utility.tokenExpiration());
        user = userRepository.save(user);
        log.debug("Create new user - {}"+ new Gson().toJson(user));
        return mapper.map(user, UserResponse.class);
    }



    /** <summary>
     * User update
     * </summary>
     * <remarks>this method is responsible for updating already existing user</remarks>
     */

    public UserResponse updateUser(UserDto request) {
//        coreValidations.validateFunction(request);
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));
        mapper.map(request, user);
        user.setUpdatedBy(0l);
        userRepository.save(user);
        log.debug("permission record updated - {}"+ new Gson().toJson(user));
        return mapper.map(user, UserResponse.class);
    }



    /** <summary>
     * Find user
     * </summary>
     * <remarks>this method is responsible for getting a single record</remarks>
     */
    public UserResponse findUser(Long id){
        User user  = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));
        return mapper.map(user,UserResponse.class);
    }




    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this method is responsible for enabling and dis enabling a market</remarks>
     */
    public void enableDisEnableState (EnableDisEnableDto request){
        User user  = userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));
        user.setIsActive(request.getIsActive());
        user.setUpdatedBy(0l);
        userRepository.save(user);

    }




//    public void changeUserPassword(UserDto request) {
////        coreValidations.validateFunction(request);
//        User user = userRepository.findById(request.getId())
//                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
//                        "Requested user id does not exist!"));
//        mapper.map(request, user);
//        user.setUpdatedBy(0l);
//        userRepository.save(user);
//        log.debug("permission record updated - {}"+ new Gson().toJson(user));
//        return mapper.map(user, UserResponse.class);
//    }

}

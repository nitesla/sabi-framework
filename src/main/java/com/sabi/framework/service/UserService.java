package com.sabi.framework.service;

import com.google.gson.Gson;
import com.sabi.framework.dto.requestDto.*;
import com.sabi.framework.dto.responseDto.UserResponse;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.exceptions.ConflictException;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.helpers.CoreValidations;
import com.sabi.framework.helpers.Encryptions;
import com.sabi.framework.models.PreviousPasswords;
import com.sabi.framework.models.User;
import com.sabi.framework.repositories.PreviousPasswordRepository;
import com.sabi.framework.repositories.UserRepository;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.framework.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;



@SuppressWarnings("ALL")
@Slf4j
@Service
public class UserService {




    @Autowired
    private PasswordEncoder passwordEncoder;
    private PreviousPasswordRepository previousPasswordRepository;
    private UserRepository userRepository;
    private final ModelMapper mapper;
    private final CoreValidations coreValidations;

    public UserService(PreviousPasswordRepository previousPasswordRepository,UserRepository userRepository,
                       ModelMapper mapper,CoreValidations coreValidations) {
        this.previousPasswordRepository = previousPasswordRepository;
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
        coreValidations.validateUser(request);
        User user = mapper.map(request,User.class);
        User userExist = userRepository.findByEmail(request.getEmail());
        if(userExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " User already exist");
        }
        String password = request.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setCreatedBy(0l);
        user.setUserCategory(Constants.ADMIN_USER);
        user.setIsActive(false);
        user.setResetToken(Utility.registrationCode());
        user.setResetTokenExpirationDate(Utility.tokenExpiration());
        user = userRepository.save(user);
        log.debug("Create new user - {}"+ new Gson().toJson(user));
        PreviousPasswords previousPasswords = PreviousPasswords.builder()
                .userId(user.getId())
                .password(user.getPassword())
                .build();
        previousPasswordRepository.save(previousPasswords);
        return mapper.map(user, UserResponse.class);
    }



    /** <summary>
     * User update
     * </summary>
     * <remarks>this method is responsible for updating already existing user</remarks>
     */

    public UserResponse updateUser(UserDto request) {
        coreValidations.updateUser(request);
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));
        mapper.map(request, user);
        user.setUpdatedBy(0l);
        userRepository.save(user);
        log.debug("user record updated - {}"+ new Gson().toJson(user));
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
     * Find all users
     * </summary>
     * <remarks>this method is responsible for getting all records in pagination</remarks>
     */
    public Page<User> findAll(String firstName,String lastName,String phone,Boolean isActive,String email, PageRequest pageRequest ){
        Page<User> users = userRepository.findUsers(firstName,lastName,phone,isActive,email,pageRequest);
        if(users == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, " No record found !");
        }
        return users;

    }





    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this method is responsible for enabling and dis enabling a user</remarks>
     */
    public void enableDisEnableUser (EnableDisEnableDto request){
        User user  = userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));
        user.setIsActive(request.getIsActive());
        user.setUpdatedBy(0l);
        userRepository.save(user);

    }



    /** <summary>
     * Change password
     * </summary>
     * <remarks>this method is responsible for changing password</remarks>
     */

    public void changeUserPassword(ChangePasswordDto request) {
        coreValidations.changePassword(request);
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));
        mapper.map(request, user);
            if(getPrevPasswords(user.getId(),request.getPassword())){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " Password already used");
        }
        if (!getPrevPasswords(user.getId(), request.getPreviousPassword())) {
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid previous password");
        }
        String password = request.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setIsActive(true);
        user.setIsLocked("NULL");
        user.setUpdatedBy(0l);
        user = userRepository.save(user);

        PreviousPasswords previousPasswords = PreviousPasswords.builder()
                .userId(user.getId())
                .password(user.getPassword())
                .build();
        previousPasswordRepository.save(previousPasswords);

    }


    /** <summary>
     * Previous password
     * </summary>
     * <remarks>this method is responsible for fetching the last 4 passwords</remarks>
     */

    public Boolean getPrevPasswords(Long userId,String password){
        List<PreviousPasswords> prev = previousPasswordRepository.previousPasswords(userId);
        for (PreviousPasswords pass : prev
                ) {
            if (passwordEncoder.matches(password, pass.getPassword())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }





    /** <summary>
     * Unlock account
     * </summary>
     * <remarks>this method is responsible for unlocking a user account</remarks>
     */
    public void unlockAccounts (UnlockAccountRequestDto request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));
        mapper.map(request, user);
        user.setIsLocked("NULL");
        user.setFailedPasswordAttemptCount(0);
        userRepository.save(user);

    }



    /** <summary>
     * Forget password
     * </summary>
     * <remarks>this method is responsible for a user that forgets his password</remarks>
     */

    public  void forgetPassword (ForgetPasswordDto request) {
        User user = userRepository.findByEmail(request.getEmail());
        if(user == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Invalid email");
        }
        if(user.getIsActive().equals(false)){
            throw new BadRequestException(CustomResponseCode.FAILED, "User account has been disabled");
        }
        user.setResetToken(Utility.registrationCode());
        user.setResetTokenExpirationDate(Utility.tokenExpiration());
        userRepository.save(user);

        //----------------TODO -------------NOTIFICATION WITH NEW OTP

    }

    /** <summary>
     * Activate user
     * </summary>
     * <remarks>this method is responsible for activating users</remarks>
     */

    public  void activateUser (ActivateUserAccountDto request) {
        User user = userRepository.findByResetToken(request.getResetToken());
        if(user == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Invalid OTP supplied");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
        String currentDate = df.format(calobj.getTime());
        String regDate = user.getResetTokenExpirationDate();
        String result = String.valueOf(currentDate.compareTo(regDate));
        if(result.equals("1")){
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, " OTP invalid/expired");
        }

        request.setUpdatedBy(0l);
        request.setIsActive(true);
        userOTPValidation(user,request);

    }



    public User userOTPValidation(User user, ActivateUserAccountDto activateUserAccountDto) {
        user.setUpdatedBy(activateUserAccountDto.getUpdatedBy());
        user.setIsActive(activateUserAccountDto.getIsActive());
        return userRepository.saveAndFlush(user);
    }


    /** <summary>
     * Set Transaction pin
     * </summary>
     * <remarks>this method is responsible for setting new transaction pin</remarks>
     */
    public void setPin (CreateTransactionPinDto request) {
        coreValidations.validateTransactionPin(request);
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));
        mapper.map(request, user);
        String pin = Encryptions.generateSha256(request.getTransactionPin());
        user.setTransactionPin(pin);
        userRepository.save(user);

    }


    /** <summary>
     * Change Transaction pin OTP
     * </summary>
     * <remarks>this method is responsible for changing transaction pin OTP</remarks>
     */
    public void changePinOTP (CreateTransactionPinDto request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));

        if (!matchPasswords(user.getId(), request.getPassword())) {
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid password");
        }
        user.setResetToken(Utility.registrationCode());
        user.setResetTokenExpirationDate(Utility.tokenExpiration());
        user = userRepository.save(user);
        //--------- TODO NOTIFICATION --------------------
    }


    public Boolean matchPasswords(Long id,String password){
        User prev = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));
        System.out.println(":::::::::  PREV PASSWORD :::::;" + prev.getPassword());
            if (passwordEncoder.matches(password,prev.getPassword())) {
                return Boolean.TRUE;
            }

        return Boolean.FALSE;
    }



    /** <summary>
     * Change Transaction pin
     * </summary>
     * <remarks>this method is responsible for changing transaction pin </remarks>
     */

    public  void changePin (CreateTransactionPinDto request) {
        coreValidations.validateTransactionPin(request);
        User userExist = userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested user id does not exist!"));
        User user = userRepository.findByResetToken(request.getResetToken());
        if(user == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Invalid OTP supplied");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
        String currentDate = df.format(calobj.getTime());
        String regDate = user.getResetTokenExpirationDate();
        String result = String.valueOf(currentDate.compareTo(regDate));
        if(result.equals("1")){
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, " OTP invalid/expired");
        }
        String pin = Encryptions.generateSha256(request.getTransactionPin());
        userExist.setTransactionPin(pin);
        userRepository.save(userExist);

    }




}

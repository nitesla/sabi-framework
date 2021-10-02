package com.sabi.framework.helpers;


import com.sabi.framework.dto.requestDto.*;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.exceptions.ConflictException;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.models.User;
import com.sabi.framework.repositories.PermissionRepository;
import com.sabi.framework.repositories.RoleRepository;
import com.sabi.framework.repositories.UserRepository;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.framework.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@SuppressWarnings("All")
@Slf4j
@Service
public class CoreValidations {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PermissionRepository permissionRepository;

    public CoreValidations(RoleRepository roleRepository,UserRepository userRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    public void validateRole(RoleDto roleDto) {
        if (roleDto.getName() == null || roleDto.getName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Name cannot be empty");
        if (roleDto.getName().length() < 2 || roleDto.getName().length() > 100)// NAME LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid name  length");
        if (roleDto.getDescription() == null || roleDto.getDescription().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Description cannot be empty");
    }

    public void validateFunction(PermissionDto permissionDto) {
        if (permissionDto.getName() == null || permissionDto.getName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Name cannot be empty");
        if (permissionDto.getName().length() < 2 || permissionDto.getName().length() > 100)// NAME LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid name  length");

        if (permissionDto.getCode() == null || permissionDto.getCode().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Description cannot be empty");
    }

    public void validateRolePermission(RolePermissionDto rolePermissionDto) {
        if ((Long) rolePermissionDto.getRoleId() == null)
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Role Id cannot be empty");
        if (rolePermissionDto.getPermissionIds() == null)
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Role permission(s) cannot be empty");
        roleRepository.findById(rolePermissionDto.getRoleId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        " Enter a valid Role"));
        rolePermissionDto.getPermissionIds().forEach((p) -> {
            permissionRepository.findById(p).orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                    " Permission " + p + " Does not exist"));
        });
    }


    public void validateUser(UserDto userDto) {
        if (userDto.getFirstName() == null || userDto.getFirstName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "First name cannot be empty");
        if (userDto.getFirstName().length() < 2 || userDto.getFirstName().length() > 100)// NAME LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid first name  length");

        if (userDto.getLastName() == null || userDto.getLastName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Last name cannot be empty");
        if (userDto.getLastName().length() < 2 || userDto.getLastName().length() > 100)// NAME LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid last name  length");

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "email cannot be empty");
        if (!Utility.validEmail(userDto.getEmail().trim()))
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid Email Address");
        User user = userRepository.findByEmail(userDto.getEmail());
        if(user !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " Email already exist");
        }

        if (userDto.getPhone() == null || userDto.getPhone().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Phone number cannot be empty");
        if (userDto.getPhone().length() < 8 || userDto.getPhone().length() > 14)// NAME LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid phone number  length");
        if (!Utility.isNumeric(userDto.getPhone()))
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid data type for phone number ");
        User userExist = userRepository.findByPhone(userDto.getPhone());
        if(userExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, "  user already exist");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Password cannot be empty");
        if (userDto.getPassword().length() < 6 || userDto.getPassword().length() > 20)// NAME LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid password length");
//        if (!PasswordUtil.passwordValidator(userDto.getPassword()))
//            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid Password Format");
    }



    public void changeTransactionPin(ChangeTransactionPin changeTransactionPin) {
        if (changeTransactionPin.getTransactionPin() == null || changeTransactionPin.getTransactionPin().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Transaction pin cannot be empty");

        if (!Utility.isNumeric(changeTransactionPin.getTransactionPin()))
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Transaction pin must be numeric ");

        if (changeTransactionPin.getTransactionPin().length() < 4 || changeTransactionPin.getTransactionPin().length() > 6)// LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid pin length");
    }

    public void validateTransactionPin(CreateTransactionPinDto transactionPinDto) {
        if (transactionPinDto.getTransactionPin() == null || transactionPinDto.getTransactionPin().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Transaction pin cannot be empty");

        if (!Utility.isNumeric(transactionPinDto.getTransactionPin()))
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Transaction pin must be numeric ");

        if (transactionPinDto.getTransactionPin().length() < 4 || transactionPinDto.getTransactionPin().length() > 6)// LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid pin length");
    }


    public void updateUser(UserDto userDto) {
        if (userDto.getFirstName() == null || userDto.getFirstName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "First name cannot be empty");
        if (userDto.getFirstName().length() < 2 || userDto.getFirstName().length() > 100)// NAME LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid first name  length");

        if (userDto.getLastName() == null || userDto.getLastName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Last name cannot be empty");
        if (userDto.getLastName().length() < 2 || userDto.getLastName().length() > 100)// NAME LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid last name  length");

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "email cannot be empty");
        if (!Utility.validEmail(userDto.getEmail().trim()))
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid Email Address");

        if (userDto.getPhone() == null || userDto.getPhone().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Phone number cannot be empty");
        if (userDto.getPhone().length() < 8 || userDto.getPhone().length() > 14)// NAME LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid phone number  length");
        if (!Utility.isNumeric(userDto.getPhone()))
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid data type for phone number ");

    }



    public void changePassword(ChangePasswordDto changePasswordDto) {
        if (changePasswordDto.getPassword() == null || changePasswordDto.getPassword().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Password cannot be empty");
        if (changePasswordDto.getPassword().length() < 6 || changePasswordDto.getPassword().length() > 20)// NAME LENGTH*********
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid password length");
//        if (!PasswordUtil.passwordValidator(changePasswordDto.getPassword()))
//            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Invalid Password Format");
        if (changePasswordDto.getPreviousPassword() == null || changePasswordDto.getPreviousPassword().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Previous password cannot be empty");


    }

}

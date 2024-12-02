package vn.hoidanit.laptopshop.service.validator;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.UserService;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

    private final UserService userService;

    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        // Check if password fields match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Password nhập sai")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if (user.getEmail().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Email không được trống")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Additional validations can be added here
        // check email service
        if (this.userService.checkEmailExits(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email đã tồn tại")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if (user.getFirstName().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Firstname không được trống")
                    .addPropertyNode("firstName")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        if (user.getFirstName().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Lastname không được trống")
                    .addPropertyNode("lastName")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if (user.getPassword().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Password không được trống")
                    .addPropertyNode("password")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }
}

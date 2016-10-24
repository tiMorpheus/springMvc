package form.validator;

import form.model.User;
import form.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("userValidator")
public class UserFormValidator implements Validator {

    @Autowired
    @Qualifier("emailValidator")
    EmailValidator emailValidator;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.userForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.userForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","NotEmpty.userForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.userForm.sex");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.userForm.country");

        if(!emailValidator.valid(user.getEmail())){
            errors.rejectValue("email", "Pattern.userForm.email");
        }

        if(user.getNumber()==null || user.getNumber()<=0){
            errors.rejectValue("number", "NotEmpty.userForm.number");
        }

        if(user.getCountry().equalsIgnoreCase("none")){
            errors.rejectValue("country", "NotEmpty.userForm.country");
        }

        if (user.getFramework().equalsIgnoreCase("none")) {
            errors.rejectValue("framework", "Valid.userForm.framework");
        }

        if (user.getSkill().equalsIgnoreCase("none")) {
            errors.rejectValue("skill", "Valid.userForm.skill");
        }

    }
}

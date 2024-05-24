import com.example.cab302.controller.CreateAccountController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CreateAccountTest {
    CreateAccountController createAccountController;

    @BeforeEach
    void Setup(){
        createAccountController = new CreateAccountController();
    }

    @Test
    void PasswordMatchAndMeetTest(){
        // Passwords match and meet the requirements
        boolean result = createAccountController.AuthenticatePassword("Ab#45678","Ab#45678");
        assertTrue(result);
    }

    @Test
    void PasswordNoMatchButMeetTest(){
        //Passwords don't match but do meet the requirements
        boolean result = createAccountController.AuthenticatePassword("Ab#45678","aB#45678");
        assertFalse(result);
    }

    @Test
    void PasswordIllegalCharactersTest(){
        //Paassword contains illegal characters
        boolean result = createAccountController.AuthenticatePassword("\"'{}\\Ef8","\"'{}\\Ef8");
        assertFalse(result);
    }

    @Test
    void PasswordMatchButNotMeetTest(){
        //Passwords match but don't meet the requirements
        boolean result = createAccountController.AuthenticatePassword("password","password");
        assertFalse(result);
    }

    @Test
    void EmailValidTest(){
        boolean result = createAccountController.AuthenticateEmail("test@gmail.com");
        assertTrue(result);
    }

    @Test
    void EmailNoDomainTest(){
        boolean result = createAccountController.AuthenticateEmail("test@");
        assertFalse(result);
    }

    @Test
    void EmailNoPrimaryTest(){
        boolean result = createAccountController.AuthenticateEmail("@gmail.com");
        assertFalse(result);
    }
    @Test
    void EmailNoAtTest(){
        boolean result = createAccountController.AuthenticateEmail("testgmail.com");
        assertFalse(result);
    }

}

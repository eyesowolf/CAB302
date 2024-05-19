import static org.junit.jupiter.api.Assertions.*;

import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.User;
import com.example.cab302.dbmodelling.UserData;
import org.junit.jupiter.api.*;

import java.util.Objects;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {
    static User user = new User("John",
                              "Smith",
                              "Male",
                              "john.smith@test.com",
                              "P@ssw0rd",
                              "1999-04-23",
                              "What is the name of the street you grew up in?",
                              "Infinite Loop",
                              "0",
                              0);
    private static final SqliteUsersDAO UsersDAO = new SqliteUsersDAO();

    @BeforeAll
    static void AddUser(){
        UsersDAO.addUser(user);
    }

    @Test
    @Order(1)
    void AddUserTest(){
        assertTrue(user.getID()>0);
    }

    @Test
    @Order(2)
    void GetUserByEmailTest(){
        String userEmail = user.getEmail();
        User pulledUser = UsersDAO.getUserByEmail(userEmail);
        assertEquals(user.getID(), pulledUser.getID());
    }

    @Test
    @Order(3)
    void GetUserByIDTest(){
        int userID = user.getID();
        User pulledUser = UsersDAO.getUserById(userID);
        assertEquals(user.getID(), pulledUser.getID());
    }

    @Test
    @Order(4)
    void UpdateUserTest(){
        User copyOfUser = user;
        copyOfUser.setPassword("P@ssw0rd2");
        UsersDAO.updateUser(copyOfUser);
        User pulledUser = UsersDAO.getUserById(user.getID());
        boolean result = Objects.equals(pulledUser.getPassword(), "P@ssw0rd2");
        assertTrue(result);
    }

    @Test
    @Order(5)
    void DeleteUserTest(){
        UsersDAO.deleteUser(user);
        assertNull(UsersDAO.getUserById(user.getID()));
    }
}

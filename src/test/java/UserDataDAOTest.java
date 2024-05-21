import com.example.cab302.dbmodelling.SqliteUserDataDAO;
import com.example.cab302.dbmodelling.SqliteUsersDAO;
import com.example.cab302.dbmodelling.User;
import com.example.cab302.dbmodelling.UserData;
import com.example.cab302.MoodEApplication;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDataDAOTest {
    static MoodEApplication app = new MoodEApplication();
    static User user = new User("John",
                              "Smith",
                              "Male",
                              "john.smith@test.com",
                              "P@ssw0rd",
                              app.convertDateToEpoch("1999-04-23"),
                              "What is the name of the street you grew up in?",
                              "Infinite Loop",
                              "0",
                              0);
    public static SqliteUsersDAO UsersDAO = new SqliteUsersDAO();
    public static SqliteUserDataDAO UserDataDAO = Initialise();

    UserData data1 = new UserData("TestEntry1",
            app.convertDateToEpoch("2024-5-12"),
            "Happy",
            "Some random description",
            user.getID());

    UserData data2 = new UserData("TestEntry2",
            app.convertDateToEpoch("2024-4-12"),
            "Happy",
            "Some random description",
            user.getID());

    UserData data3 = new UserData("TestEntry3",
            app.convertDateToEpoch("2024-3-12"),
            "Happy",
            "Some random description",
            user.getID());

    UserData data4 = new UserData("TestEntry4",
            app.convertDateToEpoch("2024-2-12"),
            "Happy",
            "Some random description",
            user.getID());

    UserData data5 = new UserData("TestEntry5",
            app.convertDateToEpoch("2024-1-12"),
            "Happy",
            "Some random description",
            user.getID());

    UserData data6 = new UserData("TestEntry6",
            app.convertDateToEpoch("2023-12-12"),
            "Happy",
            "Some random description",
            user.getID());

    static SqliteUserDataDAO Initialise(){
        UsersDAO.addUser(user);
        SqliteUserDataDAO UserDataDAOLocal = new SqliteUserDataDAO(user.getID());
        return UserDataDAOLocal;
    }

    @Test
    @Order(1)
    void AddUserDataTest(){
        UserDataDAO.addUserData(data1);
        UserDataDAO.addUserData(data2);
        UserDataDAO.addUserData(data3);
        UserDataDAO.addUserData(data4);
        UserDataDAO.addUserData(data5);
        UserDataDAO.addUserData(data6);
    }

    @Test
    @Order(2)
    void GetUserDataTest(){
        int start = app.convertDateToEpoch("2024-2-15");
        int end = app.convertDateToEpoch("2024-5-19");
        List<UserData> userDataList = UserDataDAO.getUserDataInRange(start,end);
        int size = userDataList.size();
        assertEquals(3, size);
    }

    @Test
    @Order(3)
    void UpdateUserTest(){
        UserData copyOfData6 = data6;
        copyOfData6.setID(6);
        copyOfData6.setDate(app.convertDateToEpoch("2024-3-13"));
        UserDataDAO.updateUserData(copyOfData6);
        int start = app.convertDateToEpoch("2024-2-15");
        int end = app.convertDateToEpoch("2024-5-19");
        List<UserData> userDataList = UserDataDAO.getUserDataInRange(start,end);
        int size = userDataList.size();
        assertEquals(4, size);
    }

    @Test
    @Order(4)
    void DeleteUserDataTest(){
        data1.setID(1);
        UserDataDAO.deleteEntry(data1);
        int start = app.convertDateToEpoch("2024-2-15");
        int end = app.convertDateToEpoch("2024-5-19");
        List<UserData> userDataList = UserDataDAO.getUserDataInRange(start,end);
        int size = userDataList.size();
        assertEquals(3, size);
    }

    @AfterAll
    static void cleanup(){
        UsersDAO.deleteUser(user);
        UserDataDAO.deleteAllUserData(user);
    }
}

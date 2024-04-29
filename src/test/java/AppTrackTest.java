import com.example.cab302.ApplicationTracker;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class AppTrackTest {
    ApplicationTracker applicationTracker;

    @BeforeEach
    void Setup(){
        applicationTracker = new ApplicationTracker();
    }

    @Test
    void StringNotNull(){
        String activeWindow = applicationTracker.getActiveWindow();
        assertNotNull(activeWindow);
    }
}

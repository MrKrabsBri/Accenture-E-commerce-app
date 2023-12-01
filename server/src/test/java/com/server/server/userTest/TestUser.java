package com.server.server.userTest;
import com.server.server.enums.UserType;
import com.server.server.models.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TestUser {

    @Test
    public void testUserConstructor() {
        String username = "tesingUsername";
        String password = "w0rkingP@ssWord123!";
        UserType userType = UserType.ADMIN;
        String email = "dummyEmail@testing.com";

        User user = new User(username, password, userType, email);

        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(userType, user.getUserType());
        assertEquals(email, user.getEmail());

        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());

        assertEquals(user.getCreatedAt(), user.getUpdatedAt());
    }

    @Test
    public void testUserGetterSetter() {
        User user = new User();

        int userId = 1;
        String username = "dummyUsername";
        String password = "harqwedP@ssWord123";
        UserType userType = UserType.ADMIN;
        String email = "dummy@email.com";
        LocalDateTime updatedAt = LocalDateTime.now();

        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);
        user.setEmail(email);
        user.setUpdatedAt(updatedAt);

        assertEquals(userId, user.getUserId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(userType, user.getUserType());
        assertEquals(email, user.getEmail());
        assertEquals(updatedAt, user.getUpdatedAt());
    }
}

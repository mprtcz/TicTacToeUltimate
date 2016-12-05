package com.mprtcz.tictactoeultimate.mapper;

import com.mprtcz.tictactoeultimate.model.User;
import com.mprtcz.tictactoeultimate.model.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Azet on 2016-12-05.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootApplication
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void PersonToPersonDTOTest_allFields() {
        User testUser = getDummyUser();
        UserDTO resultedUser = userMapper.toDTO(testUser);

        assertNotNull(resultedUser);
        assertEquals(testUser.getSsoId(), resultedUser.getSsoId());
        assertEquals(testUser.getNickname(), resultedUser.getNickname());
        assertEquals(testUser.getEmail(), resultedUser.getEmail());
        assertEquals(testUser.getRole(), resultedUser.getRole());
    }


    private User getDummyUser() {
        User user = new User();
        user.setId(1L);
        user.setSsoId("DummySSOID");
        user.setNickname("DummyUserNickname");
        user.setPassword("DummyPassword");
        user.setEmail("DummyEmail");
        user.setRole("ROLE_USER");
        return user;
    }
}

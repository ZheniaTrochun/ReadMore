package com.yevhenii.kpi.readmore;

import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import com.yevhenii.kpi.readmore.service.AdminService;
import com.yevhenii.kpi.readmore.service.AdminServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    private AdminService adminService;

    @Before
    public void setup() {
        repository = Mockito.mock(UserRepository.class);

        Mockito.when(repository.findUserByName("user"))
                .thenReturn(Optional.of(new User()));
        Mockito.when(repository.findUserByName("user1"))
                .thenReturn(Optional.empty());

        Mockito.when(repository.findUserByEmail("test@mail.com"))
                .thenReturn(Optional.of(new User()));
        Mockito.when(repository.findUserByEmail("test1@mail.com"))
                .thenReturn(Optional.empty());

        Mockito.when(repository.save(Matchers.any(User.class)))
                .thenReturn(new User());

        this.adminService = new AdminServiceImpl(repository, encoder, null);
    }

    @Test
    public void registerPositive() throws RegistrationException {
        User user = adminService.register("user1", "test1@mail.com", "123");

        Assert.assertNotNull(user);
    }

    @Test(expected = RegistrationException.class)
    public void registerNegative() throws RegistrationException {
        User user = adminService.register("user", "test1@mail.com", "123");

        Assert.assertNotNull(user);
    }
}

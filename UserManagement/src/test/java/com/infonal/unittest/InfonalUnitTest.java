package com.infonal.unittest;

import com.infonal.controler.UserBean;
import com.infonal.db.MyEntityManager;
import com.infonal.entities.Users;
import com.infonal.validations.UserValidations;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;

import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 *
 * @author mkilic
 */
@RunWith(MockitoJUnitRunner.class)
public class InfonalUnitTest {

    @Mock
    UserBean userbean;

    @Test
    public void checkUserTest() {
        Users users = new Users();
        users.setFirstName("Test");
        users.setLastName("Test");
        String check = UserValidations.checkNull(users);
        Assert.assertEquals("99", check);
    }

    @Test
    public void userResultList() {
        org.mockito.Mockito.when(userbean.getUserList()).thenReturn(mockResultList());
        Assert.assertEquals(1, userbean.getUserList().size());
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("beforeclass");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("afterclass");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("setup");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }

    public List<Users> mockResultList() {
        List<Users> users = new ArrayList<Users>();
        Users user = new Users();
        user.setId("1");
        user.setFirstName("Mert");
        user.setLastName("Metin");
        user.setLastName("(507) 131-7735");
        users.add(user);
        return users;
    }
}

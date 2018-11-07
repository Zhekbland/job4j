package ru.job4j.lambda;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenWeGetConstructorThroughLambdaReference() {
        List<String> names = Arrays.asList("Petr", "Nick", "Ban");
        UserConvert users = new UserConvert();
        List<UserConvert.User> except = new ArrayList<>();
        List<UserConvert.User> data = users.convert(names, UserConvert.User::new);
        except.add(data.get(0));
        except.add(data.get(1));
        except.add(data.get(2));
        assertThat(data, is(except));
    }
}

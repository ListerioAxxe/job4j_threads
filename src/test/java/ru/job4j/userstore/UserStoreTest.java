package ru.job4j.userstore;

import org.junit.Test;
import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertThat;

public class UserStoreTest {

    @Test
    public void whenAddAndGet() throws InterruptedException {
        UserStore store = new UserStore();
        Thread thread1 = new Thread(() -> {
            User user1 = new User(1, 100);
            store.add(user1);
        });
        Thread thread2 = new Thread(() -> {
            User user2 = new User(2, 200);
            store.add(user2);
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(store.get(1).getAmount(), is(100));
    }
}
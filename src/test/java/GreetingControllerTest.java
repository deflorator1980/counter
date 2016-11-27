import hello.Application;
import hello.Greeting;
import hello.GreetingController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.io.RandomAccessFile;

import static org.junit.Assert.*;

/**
 * Created by a on 25.11.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class GreetingControllerTest {
/*
   * Юнит-тестируем по очереди  все методы
   *
 */
    @Autowired
    GreetingController gc;

    @Test
    public void disp() throws IOException {          // если файлик отсутствует, тоже работает
        RandomAccessFile raf = new RandomAccessFile("storage", "r");
        raf.seek(0);
        long fromFile = raf.readLong();

        assertEquals(new Greeting(fromFile, "Hello, a!"), gc.display("a"));
    }

    @Test
    public void increm() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("storage", "r");
        raf.seek(0);
        long fromFile = raf.readLong();

        assertEquals(new Greeting(fromFile + 1, "Hello, a!"), gc.increment("a"));
    }

    @Test
    public void decrem() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("storage", "r");
        raf.seek(0);
        long fromFile = raf.readLong();

        assertEquals(new Greeting(fromFile - 1, "Hello, a!"), gc.decrement("a"));
    }


}
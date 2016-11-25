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
    @Test
    public void disp() throws Exception {
        System.out.println("hui");
    }

    @Test
    public void constr() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("storage.txt", "r");
        raf.seek(0);
        long fromFile = raf.readLong();
//        System.out.println(raf.readLong());
        GreetingController gc = new GreetingController();

        assertEquals(new Greeting(fromFile, "Hello, a!"), gc.display("a"));
    }

}
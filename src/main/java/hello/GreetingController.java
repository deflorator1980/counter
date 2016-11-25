package hello;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {


    private static final String template = "Hello, %s!";  // Чисто приветственная надпись, чтобы не только цифры были
    private AtomicLong counter = null;
    private RandomAccessFile raf = null;

    /*
    * Данные счётчика берём из файлика.
    * Если файла нет - создаёт и записываем в него нулевое значение
    */
    public GreetingController() throws IOException {
        if (!new File("storage.txt").isFile()) {
            raf = new RandomAccessFile("storage.txt", "rw"); //доступ к файлу
            raf.seek(0);                                     //позиция в файле
            raf.writeLong(0l);                               // тут понятно :)
        } else {
            raf = new RandomAccessFile("storage.txt", "rw");
        }
        raf.seek(0);
        this.counter = new AtomicLong(raf.readLong());
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.POST) // можно имя вводить, если потом аутентификацию добавлять в приложение - пригодиться
    public Greeting increment(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
        Long count = counter.incrementAndGet();
        raf.seek(0);
        raf.writeLong(count);
        return new Greeting(count,
                String.format(template, name));
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting display(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.get(),
                String.format(template, name));
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.DELETE)
    public Greeting decrement(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
        Long count = counter.decrementAndGet();
        raf.seek(0);
        raf.writeLong(count);
        return new Greeting(count,
                String.format(template, name));
    }
}

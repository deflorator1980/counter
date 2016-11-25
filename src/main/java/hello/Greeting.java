package hello;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data                   // плагин Ломбок. Делает геттеры, сеттеры, переопределяет toString, equals и прочие плюшки
@AllArgsConstructor
public class Greeting {
    private final long id;
    private final String content;
}

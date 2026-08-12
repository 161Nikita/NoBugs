package utils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.METHOD, ElementType.TYPE}) // Можно ставить на метод или на весь класс
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(APIVersionCondition.class) // Связываем аннотацию с логикой фильтрации
public @interface APIVersion {
    String value(); // Здесь будем указывать версию, например "with_database"
}
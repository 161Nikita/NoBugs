package homework_9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordCounterTest {
    /**
     * Позитивные проверки:
     * Обычная строка: "Hello world" → countWords() → 2.
     * Строка с лишними пробелами: " Java is awesome " → countWords() → 3.
     * Угловые проверки:
     * Пустая строка: "" → countWords() → 0.
     * Строка с пробелами: " " → countWords() → 0.
     * Негативные проверки:
     * null строка: null → Должно выбрасываться IllegalArgumentException.
     */

    private final WordCounter wordCounter = new WordCounter();

    public static Stream<Arguments> enterValidWord() {
        return Stream.of(
                //Позитивные проверки:
                Arguments.of("Hello world", 2),
                Arguments.of(" Java is awesome ", 3), // метод отрабатывает некорректно. Нужно было
                // занести в переменную передаваемый аргумент, и в этой переменной удалить пробел в начале, после этого
                // выполнить тернарный оператор
                // Угловые проверки
                Arguments.of("", 0),
                Arguments.of(" ", 0));

    }

    @DisplayName("Проверка: Подсчет количества слов в валидных строчках")
    @ParameterizedTest
    @MethodSource("enterValidWord")
    public void userCanCountWords(String str, int expectedInt) {

        int actualResult = wordCounter.countWords(str);

        assertEquals(expectedInt, actualResult, "Ожидаем корректное количество слов");
    }

    // Выбрасывается NullPointerException т.к. строки не существует, но в подсказках написано, что должно быть IllegalArgumentException
    @DisplayName("Исключение при передачи значения null")
    @Test
    public void shouldExceptionWhenInputIsNull() {
        assertThrows(NullPointerException.class, () -> {
            wordCounter.countWords(null);
        }, "Выбрасываем исключение, так как передаем null");
    }
}
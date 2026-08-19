package utils;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import java.util.Optional;

public class APIVersionCondition implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        // 1. Получаем версию бэкенда, которая сейчас запущена (из System Properties или Environment)
        // Если переменная не задана, по умолчанию ставим ту, что сейчас в Docker
        String currentBackendVersion = System.getProperty("backend.version", "with_database");

        // 2. Ищем аннотацию на методе или на классе теста
        Optional<APIVersion> annotation = context.getTestMethod()
                .flatMap(method -> Optional.ofNullable(method.getAnnotation(APIVersion.class)))
                .or(() -> context.getTestClass()
                        .flatMap(clazz -> Optional.ofNullable(clazz.getAnnotation(APIVersion.class))));

        if (annotation.isPresent()) {
            String requiredVersion = annotation.get().value();

            // 3. Если версии совпадают — запускаем тест, если нет — пропускаем
            if (currentBackendVersion.equalsIgnoreCase(requiredVersion)) {
                return ConditionEvaluationResult.enabled("Тест запущен: версия совпадает (" + requiredVersion + ")");
            } else {
                return ConditionEvaluationResult.disabled("Тест пропущен: требуется версия "
                        + requiredVersion + ", а запущена " + currentBackendVersion);
            }
        }

        // Если аннотации нет, запускаем тест по умолчанию
        return ConditionEvaluationResult.enabled("Аннотация @APIVersion отсутствует, тест запущен");
    }
}
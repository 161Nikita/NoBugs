package practice_5.cost_counter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CostCounter {

    // массив, индекс = номер месяца
    private ArrayList<Double> costsPerMonth;

    public CostCounter() {
        this.costsPerMonth = new ArrayList<>();
    }

    // метод добавления значения по номеру месяца (от 1 до 12) и расходам в этом месяце
    public void addCosts(int month, double costs) {
        costsPerMonth.add(month-1, costs);
    }

    // метод получения значения расхода по месяцу
    public Double getCosts(int month) {
        return costsPerMonth.get(month-1);
    }

    // считать минимальный расход за месяц
    public Double minCostsPerMonth() {
        AtomicReference<Double> min = new AtomicReference<>(costsPerMonth.getFirst());
        costsPerMonth.forEach(costsPerMonth -> {
            if (costsPerMonth < min.get()) {
                min.set(costsPerMonth);
            }
        });
        return min.get();
    }
}

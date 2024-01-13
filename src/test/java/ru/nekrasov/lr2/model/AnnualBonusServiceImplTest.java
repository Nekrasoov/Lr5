package ru.nekrasov.lr2.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        Positions positions = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;


        double result = new AnnualBonusServiceImpl().calculate(positions, salary, bonus, workDays, Year.now().getValue());

        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void calculateQuarterlyBonus() {
        Positions positions = Positions.MANAGER;
        double bonus = 0.2;
        int workDays = 243;
        double salary = 100000.00;

        double result = new AnnualBonusServiceImpl().calculateQuarterlyBonus(positions, salary, bonus, workDays);

        double expected = 17423.8683127572;
        assertThat(result).isEqualTo(expected);
    }
}
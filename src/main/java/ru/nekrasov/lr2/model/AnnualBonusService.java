package ru.nekrasov.lr2.model;

import org.springframework.stereotype.Service;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays,int year);
    double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays);

    Double calculate(Positions position, String salary, String bonus, String workDays, int currentYear);
}
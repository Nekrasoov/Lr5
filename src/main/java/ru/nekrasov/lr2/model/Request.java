package ru.nekrasov.lr2.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Request {
    /***
     * Уникальный идентификатор сообщения
     */
    @NotBlank(message = "UID не может быть пустым")
    @Size(max = 32, message = "Символов не должно быть больше 32")
    private String uid;
    /***
     * Уникальный идентификатор операции
     */
    @NotBlank (message = "UID не может быть пустым")
    @Size (max = 32, message = "Символов не должно быть больше 32")
    private String operationUid;
    /***
     * Имя системы, которая отправила сообщение
     */
    private SystemName   systemName;
    @NotBlank (message = "UID не может быть пустым")
    /***
     * Время получения запроса
     */
    private String systemTime;
    /***
     * Источник получения запроса
     */
    private String source;
    /***
     * Позиция сотрудника для расчета премии
     */
    private Positions position;
    /***
     * Зарплата сотрудника
     */
    private String salary;
    /***
     * Индивидуальный бонус сотрудника
     */
    private String bonus;
    /***
     * Количество отработанных дней сотрудником в этом году
     */
    private String workDays;


    @NotNull(message = "Communication ID не может быть пустым")
    @Min(value = 1, message = "Communication ID должен быть не менее 1")
    @Max(value = 100000, message = "Communication ID должен быть не более 100000")
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;

    @Override
    public String toString(){
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                "}";
    }
}

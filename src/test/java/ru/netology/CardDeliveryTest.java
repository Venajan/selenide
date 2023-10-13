package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {


    @BeforeEach
    public void initEach() {
        open("http://localhost:9999");
    }

    @Test
    void shouldPositiveTestWithY() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Йошкар-Ола");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+76665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofMillis(15000))
                .shouldHave(exactText("Успешно! Встреча успешно забронирована на " + meetingDate));
    }

    @Test
    void shouldPositiveTestWithYo() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Орёл");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+76665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofMillis(15000))
                .shouldHave(exactText("Успешно! Встреча успешно забронирована на " + meetingDate));
    }

    @Test
    void shouldUnknownCity() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Камызяк");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+76665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldEmptyInCityField() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+76665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldDateLessThreeDays() {
        String meetingDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+76665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldEmptyDate() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+76665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    void shouldEmptyName() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("");
        $("[name='phone']").setValue("+76665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNameWithDash() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов-Петров Иван");
        $("[name='phone']").setValue("+76665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofMillis(15000))
                .shouldHave(exactText("Успешно! Встреча успешно забронирована на " + meetingDate));
    }

    @Test
    void shouldLatinLettersInName() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Weghorst Wout");
        $("[name='phone']").setValue("+76665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFieldNameWithOneWord() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов");
        $("[name='phone']").setValue("+76665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldEmptyPhoneField() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldPhoneWithoutPlus() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("86665554433");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestPhoneMore11Numbers() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+766655544333");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTestPhoneLess11Numbers() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+7666555443");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestAgreementBoxUnchecked() {
        String meetingDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Астрахань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+76665554433");
        $("[class='button__text']").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}
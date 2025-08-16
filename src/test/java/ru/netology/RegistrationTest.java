package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static java.time.LocalDate.now;


public class RegistrationTest {

    public String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test

    void shouldRegisterAplicationCard() {
        String planningDate = generateDate(4, "dd.MM.yyyy");

        Selenide.open("http:localhost:9999");
        $("[data-test-id='city'] input").setValue("Екатеринбург");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").press(Keys.DELETE);
        $("[data-test-id='date'] input"). setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Сергей");
        $("[data-test-id='phone'] input").setValue("+71231231231");
        $("[data-test-id='agreement']").click();
        $(".button__text").click();
        $("[data-test-id='notification']").should(Condition.visible,Duration.ofSeconds(15))
                .should(Condition.text("Встреча успешно забронирована на " + planningDate));


    }


}

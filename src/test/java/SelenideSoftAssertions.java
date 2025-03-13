import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideSoftAssertions {
    @BeforeAll
    static void setupConfig() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void checkJunitExample() {
        //открываем Github
        open("https://github.com");
        //кликаем на кнопку поиска
        $("[data-target='qbsearch-input.inputButton']").click();
        //проверяем, что отобразилась строка поиска, и вводим в неё название Selenide
        $("[data-target='query-builder.input']").shouldBe(visible).setValue("Selenide").pressEnter();
        //выбираем первый в списке репозиторий, проверив, что ссылка корректная
        $("[data-testid='results-list']").$$("div").first().$("a").shouldHave(href("/selenide/selenide")).click();
        //кликаем на вкладку Wiki
        $("#wiki-tab").click();
        //проверяем, что существует раздел Soft Assertions и кликаем на него
        $("#wiki-body").$$("a").findBy(text("Soft assertions")).click();
        //проверяем, что в разделе есть пример кода для JUnit5
        $(".markdown-body").shouldHave(text(
                """
                        @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                          @Test
                          void test() {
                            Configuration.assertionMode = SOFT;
                            open("page.html");
                        
                            $("#first").should(visible).click();
                            $("#second").should(visible).click();
                          }
                        }"""));
    }
}

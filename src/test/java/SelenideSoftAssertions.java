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
        open("https://github.com");
        $("[data-target='qbsearch-input.inputButton']").click();
        $("[data-target='query-builder.input']").shouldBe(visible).setValue("Selenide").pressEnter();
        $("[data-testid='results-list']").$$("div").first().$("a").shouldHave(href("/selenide/selenide")).click();
        $("#wiki-tab").click();
        $("#wiki-body").$$("a").findBy(text("Soft assertions")).click();
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

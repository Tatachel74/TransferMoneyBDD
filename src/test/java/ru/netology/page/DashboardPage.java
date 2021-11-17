package ru.netology.page;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement firstButton = $$("[data-test-id=action-deposit]").first();
    private SelenideElement secondButton = $$("[data-test-id=action-deposit]").last();
    private SelenideElement balance1 = $$(".list__item").first();
    private SelenideElement balance2 = $$(".list__item").last();

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage transferToFirst(int amount) {
        firstButton.click();
        return new TransferPage(DataHelper.getSecondNumberCard(), amount);
    }

    public TransferPage transferToSecond(int amount){
        secondButton.click();
        return new TransferPage(DataHelper.getFirstNumberCard(), amount);
    }

    public int restOfMoney1(){
        String result = balance1.getText().split(" ")[5];
        int num = Integer.parseInt(result);
        return num;
    }

    public int restOfMoney2(){
        String result = balance2.getText().split(" ")[5];
        int num = Integer.parseInt(result);
        return num;
    }


}

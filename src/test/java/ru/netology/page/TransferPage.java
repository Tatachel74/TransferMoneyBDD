package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement send = $("[data-test-id=action-transfer]");
    private String cardNumber;
    private int transferAmount;
    private SelenideElement errorToast = $("[data-test-id=error-notification]");

    public void getErrorToast() {
        errorToast.shouldBe(Condition.visible);
    }
    public TransferPage(String cardNumber, int transferAmount){
        amount.shouldBe(Condition.visible);
        this.cardNumber = cardNumber;
        this.transferAmount = transferAmount;
    }

    public DashboardPage transferResult(){
        amount.setValue(Integer.toString(transferAmount));
        from.setValue(cardNumber);
        send.click();
        return new DashboardPage();
    }
}


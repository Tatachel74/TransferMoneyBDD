package ru.netology.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MoneyTransferTest {
    @Order(1)
    @Test
    void shouldTransferMoneyTo1Card() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val amount = 100;
        val dashboardPage = loginPage.validLogin(authInfo).validVerify(DataHelper.getVerificationCodeFor(authInfo));
        int balance1Before = dashboardPage.restOfMoney1();
        int balance2Before = dashboardPage.restOfMoney2();
        val transferPage = dashboardPage.transferToFirst(amount);
        val newDashboardPage = transferPage.transferResult();
        assertEquals(balance1Before + amount, newDashboardPage.restOfMoney1());
        assertEquals(balance2Before - amount, newDashboardPage.restOfMoney2());
    }

    @Order(2)
    @Test
    void shouldTransferMoneyTo2Card() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val amount = 100;
        val dashboardPage = loginPage.validLogin(authInfo).validVerify(DataHelper.getVerificationCodeFor(authInfo));
        int balance1Before = dashboardPage.restOfMoney1();
        int balance2Before = dashboardPage.restOfMoney2();
        val transferPage = dashboardPage.transferToSecond(amount);
        val newDashboardPage = transferPage.transferResult();
        assertEquals(balance1Before - amount, newDashboardPage.restOfMoney1());
        assertEquals(balance2Before + amount, newDashboardPage.restOfMoney2());
    }

    @Order(4)
    @Test
    void shouldTransferMoneyMoreThanThereIs() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val dashboardPage = loginPage.validLogin(authInfo).validVerify(DataHelper.getVerificationCodeFor(authInfo));
        int balance1Before = dashboardPage.restOfMoney1();
        val transferPage = dashboardPage.transferToSecond(balance1Before + 1);
        transferPage.transferResult();
        transferPage.getErrorToast();
    }

    @Order(3)
    @Test
    void shouldTransferMoney0() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val dashboardPage = loginPage.validLogin(authInfo).validVerify(DataHelper.getVerificationCodeFor(authInfo));
        val transferPage = dashboardPage.transferToSecond(DataHelper.getZero());
        transferPage.transferResult();
        transferPage.getErrorToast();
    }
}


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class VladimirMikhnevichTest {

    //    TC 1_1 - Тест кейс
//    1. Открыть страницу https://openweathermap.org/   "D:\Application\chromedriver_win32\chromedriver.exe"
//    2. Набрать в строке поиска город Paris   youtube 4.20 - 4.31 про поиск по XPath divam
//    3. Нажать пункт меню Search
//    4. Из выпадающего списка выбрать Paris, FR
//    5. Подтвердить что загаловокь изменился на "Paris, FR"
    @Test
    public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Application\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']"));
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropDownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']"));
        parisFRChoiceInDropDownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

//        Thread.sleep(5000);

        driver.quit();
//        driver.close();
    }

    //    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу
// со ссылкой https://openweathermap.org/guide и что title этой
// страницы OpenWeatherMap API guide - OpenWeatherMap
    @Test

    public void testHGuideUrlAndHeader() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Application\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        driver.get(url);
        Thread.sleep(5000);

        WebElement guideElementInMenu = driver.findElement(
                By.xpath("//a[@href= '/guide']"));

        guideElementInMenu.click();

        Thread.sleep(1000);

        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);


        driver.quit();
    }

    //    TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//
//3.  Подтвердить, что температура для города показана в Фарингейтах
    @Test
    public void test2() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Application\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement menuImperial = driver.findElement(
                By.xpath("//div[@class = 'switch-container']/div[@class='option']/following-sibling::div"));

        menuImperial.click();

        Thread.sleep(1000);

        WebElement tempF = driver.findElement(By.xpath("//div[@class='current-temp']/span"));

        String tempInF = tempF.getText();
        String actualResult = tempInF.substring((tempInF.length() - 2));

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();


    }

    //    TC_11_03
//1.  Открыть базовую ссылку
//2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential
// for the site to work. We also use non-essential cookies to help us improve our services.
// Any data collected is anonymised. You can allow all cookies or manage them individually.”
//3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies” 3.00.12 youtube
    @Test

    public void testApproveTwoButtonsInPanel() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Application\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential\n"
                + " for the site to work. We also use non-essential cookies to help us improve our services.\n"
                + " Any data collected is anonymised. You can allow all cookies or manage them individually.";

        driver.get(url);
        Thread.sleep(5000);

        WebElement textElement = driver.findElement(
                By.className("stick-footer-panel__description"));

        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//button[text()='Allow all']"));
        WebElement buttonManagerCookies = driver.findElement(
                By.xpath("//a[@href='/cookies-settings']"));

        Assert.assertEquals(buttonAllowAll.getText(), "Allow all");
        Assert.assertEquals(buttonManagerCookies.getText(), "Manage cookies");
        Assert.assertEquals(textElement.getText(), expectedResult);

        driver.quit();
    }

    //    TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”,
// “How to start” и “Ask a question”
    @Test

    public void testTC_11_4() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Application\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(5000);

        WebElement supprotDropDown = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']"));
        supprotDropDown.click();

        Thread.sleep(1000);
//3.09.46- 3.10.56 youtube
        WebElement checkIfTextFAQIsPresent = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']"));

        String actualResultIfTextFAQIsPresent = checkIfTextFAQIsPresent.getText();
        System.out.println(actualResultIfTextFAQIsPresent);

        Assert.assertEquals(actualResultIfTextFAQIsPresent, expectedResultFAQ);

        WebElement checkIfTextHowToStartIsPresent = driver.findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']//a[@href = '/appid']"));
        String actualResultIfTextHowToStartIsPresent = checkIfTextHowToStartIsPresent.getText();
        System.out.println(actualResultIfTextHowToStartIsPresent);

        Assert.assertEquals(actualResultIfTextHowToStartIsPresent, expectedResultHowToStart);

        WebElement checkIfTextAskAQuestionIsPresent = driver.findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));
        String actualResultIfTextAskAQuestionIsPresent = checkIfTextAskAQuestionIsPresent.getText();
        System.out.println(actualResultIfTextAskAQuestionIsPresent);

        Assert.assertEquals(actualResultIfTextAskAQuestionIsPresent, expectedResultAskAQuestion);

        driver.quit();
    }

    //    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка
// “reCAPTCHA verification failed, please try again.”
    @Test

    public void testTC_11_5() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Application\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String email = "google@gmail.com";
        String message = "Test message";

        driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(5000);

        WebElement supportMenu = driver.findElement(
                By.xpath("//li[@class='with-dropdown']"));
        supportMenu.click();

        WebElement itemAskAQuestion = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='https://home.openweathermap.org/questions']"));
        itemAskAQuestion.click();

        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        WebElement emailField = driver.findElement(
                By.xpath("//input[@class='form-control string email required']"));
        emailField.sendKeys(email);

        WebElement selectField = driver.findElement(
                By.xpath("//select[@class='form-control select required']"));
        selectField.click();

        WebElement selectFieldChoice = driver.findElement(
                By.xpath("//option[@value='Sales']"));
        selectFieldChoice.click();

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@class='form-control text required']"));
        messageField.sendKeys(message);

        WebElement submitButton = driver.findElement(
                By.xpath("//input[@data-disable-with='Create Question form']"));
        submitButton.click();
        Thread.sleep(2000);

        WebElement reCaptchaText = driver.findElement(
                By.xpath("//div[@class='help-block']"));

        String actualResult = reCaptchaText.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
//    TC_11_06   getDriver().get(url);
//Thread.sleep(10000);
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
    @Test

    public void testTC_11_06() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "D:\\Application\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

    }

}

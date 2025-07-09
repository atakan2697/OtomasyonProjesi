package com.vrgsl.step;

import com.thoughtworks.gauge.Step;
import com.virgosol.qa.web.core.di.InjectionHelper;
import com.virgosol.qa.web.core.element.Element;
import com.virgosol.qa.web.core.expected.SlnExpectedConditions;
import com.virgosol.qa.web.core.helper.ConfigurationHelper;
import com.virgosol.qa.web.core.helper.ElementHelper;
import com.virgosol.qa.web.core.helper.LanguageHelper;
import com.virgosol.qa.web.core.helper.StoreHelper;
import com.virgosol.qa.web.core.wait.WaitingAction;
import org.apache.http.util.Asserts;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class StepImpl {

    private static final Logger logger = LoggerFactory.getLogger(StepImpl.class);

    @Inject
    Element element;

    @Inject
    WebDriver driver;

    @Inject
    WaitingAction waitingAction;

    ElementHelper elementHelper;
    StoreHelper storeHelper;

    LanguageHelper languageHelper;

    private String lang;

    public StepImpl() {
        InjectionHelper.getInstance().getFeather().injectFields(this);
        elementHelper = ElementHelper.getInstance();
        storeHelper = StoreHelper.INSTANCE;
        languageHelper = LanguageHelper.INSTANCE;
        lang = ConfigurationHelper.INSTANCE.getConfiguration().getLanguage();
    }

    @Step({"Dil Seçilir",
            "Select Language"})
    public void selectLanguage() {
        element.findByPresenceKey("languageDropdown").hover();
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(elementHelper.getElementInfoToBy("languageOptions")));
        List<WebElement> languageOptions = driver.findElements(elementHelper.getElementInfoToBy("languageOptions"));
        if(lang.equals("en")){
            element.findByEqualsAnyText(languageOptions, "EN").click();
        }else if (lang.equals("tr")){
            element.findByEqualsAnyText(languageOptions, "TR").click();
        }
    }

    public void fail(String message){
        Assert.fail(message);
    }

    @Step({"Sayfayı <pixel> kadar aşağı kaydır", "Scroll Down <pixel>"})
    public void scrollDownByPixel(String pixel) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String script = String.format("window.scrollBy(0, %s);", pixel);
        jsExecutor.executeScript(script);
    }

    @Step("Input required eleman sayısı <count> eşit mi?")
    public void requiredInputCountIsCorrect(String count) {
        List <WebElement> elements = driver.findElements(By.xpath("//input[@required]"));
        Assert.assertEquals(elements.size(), Integer.parseInt(count));

    }

    @Step("<key> li elementin input değerini sil")
    public void deleteInputValue(String key) {
        By byElement = elementHelper.getElementInfoToBy(key);

        WebElement element = driver.findElement(byElement);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", element);

    }


}

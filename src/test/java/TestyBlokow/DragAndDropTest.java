package TestyBlokow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class DragAndDropTest extends BaseSeleniumTest{

    @Test
    public void dragAndDropTest(){
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
        WebElement coockieButtonAccept = driver.findElement(By.id("onetrust-accept-btn-handler"));
        coockieButtonAccept.click();

        WebElement elementToDrag = driver.findElement(By.id("draggable"));
        WebElement dropZone = driver.findElement(By.id("droptarget"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(elementToDrag,dropZone).build().perform();

        System.out.println(dropZone.getText());




    }

}

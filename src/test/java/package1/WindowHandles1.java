package package1;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandles1 {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.get("https://omayo.blogspot.com/");
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(50000));

		driver.findElement(By.id("ta1")).sendKeys("Hello");
//		Thread.sleep(5000);
		String p1 = driver.getWindowHandle();
		driver.findElement(By.linkText("Open a popup window")).click();// first child window
		driver.findElement(By.linkText("Blogger")).click();// second child window

		Set<String> windowIds = driver.getWindowHandles();// set will store elements in random order
		Iterator<String> itr1 = windowIds.iterator();
		while (itr1.hasNext()) {
			String win = itr1.next();
			driver.switchTo().window(win);

			if (driver.getTitle().equals("Basic Web Page Title")) {
				String textOnChildID = driver.findElement(By.id("para1")).getText();
				System.out.println(textOnChildID);
				break;

			}

		}
		Iterator<String> itr2 = windowIds.iterator();
		while (itr2.hasNext()) {
			String win = itr2.next();
			driver.switchTo().window(win);

			if (driver.getTitle().equals("Blogger.com - Create a unique and beautiful blog easily.")) {
				driver.findElement(By.xpath("//span[text()='Sign in']")).click();
				Thread.sleep(1000);

				break;

			}

		}
		driver.switchTo().window(p1);
		Thread.sleep(2000);
		driver.findElement(By.name("q")).sendKeys("priya");
//        Thread.sleep(5000);
		// when we use close command the particular Active window will be close
		// when we use quit command all windows will be close

	}

}

package package1;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandles2 {
	   static WebDriver driver=null;
	   static Set<String> windowIds=null;

	public static void main(String[] args) {
		
		driver=WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.get("https://omayo.blogspot.com/");
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(50000));

		driver.findElement(By.id("ta1")).sendKeys("Hello");
//		Thread.sleep(5000);
		String p1=driver.getWindowHandle();
		driver.findElement(By.linkText("Open a popup window")).click();//first child window
		driver.findElement(By.linkText("Blogger")).click();//second child window
		
		windowIds=driver.getWindowHandles();//set will store elements in random order
		requiredWindow("Basic Web Page Title");
		String textOnChildID=driver.findElement(By.id("para1")).getText();
		System.out.println(textOnChildID);
		
		requiredWindow("Blogger.com - Create a unique and beautiful blog easily.");
		driver.findElement(By.xpath("//span[text()='Sign in']")).click();
        driver.switchTo().window(p1);
        driver.findElement(By.name("q")).sendKeys("priya");
	}
//        Thread.sleep(5000);
        //when we use close command the particular Active window will be close
        //when we use quit command all windows will be close
        
        public static void requiredWindow(String title)
        {
        	Iterator<String> itr=windowIds.iterator();
            while(itr.hasNext())
            {
            	String win=itr.next();
            	driver.switchTo().window(win);
            	
            	if(driver.getTitle().equals(title))
            	{
            		            		break;

            	}
         
            }
        	

	}
}

		// TODO Auto-generated method s


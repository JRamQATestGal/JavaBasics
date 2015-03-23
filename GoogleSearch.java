/*
        Name: Joanna Ramberg, Sr. Software QA engineer

        For illustration purposes, this version only works with FireFox.  I will eventually create a test framework
        for this.
 */

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GoogleSearch {

    WebDriver driver;
    WebDriverWait wait;

    static final String GOOGLE_URL = "https://www.google.com/";
    static final String SEARCH_STR = "12345";
    static final By GOOGLE_SEARCH_QUERY_BOX = By.name("q");                       //Google query box
    static final By GOOGLE_SEARCH_BUTTON = By.name("btnG");                       //Google Search button
    static final String MAP_IMAGE_XPATH = "//img[@alt='Map of " + SEARCH_STR + "']";
    static final By MAP_IMAGE_LINK = By.xpath(MAP_IMAGE_XPATH);


    /*
        All tests are testing the result of the Google search, so setting up the
     */
    @BeforeClass
    public void setUp()
    {
        // Set Selenium Driver
        setupSeleniumDriver();
        doGoogleSearch();

    }

    /*
         Verify the Google Search results page appears
    */

    @Test
    public void testResultPageAppears()
    {
       CharSequence resultsText = "results";
       // Checking for title of page.  All Google search results have this title.
       String searchPageTitle = driver.getTitle();
       Assert.assertEquals(SEARCH_STR+" - Google Search", searchPageTitle);

       String bodyText = driver.findElement(By.tagName("body")).getText();
       Assert.assertTrue(driver.getPageSource().contains(resultsText), "Results Page was not loaded");
    }

    /*
         Verify the Map Image appears
    */
    @Test
    public void testMapImageLinkExists()
    {
        if (isElementPresent(MAP_IMAGE_LINK)== false)
        {
           Assert.fail("Map Image Link does not exists");
        }

    }

    /*
         Verify the Map Image link starts with "https://www.google.com/maps/place"
    */
    @Test
    public void testMapLinkString()
    {
        String mapUrl = "//*[@id=\"rhs_block\"]/ol/div/div[3]/a";
        wait.until(ExpectedConditions.presenceOfElementLocated(MAP_IMAGE_LINK));
        String href = driver.findElement(By.xpath(mapUrl)).getAttribute("href");

        if (href.startsWith("https://www.google.com/maps/place") == false)
            Assert.fail("Map link does not start with maps/place");

    }

    /*
         Verify "12345" is in the Search query text in the Search Result page.
    */
    @Test
    public void testSearchBoxText()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(GOOGLE_SEARCH_QUERY_BOX));
        String GOOGLE_SEARCH_BOX_VALUE = driver.findElement(GOOGLE_SEARCH_QUERY_BOX).getAttribute("value");
        Assert.assertEquals(SEARCH_STR,GOOGLE_SEARCH_BOX_VALUE);
    }

    @AfterClass
    public void cleanUp()
    {
        cleanUpSelenium();
    }

    // Verification
    private boolean isElementPresent(By by) {
        try {
            //wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Action method

    private void setupSeleniumDriver()
    {
        // Create a new instance of the Firefox driver.
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 20);
    }

    private void doGoogleSearch()
    {
        // Go to Google main website
        driver.get(GOOGLE_URL);

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Find the text input element by its name
        WebElement element = driver.findElement(GOOGLE_SEARCH_QUERY_BOX);

        // Enter something to search for
        element.sendKeys(SEARCH_STR);

        // Click Google Search
        element = driver.findElement(GOOGLE_SEARCH_BUTTON);
        element.click();

    }

    public void cleanUpSelenium()
    {
        //Close the browser
        driver.quit();
    }




}

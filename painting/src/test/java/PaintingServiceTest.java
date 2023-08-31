import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.zeroone.Model.Painting;
import net.zeroone.Service.PaintingService;

public class PaintingServiceTest {

    // @Before (every test case -eg set up a db)
    // @After (every test case -eg kill a db)
    // @AfterClass
    // @BeforeClass
    /**
     * When we provide addPaintingAndReturn with a painting, it should respond with the new painting.
     * 
     */
    @Test
    public void addPaintingTest() {
        // Arrange
        PaintingService paintingService = new PaintingService();
        Painting testPainting = new Painting(1, "test author", "test painting", 2023);
        Painting expectedPainting = new Painting(1, "test author", "test painting", 2023);
        
        // Act
        Painting actualPainting = paintingService.savePaintingAndReturn(testPainting);
        
        // Assert
        Assertions.assertEquals(expectedPainting, actualPainting);
    }

    // Mocking
    /**
     * Mocking allows us to make fake DAOs, fake services, fake controllers, so that the other layers may assume that they act correctly,
     * so that we can build the functionality in a isolated environment
     * 
     */

}

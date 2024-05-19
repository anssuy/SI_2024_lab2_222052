import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    @Test
    public void EveryBranchTest() {
        RuntimeException e;

        // Test case 1
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertEquals(e.getMessage(), "allItems list can't be null!");

        // Test case 2
        List<Item> allItems1 = new ArrayList<>();
        allItems1.add(new Item(null, null, 100, 0.2F));

        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems1, 40));
        assertEquals(e.getMessage(), "No barcode!");

        // Test case 3
        List<Item> allItems2 = new ArrayList<>();
        allItems2.add(new Item("item", "!", 300, 0.3F));

        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems2, 100));
        assertEquals(e.getMessage(), "Invalid character in item barcode!");

        // Test case 4
        List<Item> allItems3 = new ArrayList<>();
        allItems3.add(new Item("item", "0", 350, 0.5F));

        assertTrue(SILab2.checkCart(allItems3, 160));

        // TestCase5
        List<Item> allItems4 = new ArrayList<>();
        allItems4.add(new Item("item", "2", 150, 0));

        assertFalse(SILab2.checkCart(allItems4, 140));
    }

    @Test
    public void MultipleConditionTest() {

        // Test case 1
        List<Item> allItems1 = new ArrayList<>();
        allItems1.add(new Item("item", "0", 380, 0.5F));

        assertTrue(SILab2.checkCart(allItems1, 160));

        // Test case 2
        List<Item> allItems2 = new ArrayList<>();
        allItems2.add(new Item("item", "1", 500, 0.2F));

        assertFalse(SILab2.checkCart(allItems2, 80));

        // Test case 3
        List<Item> allItems3 = new ArrayList<>();
        allItems3.add(new Item("item", "0", 400, 0));

        assertFalse(SILab2.checkCart(allItems3, 370));

        // Test case 4
        List<Item> allItems4 = new ArrayList<>();
        allItems4.add(new Item("item", "0", 250, 0.5F));

        assertFalse(SILab2.checkCart(allItems4, 95));
    }
}
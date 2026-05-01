package es.unican.is2.DataTypes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for ConjuntoOrdenado.
 */
public class ConjuntoOrdenadoTest 
{
    private ConjuntoOrdenado<Integer> conjuntoOrdenado;

	@BeforeEach
	public void setUp() {
        conjuntoOrdenado = new ConjuntoOrdenado<>();
        int[] nums = {1, 3, 5, 7, 9};
        for (int i = 0; i < nums.length; i++)
            conjuntoOrdenado.add(nums[i]);
    }

    /**
     * Compares the contents of set and nums.
     * @return True if they are the same, False otherwise.
     */
    private boolean compareValues(ConjuntoOrdenado<Integer> set, Integer[] nums) {
        if (set == null || nums == null || set.size() != nums.length) 
            return false;

        for (int i = 0; i < nums.length; i++)
            if (set.get(i) != nums[i]) return false;

        return true;
    }

    //// Tests de caja negra

    @Test
    public void testGet()
    {   
        assertDoesNotThrow(() -> { 
            ConjuntoOrdenado<Integer> set = new ConjuntoOrdenado<>();
            set.add(1);
            assertEquals(1, set.get(0));

            assertEquals(5, conjuntoOrdenado.get(conjuntoOrdenado.size() / 2));

            assertEquals(9, conjuntoOrdenado.get(conjuntoOrdenado.size() - 1));
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.get(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.get(-6538);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.get(Integer.MIN_VALUE);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.get(conjuntoOrdenado.size());
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.get(conjuntoOrdenado.size() * conjuntoOrdenado.size());
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.get(Integer.MAX_VALUE);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            ConjuntoOrdenado set = new ConjuntoOrdenado();
            set.get(0);
        });
    }

    @Test
    public void testAdd()
    {   
        assertDoesNotThrow(() -> { 
            ConjuntoOrdenado<Integer> set = new ConjuntoOrdenado<>();
            int[] nums = {3, 5, 7, 9};
            for (int i = 0; i < nums.length; i++) {
                set.add(nums[i]);
            }
            assertTrue(set.add(1));
            assertTrue(compareValues(set, new Integer[]{1, 3, 5, 7, 9}));

            set = new ConjuntoOrdenado<>();
            assertTrue(set.add(1));
            assertTrue(compareValues(set, new Integer[]{1}));

            assertTrue(set.add(3));
            assertTrue(compareValues(set, new Integer[]{1, 3}));

            assertFalse(conjuntoOrdenado.add(1));
            assertTrue(compareValues(conjuntoOrdenado, new Integer[]{1, 3, 5, 7, 9}));
        });

        assertThrows(NullPointerException.class, () -> {
            conjuntoOrdenado.add(null);
        });
    }

    @Test
    public void testRemove()
    {   
        assertDoesNotThrow(() -> { 
            ConjuntoOrdenado<Integer> set = new ConjuntoOrdenado<>();
            set.add(1);
            assertEquals(1, set.remove(0));
            assertTrue(compareValues(set, new Integer[]{}));


            set = new ConjuntoOrdenado<>();
            int[] nums = {1, 3, 5, 7, 9};
            for (int i = 0; i < nums.length; i++)
                set.add(nums[i]);

            assertEquals(5, set.remove(set.size() / 2));
            assertTrue(compareValues(set, new Integer[]{1, 3, 7, 9}));


            set = new ConjuntoOrdenado<>();
            for (int i = 0; i < nums.length; i++)
                set.add(nums[i]);

            assertEquals(9, set.remove(set.size() - 1));
            assertTrue(compareValues(set, new Integer[]{1, 3, 5, 7}));
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.remove(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.remove(-6538);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.remove(Integer.MIN_VALUE);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.remove(conjuntoOrdenado.size());
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.remove(conjuntoOrdenado.size() * conjuntoOrdenado.size());
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoOrdenado.remove(Integer.MAX_VALUE);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            ConjuntoOrdenado<Integer> set = new ConjuntoOrdenado();
            set.remove(0);
        });
    }

    @Test
    public void testSize()
    {   
        assertDoesNotThrow(() -> { 
            ConjuntoOrdenado<Integer> set = new ConjuntoOrdenado<>();
            assertEquals(0, set.size());

            
            set.add(1);
            assertEquals(1, set.size());


            assertEquals(5, conjuntoOrdenado.size());
        });
    }

    @Test
    public void testClear()
    {   
        assertDoesNotThrow(() -> { 
            ConjuntoOrdenado<Integer> set = new ConjuntoOrdenado<>();
            set.clear();
            assertTrue(compareValues(set, new Integer[]{}));

            
            set.add(1);
            set.clear();
            assertTrue(compareValues(set, new Integer[]{}));


            set = new ConjuntoOrdenado<>();
            int[] nums = {1, 3, 5, 7, 9};
            for (int i = 0; i < nums.length; i++)
                set.add(nums[i]);
            
            set.clear();
            assertTrue(compareValues(set, new Integer[]{}));
        });
    }

    //// Tests de caja blanca
 
    @Test
    public void testCoberturaGet() {
        assertDoesNotThrow(() -> {
            assertEquals(1, conjuntoOrdenado.get(0));
        });
    }

    @Test
    public void testCoberturaAdd() {
        assertDoesNotThrow(() -> {
            ConjuntoOrdenado<Integer> set = new ConjuntoOrdenado<>();
            assertTrue(set.add(1));
            assertTrue(compareValues(set, new Integer[]{1}));
            
            set = new ConjuntoOrdenado<>();
            int[] nums = {1, 3, 5, 7, 9};
            for (int i = 0; i < nums.length; i++)
                set.add(nums[i]);

            assertTrue(set.add(2));
            assertTrue(compareValues(set, new Integer[]{1, 2, 3, 5, 7, 9}));

            assertFalse(conjuntoOrdenado.add(3));
            assertTrue(compareValues(conjuntoOrdenado, new Integer[]{1, 3, 5, 7, 9}));
        });

        assertThrows(NullPointerException.class, () -> {
            conjuntoOrdenado.add(null);
        });
    }

    @Test
    public void testCoberturaRemove() {
        assertDoesNotThrow(() -> {
            ConjuntoOrdenado<Integer> set = new ConjuntoOrdenado<>();
            int[] nums = {1, 3, 5, 7, 9};
            for (int i = 0; i < nums.length; i++)
                set.add(nums[i]);

            assertEquals(1, set.remove(0));
            assertTrue(compareValues(set, new Integer[]{3, 5, 7, 9}));
        });
    }

    @Test
    public void testCoberturaSize() {
        assertDoesNotThrow(() -> {
            assertEquals(5, conjuntoOrdenado.size());
        });
    }

    @Test
    public void testCoberturaClear() {
        assertDoesNotThrow(() -> {
            ConjuntoOrdenado<Integer> set = new ConjuntoOrdenado<>();
            int[] nums = {1, 3, 5, 7, 9};
            for (int i = 0; i < nums.length; i++)
                set.add(nums[i]);

            set.clear();
            assertTrue(compareValues(set, new Integer[]{}));
        });
    }
}


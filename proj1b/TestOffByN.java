import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testoffbyn() {
        OffByN obo = new OffByN(2);
        assertTrue(obo.equalChars('a', 'c'));
        assertTrue(obo.equalChars('r', 'p'));
        assertFalse(obo.equalChars('a', 'z'));
    }
}

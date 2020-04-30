import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

public class AuthorNameNormalizerTest {
    private AuthorNameNormalizer normalizer = new AuthorNameNormalizer();

    @Test
    public void returnsEmptyStringWhenEmpty() {
        String actual = normalizer.normalize("");
        assertEquals("", actual);
    }

    @Test
    @Ignore
    public void returnsSingleWordName() {
        String actual = normalizer.normalize("Plato");
        assertEquals("Plato", actual);
    }

    @Ignore
    @Test
    public void swapsFirstAndLastNames() {
        String actual = normalizer.normalize("Haruki Murakami");
        assertEquals("Murakami, Haruki", actual);
    }

    @Ignore
    @Test
    public void trimsLeadingAndTrailingWhitespace() {
        String actual = normalizer.normalize("  Big Boi   ");
        assertEquals("Boi, Big", actual);
    }

    @Ignore
    @Test
    public void initializesMiddleName() {
        String actual = normalizer.normalize("Henry David Thoreau");
        assertEquals("Thoreau, Henry D.", actual);
    }

    @Ignore
    @Test
    public void doesNotInitializeOneLetterMiddleName() {
        String actual = normalizer.normalize("Harry S Truman");
        assertEquals("Truman, Harry S", actual);
    }

    @Ignore
    @Test
    public void initializesEachOfMultipleMiddleNames() {
        String actual = normalizer.normalize("Julia Scarlett Elizabeth Louis-Dreyfus");
        assertEquals("Louis-Dreyfus, Julia S. E.", actual);
    }

    @Ignore
    @Test
    public void appendsSuffixesToEnd() {
        String actual = normalizer.normalize("Martin Luther King, Jr.");
        assertEquals("King, Martin L., Jr.", actual);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenNameContainsTwoCommas() {
        normalizer.normalize("Thurston, Howell, III");
    }
}
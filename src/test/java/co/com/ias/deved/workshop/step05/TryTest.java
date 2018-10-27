package co.com.ias.deved.workshop.step05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TryTest {

    @Test
    @DisplayName("Test filter of try")
    public void testFilterTry() {
        Try<Integer> aTry = Try.of(() -> 15)
                .filter(integer -> integer % 3 == 0);
        Assertions.assertTrue(aTry.isSuccess());
    }
}

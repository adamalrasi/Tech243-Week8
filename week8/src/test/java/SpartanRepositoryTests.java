import org.example.aa.mocking.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.inOrder;

public class SpartanRepositoryTests{
SpartanRepository sut = new SpartanRepository();
Spartan mock1 = Mockito.mock(Spartan.class);
Spartan mock2 = Mockito.mock(Spartan.class);

@BeforeEach
void setUp() {
    sut.addSpartan(mock1);
    sut.addSpartan(mock2);
}

    @Test
    @DisplayName("Testing AddSpartan")
    public void testAddSpartan() {
        Assertions.assertEquals(2, sut.getNumSpartans());
    }

    @Test
    @DisplayName("Testing findSpartan")
    public void findSpartanTest() {
        // Arrange
        Mockito.when(mock1.getId()).thenReturn(345);
        Mockito.when(mock1.toString()).thenReturn("Found 345");
        Mockito.when(mock2.toString()).thenReturn("wrong spartan");
        // Act
        var result = sut.findSpartan(345);
        // Assert
        Assertions.assertEquals("Found 345", result.toString());
    }

    @Test
    @DisplayName("Testing getSpartansCreatedLast24Hours")
    public void testingLast24Hours() {
        Mockito.when(mock1.getStartDate())
                .thenReturn(LocalDate.now().minusDays(3)
                );

        Mockito.when(mock2.getStartDate()).thenReturn(LocalDate.now());

        var newSpartans = sut.getSpartansCreatedLast24Hours();
        Assertions.assertEquals(1,newSpartans.size());
    }

    @Test
    @DisplayName("Testing getSpartansCreatedLast24Hours with different return values")
    public void testingLast24HoursB() {
        Mockito.when(mock1.getStartDate())
                .thenReturn(LocalDate.now().minusDays(3))
                .thenReturn(LocalDate.now());

        Mockito.when(mock2.getStartDate()).thenReturn(LocalDate.now());
        // the first invocation of getStartDate()
        var newSpartans = sut.getSpartansCreatedLast24Hours();
        Assertions.assertEquals(1,newSpartans.size());
        // the second invocation of getStartDate()
        var newSpartans2 = sut.getSpartansCreatedLast24Hours();
        Assertions.assertEquals(2,newSpartans2.size());
    }

    @Test
    @DisplayName("Check getId is called once per Spartan")
    public void checkGetIdCalledOnce() {
        sut.findSpartan(20);
        Mockito.verify(mock1, Mockito.times(1)).getId();
        Mockito.verify(mock2, Mockito.times(1)).getId();
    }

    @Test
    @DisplayName("Check methods are called in order")
    public void checkOrder() {
        sut.getAllSpartans();

        InOrder inOrder = inOrder(mock2);
        inOrder.verify(mock2).getId();
        inOrder.verify(mock2).getName();
    }

    @Test
    @DisplayName("test correct parameter")
    public void testMethodIsCalledWithCorrectParameter() {
        Mockito.when(mock1.getId()).thenReturn(3);
        sut.changeName(3, "Cathy");
        Mockito.verify(mock1).setName("Cathy");
    }
}
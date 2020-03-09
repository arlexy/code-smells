package nerdschool.bar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class PubPricesTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Pub pub;

    @Before
    public void setUp() throws Exception {
        pub = new Pub();
    }

    @Test
    public void OneBeer() {
        int actualPrice = pub.computeCost(Pub.ONE_BEER, false, 1);
        assertEquals(74, actualPrice);
    }

    @Test
    public void StudentsGetADiscountForBeer() throws Exception {
        int actualPrice = pub.computeCost(Pub.ONE_BEER, true, 1);
        assertEquals(67, actualPrice);
    }

    @Test
    public void CidersAreCostly() throws Exception {
        int actualPrice = pub.computeCost(Pub.ONE_CIDER, false, 1);
        assertEquals(103, actualPrice);
    }

    @Test
    public void ProperCidersAreEvenMoreExpensive() throws Exception {
        int actualPrice = pub.computeCost(Pub.A_PROPER_CIDER, false, 1);
        assertEquals(110, actualPrice);
    }

    @Test
    public void ACocktail() throws Exception {
        int actualPrice = pub.computeCost(Pub.GT, false, 1);
        assertEquals(115, actualPrice);
    }

    @Test
    public void testThatADrinkNotInTheSortimentGivesError() throws Exception {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("No such drink");
        pub.computeCost("sanfranciscosling", false, 1);
    }

    @Test
    public void StudentsDoNotGetDiscountsForCocktails() throws Exception {
        int actualPrice = pub.computeCost(Pub.GT, true, 1);
        assertEquals(115, actualPrice);
    }

    @Test
    public void BacardiSpecial() throws Exception {
        int actualPrice = pub.computeCost(Pub.BACARDI_SPECIAL, false, 1);
        assertEquals(127, actualPrice);
    }

    @Test
    public void CanBuyAtMostTwoDrinksInOneGo() throws Exception {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Too many");
        pub.computeCost(Pub.BACARDI_SPECIAL, false, 3);
    }

    @Test
    public void StudentsGetDiscountsWhenOrderingMoreThanOneBeer() throws Exception {
        int actualPrice = pub.computeCost(Pub.ONE_BEER, true, 2);
        assertEquals(67*2, actualPrice);
    }

    @Test
    public void CanOrderMoreThanTwoBeers() throws Exception {
        pub.computeCost(Pub.ONE_BEER, false, 5);
    }
}

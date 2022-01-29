package Test;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;
import model.*;

public class TestTest {

	@Test
	public void test() {
		Salle s1 = new Salle(995, 600, 20);
		double d = Reservation.CalculPrixLocation(s1,LocalTime.of(8, 30), LocalTime.of(12,30));
	    assertEquals(1300,d,0);
		
	}

}
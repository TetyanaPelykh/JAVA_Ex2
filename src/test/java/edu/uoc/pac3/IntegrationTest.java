package edu.uoc.pac3;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class IntegrationTest {
	Team team1, team2;
	Footballer footballer1, footballer2;
	
	@BeforeAll
	void init(){
		try {
			team1 = new Team();
			team2 = new Team("Real Madrid", "1902", "G28034718", "atencionpublico@corp.realmadrid.com", 25);
			footballer1 = new Footballer();
			footballer2 = new Footballer("Messi", 30);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Init failed");
		}
	}

	@Test
	@Order(1)
	void testIntegration1() {
		try {
			team1.addFootballer(footballer1);
			assertEquals(0, team1.getGapByFootballer(footballer1));
			assertEquals(20, team1.getNumFreeGaps());
			assertEquals(1, team1.getFirstFreeGap());
			assertEquals(footballer1, team1.getFootballers()[team1.getGapByFootballer(footballer1)]);
			assertEquals(team1, footballer1.getTeam());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration1 failed");
		}
	}
	
	@Test
	@Order(2)
	void testIntegration2()  {		
		try{
			footballer1.setTeam(team1);
			Exception ex = assertThrows( Exception.class,() -> team1.addFootballer(footballer1));
			assertEquals("[ERROR] This footballer is already in this team", ex.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration2 failed");
		}		
	}
	
	
	@Test
	@Order(3)
	void testIntegration3() {
		try {
			footballer1.setTeam(null);
			assertTrue(team1.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration3 failed");
		}		
	}
	
	@Test
	@Order(4)
	void testIntegration4() {	
		try {
			footballer1.setTeam(team1);
			assertEquals(0, team1.getGapByFootballer(footballer1));
			assertEquals(20, team1.getNumFreeGaps());
			assertEquals(1, team1.getFirstFreeGap());
			assertEquals(footballer1, team1.getFootballers()[team1.getGapByFootballer(footballer1)]);
			assertEquals(team1, footballer1.getTeam());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration4 failed");
		}		
	}
	
	
	@Test
	@Order(5)
	void testIntegration5() {		
		try {
			team1.removeFootballer(footballer1);
			assertTrue(team1.isEmpty());
			assertNull(footballer1.getTeam());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration5 failed");
		}		
	}
	
	
	
	@Test
	@Order(6)
	void testIntegration6() {		
		try {
			team1.addFootballer(footballer2);
			assertEquals(-1, team1.getGapByFootballer(footballer1));
			assertEquals(0, team1.getGapByFootballer(footballer2));
			assertEquals(20, team1.getNumFreeGaps());
			assertEquals(1, team1.getFirstFreeGap());
			assertEquals(footballer2, team1.getFootballers()[team1.getGapByFootballer(footballer2)]);
			assertEquals(team1, footballer2.getTeam());
			assertNull(footballer1.getTeam());
			assertEquals(25, team2.getNumFreeGaps());
			
			Exception ex = assertThrows( Exception.class,() -> team1.addFootballer(null));
			assertEquals("[ERROR] The footballer cannot be null", ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration6 failed");
		}		
	}
	
	
	
	@Test
	@Order(7)
	void testIntegration7() {
		try{
			footballer1.setTeam(team2);
			footballer2.setTeam(team2);
			assertEquals(0, team2.getGapByFootballer(footballer1));
			assertEquals(1, team2.getGapByFootballer(footballer2));
			assertEquals(21, team1.getNumFreeGaps());
			assertEquals(23, team2.getNumFreeGaps());
			assertEquals(0, team1.getFirstFreeGap());
			assertTrue(team1.isEmpty());
			assertFalse(team2.isEmpty());
			assertEquals(2, team2.getFirstFreeGap());
			assertEquals(footballer1, team2.getFootballers()[team2.getGapByFootballer(footballer1)]);
			assertEquals(footballer2, team2.getFootballers()[team2.getGapByFootballer(footballer2)]);
			assertEquals(team2, footballer1.getTeam());
			assertEquals(team2, footballer2.getTeam());
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration7 failed");
		}	
	}
	
	
	@Test
	@Order(8)
	void testIntegration8() {
		try{
			team2.removeFootballer(footballer2);
			team2.removeFootballer(footballer1);
			assertNull(footballer1.getTeam());
			assertNull(footballer2.getTeam());
			assertTrue(team1.isEmpty());
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration8 failed");
		}	
	}
	
	@Test
	@Order(9)
	void testIntegration9() {
		try{
			for(int i = 0; i < team2.getCapacity()-1; i++) {
				team2.addFootballer(new Footballer());
			}
			assertFalse(team2.isEmpty());
			assertFalse(team2.isFull());
			team2.addFootballer(new Footballer());
			assertFalse(team2.isEmpty());
			assertTrue(team2.isFull());
			Exception ex = assertThrows( Exception.class,() -> team2.addFootballer(new Footballer()));
			assertEquals("[ERROR] This team is full", ex.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration9 failed");
		}	
	}

	@Test
	@Order(10)
	void testGetInfo() {
		try {
			Team team = new Team("PSG", "1970", "A12345678", "info@psg.com", 19);
			Footballer f1 = new Footballer("Messi", 30);
			Footballer f2 = new Footballer("Ramos", 4);
			team.addFootballer(f1);
			team.addFootballer(f2);
			assertEquals("Name: PSG - Foundation year: 1970 - NIF: A12345678 - Footballers: 30-Messi,4-Ramos", team.getInfo());
		} catch (Exception e) {
			fail("testToString failed");
		}


	}
	
}

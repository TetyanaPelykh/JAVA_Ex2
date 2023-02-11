package edu.uoc.pac3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class PrivateTest {
	Team team1, team2;
	Footballer footballer1, footballer2, footballer3;
	
	@BeforeAll
	void init(){
		try {
			team1 = new Team("Team 1", "1899", "G08266298", "sac@fcbarcelona.com", 25);
			team2 = new Team("Team 2", "1902", "G28034718", "atencionpublico@corp.realmadrid.com", 21);
			footballer1 = new Footballer("Footballer 1", 7);
			footballer2 = new Footballer("Footballer 2", 21);
			footballer3 = new Footballer("Footballer 3", 23);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Init failed");
		}
	}

	@Test
	@Order(1)
	//Add Footballer1 and Footballer2 to Team1 (Team1: Footballer1, Footballer2)
	void testIntegration1() {
		try {
			
			team1.addFootballer(footballer1);
			team1.addFootballer(footballer2);
			assertEquals(23, team1.getNumFreeGaps());
			assertEquals(21, team2.getNumFreeGaps());
			assertEquals("Team 1", footballer1.getTeam().getName());
			assertEquals("Team 1", footballer2.getTeam().getName());	
			assertEquals(footballer1, team1.getFootballers()[0]);
			assertEquals(footballer2, team1.getFootballers()[1]);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration1 failed");
		}
	}
	
	
	@Test
	@Order(2)
	//Change Footballer2 to Team2 (Team1: Footballer1 // Team2: Footballer2)
	void testIntegration2()  {		
		try{
			team2.addFootballer(footballer2);
			assertEquals(24, team1.getNumFreeGaps());
			assertEquals(20, team2.getNumFreeGaps());
			assertEquals("Team 1", footballer1.getTeam().getName());
			assertEquals("Team 2", footballer2.getTeam().getName());		
			assertEquals(footballer1, team1.getFootballers()[0]);
			assertEquals(footballer2, team2.getFootballers()[0]);
						
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration2 failed");
		}		
	}
	
	
	@Test
	@Order(3)
	//Add Footballer3 to Team1(Team1: Footballer1, Footballer3 // Team2: Footballer2)
	void testIntegration3() {
		try {
			team1.addFootballer(footballer3);
			assertEquals(23, team1.getNumFreeGaps());
			assertEquals(20, team2.getNumFreeGaps());
			assertEquals("Team 1", footballer1.getTeam().getName());
			assertEquals("Team 2", footballer2.getTeam().getName());
			assertEquals("Team 1", footballer3.getTeam().getName());			
			assertEquals(footballer1, team1.getFootballers()[0]);
			assertEquals(footballer3, team1.getFootballers()[1]);
			assertEquals(footballer2, team2.getFootballers()[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration3 failed");
		}		
	}
	
	@Test
	@Order(4)
	//THROW EXCEPTION: Add Footballer3 to Team1, but Footballer is already in this Team
	void testIntegration4() {
		try {
			Exception ex = assertThrows( Exception.class,() -> team1.addFootballer(footballer3));
			assertEquals("[ERROR] This footballer is already in this team", ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration4 failed");
		}		
	}
	
	@Test
	@Order(5)
	//Change Footballer3 to Team2(Team1: Footballer1 // Team2: Footballer2, Footballer3)
	void testIntegration5() {
		try {
			team2.addFootballer(footballer3);
			assertEquals(24, team1.getNumFreeGaps());
			assertEquals(19, team2.getNumFreeGaps());
			assertEquals("Team 1", footballer1.getTeam().getName());
			assertEquals("Team 2", footballer2.getTeam().getName());
			assertEquals("Team 2", footballer3.getTeam().getName());
			assertEquals(footballer1, team1.getFootballers()[0]);
			assertEquals(footballer2, team2.getFootballers()[0]);
			assertEquals(footballer3, team2.getFootballers()[1]);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration5 failed");
		}		
	}
	
	@Test
	@Order(6)
	//Remove Team2 from Footballer3(Team1: Footballer1 // Team2: Footballer2)
	void testIntegration6() {
		try {
			footballer3.setTeam(null);
			assertEquals(24, team1.getNumFreeGaps());
			assertEquals(20, team2.getNumFreeGaps());
			assertEquals("Team 1", footballer1.getTeam().getName());
			assertEquals("Team 2", footballer2.getTeam().getName());
			assertNull(footballer3.getTeam());
			assertEquals(footballer1, team1.getFootballers()[0]);
			assertEquals(footballer2, team2.getFootballers()[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration6 failed");
		}		
	}
	
	@Test
	@Order(7)
	//THROW EXCEPTION: remove Footballer3 from Team1, but does not exist.
	void testIntegration7() {
		try {
			Exception ex = assertThrows( Exception.class,() -> team1.removeFootballer(footballer3));
			assertEquals("[ERROR] This footballer does not exist in this team", ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration7 failed");
		}		
	}
	
	@Test
	@Order(8)
	//Remove Footballer1 from Team1 (Team2: Footballer2)
	void testIntegration8() {
		try {
			team1.removeFootballer(footballer1);
			assertEquals(25, team1.getNumFreeGaps());
			assertEquals(20, team2.getNumFreeGaps());
			assertNull(footballer1.getTeam());
			assertEquals("Team 2", footballer2.getTeam().getName());
			assertEquals(footballer2, team2.getFootballers()[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration8 failed");
		}		
	}
	
	@Test
	@Order(9)
	//Remove Team from Footballer2
	void testIntegration9() {
		try {
			footballer2.setTeam(null);
						
			assertEquals(25, team1.getNumFreeGaps());
			assertEquals(21, team2.getNumFreeGaps());
			assertNull(footballer1.getTeam());
			assertNull(footballer2.getTeam());
			assertTrue(team1.isEmpty());
			assertTrue(team2.isEmpty());
						
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration9 failed");
		}		
	}
	
}

package edu.uoc.pac3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.lang.reflect.Field;

@TestInstance(Lifecycle.PER_CLASS)
class TeamTest {

	Team team;
	private final static int CAPACITY = 25;
			
	@BeforeEach
	void init() {
		 try{
			 Field field = Team.class.getDeclaredField("nextId");
			 field.setAccessible(true);
			 field.set(null, 0);
			 team = new Team("Real Madrid", "1902", "G28034718", "atencionpublico@corp.realmadrid.com", CAPACITY);
		 }catch(Exception e) {
			e.printStackTrace();
			fail("Parameterized constructor failed");
		}
	}
	
	@Test
	void testTeamDefault() {
		try{
			Team t = new Team();
			assertEquals(1, t.getId());
			assertEquals("Lorem club", t.getName());
			assertEquals("2000", t.getFoundationYear());
			assertEquals("G12345678", t.getNif());
			assertEquals("info@yourmail.com", t.getEmail());
			assertEquals(21, t.getCapacity());

			assertNotNull(t.getStatisticsTeam());
			
		}catch(Exception e) {
			fail("Default constructor failed");
		}		
	}


	@Test
	void testGetId() {
		assertEquals(0, team.getId());
	}

	@Test
	void testGetNextId() {
		assertEquals(1, Team.getNextId());
	}

	@Test
	void testGetName() {
		assertEquals("Real Madrid", team.getName());
	}

	@Test
	void testSetName() {
		try{
			team.setName("FCB");
			assertEquals("FCB", team.getName());
		}catch(Exception e) {
			fail("setName failed");
		}
		Exception ex = assertThrows(Exception.class, () ->	team.setName("Lorem ipsum dolor sit amet, consectetur vestibulum."));
		assertEquals("[ERROR] Team's name cannot be longer than 50 characters", ex.getMessage());
	}

	@Test
	void testGetStatisticsTeam() {
		assertNotNull(team.getStatisticsTeam());
		assertEquals(0, team.getStatisticsTeam().getWon());
		assertEquals(0, team.getStatisticsTeam().getDrawn());
		assertEquals(0, team.getStatisticsTeam().getLost());
		assertEquals(0, team.getStatisticsTeam().getGoalsFor());
		assertEquals(0, team.getStatisticsTeam().getGoalsAgainst());
	}
	
	@Test
	void testSetStatisticsTeam() {
		try{
			team.getStatisticsTeam().setWon(2);
			team.getStatisticsTeam().setLost(3);
			team.getStatisticsTeam().setDrawn(5);
			team.getStatisticsTeam().incGoalsFor(19);
			team.getStatisticsTeam().incGoalsAgainst(19);
			assertEquals(2, team.getStatisticsTeam().getWon());
			assertEquals(3, team.getStatisticsTeam().getLost());
			assertEquals(5, team.getStatisticsTeam().getDrawn());
			assertEquals(19, team.getStatisticsTeam().getGoalsFor());
			assertEquals(19, team.getStatisticsTeam().getGoalsAgainst());
			assertEquals(10, team.getStatisticsTeam().getGamesPlayed());
			assertEquals(0, team.getStatisticsTeam().getGoalsDifference());
			assertEquals(11, team.getStatisticsTeam().getPoints());

		}catch(Exception e) {
			fail("setStadium failed");
		}
	}


	@Test
	void testGetCapacity() {
		assertEquals(CAPACITY, team.getCapacity());
	}

	@Test
	void testSetCapacity() {
		try{
			team.setCapacity(15);
			assertEquals(15, team.getCapacity());
		}catch(Exception e) {
			fail("setCapacity failed");
		}
		
		Exception ex = assertThrows(Exception.class, () -> team.setCapacity(0));
		assertEquals("[ERROR] Team's capacity must be greater than 0", ex.getMessage());
			
		
		ex = assertThrows(Exception.class, () -> team.setCapacity(-10));
		assertEquals("[ERROR] Team's capacity must be greater than 0", ex.getMessage());
	}

	
	/******************
	 *   RELATIONS 
	 *****************/
	@Test
	void testGetFootballers() {
		assertArrayEquals(new Footballer[CAPACITY], team.getFootballers());
	}

	@Test
	void testGetFirstFreeGap() {
		assertEquals(0, team.getFirstFreeGap());
	}

	@Test
	void testIsFull() {
		assertFalse(team.isFull());
	}

	@Test
	void testIsEmpty() {
		assertTrue(team.isEmpty());
	}

	@Test
	void testGetNumFreeGaps() {
		assertEquals(CAPACITY, team.getNumFreeGaps());
	}

	@Test
	void testGetGapByFootballer() {
		Footballer footballer;
		try{
			footballer = new Footballer();
			assertEquals(-1, team.getGapByFootballer(footballer));
		}catch(Exception e) {
			fail("testGetGapByFootballer failed");
		}		
	}

}

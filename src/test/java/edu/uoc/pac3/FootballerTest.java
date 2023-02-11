package edu.uoc.pac3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.lang.reflect.Field;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class FootballerTest {

	Footballer footballer;
	
	@BeforeAll
	void testFootballer() {
		try {
			Field field = Footballer.class.getDeclaredField("nextId");
			field.setAccessible(true);
			field.set(null, 0);
			footballer = new Footballer();
		} catch (Exception e) {
			e.printStackTrace();
			fail("testFootballer failed");
		}
	}

	@Test
	@Order(1)
	void testGetId() {
		assertEquals(0, footballer.getId());
	}

	@Test
	@Order(2)
	void testGetNextId() {
		assertEquals(1, Footballer.getNextId());
	}

	@Test
	@Order(3)
	void testGetName() {
		assertEquals("Foo", footballer.getName());
	}

	@Test
	@Order(4)
	void testSetName() {
		footballer.setName("Bar");
		assertEquals("Bar", footballer.getName());
		
	}
	
	@Test
	@Order(5)
	void testGetNumber() {
		assertEquals(10, footballer.getNumber());
	}
	
	@Test
	@Order(6)
	void testSetNumber() {
		Exception ex = assertThrows(Exception.class, () -> footballer.setNumber(0));
		assertEquals("[ERROR] Footballer's number must be in range [1,99]",ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> footballer.setNumber(-10));
		assertEquals("[ERROR] Footballer's number must be in range [1,99]",ex.getMessage());

		ex = assertThrows(Exception.class, () -> footballer.setNumber(100));
		assertEquals("[ERROR] Footballer's number must be in range [1,99]",ex.getMessage());
		
		try{
			footballer.setNumber(99);
			assertEquals(99, footballer.getNumber());
		}catch(Exception e) {
			fail("testNumber failed");
		}		
	}

	@Test
	@Order(9)
	void testGetTeam() {
		Assertions.assertNull(footballer.getTeam());
	}

	@Test
	@Order(10)
	void testGetInfo() {
		assertEquals("99-Bar", footballer.getInfo());
	}
	
}

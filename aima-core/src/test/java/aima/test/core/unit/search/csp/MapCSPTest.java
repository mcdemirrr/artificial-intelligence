package aima.test.core.unit.search.csp;

import aima.core.search.csp.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import aima.core.search.csp.examples.MapCSP;

import java.util.Optional;

/**
 * @author Ravi Mohan
 * @author Ruediger Lunde
 * 
 */
public class MapCSPTest {
	private CSP<Variable, String> csp;

	@Before
	public void setUp() {
		csp = new MapCSP();
	}

	@Test
	public void testBackTrackingSearch() {
		Optional<Assignment<Variable, String>> results = new FlexibleBacktrackingSolver<Variable, String>().solve(csp);
		Assert.assertTrue(results.isPresent());
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.WA));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.NT));
		Assert.assertEquals(MapCSP.BLUE, results.get().getValue(MapCSP.SA));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.Q));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.NSW));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.V));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.T));
	}
	
	@Test
	public void testLCVSearch() {
		Optional<Assignment<Variable, String>> results = new FlexibleBacktrackingSolver<Variable, String>().solve(csp);
		Assert.assertTrue(results.isPresent());
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.WA));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.NT));
		Assert.assertEquals(MapCSP.BLUE, results.get().getValue(MapCSP.SA));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.Q));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.NSW));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.V));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.T));
	}
	
	@Test
	public void testMRVSearch() {
		Optional<Assignment<Variable, String>> results = new FlexibleBacktrackingSolver<Variable, String>().setAll().solve(csp);
		Assert.assertTrue(results.isPresent());
		Assert.assertEquals(MapCSP.BLUE, results.get().getValue(MapCSP.WA));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.NT));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.SA));
		Assert.assertEquals(MapCSP.BLUE, results.get().getValue(MapCSP.Q));
		Assert.assertEquals(MapCSP.GREEN, results.get().getValue(MapCSP.NSW));
		Assert.assertEquals(MapCSP.BLUE, results.get().getValue(MapCSP.V));
		Assert.assertEquals(MapCSP.RED, results.get().getValue(MapCSP.T));
	}

	@Test
	public void testMCSearch() {
		new MinConflictsSolver<Variable, String>(100).solve(csp);
	}
}

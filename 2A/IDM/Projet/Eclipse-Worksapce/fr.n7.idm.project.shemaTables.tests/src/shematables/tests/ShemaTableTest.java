/**
 */
package shematables.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import shematables.ShemaTable;
import shematables.ShematablesFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Shema Table</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ShemaTableTest extends TestCase {

	/**
	 * The fixture for this Shema Table test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShemaTable fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ShemaTableTest.class);
	}

	/**
	 * Constructs a new Shema Table test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShemaTableTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Shema Table test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ShemaTable fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Shema Table test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShemaTable getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ShematablesFactory.eINSTANCE.createShemaTable());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //ShemaTableTest

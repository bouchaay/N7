/**
 */
package shematables.tests;

import junit.textui.TestRunner;

import shematables.DerivedColumn;
import shematables.ShematablesFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Derived Column</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class DerivedColumnTest extends ColumnTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DerivedColumnTest.class);
	}

	/**
	 * Constructs a new Derived Column test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DerivedColumnTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Derived Column test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected DerivedColumn getFixture() {
		return (DerivedColumn)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ShematablesFactory.eINSTANCE.createDerivedColumn());
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

} //DerivedColumnTest

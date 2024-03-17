/**
 */
package shematables.tests;

import junit.textui.TestRunner;

import shematables.ReferencedColumn;
import shematables.ShematablesFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Referenced Column</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReferencedColumnTest extends ColumnTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ReferencedColumnTest.class);
	}

	/**
	 * Constructs a new Referenced Column test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferencedColumnTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Referenced Column test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ReferencedColumn getFixture() {
		return (ReferencedColumn)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ShematablesFactory.eINSTANCE.createReferencedColumn());
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

} //ReferencedColumnTest

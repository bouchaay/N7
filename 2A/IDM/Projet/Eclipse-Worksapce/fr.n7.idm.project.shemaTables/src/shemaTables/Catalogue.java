/**
 */
package shematables;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Catalogue</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link shematables.Catalogue#getName <em>Name</em>}</li>
 *   <li>{@link shematables.Catalogue#getAlgorithms <em>Algorithms</em>}</li>
 * </ul>
 *
 * @see shematables.ShematablesPackage#getCatalogue()
 * @model
 * @generated
 */
public interface Catalogue extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see shematables.ShematablesPackage#getCatalogue_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link shematables.Catalogue#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Algorithms</b></em>' containment reference list.
	 * The list contents are of type {@link shematables.Algorithm}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Algorithms</em>' containment reference list.
	 * @see shematables.ShematablesPackage#getCatalogue_Algorithms()
	 * @model containment="true"
	 * @generated
	 */
	EList<Algorithm> getAlgorithms();

} // Catalogue

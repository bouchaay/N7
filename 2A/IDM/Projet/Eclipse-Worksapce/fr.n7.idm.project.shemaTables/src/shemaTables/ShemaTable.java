/**
 */
package shematables;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shema Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link shematables.ShemaTable#getName <em>Name</em>}</li>
 *   <li>{@link shematables.ShemaTable#getId <em>Id</em>}</li>
 *   <li>{@link shematables.ShemaTable#getColumns <em>Columns</em>}</li>
 *   <li>{@link shematables.ShemaTable#getCatalogue <em>Catalogue</em>}</li>
 * </ul>
 *
 * @see shematables.ShematablesPackage#getShemaTable()
 * @model
 * @generated
 */
public interface ShemaTable extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see shematables.ShematablesPackage#getShemaTable_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link shematables.ShemaTable#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see shematables.ShematablesPackage#getShemaTable_Id()
	 * @model
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link shematables.ShemaTable#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

	/**
	 * Returns the value of the '<em><b>Columns</b></em>' containment reference list.
	 * The list contents are of type {@link shematables.Column}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Columns</em>' containment reference list.
	 * @see shematables.ShematablesPackage#getShemaTable_Columns()
	 * @model containment="true"
	 * @generated
	 */
	EList<Column> getColumns();

	/**
	 * Returns the value of the '<em><b>Catalogue</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Catalogue</em>' containment reference.
	 * @see #setCatalogue(Catalogue)
	 * @see shematables.ShematablesPackage#getShemaTable_Catalogue()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Catalogue getCatalogue();

	/**
	 * Sets the value of the '{@link shematables.ShemaTable#getCatalogue <em>Catalogue</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Catalogue</em>' containment reference.
	 * @see #getCatalogue()
	 * @generated
	 */
	void setCatalogue(Catalogue value);

} // ShemaTable

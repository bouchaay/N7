/**
 */
package shematables;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Referenced Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link shematables.ReferencedColumn#getForeignTable <em>Foreign Table</em>}</li>
 *   <li>{@link shematables.ReferencedColumn#getForeignColumn <em>Foreign Column</em>}</li>
 * </ul>
 *
 * @see shematables.ShematablesPackage#getReferencedColumn()
 * @model
 * @generated
 */
public interface ReferencedColumn extends Column {
	/**
	 * Returns the value of the '<em><b>Foreign Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreign Table</em>' reference.
	 * @see #setForeignTable(ShemaTable)
	 * @see shematables.ShematablesPackage#getReferencedColumn_ForeignTable()
	 * @model required="true"
	 * @generated
	 */
	ShemaTable getForeignTable();

	/**
	 * Sets the value of the '{@link shematables.ReferencedColumn#getForeignTable <em>Foreign Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Foreign Table</em>' reference.
	 * @see #getForeignTable()
	 * @generated
	 */
	void setForeignTable(ShemaTable value);

	/**
	 * Returns the value of the '<em><b>Foreign Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Foreign Column</em>' reference.
	 * @see #setForeignColumn(Column)
	 * @see shematables.ShematablesPackage#getReferencedColumn_ForeignColumn()
	 * @model required="true"
	 * @generated
	 */
	Column getForeignColumn();

	/**
	 * Sets the value of the '{@link shematables.ReferencedColumn#getForeignColumn <em>Foreign Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Foreign Column</em>' reference.
	 * @see #getForeignColumn()
	 * @generated
	 */
	void setForeignColumn(Column value);

} // ReferencedColumn

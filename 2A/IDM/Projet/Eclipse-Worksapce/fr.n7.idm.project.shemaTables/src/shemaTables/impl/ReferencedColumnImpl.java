/**
 */
package shematables.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import shematables.Column;
import shematables.ReferencedColumn;
import shematables.ShemaTable;
import shematables.ShematablesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Referenced Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link shematables.impl.ReferencedColumnImpl#getForeignTable <em>Foreign Table</em>}</li>
 *   <li>{@link shematables.impl.ReferencedColumnImpl#getForeignColumn <em>Foreign Column</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferencedColumnImpl extends ColumnImpl implements ReferencedColumn {
	/**
	 * The cached value of the '{@link #getForeignTable() <em>Foreign Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeignTable()
	 * @generated
	 * @ordered
	 */
	protected ShemaTable foreignTable;

	/**
	 * The cached value of the '{@link #getForeignColumn() <em>Foreign Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeignColumn()
	 * @generated
	 * @ordered
	 */
	protected Column foreignColumn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferencedColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShematablesPackage.Literals.REFERENCED_COLUMN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ShemaTable getForeignTable() {
		if (foreignTable != null && foreignTable.eIsProxy()) {
			InternalEObject oldForeignTable = (InternalEObject)foreignTable;
			foreignTable = (ShemaTable)eResolveProxy(oldForeignTable);
			if (foreignTable != oldForeignTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ShematablesPackage.REFERENCED_COLUMN__FOREIGN_TABLE, oldForeignTable, foreignTable));
			}
		}
		return foreignTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShemaTable basicGetForeignTable() {
		return foreignTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setForeignTable(ShemaTable newForeignTable) {
		ShemaTable oldForeignTable = foreignTable;
		foreignTable = newForeignTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShematablesPackage.REFERENCED_COLUMN__FOREIGN_TABLE, oldForeignTable, foreignTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Column getForeignColumn() {
		if (foreignColumn != null && foreignColumn.eIsProxy()) {
			InternalEObject oldForeignColumn = (InternalEObject)foreignColumn;
			foreignColumn = (Column)eResolveProxy(oldForeignColumn);
			if (foreignColumn != oldForeignColumn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ShematablesPackage.REFERENCED_COLUMN__FOREIGN_COLUMN, oldForeignColumn, foreignColumn));
			}
		}
		return foreignColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column basicGetForeignColumn() {
		return foreignColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setForeignColumn(Column newForeignColumn) {
		Column oldForeignColumn = foreignColumn;
		foreignColumn = newForeignColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShematablesPackage.REFERENCED_COLUMN__FOREIGN_COLUMN, oldForeignColumn, foreignColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ShematablesPackage.REFERENCED_COLUMN__FOREIGN_TABLE:
				if (resolve) return getForeignTable();
				return basicGetForeignTable();
			case ShematablesPackage.REFERENCED_COLUMN__FOREIGN_COLUMN:
				if (resolve) return getForeignColumn();
				return basicGetForeignColumn();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ShematablesPackage.REFERENCED_COLUMN__FOREIGN_TABLE:
				setForeignTable((ShemaTable)newValue);
				return;
			case ShematablesPackage.REFERENCED_COLUMN__FOREIGN_COLUMN:
				setForeignColumn((Column)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ShematablesPackage.REFERENCED_COLUMN__FOREIGN_TABLE:
				setForeignTable((ShemaTable)null);
				return;
			case ShematablesPackage.REFERENCED_COLUMN__FOREIGN_COLUMN:
				setForeignColumn((Column)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ShematablesPackage.REFERENCED_COLUMN__FOREIGN_TABLE:
				return foreignTable != null;
			case ShematablesPackage.REFERENCED_COLUMN__FOREIGN_COLUMN:
				return foreignColumn != null;
		}
		return super.eIsSet(featureID);
	}

} //ReferencedColumnImpl

/**
 */
package shematables.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import shematables.ShemaTable;
import shematables.ShematablesPackage;
import shematables.Tables;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tables</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link shematables.impl.TablesImpl#getShematables <em>Shematables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TablesImpl extends MinimalEObjectImpl.Container implements Tables {
	/**
	 * The cached value of the '{@link #getShematables() <em>Shematables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShematables()
	 * @generated
	 * @ordered
	 */
	protected EList<ShemaTable> shematables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TablesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShematablesPackage.Literals.TABLES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ShemaTable> getShematables() {
		if (shematables == null) {
			shematables = new EObjectContainmentEList<ShemaTable>(ShemaTable.class, this, ShematablesPackage.TABLES__SHEMATABLES);
		}
		return shematables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ShematablesPackage.TABLES__SHEMATABLES:
				return ((InternalEList<?>)getShematables()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ShematablesPackage.TABLES__SHEMATABLES:
				return getShematables();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ShematablesPackage.TABLES__SHEMATABLES:
				getShematables().clear();
				getShematables().addAll((Collection<? extends ShemaTable>)newValue);
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
			case ShematablesPackage.TABLES__SHEMATABLES:
				getShematables().clear();
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
			case ShematablesPackage.TABLES__SHEMATABLES:
				return shematables != null && !shematables.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TablesImpl

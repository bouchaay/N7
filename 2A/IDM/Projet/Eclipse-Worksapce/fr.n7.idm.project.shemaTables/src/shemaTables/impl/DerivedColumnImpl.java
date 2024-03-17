/**
 */
package shematables.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import shematables.Algorithm;
import shematables.DerivedColumn;
import shematables.Output;
import shematables.ShematablesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Derived Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link shematables.impl.DerivedColumnImpl#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link shematables.impl.DerivedColumnImpl#getOutputColumn <em>Output Column</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DerivedColumnImpl extends ColumnImpl implements DerivedColumn {
	/**
	 * The cached value of the '{@link #getAlgorithm() <em>Algorithm</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlgorithm()
	 * @generated
	 * @ordered
	 */
	protected Algorithm algorithm;

	/**
	 * The cached value of the '{@link #getOutputColumn() <em>Output Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputColumn()
	 * @generated
	 * @ordered
	 */
	protected Output outputColumn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DerivedColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ShematablesPackage.Literals.DERIVED_COLUMN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Algorithm getAlgorithm() {
		if (algorithm != null && algorithm.eIsProxy()) {
			InternalEObject oldAlgorithm = (InternalEObject)algorithm;
			algorithm = (Algorithm)eResolveProxy(oldAlgorithm);
			if (algorithm != oldAlgorithm) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ShematablesPackage.DERIVED_COLUMN__ALGORITHM, oldAlgorithm, algorithm));
			}
		}
		return algorithm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Algorithm basicGetAlgorithm() {
		return algorithm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAlgorithm(Algorithm newAlgorithm) {
		Algorithm oldAlgorithm = algorithm;
		algorithm = newAlgorithm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShematablesPackage.DERIVED_COLUMN__ALGORITHM, oldAlgorithm, algorithm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Output getOutputColumn() {
		if (outputColumn != null && outputColumn.eIsProxy()) {
			InternalEObject oldOutputColumn = (InternalEObject)outputColumn;
			outputColumn = (Output)eResolveProxy(oldOutputColumn);
			if (outputColumn != oldOutputColumn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ShematablesPackage.DERIVED_COLUMN__OUTPUT_COLUMN, oldOutputColumn, outputColumn));
			}
		}
		return outputColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Output basicGetOutputColumn() {
		return outputColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOutputColumn(Output newOutputColumn) {
		Output oldOutputColumn = outputColumn;
		outputColumn = newOutputColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ShematablesPackage.DERIVED_COLUMN__OUTPUT_COLUMN, oldOutputColumn, outputColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ShematablesPackage.DERIVED_COLUMN__ALGORITHM:
				if (resolve) return getAlgorithm();
				return basicGetAlgorithm();
			case ShematablesPackage.DERIVED_COLUMN__OUTPUT_COLUMN:
				if (resolve) return getOutputColumn();
				return basicGetOutputColumn();
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
			case ShematablesPackage.DERIVED_COLUMN__ALGORITHM:
				setAlgorithm((Algorithm)newValue);
				return;
			case ShematablesPackage.DERIVED_COLUMN__OUTPUT_COLUMN:
				setOutputColumn((Output)newValue);
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
			case ShematablesPackage.DERIVED_COLUMN__ALGORITHM:
				setAlgorithm((Algorithm)null);
				return;
			case ShematablesPackage.DERIVED_COLUMN__OUTPUT_COLUMN:
				setOutputColumn((Output)null);
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
			case ShematablesPackage.DERIVED_COLUMN__ALGORITHM:
				return algorithm != null;
			case ShematablesPackage.DERIVED_COLUMN__OUTPUT_COLUMN:
				return outputColumn != null;
		}
		return super.eIsSet(featureID);
	}

} //DerivedColumnImpl

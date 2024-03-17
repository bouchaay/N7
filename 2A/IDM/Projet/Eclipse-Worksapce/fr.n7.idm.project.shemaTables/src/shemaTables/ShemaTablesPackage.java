/**
 */
package shematables;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see shematables.ShematablesFactory
 * @model kind="package"
 * @generated
 */
public interface ShematablesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "shematables";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://shematables";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "shematables";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ShematablesPackage eINSTANCE = shematables.impl.ShematablesPackageImpl.init();

	/**
	 * The meta object id for the '{@link shematables.impl.TablesImpl <em>Tables</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.TablesImpl
	 * @see shematables.impl.ShematablesPackageImpl#getTables()
	 * @generated
	 */
	int TABLES = 0;

	/**
	 * The feature id for the '<em><b>Shematables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLES__SHEMATABLES = 0;

	/**
	 * The number of structural features of the '<em>Tables</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Tables</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link shematables.impl.ShemaTableImpl <em>Shema Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.ShemaTableImpl
	 * @see shematables.impl.ShematablesPackageImpl#getShemaTable()
	 * @generated
	 */
	int SHEMA_TABLE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEMA_TABLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEMA_TABLE__ID = 1;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEMA_TABLE__COLUMNS = 2;

	/**
	 * The feature id for the '<em><b>Catalogue</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEMA_TABLE__CATALOGUE = 3;

	/**
	 * The number of structural features of the '<em>Shema Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEMA_TABLE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Shema Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEMA_TABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link shematables.impl.ColumnImpl <em>Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.ColumnImpl
	 * @see shematables.impl.ShematablesPackageImpl#getColumn()
	 * @generated
	 */
	int COLUMN = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__NAME = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__ID = 1;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__DATA_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__CONSTRAINT = 3;

	/**
	 * The number of structural features of the '<em>Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link shematables.impl.ReferencedColumnImpl <em>Referenced Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.ReferencedColumnImpl
	 * @see shematables.impl.ShematablesPackageImpl#getReferencedColumn()
	 * @generated
	 */
	int REFERENCED_COLUMN = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_COLUMN__NAME = COLUMN__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_COLUMN__ID = COLUMN__ID;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_COLUMN__DATA_TYPE = COLUMN__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_COLUMN__CONSTRAINT = COLUMN__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Foreign Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_COLUMN__FOREIGN_TABLE = COLUMN_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Foreign Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_COLUMN__FOREIGN_COLUMN = COLUMN_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Referenced Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_COLUMN_FEATURE_COUNT = COLUMN_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Referenced Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_COLUMN_OPERATION_COUNT = COLUMN_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link shematables.impl.DerivedColumnImpl <em>Derived Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.DerivedColumnImpl
	 * @see shematables.impl.ShematablesPackageImpl#getDerivedColumn()
	 * @generated
	 */
	int DERIVED_COLUMN = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_COLUMN__NAME = COLUMN__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_COLUMN__ID = COLUMN__ID;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_COLUMN__DATA_TYPE = COLUMN__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_COLUMN__CONSTRAINT = COLUMN__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Algorithm</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_COLUMN__ALGORITHM = COLUMN_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Output Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_COLUMN__OUTPUT_COLUMN = COLUMN_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Derived Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_COLUMN_FEATURE_COUNT = COLUMN_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Derived Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_COLUMN_OPERATION_COUNT = COLUMN_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link shematables.impl.ConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.ConstraintImpl
	 * @see shematables.impl.ShematablesPackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 5;

	/**
	 * The feature id for the '<em><b>File Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__FILE_PATH = 0;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link shematables.impl.CatalogueImpl <em>Catalogue</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.CatalogueImpl
	 * @see shematables.impl.ShematablesPackageImpl#getCatalogue()
	 * @generated
	 */
	int CATALOGUE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATALOGUE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Algorithms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATALOGUE__ALGORITHMS = 1;

	/**
	 * The number of structural features of the '<em>Catalogue</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATALOGUE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Catalogue</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATALOGUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link shematables.impl.AlgorithmImpl <em>Algorithm</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.AlgorithmImpl
	 * @see shematables.impl.ShematablesPackageImpl#getAlgorithm()
	 * @generated
	 */
	int ALGORITHM = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALGORITHM__NAME = 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALGORITHM__PATH = 1;

	/**
	 * The feature id for the '<em><b>Output</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALGORITHM__OUTPUT = 2;

	/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALGORITHM__INPUT = 3;

	/**
	 * The number of structural features of the '<em>Algorithm</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALGORITHM_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Algorithm</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALGORITHM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link shematables.impl.InputImpl <em>Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.InputImpl
	 * @see shematables.impl.ShematablesPackageImpl#getInput()
	 * @generated
	 */
	int INPUT = 8;

	/**
	 * The feature id for the '<em><b>Constante</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__CONSTANTE = 0;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__COLUMNS = 1;

	/**
	 * The number of structural features of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link shematables.impl.OutputImpl <em>Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.OutputImpl
	 * @see shematables.impl.ShematablesPackageImpl#getOutput()
	 * @generated
	 */
	int OUTPUT = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__NAME = COLUMN__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__ID = COLUMN__ID;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__DATA_TYPE = COLUMN__DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__CONSTRAINT = COLUMN__CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_FEATURE_COUNT = COLUMN_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_OPERATION_COUNT = COLUMN_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link shematables.impl.ConstantImpl <em>Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.impl.ConstantImpl
	 * @see shematables.impl.ShematablesPackageImpl#getConstant()
	 * @generated
	 */
	int CONSTANT = 10;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link shematables.ColumnDataType <em>Column Data Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see shematables.ColumnDataType
	 * @see shematables.impl.ShematablesPackageImpl#getColumnDataType()
	 * @generated
	 */
	int COLUMN_DATA_TYPE = 11;


	/**
	 * Returns the meta object for class '{@link shematables.Tables <em>Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tables</em>'.
	 * @see shematables.Tables
	 * @generated
	 */
	EClass getTables();

	/**
	 * Returns the meta object for the containment reference list '{@link shematables.Tables#getShematables <em>Shematables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Shematables</em>'.
	 * @see shematables.Tables#getShematables()
	 * @see #getTables()
	 * @generated
	 */
	EReference getTables_Shematables();

	/**
	 * Returns the meta object for class '{@link shematables.ShemaTable <em>Shema Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shema Table</em>'.
	 * @see shematables.ShemaTable
	 * @generated
	 */
	EClass getShemaTable();

	/**
	 * Returns the meta object for the attribute '{@link shematables.ShemaTable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see shematables.ShemaTable#getName()
	 * @see #getShemaTable()
	 * @generated
	 */
	EAttribute getShemaTable_Name();

	/**
	 * Returns the meta object for the attribute '{@link shematables.ShemaTable#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see shematables.ShemaTable#getId()
	 * @see #getShemaTable()
	 * @generated
	 */
	EAttribute getShemaTable_Id();

	/**
	 * Returns the meta object for the containment reference list '{@link shematables.ShemaTable#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Columns</em>'.
	 * @see shematables.ShemaTable#getColumns()
	 * @see #getShemaTable()
	 * @generated
	 */
	EReference getShemaTable_Columns();

	/**
	 * Returns the meta object for the containment reference '{@link shematables.ShemaTable#getCatalogue <em>Catalogue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Catalogue</em>'.
	 * @see shematables.ShemaTable#getCatalogue()
	 * @see #getShemaTable()
	 * @generated
	 */
	EReference getShemaTable_Catalogue();

	/**
	 * Returns the meta object for class '{@link shematables.Column <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Column</em>'.
	 * @see shematables.Column
	 * @generated
	 */
	EClass getColumn();

	/**
	 * Returns the meta object for the attribute '{@link shematables.Column#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see shematables.Column#getName()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Name();

	/**
	 * Returns the meta object for the attribute '{@link shematables.Column#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see shematables.Column#getId()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Id();

	/**
	 * Returns the meta object for the attribute '{@link shematables.Column#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see shematables.Column#getDataType()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_DataType();

	/**
	 * Returns the meta object for the containment reference '{@link shematables.Column#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Constraint</em>'.
	 * @see shematables.Column#getConstraint()
	 * @see #getColumn()
	 * @generated
	 */
	EReference getColumn_Constraint();

	/**
	 * Returns the meta object for class '{@link shematables.ReferencedColumn <em>Referenced Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referenced Column</em>'.
	 * @see shematables.ReferencedColumn
	 * @generated
	 */
	EClass getReferencedColumn();

	/**
	 * Returns the meta object for the reference '{@link shematables.ReferencedColumn#getForeignTable <em>Foreign Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Foreign Table</em>'.
	 * @see shematables.ReferencedColumn#getForeignTable()
	 * @see #getReferencedColumn()
	 * @generated
	 */
	EReference getReferencedColumn_ForeignTable();

	/**
	 * Returns the meta object for the reference '{@link shematables.ReferencedColumn#getForeignColumn <em>Foreign Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Foreign Column</em>'.
	 * @see shematables.ReferencedColumn#getForeignColumn()
	 * @see #getReferencedColumn()
	 * @generated
	 */
	EReference getReferencedColumn_ForeignColumn();

	/**
	 * Returns the meta object for class '{@link shematables.DerivedColumn <em>Derived Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Derived Column</em>'.
	 * @see shematables.DerivedColumn
	 * @generated
	 */
	EClass getDerivedColumn();

	/**
	 * Returns the meta object for the reference '{@link shematables.DerivedColumn#getAlgorithm <em>Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Algorithm</em>'.
	 * @see shematables.DerivedColumn#getAlgorithm()
	 * @see #getDerivedColumn()
	 * @generated
	 */
	EReference getDerivedColumn_Algorithm();

	/**
	 * Returns the meta object for the reference '{@link shematables.DerivedColumn#getOutputColumn <em>Output Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Output Column</em>'.
	 * @see shematables.DerivedColumn#getOutputColumn()
	 * @see #getDerivedColumn()
	 * @generated
	 */
	EReference getDerivedColumn_OutputColumn();

	/**
	 * Returns the meta object for class '{@link shematables.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see shematables.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the meta object for the attribute '{@link shematables.Constraint#getFilePath <em>File Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Path</em>'.
	 * @see shematables.Constraint#getFilePath()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_FilePath();

	/**
	 * Returns the meta object for class '{@link shematables.Catalogue <em>Catalogue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Catalogue</em>'.
	 * @see shematables.Catalogue
	 * @generated
	 */
	EClass getCatalogue();

	/**
	 * Returns the meta object for the attribute '{@link shematables.Catalogue#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see shematables.Catalogue#getName()
	 * @see #getCatalogue()
	 * @generated
	 */
	EAttribute getCatalogue_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link shematables.Catalogue#getAlgorithms <em>Algorithms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Algorithms</em>'.
	 * @see shematables.Catalogue#getAlgorithms()
	 * @see #getCatalogue()
	 * @generated
	 */
	EReference getCatalogue_Algorithms();

	/**
	 * Returns the meta object for class '{@link shematables.Algorithm <em>Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Algorithm</em>'.
	 * @see shematables.Algorithm
	 * @generated
	 */
	EClass getAlgorithm();

	/**
	 * Returns the meta object for the attribute '{@link shematables.Algorithm#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see shematables.Algorithm#getName()
	 * @see #getAlgorithm()
	 * @generated
	 */
	EAttribute getAlgorithm_Name();

	/**
	 * Returns the meta object for the attribute '{@link shematables.Algorithm#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see shematables.Algorithm#getPath()
	 * @see #getAlgorithm()
	 * @generated
	 */
	EAttribute getAlgorithm_Path();

	/**
	 * Returns the meta object for the containment reference '{@link shematables.Algorithm#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Output</em>'.
	 * @see shematables.Algorithm#getOutput()
	 * @see #getAlgorithm()
	 * @generated
	 */
	EReference getAlgorithm_Output();

	/**
	 * Returns the meta object for the containment reference '{@link shematables.Algorithm#getInput <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Input</em>'.
	 * @see shematables.Algorithm#getInput()
	 * @see #getAlgorithm()
	 * @generated
	 */
	EReference getAlgorithm_Input();

	/**
	 * Returns the meta object for class '{@link shematables.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input</em>'.
	 * @see shematables.Input
	 * @generated
	 */
	EClass getInput();

	/**
	 * Returns the meta object for the containment reference '{@link shematables.Input#getConstante <em>Constante</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Constante</em>'.
	 * @see shematables.Input#getConstante()
	 * @see #getInput()
	 * @generated
	 */
	EReference getInput_Constante();

	/**
	 * Returns the meta object for the reference list '{@link shematables.Input#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Columns</em>'.
	 * @see shematables.Input#getColumns()
	 * @see #getInput()
	 * @generated
	 */
	EReference getInput_Columns();

	/**
	 * Returns the meta object for class '{@link shematables.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output</em>'.
	 * @see shematables.Output
	 * @generated
	 */
	EClass getOutput();

	/**
	 * Returns the meta object for class '{@link shematables.Constant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constant</em>'.
	 * @see shematables.Constant
	 * @generated
	 */
	EClass getConstant();

	/**
	 * Returns the meta object for the attribute '{@link shematables.Constant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see shematables.Constant#getValue()
	 * @see #getConstant()
	 * @generated
	 */
	EAttribute getConstant_Value();

	/**
	 * Returns the meta object for enum '{@link shematables.ColumnDataType <em>Column Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Column Data Type</em>'.
	 * @see shematables.ColumnDataType
	 * @generated
	 */
	EEnum getColumnDataType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ShematablesFactory getShematablesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link shematables.impl.TablesImpl <em>Tables</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.TablesImpl
		 * @see shematables.impl.ShematablesPackageImpl#getTables()
		 * @generated
		 */
		EClass TABLES = eINSTANCE.getTables();

		/**
		 * The meta object literal for the '<em><b>Shematables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLES__SHEMATABLES = eINSTANCE.getTables_Shematables();

		/**
		 * The meta object literal for the '{@link shematables.impl.ShemaTableImpl <em>Shema Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.ShemaTableImpl
		 * @see shematables.impl.ShematablesPackageImpl#getShemaTable()
		 * @generated
		 */
		EClass SHEMA_TABLE = eINSTANCE.getShemaTable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHEMA_TABLE__NAME = eINSTANCE.getShemaTable_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHEMA_TABLE__ID = eINSTANCE.getShemaTable_Id();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHEMA_TABLE__COLUMNS = eINSTANCE.getShemaTable_Columns();

		/**
		 * The meta object literal for the '<em><b>Catalogue</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHEMA_TABLE__CATALOGUE = eINSTANCE.getShemaTable_Catalogue();

		/**
		 * The meta object literal for the '{@link shematables.impl.ColumnImpl <em>Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.ColumnImpl
		 * @see shematables.impl.ShematablesPackageImpl#getColumn()
		 * @generated
		 */
		EClass COLUMN = eINSTANCE.getColumn();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__NAME = eINSTANCE.getColumn_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__ID = eINSTANCE.getColumn_Id();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__DATA_TYPE = eINSTANCE.getColumn_DataType();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLUMN__CONSTRAINT = eINSTANCE.getColumn_Constraint();

		/**
		 * The meta object literal for the '{@link shematables.impl.ReferencedColumnImpl <em>Referenced Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.ReferencedColumnImpl
		 * @see shematables.impl.ShematablesPackageImpl#getReferencedColumn()
		 * @generated
		 */
		EClass REFERENCED_COLUMN = eINSTANCE.getReferencedColumn();

		/**
		 * The meta object literal for the '<em><b>Foreign Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCED_COLUMN__FOREIGN_TABLE = eINSTANCE.getReferencedColumn_ForeignTable();

		/**
		 * The meta object literal for the '<em><b>Foreign Column</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCED_COLUMN__FOREIGN_COLUMN = eINSTANCE.getReferencedColumn_ForeignColumn();

		/**
		 * The meta object literal for the '{@link shematables.impl.DerivedColumnImpl <em>Derived Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.DerivedColumnImpl
		 * @see shematables.impl.ShematablesPackageImpl#getDerivedColumn()
		 * @generated
		 */
		EClass DERIVED_COLUMN = eINSTANCE.getDerivedColumn();

		/**
		 * The meta object literal for the '<em><b>Algorithm</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DERIVED_COLUMN__ALGORITHM = eINSTANCE.getDerivedColumn_Algorithm();

		/**
		 * The meta object literal for the '<em><b>Output Column</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DERIVED_COLUMN__OUTPUT_COLUMN = eINSTANCE.getDerivedColumn_OutputColumn();

		/**
		 * The meta object literal for the '{@link shematables.impl.ConstraintImpl <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.ConstraintImpl
		 * @see shematables.impl.ShematablesPackageImpl#getConstraint()
		 * @generated
		 */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
		 * The meta object literal for the '<em><b>File Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT__FILE_PATH = eINSTANCE.getConstraint_FilePath();

		/**
		 * The meta object literal for the '{@link shematables.impl.CatalogueImpl <em>Catalogue</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.CatalogueImpl
		 * @see shematables.impl.ShematablesPackageImpl#getCatalogue()
		 * @generated
		 */
		EClass CATALOGUE = eINSTANCE.getCatalogue();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATALOGUE__NAME = eINSTANCE.getCatalogue_Name();

		/**
		 * The meta object literal for the '<em><b>Algorithms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATALOGUE__ALGORITHMS = eINSTANCE.getCatalogue_Algorithms();

		/**
		 * The meta object literal for the '{@link shematables.impl.AlgorithmImpl <em>Algorithm</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.AlgorithmImpl
		 * @see shematables.impl.ShematablesPackageImpl#getAlgorithm()
		 * @generated
		 */
		EClass ALGORITHM = eINSTANCE.getAlgorithm();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALGORITHM__NAME = eINSTANCE.getAlgorithm_Name();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALGORITHM__PATH = eINSTANCE.getAlgorithm_Path();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALGORITHM__OUTPUT = eINSTANCE.getAlgorithm_Output();

		/**
		 * The meta object literal for the '<em><b>Input</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALGORITHM__INPUT = eINSTANCE.getAlgorithm_Input();

		/**
		 * The meta object literal for the '{@link shematables.impl.InputImpl <em>Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.InputImpl
		 * @see shematables.impl.ShematablesPackageImpl#getInput()
		 * @generated
		 */
		EClass INPUT = eINSTANCE.getInput();

		/**
		 * The meta object literal for the '<em><b>Constante</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT__CONSTANTE = eINSTANCE.getInput_Constante();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT__COLUMNS = eINSTANCE.getInput_Columns();

		/**
		 * The meta object literal for the '{@link shematables.impl.OutputImpl <em>Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.OutputImpl
		 * @see shematables.impl.ShematablesPackageImpl#getOutput()
		 * @generated
		 */
		EClass OUTPUT = eINSTANCE.getOutput();

		/**
		 * The meta object literal for the '{@link shematables.impl.ConstantImpl <em>Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.impl.ConstantImpl
		 * @see shematables.impl.ShematablesPackageImpl#getConstant()
		 * @generated
		 */
		EClass CONSTANT = eINSTANCE.getConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT__VALUE = eINSTANCE.getConstant_Value();

		/**
		 * The meta object literal for the '{@link shematables.ColumnDataType <em>Column Data Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see shematables.ColumnDataType
		 * @see shematables.impl.ShematablesPackageImpl#getColumnDataType()
		 * @generated
		 */
		EEnum COLUMN_DATA_TYPE = eINSTANCE.getColumnDataType();

	}

} //ShematablesPackage

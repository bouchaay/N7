/**
 */
package shematables.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import shematables.util.ShematablesAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ShematablesItemProviderAdapterFactory extends ShematablesAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShematablesItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.Tables} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TablesItemProvider tablesItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.Tables}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTablesAdapter() {
		if (tablesItemProvider == null) {
			tablesItemProvider = new TablesItemProvider(this);
		}

		return tablesItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.ShemaTable} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShemaTableItemProvider shemaTableItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.ShemaTable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createShemaTableAdapter() {
		if (shemaTableItemProvider == null) {
			shemaTableItemProvider = new ShemaTableItemProvider(this);
		}

		return shemaTableItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.Column} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColumnItemProvider columnItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.Column}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createColumnAdapter() {
		if (columnItemProvider == null) {
			columnItemProvider = new ColumnItemProvider(this);
		}

		return columnItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.ReferencedColumn} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferencedColumnItemProvider referencedColumnItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.ReferencedColumn}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createReferencedColumnAdapter() {
		if (referencedColumnItemProvider == null) {
			referencedColumnItemProvider = new ReferencedColumnItemProvider(this);
		}

		return referencedColumnItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.DerivedColumn} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DerivedColumnItemProvider derivedColumnItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.DerivedColumn}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDerivedColumnAdapter() {
		if (derivedColumnItemProvider == null) {
			derivedColumnItemProvider = new DerivedColumnItemProvider(this);
		}

		return derivedColumnItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.Constraint} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintItemProvider constraintItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.Constraint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createConstraintAdapter() {
		if (constraintItemProvider == null) {
			constraintItemProvider = new ConstraintItemProvider(this);
		}

		return constraintItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.Catalogue} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CatalogueItemProvider catalogueItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.Catalogue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCatalogueAdapter() {
		if (catalogueItemProvider == null) {
			catalogueItemProvider = new CatalogueItemProvider(this);
		}

		return catalogueItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.Algorithm} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AlgorithmItemProvider algorithmItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.Algorithm}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAlgorithmAdapter() {
		if (algorithmItemProvider == null) {
			algorithmItemProvider = new AlgorithmItemProvider(this);
		}

		return algorithmItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.Input} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InputItemProvider inputItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.Input}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createInputAdapter() {
		if (inputItemProvider == null) {
			inputItemProvider = new InputItemProvider(this);
		}

		return inputItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.Output} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutputItemProvider outputItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.Output}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOutputAdapter() {
		if (outputItemProvider == null) {
			outputItemProvider = new OutputItemProvider(this);
		}

		return outputItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link shematables.Constant} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstantItemProvider constantItemProvider;

	/**
	 * This creates an adapter for a {@link shematables.Constant}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createConstantAdapter() {
		if (constantItemProvider == null) {
			constantItemProvider = new ConstantItemProvider(this);
		}

		return constantItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void dispose() {
		if (tablesItemProvider != null) tablesItemProvider.dispose();
		if (shemaTableItemProvider != null) shemaTableItemProvider.dispose();
		if (columnItemProvider != null) columnItemProvider.dispose();
		if (referencedColumnItemProvider != null) referencedColumnItemProvider.dispose();
		if (derivedColumnItemProvider != null) derivedColumnItemProvider.dispose();
		if (constraintItemProvider != null) constraintItemProvider.dispose();
		if (catalogueItemProvider != null) catalogueItemProvider.dispose();
		if (algorithmItemProvider != null) algorithmItemProvider.dispose();
		if (inputItemProvider != null) inputItemProvider.dispose();
		if (outputItemProvider != null) outputItemProvider.dispose();
		if (constantItemProvider != null) constantItemProvider.dispose();
	}

}

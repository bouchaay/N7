/*
 * generated by Xtext 2.32.0
 */
package fr.n7.idm.project.xtext;


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class ShemaTablesStandaloneSetup extends ShemaTablesStandaloneSetupGenerated {

	public static void doSetup() {
		new ShemaTablesStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
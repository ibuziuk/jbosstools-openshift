package org.jboss.tools.openshift.internal.ui.wizard.deployment;

import org.jboss.tools.common.databinding.ObservablePojo;

public class DeploymentSettingsWizardPageModel  extends ObservablePojo  {

	public static final String PROPERTY_INCLUDE_BUILD_CONFIG = "includeBuildConfig";
	public static final String PROPERTY_ECLIPSE_PROJECT_NAME = "eclipseProjectName";
	public static final String PROPERTY_ECLIPSE_SERVICES_DEPENDENCIES = "serviceDependencies";
	private DeploymentWizardContext context;

	public DeploymentSettingsWizardPageModel(DeploymentWizardContext context) {
		this.context = context;
	}
	public boolean getIncludeBuildConfig(){
		return context.includeBuildConfig();
	}
	public void setIncludeBuildConfig(boolean include){
		boolean old = context.includeBuildConfig();
		context.setIncludeBuildConfig(include);
		firePropertyChange(PROPERTY_INCLUDE_BUILD_CONFIG, old, include);
	}
	
	public void setEclipseProjectName(String name){
		String old = context.getProjectName();
		context.setProjectName(name);
		firePropertyChange(PROPERTY_ECLIPSE_PROJECT_NAME, old, name);
	}

	public String getEclipseProjectName() {
		return context.getProjectName();
	}
	
	public void setServiceDependencies(String services){
		String old = context.getServiceDependencies();
		context.setServiceDependencies(services);
		firePropertyChange(PROPERTY_ECLIPSE_SERVICES_DEPENDENCIES, old, services);
	}
	
	public String getServiceDependencies(){
		return context.getServiceDependencies();
	}

	public void reset() {
		setIncludeBuildConfig(true);
		setEclipseProjectName("");
	}
}

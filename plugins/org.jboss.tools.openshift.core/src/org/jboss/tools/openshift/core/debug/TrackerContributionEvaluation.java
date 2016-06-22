package org.jboss.tools.openshift.core.debug;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.wst.server.core.IServer;

public class TrackerContributionEvaluation {
	
	private static final String ID = "org.jboss.tools.openshift.core.debugSessionTracker"; //$NON-NLS-1$
	
	public static void startDebugSessions(IServer server, int port) throws CoreException {
		Collection<DebugSessionTracker> trackers = getTrackers();
		for (DebugSessionTracker t : trackers) {
			t.startDebugSession(server, port);
		}
	}
	
	public static boolean isDebugSessionRunning (IServer server) {
		Collection<DebugSessionTracker> trackers = getTrackers();
		for (DebugSessionTracker t : trackers) {
			return t.isDebugSessionRunning(server);
		}
		return false;
	}
	
	private static Collection<DebugSessionTracker> getTrackers() {
		IConfigurationElement[] elements = getConfigurationElements();
		Collection<DebugSessionTracker> trackers = new ArrayList<>();
		for (IConfigurationElement element : elements) {
			try {
				Object extension = element.createExecutableExtension("class"); //$NON-NLS-1$
				if (extension instanceof DebugSessionTracker) {
					trackers.add((DebugSessionTracker) extension);
				}
			} catch (CoreException e) {
				throw new RuntimeException(e);
			} 
		}
		return trackers;
	}
	

    private void executeExtension(final DebugSessionTracker tracker) {
            ISafeRunnable runnable = new ISafeRunnable() {
                    @Override
                    public void handleException(Throwable e) {
                    	System.out.println("exception");
                    }

                    @Override
                    public void run() throws Exception {
                        System.out.println("run");    
                    }
            };
            SafeRunner.run(runnable);
    } 
	
	private static IConfigurationElement[] getConfigurationElements() {
		return Platform.getExtensionRegistry().getConfigurationElementsFor(ID);
	}
}

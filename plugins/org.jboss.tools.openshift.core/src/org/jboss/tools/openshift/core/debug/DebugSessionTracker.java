package org.jboss.tools.openshift.core.debug;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.wst.server.core.IServer;

public interface DebugSessionTracker {
	
	void startDebugSession(IServer server, int port) throws CoreException;
	
	boolean isDebugSessionRunning(IServer server);

}

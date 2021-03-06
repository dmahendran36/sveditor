package net.sf.sveditor.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamMonitor;

import net.sf.sveditor.core.builder.ISVBuildProcessListener;
import net.sf.sveditor.core.builder.SVBuilderProcess;
import net.sf.sveditor.ui.console.SVConsoleManager;
import net.sf.sveditor.ui.console.SVGlobalConsole;
import net.sf.sveditor.ui.console.SVProcessConsole;

public class SVBuildProcessListener implements ISVBuildProcessListener {
	private SVGlobalConsole			fGlobalBuildConsole;
	
	private IStreamListener fStdoutListener = new IStreamListener() {
		
		@Override
		public void streamAppended(String text, IStreamMonitor monitor) {
			fGlobalBuildConsole.stdout(text);
		}
	};
	
	private IStreamListener fStderrListener = new IStreamListener() {
		
		@Override
		public void streamAppended(String text, IStreamMonitor monitor) {
			fGlobalBuildConsole.stderr(text);
		}
	};

	@Override
	public void buildProcess(Process p) {
//		if (fGlobalBuildConsole == null) {
//			fGlobalBuildConsole = SVConsoleManager.getDefault().openConsole(
//					"SV Global Build Console", 
//					"sve.build");
//		}
		
    	ILaunch launch = new Launch(null, "run", null); 
    	IProject project = ((SVBuilderProcess)p).getProject();
    	IProcess process = DebugPlugin.newProcess(launch, p, 
    			"SV Build Console [" + project.getName() + "]");
    	process.setAttribute(IProcess.ATTR_PROCESS_TYPE, "sve.build");
		SVProcessConsole console = SVConsoleManager.getDefault().openProcessConsole(
				process, null);
		
//		// add stream listeners to direct project build to the global console
//		console.getStreamsProxy().getOutputStreamMonitor().addListener(fStdoutListener);
//		console.getStreamsProxy().getErrorStreamMonitor().addListener(fStderrListener);
		
	}

}

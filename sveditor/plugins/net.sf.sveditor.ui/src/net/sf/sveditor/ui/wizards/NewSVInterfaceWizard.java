/****************************************************************************
 * Copyright (c) 2008-2014 Matthew Ballance and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Ballance - initial implementation
 ****************************************************************************/


package net.sf.sveditor.ui.wizards;

import net.sf.sveditor.core.srcgen.NewInterfaceGenerator;
import net.sf.sveditor.ui.SVUiPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

public class NewSVInterfaceWizard extends AbstractNewSVItemFileWizard {
	public static final String				ID = SVUiPlugin.PLUGIN_ID + ".newSVInterfaceWizard";

	public NewSVInterfaceWizard() {
		super();
	}
	
	
	@Override
	protected AbstractNewSVItemFileWizardPage createPage() {
		return new NewSVInterfaceWizardPage(this);
	}

	@Override
	protected void generate(IProgressMonitor monitor, IFile file_path) {
		NewInterfaceGenerator gen = new NewInterfaceGenerator(fTagProc);
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
		
		gen.generate(getIndexIterator(subMonitor.newChild(1)), 
				file_path,
				fPage.getOption(AbstractNewSVItemFileWizardPage.NAME, null),
				subMonitor.newChild(1));
	}

}

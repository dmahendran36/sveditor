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


package net.sf.sveditor.core.tests.content_assist;

import net.sf.sveditor.core.SVCorePlugin;
import net.sf.sveditor.core.tests.SVCoreTestCaseBase;

public class TestContentAssistMacroDef extends SVCoreTestCaseBase {
	
	
	/**
	 * Test that simple definitions are found and complete 
	 */
	public void testMacroDefSimple() {
		SVCorePlugin.getDefault().enableDebug(false);
		
		String doc =
			"`define THE_DEFINES 1\n" +
			"`define THE_DEFINEM(arg1,arg2)\\\n" +
			"	$display (arg1, arg2);\n" +
			"class foobar;\n" +
			"	int		AAAA;\n" +
			"	int		AABB;\n" +
			"	int		BBCC;\n" +
			"\n" +
			"task my_task(foobar param);\n" +
			"	a = `THE_DE<<MARK>>\n" +
			"endtask\n" +
			"endclass\n"
			;
		
		ContentAssistTests.runTest(this, doc, 
				"THE_DEFINEM", "THE_DEFINES");
	}

	/**
	 * Test that simple definitions are found and complete 
	 */
	public void testMacroDefMacro() {
		SVCorePlugin.getDefault().enableDebug(false);
		
		String doc =
				"`define THE_DEFINEM(arg1,arg2)\\\n" +
				" 	$display (arg1, arg2);\n" +
				"`define THE_DEFINES 1\n" +
				"class foobar;\n" +
				"	int		AAAA;\n" +
				"	int		AABB;\n" +
				"	int		BBCC;\n" +
				"\n" +
				"task my_task(foobar param);\n" +
				"	a = `THE_DE<<MARK>>\n" +
				"endtask\n" +
				"endclass\n"
				;
		
		ContentAssistTests.runTest(this, doc, 
				"THE_DEFINEM", "THE_DEFINES");
	}
	

}

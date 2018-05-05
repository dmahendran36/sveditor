/****************************************************************************
 * Copyright (c) 2008-2014 Matthew Ballance and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eclipse CDT -- initial implementation
 ****************************************************************************/


package net.sf.sveditor.ui.vhdl.editor.actions;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import net.sf.sveditor.core.log.LogFactory;
import net.sf.sveditor.ui.vhdl.editor.VHDLDocumentPartitions;
import net.sf.sveditor.ui.vhdl.editor.VHDLEditor;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITypedRegion;

public class AddBlockCommentAction extends BlockCommentAction {
	
	public AddBlockCommentAction(ResourceBundle bundle, String prefix, VHDLEditor editor) {
		super(bundle, prefix, editor);
		fLog = LogFactory.getLogHandle("AddBlockCommentAction");
	}
	
	
	/*
	 * @see org.eclipse.jdt.internal.ui.actions.BlockCommentAction#runInternal(org.eclipse.jface.text.ITextSelection, org.eclipse.jface.text.IDocumentExtension3, org.eclipse.jdt.internal.ui.actions.BlockCommentAction.Edit.EditFactory)
	 */
	protected void runInternal(ITextSelection selection, IDocumentExtension3 docExtension, Edit.EditFactory factory) throws BadLocationException, BadPartitioningException {
		int selectionOffset= selection.getOffset();
		int selectionEndOffset= selectionOffset + selection.getLength();
		List<Edit> edits= new LinkedList<Edit>();
		ITypedRegion partition;
		IDocument doc = (IDocument)docExtension;

		int partEndOffset= selectionOffset;
		
		int newSelectionOffset = selectionOffset;
		int newSelectionEndOffset = selectionEndOffset;
		
		do { // while (partition.getOffset() + partition.getLength() < selectionEndOffset) {
			partition= docExtension.getPartition(VHDLDocumentPartitions.VHD_PARTITIONING, 
					partEndOffset, false);
			partEndOffset = partition.getOffset()+partition.getLength();

			if (partition.getType() != VHDLDocumentPartitions.VHD_COMMENT) {
				while (selectionOffset < partEndOffset && selectionOffset < selectionEndOffset) {
					edits.add(factory.createEdit(selectionOffset, 0, "--"));
					int line = doc.getLineOfOffset(selectionOffset);
					line++;
					selectionOffset = doc.getLineOffset(line);
					newSelectionEndOffset += 2;
				}
			} else {
				selectionOffset = partition.getOffset() + partition.getLength();
			}

		} while (selectionEndOffset > partEndOffset);

		executeEdits(edits);
		setSelection(newSelectionOffset, newSelectionEndOffset);
	}

	/*
	 * @see org.eclipse.jdt.internal.ui.actions.BlockCommentAction#validSelection(org.eclipse.jface.text.ITextSelection)
	 */
	protected boolean isValidSelection(ITextSelection selection) {
		return (selection != null && !selection.isEmpty() && selection.getLength() > 0);
	}


}

package net.sf.sveditor.core.db.index;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SVDBFSFileSystemProvider implements ISVDBFileSystemProvider {
	
	public void init(String path) {}
	
	public void dispose() {}

	public void closeStream(InputStream in) {
		try {
			in.close();
		} catch (IOException e) {}
	}

	public boolean fileExists(String path) {
		File f = new File(path);
		return f.isFile();
	}

	public long getLastModifiedTime(String path) {
		File f = new File(path);
		
		return f.lastModified();
	}

	public InputStream openStream(String path) {
		InputStream in = null;
		
		try {
			in = new FileInputStream(path);
		} catch (IOException e) { }
		
		return in;
	}

	public void addFileSystemChangeListener(ISVDBFileSystemChangeListener l) {
		// TODO Auto-generated method stub
		
	}

	public void removeFileSystemChangeListener(ISVDBFileSystemChangeListener l) {
		// TODO Auto-generated method stub
		
	}
	
}
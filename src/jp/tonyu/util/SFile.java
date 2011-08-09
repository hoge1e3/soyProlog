package jp.tonyu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

public class SFile implements Iterable<SFile>{
	java.io.File f;
	public long lastModified() {
		if (!exists()) return 0;
		return f.lastModified();
	}
	public SFile(String path) {
		f=new java.io.File(path);
	}
	public SFile(java.io.File f) {
		this.f=f;
	}
	public SFile rel(String relPath) {
		return new SFile(new java.io.File(f,relPath));
	}
	public boolean exists() {
		return f.exists();
	}
	public String text() throws IOException {
		if (!exists()) return null;
		BufferedReader rd=new BufferedReader(new InputStreamReader(new FileInputStream(f),"utf-8"));// FileReader(f));
		StringBuffer buf=new StringBuffer();
		String sep="";
		while (true) {
			String line=rd.readLine();
			if (line==null) break;
			buf.append(sep+line);
			sep="\n";
		}
		rd.close();
		return buf.toString();
	}
	public void text(String content) throws FileNotFoundException {
		File parentFile = f.getParentFile();
		if (parentFile!=null) parentFile.mkdirs();
		PrintWriter p;
		try {
			p = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), "utf-8"));
			p.print(content);
			p.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isDir() {
		return f.exists() && f.isDirectory();
	}
	@Override
	public Iterator<SFile> iterator() {
		final java.io.File[] files;
		if (!isDir()) files=new java.io.File[0];
		else {
			files=f.listFiles();
		}
		
		return new Iterator<SFile>() {
			int i=0;
			@Override
			public boolean hasNext() {
				return i<files.length;
			}

			@Override
			public SFile next() {
				return new SFile(files[i++]);
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	public String name() {
		return f.getName();
	}
	public java.io.File javaIOFile() {
		return f;
	}
	public InputStream inputStream() throws FileNotFoundException {
		return new FileInputStream(f);
	}
	@Override
	public String toString() {
		return javaIOFile().toString();
	}
}

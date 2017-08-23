package com.geelun.framework.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtil {

	
	public static String getPictureRootDir() {
		return "/upload/picture";
	}

	public static void writeBytesToFile(byte[] bytes, File file) throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(bytes);
			out.flush();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public static void writeBytesToFile(byte[] bytes, String fullDiskPath) throws IOException {
		File file = new File(fullDiskPath);
		writeBytesToFile(bytes, file);
	}
}

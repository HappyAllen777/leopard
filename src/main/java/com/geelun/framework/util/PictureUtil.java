package com.geelun.framework.util;

import java.io.IOException;

public class PictureUtil {

	public static final String DEFAULT_PICTURE_SUBFIX = ".jpg";

	public static String getFileSubfixByFileName(String fileName) {
		if (!StringUtil.contains(fileName, ".")) {
			return DEFAULT_PICTURE_SUBFIX;
		}

		String subfix = fileName.substring(fileName.lastIndexOf("."));
		return subfix;
	}

	public static byte[] convertBase64DataToBytes(String base64Data) throws IOException {
		return null;
	}

}

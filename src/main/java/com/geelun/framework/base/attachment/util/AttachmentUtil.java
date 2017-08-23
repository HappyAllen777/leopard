package com.geelun.framework.base.attachment.util;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import com.geelun.framework.base.attachment.model.Attachment;
import com.geelun.framework.util.IDUtil;

public class AttachmentUtil {
	public static Attachment createAttachmentByFile(File file) {
		Timestamp now = new Timestamp(new Date().getTime());
		Attachment attachment = new Attachment();
		attachment.setCreateTime(now);
		attachment.setId(IDUtil.getUUID());
		attachment.setLastUpdateTime(now);
		attachment.setOtherInfo(null);
		attachment.setSize(file.length());
		return attachment;
	}
}

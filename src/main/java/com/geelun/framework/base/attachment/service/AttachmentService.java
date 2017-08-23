package com.geelun.framework.base.attachment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geelun.framework.base.attachment.dao.AttachmentDao;
import com.geelun.framework.base.attachment.model.Attachment;
import com.geelun.framework.base.superclass.dao.BaseModelDao;
import com.geelun.framework.base.superclass.service.BaseModelService;

@Service
public class AttachmentService extends BaseModelService<Attachment> {
	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public BaseModelDao getDao() {
		return attachmentDao;
	}

}

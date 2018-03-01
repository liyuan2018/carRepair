package com.cys.service;

import com.cys.model.SysAttachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by liyuan on 2018/2/28.
 */
public interface ISysAttachmentService extends IBaseService<SysAttachment,String>{

    SysAttachment upload(SysAttachment sysAttachment, MultipartFile mFile);

    SysAttachment upload(SysAttachment sysAttachment, MultipartFile mFile, boolean isConvert2PDF4Preview);

    List<SysAttachment> upload(String module, String subModule, MultipartFile[] multipartFiles);

}

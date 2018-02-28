package com.cys.service.impl;

import com.cys.constants.HardCode;
import com.cys.constants.ErrorCode;
import com.cys.exception.BusinessException;
import com.cys.model.SysAttachment;
import com.cys.service.IStorageService;
import com.cys.service.ISysAttachmentService;
import com.cys.util.SessionUtils;
import com.cys.util.UUIDUtils;
import com.google.common.eventbus.AsyncEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liyuan on 2018/2/28.
 */
@Service("sysAttachmentService")
public class SysAttachmentServiceImpl extends
        BaseServiceImpl<SysAttachment, String> implements ISysAttachmentService {

    private Logger logger = LoggerFactory
            .getLogger(SysAttachmentServiceImpl.class);
    /** 缓冲区大小 */
    private final int BUFFER_SIZE = 4096;

    private final String POINT = HardCode.Separator.POINT.toString();
    private final String SLASH = File.separator;
    private final String DATE_FORMAT = "yyyy" + File.separator + "MM" + File.separator + "dd";

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Autowired
    private IStorageService storageService;

    @Override
    public SysAttachment upload(SysAttachment sysAttachment, MultipartFile mFile) {
        return upload(sysAttachment, mFile, true);
    }

    @Override
    public SysAttachment upload(SysAttachment sysAttachment, MultipartFile mFile, boolean isConvert2PDF4Preview) {
        String originalFilename = mFile.getOriginalFilename(); // 上传的文件名称
        String originalFileNameNoSuffix = originalFilename;
        String suffix = null;
        String path = null;
        String uploadPath = HardCode.UPLOAD_PATH;
        logger.debug("environment uploadPath:"+uploadPath);

        if(originalFilename.contains(POINT)){
            originalFileNameNoSuffix = originalFilename.substring(0,
                    originalFilename.lastIndexOf(POINT));// 上传的文件名称去除后缀名
            suffix = originalFilename.substring(originalFilename
                    .lastIndexOf(POINT) + 1);// 文件的后缀名
            // 上传的路径形如/upload/2016/03/28/ProjectPlanning/DeView/UUID.suffix
            path = uploadPath + SLASH
                    + new SimpleDateFormat(DATE_FORMAT).format(new Date()) + SLASH
                    + sysAttachment.getModule() + SLASH
                    + sysAttachment.getSubModule() + SLASH
                    + UUIDUtils.generate() + POINT + suffix; // 文件在服务器保存的路径
        }
        else{
            path =  uploadPath + SLASH
                    + new SimpleDateFormat(DATE_FORMAT).format(new Date()) + SLASH
                    + sysAttachment.getModule() + SLASH
                    + sysAttachment.getSubModule() + SLASH
                    + UUIDUtils.generate();
        }
        path = path.replace("\\\\", File.separator).replace("/", File.separator);
        logger.debug(path);
        try {
            storageService.upload(path, mFile);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new BusinessException(ErrorCode.Common.uploadFailed); // 文件写入磁盘失败
        }
        sysAttachment.setName(originalFileNameNoSuffix);
        sysAttachment.setType(suffix);
        sysAttachment.setPath(path);
        sysAttachment.setSize(mFile.getSize());
        sysAttachment.setCreatorId(SessionUtils.getCurrentUserId());
        sysAttachment.setCreatedTime(new Date());
        SysAttachment rs = create(sysAttachment);
        if (isConvert2PDF4Preview) {
            asyncEventBus.post(rs);
        }
        return rs;
    }

    @Override
    public List<SysAttachment> upload(String module, String subModule, MultipartFile[] multipartFiles) {
        List<SysAttachment> sysAttachments = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            SysAttachment sysAttachment = new SysAttachment();
            sysAttachment.setModule(module);
            sysAttachment.setSubModule(subModule);
            upload(sysAttachment, multipartFile);
            sysAttachments.add(sysAttachment);
        }
        return sysAttachments;
    }
}

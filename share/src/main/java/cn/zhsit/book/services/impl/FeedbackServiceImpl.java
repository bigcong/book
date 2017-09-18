package cn.zhsit.book.services.impl;

import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.book.managers.FeedbackManager;
import cn.zhsit.book.models.po.Feedback;
import cn.zhsit.book.models.vo.FeedbackReq;
import cn.zhsit.book.services.FeedbackService;
import cn.zhsit.common.configs.ZhsConfig;
import cn.zhsit.common.enums.ContainFileEnum;
import cn.zhsit.common.enums.FileType;
import cn.zhsit.common.enums.ServiceNameEnum;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.helpers.ZhsFileHelper;
import cn.zhsit.common.utils.ZhsFileUtils;
import cn.zhsit.common.utils.ZhsOrderNumUtil;
import cn.zhsit.common.utils.ZhsUnique;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class FeedbackServiceImpl implements FeedbackService {
    private static Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class);
    @Autowired
    private FeedbackManager feedbackManager;
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private ZhsConfig zhsConfig;

    @Override
    public boolean createFeedback(FeedbackReq feedbackReq, Errors errors, MultipartFile[] imgFiles) throws Exception {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Date current = Calendar.getInstance().getTime();
        String feedbackId = ZhsUnique.unique25();
        long baseOrderNum = ZhsOrderNumUtil.currentBaseNum();
        int start = 0;
        List<ZhsFileGeneral> fileGeneralList = new ArrayList<>();
        if (null == imgFiles || imgFiles.length < 1) {
        } else {
            for (int i = 0; i < imgFiles.length; i++) {
                MultipartFile mf = imgFiles[i];
                if (mf.getBytes() == null || mf.getBytes().length < 1 ) {
                    continue;
                }
                ZhsFileGeneral f = new ZhsFileGeneral();
                f.setServiceId(feedbackId);
                f.setServiceName(ServiceNameEnum.Feedback.getService());
                f.setCreateTime(current);
                f.setModifyTime(current);
                long orderNum=baseOrderNum + start++;
                boolean writeFileSuccess = ZhsFileHelper.addFile(zhsConfig.getStore(),f,FileType.Feedback,mf,orderNum);
                if (writeFileSuccess) {
                    fileGeneralList.add(f);
                }
            }
        }

        Feedback fb = new Feedback();
        fb.setId(feedbackId);
        fb.setPersonId(session.getPersonId());
        fb.setContent(feedbackReq.getContent());
        fb.setTel(feedbackReq.getTel());
        fb.setCreateTime(current);
        fb.setModifyTime(current);
        return feedbackManager.insert(fb, fileGeneralList) == 1;
    }
}

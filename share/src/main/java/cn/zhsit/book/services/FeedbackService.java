package cn.zhsit.book.services;

import cn.zhsit.book.models.vo.FeedbackReq;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;


public interface FeedbackService {
    boolean createFeedback(FeedbackReq feedbackReq, Errors errors, MultipartFile[] imgFiles)  throws Exception;
}

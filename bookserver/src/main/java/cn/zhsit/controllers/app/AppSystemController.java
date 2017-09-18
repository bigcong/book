package cn.zhsit.controllers.app;

import cn.zhsit.authority.annotations.Login;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.authority.models.vo.UserResp;
import cn.zhsit.authority.services.PersonAuthorityService;
import cn.zhsit.book.models.vo.*;
import cn.zhsit.book.services.BooksUploadedService;
import cn.zhsit.book.services.CollectingService;
import cn.zhsit.book.services.FeedbackService;
import cn.zhsit.common.configs.ZhsConfig;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.utils.ZhsJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("m")
public class AppSystemController {
    private static Logger log = LoggerFactory.getLogger(AppSystemController.class);
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private BooksUploadedService booksUploadedService;
    @Autowired
    private CollectingService collectingService;
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private ZhsConfig zhsConfig;
    @Autowired
    private PersonAuthorityService personAuthorityService;

    @RequestMapping("/index")
    public String toIndex(Model model) {
        int pageSize = 3;
        List<MyBooksListReq> bookList = booksUploadedService.findSuggestList(pageSize);
        List<CollectionReq> hotList = collectingService.findHotList(pageSize);
        model.addAttribute("bookList", bookList);
        model.addAttribute("hotList", hotList);
        return "app/index";
    }



    @RequestMapping("/toaboutus")
    @Login
    public String toAboutUs() {
        return "app/关于我们";
    }

    @Login
    @RequestMapping("/tosetting")
    public String toSetting() {
        return "app/设置";
    }


    @Login
    @RequestMapping(value = "/tofeedback")
    public String toFeedback(@ModelAttribute("feedback") FeedbackReq feedbackReq) {
        return "app/意见反馈";
    }

    @Login
    @RequestMapping(value = "/feedback", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String feedback(@Valid @ModelAttribute("feedback") FeedbackReq feedbackReq, Errors errors, @RequestParam(name = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {
        if (errors.hasErrors()) {
            return "app/意见反馈";
        }

        boolean success = feedbackService.createFeedback(feedbackReq, errors, imgFiles);
        if (success) {
            return "redirect:/m/tosetting";
        }
        return "app/意见反馈";
    }

}


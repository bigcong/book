package cn.zhsit.controllers.pc;

import cn.zhsit.authority.annotations.Login;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.services.PersonAuthorityService;
import cn.zhsit.book.models.vo.CollectionReq;
import cn.zhsit.book.models.vo.FeedbackReq;
import cn.zhsit.book.models.vo.MyBooksListReq;
import cn.zhsit.book.services.BooksUploadedService;
import cn.zhsit.book.services.CollectingService;
import cn.zhsit.book.services.FeedbackService;
import cn.zhsit.common.configs.ZhsConfig;
import cn.zhsit.common.utils.page.Page;
import cn.zhsit.controllers.app.AppSystemController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("p")
public class PcSystemController {
    private static Logger log = LoggerFactory.getLogger(PcSystemController.class);
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



    @RequestMapping("/index0")
    public String toInndexNoRegister(Model model, @ModelAttribute("page") Page page){
        int pageSize = 6;
        List<MyBooksListReq> bookList = booksUploadedService.findSuggestList(pageSize);
        List<CollectionReq> hotList = collectingService.findHotList(pageSize);
        model.addAttribute("bookList", bookList);
        model.addAttribute("hotList", hotList);
        return "pc/首页--未注册";
    }


    @RequestMapping("/index")
    @Login
    public String toIndex(Model model, @ModelAttribute("page") Page page){
        int pageSize = 6;
        List<MyBooksListReq> bookList = booksUploadedService.findSuggestList(pageSize);
        List<CollectionReq> hotList = collectingService.findHotList(pageSize);
        model.addAttribute("bookList", bookList);
        model.addAttribute("hotList", hotList);
        return "pc/index";
    }
//
//    @RequestMapping("/tomybooks")
//    public String toMyBooks(){
//        return "pc/我上传的书籍";
//    }


    @RequestMapping(value = "/tofeedback")
    @Login
    public String toFeedback(Model model,@ModelAttribute("feedback") FeedbackReq feedbackReq) {
        model.addAttribute("rstatus", false);
        return "pc/意见反馈";
    }


    @Login
    @RequestMapping(value = "/feedback", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String feedback(Model model,@Valid @ModelAttribute("feedback") FeedbackReq feedbackReq, Errors errors, @RequestParam(name = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {
        model.addAttribute("rstatus", false);
        if (errors.hasErrors()) {
            return "pc/意见反馈";
        }

        boolean success = feedbackService.createFeedback(feedbackReq, errors, imgFiles);
        if (success) {
            model.addAttribute("rstatus", true);
            model.addAttribute("rmsg", "反馈成功！");
             return "pc/意见反馈";
        }
        return "pc/意见反馈";
    }
}

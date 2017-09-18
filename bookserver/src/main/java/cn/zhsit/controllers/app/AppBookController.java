package cn.zhsit.controllers.app;

import cn.zhsit.authority.annotations.Login;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.authority.models.vo.UserResp;
import cn.zhsit.authority.services.PersonAuthorityService;
import cn.zhsit.book.models.vo.BookDesiredReq;
import cn.zhsit.book.models.vo.CollectionResp;
import cn.zhsit.book.models.vo.MyBooksAddReq;
import cn.zhsit.book.services.BookDesiredService;
import cn.zhsit.book.services.BooksUploadedService;
import cn.zhsit.book.services.CollectingService;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.utils.ZhsJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("m/book")
public class AppBookController {
    @Autowired
    private CollectingService collectingService;
    @Autowired
    private BooksUploadedService booksUploadedService;
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private PersonAuthorityService personAuthorityService;
    @Autowired
    private BookDesiredService bookDesiredService;


    @RequestMapping("/bookcollect/{bookId}")
    @ResponseBody
    @Login
    public CollectionResp bookCollect(@PathVariable("bookId") String bookId) {
        CollectionResp resp = new CollectionResp();
        boolean success = collectingService.collect(bookId, resp);
        if (success) {
            resp.setRstatus(true);
        }
        return resp;
    }



    @RequestMapping("/bookdetail/{bookId}")
    @Login
    public String bookDetail(Model model, @PathVariable("bookId") String bookId) {
        MyBooksAddReq req = booksUploadedService.findById(bookId);
        model.addAttribute("book", req);
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        String personId = session.getPersonId();
        if (null != personId) {
            UserResp user = personAuthorityService.findCurrentUser();
            req.setAuthStatus(user.getAuthStatus());
            req.setMobile(user.getMobile());
            boolean exits = collectingService.exists(bookId, personId);
            model.addAttribute("collect", exits);
        }
        return "app/图书详情";
    }


    /**
     * 跳往心愿书单页面
     *
     * @return
     */
    @RequestMapping("/tobooksdesired/{bookId}")
    public String toBooksDesired(Model model, @ModelAttribute @PathVariable String bookId) {
        MyBooksAddReq booksAddReq = booksUploadedService.findById(bookId);
        List<BookDesiredReq> desiredList = bookDesiredService.findByPersonId(booksAddReq.getPersonId());
        model.addAttribute("desiredList", desiredList);
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        boolean exits = collectingService.exists(bookId, session.getPersonId());
        model.addAttribute("collect", exits);
        MyBooksAddReq req = booksUploadedService.findById(bookId);
        model.addAttribute("book", req);


        return "app/心愿书单";
    }
}

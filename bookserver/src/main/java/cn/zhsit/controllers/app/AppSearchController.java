package cn.zhsit.controllers.app;

import cn.zhsit.authority.enums.SearchType;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.book.models.vo.BookResp;
import cn.zhsit.book.models.vo.MyBooksListReq;
import cn.zhsit.book.models.vo.SearchHisResp;
import cn.zhsit.book.services.BookDesiredService;
import cn.zhsit.book.services.BooksUploadedService;
import cn.zhsit.book.services.SearchHisService;
import cn.zhsit.common.handlers.ZhsContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("m/search")
public class AppSearchController {
    @Autowired
    private BooksUploadedService booksUploadedService;
    @Autowired
    private SearchHisService searchHisService;
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private BookDesiredService bookDesiredService;

    /**
     * 跳往特色搜索页面
     *
     * @return
     */
    @RequestMapping("/tosearch")
    public String toSearch(Model model) {
        int pageSize = 1000;
        List<MyBooksListReq> bookList = booksUploadedService.findSuggestList(pageSize);
        model.addAttribute("bookList", bookList);
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        List<SearchHisResp> searchHis = null;
        if (session.getPersonId() != null) {
            searchHis = searchHisService.findHisByPerson(session.getPersonId());
        }
        model.addAttribute("searchHis", searchHis);
        return "app/特色搜索";
    }

    /**
     * 跳往特色页面
     *
     * @return
     */
    @RequestMapping("/search")
    public String search(Model model,@ModelAttribute("searchKey")String searchKey,@ModelAttribute("searchType") String searchType) {
        List<BookResp> bookList=null;
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        if (session.getPersonId() != null) {
              searchHisService.createSearchHis(session.getPersonId(),searchKey);
        }
        if(SearchType.JiaoHuan.getCode().equals(searchType)){
            bookList=booksUploadedService.findByName(searchKey,null);
            model.addAttribute("bookList", bookList);
            return "app/搜索结果";
        }else if(SearchType.XinYuan.getCode().equals(searchType)) {
            bookList= bookDesiredService.findByName(searchKey,null);
            model.addAttribute("bookList", bookList);
            return "app/搜索结果--心愿";
        }
        model.addAttribute("bookList", bookList);
        return "app/搜索结果";
    }

    @RequestMapping("/delsearchhis")
    @ResponseBody
    public String delSearchHis(){
        boolean success=false;
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        if (session.getPersonId() != null) {
              success = searchHisService.delSearchHis(session.getPersonId());
        }
        return success ? "ok" : "fail";
    }

}

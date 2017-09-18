package cn.zhsit.controllers.pc;

import cn.zhsit.authority.annotations.Login;
import cn.zhsit.authority.enums.SearchType;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.book.models.vo.BookResp;
import cn.zhsit.book.services.BookDesiredService;
import cn.zhsit.book.services.BooksUploadedService;
import cn.zhsit.book.services.SearchHisService;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.utils.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("p/search")
@Login
public class PcSearchController {
    @Autowired
    private BooksUploadedService booksUploadedService;
    @Autowired
    private SearchHisService searchHisService;
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private BookDesiredService bookDesiredService;

    /**
     * 跳往交互书籍搜索页面
     *
     * @return
     */
//    @RequestMapping("/search")
    @PostMapping("/search")
    public String search(
            Model model,
            @ModelAttribute("searchJiaoHuanKey") String searchJiaoHuanKey,
            @ModelAttribute("searchXinYuanKey") String searchXinYuanKey,
            @ModelAttribute("searchType") String searchType,
            @ModelAttribute("page") Page page
    ) {
        page.setShowPageSize(8);
        List<BookResp> bookList = null;
        String searchKey = null;
        String toPage = null;
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        if (session.getPersonId() != null) {
            searchHisService.createSearchHis(session.getPersonId(), searchJiaoHuanKey);
        }
        if (SearchType.JiaoHuan.getCode().equals(searchType)) {
            searchKey = searchJiaoHuanKey;
            toPage = "pc/首页--搜索";
            page.setRows(6);
            bookList = booksUploadedService.findByName(searchJiaoHuanKey, page);
            model.addAttribute("bookList", bookList);
        } else if (SearchType.XinYuan.getCode().equals(searchType)) {
            searchKey = searchXinYuanKey;
            toPage = "pc/首页--搜索(心愿书单)";
            page.setRows(12);
            bookList = bookDesiredService.findByNamePerson(session.getPersonId(),searchXinYuanKey, page);
            List<BookResp> bookList1 = new ArrayList<>();
            List<BookResp> bookList2 = new ArrayList<>();
            List<BookResp> bookList3 = new ArrayList<>();
            List<BookResp> bookList4 = new ArrayList<>();
            if (bookList.size() > 0) {
                for (int i = 0; i < bookList.size(); i++) {
                    if (i>=0&&i<3) {
                        BookResp book = bookList.get(i);
                        bookList1.add(book);
                    }else if(i>=3 && i<6) {
                        BookResp book = bookList.get(i);
                        bookList2.add(book);
                    }else if(i >=6 && i<9) {
                        BookResp book = bookList.get(i);
                        bookList3.add(book);
                    }else if(i >=9&& i<12) {
                        BookResp book = bookList.get(i);
                        bookList4.add(book);
                    }
                }
            }
            model.addAttribute("bookList", bookList1);
            model.addAttribute("bookList2", bookList2);
            model.addAttribute("bookList3", bookList3);
            model.addAttribute("bookList4", bookList4);
        }
        model.addAttribute("searchKey", searchKey);
        return toPage;
    }
}

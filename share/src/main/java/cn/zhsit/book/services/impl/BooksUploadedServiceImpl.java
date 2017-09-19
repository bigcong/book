package cn.zhsit.book.services.impl;

import cn.zhsit.authority.api.models.ConstantsAuthority;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.book.daos.BooksUploadedMapper;
import cn.zhsit.book.managers.BooksUploadedManager;
import cn.zhsit.book.models.po.BooksUploaded;
import cn.zhsit.book.models.po.BooksUploadedExample;
import cn.zhsit.book.models.vo.BookResp;
import cn.zhsit.book.models.vo.MyBooksAddReq;
import cn.zhsit.book.models.vo.MyBooksListReq;
import cn.zhsit.book.services.BooksUploadedService;
import cn.zhsit.common.configs.ZhsConfig;
import cn.zhsit.common.enums.FileType;
import cn.zhsit.common.enums.ServiceNameEnum;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.helpers.ZhsFileHelper;
import cn.zhsit.common.utils.ZhsOrderNumUtil;
import cn.zhsit.common.utils.ZhsUnique;
import cn.zhsit.common.utils.page.Page;
import cn.zhsit.generator.manager.ZhsFileGeneralManager;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class BooksUploadedServiceImpl implements BooksUploadedService {
    private static Logger log = LoggerFactory.getLogger(BooksUploadedServiceImpl.class);
    @Autowired
    private BooksUploadedManager booksUploadedManager;

    @Autowired
    BooksUploadedMapper booksUploadedMapper;

    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private ZhsConfig zhsConfig;
    @Autowired
    private ZhsFileGeneralManager zhsFileGeneralManager;

    @Override
    public boolean createBook(MyBooksAddReq req, Errors errors, MultipartFile[] imgFiles) throws Exception {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Date current = Calendar.getInstance().getTime();
//        ContainFileEnum containFile = ContainFileEnum.NoContain;
        String id = ZhsUnique.unique25();
        long baseOrderNum = ZhsOrderNumUtil.currentBaseNum();
        int start = 0;
        List<ZhsFileGeneral> fileGeneralList = new ArrayList<>();
        if (null == imgFiles || imgFiles.length < 1) {
        } else {
            for (int i = 0; i < imgFiles.length; i++) {
                MultipartFile mf = imgFiles[i];
                if (mf.getBytes() == null || mf.getBytes().length < 1) {
                    continue;
                }
                ZhsFileGeneral f = new ZhsFileGeneral();
                f.setServiceId(id);
                f.setServiceName(ServiceNameEnum.BooksUploaded.getService());
                f.setCreateTime(current);
                f.setModifyTime(current);
                long orderNum = baseOrderNum + start++;
                boolean writeFileSuccess = ZhsFileHelper.addFile(zhsConfig.getStore(), f, FileType.Book, mf, orderNum);
                if (writeFileSuccess) {
                    fileGeneralList.add(f);
//                    containFile = ContainFileEnum.Contain;
                }
            }
        }


        BooksUploaded bu = new BooksUploaded();
        bu.setId(id);
        bu.setPersonId(session.getPersonId());
        bu.setName(req.getName());
        bu.setAuthor(req.getAuthor());
        bu.setDescr(req.getDescr());
        bu.setIsbn(req.getIsbn());
        bu.setPublishers(req.getPublishers());
        bu.setPages(req.getPages());
//        bu.setContainFile(containFile.getType());
        bu.setCreateTime(current);
        bu.setModifyTime(current);
        bu.setArea(req.getArea());


        return booksUploadedManager.insert(bu, fileGeneralList);
    }

    @Override
    public List<MyBooksListReq> findByPersonId(String personId) {
        List<MyBooksListReq> list = new ArrayList<>();
        List<BooksUploaded> poList = booksUploadedManager.selectByPersonId(personId);
        for (BooksUploaded po : poList) {
            MyBooksListReq req = new MyBooksListReq();
            list.add(req);
            BeanUtils.copyProperties(po, req);
            ZhsFileGeneral f = zhsFileGeneralManager.selectLastByServiceId(po.getId());
            if (null != f) {
                req.setPath(f.getLocation() + "/" + f.getThumbnail());
            }
        }
        return list;
    }

    @Override
    public List<MyBooksListReq> findByPersonId(String personId, Page page) {
//        List<BookResp> respList = new ArrayList<>();
//        BooksUploadedExample sql=new BooksUploadedExample();
//        sql.createCriteria().andNameLike( "%"+searchKey+"%" );
//        if(null!=page){
//            sql.setPage(page);
//            Long total= booksUploadedManager.count(sql);
//            page.setTotal(total);
//        }
//        List<BooksUploaded>  poList= booksUploadedManager.select(sql);
//        for (BooksUploaded po : poList) {
//            BookResp resp=new BookResp();
//            respList.add(resp);
//            BeanUtils.copyProperties(po,resp);
//            ZhsFileGeneral file= zhsFileGeneralManager.selectLastByServiceId(po.getId());
//            if(file!=null){
//                resp.setPath(file.getLocation()+"/"+file.getThumbnail());
//            }
//        }
//        return respList;
        BooksUploadedExample sql = new BooksUploadedExample();
        sql.createCriteria().andPersonIdEqualTo(personId);
        if (null != page) {
            sql.setPage(page);
            Long total = booksUploadedManager.count(sql);
            page.setTotal(total);
        }
        sql.setOrderByClause(" create_time desc ");
        List<MyBooksListReq> list = new ArrayList<>();
        List<BooksUploaded> poList = booksUploadedManager.select(sql);
        for (BooksUploaded po : poList) {
            MyBooksListReq req = new MyBooksListReq();
            list.add(req);
            BeanUtils.copyProperties(po, req);
            ZhsFileGeneral f = zhsFileGeneralManager.selectLastByServiceId(po.getId());
            if (null != f) {
                req.setPath(f.getLocation() + "/" + f.getThumbnail());
            }
        }
        return list;
    }

    @Override
    public boolean delBookWithPersonId(String personId, String bookId) {
        return booksUploadedManager.del(personId, bookId);
    }

    @Override
    public boolean modifyBook(MyBooksAddReq req, Errors errors, MultipartFile[] imgFiles) throws Exception {
        Date current = Calendar.getInstance().getTime();
        String id = req.getId();
        long baseOrderNum = ZhsOrderNumUtil.currentBaseNum();
        int start = 0;
        List<ZhsFileGeneral> addFileGeneralList = new ArrayList<>();
        if (null == imgFiles || imgFiles.length < 1) {
        } else {
            for (int i = 0; i < imgFiles.length; i++) {
                MultipartFile mf = imgFiles[i];
                if (mf.getBytes() == null || mf.getBytes().length < 1) {
                    continue;
                }
                ZhsFileGeneral f = new ZhsFileGeneral();
                f.setServiceId(id);
                f.setServiceName(ServiceNameEnum.BooksUploaded.getService());
                f.setCreateTime(current);
                f.setModifyTime(current);
                long orderNum = baseOrderNum + start++;
                boolean writeFileSuccess = ZhsFileHelper.addFile(zhsConfig.getStore(), f, FileType.Book, mf, orderNum);
                if (writeFileSuccess) {
                    addFileGeneralList.add(f);
                }
            }
        }
        BooksUploaded book = new BooksUploaded();
        book.setId(req.getId());
        book.setName(req.getName());
        book.setPages(req.getPages());
        book.setPublishers(req.getPublishers());
        book.setIsbn(req.getIsbn());
        book.setAuthor(req.getAuthor());
        book.setDescr(req.getDescr());
        book.setModifyTime(current);
        boolean success = booksUploadedManager.modifyById(book, addFileGeneralList);
        return success;
    }

    @Override
    public MyBooksAddReq findById(String bookId) {
        MyBooksAddReq req = new MyBooksAddReq();
        BooksUploaded book = booksUploadedManager.selectByKey(bookId);
        List<ZhsFileGeneral> fileGeneralList = zhsFileGeneralManager.selectByServiceId(bookId);
        BeanUtils.copyProperties(book, req);
        req.addFileReqList(fileGeneralList);
        return req;
    }

    @Override
    @Cacheable(value = ConstantsAuthority.CacheKey.SuggestHotBookCacheName
            , key = "#pageSize+'_" + ConstantsAuthority.CacheKey.SuggestList + "'", unless = "#result.size() == 0")
    public List<MyBooksListReq> findSuggestList(int pageSize) {
        List<MyBooksListReq> reqList = new ArrayList<>();
        BooksUploadedExample query = new BooksUploadedExample();
        query.isDistinct();
        query.setPage(new Page().setRows(pageSize).setPage(0));
        query.setOrderByClause("create_time desc");
        List<BooksUploaded> list = booksUploadedManager.select(query);
        for (BooksUploaded po : list) {
            MyBooksListReq req = new MyBooksListReq();
            reqList.add(req);
            BeanUtils.copyProperties(po, req);
            ZhsFileGeneral f = zhsFileGeneralManager.selectLastByServiceId(po.getId());
            if (null != f) {
                req.setPath(f.getLocation() + "/" + f.getThumbnail());
            }
        }
        return reqList;
    }

    //        根据imgid查询Img对应的serviceid，根据serviceid查询对应的用户，对比当前登录人就是这个用户
    @Override
    public boolean delImg(String imgId) {
        if ("undefined".equalsIgnoreCase(imgId)) {
            return true;
        }
        ZhsFileGeneral zhsFileGeneral = zhsFileGeneralManager.selectByKey(imgId);
        String bookId = zhsFileGeneral.getServiceId();
        BooksUploaded booksUploaded = booksUploadedManager.selectByKey(bookId);
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        if (booksUploaded.getPersonId().equals(session.getPersonId())) {
            int num = zhsFileGeneralManager.delByKey(imgId);
            return true;
        }
        return false;
    }

    @Override
    public List<BookResp> findByName(String searchKey, Page page) {
        List<BookResp> respList = new ArrayList<>();
        BooksUploadedExample sql = new BooksUploadedExample();
        BooksUploaded serach = new BooksUploaded();
        serach.setName(searchKey);
        serach.setArea(searchKey);

        if (null != page) {
            sql.setPage(page);


            Long total = booksUploadedMapper.countByName(serach);
            page.setTotal(total);
        }
        List<BooksUploaded> poList = booksUploadedMapper.searchByName(serach);
        for (BooksUploaded po : poList) {
            BookResp resp = new BookResp();
            respList.add(resp);
            BeanUtils.copyProperties(po, resp);
            ZhsFileGeneral file = zhsFileGeneralManager.selectLastByServiceId(po.getId());
            if (file != null) {
                resp.setPath(file.getLocation() + "/" + file.getThumbnail());
            }
        }
        return respList;
    }
}

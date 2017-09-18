package cn.zhsit.book.services.impl;

import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.book.managers.BookDesiredManager;
import cn.zhsit.book.models.po.BookDesired;
import cn.zhsit.book.models.po.BookDesiredExample;
import cn.zhsit.book.models.vo.BookDesiredReq;
import cn.zhsit.book.models.vo.BookResp;
import cn.zhsit.book.services.BookDesiredService;
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
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class BookDesiredServiceImpl implements BookDesiredService {
    private static Logger log = LoggerFactory.getLogger(BookDesiredServiceImpl.class);

    @Autowired
    private BookDesiredManager bookDesiredManager;
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private ZhsConfig zhsConfig;
    @Autowired
    private ZhsFileGeneralManager zhsFileGeneralManager;

    @Override
    public boolean createDesire(BookDesiredReq req, Errors errors, MultipartFile[] imgFiles) throws Exception {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Date current = Calendar.getInstance().getTime();
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
                f.setServiceName(ServiceNameEnum.BookDesired.getService());
                f.setCreateTime(current);
                f.setModifyTime(current);
                long orderNum = baseOrderNum + start++;
                boolean writeFileSuccess = ZhsFileHelper.addFile(zhsConfig.getStore(), f, FileType.Book, mf, orderNum);
                if (writeFileSuccess) {
                    fileGeneralList.add(f);
                }
            }
        }

        BookDesired bu = new BookDesired();
        bu.setId(id);
        bu.setPersonId(session.getPersonId());
        bu.setName(req.getName());
        bu.setAuthor(req.getAuthor());
        bu.setCreateTime(current);
        bu.setModifyTime(current);
        return bookDesiredManager.insert(bu, fileGeneralList);
    }

    @Override
    public List<BookDesiredReq> findByPersonId(String personId) {
        List<BookDesiredReq> reqList = new ArrayList();
        BookDesiredExample querySql = new BookDesiredExample();
        querySql.createCriteria().andPersonIdEqualTo(personId);
        querySql.setOrderByClause(" create_time desc");
        List<BookDesired> list = bookDesiredManager.select(querySql);
        for (BookDesired po : list) {
            BookDesiredReq req = new BookDesiredReq();
            reqList.add(req);
            BeanUtils.copyProperties(po, req);
            ZhsFileGeneral f = zhsFileGeneralManager.selectLastByServiceId(po.getId());
            if(null!=f) {
                req.setPath(f.getLocation() + "/" + f.getThumbnail());
            }
        }
        return reqList;
    }

    @Override
    public List<BookDesiredReq> findByPersonId(String personId, Page page) {
        List<BookDesiredReq> reqList = new ArrayList();
        BookDesiredExample sql = new BookDesiredExample();
        sql.createCriteria().andPersonIdEqualTo(personId);
        sql.setOrderByClause(" create_time desc");

        if(null!=page){
            sql.setPage(page);
            Long total= bookDesiredManager.count(sql);
            page.setTotal(total);
        }
        List<BookDesired> list = bookDesiredManager.select(sql);
        for (BookDesired po : list) {
            BookDesiredReq req = new BookDesiredReq();
            reqList.add(req);
            BeanUtils.copyProperties(po, req);
            ZhsFileGeneral f = zhsFileGeneralManager.selectLastByServiceId(po.getId());
            if(null!=f) {
                req.setPath(f.getLocation() + "/" + f.getThumbnail());
            }
        }
        return reqList;
    }

    @Override
    public boolean del(String id) {
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        return bookDesiredManager.del(session.getPersonId(), id);
    }

    @Override
    public List<BookResp> findByName(String searchKey,Page page) {
        List<BookResp> respList = new ArrayList<>();
        BookDesiredExample sql = new BookDesiredExample();
        sql.createCriteria().andNameLike("%" + searchKey + "%");
        if (null != page) {
            sql.setPage(page);
            Long total=bookDesiredManager.count(sql);
            page.setTotal(total);
        }

        List<BookDesired> poList = bookDesiredManager.select(sql);
        for (BookDesired po : poList) {
            BookResp resp = new BookResp();
            respList.add(resp);
            BeanUtils.copyProperties(po, resp);
            ZhsFileGeneral file = zhsFileGeneralManager.selectLastByServiceId(po.getId());
            if(null!=file) {
                resp.setPath(file.getLocation() + "/" + file.getThumbnail());
            }
        }
        return respList;
    }

    @Override
    public List<BookResp> findByNamePerson(String personId,String searchKey, Page page) {
        List<BookResp> respList = new ArrayList<>();
        BookDesiredExample sql = new BookDesiredExample();
        sql.createCriteria().andPersonIdEqualTo(personId).andNameLike("%" + searchKey + "%");
        if (null != page) {
            sql.setPage(page);
            Long total=bookDesiredManager.count(sql);
            page.setTotal(total);
        }

        List<BookDesired> poList = bookDesiredManager.select(sql);
        for (BookDesired po : poList) {
            BookResp resp = new BookResp();
            respList.add(resp);
            BeanUtils.copyProperties(po, resp);
            ZhsFileGeneral file = zhsFileGeneralManager.selectLastByServiceId(po.getId());
            if(null!=file) {
                resp.setPath(file.getLocation() + "/" + file.getThumbnail());
            }
        }
        return respList;
    }
}

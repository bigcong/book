package cn.zhsit.book.services.impl;

import cn.zhsit.authority.api.models.ConstantsAuthority;
import cn.zhsit.authority.helpers.CacheHelper;
import cn.zhsit.authority.interceptors.models.ZhsSession;
import cn.zhsit.book.managers.BooksUploadedManager;
import cn.zhsit.book.managers.CollectingManager;
import cn.zhsit.book.models.po.BooksUploaded;
import cn.zhsit.book.models.po.Collecting;
import cn.zhsit.book.models.po.CollectingExample;
import cn.zhsit.book.models.vo.CollectionReq;
import cn.zhsit.book.models.vo.CollectionResp;
import cn.zhsit.book.services.CollectingService;
import cn.zhsit.common.handlers.ZhsContextHandler;
import cn.zhsit.common.utils.ZhsUnique;
import cn.zhsit.common.utils.page.Page;
import cn.zhsit.generator.manager.ZhsFileGeneralManager;
import cn.zhsit.generator.models.po.ZhsFileGeneral;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class CollectingServiceImpl implements CollectingService {
    @Autowired
    private CollectingManager collectingManager;
    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private BooksUploadedManager booksUploadedManager;
    @Autowired
    private ZhsFileGeneralManager zhsFileGeneralManager;

    @Override
    public List<CollectionReq> findHotList(int pageSize) {
        List<CollectionReq> reqs = new ArrayList<>();
        List<Collecting> list = collectingManager.findHotList(new Page().setPage(0).setRows(pageSize));
        for (Collecting po : list) {
            CollectionReq req = new CollectionReq();
            BeanUtils.copyProperties(po, req);
            reqs.add(req);
            ZhsFileGeneral f = zhsFileGeneralManager.selectLastByServiceId(po.getBooksUploadedId());
            if (null != f) {
                req.setPath(f.getLocation() + "/" + f.getThumbnail());
            }
        }
        return reqs;
    }

    @Override
    public boolean exists(String bookId, String personId) {
        CollectingExample querySql = new CollectingExample();
        querySql.createCriteria().andPersonIdEqualTo(personId).andBooksUploadedIdEqualTo(bookId);
        return collectingManager.count(querySql) > 0;
    }

    @Override
    public boolean collect(String bookId, CollectionResp resp) {
        BooksUploaded book = booksUploadedManager.selectByKey(bookId);
        ZhsSession session = cacheHelper.getSession(ZhsContextHandler.instance.getSessionKey());
        Date current = Calendar.getInstance().getTime();
        Collecting collecting = new Collecting();
        collecting.setId(ZhsUnique.unique25());
        collecting.setPersonId(session.getPersonId());
        collecting.setBooksUploadedId(book.getId());
        collecting.setName(book.getName());
        collecting.setAuthor(book.getAuthor());
        collecting.setCreateTime(current);
        collecting.setModifyTime(current);
        try {
            return collectingManager.insert(collecting) == 1;
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                if (StringUtils.contains(e.getMessage(), ConstantsAuthority.index_union_collecting_person_book)) {
                    resp.setMsg("已经收藏");
                }
            } else {
                throw e;
            }
        }
        return false;
    }

    @Override
    public List<CollectionResp> findCollectingByPerson(String personId) {
        List<CollectionResp> respList = new ArrayList<>();
        CollectingExample sql = new CollectingExample();
        sql.createCriteria().andPersonIdEqualTo(personId);
        sql.setOrderByClause("create_time desc");
        List<Collecting>  list= collectingManager.select(sql);
        for(Collecting po:list){
            CollectionResp resp=new CollectionResp();
            respList.add(resp);
            BeanUtils.copyProperties(po,resp);
            ZhsFileGeneral f = zhsFileGeneralManager.selectLastByServiceId(po.getBooksUploadedId());
            if(null!=f){
                resp.setPath(f.getLocation()+"/"+f.getThumbnail());
            }
        }
        return respList;
    }

    @Override
    public List<CollectionResp> findCollectingByPerson(String personId, Page page) {
        List<CollectionResp> respList = new ArrayList<>();
        CollectingExample sql = new CollectingExample();
        sql.createCriteria().andPersonIdEqualTo(personId);
        sql.setOrderByClause("create_time desc");
        if(null!=page){
            sql.setPage(page);
            Long total = collectingManager.count(sql);
            page.setTotal(total);
        }
        List<Collecting>  list= collectingManager.select(sql);
        for(Collecting po:list){
            CollectionResp resp=new CollectionResp();
            respList.add(resp);
            BeanUtils.copyProperties(po,resp);
            ZhsFileGeneral f = zhsFileGeneralManager.selectLastByServiceId(po.getBooksUploadedId());
            if(null!=f){
                resp.setPath(f.getLocation()+"/"+f.getThumbnail());
            }
        }
        return respList;
    }

    @Override
    public boolean delWithPersonId(String personId, String collectingId) {
        CollectingExample sql = new CollectingExample();
        sql.createCriteria().andPersonIdEqualTo(personId).andIdEqualTo(collectingId);
        int num= collectingManager.del(sql);
        return true;
    }

}

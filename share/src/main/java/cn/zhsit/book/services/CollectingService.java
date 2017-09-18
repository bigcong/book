package cn.zhsit.book.services;

import cn.zhsit.book.models.vo.CollectionReq;
import cn.zhsit.book.models.vo.CollectionResp;
import cn.zhsit.common.utils.page.Page;

import java.util.List;


public interface CollectingService {
    List<CollectionReq> findHotList(int pageSize);

    /**
     * 是否存在符合条件的
     * @param bookId
     * @param personId
     * @return
     */
    boolean exists(String bookId, String personId);

    boolean collect(String bookId, CollectionResp resp);

    List<CollectionResp> findCollectingByPerson(String personId);
    List<CollectionResp> findCollectingByPerson(String personId, Page page);

    boolean delWithPersonId(String personId, String collectingId);
}

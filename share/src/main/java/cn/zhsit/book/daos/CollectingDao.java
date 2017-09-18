package cn.zhsit.book.daos;

import cn.zhsit.book.models.po.Collecting;
import cn.zhsit.common.utils.page.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CollectingDao {

    //    @Select("SELECT id,org_code,org_id,product_code,product_id from t_org_product " +
//            "where record_status=-1 and org_code=#{op.orgCode} and product_code=#{op.productCode} ORDER BY org_id ,product_id " +
//            "LIMIT #{n}")
//    @Results({
//            @Result(column = "org_code", property = "orgCode")
//            , @Result(column = "org_id", property = "orgId")
//            , @Result(column = "product_code", property = "productCode")
//            , @Result(column = "product_id", property = "productId")
//    })
    @Select("SELECT person_id,books_uploaded_id,name,author, count(books_uploaded_id) AS num " +
            "FROM t_collecting " +
            "GROUP BY books_uploaded_id,person_id,books_uploaded_id,NAME,author " +
            "ORDER BY num desc " +
            "LIMIT #{page.page},#{page.rows} "
    )
    @Results({
            @Result(column ="person_id",property = "personId")
            ,@Result(column ="books_uploaded_id",property = "booksUploadedId")
            ,@Result(column ="name",property = "name")
            ,@Result(column ="author",property = "author")
    })
    List<Collecting> selectByPage(@Param("page") Page page);

}
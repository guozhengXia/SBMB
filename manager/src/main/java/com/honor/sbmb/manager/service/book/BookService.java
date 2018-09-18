package com.honor.sbmb.manager.service.book;

import com.honor.sbmb.base.model.book.BookModel;

import java.util.List;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
public interface BookService {
    /**
     * 获取某一本书的详情
     *
     * @param id
     * @return
     */
    BookModel get(int id);

    /**
     * 获取书的列表
     *
     * @param bookModel
     * @return
     */
    List<BookModel> queryList(BookModel bookModel);

    /**
     * 添加或修改book
     *
     * @param bookModel
     */
    void save(BookModel bookModel);
}

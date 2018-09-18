package com.honor.sbmb.manager.service.book.impl;

import com.honor.sbmb.base.model.book.BookModel;
import com.honor.sbmb.base.service.book.BookBaseService;
import com.honor.sbmb.manager.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookBaseService bookBaseService;

    @Override
    public BookModel get(int id) {
        return bookBaseService.queryOne(id);
    }

    @Override
    public List<BookModel> queryList(BookModel bookModel) {
        return bookBaseService.queryList(bookModel);
    }

    @Override
    public void save(BookModel bookModel) {
        bookBaseService.save(bookModel);
    }
}

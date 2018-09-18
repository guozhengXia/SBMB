package com.honor.sbmb.base.service.book.impl;

import com.honor.sbmb.base.dao.base.BaseDao;
import com.honor.sbmb.base.dao.book.BookDao;
import com.honor.sbmb.base.model.book.BookModel;
import com.honor.sbmb.base.service.base.impl.BaseServiceImpl;
import com.honor.sbmb.base.service.book.BookBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
@Service
public class BookBaseServiceImpl extends BaseServiceImpl<BookModel> implements BookBaseService {

    @Autowired
    BookDao bookDao;

    @Override
    protected BaseDao<BookModel> getBaseDao() {
        return bookDao;
    }
}

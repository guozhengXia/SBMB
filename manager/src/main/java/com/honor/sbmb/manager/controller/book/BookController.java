package com.honor.sbmb.manager.controller.book;

import com.alibaba.fastjson.JSONObject;
import com.honor.sbmb.base.model.book.BookModel;
import com.honor.sbmb.manager.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xiagz
 * Date:2018/9/17
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    /**
     * 获取书的详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get.do", method = RequestMethod.GET)
    public Object get(@RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        BookModel bookModel = bookService.get(id);

        if (bookModel == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "数据不存在");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "success");
            jsonObject.put("data", bookModel);
        }
        return jsonObject;

    }

    /**
     * 获取书的列表
     *
     * @param bookModel
     * @return
     */
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public Object list(BookModel bookModel) {

        JSONObject jsonObject = new JSONObject();
        List<BookModel> bookModelList = bookService.queryList(new BookModel());

        if (bookModelList == null || bookModelList.size() == 0) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "数据不存在");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "success");
            jsonObject.put("data", bookModelList);
        }
        return jsonObject;
    }


    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public Object add(@RequestBody BookModel bookModel) {
        JSONObject jsonObject = new JSONObject();
        bookService.save(bookModel);

        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        return jsonObject;

    }



}

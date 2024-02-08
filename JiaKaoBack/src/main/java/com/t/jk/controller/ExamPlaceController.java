package com.t.jk.controller;


import com.baomidou.mybatisplus.extension.service.IService;
import com.t.jk.common.util.Rs;
import com.t.jk.pojo.po.ExamPlace;
import com.t.jk.pojo.query.ExamPlaceQuery;
import com.t.jk.pojo.result.R;
import com.t.jk.service.ExamPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examPlaces")
public class ExamPlaceController extends BaseController<ExamPlace> {

    @Autowired
    ExamPlaceService service;

    @GetMapping
    public R list(ExamPlaceQuery query) {
        service.list(query);
        return Rs.ok(query);
    }

    @Override
    protected IService<ExamPlace> getService() {
        return service;
    }
}


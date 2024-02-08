package com.t.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.t.jk.common.enhance.MpPage;
import com.t.jk.common.enhance.MpQueryWrapper;
import com.t.jk.mapper.ExamPlaceMapper;
import com.t.jk.pojo.po.ExamPlace;
import com.t.jk.pojo.query.ExamPlaceQuery;
import com.t.jk.service.ExamPlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("examPlaceService")
@Transactional
public class ExamPlaceServiceImpl extends ServiceImpl<ExamPlaceMapper, ExamPlace> implements ExamPlaceService {

    @Override
    @Transactional(readOnly = true)
    public void list(ExamPlaceQuery query) {

        MpQueryWrapper<ExamPlace> wrapper = new MpQueryWrapper<>();
        wrapper.like(query.getKeyword(), ExamPlace::getName, ExamPlace::getAddress);

        /**
         * 城市省份
         * 如果有城市了那么省份可有可无
         */
        Integer cityId = query.getCityId();
        Integer provinceId = query.getProvinceId();
        if (cityId != null && cityId > 0) {
            wrapper.eq(ExamPlace::getCityId, cityId);
        } else if (provinceId != null && provinceId > 0) {
            wrapper.eq(ExamPlace::getProvinceId, provinceId);
        }

        wrapper.orderByDesc(ExamPlace::getId);

        baseMapper.selectPage(new MpPage<>(query), wrapper).updateQuery();
    }
}


package com.gong.onlinetest.Service.impl;


import com.gong.onlinetest.Mapper.CoreMapper;
import com.gong.onlinetest.Pojo.Core;
import com.gong.onlinetest.Service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CoreServiceImpl implements CoreService {
    @Autowired
    private CoreMapper coreMapper;

    @Override
    public Core selectAllCore(Integer student_id) {
        Core core = new Core();
        core.setMaxCore(coreMapper.findMaxCore(student_id));
        core.setMinCore(coreMapper.findMinCore(student_id));
        core.setTotal(coreMapper.findAllNum(student_id));
        core.setBestCore(coreMapper.findBestCore(student_id));
        core.setMiddleCore(coreMapper.findMiddleCore(student_id));
        core.setBadCore(coreMapper.findBadCore(student_id));
        core.setAverageCore(coreMapper.findAvgCore(student_id));
        return core;
    }
}

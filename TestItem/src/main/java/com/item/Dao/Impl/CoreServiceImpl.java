package com.item.Dao.Impl;

import com.item.Mapper.CoreMapper.CoreMapper;
import com.item.pojo.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CoreServiceImpl implements com.item.Dao.CoreService {
    @Autowired
    private CoreMapper coreMapper;

    public Core selectAllCore(Integer student_id)
    {
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

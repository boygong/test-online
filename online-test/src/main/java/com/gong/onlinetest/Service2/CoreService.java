package com.gong.onlinetest.Service2;


import com.gong.onlinetest.Pojo.Core;
import com.gong.onlinetest.Service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoreService {
    @Autowired
    private CoreServiceImpl coreService;
    public Core findAllCore(Integer student_id)
    {
        return coreService.selectAllCore(student_id);
    }
}

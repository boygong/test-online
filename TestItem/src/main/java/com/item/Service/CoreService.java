package com.item.Service;

import com.item.Dao.Impl.CoreServiceImpl;
import com.item.pojo.Core;
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

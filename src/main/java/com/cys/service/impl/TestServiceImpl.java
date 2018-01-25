package com.cys.service.impl;

import com.cys.service.TestService;
import com.cys.model.ProjectUserRel;
import com.cys.repository.TestResitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyuan on 2018/1/24.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestResitory testResitory;
    @Override
    public String say() throws Exception {
        List<ProjectUserRel> projectUserRelList = testResitory.findAll();
        projectUserRelList.forEach(projectUserRel -> {
            System.out.print(projectUserRel.getId());
        });
        return "sucess";
    }
}

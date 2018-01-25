package com.cys.repository;

import com.cys.model.ProjectUserRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by liyuan on 2018/1/24.
 */
public interface TestResitory extends JpaRepository<ProjectUserRel, String>,JpaSpecificationExecutor<ProjectUserRel> {

    List<ProjectUserRel>  findAll();
}

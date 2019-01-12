package com.java.fanke.miniprogram.institute.controller;

import com.github.pagehelper.PageInfo;
import com.java.fanke.common.vo.Result;
import com.java.fanke.miniprogram.institute.service.InstituteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("institute")
@CrossOrigin
public class InstituteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstituteController.class);

    @Autowired
    private InstituteService instituteService;

    @RequestMapping("insert")
    public Result insert(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("insert_author", userId);
        LOGGER.info("保存机构参数：{}", params);
        Map<String, Object> res = instituteService.insert(params);
        LOGGER.info("保存机构返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("pageByInstitute")
    public Result pageByInstitute(@RequestBody Map<String, Object> params) {
        LOGGER.info("分页查询机构参数：{}", params);
        PageInfo res = instituteService.pageByInstitute(params);
        LOGGER.info("分页查询机构返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("getByInstitute")
    public Result getByInstitute(@RequestBody Map<String, Object> params) {
        LOGGER.info("查询机构详情参数：{}", params);
        Map<String, Object> res = instituteService.getByInstitute(params);
        LOGGER.info("查询机构详情返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("update")
    public Result update(@RequestBody Map<String, Object> params) {
        LOGGER.info("更新机构参数：{}", params);
        int res = instituteService.update(params);
        LOGGER.info("更新机构返回：{}", res);
        return new Result(100, res);
    }

}

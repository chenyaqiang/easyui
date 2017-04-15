package com.fcbox.easyui.service.impl;

import com.fcbox.easyui.common.EuPagerInfo;
import com.fcbox.easyui.common.PageLister;
import com.fcbox.easyui.dao.FcboxEmployeeDao;
import com.fcbox.easyui.service.IFcboxEmployeeService;
import com.fcbox.easyui.vo.FcboxEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 000538 on 2017/4/14.
 */
@Service("fcboxEmployeeService")
public class FcboxEmployeeServiceImpl implements IFcboxEmployeeService {

    @Autowired
    private FcboxEmployeeDao fcboxEmployeeDao;

    @Override
    public void add(FcboxEmployeeDto employeeDto) {
        fcboxEmployeeDao.insertFcboxEmployeeDto(employeeDto);
    }

    @Override
    public void update(FcboxEmployeeDto employeeDto) {
        fcboxEmployeeDao.updateFcboxEmployeeByPk(employeeDto);
    }

    @Override
    public void delete(FcboxEmployeeDto employeeDto) {
        fcboxEmployeeDao.deleteFcboxEmployeeByPk(employeeDto);
    }

    @Override
    public FcboxEmployeeDto get(FcboxEmployeeDto employeeDto) {
        return fcboxEmployeeDao.selectFcboxEmployeeByPk(employeeDto);
    }

    @Override
    public List<FcboxEmployeeDto> list(FcboxEmployeeDto employeeDto) {
        return fcboxEmployeeDao.selectFcboxEmployeeByDto(employeeDto);
    }

    @Override
    public PageLister<FcboxEmployeeDto> listByPage(FcboxEmployeeDto employeeDto, EuPagerInfo pageInfo) {
        pageInfo.startPage();
        List<FcboxEmployeeDto> list = fcboxEmployeeDao.selectFcboxEmployeeByDto(employeeDto);
        return new PageLister<FcboxEmployeeDto>(list);
    }
}

package com.zen.easyui.controller;

import com.alibaba.fastjson.JSON;
import com.zen.easyui.common.constant.GlobalConstant;
import com.zen.easyui.common.enums.EditEnum;
import com.zen.easyui.common.web.EuPagerInfo;
import com.zen.easyui.common.web.PageLister;
import com.zen.easyui.common.web.ResultDto;
import com.zen.easyui.dto.SysUserDto;
import com.zen.easyui.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 跳转到用户列表页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toListPage")
    ModelAndView toListPage() {
        ModelAndView modelAndView = new ModelAndView(GlobalConstant.LIST_PAGE);
        modelAndView.addObject("page", "jsp/sys/listUser");
        return modelAndView;
    }

    /**
     * 分页用户获取信息
     *
     * @param userDto
     * @param pageInfo
     * @return
     */
    @RequestMapping(value = "/listByPage")
    PageLister<SysUserDto> listByPage(SysUserDto userDto, EuPagerInfo pageInfo) {
        return sysUserService.listUserByPage(userDto, pageInfo);
    }

    /**
     * 跳转到用户编辑页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/toEditPage")
    ModelAndView toEditPage(SysUserDto userDto, String flag) {
        ModelAndView modelAndView = new ModelAndView(GlobalConstant.EDIT_PAGE);
        if (EditEnum.UPDATE.name().equalsIgnoreCase(flag)) {//修改
            modelAndView.addObject("formJson", JSON.toJSONString(sysUserService.getUserByPk(userDto)));
        }
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("page", "jsp/sys/editUser");
        return modelAndView;
    }

    /**
     * 编辑用户信息
     *
     * @param userDto
     * @param flag
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    ResultDto<Void> edit(SysUserDto userDto, String flag) {
        ResultDto<Void> resultDto = new ResultDto<>();
        try {
            if (EditEnum.UPDATE.name().equalsIgnoreCase(flag)) {//修改
                sysUserService.updateUserByPk(userDto);
            } else {//新增
                sysUserService.addUser(userDto);
            }
        } catch (Exception e) {
            resultDto.setCode("-1");
            resultDto.setSuccess(false);
            resultDto.setMsg(e.getMessage());
        }
        return resultDto;
    }

    /**
     * 删除用户信息
     *
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    ResultDto<Void> delete(SysUserDto userDto) {
        ResultDto<Void> resultDto = new ResultDto<>();
        try {
            sysUserService.deleteUserByPk(userDto);
        } catch (Exception e) {
            resultDto.setCode("-1");
            resultDto.setSuccess(false);
            resultDto.setMsg(e.getMessage());
        }
        return resultDto;
    }
}
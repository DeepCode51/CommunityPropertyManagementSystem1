package com.yx.controller;

import com.github.pagehelper.PageInfo;
import com.yx.model.*;
import com.yx.service.IOwnerService;
import com.yx.service.IRepairService;
import com.yx.service.IRepairtypeService;
import com.yx.util.JsonObject;
import com.yx.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kappy
 * @since 2020-10-28
 */
@Api(tags = {""})
@RestController
@RequestMapping("/repair")
public class RepairController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IRepairService repairService;
    @Resource
    private IRepairtypeService repairtypeService;

    @Resource
    private IOwnerService ownerService;

    @RequestMapping("/queryRepairAll")
    public JsonObject queryRepairAll(Repair repair,
                                     @RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "15") Integer limit){


        PageInfo<Repair> pageInfo=repairService.findRepairAll(page,limit,repair);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    @RequestMapping("/queryRepairAll2")
    public JsonObject queryRepairAll2(Repair repair, HttpServletRequest request,
                                     @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "15") Integer limit){


        //获取当前得登录用户
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();
        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        repair.setOwnerId(owner.getId());
        PageInfo<Repair> pageInfo=repairService.findRepairAll(page,limit,repair);
        return new JsonObject(0,"ok",pageInfo.getTotal(),pageInfo.getList());

    }

    @RequestMapping("/queryAll")
    public List<Repairtype> queryAll(){
       return repairtypeService.findList();
    }


    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){
       List<String> list= Arrays.asList(ids.split(","));
       for(String id:list){
           repairService.delete(Long.parseLong(id));
       }

       return R.ok();
    }

    @ApiOperation(value = "新增")
    @RequestMapping("/add")
    public R add(@RequestBody Repair repair,HttpServletRequest request)
    {
        System.out.println("-------111--------");
        System.out.println(repair);
        System.out.println("---------------");
        //获取当前得登录用户
        Userinfo userinfo= (Userinfo) request.getSession().getAttribute("user");
        String username=userinfo.getUsername();

        //根据username获取登录账号得业主id
        Owner owner=ownerService.queryOwnerByName(username);
        repair.setOwnerId(owner.getId());
        repair.setStatus(0);
        repair.setComDate(new Date());
        System.out.println("-------222--------");
        System.out.println(repair);
        System.out.println("---------------");
        int num=repairService.add(repair);
        if(num>0){
            return  R.ok();
        }
        return R.fail("新增失败");
    }

    @ApiOperation(value = "更新")
    @RequestMapping("/update")
    public R update(Integer id){
         Repair repair=new Repair();
         repair.setId(id);
         repair.setStatus(1);
         repair.setHandleDate(new Date());
         int num=repairService.updateData(repair);
         return R.ok();
    }

    /**
     * 统计分析
     */
    @RequestMapping("/queryTongJi")
    public List<Tongji> queryTongji(){
        return repairService.queryTongji();
    }



}

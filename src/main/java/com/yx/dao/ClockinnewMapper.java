package com.yx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yx.model.Clockinnew;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yx
 * @since 2021-04-27
 */
@Component("clockInNewDao")
public interface ClockinnewMapper extends BaseMapper<Clockinnew> {

    /**
     * 查询所有疫情打卡记录
     */
    List<Clockinnew> queryClockInAll(Clockinnew clockinnew);

    Date queryCountByOwnId(Integer ownId);
}

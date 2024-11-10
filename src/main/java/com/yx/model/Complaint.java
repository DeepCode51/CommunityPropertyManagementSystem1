package com.yx.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author kappy
 * @since 2020-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Complaint对象", description="")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

    private Owner owner;
    private ComplaintType type;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String comId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date comDate;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date handleDate;

    private Integer ownerId;

    private Integer status;

    private Integer clr;

    private String remarks;


}

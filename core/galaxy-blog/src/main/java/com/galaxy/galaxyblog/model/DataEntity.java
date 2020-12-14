package com.galaxy.galaxyblog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/4/18
 */
@Data
public class DataEntity {
    @TableId(value="id",type= IdType.AUTO)
    private BigInteger id;
    private Date gmtCreate;
    private Date gmtModified;
    private String creator;
    private String updator;

    public Date getGmtCreate() {
//        if (this.gmtCreate == null){
//            this.gmtCreate = new Date();
//        }
        return gmtCreate;
    }

    public Date getGmtModified() {
//        if (this.gmtModified == null){
//            this.gmtModified = new Date();
//        }
        return gmtModified;
    }
}

package com.dao;

import com.entity.YanxueYuyueEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YanxueYuyueView;

/**
 * 研学报名 Dao 接口
 *
 * @author 
 */
public interface YanxueYuyueDao extends BaseMapper<YanxueYuyueEntity> {

   List<YanxueYuyueView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

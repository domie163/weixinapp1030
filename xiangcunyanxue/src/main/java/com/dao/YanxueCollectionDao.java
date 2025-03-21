package com.dao;

import com.entity.YanxueCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YanxueCollectionView;

/**
 * 研学收藏 Dao 接口
 *
 * @author 
 */
public interface YanxueCollectionDao extends BaseMapper<YanxueCollectionEntity> {

   List<YanxueCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

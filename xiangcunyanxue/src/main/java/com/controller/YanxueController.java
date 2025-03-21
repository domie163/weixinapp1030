
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 研学
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yanxue")
public class YanxueController {
    private static final Logger logger = LoggerFactory.getLogger(YanxueController.class);

    private static final String TABLE_NAME = "yanxue";

    @Autowired
    private YanxueService yanxueService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AddressService addressService;//收货地址
    @Autowired
    private CartService cartService;//购物车
    @Autowired
    private ChatService chatService;//客服聊天
    @Autowired
    private DictionaryService dictionaryService;//字典表
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private JingdianService jingdianService;//景点
    @Autowired
    private JingdianCollectionService jingdianCollectionService;//景点收藏
    @Autowired
    private JingdianCommentbackService jingdianCommentbackService;//景点评价
    @Autowired
    private JingdianOrderService jingdianOrderService;//景点预约
    @Autowired
    private ShangpinService shangpinService;//商品
    @Autowired
    private ShangpinCollectionService shangpinCollectionService;//商品收藏
    @Autowired
    private ShangpinCommentbackService shangpinCommentbackService;//商品评价
    @Autowired
    private ShangpinOrderService shangpinOrderService;//商品订单
    @Autowired
    private YanxueCollectionService yanxueCollectionService;//研学收藏
    @Autowired
    private YanxueLiuyanService yanxueLiuyanService;//研学留言
    @Autowired
    private YanxueYuyueService yanxueYuyueService;//研学报名
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("yanxueDeleteStart",1);params.put("yanxueDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = yanxueService.queryPage(params);

        //字典表数据转换
        List<YanxueView> list =(List<YanxueView>)page.getList();
        for(YanxueView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YanxueEntity yanxue = yanxueService.selectById(id);
        if(yanxue !=null){
            //entity转view
            YanxueView view = new YanxueView();
            BeanUtils.copyProperties( yanxue , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YanxueEntity yanxue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yanxue:{}",this.getClass().getName(),yanxue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YanxueEntity> queryWrapper = new EntityWrapper<YanxueEntity>()
            .eq("yanxue_name", yanxue.getYanxueName())
            .eq("yanxue_fuzeren", yanxue.getYanxueFuzeren())
            .eq("yanxue_phone", yanxue.getYanxuePhone())
            .eq("yanxue_address", yanxue.getYanxueAddress())
            .eq("zan_number", yanxue.getZanNumber())
            .eq("cai_number", yanxue.getCaiNumber())
            .eq("yanxue_types", yanxue.getYanxueTypes())
            .eq("yanxue_kucun_number", yanxue.getYanxueKucunNumber())
            .eq("shangxia_types", yanxue.getShangxiaTypes())
            .eq("yanxue_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YanxueEntity yanxueEntity = yanxueService.selectOne(queryWrapper);
        if(yanxueEntity==null){
            yanxue.setZanNumber(1);
            yanxue.setCaiNumber(1);
            yanxue.setYanxueClicknum(1);
            yanxue.setShangxiaTypes(1);
            yanxue.setYanxueDelete(1);
            yanxue.setInsertTime(new Date());
            yanxue.setCreateTime(new Date());
            yanxueService.insert(yanxue);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YanxueEntity yanxue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yanxue:{}",this.getClass().getName(),yanxue.toString());
        YanxueEntity oldYanxueEntity = yanxueService.selectById(yanxue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(yanxue.getYanxuePhoto()) || "null".equals(yanxue.getYanxuePhoto())){
                yanxue.setYanxuePhoto(null);
        }
        if("".equals(yanxue.getYanxueContent()) || "null".equals(yanxue.getYanxueContent())){
                yanxue.setYanxueContent(null);
        }

            yanxueService.updateById(yanxue);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YanxueEntity> oldYanxueList =yanxueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<YanxueEntity> list = new ArrayList<>();
        for(Integer id:ids){
            YanxueEntity yanxueEntity = new YanxueEntity();
            yanxueEntity.setId(id);
            yanxueEntity.setYanxueDelete(2);
            list.add(yanxueEntity);
        }
        if(list != null && list.size() >0){
            yanxueService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<YanxueEntity> yanxueList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YanxueEntity yanxueEntity = new YanxueEntity();
//                            yanxueEntity.setYanxueName(data.get(0));                    //研学标题 要改的
//                            yanxueEntity.setYanxueUuidNumber(data.get(0));                    //研学编号 要改的
//                            yanxueEntity.setYanxuePhoto("");//详情和图片
//                            yanxueEntity.setYanxueFuzeren(data.get(0));                    //负责人 要改的
//                            yanxueEntity.setYanxuePhone(data.get(0));                    //联系方式 要改的
//                            yanxueEntity.setYanxueAddress(data.get(0));                    //研学路线 要改的
//                            yanxueEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            yanxueEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            yanxueEntity.setYanxueTypes(Integer.valueOf(data.get(0)));   //研学类型 要改的
//                            yanxueEntity.setYanxueKucunNumber(Integer.valueOf(data.get(0)));   //剩余可参与人数 要改的
//                            yanxueEntity.setYanxueTime(sdf.parse(data.get(0)));          //出发时间 要改的
//                            yanxueEntity.setYanxueClicknum(Integer.valueOf(data.get(0)));   //研学热度 要改的
//                            yanxueEntity.setYanxueContent("");//详情和图片
//                            yanxueEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            yanxueEntity.setYanxueDelete(1);//逻辑删除字段
//                            yanxueEntity.setInsertTime(date);//时间
//                            yanxueEntity.setCreateTime(date);//时间
                            yanxueList.add(yanxueEntity);


                            //把要查询是否重复的字段放入map中
                                //研学编号
                                if(seachFields.containsKey("yanxueUuidNumber")){
                                    List<String> yanxueUuidNumber = seachFields.get("yanxueUuidNumber");
                                    yanxueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yanxueUuidNumber = new ArrayList<>();
                                    yanxueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yanxueUuidNumber",yanxueUuidNumber);
                                }
                                //联系方式
                                if(seachFields.containsKey("yanxuePhone")){
                                    List<String> yanxuePhone = seachFields.get("yanxuePhone");
                                    yanxuePhone.add(data.get(0));//要改的
                                }else{
                                    List<String> yanxuePhone = new ArrayList<>();
                                    yanxuePhone.add(data.get(0));//要改的
                                    seachFields.put("yanxuePhone",yanxuePhone);
                                }
                        }

                        //查询是否重复
                         //研学编号
                        List<YanxueEntity> yanxueEntities_yanxueUuidNumber = yanxueService.selectList(new EntityWrapper<YanxueEntity>().in("yanxue_uuid_number", seachFields.get("yanxueUuidNumber")).eq("yanxue_delete", 1));
                        if(yanxueEntities_yanxueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YanxueEntity s:yanxueEntities_yanxueUuidNumber){
                                repeatFields.add(s.getYanxueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [研学编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<YanxueEntity> yanxueEntities_yanxuePhone = yanxueService.selectList(new EntityWrapper<YanxueEntity>().in("yanxue_phone", seachFields.get("yanxuePhone")).eq("yanxue_delete", 1));
                        if(yanxueEntities_yanxuePhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YanxueEntity s:yanxueEntities_yanxuePhone){
                                repeatFields.add(s.getYanxuePhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yanxueService.insertBatch(yanxueList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<YanxueView> returnYanxueViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("yanxueYesnoTypes",2);
        PageUtils pageUtils = yanxueCollectionService.queryPage(params1);
        List<YanxueCollectionView> collectionViewsList =(List<YanxueCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(YanxueCollectionView collectionView:collectionViewsList){
            Integer yanxueTypes = collectionView.getYanxueTypes();
            if(typeMap.containsKey(yanxueTypes)){
                typeMap.put(yanxueTypes,typeMap.get(yanxueTypes)+1);
            }else{
                typeMap.put(yanxueTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("yanxueTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("yanxueYesnoTypes",2);
            PageUtils pageUtils1 = yanxueService.queryPage(params2);
            List<YanxueView> yanxueViewList =(List<YanxueView>)pageUtils1.getList();
            returnYanxueViewList.addAll(yanxueViewList);
            if(returnYanxueViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("yanxueYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = yanxueService.queryPage(params);
        if(returnYanxueViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnYanxueViewList.size();//要添加的数量
            List<YanxueView> yanxueViewList =(List<YanxueView>)page.getList();
            for(YanxueView yanxueView:yanxueViewList){
                Boolean addFlag = true;
                for(YanxueView returnYanxueView:returnYanxueViewList){
                    if(returnYanxueView.getId().intValue() ==yanxueView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnYanxueViewList.add(yanxueView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnYanxueViewList = returnYanxueViewList.subList(0, limit);
        }

        for(YanxueView c:returnYanxueViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnYanxueViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yanxueService.queryPage(params);

        //字典表数据转换
        List<YanxueView> list =(List<YanxueView>)page.getList();
        for(YanxueView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YanxueEntity yanxue = yanxueService.selectById(id);
            if(yanxue !=null){

                //点击数量加1
                yanxue.setYanxueClicknum(yanxue.getYanxueClicknum()+1);
                yanxueService.updateById(yanxue);

                //entity转view
                YanxueView view = new YanxueView();
                BeanUtils.copyProperties( yanxue , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody YanxueEntity yanxue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yanxue:{}",this.getClass().getName(),yanxue.toString());
        Wrapper<YanxueEntity> queryWrapper = new EntityWrapper<YanxueEntity>()
            .eq("yanxue_name", yanxue.getYanxueName())
            .eq("yanxue_uuid_number", yanxue.getYanxueUuidNumber())
            .eq("yanxue_fuzeren", yanxue.getYanxueFuzeren())
            .eq("yanxue_phone", yanxue.getYanxuePhone())
            .eq("yanxue_address", yanxue.getYanxueAddress())
            .eq("zan_number", yanxue.getZanNumber())
            .eq("cai_number", yanxue.getCaiNumber())
            .eq("yanxue_types", yanxue.getYanxueTypes())
            .eq("yanxue_kucun_number", yanxue.getYanxueKucunNumber())
            .eq("yanxue_clicknum", yanxue.getYanxueClicknum())
            .eq("shangxia_types", yanxue.getShangxiaTypes())
            .eq("yanxue_delete", yanxue.getYanxueDelete())
//            .notIn("yanxue_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YanxueEntity yanxueEntity = yanxueService.selectOne(queryWrapper);
        if(yanxueEntity==null){
                yanxue.setZanNumber(1);
                yanxue.setCaiNumber(1);
            yanxue.setYanxueClicknum(1);
            yanxue.setYanxueDelete(1);
            yanxue.setInsertTime(new Date());
            yanxue.setCreateTime(new Date());
        yanxueService.insert(yanxue);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}


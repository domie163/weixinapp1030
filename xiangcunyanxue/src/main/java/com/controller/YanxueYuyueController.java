
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
 * 研学报名
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yanxueYuyue")
public class YanxueYuyueController {
    private static final Logger logger = LoggerFactory.getLogger(YanxueYuyueController.class);

    private static final String TABLE_NAME = "yanxueYuyue";

    @Autowired
    private YanxueYuyueService yanxueYuyueService;


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
    private YanxueService yanxueService;//研学
    @Autowired
    private YanxueCollectionService yanxueCollectionService;//研学收藏
    @Autowired
    private YanxueLiuyanService yanxueLiuyanService;//研学留言
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
        CommonUtil.checkMap(params);
        PageUtils page = yanxueYuyueService.queryPage(params);

        //字典表数据转换
        List<YanxueYuyueView> list =(List<YanxueYuyueView>)page.getList();
        for(YanxueYuyueView c:list){
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
        YanxueYuyueEntity yanxueYuyue = yanxueYuyueService.selectById(id);
        if(yanxueYuyue !=null){
            //entity转view
            YanxueYuyueView view = new YanxueYuyueView();
            BeanUtils.copyProperties( yanxueYuyue , view );//把实体数据重构到view中
            //级联表 研学
            //级联表
            YanxueEntity yanxue = yanxueService.selectById(yanxueYuyue.getYanxueId());
            if(yanxue != null){
            BeanUtils.copyProperties( yanxue , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYanxueId(yanxue.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(yanxueYuyue.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody YanxueYuyueEntity yanxueYuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yanxueYuyue:{}",this.getClass().getName(),yanxueYuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            yanxueYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<YanxueYuyueEntity> queryWrapper = new EntityWrapper<YanxueYuyueEntity>()
            .eq("yanxue_id", yanxueYuyue.getYanxueId())
            .eq("yonghu_id", yanxueYuyue.getYonghuId())
            .in("yanxue_yuyue_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YanxueYuyueEntity yanxueYuyueEntity = yanxueYuyueService.selectOne(queryWrapper);
        if(yanxueYuyueEntity==null){
            yanxueYuyue.setInsertTime(new Date());
            yanxueYuyue.setYanxueYuyueYesnoTypes(1);
            yanxueYuyue.setCreateTime(new Date());
            yanxueYuyueService.insert(yanxueYuyue);
            return R.ok();
        }else {
            if(yanxueYuyueEntity.getYanxueYuyueYesnoTypes()==1)
                return R.error(511,"该用户已经报名过了该研学,请耐心等待审核");
            else if(yanxueYuyueEntity.getYanxueYuyueYesnoTypes()==2)
                return R.error(511,"该用户已经报名通过了该研学,不能重复申请");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YanxueYuyueEntity yanxueYuyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yanxueYuyue:{}",this.getClass().getName(),yanxueYuyue.toString());
        YanxueYuyueEntity oldYanxueYuyueEntity = yanxueYuyueService.selectById(yanxueYuyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            yanxueYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(yanxueYuyue.getYanxueYuyueText()) || "null".equals(yanxueYuyue.getYanxueYuyueText())){
                yanxueYuyue.setYanxueYuyueText(null);
        }
        if("".equals(yanxueYuyue.getYanxueYuyueYesnoText()) || "null".equals(yanxueYuyue.getYanxueYuyueYesnoText())){
                yanxueYuyue.setYanxueYuyueYesnoText(null);
        }

            yanxueYuyueService.updateById(yanxueYuyue);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody YanxueYuyueEntity yanxueYuyueEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,yanxueYuyueEntity:{}",this.getClass().getName(),yanxueYuyueEntity.toString());

        YanxueYuyueEntity oldYanxueYuyue = yanxueYuyueService.selectById(yanxueYuyueEntity.getId());//查询原先数据

//        if(yanxueYuyueEntity.getYanxueYuyueYesnoTypes() == 2){//通过
//            yanxueYuyueEntity.setYanxueYuyueTypes();
//        }else if(yanxueYuyueEntity.getYanxueYuyueYesnoTypes() == 3){//拒绝
//            yanxueYuyueEntity.setYanxueYuyueTypes();
//        }
        yanxueYuyueEntity.setYanxueYuyueShenheTime(new Date());//审核时间
        yanxueYuyueService.updateById(yanxueYuyueEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YanxueYuyueEntity> oldYanxueYuyueList =yanxueYuyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        yanxueYuyueService.deleteBatchIds(Arrays.asList(ids));

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
            List<YanxueYuyueEntity> yanxueYuyueList = new ArrayList<>();//上传的东西
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
                            YanxueYuyueEntity yanxueYuyueEntity = new YanxueYuyueEntity();
//                            yanxueYuyueEntity.setYanxueYuyueUuidNumber(data.get(0));                    //报名编号 要改的
//                            yanxueYuyueEntity.setYanxueId(Integer.valueOf(data.get(0)));   //研学 要改的
//                            yanxueYuyueEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yanxueYuyueEntity.setYanxueYuyueText(data.get(0));                    //报名理由 要改的
//                            yanxueYuyueEntity.setInsertTime(date);//时间
//                            yanxueYuyueEntity.setYanxueYuyueYesnoTypes(Integer.valueOf(data.get(0)));   //报名状态 要改的
//                            yanxueYuyueEntity.setYanxueYuyueYesnoText(data.get(0));                    //审核回复 要改的
//                            yanxueYuyueEntity.setYanxueYuyueShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            yanxueYuyueEntity.setCreateTime(date);//时间
                            yanxueYuyueList.add(yanxueYuyueEntity);


                            //把要查询是否重复的字段放入map中
                                //报名编号
                                if(seachFields.containsKey("yanxueYuyueUuidNumber")){
                                    List<String> yanxueYuyueUuidNumber = seachFields.get("yanxueYuyueUuidNumber");
                                    yanxueYuyueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yanxueYuyueUuidNumber = new ArrayList<>();
                                    yanxueYuyueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yanxueYuyueUuidNumber",yanxueYuyueUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名编号
                        List<YanxueYuyueEntity> yanxueYuyueEntities_yanxueYuyueUuidNumber = yanxueYuyueService.selectList(new EntityWrapper<YanxueYuyueEntity>().in("yanxue_yuyue_uuid_number", seachFields.get("yanxueYuyueUuidNumber")));
                        if(yanxueYuyueEntities_yanxueYuyueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YanxueYuyueEntity s:yanxueYuyueEntities_yanxueYuyueUuidNumber){
                                repeatFields.add(s.getYanxueYuyueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yanxueYuyueService.insertBatch(yanxueYuyueList);
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
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yanxueYuyueService.queryPage(params);

        //字典表数据转换
        List<YanxueYuyueView> list =(List<YanxueYuyueView>)page.getList();
        for(YanxueYuyueView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YanxueYuyueEntity yanxueYuyue = yanxueYuyueService.selectById(id);
            if(yanxueYuyue !=null){


                //entity转view
                YanxueYuyueView view = new YanxueYuyueView();
                BeanUtils.copyProperties( yanxueYuyue , view );//把实体数据重构到view中

                //级联表
                    YanxueEntity yanxue = yanxueService.selectById(yanxueYuyue.getYanxueId());
                if(yanxue != null){
                    BeanUtils.copyProperties( yanxue , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYanxueId(yanxue.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(yanxueYuyue.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody YanxueYuyueEntity yanxueYuyue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yanxueYuyue:{}",this.getClass().getName(),yanxueYuyue.toString());
        Wrapper<YanxueYuyueEntity> queryWrapper = new EntityWrapper<YanxueYuyueEntity>()
            .eq("yanxue_yuyue_uuid_number", yanxueYuyue.getYanxueYuyueUuidNumber())
            .eq("yanxue_id", yanxueYuyue.getYanxueId())
            .eq("yonghu_id", yanxueYuyue.getYonghuId())
            .eq("yanxue_yuyue_text", yanxueYuyue.getYanxueYuyueText())
            .in("yanxue_yuyue_yesno_types", new Integer[]{1,2})
            .eq("yanxue_yuyue_yesno_text", yanxueYuyue.getYanxueYuyueYesnoText())
//            .notIn("yanxue_yuyue_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YanxueYuyueEntity yanxueYuyueEntity = yanxueYuyueService.selectOne(queryWrapper);
        if(yanxueYuyueEntity==null){
            yanxueYuyue.setInsertTime(new Date());
            yanxueYuyue.setYanxueYuyueYesnoTypes(1);
            yanxueYuyue.setCreateTime(new Date());
        yanxueYuyueService.insert(yanxueYuyue);

            return R.ok();
        }else {
            if(yanxueYuyueEntity.getYanxueYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(yanxueYuyueEntity.getYanxueYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}


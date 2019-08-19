package com.mzkj.service.companyOriginal.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.DictionariesBean;
import com.mzkj.bean.OriginalBean;
import com.mzkj.bean.OriginalProcessRecordsBean;
import com.mzkj.bean.UserBean;
import com.mzkj.domain.Original;
import com.mzkj.facade.vo.Result;
import com.mzkj.mapper.companyOriginal.CompanyInformationMapper;
import com.mzkj.mapper.companyOriginal.OriginalMapper;
import com.mzkj.mapper.companyOriginal.OriginalProcessRecordsMapper;
import com.mzkj.mapper.system.DictionariesMapper;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.service.companyOriginal.OriginalManager;
import com.mzkj.util.Const;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.DateUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.companyOriginal.OriginalProcessRecordsQueryVo;
import com.mzkj.vo.companyOriginal.OriginalQueryVo;
import com.mzkj.vo.companyOriginal.OriginalVo;
import com.mzkj.vo.system.UserVo;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 说明： 公司原件详情
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@Service("originalService")
public class OriginalService implements OriginalManager {

    @Autowired
    private OriginalMapper originalMapper;
    @Autowired
    private OriginalProcessRecordsMapper originalProcessRecordsMapper;
    @Autowired
    private DictionariesMapper dictionariesMapper;
    @Autowired
    private CompanyInformationMapper companyInformationMapper;

    @Autowired
    private UserMapper userMapper;
    /**
     * 新增
     */
    @Override
    public List<OriginalVo> save(List<OriginalVo> originalVoList) throws Exception {
        for(OriginalVo originalVo :originalVoList) {
            OriginalBean originalBean = ConvertUtil.objectCopyParams(originalVo, OriginalBean.class);
            //如果原件持有状态为在公司内部，设置原件持有人为当前登录人
            if (!StringUtils.isEmpty(originalBean.getOriginalHoldStatus()) && originalBean.getOriginalHoldStatus().equals("2")) {
                originalBean.setOriginalHolder(Jurisdiction.getUsername());
                originalBean.setOriginalHolderDepartment(Jurisdiction.getDEPARTMENT_ID());
            }
            //公司名称
            originalBean.setCompanyName(companyInformationMapper.findById(originalBean.getCompanyInformationId()).getCompanyName());
            originalBean.setOriginalId(UuidUtil.get32UUID());
            originalBean.setCreateDate(DateUtil.getTime());
            originalBean.setCreateUser(Jurisdiction.getUsername());
            originalBean.setTenantId(Jurisdiction.getTenant());
            originalBean.setOriginalOutStatus("2");//原件流转状态设为入库
            originalMapper.save(originalBean);
        }
        return originalVoList;
    }

    /**
     * 根据ID查询
     * return
     * Author luosc
     * param
     * Date 2019-04-25 15:26
     */
    @Override
    public OriginalQueryVo findById(OriginalQueryVo originalQueryVo) throws Exception {
        OriginalBean originalBean = ConvertUtil.objectCopyParams(originalQueryVo, OriginalBean.class);
        originalBean = originalMapper.findById(originalBean);
        castUsernameToName(originalBean);
        return ConvertUtil.objectCopyParams(originalBean, OriginalQueryVo.class);
    }
    /**
     * 将username转为name
     * return
     * Author luosc
     * param
     * Date 2019-04-30 14:18
     */
    private void castUsernameToName(OriginalBean originalBean) throws Exception {
        String originalHolder =originalBean.getOriginalHolder();
        String originalOutTo = originalBean.getOriginalOutTo();
        if (!StringUtils.isEmpty(originalHolder)) {
            UserBean userBean=userMapper.findByUsername(originalHolder);
            if (userBean != null) {
                originalBean.setOriginalHolder(userBean.getName());
            }
        }
        if (!StringUtils.isEmpty(originalOutTo)) {
            UserBean userBean=userMapper.findByUsername(originalOutTo);
            if (userBean != null) {
                originalBean.setOriginalOutTo(userBean.getName());
            }
        }
    }
    /**
     * 删除
     */
    @Override
    public void delete(String ORIGINAL_ID) throws Exception {
        originalMapper.delete(ORIGINAL_ID);
    }

    /**
     * 修改
     */
    @Override
    public void edit(OriginalVo originalVo) throws Exception {
        OriginalBean originalBean = ConvertUtil.objectCopyParams(originalVo, OriginalBean.class);
        originalBean.setTenantId(Jurisdiction.getTenant());
        String originalHoldStatus =originalBean.getOriginalHoldStatus();
        if (!StringUtils.isEmpty(originalHoldStatus) && originalHoldStatus.equals(Const.ORIGINAL_OUT_STATUS_2)) {
            originalBean.setOriginalHolder(Jurisdiction.getUsername());
        } else {
            originalBean.setOriginalHolder("");
        }
        originalMapper.edit(originalBean);
    }

    /**
     * 借出并插入原件流转记录表
     * return
     * Author luosc
     * param
     * Date 2019-04-24 15:40
     */
    @Override
    public void loanOut(OriginalVo originalVo) throws Exception {
        OriginalBean originalBean = ConvertUtil.objectCopyParams(originalVo, OriginalBean.class);
        originalBean.setOriginalOutStatus("1");
        //插入原件流转记录表
        initOriginalProcessRecords(originalBean, Const.LOAN_OUT);
        originalMapper.loanOut(originalBean);
    }

    /**
     * 确认借入
     * return
     * Author luosc
     * param
     * Date 2019-04-24 15:44
     */
    @Override
    public void loanOutConfirmed(OriginalVo originalVo) throws Exception {
        OriginalBean originalBean = ConvertUtil.objectCopyParams(originalVo, OriginalBean.class);
        //插入原件流转记录表
        initOriginalProcessRecords(originalBean, Const.LOAN_OUT_CONFIRMED);
        originalBean =originalMapper.findById(originalBean);
        originalBean.setOriginalOutStatus("2");
        originalBean.setOriginalHolder(originalBean.getOriginalOutTo());//借入人确认，当前持有人变成当前借出对象
        originalBean.setOriginalOutTo(null);

        originalMapper.loanOutConfirmed(originalBean);
    }

    /**
     * 驳回
     * return
     * Author luosc
     * param
     * Date 2019-04-24 17:00
     */
    @Override
    public void reject(OriginalVo originalVo) throws Exception {
        OriginalBean originalBean = ConvertUtil.objectCopyParams(originalVo, OriginalBean.class);
        //插入原件流转记录表
        initOriginalProcessRecords(originalBean, Const.REJECT);
        originalBean.setOriginalOutStatus("2");
        originalBean.setOriginalOutTo(null);
        originalMapper.reject(originalBean);
    }

    /**
     * 借入
     * return
     * Author luosc
     * param
     * Date 2019-04-24 17:21
     */
    @Override
    public void loanIn(OriginalVo originalVo) throws Exception {
        OriginalBean originalBean = ConvertUtil.objectCopyParams(originalVo, OriginalBean.class);
        originalBean.setOriginalOutStatus("3");//状态修改为待借入
        originalBean.setOriginalOutTo(Jurisdiction.getUsername());//借入人为当前登录人
        //插入原件流转记录表
        initOriginalProcessRecords(originalBean, Const.LOAN_IN);
        originalMapper.loanIn(originalBean);
    }


    /**
     * 列表
     */
    @Override
    public PageInfo<OriginalQueryVo> list(OriginalQueryVo originalQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        OriginalBean originalBean = PageUtil.startPageAndObjectCopyParams(originalQueryVo, OriginalBean.class);
        List<OriginalBean> originalPageBean = originalMapper.list(originalBean);
        PageInfo<OriginalQueryVo> pageInfo = new PageInfo(originalPageBean);

        //DO转VO
        List<OriginalQueryVo> originalQueryVoList = (List<OriginalQueryVo>) ConvertUtil.castListObjectToTargetList(originalPageBean,OriginalQueryVo.class);
        pageInfo.setList(originalQueryVoList);

        //初始化当前登录人的权限
        initAuthorizedAndCastDicBianmaToName(pageInfo);
        return castUsernameToName(pageInfo);
    }
    /**
     * 将用户名替换为姓名
     * return
     * Author luosc
     * param
     * Date 2019-04-30 11:42
     */
    private PageInfo<OriginalQueryVo> castUsernameToName(PageInfo<OriginalQueryVo> OriginalQueryPageVo) throws Exception {
        List<OriginalQueryVo> list = OriginalQueryPageVo.getList();
        List<OriginalQueryVo> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i=0;i<list.size();i++) {
                OriginalQueryVo originalQueryVo = ConvertUtil.json2Obj(JSON.toJSONString(list.get(i)), OriginalQueryVo.class);
                String originalHolder = originalQueryVo.getOriginalHolder();
                if (!StringUtils.isEmpty(originalHolder)) {
                    UserBean userBean= userMapper.findByUsername(originalHolder);
                    if (userBean != null && !StringUtils.isEmpty(userBean.getName())) {
                        originalQueryVo.setOriginalHolder(userBean.getName());
                    }
                }
                result.add(originalQueryVo);
            }
        }
        OriginalQueryPageVo.setList(result);
        return OriginalQueryPageVo;
    }
    /**
     * 根据原件持有人，流转状态，持有状态，借出人，查询当前登录人的按钮操作权限
     * return
     * Author luosc
     * param
     * Date 2019-04-24 14:11
     */
    private void initAuthorizedAndCastDicBianmaToName(PageInfo<OriginalQueryVo> originalPageVo) throws Exception {
        if (originalPageVo != null && originalPageVo.getList() != null && originalPageVo.getList().size() > 0) {
            List<OriginalQueryVo> originalQueryVoList = originalPageVo.getList();
            for (OriginalQueryVo originalQueryVo : originalQueryVoList
                    ) {
                //如果原件持有状态为在公司内部时才执行
                if (!StringUtils.isEmpty(originalQueryVo.getOriginalHoldStatus()) && originalQueryVo.getOriginalHoldStatus().equals("2")) {
                    //判断原件持有人和当前登录人是否相同
                    if (!StringUtils.isEmpty(originalQueryVo.getOriginalHolder()) && originalQueryVo.getOriginalHolder().equals(Jurisdiction.getUsername())) {
                        //如果原件持有人和当前登录人相同
                        //如果当前原件流转状态状态=入库
                        if (originalQueryVo.getOriginalOutStatus().equals("2")) {
                            originalQueryVo.setHasLoanOutAuthorized(true);
                        } //借入，待原件持有人确认
                        else if (originalQueryVo.getOriginalOutStatus().equals("3")) {
                            originalQueryVo.setHasLoanOutConfirmed(true);
                        }
                    } else {
                        //主动借入
                        //如果当前原件持有状态状态=入库
                        if (originalQueryVo.getOriginalOutStatus().equals("2")) {
                            originalQueryVo.setHasLoanInAuthorized(true);
                        } //借出，待接受对象确认
                        if (originalQueryVo.getOriginalOutStatus().equals("1")) {
                            //当前借出对象和当前登录人相同时，执行
                            if (!StringUtils.isEmpty(originalQueryVo.getOriginalOutTo()) && originalQueryVo.getOriginalOutTo().equals(Jurisdiction.getUsername())) {
                                originalQueryVo.setHasLoanOutConfirmed(true);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 插入原件流转记录表
     * return
     * Author luosc
     * param
     * Date 2019-04-25 8:52
     */
    private void initOriginalProcessRecords(OriginalBean originalBean, String originalOperationType) throws Exception {
        OriginalProcessRecordsBean originalProcessRecordsBean = new OriginalProcessRecordsBean();
        originalProcessRecordsBean.setOriginalProcessRecordsId(UuidUtil.get32UUID());
        originalProcessRecordsBean.setOriginalId(originalBean.getOriginalId());//原件id
        originalProcessRecordsBean.setCreateDate(DateUtil.getTime());
        originalProcessRecordsBean.setCreateUser(Jurisdiction.getUsername());
        originalProcessRecordsBean.setTenantId(Jurisdiction.getTenant());//租户ID
        originalProcessRecordsBean.setOriginalFromTime(DateUtil.getTime());//接受原件时间
        originalProcessRecordsBean.setOriginalFromUsername(originalBean.getOriginalHolder());//从当前持有人借出
        originalProcessRecordsBean.setOriginalOutUsername(originalBean.getOriginalOutTo());//借出对象
        originalProcessRecordsBean.setOriginalOutTime(DateUtil.getTime());//借出时间
        if (originalOperationType.equals(Const.LOAN_OUT)) {
            originalProcessRecordsBean.setRemark("借出，待确认");
        } else if (originalOperationType.equals(Const.LOAN_OUT_CONFIRMED)) {
            originalProcessRecordsBean.setRemark("原件流转确认");
        } else if (originalOperationType.equals(Const.REJECT)) {
            originalProcessRecordsBean.setRemark("原件流转被驳回");
        } else if (originalOperationType.equals(Const.LOAN_IN)) {
            originalProcessRecordsBean.setRemark("借入,待确认");
        }

        originalProcessRecordsMapper.save(originalProcessRecordsBean);
    }

    /**
     * 更新原件表交接原件、并且插入原件交接流程
     */
    public void handoverOriginal(String holder, String fromHolder, String originalIds) throws Exception {
        SimpleDateFormat dateRule = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONArray objects = JSONArray.parseArray(originalIds);
        for(int i=0;i<objects.size();i++){
            originalMapper.handoverOriginalByHolderAndOriginalId(holder,objects.getJSONObject(i).getString("originalId"));
            OriginalBean originalBean = new OriginalBean();
            originalBean.setOriginalId(objects.getJSONObject(i).getString("originalId"));
            originalBean = originalMapper.findById(originalBean);
            insertOriginalProcess(holder,fromHolder,originalBean,dateRule);
        }
    };
    //插入原件交接流程
    private void insertOriginalProcess(String holder, String fromHolder,OriginalBean originalBean,SimpleDateFormat dateRule) throws Exception{
        OriginalProcessRecordsBean originalProcessRecord = ConvertUtil.objectCopyParams(originalBean,OriginalProcessRecordsBean.class);
        originalProcessRecord.setOriginalProcessRecordsId(UuidUtil.get32UUID());
        originalProcessRecord.setOriginalFromUsername(holder);
        originalProcessRecord.setOriginalOutUsername(fromHolder);
        originalProcessRecord.setOriginalFromTime(dateRule.format(new Date()));
        originalProcessRecord.setOriginalOutTime(dateRule.format(new Date()));
        originalProcessRecordsMapper.save(originalProcessRecord);
    }

    /**
     * 根据公司原件名称查询
     * return
     * Author dzw
     * param
     * Date 2019-08-19 15:08
     */
    public OriginalBean findByOriginalName(OriginalBean original) throws Exception {
        return originalMapper.findByOriginalName(original);
    };
}


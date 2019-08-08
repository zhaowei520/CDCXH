package com.mzkj.service.companyOriginal.impl;

import com.fh.util.PageData;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.CompanyInformationBean;
import com.mzkj.bean.CustomerBean;
import com.mzkj.bean.OriginalBean;
import com.mzkj.bean.OriginalProcessRecordsBean;
import com.mzkj.bean.StaffBean;
import com.mzkj.domain.Original;
import com.mzkj.facade.vo.Result;
import com.mzkj.mapper.companyOriginal.OriginalMapper;
import com.mzkj.mapper.companyOriginal.OriginalProcessRecordsMapper;
import com.mzkj.mapper.customer.CustomerMapper;
import com.mzkj.service.system.impl.StaffService;
import com.mzkj.util.Const;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.DateUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.companyOriginal.CompanyInformationQueryVo;
import com.mzkj.vo.companyOriginal.CompanyInformationVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mzkj.service.companyOriginal.CompanyInformationManager;
import com.mzkj.mapper.companyOriginal.CompanyInformationMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明： 原件管理客户信息
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 */
@Service("companyinformationService")
public class CompanyInformationService implements CompanyInformationManager {

    @Autowired
    private CompanyInformationMapper companyinformationMapper;
    @Autowired
    private StaffService staffService;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private OriginalMapper originalMapper;
    @Autowired
    private OriginalProcessRecordsMapper originalProcessRecordsMapper;
    /**
     * 新增
     */
    @Override
    public Result save(List<CompanyInformationVo> companyinformationVoList) throws Exception {
        CompanyInformationBean companyinformationBean = new CompanyInformationBean();
        Result result = new Result();
        List existCompany = new ArrayList();
        for(CompanyInformationVo companyinformationVo : companyinformationVoList) {
            companyinformationBean = ConvertUtil.objectCopyParams(companyinformationVo, CompanyInformationBean.class);
            //验证新增公司数据是否已存在,若存在就放入公司名list中
            CompanyInformationBean companyInformation = companyinformationMapper.findCompanyInformationByCompanyName(companyinformationBean);
            if(companyInformation != null) {
                existCompany.add(companyInformation.getCompanyName());
                continue;
            }
            //设置租户ID`
            companyinformationBean.setCompanyInformationId(UuidUtil.get32UUID());
            companyinformationBean.setTenantId(Jurisdiction.getTenant());
            companyinformationBean.setCreateUser(Jurisdiction.getUsername());
            companyinformationBean.setCreateDate(DateUtil.getTime());
            companyinformationMapper.save(companyinformationBean);
        }
        result.setData(existCompany);
        return result;
    }

    /**
     * 删除
     */
    @Override
    public void delete(String COMPANYINFORMATION_ID) throws Exception {
        companyinformationMapper.delete(COMPANYINFORMATION_ID);
    }

    /**
     * 修改
     */
    @Override
    public void edit(CompanyInformationVo companyinformationVo) throws Exception {
        CompanyInformationBean companyinformationBean = ConvertUtil.objectCopyParams(companyinformationVo, CompanyInformationBean.class);
        //设置租户ID
        companyinformationBean.setTenantId(Jurisdiction.getTenant());
        companyinformationBean.setUpdateUser(Jurisdiction.getUsername());
        companyinformationBean.setUpdateDate(DateUtil.getTime());
        companyinformationMapper.edit(companyinformationBean);
    }

    /**
     * 列表
     */
    @Override
    public PageInfo<CompanyInformationQueryVo> list(CompanyInformationQueryVo companyinformationQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        CompanyInformationBean companyinformationBean = PageUtil.startPageAndObjectCopyParams(companyinformationQueryVo, CompanyInformationBean.class);
        //设置租户ID
        companyinformationBean.setTenantId(Jurisdiction.getTenant());
        List<CompanyInformationBean> companyinformationPageBean = companyinformationMapper.list(companyinformationBean);
        selectOriginalByInformationBeanList(companyinformationPageBean);
        PageInfo<CompanyInformationQueryVo> pageInfo = new PageInfo(companyinformationPageBean);
        //DO转VO
        List<CompanyInformationQueryVo> informationQueryVoList = (List<CompanyInformationQueryVo>) ConvertUtil.castListObjectToTargetList(companyinformationPageBean, CompanyInformationQueryVo.class);
        pageInfo.setList(informationQueryVoList);
        return pageInfo;
    }

    /**
     * 根据传入的CompanyInformationBean 查询对应的原件List
     * return
     * Author luosc
     * param
     * Date 2019-04-23 14:56
     */
    public void selectOriginalByInformationBeanList(List<CompanyInformationBean> CompanyInformationBeanList) throws Exception {
        for (CompanyInformationBean companyInformationBean : CompanyInformationBeanList) {
            List<OriginalBean> originalBeans = originalMapper.findByCompanyInformationId(companyInformationBean.getCompanyInformationId());
            if (originalBeans != null && originalBeans.size() > 0) {
                companyInformationBean.setOriginalList(originalBeans);
            }
        }
    }

    /**
     * 根据customerId及holder查询公司持有人持有的原件
     * return
     * Author dzw
     * param
     * Date 2019-8-8
     */
    public List<OriginalBean> findOriginalNumberByCustomerIdAndHolder(String customerId,String holder)throws Exception {
        return companyinformationMapper.findOriginalNumberByCustomerIdAndHolder(customerId,holder);
    };

    @Override
    public CompanyInformationVo findById(String CompanyInformationId) throws Exception {
        CompanyInformationBean companyInformationBean = new CompanyInformationBean();
        companyInformationBean = companyinformationMapper.findById(CompanyInformationId);
        CompanyInformationVo companyinformationVo = ConvertUtil.objectCopyParams(companyInformationBean, CompanyInformationVo.class);
        return companyinformationVo;
    }

    @Override
    public Map<String, Integer> holdCountAndToBeConfirmedCountAndOutgoingCountAndLoanInCount() throws Exception {
        int holdCount = 0;//当前登录人持有原件数量
        int toBeConfirmedCount = 0;//需确认条数
        int outgoingCount = 0;//出库中数量
        int loanInCount = 0;//待借入数量
        OriginalBean originalBean = new OriginalBean();
        originalBean.setTenantId(Jurisdiction.getTenant());
        List<OriginalBean> list = originalMapper.holdCountAndToBeConfirmedCountAndOutgoingCountAndLoanInCount(originalBean);
        for (OriginalBean originalBean1 : list
                ) {
            //如果原件持有人和当前登录人相同
            if (!StringUtils.isEmpty(originalBean1.getOriginalHolder())&&originalBean1.getOriginalHolder().equals(Jurisdiction.getUsername())) {
                //如果流转状态为入库
                if (!StringUtils.isEmpty(originalBean1.getOriginalOutStatus()) && originalBean1.getOriginalOutStatus().equals("2")) {
                    holdCount++;
                }
                //如果流转状态为出库中
                if (!StringUtils.isEmpty(originalBean1.getOriginalOutStatus()) && originalBean1.getOriginalOutStatus().equals("1")) {
                    outgoingCount++;
                }
                //如果流转状态为待借入
                if (!StringUtils.isEmpty(originalBean1.getOriginalOutStatus()) && originalBean1.getOriginalOutStatus().equals("3")) {
                    toBeConfirmedCount++;
                }
            }
            //如果流转对象和当前登录人相同
            if (!StringUtils.isEmpty(originalBean1.getOriginalOutTo())&&originalBean1.getOriginalOutTo().equals(Jurisdiction.getUsername())) {
                //如果流转状态为出库中
                if (!StringUtils.isEmpty(originalBean1.getOriginalOutStatus()) && originalBean1.getOriginalOutStatus().equals("1")) {
                    toBeConfirmedCount++;
                }
                //如果流转状态为待借入
                if (!StringUtils.isEmpty(originalBean1.getOriginalOutStatus()) && originalBean1.getOriginalOutStatus().equals("3")) {
                    loanInCount++;
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("holdCount", holdCount);
        map.put("toBeConfirmedCount", toBeConfirmedCount);
        map.put("outgoingCount", outgoingCount);
        map.put("loanInCount", loanInCount);

        return map;
    }
    /**
     * 导入工商原件
     * return void
     * Author dzw
     * param excelDatas
     * Date 2019-08-7
     */
    public StringBuffer readExcelBusinessDatasSaveOrUpdate(List<PageData> excelDatas) throws Exception {
        //组装需要保存的数据
        Map assemblyInfo = assemblyDatas(excelDatas,"business");
        List<Map> notExistCompanyInCustomer =  ReturnNotExistDatasAndInsert((List<PageData>) assemblyInfo.get("preInsetDatas"),"business");
        return returnResultInformation(notExistCompanyInCustomer);
    };

    //从excel组装好数据,开始整理那些数据是存在的,那些数据是插入.
    private List<Map> ReturnNotExistDatasAndInsert(List<PageData> excellDatas,String originalType) throws Exception {
        Map AllExcellDatas = validationNameExistWithReturnAllDatas(excellDatas);
        if("finance".equals(originalType)) {
            insetOriginalFinance((List<Map>) AllExcellDatas.get("existDatas"));
        }else {
            //批量保存
            insetOrUpdateToCompanyinformation((List<Map>) AllExcellDatas.get("existDatas"));
        }
        //返回结果信息
        return (List<Map>)AllExcellDatas.get("notExistDatas");
    }
    //获取客户表里的customer_id及验证公司信息表里是否已有数据
    private Map<String ,List<Map>> validationNameExistWithReturnAllDatas(List<PageData> excellDatas) throws Exception{
        SimpleDateFormat dateRule = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map> notExistDatas = new ArrayList<Map>();
        List<Map> existDatas = new ArrayList<Map>();
        Map<String ,List<Map>> result = new HashMap();
        CustomerBean CompanyNameParma = new CustomerBean();
        for(PageData parmas : excellDatas) {
            CompanyNameParma.setCompanyName(parmas.getString("COMPANY_NAME"));
            //查询客户表获取此公司的customerId
            List<CustomerBean> oldDatas = customerMapper.queryCompanyInformationByCompanyName(CompanyNameParma);
            if(oldDatas.size() == 0) {
                //组装公司信息bean
                CompanyInformationBean notExistdata = new CompanyInformationBean();
                notExistdata.setCompanyName(parmas.getString("COMPANY_NAME"));
                //组装不存在的公司
                Map<String,CompanyInformationBean> companyData = new HashMap<String,CompanyInformationBean>();
                companyData.put("company",notExistdata);
                notExistDatas.add(companyData);
            }else {
                //组装公司信息bean
                CompanyInformationBean companyInformation = new CompanyInformationBean();
                companyInformation.setCustomerId(oldDatas.get(0).getCustomerId());
                companyInformation.setCompanyName(oldDatas.get(0).getCompanyName());
                companyInformation.setRemark(parmas.getString("REMARK"));
                init(companyInformation,dateRule);
                //组装原件流转bean
                OriginalProcessRecordsBean originalProcessRecords = new OriginalProcessRecordsBean();
                originalProcessRecords.setOriginalFromUsername(parmas.getString("ORIGINAL_HOLDER"));
                originalProcessRecords.setOriginalFromTime(parmas.getString("ORIGINAL_FROM_TIME"));
                originalProcessRecords.setOriginalOutUsername(parmas.getString("ORIGINAL_OUT_USERNAME"));
                originalProcessRecords.setOriginalOutTime(parmas.getString("ORIGINAL_OUT_TIME"));
                //组装公司相关(公司/原件/原件流转)所需所有信息
                Map<String,Object> companyData = new HashMap<String,Object>();
                companyData.put("companyData",companyInformation);
                companyData.put("original",parmas.get("ORIGINAL"));
                companyData.put("originalProcessRecords",originalProcessRecords);
                existDatas.add(companyData);
            }
        }
        result.put("notExistDatas",notExistDatas);
        result.put("existDatas",existDatas);
        return result;
    }
    //初始化需要插入的客户数据相同数据
    private CompanyInformationBean init(CompanyInformationBean source,SimpleDateFormat dateRule) {
        source.setCreateDate(dateRule.format(new Date()));
        source.setCreateUser(Jurisdiction.getUsername());
        source.setUpdateDate(dateRule.format(new Date()));
        source.setUpdateUser(Jurisdiction.getUsername());
        source.setCompanyInformationId(UuidUtil.get32UUID()); // ID
        source.setTenantId(Jurisdiction.getTenant());
        return source;
    }

    private Map<String,Object> assemblyDatas(List<PageData> excelDatas,String originalType) throws Exception{
        Map<String,Object> result = new HashMap();
        //最终组装好的公司数据
        List<PageData>  preInsetDatas= new ArrayList<PageData>();
        //用于去重公司
        Map<String,String> takeOutRepeatDatas = new HashMap();
        int index = 0;
        for(PageData exceldata: excelDatas) {
            PageData saveData = new PageData();
            String companyName = "";
            if(whetherBusiness(originalType)) {
                 companyName = exceldata.getString("var3")==null?"":exceldata.getString("var3").trim();
            }else {
                 companyName = exceldata.getString("var1")==null?"":exceldata.getString("var1").trim();
            }
            saveData.put("COMPANY_NAME",companyName);
            index++;
            if(companyNameNotEmpty(saveData) && takeOutRepeat(takeOutRepeatDatas,companyName)) {
                takeOutRepeatDatas.put(companyName,"1");
                //工商原件读取
                if(whetherBusiness(originalType)) {
                    HashMap original = new HashMap();
                    saveData.put("ORIGINAL_FROM_TIME",exceldata.getString("var1")==null?"":exceldata.getString("var1").trim());
                    saveData.put("ORIGINAL_HOLDER",exceldata.getString("var2")==null?"":exceldata.getString("var2").trim());
                    saveData.put("COMPANY_NAME",exceldata.getString("var3")==null?"":exceldata.getString("var3").trim());
                    saveData.put("REMARK",exceldata.getString("var17")==null?"":exceldata.getString("var17").trim());
                    saveData.put("ORIGINAL",returnOriginal(original,exceldata));
                }else {
                    //财税原件读取
                    HashMap original = new HashMap();
                    saveData.put("ORIGINAL_HOLDER",exceldata.getString("var3")==null?"":exceldata.getString("var3").trim());
                    saveData.put("ORIGINAL",returnFinanceOriginal(original,exceldata));
                }
            }
            preInsetDatas.add(saveData);
        }
        result.put("preInsetDatas",preInsetDatas);
        return  result;
    }
    //公司在数据库中是否存在
    private boolean companyNameNotEmpty(PageData resouce) {
        if(!StringUtils.isEmpty(resouce.getString("COMPANY_NAME"))) {
            return true;
        }
        return false;
    }
    //筛选重复数据
    private boolean takeOutRepeat(Map<String,String> takeOutRepeatDatas,String companyName) {
        if(takeOutRepeatDatas.get(companyName) != "") {
            return true;
        }
        return  false;
    }

    //批量保存
    private void insetOrUpdateToCompanyinformation(List<Map> existDatas) throws Exception{
        for(Map everyCompany : existDatas) {
            CompanyInformationBean company = (CompanyInformationBean)everyCompany.get("companyData");
            PageData original = (PageData)everyCompany.get("original");
            //检查是否存在数据
            CompanyInformationBean existData = companyinformationMapper.findCompanyInformationByCompanyName(company);
            OriginalProcessRecordsBean originalprocess = (OriginalProcessRecordsBean)everyCompany.get("originalProcessRecords");
            if(existData == null) {
                companyinformationMapper.save(company);
                assemblyOriginalAndInsert(original,company,originalprocess);
            }else {
                companyinformationMapper.edit(company);
            }
        }
    }

    //批量保存财务原件
    private void insetOriginalFinance(List<Map> existDatas) throws Exception{
        for(Map everyCompany : existDatas) {
            CompanyInformationBean company = (CompanyInformationBean)everyCompany.get("companyData");
            PageData original = (PageData)everyCompany.get("original");
            //检查是否存在数据
            CompanyInformationBean existData = companyinformationMapper.findCompanyInformationByCompanyName(company);
            OriginalProcessRecordsBean originalprocess = (OriginalProcessRecordsBean)everyCompany.get("originalProcessRecords");
            if(existData != null) {
                assemblyFinanceOriginalAndInsert(original,company,originalprocess);
            }
        }
    }
    //组装结果信息
    private StringBuffer returnResultInformation(List<Map> notExistCompanyInCustomer) {
        StringBuffer errorInfor = new StringBuffer();
        if(notExistCompanyInCustomer.size() > 0) {
            for(int index = 0;index<notExistCompanyInCustomer.size();index++) {
                CompanyInformationBean notExistCompany = (CompanyInformationBean)notExistCompanyInCustomer.get(index).get("company");
                if(index == notExistCompanyInCustomer.size()) {
                    errorInfor.append(notExistCompany.getCompanyName()+",这些公司不存在,前先添加客户信息!");
                }else {
                    errorInfor.append(notExistCompany.getCompanyName()+",");
                }
            }
        }else {
            errorInfor.append("插入成功!");
        }
        return errorInfor;
    }

    /**
     * 组装财税原件并且存入
     * return void
     * Author dzw
     * param original ,company,originalprocess
     * Date 2019-08-7
     */
    private void assemblyFinanceOriginalAndInsert(PageData original,CompanyInformationBean company,OriginalProcessRecordsBean originalprocess) throws Exception{
        OriginalBean originalbean = ConvertUtil.objectCopyParams(company,OriginalBean.class);
        originalbean.setCompanyInformationId(company.getCompanyInformationId());
        //放入持有人及归还人,查询持有人部门并放入
        if(originalprocess.getOriginalFromUsername() != null || !"".equals(originalprocess.getOriginalFromUsername())) {
            originalbean.setOriginalHolder(originalprocess.getOriginalFromUsername());
            StaffBean staffBean = new StaffBean();
            staffBean.setUserId(originalprocess.getOriginalFromUsername());
            staffBean = staffService.findOneByUserName(staffBean);
            originalbean.setOriginalHoldStatus("2");//持有人在公司内部
            originalbean.setOriginalOutStatus("2");//流转状态入库
            originalbean.setOriginalType("2");//财务原件
            originalbean.setOriginalHolderDepartment(staffBean.getDepartmentId());
            originalbean.setOriginalOutTo(originalprocess.getOriginalOutUsername());
        }
        for(Object key: original.keySet()) {
            originalbean.setOriginalId(UuidUtil.get32UUID());
            if("其它".equals((String)key)) {
                originalbean.setOtherFinance((String)original.get(key));
                originalbean.setOriginalAmount("1");
            }else {
                originalbean.setOriginalName((String)key);
                originalbean.setOriginalAmount((String)original.get(key));
            }
            originalMapper.save(originalbean);
            //assemblyOriginalprocessrecordsAndInsert(originalbean,originalprocess);
        }

    }

    /**
     * 组装工商原件并且存入
     * return void
     * Author dzw
     * param original ,company,originalprocess
     * Date 2019-08-7
     */
    private void assemblyOriginalAndInsert(PageData original,CompanyInformationBean company,OriginalProcessRecordsBean originalprocess) throws Exception{
        OriginalBean originalbean = ConvertUtil.objectCopyParams(company,OriginalBean.class);
        originalbean.setCompanyInformationId(company.getCompanyInformationId());
        //放入持有人及归还人,查询持有人部门并放入
        if(originalprocess.getOriginalFromUsername() != null || !"".equals(originalprocess.getOriginalFromUsername())) {
            originalbean.setOriginalHolder(originalprocess.getOriginalFromUsername());
            StaffBean staffBean = new StaffBean();
            staffBean.setUserId(originalprocess.getOriginalFromUsername());
            staffBean = staffService.findOneByUserName(staffBean);
            originalbean.setOriginalHoldStatus("2");//持有人在公司内部
            originalbean.setOriginalOutStatus("2");//流转状态入库
            originalbean.setOriginalType("1");//工商原件
            originalbean.setOriginalHolderDepartment(staffBean.getDepartmentId());
            originalbean.setOriginalOutTo(originalprocess.getOriginalOutUsername());
        }
       for(Object key: original.keySet()) {
           originalbean.setOriginalId(UuidUtil.get32UUID());
           if("其它".equals((String)key)) {
               originalbean.setOtherBusiness((String)original.get(key));
               originalbean.setOriginalAmount("1");
           }else {
               originalbean.setOriginalName((String)key);
               originalbean.setOriginalAmount((String)original.get(key));
           }
           originalMapper.save(originalbean);
           //assemblyOriginalprocessrecordsAndInsert(originalbean,originalprocess);
       }

    }
    /**
     * 组装原件并返回
     * return HashMap
     * Author dzw
     * param original,exceldata
     * Date 2019-08-7
     */
    private HashMap returnOriginal(HashMap original,PageData exceldata) {
        if(exceldata.getString("var4") != null || !"0".equals(exceldata.getString("var4"))) {
            original.put("执照正本",exceldata.getString("var4").trim());
        }
        if(exceldata.getString("var5") != null || !"0".equals(exceldata.getString("var5"))) {
            original.put("执照副本",exceldata.getString("var5").trim());
        }
        if(exceldata.getString("var6") != null || !"0".equals(exceldata.getString("var6"))) {
            original.put("公章",exceldata.getString("var6").trim());
        }
        if(exceldata.getString("var7") != null || !"0".equals(exceldata.getString("var7"))) {
            original.put("财务章",exceldata.getString("var7").trim());
        }
        if(exceldata.getString("var8") != null || !"0".equals(exceldata.getString("var8"))) {
            original.put("法人章",exceldata.getString("var8").trim());
        }
        if(exceldata.getString("var9") != null || !"0".equals(exceldata.getString("var9"))) {
            original.put("发票章",exceldata.getString("var9").trim());
        }
        if(exceldata.getString("var10") != null || !"0".equals(exceldata.getString("var10"))) {
            original.put("开许",exceldata.getString("var10").trim());
        }
        if(exceldata.getString("var11") != null || !"0".equals(exceldata.getString("var11"))) {
            original.put("税控盘",exceldata.getString("var11").trim());
        }
        if(exceldata.getString("var12") != null || !"0".equals(exceldata.getString("var12"))) {
            original.put("领购薄",exceldata.getString("var12").trim());
        }
        if(exceldata.getString("var13") != null || !"0".equals(exceldata.getString("var13"))) {
            original.put("银行信用证",exceldata.getString("var13").trim());
        }
        if(exceldata.getString("var14") != null || !"0".equals(exceldata.getString("var14"))) {
            original.put("法人身原件",exceldata.getString("var14").trim());
        }
        if(exceldata.getString("var15") != null || !"0".equals(exceldata.getString("var15"))) {
            original.put("其他",exceldata.getString("var15").trim());
        }
        return original;
    }

    /**
     * 组装财税原件并返回
     * return HashMap
     * Author dzw
     * param original,exceldata
     * Date 2019-08-7
     */
    private HashMap returnFinanceOriginal(HashMap original,PageData exceldata) {
        String financeOriginal = exceldata.getString("var2")==null?"":exceldata.getString("var2").trim();
        if(!"".equals(financeOriginal)) {
            String [] originalNumber= financeOriginal.split(",");
            if(originalNumber.length > 0) {
                for(String originalFinance : originalNumber) {
                    String [] originalData = originalFinance.split(":");
                    original.put(originalData[0],originalData[1]);
                }
            }
        }
        return original;
    }
    /**
     * 组装原件交接记录并且存入
     * return void
     * Author dzw
     * param original,originalprocess
     * Date 2019-08-7
     */
    private void assemblyOriginalprocessrecordsAndInsert(OriginalBean originalbean,OriginalProcessRecordsBean originalprocess) throws Exception{
        originalprocess.setOriginalProcessRecordsId(UuidUtil.get32UUID());
        originalprocess.setOriginalId(originalbean.getOriginalId());
        originalprocess.setCreateUser(Jurisdiction.getUsername());
        originalprocess.setCreateDate(originalbean.getCreateDate());
        originalprocess.setTenantId(Jurisdiction.getTenant());
        originalProcessRecordsMapper.save(originalprocess);
    }

    private Boolean whetherBusiness(String originalType) {
        return "business".equals(originalType)?true:false;
    }

    /**
     * 导入工商原件
     * return void
     * Author dzw
     * param excelDatas
     * Date 2019-08-7
     */
    public StringBuffer readExcelFinanceDatasSaveOrUpdate(List<PageData> excelDatas) throws Exception {
        //组装需要保存的数据
        Map assemblyInfo = assemblyDatas(excelDatas,"finance");
        List<Map> notExistCompanyInCustomer =  ReturnNotExistDatasAndInsert((List<PageData>) assemblyInfo.get("preInsetDatas"),"finance");
        return returnResultInformation(notExistCompanyInCustomer);
    };
}


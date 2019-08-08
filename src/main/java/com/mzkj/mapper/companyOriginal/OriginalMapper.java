package com.mzkj.mapper.companyOriginal;

import com.mzkj.bean.CompanyInformationBean;
import com.mzkj.bean.OriginalBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 公司原件详情接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
@Mapper
public interface OriginalMapper{

	/**新增
	 * @param originalBean
	 * @throws Exception
	 */
	public void save(OriginalBean originalBean)throws Exception;

	/**
	 * findById
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-25 15:27
	 */
	public OriginalBean findById(OriginalBean originalBean)throws Exception;
	/**删除
	 * @param ORIGINAL_ID
	 * @throws Exception
	 */
	public void delete(String ORIGINAL_ID)throws Exception;
	
	/**修改
	 * @param originalBean
	 * @throws Exception
	 */
	public void edit(OriginalBean originalBean)throws Exception;
	/**
	 * 借出
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-24 15:36
	 */
	public void loanOut(OriginalBean originalBean)throws Exception;

	/**
	 * 借入
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-24 17:25
	 */
    public void loanIn(OriginalBean originalBean)throws Exception;

	/**
	 * 借入确认
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-24 15:50
	 */
	public void loanOutConfirmed(OriginalBean originalBean)throws Exception;

	/**
	 * 驳回
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-24 17:02
	 */
    public void reject(OriginalBean originalBean)throws Exception;

	/**列表
	 * @param originalBean
	 * @throws Exception
	 */
	public List<OriginalBean> list(OriginalBean originalBean)throws Exception;

	/**
	 * 根据公司信息ID查询
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-23 13:59
	 */
	public List<OriginalBean> findByCompanyInformationId(String companyInformationId) throws Exception;

	/**
	 * 统计当前登录人持有原件数量、需确认条数、出库中数量、待借入数量
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-30 17:27
	 */
	public List<OriginalBean> holdCountAndToBeConfirmedCountAndOutgoingCountAndLoanInCount(OriginalBean originalBean) throws Exception;


	/**
	 * 根据交接人及被交接人和原件ID更新原件持有人
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-23 13:59
	 */
	public void handoverOriginalByHolderAndOriginalId(String holder,String originalId) throws Exception;
}


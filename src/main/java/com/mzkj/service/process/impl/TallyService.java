package com.mzkj.service.process.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.CommerceBean;
import com.mzkj.bean.TallyBean;
import com.mzkj.convert.FollowUpConvert;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.CommerceProcessQueryVo;
import com.mzkj.vo.process.TallyProcessQueryVo;
import com.mzkj.vo.process.TallyQueryVo;
import com.mzkj.vo.process.TallyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import com.mzkj.service.process.TallyManager;
import com.mzkj.mapper.process.TallyMapper;

import java.util.List;

/**
 * 说明： 代理记账
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 * @version
 */
@Service("tallyService")
public class TallyService implements TallyManager{

    @Autowired
    private TallyMapper tallyMapper;

	/**新增
	 * @param tallyVo
	 * @throws Exception
	 */
    @Override
	public TallyVo save(TallyVo tallyVo)throws Exception{
        TallyBean tallyBean = ConvertUtil.objectCopyParams(tallyVo, TallyBean.class);
        tallyMapper.save(tallyBean);
        tallyVo = ConvertUtil.objectCopyParams(tallyBean, TallyVo.class);
        return tallyVo;
	}

	/**删除
	 * @param tallyId
	 * @throws Exception
	 */
    @Override
	public void delete(String tallyId)throws Exception{
        tallyMapper.delete(tallyId);
	}

	/**修改
	 * @param tallyVo
	 * @throws Exception
	 */
    @Override
	public void edit(TallyVo tallyVo)throws Exception{
        TallyBean tallyBean = ConvertUtil.objectCopyParams(tallyVo, TallyBean.class);
        tallyMapper.edit(tallyBean);
	}

	/**列表
	 * @param tallyQueryVo
	 * @throws Exception
	 */
    @Override
	public PageInfo<TallyQueryVo> list(TallyQueryVo tallyQueryVo)throws Exception{
        //将vo转DO并将分页信息传到pageHelper
        TallyBean tallyBean = PageUtil.startPageAndObjectCopyParams(tallyQueryVo, TallyBean.class);
        //设置租户ID
        tallyBean.setTenantId(Jurisdiction.getTenant());
        List<TallyBean> tallyPageBean = tallyMapper.list(tallyBean);
        //DO转VO
        List<TallyQueryVo> tallyQueryVoList = (List<TallyQueryVo>) ConvertUtil.castListObjectToTargetList(tallyPageBean,TallyQueryVo.class);
        PageInfo<TallyQueryVo> pageInfo = new PageInfo<>(tallyQueryVoList);
         return pageInfo;
	}

	@Override
	public PageInfo<FollowUpQueryVo> listProcessByUser(FollowUpQueryVo followUpQueryVo) throws Exception {
		//将vo转DO并将分页信息传到pageHelper
		TallyBean tallyBean =
			FollowUpConvert.followUpVoToTallyProcessBean(followUpQueryVo);
		//设置租户ID
		tallyBean.setTenantId(Jurisdiction.getTenant());
		tallyBean.setSignPerson(Jurisdiction.getUsername());
		List<TallyBean> tallyBeanPageBean = tallyMapper.listProcessByUser(tallyBean);
		//DO转VO
		List<FollowUpQueryVo> followUpQueryVoList =
			FollowUpConvert.tallyProcessBeanToFollowUpVo(tallyBeanPageBean);
		PageInfo<FollowUpQueryVo> pageInfo = new PageInfo<>(followUpQueryVoList);
		return pageInfo;
	}

}


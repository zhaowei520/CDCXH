package com.mzkj.service.companyOriginal.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.OriginalBean;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.companyOriginal.OriginalQueryVo;
import com.mzkj.vo.companyOriginal.OriginalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import com.mzkj.service.companyOriginal.OriginalManager;
import com.mzkj.mapper.companyOriginal.OriginalMapper;

import java.util.List;

/** 
 * 说明： 公司原件详情
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
@Service("originalService")
public class OriginalService implements OriginalManager{

    @Autowired
    private OriginalMapper originalMapper;
	
	/**新增
	 * @param originalVo
	 * @throws Exception
	 */
    @Override
	public OriginalVo save(OriginalVo originalVo)throws Exception{
        OriginalBean originalBean = ConvertUtil.objectCopyParams(originalVo, OriginalBean.class);
        originalMapper.save(originalBean);
        originalVo = ConvertUtil.objectCopyParams(originalBean, OriginalVo.class);
        return originalVo;
	}
	
	/**删除
	 * @param ORIGINAL_ID
	 * @throws Exception
	 */
    @Override
	public void delete(String ORIGINAL_ID)throws Exception{
        originalMapper.delete(ORIGINAL_ID);
	}
	
	/**修改
	 * @param originalVo
	 * @throws Exception
	 */
    @Override
	public void edit(OriginalVo originalVo)throws Exception{
        OriginalBean originalBean = ConvertUtil.objectCopyParams(originalVo, OriginalBean.class);
        originalMapper.edit(originalBean);
	}
	
	/**列表
	 * @param originalQueryVo
	 * @throws Exception
	 */
    @Override
	public PageInfo<OriginalQueryVo> list(OriginalQueryVo originalQueryVo)throws Exception{
        //将vo转DO并将分页信息传到pageHelper
        OriginalBean originalBean =PageUtil.startPageAndObjectCopyParams(originalQueryVo,OriginalBean.class);
        List<OriginalBean> originalPageBean = originalMapper.list(originalBean);
        PageInfo<OriginalBean> pageInfo = new PageInfo<>(originalPageBean);
        //将DO转vo
        PageInfo<OriginalQueryVo> originalPageVo = ConvertUtil.objectCopyParams(pageInfo, PageInfo.class);
		return originalPageVo;
	}
	
}


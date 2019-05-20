package com.mzkj.service.system.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.DictionariesBean;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.system.DictionariesQueryVo;
import com.mzkj.vo.system.DictionariesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import com.mzkj.service.system.DictionariesManager;
import com.mzkj.mapper.system.DictionariesMapper;

import java.util.ArrayList;
import java.util.List;

/** 
 * 说明： 字典
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
@Service("dictionariesService")
public class DictionariesService implements DictionariesManager{

    @Autowired
    private DictionariesMapper dictionariesMapper;
	
	/**新增
	 * @param dictionariesVo
	 * @throws Exception
	 */
    @Override
	public DictionariesVo save(DictionariesVo dictionariesVo)throws Exception{
        DictionariesBean dictionariesBean = ConvertUtil.objectCopyParams(dictionariesVo, DictionariesBean.class);
        dictionariesMapper.save(dictionariesBean);
        dictionariesVo = ConvertUtil.objectCopyParams(dictionariesBean, DictionariesVo.class);
        return dictionariesVo;
	}
	
	/**删除
	 * @param DICTIONARIES_ID
	 * @throws Exception
	 */
    @Override
	public void delete(String DICTIONARIES_ID)throws Exception{
        dictionariesMapper.delete(DICTIONARIES_ID);
	}
	
	/**修改
	 * @param dictionariesVo
	 * @throws Exception
	 */
    @Override
	public void edit(DictionariesVo dictionariesVo)throws Exception{
        DictionariesBean dictionariesBean = ConvertUtil.objectCopyParams(dictionariesVo, DictionariesBean.class);
        dictionariesMapper.edit(dictionariesBean);
	}
	
	/**列表
	 * @param dictionariesQueryVo
	 * @throws Exception
	 */
    @Override
	public PageInfo<DictionariesQueryVo> list(DictionariesQueryVo dictionariesQueryVo)throws Exception{
        //将vo转DO并将分页信息传到pageHelper
        DictionariesBean dictionariesBean =PageUtil.startPageAndObjectCopyParams(dictionariesQueryVo,DictionariesBean.class);
        List<DictionariesBean> dictionariesPageBean = dictionariesMapper.list(dictionariesBean);
        PageInfo<DictionariesBean> pageInfo = new PageInfo<>(dictionariesPageBean);
        //将DO转vo
        PageInfo<DictionariesQueryVo> dictionariesPageVo = ConvertUtil.objectCopyParams(pageInfo, PageInfo.class);
		return dictionariesPageVo;
	}

	/**
	 * 根据编码获取菜单子集
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-24 11:09
	 */
	@Override
	public List<DictionariesQueryVo> findChildlListByBianma(String bianma) throws Exception {
		DictionariesBean dictionariesBean = new DictionariesBean();
		dictionariesBean.setBianma(bianma);
		//通过编码查询菜单
		DictionariesBean dictionarieBean = dictionariesMapper.findByBianma(dictionariesBean);
		//通过parentId查询
		List<DictionariesBean> dictionariesBeans = dictionariesMapper.findChildListByParentId(dictionarieBean.getDictionariesId());
		List<DictionariesQueryVo> dictionariesQueryVoList = new ArrayList<>();

		for (DictionariesBean dictionariesBean1:dictionariesBeans
			 ) {
			dictionariesQueryVoList.add(ConvertUtil.objectCopyParams(dictionariesBean1, DictionariesQueryVo.class));
		}
		return dictionariesQueryVoList;
	}

	/**
	 * 通过编码查询字典TREE
	 * return
	 * Author luosc
	 * param
	 * Date 2019-05-15 14:33
	 */
	@Override
	public DictionariesQueryVo findDicTreeByBianma(String bianma) throws Exception {
		DictionariesBean dictionariesBean = new DictionariesBean();
		dictionariesBean.setBianma(bianma);
		//通过编码查询菜单
		DictionariesBean dictionarieBean = dictionariesMapper.findByBianma(dictionariesBean);
		//do转VO
		DictionariesQueryVo dictionariesQueryVo = ConvertUtil.objectCopyParams(dictionarieBean, DictionariesQueryVo.class);
		//通过parentId查询
		List<DictionariesBean> dictionariesBeans = dictionariesMapper.findChildListByParentId(dictionarieBean.getDictionariesId());
		List<DictionariesQueryVo> list = new ArrayList<>();
		for (DictionariesBean dictionarieBean1:dictionariesBeans) {
			DictionariesQueryVo dictionariesQueryVo1 = ConvertUtil.objectCopyParams(dictionarieBean1, DictionariesQueryVo.class);
			dictionariesQueryVo1.setChildTreeList(getChildList(dictionariesQueryVo1));
			list.add(dictionariesQueryVo1);
		}
		dictionariesQueryVo.setChildTreeList(list);
		return dictionariesQueryVo;
	}

	private List<DictionariesQueryVo> getChildList(DictionariesQueryVo dictionariesQueryVo) throws Exception {
		List<DictionariesBean>  list=dictionariesMapper.findChildListByParentId(dictionariesQueryVo.getDictionariesId());
		List<DictionariesQueryVo> dictionariesQueryVos = new ArrayList<>();
		for (DictionariesBean d:list) {
			DictionariesQueryVo dictionariesQueryVo1 = ConvertUtil.objectCopyParams(d, DictionariesQueryVo.class);
			dictionariesQueryVo1.setChildTreeList(getChildList(dictionariesQueryVo1));
			dictionariesQueryVos.add(dictionariesQueryVo1);
		}
		return dictionariesQueryVos;
	}
}


package com.mzkj.service.system.menu;

import com.fh.entity.system.Menu;
import com.fh.util.PageData;

import java.util.List;


/**
 * 说明： 菜单服务接口
 * 创建人：zw
 * 创建时间：2019-03-26
 * @version
 */
public interface MenuManager {

  /**
   * 获取指定父类菜单
   * @param pd
   * @return
   * @throws Exception
   */
  public List<Menu> listSubMenuByParentId(PageData pd) throws Exception;

  /**
   * 获取所有菜单
   * @param pd
   * @return
   * @throws Exception
   */
  List<Menu> listAllMenuQx(PageData pd) throws Exception;
}

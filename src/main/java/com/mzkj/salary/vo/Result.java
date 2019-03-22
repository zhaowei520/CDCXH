/**
 * Copyright &copy; 2015-2020 <a href="http://www.xxx.com/">TBM</a> All rights reserved.
 */
package com.mzkj.salary.vo;

/**
 * @Author: zw
 * @Date: 2019/3/18 13:08
 * @Version: 1.0
 */
public class Result<T> {

  private boolean success = true;// 是否成功
  private int status = 200;// 状态代码
  private String msg = "操作成功";// 提示信息
  private T data; // 返回数据

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}

package com.nahsshan.common.exception;

import java.io.Serializable;

import static com.nahsshan.common.exception.ExpPrefix.*;

/**
 * 全局的异常状态码 和 异常描述
 *
 * PS:异常码一共由5位组成，前两位为固定前缀，请参考{@link ExpPrefix}
 * @author J.zhu
 */
@SuppressWarnings("all")
public enum ExpCodeEnum implements Serializable {

    /** 通用异常 */
    UNKNOW_ERROR(COM_EXP_PREFIX + "000", "未知异常"),
    ERROR_404(COM_EXP_PREFIX + "001", "没有该接口"),
    PARAM_NULL(COM_EXP_PREFIX + "002", "参数为空"),
    PARAM_ERROR(COM_EXP_PREFIX + "003", "参数错误"),
    NO_REPEAT(COM_EXP_PREFIX + "004", "请勿重复提交"),
    SESSION_NULL(COM_EXP_PREFIX + "005", "请求头中SessionId不存在"),
    HTTP_REQ_METHOD_ERROR(COM_EXP_PREFIX + "006", "HTTP请求方法不正确"),
    JSONERROR(COM_EXP_PREFIX + "007", "JSON解析异常"),
    UPLOAD_FAILED(COM_EXP_PREFIX + "008","上传文件失败"),
    EMAIL_FORMAT_INCORRECT(COM_EXP_PREFIX + "009","邮箱格式有误！"),

    /** User模块异常 */
    USERNAME_NULL(USER_EXP_PREFIX + "000", "用户名为空"),
    PASSWD_NULL(USER_EXP_PREFIX + "001", "密码为空"),
    AUTH_NULL(USER_EXP_PREFIX + "002", "手机、电子邮件、用户名 至少填一个"),
    LOGIN_FAIL(USER_EXP_PREFIX + "003", "登录失败"),
    UNLOGIN(USER_EXP_PREFIX + "004", "尚未登录"),
    NO_PERMISSION(USER_EXP_PREFIX + "005", "没有权限"),
    PHONE_NULL(USER_EXP_PREFIX + "006", "手机号为空"),
    MAIL_NULL(USER_EXP_PREFIX + "007", "电子邮件为空"),
    USERTYPE_NULL(USER_EXP_PREFIX + "008", "用户类别为空"),
    ROLE_NULL(USER_EXP_PREFIX + "009", "角色为空"),
    ROLEID_NULL(USER_EXP_PREFIX + "010", "roleId为空"),
    MENUIDLIST_NULL(USER_EXP_PREFIX + "011", "menuIdList为空"),
    PERMISSIONIDLIST_NULL(USER_EXP_PREFIX + "012", "permissionIdList为空"),
    NAME_NULL(USER_EXP_PREFIX + "013", "name为空"),

    /** Product模块异常 */
    PRODUCT_NAME_NULL(PROD_EXP_PREFIX + "000", "产品名称为空"),

    /** Order模块异常 */
    PROCESSREQ_ORDERID_NULL(ORDER_EXP_PREFIX + "000", "受理请求中的orderId为空"),
    ORDER_NOT_EXIST(ORDER_EXP_PREFIX + "001", "OrderId未查询到订单信息"),

    /** Analysis模块异常 */
    XXXX_NULL(ANALYSIS_EXP_PREFIX + "000", "XXXX异常"),

    /** Common模块异常*/


    /** Seckill模块异常*/
    SECKILL_MUCH(SEC_KILL_EXP_PREFIX + "000","哎呦喂，人也太多了，请稍后！"),
    SECKILL_SUCCESS(SEC_KILL_EXP_PREFIX + "001","秒杀成功"),
    SECKILL_END(SEC_KILL_EXP_PREFIX + "002","秒杀结束"),
    SECKILL_REPEAT_KILL(SEC_KILL_EXP_PREFIX + "003","重复秒杀"),
    SECKILL_INNER_ERROR(SEC_KILL_EXP_PREFIX + "004","系统异常"),
    SECKILL_DATE_REWRITE(SEC_KILL_EXP_PREFIX + "005","数据篡改"),

    END(ORDER_EXP_PREFIX + "XXX", "XXX");




    private String code;
    private String message;

    ExpCodeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

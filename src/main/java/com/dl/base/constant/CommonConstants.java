package com.dl.base.constant;

/**
 * Created by ace on 2017/8/29.
 */
public class CommonConstants {
    public final static String RESOURCE_TYPE_MENU = "menu";
    public final static String RESOURCE_TYPE_BTN = "button";
    public static final Integer EX_TOKEN_ERROR_CODE = 40101;
    // 用户token异常
    public static final Integer EX_USER_INVALID_CODE = 40102;
    // 客户端token异常
    public static final Integer EX_CLIENT_INVALID_CODE = 40131;
    public static final Integer EX_CLIENT_FORBIDDEN_CODE = 40331;
    public static final Integer EX_OTHER_CODE = 500;
    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    public static final String CONTEXT_KEY_USER_NAME = "currentUser";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String CONTEXT_KEY_USER_ADDRESS = "currentUserAddress";
    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_NAME = "name";

    public static final String HTTP_HEADER_ADDRESS = "dl-address";
    public static final String SMS_URL = "http://49.232.65.109:8080/sms/send";
    
    
    public static final Integer BUSINESS_ID_REWARD = 9;
    public static final Integer BUSINESS_ID_WITHDRAW = 8;
    
    public static final String HTTP_HEADER_DEVICE = "dl-device";
    public static final String CONTEXT_KEY_USER_DEVICE = "currentUserDevice";
    
    //消息模板
    public static final String FORMAT_REWARD_PUSH_TITLE = "中奖啦";
    public static final String FORMAT_REWARD_PUSH_DESC = "恭喜您购买的【{0}】中了{1}元";
    public static final String FORMAT_REWARD_TITLE = "中奖通知";
    public static final String FORMAT_REWARD_CONTENT = "中奖{0}元";
    public static final String FORMAT_REWARD_CONTENT_DESC = "中奖金额已打入您的余额";
    public static final String FORMAT_REWARD_MSG_DESC = "彩种：{0}#投注金额：{1}元#投注时间：{2}";
    public static final String FORMAT_REWARD_DESC = "恭喜您购买的{0}中了{1}元";
    
    //出票失败
    public static final String FORMAT_PRINTLOTTERY_TITLE = "出票失败通知";
    public static final String FORMAT_PRINTLOTTERY_CONTENT = "出票失败，请重新下单";
    public static final String FORMAT_PRINTLOTTERY_CONTENT_DESC = "出票失败";
    public static final String FORMAT_PRINTLOTTERY_MSG_DESC = "彩种：{0}#投注金额：{1}元#投注时间：{2}";
    
    //提现成功
    public static final String FORMAT_WITHDRAW_SUC_TITLE = "提现成功通知";
    public static final String FORMAT_WITHDRAW_FAIL_TITLE = "提现失败通知";
    public static final String FORMAT_WITHDRAW_CONTENT = "提现{0}元";
    public static final String FORMAT_WITHDRAW_SUC_CONTENT_DESC = "提现成功";
    public static final String FORMAT_WITHDRAW_FAIL_CONTENT_DESC = "提现失败";
    public static final String FORMAT_WITHDRAW_MSG_DESC = "申请时间：{0}#审核通过时间：{1}#提现成功时间：{2}";
    public static final String FORMAT_WITHDRAW_MSG_FAIL_DESC = "申请时间：{0}#审核时间：{1}#提现失败时间：{2}";
    
    public static final String FORMAT_PRINTLOTTERY_PUSH_TITLE = "出票失败";
    public static final String FORMAT_PRINTLOTTERY_PUSH_DESC = "抱歉，您购买的【{0}】出票失败，请重新购买";
    
    public static final String FORMAT_BONUS_TITLE = "优惠券到期通知";
    public static final String FORMAT_BONUS_DESC = "您有优惠券即将到期，请尽快使用";
    
//    1、中奖提示：其中“竞彩足球”是彩种，“9”是中奖金额，这2个字段由服务端控制
//    标题＝中奖啦
//    描述＝恭喜您购买的【竞彩足球】中了9元
//    推送时间＝实时推送
//
//    2、出票失败提示：其中“竞彩足球”是彩种，这个字段由服务端控制
//    标题＝出票失败
//    描述＝抱歉，您购买的【竞彩足球】出票失败，请重新购买
//    推送时间＝实时推送
//
//    3、优惠券到期提示：
//    标题＝优惠券到期通知
//    描述＝您有优惠券即将到期，请尽快使用
//    推送时间＝到期日前1天晚上7点推送
}

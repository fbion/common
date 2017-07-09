package utils.maimai;

import com.yunsign.business.entity.ContractEntity;
import com.yunsign.business.entity.EmailReminderEntity;
import com.yunsign.business.entity.EmailTemplateEntity;
import com.yunsign.business.entity.IdentityEntity;
import com.yunsign.business.mappper.DataManageMapper;
import com.yunsign.business.service.DataManageService;
import com.yunsign.common.Constant;
import com.yunsign.common.MailSendImpl;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.yunsign.util.TestConfig.url;

/**
 * Created by Administrator on 2016/11/18.
 */
public class ControllerUtil {
    /**
     * 判断map里是否存在目标数据
     * @param mapList
     * @param des
     * @param desName
     * @param lineNumber
     * @param message
     * @return
     */
    public static boolean mapContains(List<Map<String, Integer>> mapList, String[] des, String[] desName, int lineNumber, StringBuffer message) {
        boolean result = true;
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Integer> map = mapList.get(i);
            if(map.containsKey(des[i])) {
                System.out.println("第" + lineNumber + "行" + desName[i] + "与第" + map.get(des[i]) + "行数据重复,不符合该数据的唯一性要求。");
                message.append("第" + lineNumber + "行" + desName[i] + "与第" + map.get(des[i]) + "行数据重复,不符合该数据的唯一性要求。");
                result = false;
            } else {
                map.put(des[i], lineNumber);
            }
        }
        return result;
    }

    /**
     * 判断字符串数字是否全是blank
     * @param array
     * @return
     */
    public static boolean isAllBlank(String... array) {
        for (String s : array) {
            if(StringUtils.isNotBlank(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对一组参数的长度按要求进行校验
     * @param des
     * @param desName
     * @param minLength
     * @param maxLength
     * @param lineNumber
     * @param message
     * @return
     */
    public static boolean checkParam(String[] des, String[] desName, Integer[] minLength, Integer[] maxLength, int lineNumber, StringBuffer message){
        boolean result = true;
        for (int i = 0; i < des.length; i++) {
            System.out.println(i);
            if(minLength[i] != null) {
                if(StringUtils.isBlank(des[i])) {
                    System.out.println("第" + lineNumber + "行数据的" + desName[i]+ "为空。");
                    message.append("第" + lineNumber + "行数据的" + desName[i]+ "为空。");
                    result = false;
                } else if (des[i].length() < minLength[i]) {
                    System.out.println("第" + lineNumber + "行数据的" + desName[i]+ "长度不能小于" + minLength[i] + "个字符。");
                    message.append("第" + lineNumber + "行数据的" + desName[i]+ "长度不能小于" + minLength[i] + "个字符。");
                    result = false;
                }
            }
            if(maxLength[i] != null) {
                if(des[i] != null && des[i].length() > maxLength[i]) {
                    System.out.println("第" + lineNumber + "行数据的" + desName[i]+ "长度不能超过" + maxLength[i] + "个字符。");
                    message.append("第" + lineNumber + "行数据的" + desName[i]+ "长度不能超过" + maxLength[i] + "个字符。");
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * 对一列参数的长度按要求进行校验
     * @param des
     * @param desName
     * @param minLength
     * @param maxLength
     * @return
     */
    public static String checkParam(String des[], String[] desName, Integer[] minLength, Integer[] maxLength){
        for (int i = 0; i < des.length; i++) {
            if(minLength[i] != null) {
                if(StringUtils.isBlank(des[i])) {
                    System.out.println(desName[i]+ "不能为空");
                    return desName[i]+ "不能为空";
                } else if (des[i].length() < minLength[i]) {
                    System.out.println(desName[i]+ "长度不能小于" + minLength[i] + "个字符。");
                    return desName[i]+ "长度不能小于" + minLength[i] + "个字符。";
                }
            }
            if(maxLength[i] != null) {
                if(des[i] != null && des[i].length() > maxLength[i]) {
                    System.out.println(desName[i]+ "长度不能超过" + maxLength[i] + "个字符");
                    return desName[i]+ "长度不能超过" + maxLength[i] + "个字符";
                }
            }
        }
        return null;
    }

    /**
     * 组装查询参数
     * @param request
     * @param map
     * @return
     */
    public static String assembleUrlWithParams(HttpServletRequest request, Map<String, Object> map) {
        StringBuffer url = new StringBuffer(request.getRequestURI() + "?");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(entry.getValue() != null && StringUtils.isNotEmpty(entry.getValue().toString())) {
                url.append(entry.getKey() + "=" + entry.getValue() + "&");
            } else {
                url.append(entry.getKey() + "=&");
            }
        }
        url.deleteCharAt(url.length() - 1);
        return url.toString();
    }

    /**
     * 创建用户失败发送邮箱通知管理员
     * @param message
     */
    public static void sendMailToAdministrator(MailSendImpl mailSendImpl, DataManageService dataManageService, String message) {
        EmailTemplateEntity template = dataManageService.queryEmailTemplateByName(Constant.CREATE_ACCOUNT_FAILURE_EMAIL_TEMPLATE_NAME);
        System.out.println(template);
        boolean result = mailSendImpl.sendMail("创建用户失败", template.getEmailContent() + message);
        int status = (result ? Constant.EMAIL_REMINDER_STAUS_SENT_SUCCESS : Constant.EMAIL_REMINDER_STAUS_SENT_FAILURE);
        Date now = new Date();
        for (String email : PropertiesUtil.getProperties().readValue("mail.smtp.to").split(",")) {
            EmailReminderEntity emailReminderEntity = new EmailReminderEntity(template.getId(), email, now, status, "admin");
            dataManageService.addEmailReminderEntity(emailReminderEntity);
        }
    }

    /**
     * Service层发送邮箱验证邮件
     * @param mailSendImpl
     * @param dataManageMapper
     * @param id
     * @param email
     * @throws Exception
     */
    public static void sendValidateMail(MailSendImpl mailSendImpl, DataManageMapper dataManageMapper,
                                        HttpServletRequest request, Integer id, String email) throws Exception {
        EmailTemplateEntity template = dataManageMapper.queryEmailTemplateByName(Constant.EMAIL_VALIDATE_EMAIL_TEMPLATE_NAME);
        EmailReminderEntity emailReminderEntity = sendValidateMail(mailSendImpl, template, request, id, email, "N");
        dataManageMapper.addEmailReminderEntity(emailReminderEntity);
    }

    /**
     * Controller层发送邮箱验证邮件，返回EmailReminderEntity实例，调用DataManageService的addEmailReminderEntity方法把发送邮件记录到数据库
     * @param mailSendImpl
     * @param dataManageService
     * @param id
     * @param email
     * @return
     * @throws Exception
     */
    public static EmailReminderEntity sendValidateMail(MailSendImpl mailSendImpl, DataManageService dataManageService,
                                                       HttpServletRequest request, Integer id, String email, String isActivator) throws Exception {
        EmailTemplateEntity template = dataManageService.queryEmailTemplateByName(Constant.EMAIL_VALIDATE_EMAIL_TEMPLATE_NAME);
        return sendValidateMail(mailSendImpl, template, request, id, email, isActivator);
    }

    /**
     * 发送邮箱验证邮件
     * @param mailSendImpl
     * @param template
     * @param id
     * @param email
     * @return
     * @throws Exception
     */
    public static EmailReminderEntity sendValidateMail(MailSendImpl mailSendImpl, EmailTemplateEntity template,
                                                       HttpServletRequest request, Integer id, String email, String flag) throws Exception {
        System.out.println(template);
//        System.out.println("isSecure: " + request.isSecure());
//        System.out.println("getScheme: " + request.getScheme());
//        System.out.println("getProtocol: " + request.getProtocol());
//        String url = request.getScheme() + "://"+request.getServerName() + ":" + request.getServerPort()
        String url = PropertiesUtil.getProperties().readValue("server.protocal") + "://"+request.getServerName() + ":" +  PropertiesUtil.getProperties().readValue("server.port")
                + request.getContextPath()
                + "/accountManage/toEmailResult.do?id=" + Hex.encodeHexString(Des.encrypt(id + ""))
                + "&email=" + Hex.encodeHexString(Des.encrypt(email))
                + "&flag=" + Hex.encodeHexString(Des.encrypt(flag))
                + "&time=" + Hex.encodeHexString(Des.encrypt(System.currentTimeMillis() + ""));
        boolean result = mailSendImpl.sendMail("邮箱验证", template.getEmailContent().replaceAll("url", url), email);
        int status = (result ? Constant.EMAIL_REMINDER_STAUS_SENT_SUCCESS : Constant.EMAIL_REMINDER_STAUS_SENT_FAILURE);
        Date now = new Date();
        return new EmailReminderEntity(template.getId(), email, now, status, "admin");
    }

    /**
     * 发送初始密码或重置的密码给用户
     * @param templateName
     * @param subject
     * @param identityEntities
     */
    public static void sendPasswordToUser(MailSendImpl mailSendImpl, DataManageService dataManageService,
                                          String templateName, String subject, IdentityEntity... identityEntities) {
        EmailTemplateEntity template = dataManageService.queryEmailTemplateByName(templateName);
        System.out.println(template);
        for (IdentityEntity identityEntity : identityEntities) {
            boolean result = mailSendImpl.sendMail(subject, template.getEmailContent().replaceAll("password", identityEntity.getPassword()).replaceAll("account", identityEntity.getAccount()), identityEntity.getEmail());
            int status = (result ? Constant.EMAIL_REMINDER_STAUS_SENT_SUCCESS : Constant.EMAIL_REMINDER_STAUS_SENT_FAILURE);
            Date now = new Date();
            EmailReminderEntity emailReminderEntity = new EmailReminderEntity(template.getId(), identityEntity.getEmail(), now, status, "user");
            dataManageService.addEmailReminderEntity(emailReminderEntity);
        }
    }

    /**
     * 发送百胜签署完成提醒邮件
     * @param mailSendImpl
     * @param dataManageMapper
     * @param contractTitle
     * @param emails
     */
    public static void sendEmailToSupplierSigner(MailSendImpl mailSendImpl, DataManageMapper dataManageMapper, String contractTitle, String[] emails) {
        EmailTemplateEntity template = dataManageMapper.queryEmailTemplateByName("百胜签署完成提醒模板");
        System.out.println(template);
        for (String email : emails) {
            boolean result = mailSendImpl.sendMail("百胜签署完成提醒", template.getEmailContent().replaceAll("title", contractTitle), email);
            int status = (result ? Constant.EMAIL_REMINDER_STAUS_SENT_SUCCESS : Constant.EMAIL_REMINDER_STAUS_SENT_SUCCESS);
            Date now = new Date();
            EmailReminderEntity emailReminderEntity = new EmailReminderEntity(template.getId(), email, now, status, "notify");
            dataManageMapper.addEmailReminderEntity(emailReminderEntity);
        }
    }

    /**
     * 通过邮箱地址获取邮箱登录地址
     * @param email
     * @return
     */
    public static String getLoginUrlByEmail(String email) {
        //获取邮箱帐号的地址
        String fulAddress = email.trim();
        //获取邮箱帐号的后缀
        String mainAddress = fulAddress.substring(fulAddress.lastIndexOf("@")+1);
        String comainAddress = mainAddress.substring(0, mainAddress.lastIndexOf("."));
        switch (comainAddress)
        {
            case "163":   //网易163
                return "http://mail.163.com/";
            case "126":   //  网易126
                return "http://mail.126.com/";
            case "sina":  //  sina
                return "http://mail.sina.com.cn/";
            case "yahoo": //雅虎
                return "http://mail.cn.yahoo.com/";
            case "sohu":  //  搜狐
                return "http://mail.sohu.com/";
            case "yeah":  //网易yeah.net
                return "http://www.yeah.net/";
            case "gmail": //Gmail
                return "http://gmail.google.com/";
            case "hotmail":   //Hotmail
                return "http://www.hotmail.com/";
            case "live":      //Hotmail
                return "http://www.hotmail.com/";
            case "qq":        //QQ
                return "https://mail.qq.com/";
            case "139":       //139
                return "http://mail.10086.cn/";
            default:
                return "mail." + comainAddress;
        }
    }

    /**
     * 获取当前登录用户名
     * @param request
     * @return
     */
    public static String getCurrentUser(HttpServletRequest request) {
        return (String)((Map)request.getSession().getAttribute("current_user")).get("account");
    }

    /**
     * 数组拼接成字符串
     * @param array
     * @return
     */
    public static String convertArrayToString(Object[] array) {
        return Arrays.deepToString(array).replaceAll("\\s", "").replaceAll("\\[", "").replaceAll("\\]", "");
    }

    public static String dealWithEndDate(String endTime){
        if(StringUtils.isNotBlank(endTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(endTime);
                date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
                return sdf.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String dealWithPath(String path) {
        return path.replaceAll("\\\\", "/").replaceAll("//", "/");
    }

    public static void main(String[] args) {
        StringBuffer message = new StringBuffer("");
        System.out.println(checkParam(new String[]{"",null,"asdf","zxcv"},
                new String[]{"1","2","3","4"},
                new Integer[]{1,1,1,1},
                new Integer[]{2,2,3,4},
                5,
                message));
        System.out.println(message);
    }
}

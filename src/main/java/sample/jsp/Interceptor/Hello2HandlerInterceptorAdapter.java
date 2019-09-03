package sample.jsp.Interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;
import sample.jsp.hybris.GlobalMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

public class Hello2HandlerInterceptorAdapter extends HandlerInterceptorAdapter {

    public static final String FLASH = "flash";
    public static final String NO_FLASH = "no_flash";

    public static final String TYPE = "type";
    public static final String MESSAGEHOLDER = "messageHolder";
    public static final String MESSAGEKEY = "messageKey";
    public static final String ATTRIBUTES = "attributes";

    public static final String REQUESTKEY = "CoreGlobalMessage";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        final ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes());
        List<Map> msgList = (List<Map>) attributes.getAttribute(REQUESTKEY, SCOPE_REQUEST);
        if (CollectionUtils.isEmpty(msgList)) {
            msgList = new ArrayList<>();
        }
        for (Map map : msgList) {
            String type = (String) map.get(TYPE);
            String messageKey = (String) map.get(MESSAGEKEY);
            List attributeList = (List) map.get(ATTRIBUTES);
            String messageHolder = (String) map.get(MESSAGEHOLDER);
            GlobalMessage globalMsg = new GlobalMessage();
            globalMsg.setAttributes(attributeList);
            globalMsg.setCode(messageKey);
            if (FLASH.equals(type)) {
                List<GlobalMessage> globalMessages = (List<GlobalMessage>) RequestContextUtils.getOutputFlashMap(request).get(messageHolder);
                if (CollectionUtils.isEmpty(globalMessages)) {
                    globalMessages = new ArrayList<>();
                    RequestContextUtils.getOutputFlashMap(request).put(messageHolder, globalMessages);
                }
                globalMessages.add(globalMsg);
            } else if (NO_FLASH.equals(type) && modelAndView != null && null != modelAndView.getModelMap()) {
                ModelMap modelMap = modelAndView.getModelMap();
                List<GlobalMessage> globalMessages = (List<GlobalMessage>) RequestContextUtils.getOutputFlashMap(request).get(messageHolder);
                if (CollectionUtils.isEmpty(globalMessages)) {
                    globalMessages = new ArrayList<>();
                    modelMap.addAttribute(messageHolder, globalMessages);
                }
                globalMessages.add(globalMsg);
            }
        }
//        FlashMap flashMap = new FlashMap();
//        FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
//        flashMapManager.saveOutputFlashMap(flashMap, request, response);
    }
}

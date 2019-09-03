package sample.jsp.utils;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MyGlobalMessage {


    public static final String CONF_MESSAGES_HOLDER = "accConfMsgs";
    public static final String INFO_MESSAGES_HOLDER = "accInfoMsgs";
    public static final String ERROR_MESSAGES_HOLDER = "accErrorMsgs";

    public static final String FLASH = "flash";
    public static final String NO_FLASH = "no_flash";

    public static final String TYPE = "type";
    public static final String MESSAGEHOLDER = "messageHolder";
    public static final String MESSAGEKEY = "messageKey";
    public static final String ATTRIBUTES = "attributes";

    public static final String REQUESTKEY = "CoreGlobalMessage";


    public static void addConfMessage(final String messageKey)
    {
        addMessage(CONF_MESSAGES_HOLDER, messageKey, null);
    }

    public static void addInfoMessage(final String messageKey)
    {
        addMessage(INFO_MESSAGES_HOLDER, messageKey, null);
    }

    public static void addErrorMessage(final String messageKey)
    {
        addMessage(ERROR_MESSAGES_HOLDER, messageKey, null);
    }

    public static void addMessage(final String messageHolder, final String messageKey,
        final Object[] attributes) {
        addParamToRequest(messageHolder, messageKey, attributes, NO_FLASH);
    }

    public static void addFlashMessage(final String messageHolder, final String messageKey) {
        addParamToRequest(messageHolder, messageKey, null, FLASH);
    }

    public static void addFlashMessage(final String messageHolder, final String messageKey,
        final Object[] attributes) {
        addParamToRequest(messageHolder, messageKey, attributes, FLASH);
    }



    private static void addParamToRequest(String messageHolder, String messageKey, Object[] attributes, String type) {
        final ServletRequestAttributes request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        List<Map> msgList = (List<Map>) request.getAttribute(REQUESTKEY, SCOPE_REQUEST);
        if (CollectionUtils.isEmpty(msgList)) {
            msgList = new ArrayList<>();
        }
        Map msg =  new HashMap<>();
        msg.put(MESSAGEKEY, messageKey);
        msg.put(ATTRIBUTES, attributes != null ? Arrays.asList(attributes) : Collections.emptyList());
        msg.put(MESSAGEHOLDER, messageHolder);
        msg.put(TYPE, type);
        msgList.add(msg);
        request.setAttribute(REQUESTKEY, msgList, SCOPE_REQUEST);
    }

}

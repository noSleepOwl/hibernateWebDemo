package com.cn.sleep.study.consoleimpl;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.alibaba.druid.sql.SQLUtils;

import java.sql.Date;
import java.text.DateFormat;

public class LogFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLoggerName().equals("jdbc.sqlonly") && event.getLevel().levelStr.equals("INFO")) {
            createMessage(event, 1);
        }
       /* if (event.getLoggerName().equals("org.hibernate.type.descriptor.sql.BasicBinder")) {
            createMessage(event, 2);
        }*/
        return FilterReply.ACCEPT;
    }

    private LoggerMessage createMessage(ILoggingEvent event, int type) {
        String msg = event.getMessage();
        if (type == 1) {
            msg = SQLUtils.formatMySql(msg).trim();
        }

        LoggerMessage loggerMessage = new LoggerMessage(
                msg
                , DateFormat.getDateTimeInstance().format(new Date(event.getTimeStamp())),
                event.getThreadName(),
                event.getLoggerName(),
                event.getLevel().levelStr
        );
        loggerMessage.setType(type);
        LoggerQueue.getInstance().push(loggerMessage);
        return loggerMessage;
    }


}  
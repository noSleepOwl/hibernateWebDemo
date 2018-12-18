package com.cn.sleep.study.consoleimpl;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

import java.sql.Date;
import java.text.DateFormat;

public class LogFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLoggerName().equals("jdbc.sqlonly")) {
            createMessage(event, 1);
        }
        if (event.getLoggerName().equals("org.hibernate.type.descriptor.sql.BasicBinder")) {
            createMessage(event, 2);
        }
        return FilterReply.ACCEPT;
    }

    private LoggerMessage createMessage(ILoggingEvent event, int type) {
        LoggerMessage loggerMessage = new LoggerMessage(
                event.getMessage()
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
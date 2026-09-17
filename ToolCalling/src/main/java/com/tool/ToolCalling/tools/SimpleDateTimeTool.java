package com.tool.ToolCalling.tools;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SimpleDateTimeTool {

    // Information tool
    @Tool(description = "Get the current date and time in the users zone.")
    public String getSimpleDateTime(){
        System.out.println("SimpleDateTimeTool is called.");
        return LocalDateTime.now()
                .atZone(LocaleContextHolder.getTimeZone().toZoneId())
                .toString();
    }
}

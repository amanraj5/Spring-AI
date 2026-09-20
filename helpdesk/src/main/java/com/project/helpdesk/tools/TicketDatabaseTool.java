package com.project.helpdesk.tools;

import com.project.helpdesk.entity.Ticket;
import com.project.helpdesk.service.TicketService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketDatabaseTool {
    @Autowired
    private TicketService ticketService;

    @Tool(description = "This tool helps in creating new ticket in database.")
    public Ticket createTicketTool(@ToolParam(description = "Ticket fields required to create ticket") Ticket ticket){
        try{
            System.out.println("TicketDatabaseTool called...");
            return ticketService.createTicket(ticket);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ticket;
    }
}

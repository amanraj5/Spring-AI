package com.springai.agent.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderTools {

    private final Map<String, String> orders = Map.of(
            "1021", "Order on the way...",
            "1022", "Order processing...",
            "1023", "Order shipping...",
            "1024", "Order delivered...",
            "1025", "Order on the way..."
    );

    @Tool(description = "Get the status of an order by its orderId.")
    public String getOrder(String orderId ){
        System.out.println("OrderTools.getOrder called...");
        return orders.getOrDefault(orderId, "Order Not Found.");
    }

    @Tool(description = "Cancel a order by its orderId.")
    public String cancelOrder(String orderId ){
        System.out.println("OrderTools.cancelOrder called...");
        if(orders.containsKey(orderId)){
            return "Order " + orderId + "has been cancelled.";
        }else{
            return "Order Not Found.";
        }
    }

    @Tool(description = "Get total count of customers order.")
    public Integer totalOrder(String orderId ){
        System.out.println("OrderTools.totalOrder called...");
        return orders.size();
    }
}

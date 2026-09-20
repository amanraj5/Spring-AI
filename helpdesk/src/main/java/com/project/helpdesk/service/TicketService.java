package com.project.helpdesk.service;

import com.project.helpdesk.entity.Ticket;
import com.project.helpdesk.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    // Constructor Injection
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    // Create ticket
    @Transactional
    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    // Update Ticket
    public Ticket updateTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    // Get Ticket
    public Ticket getTicket(Long ticketId){
        return ticketRepository.findById(ticketId).orElseThrow();
    }

    // Get Ticket By username
    public Ticket getTicketByUserName(String email){
        return ticketRepository.findByEmail(email).orElseThrow();
    }
}

package uk.gov.dwp.uc.pairtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceImplTest {

    @Test
    void testAdultCheck() {

        InvalidPurchaseException thrown = Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            TicketService ticketService = new TicketServiceImpl();
            TicketTypeRequest[] request = {new TicketTypeRequest(TicketTypeRequest.Type.CHILD,1),new TicketTypeRequest(TicketTypeRequest.Type.INFANT,1)};
            ticketService.purchaseTickets(1000L,request);
        });

        Assertions.assertEquals("An Adult is required to complete the purchase", thrown.getMessage());
    }

    @Test
    void testTicketCheckMoreTwenty() {
        InvalidPurchaseException thrown = Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            TicketService ticketService = new TicketServiceImpl();
            TicketTypeRequest[] request = {new TicketTypeRequest(TicketTypeRequest.Type.ADULT,20),new TicketTypeRequest(TicketTypeRequest.Type.INFANT,1)};
            ticketService.purchaseTickets(1000L,request);
        });

        Assertions.assertEquals("Only can buy 20 Tickets per request", thrown.getMessage());

    }

    @Test
    void testCheckNull() {
        InvalidPurchaseException thrown = Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            TicketService ticketService = new TicketServiceImpl();
            TicketTypeRequest[] request = null;
            ticketService.purchaseTickets(1000L,request);
        });

        Assertions.assertEquals("The purchase is empty", thrown.getMessage());
    }
}
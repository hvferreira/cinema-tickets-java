package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.Arrays;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    //VAR
    private final int PRICE_ADULT = 20;
    private final int PRICE_CHILD = 10;
    private final int PRICE_INFANT = 0;
    private final int MAX_SEAT_PER_REQUEST=20;
    private int numTicketsTotal=0;
    private int numSeats=0;
    private int costTotal=0;
    private TicketPaymentService ticketPaymentService;
    private SeatReservationService seatReservationService;


    //CONSTRUCTOR
    public TicketServiceImpl() {
    }
    //CONSTRUCTOR
    public TicketServiceImpl(TicketPaymentService ticketPaymentService, SeatReservationService seatReservationService) {
        this.ticketPaymentService = ticketPaymentService;
        this.seatReservationService = seatReservationService;
    }


    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {

        if (ticketTypeRequests == null)
            throw new InvalidPurchaseException("The purchase is empty");

        else if(!isAdultChecked( ticketTypeRequests)) {
            throw new InvalidPurchaseException("An Adult is required to complete the purchase");
        }
        else if(numberOfTickets(ticketTypeRequests) >MAX_SEAT_PER_REQUEST){
            throw new InvalidPurchaseException("Only can buy 20 Tickets per request");

        }
        else {
            ticketPaymentService.makePayment(accountId, costTotal);
            seatReservationService.reserveSeat(accountId, numSeats);
        }
    }

    //Calculates number of total tickets
    private int numberOfTickets(TicketTypeRequest... ticketTypeRequests) {

        Arrays.stream(ticketTypeRequests).forEach(ticketTypeRequest -> {
                         numTicketsTotal = numTicketsTotal + ticketTypeRequest.getNoOfTickets();
                         calcCost(ticketTypeRequest.getTicketType(), ticketTypeRequest.getNoOfTickets());});

        return numTicketsTotal;
    }

    //Calculates number of seats and cost per Adult, Child and Infant (Infant doesn't count for seats)
    private void calcCost (TicketTypeRequest.Type type, int numTickets) {
        switch(type) {
            case ADULT -> {costTotal=costTotal+numTickets*PRICE_ADULT;
                        numSeats=numSeats+numTickets;}
            case CHILD -> {costTotal=costTotal+numTickets*PRICE_CHILD;
                            numSeats=numSeats+numTickets;}
            case INFANT -> costTotal=costTotal+numTickets*PRICE_INFANT;
        };
    }

    //Check if at least one Adult ticket is requested
    private boolean isAdultChecked(TicketTypeRequest... ticketTypeRequests) {
        return Arrays.stream(ticketTypeRequests).anyMatch(ticketTypeRequest ->
                (ticketTypeRequest.getTicketType() == TicketTypeRequest.Type.ADULT && ticketTypeRequest.getNoOfTickets()>=1));
    }
}

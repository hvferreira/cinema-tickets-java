CINEMA TICKETS

# Objective

Demonstrate code and approach to a given problem. Implementation of a cinema ticket app, using Java 17.

## Business Rules

- There are 3 types of tickets i.e., Infant, Child, and Adult.
- The ticket prices are based on the type of ticket (see table below).
- The ticket purchaser declares how many and what type of tickets they want to buy.
- Multiple tickets can be purchased at any given time.
- Only a maximum of 20 tickets that can be purchased at a time.
- Infants do not pay for a ticket and are not allocated a seat. They will be sitting on an Adult's lap.
- Child and Infant tickets cannot be purchased without purchasing an Adult ticket.

|   Ticket Type    |     Price   |
| ---------------- | ----------- |
|    INFANT        |    £0       |
|    CHILD         |    £10      |
|    ADULT         |    £20      |

- There is an existing `TicketPaymentService` responsible for taking payments.
- There is an existing `SeatReservationService` responsible for reserving seats.

## Constraints

- The TicketService interface CANNOT be modified.
- The code in the thirdparty. * packages CANNOT be modified.
- The `TicketTypeRequest` SHOULD be an immutable object.

## Assumptions

- All accounts with an id greater than zero are valid. They also have sufficient funds to pay for any tickets.
- The `TicketPaymentService` implementation is an external provider with no defects. No need to worry about how the actual payment happens.
- The payment will always go through once a payment request has been made to the `TicketPaymentService`.
- The `SeatReservationService` implementation is an external provider with no defects. You do not need to worry about how the seat reservation algorithm works.
- The seat will always be reserved once a reservation request has been made to the `SeatReservationService`.

## Task

Provide a working implementation of a `TicketService` that:
- Consider the above objective, business rules, constraints & assumptions.
- Calculates the correct amount for the requested tickets and makes a payment request to the `TicketPaymentService`.
- Calculates the correct number of seats to reserve and makes a seat reservation request to the `SeatReservationService`.
- Rejects any invalid ticket purchase requests. Identify what should be deemed as an invalid purchase request.

## Future Thoughts

- Add front-end
- Add room size and empty seats

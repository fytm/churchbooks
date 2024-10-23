# Design Decisions

## Transactions
We assume unique fit ids. For duplicate transactions only log and send notification but do persist.
Audit log: <strategy for audit log>

## Pending
Start by persisting transaction to db as is. Setup test containers
Then refine fields
Add Payee entity to schema design. insert photo from draw.io

## Decision Log

| Context                                                                                                                     | Options                                                      | Decision |
|-----------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------|----------|
| The application should be able to track effectively both transactions inputted directly by the user and through ofx parsing | Different endpoint for loading from ofx and for direct input |          |
| There should be functionality for specifying recurring payments                                                             | Extra field in request for frequency                         |          |


## Unsupported features
- Storing credit card information

Implementation Details
Entities 
add sql to schema file and use records vs @Entity notation on a class
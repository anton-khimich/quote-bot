# quoteBot
A simple discord bot application.

## Important
Development of this project is paused because my free trial on GCP ran out, currently displaying quotes works, and testing of the save quote command is required.

## List of Future Commands
```c
// Display the quote of the day.
!q
// Display the quote by the given name.
!q <name>
// Saves a quote in the database, has the functionality of UPSERT. The author field is optional.
// Only an admin or the contributor of the existing quote may update the aforementioned quote.
!qsave name:<name>,content:<content>,author:<author>
// Delete the specified quote from the database. Only an admin or the contributor may delete the quote.
!qdelete <name>
// List all quotes with pagination.
!qlist
```

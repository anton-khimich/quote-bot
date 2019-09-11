# quoteBot
A simple discord bot application.

## Link to add the bot to your server
https://discordapp.com/api/oauth2/authorize?client_id=558829263730769920&permissions=2048&scope=bot

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

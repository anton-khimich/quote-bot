# quoteBot
A simple discord bot application.

## Link to add the bot to your server
https://discordapp.com/api/oauth2/authorize?client_id=558829263730769920&permissions=2048&scope=bot

## List of Commands
```c
// Display the quote of the day.
!q
// Add a quote to the database. The author field is optional.
!q add <name> <content> <author>
// Update an existing quote in the database. The author field is optional.
!q update <name> <content> <author>
// Delete the specified quote from the database. Only an admin or the contributor may delete the quote.
!q delete <name>
```

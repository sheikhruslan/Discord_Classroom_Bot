# Discord Chatbot for Classroom Automation

This project implements a Discord chatbot designed to assist students in a classroom setting by automating various tasks such as checking grades, seat assignments, and registration status. The bot is built using the JDA (Java Discord API) and demonstrates object-oriented programming concepts such as polymorphism, abstract classes, and interfaces.

## Features

1. **Ping Bot**: Responds to a ping message.
2. **Seat Checker**: Helps students check their quiz/practical test seat numbers.
3. **User Management Bot**: Associates a student's Discord ID with their student ID using a registration code.
4. **Who Am I Bot**: Allows students to check their registration status and actual name.
5. **Score Bot**: Helps students check their in-class assessment scores.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- A Discord bot account and token
- A Discord server to test your bot

### Setup

1. **Clone the repository:**

    ```sh
    git clone https://github.com/yourusername/discord-chatbot.git
    cd discord-chatbot
    ```

2. **Set up your Discord bot:**

    Follow the instructions [here](https://jda.wiki/using-jda/getting-started/) to create a bot account and obtain a token. Add the bot to your Discord server using the following link (replace `YOUR_SERVER_ID_HERE` with your server ID):

    ```
    https://discord.com/api/oauth2/authorize?client_id=YOUR_SERVER_ID_HERE&scope=bot
    ```

3. **Configure the bot token:**

    Open `src/MyFirstBot.java` and replace the `token` variable with your bot token:

    ```java
    private static final String token = "YOUR_BOT_TOKEN_HERE";
    ```

## Interacting with the Bot
Ping Bot: Type ping in a channel.

Seat Checker: Direct message (DM) the bot with seat.

User Management Bot: Type /registration <registration code> to register yourself.

Who Am I Bot: Type /whoami to check your registration status.

Score Bot: Type /score to check your score.

# Classes and Methods

## CommandBot
An abstract class that handles commands starting with /. Subclasses must implement the following methods:

protected abstract String respond(Command command)

protected abstract String getCommand()

protected abstract String getCommandHelp()

## TextBot
A final class that handles text messages. It keeps a list of MessageListener and calls their onMessageReceived method when a message is received.

## Message and Command

Message: Represents a message sent by a user.

Command: Extends Message and includes options for commands.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgments
JDA (Java Discord API)

Discord

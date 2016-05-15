fourinarow
============

Compile fourinarow-engine

    cd fourinarow-engine
    javac -d bin/ `find ./ -name '*.java' -regex '^[./A-Za-z0-9]*$'`

Compile fourinarow-bot

    cd fourinarow-bot
    gradle build

Execute against default bot

    java -cp /Users/aikmeng/src/theaigames/fourinarow/fourinarow-engine/bin com.theaigames.fourinarow.FourInARow \
    "java -cp /Users/aikmeng/src/theaigames/fourinarow/fourinarow-bot/build/classes/main amang.BotStarter" \
    "java -cp /Users/aikmeng/src/theaigames/fourinarow/fourinarow-bot/build/classes/main bot.BotStarter" \
    2>err.txt \
    1>out.txt

Execute unit tests

    gradle test
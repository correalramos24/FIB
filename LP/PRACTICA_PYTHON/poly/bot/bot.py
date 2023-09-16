

import sys
import os
import signal
sys.path.insert(0, '..')

from telegram.ext import Updater, CommandHandler, Filters, MessageHandler
from cl.myVisitor import *

def help(update, context):
    with open("helpTxt.md", "rt") as helpDoc:
        helpTxt = ""
        for line in helpDoc.readlines():
            helpTxt += (line)
        context.bot.send_message(chat_id=update.effective_chat.id, text=helpTxt,parse_mode='markdown')

def down(update, context):
    context.bot.send_message(chat_id=update.message.chat_id, text="shutdown the BOT.")
    print("Boot offline")
    os.kill(os.getpid(), signal.SIGINT)

def comanda(update, context):
    msg = update.message.text
    msg_tr = "comanda: " + msg
    try:
        #context.bot.send_message(chat_id=update.message.chat_id, text=msg_tr)
        result = str2parse(calc, msg)
        if result != None:
            path = getPathIMG(result)
            if path != None:
                context.bot.send_photo(chat_id=update.message.chat_id,
                photo=open(path, 'rb'))
                os.remove(path)
            else:
                context.bot.send_message(chat_id=update.message.chat_id, text=result)
    except:
        e = sys.exc_info()
        context.bot.send_message(chat_id=update.message.chat_id, text="Alguna cosa no funcionat bé.")
    
    
    


TOKEN = open('token.txt').read().strip()
updater = Updater(token=TOKEN, use_context=True)
dispatcher = updater.dispatcher

calc = myEval()     #objecte eval per mantenir les variables de poligon creades

dispatcher.add_handler(CommandHandler('help', help))
dispatcher.add_handler(CommandHandler('down', down))
updater.dispatcher.add_handler(MessageHandler(Filters.text, comanda))

updater.start_polling()
print("Boot online")
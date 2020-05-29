import requests
import time
import sqlite3 as sq

from bs4 import BeautifulSoup

def change(words):
    words = str(words.strip().replace("'", '"'))
    return words


URL_cam_er = 'https://dictionary.cambridge.org/dictionary/english-russian/'
URL_cam_e = 'https://dictionary.cambridge.org/dictionary/english/'

handle = open(r"words.txt", "r", encoding="utf8")

connection = sq.connect("llen_/app/src/main/assets/databases/words.db")

time = time.time() 
for line in handle:
    parts_of_speech, translation, pronunciation, example1, example2, example3 = '', '', '', '', '', ''
    word_main = line.replace("\n", "")
    if word_main is not "":
        word_cb = word_main.replace(" ", "-")

        URL = URL_cam_er + word_cb
        page = requests.get(URL)
        soup = BeautifulSoup(page.content, 'html.parser')
        temp = soup.find(class_='di-title')

        if temp is None:
            URL = URL_cam_e + word_cb
            page = requests.get(URL)
            soup = BeautifulSoup(page.content, 'html.parser')

            temp = soup.find(class_='pos dpos')
            if temp is not None:
                parts_of_speech = temp.get_text()

            temp = soup.find(class_='ipa dipa lpr-2 lpl-1')
            pronunciation = temp.get_text()

            temp = soup.find(class_='def ddef_d db')
            definition = temp.get_text()

            temp = soup.find(class_='examp dexamp')
            if temp is not None:
                example1 = temp.get_text()

                temp = temp.find_next_sibling(class_='examp dexamp')
                if temp is not None:
                    example2 = temp.get_text()

                    temp = temp.find_next_sibling(class_='examp dexamp')
                    if temp is not None:
                        example3 = temp.get_text()
        else:
            temp = soup.find(class_='pos dpos')
            if temp is not None:
                parts_of_speech = temp.get_text()

            temp = soup.find(class_='ipa dipa')
            if temp is not None:
                pronunciation = temp.get_text()

            temp = soup.find(class_='def ddef_d db')
            definition = temp.get_text()

            temp = soup.find(class_='trans dtrans dtrans-se')
            translation = temp.get_text()

            temp = soup.find(class_='examp dexamp')
            if temp is not None:
                example1 = temp.get_text()

                temp = temp.find_next_sibling(class_='examp dexamp')
                if temp is not None:
                    example2 = temp.get_text()

                    temp = temp.find_next_sibling(class_='examp dexamp')
                    if temp is not None:
                        example3 = temp.get_text()

        sql = "INSERT INTO wordsTop (word, pos, definition, translate, pronunciation, example1, example2) VALUES ('" + change(word_main) + "', '" + change(parts_of_speech) + "', '" + change(definition) + "', '" + change(
                translation) + "', '" + pronunciation.strip() + "', '" + change(example1) + "', '" + change(example2) + "');"

        cursor = connection.cursor()
        cursor.execute(sql)
        connection.commit()

connection.close()
handle.close()

print(time - time.time())

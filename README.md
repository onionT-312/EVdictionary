# EV app - Translate application from English to Vietnamese
This is an application that supports users in translating and learning English 
and Vietnamese. The application provides features such as text translation, 
text-to-speech reading, and a friendly interface to perform these functions 
easily and conveniently. In this app have 2 phases:
- Dictionary Commandline
  - [Word](./src/main/java/evapp/base/Word.java)
  - [Dictionary](./src/main/java/evapp/base/Dictionary.java)
  - [DictionaryCommandline](./src/main/java/evapp/base/DictionaryCommandline.java)
  - [DictionaryManagement](./src/main/java/evapp/base/DictionaryManagement.java)
- Dictionary App
  - [API](./src/main/java/evapp/API.java)
  - [Main Scene](./src/main/java/evapp/mainScene.java)
## Inheritance tree

## Setup Libraray
- Open `Project Structure` 
- Click `+` then click `JARs or Directories`

![tutorial1](./pic/tutorial1.png)
![tutorial2](./pic/tutorial2.png)
- Next step, click all file on folder lib

![tutorial3](./pic/tutorial3.png)
- Finally, click on the `empty box` and `apply` then `OK`

![tutorail4](./pic/tutorial4.png)
## Demo 
- Demo Dictionary Commandline: lookup work in data

![demo1](./pic/demo1.png)
- Demo App interface
  - Search word
    ![demo2](./pic/demo2.gif)
  - Google Translate 
    - English to Vietnamese
      ![demo3](./pic/demo3.png)
    - Vietnamese to English
      ![demo4](./pic/demo4.png)
  - Game
    - Listening game
      ![demoGame1](./pic/demo_listening.gif)
    - Hangman game
      ![demoGame2](./pic/demo_hangman.gif)
    - Pronunciation game
      ![demoGame3](./pic/demo_pronunciation.gif)
## Authors
| No | Name                   | Id            | Github                                            | Mission                                                                                                                                  |
|:---|:-----------------------|:--------------|:--------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------|
| 1  | Phạm Văn Mạnh<br/>     | 21020449<br/> | [@emvipi03](https://github.com/emvipi03)          | Fix code phần CMD, sửa cấu trúc code để code giao diện, hoàn thành package dict và class mainSence để chạy chức năng cơ bản của ứng dụng |
| 2  | Trần Khánh Phương<br/> | 21020147<br/> | [@FuongTran](https://github.com/FuongTran)        | Sửa code phần CMD, hoàn thiện giao diện cơ bản cho ứng dụng, hoàn thành package app để chạy các chức năng game, thêm từ, tra từ online   |
| 3  | Nguyễn Đức Thành<br/>  | 21020456<br/> | [@onionT-312](https://github.com/onionT-312)      | Fix code phần CMD, sửa cấu trúc code để code giao diện, hoàn thành package dict và class mainSence để chạy chức năng cơ bản của ứng dụng |

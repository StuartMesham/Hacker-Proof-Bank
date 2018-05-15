# Hacker-Proof Bank

Back in high school, we got a programming assignment to make a "banking application" that would allow users to log in and out and save each user's information into a separate text file and keep one main text file with all of the users' usernames and passwords. The problem was that a skilled hacker could bypass another user's password by simply opening the text file with that user's information. Even worse, one incredibly talented hacker found that you could see all the other users' passwords by opening up the `users.txt` file. Being the little know-it-all I was, I decided to add a bit of security to my application. I present to you...

![screen shot 2018-05-15 at 20 44 31](https://user-images.githubusercontent.com/28049022/40077081-d8ffea92-5880-11e8-978c-c1c6040c5200.png)
![screen shot 2018-05-15 at 20 50 38](https://user-images.githubusercontent.com/28049022/40077939-6768dcf6-5883-11e8-849d-65f241f1c0a4.png)


Now, when the hackers hack and open up another user's file, all they see is:

```
6nJhYe9WzFSsUh7WBt8QppZ+jpryrLKpVEtbr39Jawu7eanNwYf/wPxGB7UOgtiSkUGiOQZY1BkVKtA6NlLKxg==
```

AND when that one notorious hacker tries their same old exploit of opening up the `users.txt` file:

```
USERNAMES AND HASHED PASSWORDS STORED HERE:
tthompson
�e�Y B/�A~Hg��O��J?��~������z�
smesham
-e�ɚx�䜑�_R�y<���93�M���
e.snowy
I��¯�'}d�(��Vn�|���N���߷�
```

Take that hackers!

The bulletproof security system worked as follows:

Sign up
1. Store an MD5 hash of the user's password in the `users.txt` file.
2. Use the password to generate a key.
3. Use AES to encrypt the user's information with this key and save the encrypted data to a text file.

Log in
1. Check the username and password (by hashing the password and comparing the hashes)
2. Use the password to regenerate the key.
3. Decrypt the user's information with this key.

I have uploaded the passwords for smesham and tthompson for interested people to play around with the banking application. However, e.snowy didn't want people snooping around in his bank account so he decided not to tell anyone his password. Being a bit of a snooper-arounder myself, I'm really curious to know what e.snowy's bank account looks like. If there are any highly skilled hackers reading this, please could you hack into e.snowy's bank account and share the details with me? Thanks.

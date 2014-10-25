# -*- coding:utf-8 -*-

import os, re

__author__ = '7user'

patten = r'一|二|三|四|五|六|七|八|九|十|個|千|萬|億|元|你|我|他|因|的|★|╱|/|】|○|-|　+'


def getdaydic(filepath):
    count = 0
    f1 = open(filepath, "r")
    for line in f1.readlines():
        word_list = line.strip().split(" ")
        if len(word_list) == 0:
            break
        id = filepath[19:]
        file_id = id.split("\\")[0]+id.split("\\")[1]
        f2 = open("D:\\Data\\library\\day\\"+file_id, "a")
        if len(re.findall(patten, word_list[0])) == 0:
            if len(word_list[0].decode("utf-8")) > 4:
                if int(word_list[1]) > 1:
                    result = word_list[0] + ' '+ word_list[1]
                    f2.write(result+"\n")
                    # print result
                    count += 1
            else:
                if int(word_list[1]) > 5:
                    result = word_list[0] + ' '+ word_list[1]
                    f2.write(result+"\n")
                    # print result
                    count += 1
        f2.close()
    f1.close()
    print "There are "+ str(count) + " words in "+ str(file_id) + " file"


def getfilepath(folderpath):
    for pathname, foldername, filename in os.walk(folderpath):
        for file_list in filename:
            if file_list[2:] == ".txt":
                filepath = folderpath + "\\" + file_list
                # print filepath
                getdaydic(filepath)


path = "D:\\Data\\Result\\day"
for pathname, foldername, filename in os.walk(path):
    for folder_list in foldername:
        folderpath = path + "\\" + folder_list
        getfilepath(folderpath)
        # print folderpath


# -*- coding:utf-8 -*-

import os, re

__author__ = '7user'

patten = r'一|二|三|四|五|六|七|八|九|十|個|千|萬|億|元|你|我|他|因|的|★|╱|/|】|○|-|　+'

for ngram in range (2,7):
    path = "D:\\Data\\gram\\"+str(ngram)+"gram"
    for pathname, foldername, filename in os.walk(path):
        for file_list in filename:
            file_id = file_list[14:20]+".txt"
            count = 0
            readpath = path+"\\"+file_list
            f1 = open(readpath, "r")
            for line in f1.readlines():
                word_list = line.strip().split(" ")
                f2 = open("D:\\Data\\library\\month\\"+file_id, "a")
                if len(re.findall(patten, word_list[0])) == 0:
                    if ngram > 4:
                        if int(word_list[1]) > 50:
                            result = word_list[0] + ' '+ word_list[1]
                            f2.write(result+"\n")
                            count += 1
                    else:
                        if int(word_list[1]) > 20:
                            result = word_list[0] + ' '+ word_list[1]
                            f2.write(result+"\n")
                            count += 1
                f2.close()
            f1.close()
            print "There are "+ str(count) + " words for "+ str(ngram) +" gram's result in "+ str(file_id) + " file"

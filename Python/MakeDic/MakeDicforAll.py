# -*- coding:utf-8 -*-

__author__ = '7user'

import os, re

word_dic = {}


def makedic(readpath):
    f = open(readpath, "r")
    for line in f.readlines():
        word_list = line.strip().split(" ")
        if word_list[0] not in word_dic:
            word_dic[word_list[0]] = int(word_list[1])
        else:
            word_dic[word_list[0]] += int(word_list[1])
    f.close()


def dicfile(writepath):
    # print len(word_dic)
    f = open(writepath, "w")
    for word in sorted(word_dic.iterkeys()):
        f.write("%s %s" % (word, word_dic[word])+"\n")
    f.close()
    print 'Done'


path = "E:\\Data\\library\\month"
for pathname, foldername, filename in os.walk(path):
    for file_list in filename:
        readpath = path + "\\" + file_list
        makedic(readpath)
writepath = "E:\\Data\\library\\dic.txt"
dicfile(writepath)


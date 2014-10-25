# -*- coding:utf-8 -*-

__author__ = '7user'

import os, re

eng_dic = {}
sen_dic = {}

cleanCom = r'《|》|，|。|？|：|！|……|——|「|」|,,|、|‧|－|）|（|%|；|,|\.|\(|\)|:|—+'


def getengdic(filepath, gram):
    patten = r'[0-9]*[a-zA-Z]+' + ' ?[0-9]*[a-zA-Z]+'*(gram-1)
    f = open(filepath, 'r')
    for line in f.readlines():
        content = line.split('\t')[2]
        clean_content = re.sub(cleanCom, '|', content).split('|')
        for putSentence in clean_content:
            if putSentence not in '':
                 sen_dic[putSentence] = ''
        for sentence in sen_dic:
            result = re.findall(patten, sentence)
            print '.',
            if len(result) != 0:
                getword(result)


def getword(result):
    for words in result:
        if words not in eng_dic:
            eng_dic[words] = 1
        else:
            eng_dic[words] += 1


def outputdic(writepath):
    f = open(writepath, 'a')
    count = 0
    for word in eng_dic:
        print word + ' ' + str(eng_dic[word])+'\n'
        f.write(word + ' ' + str(eng_dic[word])+'\n')
        count += 1
        print '.',
    return count
    f.close()
    eng_dic.clear()
    sen_dic.clear()



path = "E:\\Data\\rowdata"
for gram in range (1,7):
    for pathname, foldername, filename in os.walk(path):
        for file_list in filename:
            filepath = path + '\\' + file_list
            print 'It starts get ' + str(gram) + ' gram(s) form ' + file_list + ' now!'
            print 'Loading.',
            getengdic(filepath, gram)
            print 'Writing.',
            writepath = 'E:\\Data\\library\\eng\\'+file_list
            resultcount = outputdic(writepath)
            print 'There is ' + str(resultcount) + ' words for' +  str(gram) + ' gram(s) in ' + file_list + 'file!'






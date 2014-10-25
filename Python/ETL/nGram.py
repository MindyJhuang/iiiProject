# -*- coding: utf-8 -*-

__author__ = 'mindy'

import re
'''
from pyspark import SparkContext,  SparkConf

conf = SparkConf().setAppName('simple').setMaster('local')
sc = SparkContext(conf=conf)
'''
doc_dic = {}
sen_dic = {}
words_dic = {}
cleanNum = r'\d+'
cleanCom = r'《|》|，|。|？|：|！|……|——|「|」|,,|、|‧|－|）|（|%|；|,|\.|\(|\)|:|—| +'
findEng = r'[a-zA-Z]+'


def ngram(dic, gram):
    for sentence in dic:
        for start in range(0,  len(sentence.decode('utf-8'))):
            if (start+gram) <= len(sentence.decode('utf-8')):
                words = sentence.decode('utf-8')[start:start+gram]
                if words not in words_dic:
                    words_dic[words] = 1
                else:
                    words_dic[words] += 1
    return words_dic


def cutsentence(doc_dic, gram):
    sen_dic.clear()
    words_dic.clear()
    for content in doc_dic:
        content = content.decode('utf8').strip()
        text = re.sub(r'\w+', '', content).encode('utf-8')  
        text_clean = re.sub(cleanCom, '|', text)  
        newscon = text_clean.split('|') 
        for putSentence in newscon:
            if putSentence not in '':
                sen_dic[putSentence] = ''
    words_list = ngram(sen_dic, gram)
    return words_list


def clean(path, gram):
    doc_dic.clear()
    sen_dic.clear()
    words_dic.clear()
    f = open(path, 'r')
    for line in f.readlines(): 
        doc_dic[line.split('\t')[3]] = ''
    words_list = cutsentence(doc_dic, gram)
    return words_list

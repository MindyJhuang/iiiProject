# -*- coding: utf-8 -*-
#!/usr/bin/env python

import re, sys, subprocess

word_dic = {}
for line in sys.stdin:
    line = line.strip()
    value = line.split('\t')[4]

    com = subprocess.Popen(["hadoop", "fs", "-cat", "WordDic.dic"], stdout=subprocess.PIPE)
    for result in com.stdout:
        word_dic[result.strip().split(' ')[0]] = ''

    # f2 = open('WordDic.txt', 'r')
    # for line in f2.readlines():
    #     word_dic[line.split(' ')[0]] = ''

    for keyword in word_dic:
        keywordlist = re.findall(keyword,value)
        if len(keywordlist) > 0:
            for item in keywordlist:
                print '%s\t%s' % (item, 1)
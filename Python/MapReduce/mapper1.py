# -*- coding: utf-8 -*-
#!/usr/bin/env python

import sys, ahocorasick

tree = ahocorasick.KeywordTree()
f = open('../Result/WordDic.txt', 'r')
for line in f.readlines():
    tree.add(line.split(' ')[0].strip())
tree.make()
f.close()


for line in sys.stdin:
    line = line.strip()
    value = line.split('\t')[4]
    result = tree.findall(value)
    for tuplelist in result:
        start, end = (tuplelist[0],tuplelist[1])
        keywords = value[start:end]
        if keywords is not '':
            print '%s\t%s' % (keywords, 1)


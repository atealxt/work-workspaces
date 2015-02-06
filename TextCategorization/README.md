## Text Categorization

This is a Text (Document) Category machine learning program which is base on algorithms [Tf–idf](http://en.wikipedia.org/wiki/Tf%E2%80%93idf) and [Cosine similarity](http://en.wikipedia.org/wiki/Cosine_similarity).

[data.zip](data/data.zip) is a training data zip file, which have 8 category folders with more than 3 hundred manual classified docs each one.  

### Features

For now all the data/computing are in memory.

* [DocCompareStatisticsTest](test/io/github/atealxt/nlp/analysis/statistics/DocCompareStatisticsTest.java).
  Compare 2 docs with calculate the similarity, a double value between `0` to `1`. `0` means completely unrelated, `1` means exactly the same (except text order inside). 
* [ClassificationStatisticsTest](test/io/github/atealxt/nlp/analysis/statistics/ClassificationStatisticsTest.java).
  Randomly pick up 100 docs from each folder to estimate category, accuracy is about 95% for now. (With a [Stemmer](http://en.wikipedia.org/wiki/Stemming) like Lucene [PorterStemmer](http://svn.apache.org/repos/asf/lucene/dev/trunk/lucene/analysis/common/src/java/org/apache/lucene/analysis/en/PorterStemmer.java) result will be better.)
* [ClusteringStatisticsTest](test/io/github/atealxt/nlp/analysis/statistics/ClusteringStatisticsTest.java).
  Disregards the folder, collect all docs together and clustering convergently.
  At last almost all docs are gathered to one category (root), except noisy samples.

### Step to run

Please make sure you have JDK and Maven, then open command window execute:

```
$ mvn test
```

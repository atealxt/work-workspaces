## Text Categorization

This is a Text (Document) Category machine learning program which is base on [Tf–idf](http://en.wikipedia.org/wiki/Tf%E2%80%93idf) and [Cosine similarity](http://en.wikipedia.org/wiki/Cosine_similarity) algorithm.

[data.zip](data/) is a traning data zip file, which have 8 category folders with more than 3 hundred manual classified docs each one.  

### Features

For now all the data/computing are in memory.

* [DocCompareStatisticsTest](test/io/github/atealxt/nlp/analysis/statistics/DocCompareStatisticsTest.java).
  Compare 2 docs with calculate the similarity, a double value between `0` to `1`. `0` means completely unrelated, `1` means exactly the same (except text order inside). 
* [CategorizationStatisticsTest](test/io/github/atealxt/nlp/analysis/statistics/CategorizationStatisticsTest.java).
  Randomly pick up 100 docs from each folder to estimate category, accuracy is greater than 92%.

### Step to run

Please make sure you have JDK and Maven, then open command window execute:

```
$ mvn test
```

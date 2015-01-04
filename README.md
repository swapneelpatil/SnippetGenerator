SnippetGenerator
================
We don't show the whole review, just a short section that is relevant to the query. How short? Probably shorter than the entire review (which might be a page long), and probably longer than just a single word. 

Following implements a method to finds the most relevant snippet for a document and highlights all the query terms that appear in the snippet. The program provides control on how long the snippet should be and indicates highlights by surrounding the text to be highlighted with [[HIGHLIGHT]]...

[[ENDHIGHLIGHT]]. One highlighting example might be:

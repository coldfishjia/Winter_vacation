# Winter_vacation

## Merge the pdf

纸张的书永远看起来比LCD上面方便…近来在学习Perl,所以想看看他的书,但买不到,好不容易找了一个电子版本的.想打印出来.但是太多了.200多页.这样的话太浪费纸张了.

研究了一下怎么在Linux中给多张合并成一张打印.

要这样使用的话,需要使用到一个命令 pdfnup.它包含在pdfjam这个软件包中,看看你的Linux的发行版本怎么安装.我的是Ubuntu的8.10.

`sudo apt-get install pdfjam`

这样就安装完了.使用的方法也很容易,见下面.

`pdfnup --pages all --nup 2x2`  

这句的–pages是指打印所有的.–nup是网页排列的方式.

下面讲一下网页排列的方式.

--nup 2x1 是指二个页面合并成一个.

--nup 4x1 是指4个页面合并成一个.但这是指的并排的.

--nup 2x2 也是指4个页面合并成一个,但是一个网页它会上面二页,下面二页.这样比较合理.

## 合并的在线网页
[smallpdf](http://smallpdf.com/cn/merge-pdf)


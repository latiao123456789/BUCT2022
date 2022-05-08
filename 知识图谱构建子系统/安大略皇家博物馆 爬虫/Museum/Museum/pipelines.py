# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
from scrapy.pipelines.images import ImagesPipeline
import scrapy
import pymysql


# 文本内容存储
# class MuseumPipeline:
#     fp = None
#
#     # 重写父类的方法：该方法只在开始爬虫的时候被调用一次
#     def open_spider(self, spider):
#         print('开始爬虫...')
#         self.fp = open('./first.txt', 'w', encoding='utf-8')
#
#     # 该方法可以被调用多次
#     # 该方法可以接收爬虫文件过来的item对象
#     def process_item(self, item, spider):
#         Img_name = item['img_name']
#         place = item['place']
#         Date = item['Date']
#         Period = item['Period']
#         Source = item['Source']
#         Description = item['Description']
#         Country = item['Country']
#         # dic = {
#         #     'Place': Place,
#         #     'Date': Date,
#         #     'Period': Period,
#         #     'Source':Source,
#         #     'Description': Description,
#         #     'Country': Country
#         # }
#         # self.fp.write(place+'\n')
#         # self.fp.write('Date:' + Date + '\n')
#         # self.fp.write('Period:' + Period + '\n')
#         self.fp.write('Img_name:' + Img_name + '\n')
#         # self.fp.write('Description:' + Description + '\n')
#         # self.fp.write('Country :' + Country + '\n')
#         return item
#
#     def close_spider(self, spider):
#         print('结束爬虫')
#         self.fp.close()


# 数据库操作
# class MysqlPipeline:
#     #连接数据库
#     conn = None
#     #游标用来操作数据库
#     cursor = None
#
#     def open_spider(self, spider):
#         #实例化一个连接对象
#         self.conn = pymysql.Connect(host='localhost', port=3306, user='root', password='Wzc13145', db='test',
#                                     charset='utf8')
#
#     def process_item(self, item, spider):
#         #创建游标对象
#         self.cursor = self.conn.cursor()
#         #事务处理
#         try:
#             #使用游标对象写sql语句操作数据库
#             self.cursor.execute('insert into test values("%s","%s")' % (item["data1"], item["data2"]))
#             #提交给管道
#             self.conn.commit()
#         #如何出现异常，便打印事务，回滚事务
#         except Exception as e:
#             print(e)
#             self.conn.rollback()
#
#     def close_spider(self, spider):
#         #关闭游标，关闭连接对象
#         self.cursor.close()
#         self.conn.close()

# 图片存储
class ImgPipeLine(ImagesPipeline):
    def get_media_requests(self, item, info):
        yield scrapy.Request(item['src'], meta={'img_name': item['img_name']})

    def file_path(self, request, response=None, info=None, *, item=None):
        img_name = request.url.split('/')[-2]+'.jpg'
        print(img_name)
        return img_name

    def item_completed(self, results, item, info):
        return item

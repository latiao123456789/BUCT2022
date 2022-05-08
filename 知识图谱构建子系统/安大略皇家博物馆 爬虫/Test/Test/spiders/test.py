import scrapy
from Test.items import TestItem


class TestSpider(scrapy.Spider):
    name = 'test'
    # allowed_domains = ['www.xxx.com']
    start_urls = ['https://collections.rom.on.ca/search/China']
    base_url = 'https://collections.rom.on.ca/search/China/objects/images?page=%d'
    # https://collections.rom.on.ca/search/China/objects/images?page=2
    # 观察可得对于安大略皇家博物馆的爬取

    # 首页需单独请求url,剩余页可以通过字符串拼接出url进行请求
    for page in range(2, 3):
        page_url = base_url % page
        print(page_url)
        start_urls.append(page_url)
        # print(start_urls)
        def parse(self, response):
            # 用xpath解析出每一个文物
            div_list = response.xpath('//*[@id="timagesview"]/div')
            # 对一页的文物进行遍历
            for div in div_list:
                item = TestItem()
                src = 'https://collections.rom.on.ca' + div.xpath('./div/div[1]//img/@src').extract_first()
                print(src)
                # 解析文物的名称
                img_name = div.xpath('./div/div[1]//img/@alt').get()
                print(img_name)
                # 从首页解析详情页的url
                detail_url = 'https://collections.rom.on.ca' + div.xpath('./div/div[2]/h3/a/@href').get()
                # print(detail_url)
                # print(item['src'])
                item['src'] = src
                item['img_name'] = img_name
                # 对详情页的url进行手动请求
                yield scrapy.Request(url=detail_url, callback=self.detail_parse, meta={'item': item})
                # 提交到管道
                yield item

        # 回调函数，用于解析详情页的信息
        # 可以从详情页解析到文物的产地，时间即对应的时期，来源,介绍
        def detail_parse(self, response):
            # 接受传参
            item = response.meta['item']
            place = response.xpath(
                '//div[@class="detailField geoDisplayField"]/span[@class="detailFieldValue"]/text()').get()  # 产地
            Date = response.xpath(
                '//div[@class="detailField displayDateField"]/span[@class="detailFieldValue"]/text()').get()  # 时间
            # print(Date)
            Period = response.xpath(
                '//div[@class="detailField periodField"]/span[@class="detailFieldValue"]/text()').get()  # 对应时期
            Source = response.xpath(
                '//div[@class="detailField creditlineField"]/span[@class="detailFieldValue"]/text()').get()  # 来源
            Country = response.xpath(
                '//div[@class="emuseum-detail-category detailField thesconceptsField"]/span[@class="detailFieldValue comma-separated-ul"]/text()').get()  # 来源国
            Description = response.xpath(
                '//div[@class="detailField toggleField descriptionField toggleFieldOpen"]/span[@class="toggleContent normalizeText"]/text()').get()  # 介绍
            item['place'] = place
            item['Date'] = Date
            item['Period'] = Period
            item['Source'] = Source
            item['Country'] = Country
            item['Description'] = Description
            yield item

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class MuseumItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    src = scrapy.Field()
    img_name = scrapy.Field()
    place = scrapy.Field()
    Date = scrapy.Field()
    Period = scrapy.Field()
    Source = scrapy.Field()
    Country = scrapy.Field()
    Description = scrapy.Field()

    # pass

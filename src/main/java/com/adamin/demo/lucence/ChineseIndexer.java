package com.adamin.demo.lucence;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;

/**
 * @Classname ChineseIndexer
 * @Description TODO
 * @Date 2022/4/11 19:46
 * @Created by Adam(https://www.lixiaopeng.top)
 */
public class ChineseIndexer {

    /**
     * 存放索引的位置
     */
    private Directory dir;
    //准备一下用来测试的数据
//用来标识文档
    private Integer ids[] = {1, 2, 3};
    private String citys[] = {"上海", "南京", "青岛"};
    private String descs[] = {
            "上海是个繁华的城市。",
            "Disambig gray.svg  此条目介绍的是中华人民共和国江苏省省会。关于中华民国大陆时期的院辖市，请见“南京市 (中华民国)”。\n" +
                    "坐标：32°02′38″N 118°46′43″E\n" +
                    "\n" +
                    "南京市\n" +
                    "宁\n" +
                    "地级市\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "南京市风光\n" +
                    "上：南京城市天际线、秦淮河与明城墙\n" +
                    "中上：明孝陵石像路、鸡鸣寺\n" +
                    "中下：中山陵祭堂、灵谷寺夜景\n" +
                    "下：夫子庙夜景\n" +
                    "绰号：金陵、石城、天京\n" +
                    "南京市在江苏省的地理位置\n" +
                    "南京市在江苏省的地理位置\n" +
                    "坐标：32°03′N 118°46′E\n" +
                    "国家\t 中华人民共和国\n" +
                    "省\t江苏省\n" +
                    "设立\t前473年建城\n" +
                    "1927年3月24日建市\n" +
                    "政府驻地\t玄武区北京东路41号\n" +
                    "下级行政区\t11市辖区\n" +
                    "政府\n" +
                    " • 市委书记\t韩立明\n" +
                    " • 人大常委会主任\t龙翔\n" +
                    " • 市长\t夏心旻\n" +
                    " • 政协主席\t刘以安\n" +
                    "面积\n" +
                    " • 地级市\t6,587.31 平方千米（2,543.37 平方英里）\n" +
                    "面积排名\t全省第9位（占全江苏省6.14%）\n" +
                    " • 建成区（2020年）\t817.37 平方千米（315.59 平方英里）\n" +
                    "海拔\t34 米（112 英尺）\n" +
                    "最高海拔\t448 米（1,470 英尺）\n" +
                    "人口（2020）\n" +
                    " • 地级市\t942.34万人\n" +
                    " • 排名\t全省第2位（占全江苏省10.99%）\n" +
                    " • 市区（2018[注 1]）\t696.94万人\n" +
                    " • 城镇（2019）\t850.55万人\n" +
                    "语言\n" +
                    " • 母语（方言）\t江淮官话南京话、\n" +
                    "吴语高淳话\n" +
                    "时区\t北京时间（UTC+8）\n" +
                    "邮政编码\t210000-213000\n" +
                    "电话区号\t25\n" +
                    "车辆号牌\t苏A\n" +
                    "气候\t亚热带季风气候\n" +
                    "• 年均温\t15.4 ℃\n" +
                    "• 年降水\t1,062.3毫米\n" +
                    "• 年日照\t1,982.8小时\n" +
                    "行政区划代码\t320100 [3]\n" +
                    "旧称\t金陵、秣陵、建业、建邺、建康、升州、蒋州、集庆、应天、江宁\n" +
                    "城市定位\t东部地区重要的中心城市\n" +
                    "江苏省省会\n" +
                    "国内生产总值（2018）\t¥12820.4亿\n" +
                    "1937.38亿美元（汇率）\n" +
                    "• 人均\t¥152,886\n" +
                    "23,104美元（汇率）\n" +
                    "HDI（2016）\t0.830 极高 [4]\n" +
                    "网站\twww.nanjing.gov.cn\n" +
                    "市象征\n" +
                    "花\t梅花\n" +
                    "树\t雪松\n" +
                    "南京市\n" +
                    "汉语\t南京\n" +
                    "邮政式拼音\tNanking\n" +
                    "[显示]标音\n" +
                    "南京市，简称“宁”，别称金陵、建业、建康。是中华人民共和国江苏省省会、副省级市和特大城市[5]，国家中心城市。地处长江下游沿岸，位于江苏省西南部。是长江下游和长三角城市群重要产业城市、长三角的副中心城市和中国东部暨江苏省的政治、经济、科教、文化、信息中心，华东第二大城市，仅次于上海。[6][7][8]，也是中国综合性交通和通信枢纽城市以及科教中心城市之一，东部战区司令部驻地[9]。\n" +
                    "\n" +
                    "全市下辖11个区[10]，总面积6582.31平方千米[1]，截止2020年11月1日常住人口931.46万，其中城镇人口808.52万人[11]。 南京为政治文化名城，有2500多年建城史和前后近500年建都史[12]，先后有东吴、东晋、南朝宋、齐、梁、陈，南唐、明朝、太平天国、中华民国等朝代或政权定都于此[注 2]，故有“六朝古都”、“十朝都会”之称[注 3]。历史上南京长期是中国南方的政治文化中心，亦长期被视为华夏之正朔所在，为中国首批国家历史文化名城，观光资源十分丰富。",
            "青岛是国家历史文化名城、中国道教发祥地。 [6]  因树木繁多，四季常青而得名。 [7]  1891年清政府驻兵建制， [6]  青岛是2008北京奥运会和第13届残奥会帆船比赛举办城市，是中国帆船之都， [8]  亚洲最佳航海城， [5]  世界啤酒之城、联合国“电影之都” [9]  、全国首批沿海开放城市、全国文明城市、 [10]  中国最具幸福感城市。被誉为“东方瑞士” [11]  、中国品牌之都。2020年9月2日，被交通运输部评为国家公交都市建设示范城市。"
    };


    public void index(String indexDir) throws Exception {
        dir = FSDirectory.open(Paths.get(indexDir));
// 先调用 getWriter 获取IndexWriter对象
        IndexWriter writer = getWriter();
        for (int i = 0; i < ids.length; i++) {
            Document doc = new Document();
// 把上面的数据都生成索引，分别用id、city和desc来标识
            doc.add(new StoredField("id", ids[i]));
            doc.add(new StringField("city", citys[i], Field.Store.YES));
            doc.add(new TextField("desc", descs[i], Field.Store.YES));
//添加文档
            writer.addDocument(doc);
        }
        //close了才真正写到文档中
        writer.close();

    }

    private IndexWriter getWriter() throws Exception {
//使用中文分词器
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
//将中文分词器配到写索引的配置中
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
//实例化写索引对象
        IndexWriter writer = new IndexWriter(dir, config);
        return writer;
    }


    public static void main(String[] args) throws Exception {
        new ChineseIndexer().index("E:\\springboot\\demo\\lucence2");
    }
    }


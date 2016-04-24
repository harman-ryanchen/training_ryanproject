package com.example.ryan.hkgankio.bean;

import java.util.List;

/**
 * Created by ryan on 4/23/16.
 */
public class DailyNewsBean {

    /**
     * date : 20160423
     * stories : [{"images":["http://pic3.zhimg.com/df90571c54986a9a408a09761999146a.jpg"],"type":0,"id":8199646,"ga_prefix":"042319","title":"一场由蚕丝引发的战争"},{"images":["http://pic3.zhimg.com/67031e7a21a9973ce9eb329bcea4cc1e.jpg"],"type":0,"id":8192796,"ga_prefix":"042318","title":"《米店》原唱张玮玮：米店不是卖米的，下首歌也许叫粮站"},{"images":["http://pic4.zhimg.com/679c4c6d404574d8b1119d09b0763a13.jpg"],"type":0,"id":8202224,"ga_prefix":"042317","title":"我希望我爱的人能更有尊严地离开"},{"images":["http://pic1.zhimg.com/b79865af6a659ddb98af4884fe682618.jpg"],"type":0,"id":8185983,"ga_prefix":"042316","title":"自卑不一定是气场不足，反而可能是因为气场太强大"},{"images":["http://pic1.zhimg.com/fd6b9124259e73ca407522e6e68582d4.jpg"],"type":0,"id":8196968,"ga_prefix":"042315","title":"数据告诉你：为何大龄单身不算事儿"},{"images":["http://pic4.zhimg.com/3ac5f73b4d25b1cd055263a416635893.jpg"],"type":0,"id":8163821,"ga_prefix":"042314","title":"想改变自己的「蚯蚓字」，先从一笔一画开始练"},{"images":["http://pic1.zhimg.com/01350102a0e9db0dfad4b2610c474a88.jpg"],"type":0,"id":8182125,"ga_prefix":"042313","title":"哪些食品更容易造假？我列举了六种常见的"},{"title":"大误 · 打赌你一定不知道中华小当家是谁","ga_prefix":"042312","images":["http://pic1.zhimg.com/8a5a820c7df9c9b71408247411a79458.jpg"],"multipic":true,"type":0,"id":8187857},{"images":["http://pic1.zhimg.com/1cfc05a713843649ad31b1f0362dea78.jpg"],"type":0,"id":8198643,"ga_prefix":"042311","title":"懒癌不用救，懒人经济的时代正在悄悄到来"},{"title":"一家寿司店，竟然从来不用酱油","ga_prefix":"042310","images":["http://pic1.zhimg.com/657648faa14ce8b5bda41f5873bf8c44.jpg"],"multipic":true,"type":0,"id":8200638},{"images":["http://pic4.zhimg.com/1cba335c35efbfe6cb0d50030868f02f.jpg"],"type":0,"id":8200670,"ga_prefix":"042308","title":"发现校服有怪味、质量差，可以这么去维权"},{"images":["http://pic4.zhimg.com/67cb5fc58c9e6a853cc8a7ce4aebdbc3.jpg"],"type":0,"id":8180854,"ga_prefix":"042307","title":"穷是因为懒、被骗因为笨、被性侵因为穿的少\u2014\u2014为什么会有这些「奇葩」思想？"},{"images":["http://pic1.zhimg.com/0d237c1c17948755bc824b68639b5904.jpg"],"type":0,"id":8199656,"ga_prefix":"042307","title":"关于豆浆这款「国民饮料」你可能好奇的 7 个真相"},{"images":["http://pic2.zhimg.com/a3c4fce71b2d79d300206d784e63de15.jpg"],"type":0,"id":8192586,"ga_prefix":"042307","title":"周末干什么 · 从睁眼到起床这段时间，如何度过？"},{"images":["http://pic4.zhimg.com/a4f7d42a78ff634ca14bee30c93c149f.jpg"],"type":0,"id":8200595,"ga_prefix":"042307","title":"读读日报 24 小时热门 TOP 5 · 差点被处死是一种怎样的经历？"},{"images":["http://pic3.zhimg.com/5835f4b684bca4062d04ca2dc5b6428a.jpg"],"type":0,"id":8195360,"ga_prefix":"042306","title":"瞎扯 · 你们中国好奇怪"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/9ad7d46367e494045debca741b5840dc.jpg","type":0,"id":8192796,"ga_prefix":"042318","title":"《米店》原唱张玮玮：米店不是卖米的，下首歌也许叫粮站"},{"image":"http://pic3.zhimg.com/2ef42262f65f43ca579487287e033702.jpg","type":0,"id":8185983,"ga_prefix":"042316","title":"自卑不一定是气场不足，反而可能是因为气场太强大"},{"image":"http://pic1.zhimg.com/55e8828fb5fa1f72ebe6d5e9a3f36838.jpg","type":0,"id":8187857,"ga_prefix":"042312","title":"大误 · 打赌你一定不知道中华小当家是谁"},{"image":"http://pic2.zhimg.com/d75d6fbcc2b1dbb2e57d4a18414953b9.jpg","type":0,"id":8200595,"ga_prefix":"042307","title":"读读日报 24 小时热门 TOP 5 · 差点被处死是一种怎样的经历？"},{"image":"http://pic1.zhimg.com/eb7348f144a4e15cf19e8e3794d8d2e8.jpg","type":0,"id":8198643,"ga_prefix":"042311","title":"懒癌不用救，懒人经济的时代正在悄悄到来"}]
     */

    private String date;
    /**
     * images : ["http://pic3.zhimg.com/df90571c54986a9a408a09761999146a.jpg"]
     * type : 0
     * id : 8199646
     * ga_prefix : 042319
     * title : 一场由蚕丝引发的战争
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic1.zhimg.com/9ad7d46367e494045debca741b5840dc.jpg
     * type : 0
     * id : 8192796
     * ga_prefix : 042318
     * title : 《米店》原唱张玮玮：米店不是卖米的，下首歌也许叫粮站
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }


}
